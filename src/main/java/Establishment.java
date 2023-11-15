import java.util.ArrayList;
import java.util.List;

public class Establishment {
    int id;
    List<Integer> preferences = new ArrayList();

    public List<Integer> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<Integer> preferences) {
        this.preferences = preferences;
    }
}
