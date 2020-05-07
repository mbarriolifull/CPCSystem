package Builder;

import DataValues.ID;
import Entities.Click;

import java.util.Date;

public class ClickBuilder {

    private ID id;
    private ID usersID;
    private Date date;
    private boolean isPremium;


    public ClickBuilder(ID id){
        this.id = id;
    }

    public ClickBuilder setUsersID(ID usersID) {
        this.usersID = usersID;
        return this;
    }

    public ClickBuilder setDate(Date date){
        this.date = date;
        return this;
    }

    public ClickBuilder setIsPremium(boolean isPremium){
        this.isPremium = isPremium;
        return this;
    }

    public Click build(){
        return new Click(id, date, usersID, isPremium);
    }
}
