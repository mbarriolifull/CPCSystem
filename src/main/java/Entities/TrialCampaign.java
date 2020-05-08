package Entities;

import DataValues.ID;
import DataValues.UserID;
import Repositories.ClickRepositoryInterface;

import java.util.Date;

public class TrialCampaign extends Campaign {


    public TrialCampaign(ID id, ClickRepositoryInterface chargedClicks) {
        super(id, chargedClicks);
    }


    @Override
    public void chargeToBudget(Click click) {
        super.addClick(click);
    }

    @Override
    public void fakeClicks(Date date, UserID userID) {

    }


}
