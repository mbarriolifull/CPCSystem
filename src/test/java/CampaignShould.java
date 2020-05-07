import DataValues.*;
import Entities.*;
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
        campaign = new StandardCampaign(campaign_id, budget);
    }

    @Test
    public void charge_non_premium_click(){

        Date clickDate = new Date();
        Boolean isPremium = false;
        ID clickID = new ID(2);
        ID userID = new ID(3);
        Click standardClick = new Click(clickID, clickDate, userID, isPremium);


        campaign.charge(standardClick);

        double remainingBudget = campaign.remainingBudget();
        double expectedRemainingBudget = 99.99;
        assertEquals(expectedRemainingBudget, remainingBudget);
    }

    @Test
    public void charge_premium_click(){

        Date clickDate = new Date();
        Boolean isPremium = true;
        ID clickID = new ID(2);
        ID userID = new ID(3);
        Click premiumClick = new Click(clickID, clickDate, userID, isPremium);

        campaign.charge(premiumClick);

        double remainingBudget = campaign.remainingBudget();
        double expectedRemainingBudget = 99.95;
        assertEquals(expectedRemainingBudget, remainingBudget);
    }

    @Test
    public void not_charge_when_is_paused(){
        Date clickDate = new Date();
        Boolean isPremium = false;
        ID clickID = new ID(2);
        ID userID = new ID(3);
        Click standardClick = new Click(clickID, clickDate, userID, isPremium);

        campaign.pause();
        campaign.charge(standardClick);

        double remainingBudget = campaign.remainingBudget();
        double expectedRemainingBudget = 100;

        assertEquals(expectedRemainingBudget, remainingBudget);

    }

    @Test
    public void reactivate_in_order_to_continue_charging_clicks(){
        Date clickDate = new Date();
        Boolean isPremium = false;
        ID clickID = new ID(2);
        ID userID = new ID(3);
        Click standardClick = new Click(clickID, clickDate, userID, isPremium);

        campaign.pause();
        campaign.activate();
        campaign.charge(standardClick);

        double remainingBudget = campaign.remainingBudget();
        double expectedRemainingBudget = 99.99;

        assertEquals(expectedRemainingBudget, remainingBudget);
    }

    @Test
    public void finish_when_budget_reaches_0_or_below(){
        ID campaign_id = new ID(2);
        Budget lowBudget = new Budget(0.01);
        Campaign lowBudgetCampaign = new StandardCampaign(campaign_id, lowBudget);

        Date clickDate = new Date();
        Boolean isPremium = false;
        ID clickID = new ID(2);
        ID userID = new ID(3);
        Click standardClick = new Click(clickID, clickDate, userID, isPremium);

        lowBudgetCampaign.charge(standardClick);

        boolean isFinished = lowBudgetCampaign.isFinished();
        assertEquals(true, isFinished);
    }

    @Test
    public void not_reactivate_when_they_are_already_finished(){
        ID campaign_id = new ID(2);
        Budget lowBudget = new Budget(0.01);
        Campaign lowBudgetCampaign = new StandardCampaign(campaign_id, lowBudget);

        Date clickDate = new Date();
        Boolean isPremium = false;
        ID clickID = new ID(2);
        ID userID = new ID(3);
        Click standardClick = new Click(clickID, clickDate, userID, isPremium);

        lowBudgetCampaign.charge(standardClick);
        lowBudgetCampaign.activate();

        boolean isFinished = lowBudgetCampaign.isFinished();
        assertEquals(true, isFinished);
    }

    @Test
    public void charge_two_clicks_that_are_not_duplicated(){
        Date clickDate = new Date(2020,5,7,10,0,0);
        Date click2Date = new Date(2020,5,7,11,0,0);
        Boolean isPremium = false;
        ID firstClickID = new ID(2);
        ID secondClickID = new ID(3);
        ID userID = new ID(4);
        ID secondUserID = new ID(5);
        Click firstClick = new Click(firstClickID, clickDate, userID, isPremium);
        Click secondClick = new Click(secondClickID, click2Date, secondUserID, isPremium);

        campaign.charge(firstClick);
        campaign.charge(secondClick);

        double expectedbudget = 99.98;
        double remainingbudget = campaign.remainingBudget();
        assertEquals(expectedbudget, remainingbudget);
    }


    @Test
    public void not_charge_duplicated_clicks(){
        Date clickDate = new Date(2020, 5, 7, 10, 0, 0);
        Date clickDateLessThan15secsLater = new Date(2020, 5, 7, 10, 0, 10);
        Boolean isPremium = false;
        ID firstClickID = new ID(2);
        ID secondClickID = new ID(3);
        ID userID = new ID(4);
        Click firstClick = new Click(firstClickID, clickDate, userID, isPremium);
        Click secondClick = new Click(secondClickID, clickDateLessThan15secsLater, userID, isPremium);

        campaign.charge(firstClick);
        campaign.charge(secondClick);

        double expectedBudget = 99.99;
        double remainingBudget = campaign.remainingBudget();

        assertEquals(expectedBudget, remainingBudget);
    }
}
