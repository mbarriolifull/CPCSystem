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
        Boolean isPremium = false;
        ID clickID = new ID(2);
        ID userID = new ID(3);
        Click click = new Click(clickID, clickDate, userID, isPremium);

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
        Boolean isPremium = false;
        ID click1ID = new ID(2);
        ID click2ID = new ID(3);

        Click click1 = new Click(click1ID, clickDate, userID1, isPremium);
        Click click2 = new Click(click2ID, clickDate, userID2, isPremium);

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
        Boolean isPremium = false;
        ID click1ID = new ID(5);
        ID click2ID = new ID(6);
        ID click3ID = new ID(7);
        ID click4ID = new ID(8);

        Click click1 = new Click(click1ID, click1Date, userID, isPremium);
        Click click2 = new Click(click2ID, click2Date, user2ID, isPremium);
        Click click3 = new Click(click3ID, click3Date, userID, isPremium);
        Click click4 = new Click(click4ID, click4Date, userID, isPremium);

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
        Boolean isPremium = false;
        ID clickID = new ID(2);
        ID userID = new ID(3);
        Click click = new Click(clickID, clickDate, userID, isPremium);

        clickRepository.add(click);
        clickRepository.remove(click);

        boolean isEmpty = clickRepository.isEmpty();

        assertEquals(true, isEmpty);
    }
}
