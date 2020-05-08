package Entities;

import DataValues.CampaignID;
import DataValues.UserID;
import Repositories.ClickRepositoryInterface;

import java.util.Date;

public class TrialCampaign extends Campaign {


    public TrialCampaign(CampaignID campaignId, ClickRepositoryInterface chargedClicks) {
        super(campaignId, chargedClicks);
    }


    @Override
    public void chargeToBudget(Click click) {
        super.addClick(click);
    }

    @Override
    public void fakeClicks(Date date, UserID userID) {

    }


}
