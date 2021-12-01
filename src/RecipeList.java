import java.util.ArrayList;

/**
 * Maintains list of all available recipes
 */
public class RecipeList {
    private static ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    public RecipeList() {}

    public void add(Recipe recipe){
        recipes.add(recipe);
    }

    public ArrayList<Recipe> getAll(){
        return recipes;
    }

    public static void filterRestriction(String r){
        for(int i = recipes.size()-1; i >= 0; i--) {
            if(!recipes.get(i).isValidRestriction(r)){
                recipes.remove(i);
            }
        }
    }
}
