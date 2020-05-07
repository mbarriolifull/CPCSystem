package Entities;

import CampaignState.*;
import CampaignState.StateCampaign;
import DataValues.ID;
import Repositories.ClickRepository;

import java.util.List;

public class TrialCampaign extends Campaign {
    private ID id;

    public TrialCampaign(ID id) {
        super(id);
    }


    @Override
    public void chargeToBudget(Click click) {
        super.addClick(click);
    }

    @Override
    public double remainingBudget() {
        return 0;
    }



}
