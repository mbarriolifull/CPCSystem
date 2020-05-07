public class StandardCampaign implements Campaign {

    private ID id;
    private Budget budget;
    private StateCampaign stateCampaign;

    public StandardCampaign(ID id, Budget budget) {
        this.id = id;
        this.budget = budget;
        stateCampaign = new Active();
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
        stateCampaign.charge(this, click);
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

    public void setStateCampaign(StateCampaign stateCampaign) {
        this.stateCampaign = stateCampaign;
    }
}
