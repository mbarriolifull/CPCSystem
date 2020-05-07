public interface StateCampaign {
    void pause(Campaign campaign);
    void activate(Campaign campaign);
    void finish(Campaign campaign);
    void charge(Campaign campaign, Click click);
}
