package Repositories;

import Entities.Click;

import java.util.ArrayList;
import java.util.List;

public class ClickRepository {
    private List<Click> clickList;

    public ClickRepository() {
        this.clickList = new ArrayList<>();
    }

    public List<Click> retrieveSameUserClicks(Click click) {
        List<Click> sameUserClicks = new ArrayList<>();
        for (Click currentClick : clickList){
            if (currentClick.isSameUser(click)){
                sameUserClicks.add(currentClick);
            }
        }
        return sameUserClicks;
    }

    public boolean isEmpty() {
        return clickList.isEmpty();
    }

    public void add(Click click) {
        clickList.add(click);
    }
}
