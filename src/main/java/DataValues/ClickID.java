package DataValues;

import java.util.Objects;

public class ClickID {
    private int id;

    public ClickID(int id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClickID clickID = (ClickID) o;
        return id == clickID.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
