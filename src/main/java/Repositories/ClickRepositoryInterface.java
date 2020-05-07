package Repositories;

import Entities.Click;

import java.util.Date;
import java.util.List;

public interface ClickRepositoryInterface {
    List<Click> retrieveSameUserClicks(Click click);

    boolean isEmpty();

    void add(Click click);

    List<Click> clicksSince(Date date);

    void remove(Click click);
}
