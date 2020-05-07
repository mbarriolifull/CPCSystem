package Entities;

import DataValues.Budget;
import DataValues.ID;
import CampaignState.*;
import Repositories.ClickRepository;

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

}
