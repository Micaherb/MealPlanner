import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * IO stuff
 */
public class IO {
    public IO() {}

    public void readIngredients(Planner planner) {
        try {
            File file = new File("./data/ingredients.txt");
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                //Get Next Line
                String data = fileScanner.nextLine();
                //Get the name
                String name = data.substring(0, data.indexOf(";"));
                data = data.substring(data.indexOf(";") + 1);
                //Get the unit
                String unit = data.substring(0, data.indexOf(";"));
                data = data.substring(data.indexOf(";") + 1);
                //Get Count
                int count = Integer.parseInt(data.substring(0, data.indexOf(";")));
                data = data.substring(data.indexOf(";") + 1);
                //Create ingredient
                Ingredient i = new Ingredient(name, unit, count);
                //Get any dietary restrictions
                while (data.length() > 0) {
                    String restriction = data.substring(0, data.indexOf(";"));
                    i.addRestriction(restriction);
                    data = data.substring(data.indexOf(";") + 1);
                }
                //Add Ingredient to the planner
                planner.addIngredient(i);
            }
        } catch (FileNotFoundException var10) {
            var10.printStackTrace();
        }
    }

    public void readRecipes(Planner planner) {
        try {
            File file = new File("./data/recipes.txt");
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                //Get Next Line
                String data = fileScanner.nextLine();
                //Get the name
                String name = data.substring(0, data.indexOf(";"));
                data = data.substring(data.indexOf(";") + 1);
                //Get the type
                String type = data.substring(0, data.indexOf(";"));
                Recipe r = new Recipe(name, type);
                data = data.substring(data.indexOf(";") + 1);
                //Get all Ingredients
                while (data.length() > 0) {
                    String i = data.substring(0, data.indexOf(":"));
                    Ingredient ingredient = planner.getIngredient(i);
                    data = data.substring(data.indexOf(":") + 1);
                    int count = Integer.parseInt(data.substring(0, data.indexOf(";")));
                    data = data.substring(data.indexOf(";") + 1);
                    r.addIngredient(ingredient, count);
                }
                //Add recipe to the planner
                planner.addRecipe(r);
            }
        } catch (FileNotFoundException var10) {
            var10.printStackTrace();
        }
    }

    public Profile readProfile(String profile) {
        Profile user = new Profile();
        try {
            File file = new File(profile);
            Scanner fileScanner = new Scanner(file);
            //Get allowsConsecutiveIdenticalMeals
            String data = fileScanner.nextLine();
            String value = data.substring(data.indexOf(":") + 1, data.indexOf(";"));
            if(value.equals("True")) {
                user.setAllowsConsecutiveIdenticalMeals(true);
            } else {
                user.setAllowsConsecutiveIdenticalMeals(false);
            }
            //Get allowsConsecutiveIdenticalBreakfast
            data = fileScanner.nextLine();
            value = data.substring(data.indexOf(":") + 1, data.indexOf(";"));
            if(value.equals("True")) {
                user.setAllowsConsecutiveIdenticalMeals(Recipe.RecipeType.BREAKFAST, true);
            } else {
                user.setAllowsConsecutiveIdenticalMeals(Recipe.RecipeType.BREAKFAST, false);
            }
            //Get allowsConsecutiveIdenticalLunch
            data = fileScanner.nextLine();
            value = data.substring(data.indexOf(":") + 1, data.indexOf(";"));
            if(value.equals("True")) {
                user.setAllowsConsecutiveIdenticalMeals(Recipe.RecipeType.LUNCH, true);
            } else {
                user.setAllowsConsecutiveIdenticalMeals(Recipe.RecipeType.LUNCH, false);
            }
            //Get allowsConsecutiveIdenticalDinner
            data = fileScanner.nextLine();
            value = data.substring(data.indexOf(":") + 1, data.indexOf(";"));
            if(value.equals("True")) {
                user.setAllowsConsecutiveIdenticalMeals(Recipe.RecipeType.DINNER, true);
            } else {
                user.setAllowsConsecutiveIdenticalMeals(Recipe.RecipeType.DINNER, false);
            }
            //Get followsDailySchedule
            data = fileScanner.nextLine();
            value = data.substring(data.indexOf(":") + 1, data.indexOf(";"));
            if(value.equals("True")) {
                user.setFollowsDailySchedule(true);
            } else {
                user.setFollowsDailySchedule(false);
            }
            //Get Dietary Restrictions
            data = fileScanner.nextLine();
            data = data.substring(data.indexOf(":") + 1);
            while (data.length() > 1) {
                String r = data.substring(0, data.indexOf(";"));
                user.addDietaryRestriction(r);
                data = data.substring(data.indexOf(";") + 1);
            }

        } catch (FileNotFoundException var10) {
            var10.printStackTrace();
        }
        return user;
    }
}