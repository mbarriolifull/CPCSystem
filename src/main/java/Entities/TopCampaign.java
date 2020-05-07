package Entities;

import CampaignState.*;
import CampaignState.StateCampaign;
import DataValues.Budget;
import DataValues.ID;
import Repositories.ClickRepository;

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
        List<Click>  fakeClicks = super.retrieveFakeClicks(date, userID);
        double totalFakeClickPrice = 0;
        for (Click currentClick : fakeClicks){
            if (currentClick.isPremium()){
                totalFakeClickPrice += 0.2;
            }
            if (!currentClick.isPremium()){
                totalFakeClickPrice += 0.1;
            }
            super.removeClick(currentClick);
        }
        double fivePercentOfTotalBudget = (5*initialBudget.getBudget())/100;
        if(totalFakeClickPrice < fivePercentOfTotalBudget){
            budget = initialBudget;
        }
        else{
            budget.refund(totalFakeClickPrice);
        }
    }


}
