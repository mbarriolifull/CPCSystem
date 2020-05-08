package Entities;

import java.util.Objects;

public class Budget {
    private double budget;

    public Budget(double budget) {
        this.budget = budget;
    }

    public Budget(Budget budget) {
        this.budget = budget.budget;
    }


    public void charge(double clickPrice) {
        budget -= clickPrice;
        budget = Math.round(budget * 100.0)/ 100.0;
    }

    public void refund(double clickPrice) {
        budget += clickPrice;
        budget = Math.round(budget * 100.0)/ 100.0;

    }

    public boolean isFinished() {
        if (budget <= 0){
            return true;
        }
        return false;
    }

    public double getFivePercent() {
        return (5*budget)/100;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Budget budget1 = (Budget) o;
        return Double.compare(budget1.budget, budget) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(budget);
    }
}
