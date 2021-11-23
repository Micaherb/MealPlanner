import java.util.ArrayList;
/**
 * A possible Ingredient
 */
public class Ingredient {
    private String name;
    private String unit;
    private int count;
    private ArrayList<String> restrictions;

    public Ingredient(String name, String unit, int count) {
        this.name = name;
        this.unit = unit;
        this.count = count;
        restrictions = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public int getCount() {
        return count;
    }

    public void addRestriction(String restriction) {
        restrictions.add(restriction);
    }
}

