package Entities;

import DataValues.Budget;
import DataValues.ID;
import DataValues.UserID;
import Repositories.ClickRepositoryInterface;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class StandardCampaign extends Campaign {

    private Budget budget;

    public StandardCampaign(ID id, Budget budget, ClickRepositoryInterface chargedClicks) {
        super(id, chargedClicks);
        this.budget = budget;
    }

    @Override
    public void chargeToBudget(Click click) {
        if (!click.isPremium()) {
            budget.charge(0.01);
        }
        if (click.isPremium()) {
            budget.charge(0.05);
        }
        if (budget.isFinished()){
            super.finishCampaign();
        }
        super.addClick(click);

    }


    @Override
    public void fakeClicks(Date date, UserID userid){
        List<Click> clicksSinceDate = super.retrieveCicksSince(date);
        for (Click currentClick : clicksSinceDate){
            calculateRefund(userid, currentClick);
        }
    }

    private void calculateRefund(UserID userid, Click currentClick) {
        if (currentClick.isFrom(userid)){
            calculateRefundType(currentClick);
            super.removeClick(currentClick);
        }
    }

    private void calculateRefundType(Click currentClick) {
        if (currentClick.isPremium()){
            budget.refund(0.05);
        }
        if (!currentClick.isPremium()){
            budget.refund(0.01);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StandardCampaign that = (StandardCampaign) o;
        return Objects.equals(budget, that.budget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), budget);
    }
}
