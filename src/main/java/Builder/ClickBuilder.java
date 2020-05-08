package Builder;

import DataValues.ClickID;
import DataValues.ClickType;
import DataValues.ID;
import Entities.Click;

import java.util.Date;

public class ClickBuilder {

    private ClickID id;
    private ID usersID;
    private Date date;
    private ClickType clickType;


    public ClickBuilder(ClickID id){
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

    public ClickBuilder setIsPremium(ClickType clickType){
        this.clickType = clickType;
        return this;
    }

    public Click build(){
        return new Click(id, date, usersID, clickType);
    }
}
