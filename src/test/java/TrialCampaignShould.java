import DataValues.ClickID;
import DataValues.ClickType;
import DataValues.CampaignID;
import DataValues.UserID;
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
        CampaignID campaignId_campaign = new CampaignID(1);
        ClickRepositoryInterface chargedClicks = new ClickRepository();
        campaign = new TrialCampaign(campaignId_campaign, chargedClicks);
    }

    @Test
    public void not_charge_for_clicks_to_trial_campaigns(){


        Date clickDate = new Date(2020,5,7,10,0,0);
        Date click2Date = new Date(2020,5,7,11,0,0);
        ClickType clickType = new ClickType(false);
        ClickType clickType2 = new ClickType(true);
        ClickID clickID1 = new ClickID(2);
        ClickID clickID2 = new ClickID(3);
        UserID userID = new UserID(3);
        Click standardClick1 = new Click(clickID1, clickDate, userID, clickType);
        Click standardClick2 = new Click(clickID2, click2Date, userID, clickType2);

        campaign.charge(standardClick1);
        campaign.charge(standardClick2);



        CampaignID campaign_Campaign_id = new CampaignID(1);
        ClickRepositoryInterface chargedClicks = new ClickRepository();
        chargedClicks.add(standardClick1);
        chargedClicks.add(standardClick2);
        Campaign expectedCampaign = new TrialCampaign(campaign_Campaign_id, chargedClicks);


        assertEquals(expectedCampaign, campaign);
    }
}
