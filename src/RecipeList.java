import java.util.ArrayList;

/**
 * Maintains list of all available recipes
 */
public class RecipeList {
    private ArrayList<Recipe> recipes;
    public RecipeList() {
        recipes = new ArrayList<Recipe>();
    }

    public void add(Recipe recipe){
        recipes.add(recipe);
    }

    public ArrayList<Recipe> getAll(){
        return recipes;
    }
}
