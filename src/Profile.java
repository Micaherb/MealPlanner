import java.util.ArrayList;

public class Profile {
    /**
     * Allow identical consecutive meals
     */
    private boolean allowsConsecutiveIdenticalMeals;
    /**
     * Allow identical consecutive meals of type Breakfast
     */
    private boolean allowsConsecutiveIdenticalBreakfast;
    /**
     * Allow identical consecutive meals of type Lunch
     */
    private boolean allowsConsecutiveIdenticalLunch;
    /**
     * Allow identical consecutive meals of type Dinner
     */
    private boolean allowsConsecutiveIdenticalDinner;
    /**
     * Arrange meal schedule so that it adheres to a daily schedule
     */
    private boolean followsDailySchedule;
    private ArrayList<String> dietaryRestrictions;

    public Profile() {
        dietaryRestrictions = new ArrayList<String>();
    }

    public boolean allowsIdenticalConsecutiveMeals() {
        return allowsConsecutiveIdenticalMeals;
    }

    public void setAllowsConsecutiveIdenticalMeals(boolean allowsConsecutiveIdenticalMeals) {
        this.allowsConsecutiveIdenticalMeals = allowsConsecutiveIdenticalMeals;
    }

    public boolean allowsIdenticalConsecutiveMeals(Recipe.RecipeType ofType) {
        switch(ofType) {
            case BREAKFAST:
                return allowsConsecutiveIdenticalBreakfast;
            case LUNCH:
                return allowsConsecutiveIdenticalLunch;
            case DINNER:
                return allowsConsecutiveIdenticalDinner;
            default:
                return false;
        }
    }

    public void setAllowsConsecutiveIdenticalMeals(Recipe.RecipeType ofType, boolean value) {
        switch(ofType) {
            case BREAKFAST:
                allowsConsecutiveIdenticalBreakfast = value; break;
            case LUNCH:
                allowsConsecutiveIdenticalLunch = value; break;
            case DINNER:
                allowsConsecutiveIdenticalDinner = value; break;
        }
    }

    public boolean followsDailySchedule() {
        return followsDailySchedule;
    }

    public void setFollowsDailySchedule(boolean followsDailySchedule) {
        this.followsDailySchedule = followsDailySchedule;
    }

    public ArrayList<String> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void addDietaryRestriction(String restriction) {
        dietaryRestrictions.add(restriction);
    }

}
