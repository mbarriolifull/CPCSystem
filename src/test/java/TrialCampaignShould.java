import DataValues.ID;
import Entities.Campaign;
import Entities.Click;
import Entities.TrialCampaign;
import Repositories.ClickRepository;
import Repositories.ClickRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrialCampaignShould {

    private Campaign campaign;

    @BeforeEach
    public void setup(){
        ID id_campaign = new ID(1);
        ClickRepositoryInterface chargedClicks = new ClickRepository();
        campaign = new TrialCampaign(id_campaign, chargedClicks);
    }

    @Test
    public void not_charge_for_clicks_to_trial_campaigns(){


        Date clickDate = new Date(2020,5,7,10,0,0);
        Date click2Date = new Date(2020,5,7,11,0,0);
        Boolean isPremium = false;
        Boolean isPremium2 = true;
        ID clickID1 = new ID(2);
        ID clickID2 = new ID(3);
        ID userID = new ID(3);
        Click standardClick1 = new Click(clickID1, clickDate, userID, isPremium);
        Click standardClick2 = new Click(clickID2, click2Date, userID, isPremium2);

        campaign.charge(standardClick1);
        campaign.charge(standardClick2);



        ID campaign_id = new ID(1);
        ClickRepositoryInterface chargedClicks = new ClickRepository();
        chargedClicks.add(standardClick1);
        chargedClicks.add(standardClick2);
        Campaign expectedCampaign = new TrialCampaign(campaign_id, chargedClicks);


        assertEquals(expectedCampaign, campaign);
    }
}
