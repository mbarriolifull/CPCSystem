package CampaignState;
import Entities.*;

public class Paused implements StateCampaign {
    @Override
    public void pause(Campaign campaign) {

    }

    @Override
    public void activate(Campaign campaign) {
        campaign.setStateCampaign(new Active());
    }

    @Override
    public void finish(Campaign campaign) {

    }

    @Override
    public void charge(Campaign campaign, Click click) {

    }
}
