import java.util.ArrayList;
import java.util.HashMap;

/**
 * A possible Recipe
 */
public class Recipe implements Comparable<Recipe> {
    private String name;
    private ArrayList<Ingredient> ingredients;
    public Recipe(String name) {
        this.name = name;
        ingredients = new ArrayList<Ingredient>();
    }

    public void addIngredient(Ingredient ingredient, int count){
        Ingredient newIngredient = new Ingredient(ingredient);
        ingredients.add(newIngredient);
    }

    public ArrayList<Ingredient> getIngredients(){
        return ingredients;
    }

    @Override
    public int compareTo(Recipe r) {
        return 1;
    }
}

