import DataValues.Budget;
import Entities.Click;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BudgetShould {
    @Test
    public void lower_budget_whenever_it_is_charged(){
        Budget budget = new Budget(100);

        budget.charge(0.05);
        Budget expectedBudget = new Budget(99.95);
        //double remainingBudget = budget.getBudget();
        assertEquals(expectedBudget, budget);
    }

    @Test
    public void lower_the_budget_two_times_and_give_proper_result(){
        Budget budget = new Budget(100);

        budget.charge(0.01);
        budget.charge(0.01);

        Budget expectedBudget = new Budget(99.98);
        //double remainingBudget = budget.getBudget();
        assertEquals(expectedBudget, budget);
    }

    @Test
    public void raise_budge_when_refunding_a_clicks_price(){
        Budget budget = new Budget(100);
        budget.charge(0.01);
        budget.charge(0.05);
        budget.charge(0.01);

        budget.refund(0.01);

        Budget expectedBudget = new Budget(99.94);
        //double remainingBudget = budget.getBudget();
        //double remainingBudget = budget.getBudget();
        assertEquals(expectedBudget, budget);
    }
}
