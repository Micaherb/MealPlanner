import java.util.HashMap;

/**
 * A possible Recipie
 */
public class Recipie {
    private String name;
    private HashMap<Ingredient, Integer> ingredients;
    public Recipie(String name) {
        this.name = name;
        ingredients = new HashMap<Ingredient, Integer>();
    }

    public void addIngredient(Ingredient ingredient, int count){
        ingredients.put(ingredient, count);
    }

    public HashMap<Ingredient, Integer> getIngredients(){
        return ingredients;
    }
}

