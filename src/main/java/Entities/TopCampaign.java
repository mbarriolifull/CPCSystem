package Entities;

import DataValues.CampaignID;
import DataValues.UserID;
import Repositories.ClickRepositoryInterface;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TopCampaign extends Campaign {

    private Budget budget;
    private Budget initialBudget;


    public TopCampaign(CampaignID campaignId, Budget budget, ClickRepositoryInterface chargedClicks) {
        super(campaignId, chargedClicks);
        this.budget = budget;
        this.initialBudget = new Budget(budget);
    }

    @Override
    public void chargeToBudget(Click click) {
        if (!click.isPremium()) {
            budget.charge(0.1);
        }
        if (click.isPremium()) {
            budget.charge(0.2);
        }
        if (budget.isFinished()){
            super.finishCampaign();
        }
        super.addClick(click);
    }

    @Override
    public void fakeClicks(Date date, UserID userID) {
        List<Click>  clicksSinceDate = super.retrieveCicksSince(date);
        double totalFakeClickPrice = 0;
        double totalClickPriceSinceDate = 0;
        for (Click currentClick : clicksSinceDate) {
            if (currentClick.isPremium()) {
                if (currentClick.isFrom(userID)) {
                    totalFakeClickPrice += 0.2;
                }
                totalClickPriceSinceDate += 0.2;
            }
            if (!currentClick.isPremium()) {
                if (currentClick.isFrom(userID)){
                    totalFakeClickPrice += 0.1;
                }
                totalClickPriceSinceDate += 0.1;
            }
            super.removeClick(currentClick);

        }
        double fivePercentOfTotalBudget = initialBudget.getFivePercent();
        if(totalClickPriceSinceDate < fivePercentOfTotalBudget){
            budget.refund(totalClickPriceSinceDate);
        }
        else{
            budget.refund(totalFakeClickPrice);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TopCampaign that = (TopCampaign) o;
        return Objects.equals(budget, that.budget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), budget, initialBudget);
    }
}
