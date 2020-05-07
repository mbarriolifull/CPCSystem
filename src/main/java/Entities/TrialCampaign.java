package Entities;

import CampaignState.StateCampaign;
import DataValues.ID;

public class TrialCampaign implements Campaign {
    private ID id;

    @Override
    public void pause() {

    }

    @Override
    public void activate() {

    }

    @Override
    public void charge(Click click) {

    }

    @Override
    public void chargeToBudget(Click click) {

    }

    @Override
    public double remainingBudget() {
        return 0;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void setStateCampaign(StateCampaign stateCampaign) {

    }
}
