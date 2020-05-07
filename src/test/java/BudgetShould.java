import DataValues.Budget;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BudgetShould {
    @Test
    public void lower_budget_whenever_it_is_charged(){
        Budget budget = new Budget(100);

        budget.charge(0.05);
        double expectedBudget = 99.95;
        double remainingBudget = budget.getBudget();
        assertEquals(expectedBudget, remainingBudget);
    }

    @Test
    public void lower_the_budget_two_times_and_give_proper_result(){
        Budget budget = new Budget(100);

        budget.charge(0.01);
        budget.charge(0.01);

        double expectedBudget = 99.98;
        double remainingBudget = budget.getBudget();
        assertEquals(expectedBudget, remainingBudget);
    }
}
