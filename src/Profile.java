import java.util.ArrayList;

public class Profile {
    private boolean conMealTimes;
    private boolean conBreakfast;
    private boolean conLunch;
    private boolean conDinner;
    private boolean dailySchedule;
    private ArrayList<String> dietaryRestrictions;

    public Profile() {
        dietaryRestrictions = new ArrayList<String>();
    }

    public boolean isConMealTimes() {
        return conMealTimes;
    }

    public void setConMealTimes(boolean conMealTimes) {
        this.conMealTimes = conMealTimes;
    }

    public boolean isConBreakfast() {
        return conBreakfast;
    }

    public void setConBreakfast(boolean conBreakfast) {
        this.conBreakfast = conBreakfast;
    }

    public boolean isConLunch() {
        return conLunch;
    }

    public void setConLunch(boolean conLunch) {
        this.conLunch = conLunch;
    }

    public boolean isConDinner() {
        return conDinner;
    }

    public void setConDinner(boolean conDinner) {
        this.conDinner = conDinner;
    }

    public boolean isDailySchedule() {
        return dailySchedule;
    }

    public void setDailySchedule(boolean dailySchedule) {
        this.dailySchedule = dailySchedule;
    }

    public ArrayList<String> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void addDietaryRestriction(String restriction) {
        dietaryRestrictions.add(restriction);
    }
}
