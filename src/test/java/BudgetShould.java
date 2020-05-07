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
}
