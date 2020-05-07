import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CampaignShould {

    @Test
    public void charge_non_premium_click(){
        ID campaign_id = new ID(1);
        Budget budget = new Budget(100);
        Campaign campaign = new StandardCampaign(campaign_id, budget);

        Date clickDate = new Date();
        Boolean isPremium = false;
        ID clickID = new ID(2);
        ID userID = new ID(3);
        Click click = new Click(clickID, clickDate, userID, isPremium);


        campaign.charge(click);

        double remaining_budget = campaign.remainingBudget();
        double expectedRemainingBudget = 99.99;
        assertEquals(expectedRemainingBudget, remaining_budget);
    }

    @Test
    public void test(){

    }


}
