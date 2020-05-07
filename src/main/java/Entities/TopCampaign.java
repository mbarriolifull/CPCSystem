package Entities;

import DataValues.Budget;
import DataValues.ID;

import java.util.Date;
import java.util.List;

public class TopCampaign extends Campaign {

    private Budget budget;
    private Budget initialBudget;


    public TopCampaign(ID id, Budget budget) {
        super(id);
        this.budget = budget;
        this.initialBudget = new Budget(budget);
    }

    @Override
    public void chargeToBudget(Click click) {
        if (!click.isPremium()) {
            budget.charge(0.1);
        }
        if (click.isPremium()) {
            budget.charge(0.2);
        }
        if (budget.getBudget() <= 0){
            super.finishCampaign();
        }
        super.addClick(click);
    }

    @Override
    public double remainingBudget() {
        return budget.getBudget();
    }

    @Override
    public void fakeClicks(Date date, ID userID) {
        List<Click>  clicksSinceDate = super.retrieveCicksSince(date);
        double totalFakeClickPrice = 0;
        double totalClickPriceSinceDate = 0;
        for (Click currentClick : clicksSinceDate) {
            if (currentClick.isPremium()) {
                if (currentClick.isFrom(userID)) {
                    totalFakeClickPrice += 0.2;
                }
                totalClickPriceSinceDate += 0.2;
            }
            if (!currentClick.isPremium()) {
                if (currentClick.isFrom(userID)){
                    totalFakeClickPrice += 0.1;
                }
                totalClickPriceSinceDate += 0.1;
            }
            super.removeClick(currentClick);

        }
        double fivePercentOfTotalBudget = (5*initialBudget.getBudget())/100;
        if(totalFakeClickPrice < fivePercentOfTotalBudget){
            budget.refund(totalClickPriceSinceDate);
        }
        else{
            budget.refund(totalFakeClickPrice);
        }
    }


}
