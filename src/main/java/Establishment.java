import java.util.ArrayList;
import java.util.List;

public class Establishment {
    private static int count = 0;
    int id;
    List<Integer> preferences = new ArrayList<>();


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

        this.preferences.clear();
        this.preferences.addAll(preferences);
    }

    @Override
    public String toString() {
        return "id{" + id +'}';
    }
}
