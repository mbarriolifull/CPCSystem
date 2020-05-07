package Entities;

import DataValues.Budget;
import DataValues.ID;

import java.util.Date;
import java.util.List;

public class StandardCampaign extends Campaign {

    private Budget budget;

    public StandardCampaign(ID id, Budget budget) {
        super(id);
        this.budget = budget;
    }

    @Override
    public void chargeToBudget(Click click) {
        if (!click.isPremium()) {
            budget.charge(0.01);
        }
        if (click.isPremium()) {
            budget.charge(0.05);
        }
        if (budget.getBudget() <= 0){
            super.finishCampaign();
            //stateCampaign.finish(this);
        }
        super.addClick(click);
        //chargedClicks.add(click);
    }

    @Override
    public double remainingBudget() {
        return budget.getBudget();
    }

    @Override
    public void fakeClicks(Date date, ID userid){
        List<Click> clicksSinceDate = super.retrieveCicksSince(date);
        for (Click currentClick : clicksSinceDate){
            calculateRefund(userid, currentClick);
        }
    }

    private void calculateRefund(ID userid, Click currentClick) {
        if (currentClick.isFrom(userid)){
            calculateRefundType(currentClick);
            super.removeClick(currentClick);
        }
    }

    private void calculateRefundType(Click currentClick) {
        if (currentClick.isPremium()){
            budget.refund(0.05);
        }
        if (!currentClick.isPremium()){
            budget.refund(0.01);
        }
    }
}
