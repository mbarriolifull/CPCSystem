package DataValues;

import java.util.Objects;

public class ClickType {
    private boolean isPremium;

    public ClickType(boolean isPremium) {
        this.isPremium = isPremium;
    }

    public boolean isPremium(){
        return isPremium;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClickType clickType = (ClickType) o;
        return isPremium == clickType.isPremium;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isPremium);
    }
}
