import java.util.ArrayList;
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
    private boolean decreaseIngredientAmount(Ingredient ingredient, int amount) {
        Ingredient hashMapIngredient = ingredients.get(ingredient.getName().toLowerCase());
        if (hashMapIngredient.getCount() < amount) {
            return false;
        }
        hashMapIngredient.decreaseAmount(amount);
        return true;
    }
    // Same idea as decreaseIngredient, except cannot currently fail
    private boolean increaseIngredientAmount(Ingredient ingredient, int amount) {
        Ingredient hashMapIngredient = ingredients.get(ingredient.getName().toLowerCase());
        hashMapIngredient.increaseAmount(amount);
        return true;
    }

    public boolean hasIngredients(Recipe r) {
        HashMap<Ingredient, Integer> requirements = r.getIngredients();
        for(Ingredient i : requirements.keySet()) {
            String name = i.getName().toLowerCase();
            int amountRequired = requirements.get(i);
            int amountOwned = ingredients.get(name).getCount();
            if(amountRequired > amountOwned) {
                return false;
            }
        }
        return true;
    }

    public void removeIngredients(Recipe r) {
        HashMap<Ingredient, Integer> requirements = r.getIngredients();
        for(Ingredient i : requirements.keySet()) {
            decreaseIngredientAmount(i, requirements.get(i));
        }
    }

    public void addIngredients(Recipe r) {
        HashMap<Ingredient, Integer> requirements = r.getIngredients();
        for(Ingredient i : requirements.keySet()) {
            increaseIngredientAmount(i, requirements.get(i));
        }
    }

}
