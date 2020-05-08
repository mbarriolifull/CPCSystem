import Builder.ClickBuilder;
import DataValues.ClickID;
import DataValues.ClickType;
import DataValues.ID;
import Entities.Click;
import Repositories.ClickRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClickRepositoryShould {

    @Test
    public void add_Clicks_to_repository() {

        ClickRepository clickRepository = new ClickRepository();

        Date clickDate = new Date();
        ClickType clickType = new ClickType(false);
        ClickID clickID = new ClickID(2);
        ID userID = new ID(3);
        Click click = new ClickBuilder(clickID)
                .setDate(clickDate)
                .setUsersID(userID)
                .setIsPremium(clickType)
                .build();

        clickRepository.add(click);

        boolean isEmpty = clickRepository.isEmpty();

        assertEquals(false, isEmpty);
    }

    @Test
    public void retrieve_a_click_list_with_clicks_from_the_same_user(){
        ClickRepository clickRepository = new ClickRepository();
        ID userID1 = new ID(3);
        ID userID2 = new ID(4);

        Date clickDate = new Date();
        ClickType clickType = new ClickType(false);
        ClickID click1ID = new ClickID(2);
        ClickID click2ID = new ClickID(3);

        Click click1 = new ClickBuilder(click1ID)
                .setDate(clickDate)
                .setUsersID(userID1)
                .setIsPremium(clickType)
                .build();
        Click click2 = new ClickBuilder(click2ID)
                .setDate(clickDate)
                .setUsersID(userID2)
                .setIsPremium(clickType)
                .build();


        clickRepository.add(click1);
        clickRepository.add(click2);

        List<Click> retrievedList = clickRepository.retrieveSameUserClicks(click1);
        List<Click> expectedList = new ArrayList<>();
        expectedList.add(click1);

        assertEquals(expectedList, retrievedList);

    }

    @Test
    public void retrieve_clicks_since_given_date(){
        ClickRepository clickRepository = new ClickRepository();
        ID userID = new ID(3);
        ID user2ID = new ID(4);

        Date click1Date = new Date(2020, 5, 7, 10, 0, 0);
        Date click2Date = new Date(2020, 5, 7, 10, 30, 0);
        Date click3Date = new Date(2020, 5, 7, 11, 0, 0);
        Date click4Date = new Date(2020, 5, 7, 11, 30, 0);
        ClickType clickType = new ClickType(false);
        ClickID click1ID = new ClickID(5);
        ClickID click2ID = new ClickID(6);
        ClickID click3ID = new ClickID(7);
        ClickID click4ID = new ClickID(8);

        Click click1 = new ClickBuilder(click1ID)
                .setDate(click1Date)
                .setUsersID(userID)
                .setIsPremium(clickType)
                .build();
        Click click2 = new ClickBuilder(click2ID)
                .setDate(click2Date)
                .setUsersID(user2ID)
                .setIsPremium(clickType)
                .build();
        Click click3 = new ClickBuilder(click3ID)
                .setDate(click3Date)
                .setUsersID(userID)
                .setIsPremium(clickType)
                .build();
        Click click4 = new ClickBuilder(click4ID)
                .setDate(click4Date)
                .setUsersID(userID)
                .setIsPremium(clickType)
                .build();

        clickRepository.add(click1);
        clickRepository.add(click2);
        clickRepository.add(click3);
        clickRepository.add(click4);

        Date fakeClickDate = new Date(2020,5,7,10,50,0);
        List<Click> retrievedList = clickRepository.clicksSince(fakeClickDate);
        List<Click> expectedList = new ArrayList<>();
        expectedList.add(click3);
        expectedList.add(click4);

        assertEquals(expectedList, retrievedList);

    }

    @Test
    public void remove_clicks_from_repository(){
        ClickRepository clickRepository = new ClickRepository();

        Date clickDate = new Date();
        ClickType clickType = new ClickType(false);
        ClickID clickID = new ClickID(2);
        ID userID = new ID(3);
        Click click = new Click(clickID, clickDate, userID, clickType);

        clickRepository.add(click);
        clickRepository.remove(click);

        boolean isEmpty = clickRepository.isEmpty();

        assertEquals(true, isEmpty);
    }
}
