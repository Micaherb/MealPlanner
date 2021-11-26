import java.util.ArrayList;
import java.util.HashMap;

/**
 * Plans out meals
 */
// Test change
public class Planner {
    private RecipeList recipes;
    private ArrayList<Recipe> mealPlan;
    private HashMap<String, Ingredient> totalIngredients;
    public Planner() {
        recipes = new RecipeList();
        mealPlan = new ArrayList<Recipe>();
        totalIngredients = new HashMap<String, Ingredient>();
    }

    public void addIngredient(Ingredient ingredient){
        totalIngredients.put(ingredient.getName(), ingredient);
    }

    public Ingredient getIngredient(String i){
        return totalIngredients.get(i);
    }

    public void addRecipe(Recipe recipe){
        recipes.add(recipe);
    }

    public HashMap<String, Ingredient> getIngredients(){
        return totalIngredients;
    }

    public ArrayList<Recipe> getRecipes(){
        return recipes.getAll();
    }
}
