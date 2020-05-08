import Builder.ClickBuilder;
import DataValues.*;
import Entities.Budget;
import Entities.Campaign;
import Entities.Click;
import Entities.TopCampaign;
import Repositories.ClickRepository;
import Repositories.ClickRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TopCampaignShould {

    private Campaign campaign;

    @BeforeEach
    public void setup(){
        CampaignID campaign_Campaign_id = new CampaignID(1);
        Budget budget = new Budget(100);
        ClickRepositoryInterface chargedClicks = new ClickRepository();
        campaign = new TopCampaign(campaign_Campaign_id, budget, chargedClicks);
    }

    @Test
    public void charge_non_premium_click_for_top_campaign(){
        Date clickDate = new Date();
        ClickType clickType = new ClickType(false);
        ClickID clickID = new ClickID(2);
        UserID userID = new UserID(3);
        Click standardClick = new ClickBuilder(clickID)
                .setDate(clickDate)
                .setUsersID(userID)
                .setIsPremium(clickType)
                .build();


        campaign.charge(standardClick);

        CampaignID campaign_Campaign_id = new CampaignID(1);
        Budget budget = new Budget(99.90);
        ClickRepositoryInterface chargedClicks = new ClickRepository();
        chargedClicks.add(standardClick);
        Campaign expectedCampaign = new TopCampaign(campaign_Campaign_id, budget, chargedClicks);

        assertEquals(expectedCampaign, campaign);
    }

    @Test
    public void charge_premium_click_for_top_campaign(){
        Date clickDate = new Date();
        ClickType clickType = new ClickType(true);
        ClickID clickID = new ClickID(2);
        UserID userID = new UserID(3);
        Click premiumClick = new ClickBuilder(clickID)
                .setDate(clickDate)
                .setUsersID(userID)
                .setIsPremium(clickType)
                .build();


        campaign.charge(premiumClick);

        CampaignID campaign_Campaign_id = new CampaignID(1);
        Budget budget = new Budget(99.80);
        ClickRepositoryInterface chargedClicks = new ClickRepository();
        chargedClicks.add(premiumClick);
        Campaign expectedCampaign = new TopCampaign(campaign_Campaign_id, budget, chargedClicks);

        assertEquals(expectedCampaign, campaign);
    }

    @Test
    public void refund_the_clicks_made_by_some_userid_since_a_given_date_which_the_total_cost_is_less_than_5_percent_of_total_budget(){
        UserID userID = new UserID(3);
        UserID user2ID = new UserID(4);

        Date click1Date = new Date(2020, 5, 7, 10, 0, 0);
        Date click2Date = new Date(2020, 5, 7, 10, 30, 0);
        Date click3Date = new Date(2020, 5, 7, 11, 0, 0);
        Date click4Date = new Date(2020, 5, 7, 11, 30, 0);
        Date click5Date = new Date(2020, 5, 7, 11, 40, 0);
        ClickType clickType = new ClickType(false);
        ClickID click1ID = new ClickID(5);
        ClickID click2ID = new ClickID(6);
        ClickID click3ID = new ClickID(7);
        ClickID click4ID = new ClickID(8);
        ClickID click5ID = new ClickID(9);


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
        Click click5 = new ClickBuilder(click5ID)
                .setDate(click5Date)
                .setUsersID(user2ID)
                .setIsPremium(clickType)
                .build();

        campaign.charge(click1);
        campaign.charge(click2);
        campaign.charge(click3);
        campaign.charge(click4);
        campaign.charge(click5);

        Date fakeClickDate = new Date(2020,5,7,10,50,0);
        UserID fakeClickUserId = new UserID(3);
        campaign.fakeClicks(fakeClickDate, fakeClickUserId);


        CampaignID campaign_Campaign_id = new CampaignID(1);
        Budget budget = new Budget(99.80);
        ClickRepositoryInterface chargedClicks = new ClickRepository();
        chargedClicks.add(click1);
        chargedClicks.add(click2);
        Campaign expectedCampaign = new TopCampaign(campaign_Campaign_id, budget, chargedClicks);


        assertEquals(expectedCampaign, campaign);
    }

    @Test
    public void refund_the_clicks_made_by_some_userid_since_a_given_date_which_the_total_cost_is_MORE_than_5_percent_of_total_budget(){
        CampaignID campaign_Campaign_id = new CampaignID(1);
        Budget budget = new Budget(10);
        ClickRepositoryInterface chargedClicks = new ClickRepository();
        Campaign lowBudgetCampaign = new TopCampaign(campaign_Campaign_id, budget, chargedClicks);
        UserID userID = new UserID(3);
        UserID user2ID = new UserID(4);

        Date click1Date = new Date(2020, 5, 7, 10, 0, 0);
        Date click2Date = new Date(2020, 5, 7, 10, 30, 0);
        Date click3Date = new Date(2020, 5, 7, 11, 0, 0);
        Date click4Date = new Date(2020, 5, 7, 11, 30, 0);
        Date click5Date = new Date(2020, 5, 7, 11, 40, 0);
        Date click6Date = new Date(2020, 5, 7, 11, 50, 0);
        Date click7Date = new Date(2020, 5, 7, 11, 60, 0);
        ClickType clickType = new ClickType(true);
        ClickID click1ID = new ClickID(5);
        ClickID click2ID = new ClickID(6);
        ClickID click3ID = new ClickID(7);
        ClickID click4ID = new ClickID(8);
        ClickID click5ID = new ClickID(9);
        ClickID click6ID = new ClickID(10);
        ClickID click7ID = new ClickID(11);

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
        Click click5 = new ClickBuilder(click5ID)
                .setDate(click5Date)
                .setUsersID(userID)
                .setIsPremium(clickType)
                .build();
        Click click6 = new ClickBuilder(click6ID)
                .setDate(click6Date)
                .setUsersID(userID)
                .setIsPremium(clickType)
                .build();
        Click click7 = new ClickBuilder(click7ID)
                .setDate(click7Date)
                .setUsersID(userID)
                .setIsPremium(clickType)
                .build();


        lowBudgetCampaign.charge(click1);
        lowBudgetCampaign.charge(click2);
        lowBudgetCampaign.charge(click3);
        lowBudgetCampaign.charge(click4);
        lowBudgetCampaign.charge(click5);
        lowBudgetCampaign.charge(click6);
        lowBudgetCampaign.charge(click7);

        Date fakeClickDate = new Date(2020,5,7,10,50,0);
        UserID fakeClickUserId = new UserID(3);
        lowBudgetCampaign.fakeClicks(fakeClickDate, fakeClickUserId);

        CampaignID expected_campaign_Campaign_id = new CampaignID(1);
        Budget expected_budget = new Budget(9.6);
        ClickRepositoryInterface expected_chargedClicks = new ClickRepository();
        expected_chargedClicks.add(click1);
        expected_chargedClicks.add(click2);
        Campaign expectedCampaign = new TopCampaign(expected_campaign_Campaign_id, expected_budget, expected_chargedClicks);


        assertEquals(expectedCampaign, lowBudgetCampaign);
    }
}
