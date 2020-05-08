package Entities;

import DataValues.ClickID;
import DataValues.ClickType;
import DataValues.ID;

import java.util.Date;
import java.util.Objects;

public class Click {
    private ClickID id;
    private ID usersID;
    private Date date;
    private ClickType clickType;

    public Click(ClickID id, Date date, ID usersID, ClickType clickType ) {
        this.id = id;

        this.date = date;
        this.usersID = usersID;
        this.clickType = clickType;
    }

    public boolean isPremium() {
        return clickType.isPremium();
    }

    public boolean lessThan15seconds(Click clickToCompare) {
        long difference = (this.date.getTime()-clickToCompare.date.getTime())/1000;
        return Math.abs(difference)<15;
    }

    public boolean isSameUser(Click click) {
        return this.usersID == click.usersID;
    }

    public boolean isFrom(ID userID) {
        if (this.usersID.equals(userID))
            return true;
        return false;
    }

    public boolean olderThan(Date date) {
        return this.date.compareTo(date) > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Click click = (Click) o;
        return clickType == click.clickType &&
                Objects.equals(id, click.id) &&
                Objects.equals(usersID, click.usersID) &&
                Objects.equals(date, click.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usersID, date, clickType);
    }
}
