import Builder.ClickBuilder;
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
        Click click = new ClickBuilder(clickID)
                .setDate(clickDate)
                .setUsersID(userID)
                .setIsPremium(isPremium)
                .build();

        Date click2Date = new Date(2020, 5, 7, 11, 0, 0);
        ID click2ID = new ID(3);
        Click click2 = new ClickBuilder(click2ID)
                .setDate(click2Date)
                .setUsersID(userID)
                .setIsPremium(isPremium)
                .build();

        boolean lessThan15SecondsDifference = click.lessThan15seconds(click2);
        assertEquals(false, lessThan15SecondsDifference);
    }

    @Test
    public void state_that_2_clicks_are_done_with_a_difference_below_15_seconds(){
        Date clickDate = new Date(2020, 5, 7, 10, 0, 0);
        Boolean isPremium = false;
        ID clickID = new ID(2);
        ID userID = new ID(3);
        Click click = new ClickBuilder(clickID)
                .setDate(clickDate)
                .setUsersID(userID)
                .setIsPremium(isPremium)
                .build();

        Date click2Date = new Date(2020, 5, 7, 10, 0, 5);
        ID click2ID = new ID(3);
        Click click2 = new ClickBuilder(click2ID)
                .setDate(click2Date)
                .setUsersID(userID)
                .setIsPremium(isPremium)
                .build();

        boolean lessThan15SecondsDifference = click.lessThan15seconds(click2);
        assertEquals(true, lessThan15SecondsDifference);
    }

    @Test
    public void check_that_a_click_is_from_some_user(){
        Date clickDate = new Date(2020, 5, 7, 10, 0, 0);
        Boolean isPremium = false;
        ID clickID = new ID(2);
        ID userID = new ID(3);
        Click click = new ClickBuilder(clickID)
                .setDate(clickDate)
                .setUsersID(userID)
                .setIsPremium(isPremium)
                .build();

        boolean isFrom = click.isFrom(new ID(3));
        assertEquals(true, isFrom);
    }

    @Test
    public void check_that_a_click_is_not_from_some_user(){
        Date clickDate = new Date(2020, 5, 7, 10, 0, 0);
        Boolean isPremium = false;
        ID clickID = new ID(2);
        ID userID = new ID(3);
        Click click = new ClickBuilder(clickID)
                .setDate(clickDate)
                .setUsersID(userID)
                .setIsPremium(isPremium)
                .build();

        boolean isFrom = click.isFrom(new ID(4));
        assertEquals(false, isFrom);
    }

    @Test
    public void check_that_a_click_is_older_than_some_date(){
        Date clickDate = new Date(2020, 5, 7, 10, 0, 0);
        Boolean isPremium = false;
        ID clickID = new ID(2);
        ID userID = new ID(3);
        Click click = new ClickBuilder(clickID)
                .setDate(clickDate)
                .setUsersID(userID)
                .setIsPremium(isPremium)
                .build();

        Date someDate = new Date(2020, 5, 7, 9, 0, 0);
        boolean isOlder = click.olderThan(someDate);
        assertEquals(true, isOlder);
    }

    @Test
    public void check_that_a_click_is_Not_older_than_some_date(){
        Date clickDate = new Date(2020, 5, 7, 10, 0, 0);
        Boolean isPremium = false;
        ID clickID = new ID(2);
        ID userID = new ID(3);
        Click click = new ClickBuilder(clickID)
                .setDate(clickDate)
                .setUsersID(userID)
                .setIsPremium(isPremium)
                .build();

        Date someDate = new Date(2020, 5, 7, 11, 0, 0);
        boolean isOlder = click.olderThan(someDate);
        assertEquals(false, isOlder);
    }
}
