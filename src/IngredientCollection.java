import java.util.HashMap;

public class IngredientCollection {
    HashMap<String, Ingredient> ingredients;

    public IngredientCollection(HashMap<String, Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Returns true if enough ingredient is present to meet decrease request
     * @param ingredient given ingredient
     * @param amount amount to decrease by
     * @return if decrease was successful or not
     */
    public boolean decreaseIngredientAmount(Ingredient ingredient, int amount) {
        Ingredient hashMapIngredient = ingredients.get(ingredient.getName().toLowerCase());
        if (hashMapIngredient.getCount() < amount) {
            return false;
        }
        hashMapIngredient.decreaseAmount(amount);
        return true;
    }

    public boolean increaseIngredientAmount(Ingredient ingredient, int amount) {
        Ingredient hashMapIngredient = ingredients.get(ingredient.getName().toLowerCase());
        hashMapIngredient.increaseAmount(amount);
        return true;
    }

}
