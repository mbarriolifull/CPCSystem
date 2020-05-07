import DataValues.Budget;
import DataValues.ID;
import Entities.Campaign;
import Entities.Click;
import Entities.TopCampaign;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TopCampaignShould {

    private Campaign campaign;

    @BeforeEach
    public void setup(){
        ID campaign_id = new ID(1);
        Budget budget = new Budget(100);
        campaign = new TopCampaign(campaign_id, budget);
    }

    @Test
    public void charge_non_premium_click_for_top_campaign(){
        Date clickDate = new Date();
        Boolean isPremium = false;
        ID clickID = new ID(2);
        ID userID = new ID(3);
        Click standardClick = new Click(clickID, clickDate, userID, isPremium);


        campaign.charge(standardClick);

        double remainingBudget = campaign.remainingBudget();
        double expectedRemainingBudget = 99.90;
        assertEquals(expectedRemainingBudget, remainingBudget);
    }

    @Test
    public void charge_premium_click_for_top_campaign(){
        Date clickDate = new Date();
        Boolean isPremium = true;
        ID clickID = new ID(2);
        ID userID = new ID(3);
        Click standardClick = new Click(clickID, clickDate, userID, isPremium);


        campaign.charge(standardClick);

        double remainingBudget = campaign.remainingBudget();
        double expectedRemainingBudget = 99.80;
        assertEquals(expectedRemainingBudget, remainingBudget);
    }

    @Test
    public void refund_the_clicks_made_by_some_userid_since_a_given_date_which_the_total_cost_is_less_than_5_percent_of_total_budget(){
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

        campaign.charge(click1);
        campaign.charge(click2);
        campaign.charge(click3);
        campaign.charge(click4);

        Date fakeClickDate = new Date(2020,5,7,10,50,0);
        ID fakeClickUserId = new ID(3);
        campaign.fakeClicks(fakeClickDate, fakeClickUserId);

        double remainingBudget = campaign.remainingBudget();
        double expectedBudget = 99.8;

        assertEquals(expectedBudget, remainingBudget);
    }

    @Test
    public void refund_all_clicks_when_price_of_fake_clicks_is_(){
        ID campaign_id = new ID(1);
        Budget budget = new Budget(10);
        Campaign lowBudgetCampaign = new TopCampaign(campaign_id, budget);
        ID userID = new ID(3);
        ID user2ID = new ID(4);

        Date click1Date = new Date(2020, 5, 7, 10, 0, 0);
        Date click2Date = new Date(2020, 5, 7, 10, 30, 0);
        Date click3Date = new Date(2020, 5, 7, 11, 0, 0);
        Date click4Date = new Date(2020, 5, 7, 11, 30, 0);
        Date click5Date = new Date(2020, 5, 7, 11, 40, 0);
        Date click6Date = new Date(2020, 5, 7, 11, 50, 0);
        Date click7Date = new Date(2020, 5, 7, 11, 60, 0);
        Boolean isPremium = true;
        ID click1ID = new ID(5);
        ID click2ID = new ID(6);
        ID click3ID = new ID(7);
        ID click4ID = new ID(8);
        ID click5ID = new ID(9);
        ID click6ID = new ID(10);
        ID click7ID = new ID(11);

        Click click1 = new Click(click1ID, click1Date, userID, isPremium);
        Click click2 = new Click(click2ID, click2Date, user2ID, isPremium);
        Click click3 = new Click(click3ID, click3Date, userID, isPremium);
        Click click4 = new Click(click4ID, click4Date, userID, isPremium);
        Click click5 = new Click(click5ID, click5Date, userID, isPremium);
        Click click6 = new Click(click6ID, click6Date, userID, isPremium);
        Click click7 = new Click(click7ID, click7Date, userID, isPremium);


        lowBudgetCampaign.charge(click1);
        lowBudgetCampaign.charge(click2);
        lowBudgetCampaign.charge(click3);
        lowBudgetCampaign.charge(click4);
        lowBudgetCampaign.charge(click5);
        lowBudgetCampaign.charge(click6);
        lowBudgetCampaign.charge(click7);

        Date fakeClickDate = new Date(2020,5,7,10,50,0);
        ID fakeClickUserId = new ID(3);
        lowBudgetCampaign.fakeClicks(fakeClickDate, fakeClickUserId);

        double remainingBudget = lowBudgetCampaign.remainingBudget();
        double expectedBudget = 9.6;

        assertEquals(expectedBudget, remainingBudget);
    }
}
