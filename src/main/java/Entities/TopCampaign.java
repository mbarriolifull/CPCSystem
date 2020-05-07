package Entities;

import CampaignState.*;
import CampaignState.StateCampaign;
import DataValues.Budget;
import DataValues.ID;
import Repositories.ClickRepository;

import java.util.List;

public class TopCampaign extends Campaign {

    private Budget budget;


    public TopCampaign(ID id, Budget budget) {
        super(id);
        this.budget = budget;
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


}
