public class Active implements StateCampaign {
    @Override
    public void pause(Campaign campaign) {
        campaign.setStateCampaign(new Paused());
    }

    @Override
    public void activate(Campaign campaign) {

    }

    @Override
    public void finish(Campaign campaign) {
        campaign.setStateCampaign(new Finished());
    }

    @Override
    public void charge(Campaign campaign, Click click) {
        campaign.chargeToBudget(click);
    }
}
