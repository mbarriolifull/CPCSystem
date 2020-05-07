public class StandardCampaign implements Campaign {

    private ID id;
    private Budget budget;
    private CampaignState campaignState;

    public StandardCampaign(ID id, Budget budget) {
        this.id = id;
        this.budget = budget;
        campaignState = CampaignState.ACTIVE;
    }

    @Override
    public void pause() {
        campaignState = CampaignState.PAUSED;
    }

    @Override
    public void activate() {

    }

    @Override
    public void charge(Click click) {
        if (campaignState.equals(CampaignState.ACTIVE)) {
            chargeToBudget(click);
        }
    }

    private void chargeToBudget(Click click) {
        if (!click.isPremium()) {
            budget.charge(0.01);
        }
        if (click.isPremium()) {
            budget.charge(0.05);
        }
    }

    @Override
    public double remainingBudget() {
        return budget.getBudget();
    }
}
