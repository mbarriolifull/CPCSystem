import DataValues.ID;
import Entities.Click;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClickShould {
    @Test
    public void state_that_2_clicks_are_done_with_a_difference_greater_than_15_seconds(){
        Date clickDate = new Date(2020, 5, 7, 10, 0, 0);
        Boolean isPremium = false;
        ID clickID = new ID(2);
        ID userID = new ID(3);
        Click click = new Click(clickID, clickDate, userID, isPremium);

        Date click2Date = new Date(2020, 5, 7, 11, 0, 0);
        ID click2ID = new ID(3);
        Click click2 = new Click(click2ID, click2Date, userID, isPremium);

        boolean lessThan15SecondsDifference = click.lessThan15seconds(click2);
        assertEquals(false, lessThan15SecondsDifference);
    }

    @Test
    public void state_that_2_clicks_are_done_with_a_difference_below_15_seconds(){
        Date clickDate = new Date(2020, 5, 7, 10, 0, 0);
        Boolean isPremium = false;
        ID clickID = new ID(2);
        ID userID = new ID(3);
        Click click = new Click(clickID, clickDate, userID, isPremium);

        Date click2Date = new Date(2020, 5, 7, 10, 0, 5);
        ID click2ID = new ID(3);
        Click click2 = new Click(click2ID, click2Date, userID, isPremium);

        boolean lessThan15SecondsDifference = click.lessThan15seconds(click2);
        assertEquals(true, lessThan15SecondsDifference);
    }
}
