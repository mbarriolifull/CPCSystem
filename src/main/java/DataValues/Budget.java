package DataValues;

public class Budget {
    private double budget;

    public Budget(double budget) {
        this.budget = budget;
    }

    public Budget(Budget budget) {
        this.budget = budget.getBudget();
    }

    public double getBudget(){
        if (budget%1 == 0)
            return budget;
        return Math.round(budget * 100.0) / 100.0;
    }

    public void charge(double clickPrice) {
        budget -= clickPrice;
    }

    public void refund(double clickPrice) {
        budget += clickPrice;

    }
}
