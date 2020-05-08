package Entities;

import CampaignState.Active;
import CampaignState.Finished;
import CampaignState.StateCampaign;
import DataValues.CampaignID;
import DataValues.UserID;
import Repositories.ClickRepositoryInterface;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public abstract class Campaign{
    private CampaignID campaignId;
    private StateCampaign stateCampaign;
    private ClickRepositoryInterface chargedClicks;

    public Campaign(CampaignID campaignId, ClickRepositoryInterface chargedClicks) {
        this.campaignId = campaignId;
        stateCampaign = new Active();
        this.chargedClicks = chargedClicks;
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

    public List<Click> retrieveCicksSince(Date date){
        return chargedClicks.clicksSince(date);
    }

    public void removeClick(Click click){
        chargedClicks.remove(click);
    }

    public abstract void fakeClicks(Date date, UserID userID);

    public abstract void chargeToBudget(Click click);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campaign campaign = (Campaign) o;
        return Objects.equals(campaignId, campaign.campaignId) &&
                stateCampaign.getClass() == campaign.stateCampaign.getClass() &&
                Objects.equals(chargedClicks, campaign.chargedClicks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(campaignId, stateCampaign, chargedClicks);
    }
}
