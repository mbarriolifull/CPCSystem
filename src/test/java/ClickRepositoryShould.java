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
}
