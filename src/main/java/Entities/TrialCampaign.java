package Entities;

import CampaignState.*;
import CampaignState.StateCampaign;
import DataValues.ID;
import Repositories.ClickRepository;

import java.util.List;

public class TrialCampaign implements Campaign {
    private ID id;
    private StateCampaign stateCampaign;
    private ClickRepository chargedClicks;

    public TrialCampaign(ID id) {
        this.id = id;
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
        chargedClicks.add(click);
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
        this.stateCampaign = stateCampaign;
    }

    @Override
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
