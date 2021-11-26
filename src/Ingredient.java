import java.util.ArrayList;
/**
 * A possible Ingredient
 */
public class Ingredient {
    private String name;
    private String unit;
    private int count;
    private ArrayList<String> restrictions;

    public Ingredient(String name, String unit, int count, ArrayList<String> restrictions) {
        this.name = name;
        this.unit = unit;
        this.count = count;
        this.restrictions = new ArrayList<String>();
        // If restrictions is not null or empty, add all strings from that list to this list
        if (restrictions != null && !restrictions.isEmpty()) {
            this.restrictions.addAll(restrictions);
        }
    }

    public Ingredient(String name, String unit, int count) {
        this(name, unit, count, null);
    }

    public Ingredient(Ingredient ingredient) {
        this(ingredient.getName(), ingredient.getUnit(), ingredient.getCount(), ingredient.getRestrictions());
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

    /**
     * See if the ingredient contains a given restriction (CAPS_EXCLUSIVE)
     * @param restriction given restriction
     * @return if ingredient has restriction
     */
    public boolean hasRestriction(String restriction) {
        for (String r : restrictions) {
            if (restriction.equalsIgnoreCase(r)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Decrease amount of ingredient
     * @param amount Amount to lower by
     */
    public void decreaseAmount(int amount) {
        this.count -= amount;
    }

    /**
     * Increase amount of ingredient
     * @param amount Amount to lower by
     */
    public void increaseAmount(int amount) {
        this.count += amount;
    }

    public ArrayList<String> getRestrictions() {
        return restrictions;
    }
}

