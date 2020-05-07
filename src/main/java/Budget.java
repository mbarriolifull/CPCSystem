public class Budget {
    private double budget;

    public Budget(double budget) {
        this.budget = budget;
    }

    public double getBudget(){
        return budget;
    }

    public void charge(double clickPrice) {
        budget -= clickPrice;
    }
}
