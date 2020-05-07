public class StandardCampaign implements Campaign {

    private ID id;
    private Budget budget;
    private boolean active;

    public StandardCampaign(ID id, Budget budget) {
        this.id = id;
        this.budget = budget;
        active = true;
    }

    @Override
    public void pause() {

    }

    @Override
    public void activate() {

    }

    @Override
    public void charge(Click click) {
        if (!click.isPremium()){
            budget.charge(0.01);
        }
        if (click.isPremium()){
            budget.charge(0.05);
        }
    }

    @Override
    public double remainingBudget() {
        return budget.getBudget();
    }
}
