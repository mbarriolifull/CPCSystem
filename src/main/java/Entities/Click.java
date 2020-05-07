package Entities;

import DataValues.ID;

import java.util.Date;
import java.util.Objects;

public class Click {
    private ID id;
    private ID usersID;
    private Date date;
    private boolean isPremium;

    public Click(ID id, Date date, ID usersID, boolean isPremium) {

        this.date = date;
        this.usersID = usersID;
        this.isPremium = isPremium;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public boolean lessThan15seconds(Click clickToCompare) {
        long difference = (this.date.getTime()-clickToCompare.date.getTime())/1000;
        return Math.abs(difference)<15;
    }

    public boolean isSameUser(Click click) {
        return this.usersID == click.usersID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Click click = (Click) o;
        return isPremium == click.isPremium &&
                Objects.equals(id, click.id) &&
                Objects.equals(usersID, click.usersID) &&
                Objects.equals(date, click.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usersID, date, isPremium);
    }
}
