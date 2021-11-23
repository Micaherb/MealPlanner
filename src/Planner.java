import java.util.ArrayList;
import java.util.HashMap;

/**
 * Plans out meals
 */
public class Planner {
    private RecipieList recipies;
    private ArrayList<Recipie> mealPlan;
    private HashMap<String, Ingredient> totalIngredients;
    public Planner() {
        recipies = new RecipieList();
        mealPlan = new ArrayList<Recipie>();
        totalIngredients = new HashMap<String, Ingredient>();
    }

    public void addIngredient(Ingredient ingredient){
        totalIngredients.put(ingredient.getName(), ingredient);
    }

    public Ingredient getIngredient(String i){
        return totalIngredients.get(i);
    }

    public void addRecipie(Recipie recipie){
        recipies.add(recipie);
    }

    public HashMap<String, Ingredient> getIngredients(){
        return totalIngredients;
    }

    public ArrayList<Recipie> getRecipies(){
        return recipies.getAll();
    }
}
