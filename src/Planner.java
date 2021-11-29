import java.util.ArrayList;
import java.util.HashMap;

/**
 * Plans out meals
 */
// Test change
public class Planner {
    private RecipeList recipes;
    private HashMap<String, Ingredient> totalIngredients;
    public Planner() {
        recipes = new RecipeList();
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
        for(int i = 0; i < user.getDietaryRestrictions().size(); i++) {
            String r = user.getDietaryRestrictions().get(i);
            RecipeList.filterRestriction(r);
        }
    }

    public void generatePlans(Profile user, int mealCount) {
        //Create Graph
        MealPlanGraph mealPlan = new MealPlanGraph(recipes.getAll());
            //Fill out graph with vertices and edges
        //Run recursive algorithm
        recursivePlan(mealPlan);
    }

    private MealPlanGraph recursivePlan(MealPlanGraph mealPlan) {
        return mealPlan;
    }

}
