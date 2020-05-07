package Entities;

import DataValues.ID;
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
    public double remainingBudget() {
        return 0;
    }

    @Override
    public void fakeClicks(Date date, ID userID) {

    }


}
