import java.util.ArrayList;
import java.util.HashMap;

/**
 * A possible Recipe
 */
public final class Recipe implements Comparable<Recipe> {
    private String name;
    private HashMap<Ingredient, Integer> ingredients;
    private RecipeType recipeType;
    public enum RecipeType { BREAKFAST, LUNCH, DINNER, ALL }
    public Recipe(String name, String type) {
        this.name = name;
        if(type.equals("Breakfast")){
            recipeType = RecipeType.BREAKFAST;
        } else if(type.equals("Lunch")){
            recipeType = RecipeType.LUNCH;
        } else if(type.equals("Dinner")){
            recipeType = RecipeType.DINNER;
        } else {
            recipeType = RecipeType.ALL;
        }
        ingredients = new HashMap<Ingredient, Integer>();
    }

    public void addIngredient(Ingredient ingredient, int count){
        Ingredient newIngredient = new Ingredient(ingredient);
        ingredients.put(newIngredient, count);
    }

    public void setRecipeType( RecipeType recipeType ) {
        this.recipeType = recipeType;
    }

    public HashMap<Ingredient, Integer> getIngredients(){
        return ingredients;
    }

    @Override
    public int compareTo(Recipe r) {
        return this.name.compareTo(r.name);
    }

    public RecipeType getRecipeType() {
        return recipeType;
    }

    /**
     * See if recipe has given restriction
     * @param r given restriction
     * @return if recipe has given restriction
     */
    public boolean isValidRestriction(String r) {
        for (Ingredient ingredient : ingredients.keySet()) {
            if (!ingredient.hasRestriction(r)) {
                return false;
            }
        }
        return true;
    }

    public String getName() {
        return this.name;
    }

    public boolean hasIngredientForName(String name) {
        for (Ingredient i : ingredients.keySet()) {
            if (i.getName().equals(name))
                return true;
        }
        return false;
    }
}

