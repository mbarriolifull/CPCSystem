package Entities;

import CampaignState.StateCampaign;

public interface Campaign {
    void pause();
    void activate();
    void charge(Click click);
    void chargeToBudget(Click click);

    double remainingBudget();

    boolean isFinished();

    void setStateCampaign(StateCampaign stateCampaign);

    boolean isduplicated(Click click);

}
