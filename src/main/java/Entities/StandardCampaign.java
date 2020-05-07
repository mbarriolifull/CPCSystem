package Entities;

import DataValues.Budget;
import DataValues.ID;
import CampaignState.Active;
import CampaignState.Finished;
import CampaignState.StateCampaign;
import Repositories.ClickRepository;

import java.util.List;

public class StandardCampaign implements Campaign {

    private ID id;
    private Budget budget;
    private StateCampaign stateCampaign;
    private ClickRepository chargedClicks;

    public StandardCampaign(ID id, Budget budget) {
        this.id = id;
        this.budget = budget;
        stateCampaign = new Active();
        chargedClicks = new ClickRepository();
    }

    @Override
    public void pause() {
        stateCampaign.pause(this);
    }

    @Override
    public void activate() {
        stateCampaign.activate(this);
    }

    @Override
    public void charge(Click click) {
        if(!isduplicated(click)){
            stateCampaign.charge(this, click);
        }
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
            stateCampaign.finish(this);
        }
        chargedClicks.add(click);
    }

    @Override
    public double remainingBudget() {
        return budget.getBudget();
    }

    @Override
    public boolean isFinished() {
        if (Finished.class == stateCampaign.getClass()){
            return true;
        }
        return false;
    }

    @Override
    public void setStateCampaign(StateCampaign stateCampaign) {
        this.stateCampaign = stateCampaign;
    }

    public boolean isduplicated(Click click) {
        List<Click> sameUserClick = chargedClicks.retrieveSameUserClicks(click);
        for (Click currentClick : sameUserClick){
            if(click.lessThan15seconds(currentClick)){
                return true;
            }
        }
        return false;
    }
}
