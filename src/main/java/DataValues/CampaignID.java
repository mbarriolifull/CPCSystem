package DataValues;

import java.util.Objects;

public class CampaignID {
    private int id;

    public CampaignID(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CampaignID campaignId1 = (CampaignID) o;
        return id == campaignId1.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
