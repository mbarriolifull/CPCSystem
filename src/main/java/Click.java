import java.util.Date;

public class Click {
    private ID id;
    private ID usersID;
    private Date date;
    private boolean isPremium;

    public Click(ID id,Date date, ID usersID, boolean isPremium) {

        this.date = date;
        this.usersID = usersID;
        this.isPremium = isPremium;
    }

    public boolean isPremium() {
        return isPremium;
    }
}
