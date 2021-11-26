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
                Recipe r = new Recipe(name);
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
}