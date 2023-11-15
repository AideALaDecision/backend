import java.util.ArrayList;
import java.util.List;

public class Establishment {
    private static int count = -1;
    int id;
    List<Integer> preferences = new ArrayList();


    public Establishment() {
        setId(count++);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<Integer> preferences) {
        this.preferences = preferences;
    }
}
