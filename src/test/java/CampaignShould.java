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

}
