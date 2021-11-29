import java.util.ArrayList;
import java.util.HashMap;

/**
 * Plans out meals
 */
// Test change
public class Planner {
    private RecipeList recipes;
    private MealPlanGraph mealPlan;
    private HashMap<String, Ingredient> totalIngredients;
    public Planner() {
        recipes = new RecipeList();
        mealPlan = new MealPlanGraph(recipes.getAll());
        totalIngredients = new HashMap<String, Ingredient>();
    }

    public void addIngredient(Ingredient ingredient){
        totalIngredients.put(ingredient.getName().toLowerCase(), ingredient);
    }

    public Ingredient getIngredient(String i){
        return totalIngredients.get(i.toLowerCase());
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

    public void filterRecipes(Profile user) {

    }

    public void generatePlans(Profile user) {

    }
}
