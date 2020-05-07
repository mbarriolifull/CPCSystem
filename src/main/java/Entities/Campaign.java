package Entities;

import CampaignState.Active;
import CampaignState.Finished;
import CampaignState.StateCampaign;
import DataValues.ID;
import Repositories.ClickRepository;

import java.util.List;

public abstract class Campaign {
    private ID id;
    private StateCampaign stateCampaign;
    private ClickRepository chargedClicks;

    public Campaign(ID id) {
        this.id = id;
        stateCampaign = new Active();
        chargedClicks = new ClickRepository();
    }

    public void pause(){
        stateCampaign.pause(this);
        }
    public void activate(){
        stateCampaign.activate(this);
    }
    public void charge(Click click){
        if(!isduplicated(click)){
            stateCampaign.charge(this, click);
        }
    }
    public void setStateCampaign(StateCampaign stateCampaign){
        this.stateCampaign = stateCampaign;
    }

    public boolean isduplicated(Click click){
        List<Click> sameUserClick = chargedClicks.retrieveSameUserClicks(click);
        for (Click currentClick : sameUserClick){
            if(click.lessThan15seconds(currentClick)){
                return true;
            }
        }
        return false;
    }

    public abstract void chargeToBudget(Click click);

    public abstract double remainingBudget();

    public boolean isFinished(){
        if(Finished.class == stateCampaign.getClass()){
            return true;
        }
        return false;
    }

    public void finishCampaign() {
        stateCampaign.finish(this);
    }

    protected void addClick(Click click) {
        chargedClicks.add(click);
    }
}