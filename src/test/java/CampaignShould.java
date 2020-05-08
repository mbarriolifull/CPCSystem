import Builder.ClickBuilder;
import DataValues.*;
import Entities.*;
import Repositories.ClickRepository;
import Repositories.ClickRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CampaignShould {

    private Campaign campaign;

    @BeforeEach
    public void setup(){
        ID campaign_id = new ID(1);
        Budget budget = new Budget(100);
        ClickRepositoryInterface chargedClicks = new ClickRepository();
        campaign = new StandardCampaign(campaign_id, budget, chargedClicks);
    }

    @Test
    public void charge_non_premium_click(){

        Date clickDate = new Date();
        ClickType clickType = new ClickType(false);
        ClickID clickID = new ClickID(2);
        ID userID = new ID(3);
        Click standardClick = new ClickBuilder(clickID)
                .setDate(clickDate)
                .setUsersID(userID)
                .setIsPremium(clickType)
                .build();

        campaign.charge(standardClick);

        ID campaign_id = new ID(1);
        Budget budget = new Budget(99.99);
        ClickRepositoryInterface chargedClicks = new ClickRepository();
        chargedClicks.add(standardClick);
        Campaign expectedCampaign = new StandardCampaign(campaign_id, budget, chargedClicks);

        assertEquals(expectedCampaign, campaign);
    }

    @Test
    public void charge_premium_click(){

        Date clickDate = new Date();
        ClickType clickType = new ClickType(true);
        ClickID clickID = new ClickID(2);
        ID userID = new ID(3);
        Click premiumClick = new ClickBuilder(clickID)
                .setDate(clickDate)
                .setUsersID(userID)
                .setIsPremium(clickType)
                .build();

        campaign.charge(premiumClick);


        ID campaign_id = new ID(1);
        Budget budget = new Budget(99.95);
        ClickRepositoryInterface chargedClicks = new ClickRepository();
        chargedClicks.add(premiumClick);
        Campaign expectedCampaign = new StandardCampaign(campaign_id, budget, chargedClicks);

        assertEquals(expectedCampaign, campaign);
    }

    @Test
    public void not_charge_when_is_paused(){
        Date clickDate = new Date();
        ClickType clickType = new ClickType(false);
        ClickID clickID = new ClickID(2);
        ID userID = new ID(3);
        Click standardClick = new ClickBuilder(clickID)
                .setDate(clickDate)
                .setUsersID(userID)
                .setIsPremium(clickType)
                .build();

        campaign.pause();
        campaign.charge(standardClick);

        ID campaign_id = new ID(1);
        Budget budget = new Budget(100);
        ClickRepositoryInterface chargedClicks = new ClickRepository();
        Campaign expectedCampaign = new StandardCampaign(campaign_id, budget, chargedClicks);
        expectedCampaign.pause();


        assertEquals(expectedCampaign, campaign);

    }

    @Test
    public void reactivate_in_order_to_continue_charging_clicks(){
        Date clickDate = new Date();
        ClickType clickType = new ClickType(false);
        ClickID clickID = new ClickID(2);
        ID userID = new ID(3);
        Click standardClick = new ClickBuilder(clickID)
                .setDate(clickDate)
                .setUsersID(userID)
                .setIsPremium(clickType)
                .build();

        campaign.pause();
        campaign.activate();
        campaign.charge(standardClick);

        ID campaign_id = new ID(1);
        Budget budget = new Budget(99.99);
        ClickRepositoryInterface chargedClicks = new ClickRepository();
        chargedClicks.add(standardClick);
        Campaign expectedCampaign = new StandardCampaign(campaign_id, budget, chargedClicks);



        assertEquals(expectedCampaign, campaign);
    }

    @Test
    public void finish_when_budget_reaches_0_or_below(){
        ID campaign_id = new ID(2);
        Budget lowBudget = new Budget(0.01);
        ClickRepositoryInterface chargedClicks = new ClickRepository();
        Campaign lowBudgetCampaign = new StandardCampaign(campaign_id, lowBudget, chargedClicks);

        Date clickDate = new Date();
        ClickType clickType = new ClickType(false);
        ClickID clickID = new ClickID(2);
        ID userID = new ID(3);
        Click standardClick = new ClickBuilder(clickID)
                .setDate(clickDate)
                .setUsersID(userID)
                .setIsPremium(clickType)
                .build();

        lowBudgetCampaign.charge(standardClick);

        boolean isFinished = lowBudgetCampaign.isFinished();
        assertEquals(true, isFinished);
    }

    @Test
    public void not_reactivate_when_they_are_already_finished(){
        ID campaign_id = new ID(2);
        Budget lowBudget = new Budget(0.01);
        ClickRepositoryInterface chargedClicks = new ClickRepository();
        Campaign lowBudgetCampaign = new StandardCampaign(campaign_id, lowBudget, chargedClicks);

        Date clickDate = new Date();
        ClickType clickType = new ClickType(false);
        ClickID clickID = new ClickID(2);
        ID userID = new ID(3);
        Click standardClick = new ClickBuilder(clickID)
                .setDate(clickDate)
                .setUsersID(userID)
                .setIsPremium(clickType)
                .build();

        lowBudgetCampaign.charge(standardClick);
        lowBudgetCampaign.activate();

        boolean isFinished = lowBudgetCampaign.isFinished();
        assertEquals(true, isFinished);
    }

    @Test
    public void charge_two_clicks_that_are_not_duplicated(){
        Date clickDate = new Date(2020,5,7,10,0,0);
        Date click2Date = new Date(2020,5,7,11,0,0);
        ClickType clickType = new ClickType(false);
        ClickID firstClickID = new ClickID(2);
        ClickID secondClickID = new ClickID(3);
        ID userID = new ID(4);
        ID secondUserID = new ID(5);
        Click firstClick = new ClickBuilder(firstClickID)
                .setDate(clickDate)
                .setUsersID(userID)
                .setIsPremium(clickType)
                .build();
        Click secondClick = new ClickBuilder(secondClickID)
                .setDate(click2Date)
                .setUsersID(secondUserID)
                .setIsPremium(clickType)
                .build();

        campaign.charge(firstClick);
        campaign.charge(secondClick);



        ID campaign_id = new ID(1);
        Budget budget = new Budget(99.98);
        ClickRepositoryInterface chargedClicks = new ClickRepository();
        chargedClicks.add(firstClick);
        chargedClicks.add(secondClick);
        Campaign expectedCampaign = new StandardCampaign(campaign_id, budget, chargedClicks);

        assertEquals(expectedCampaign, campaign);
    }


    @Test
    public void not_charge_duplicated_clicks(){
        Date clickDate = new Date(2020, 5, 7, 10, 0, 0);
        Date clickDateLessThan15secsLater = new Date(2020, 5, 7, 10, 0, 10);
        ClickType clickType = new ClickType(false);
        ClickID firstClickID = new ClickID(2);
        ClickID secondClickID = new ClickID(3);
        ID userID = new ID(4);
        Click firstClick = new ClickBuilder(firstClickID)
                .setDate(clickDate)
                .setUsersID(userID)
                .setIsPremium(clickType)
                .build();
        Click secondClick = new ClickBuilder(secondClickID)
                .setDate(clickDateLessThan15secsLater)
                .setUsersID(userID)
                .setIsPremium(clickType)
                .build();

        campaign.charge(firstClick);
        campaign.charge(secondClick);


        ID campaign_id = new ID(1);
        Budget budget = new Budget(99.99);
        ClickRepositoryInterface chargedClicks = new ClickRepository();
        chargedClicks.add(firstClick);
        Campaign expectedCampaign = new StandardCampaign(campaign_id, budget, chargedClicks);


        assertEquals(expectedCampaign, campaign);
    }

    @Test
    public void refund_the_clicks_made_by_some_userid_since_a_given_date(){
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

        campaign.charge(click1);
        campaign.charge(click2);
        campaign.charge(click3);
        campaign.charge(click4);

        Date fakeClickDate = new Date(2020,5,7,10,50,0);
        ID fakeClickUserId = new ID(3);

        campaign.fakeClicks(fakeClickDate, fakeClickUserId);

        ID campaign_id = new ID(1);
        Budget budget = new Budget(99.98);
        ClickRepositoryInterface chargedClicks = new ClickRepository();
        chargedClicks.add(click1);
        chargedClicks.add(click2);
        Campaign expectedCampaign = new StandardCampaign(campaign_id, budget, chargedClicks);


        assertEquals(expectedCampaign, campaign);

    }
}
