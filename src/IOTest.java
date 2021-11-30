import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertTrue;
class IOTest {
    Planner testPlanner;
    IO testIO;
    static final String TEST_PROFILE_PATH = "profiles/mjerb.txt";
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        testPlanner = new Planner();
        testIO = new IO();
    }

    @org.junit.jupiter.api.Test
    void readIngredients() {
        testIO.readIngredients(testPlanner);
        // Test out a few scattered ingredients to make sure they're valid
        Ingredient ingredient1 = testPlanner.getIngredient("Ground Beef");
        Ingredient ingredient6 = testPlanner.getIngredient("Egg");
        Ingredient ingredient14 = testPlanner.getIngredient("Waffle Mix");
        // Make sure they're all valid ingredients
        Assert.assertNotNull(ingredient1);
        Assert.assertNotNull(ingredient6);
        Assert.assertNotNull(ingredient14);
        // Make sure all the units are correct
        Assert.assertEquals("1/4 Pound", ingredient1.getUnit());
        Assert.assertEquals("Egg", ingredient6.getUnit());
        Assert.assertEquals("Box", ingredient14.getUnit());
        // Make sure all the counts are correct
        Assert.assertEquals(8, ingredient1.getCount());
        Assert.assertEquals(3, ingredient6.getCount());
        Assert.assertEquals(1, ingredient14.getCount());
        // Make sure restrictions are present
        Assert.assertTrue(ingredient6.hasRestriction("Vegetarian"));
        Assert.assertTrue(ingredient14.hasRestriction("Vegetarian"));
        Assert.assertTrue(ingredient14.hasRestriction("Vegan"));
    }

    @org.junit.jupiter.api.Test
    void readRecipes() {
        testIO.readIngredients(testPlanner);
        testIO.readRecipes(testPlanner);
        Recipe recipe1 = testPlanner.getRecipes().get(0);
        Recipe recipe6 = testPlanner.getRecipes().get(5);
        Recipe recipe9 = testPlanner.getRecipes().get(8);
        // Make sure all recipes exist
        Assert.assertNotNull(recipe1);
        Assert.assertNotNull(recipe6);
        Assert.assertNotNull(recipe9);
        // Make sure all recipes have the appropriate name
        Assert.assertEquals("Burger", recipe1.getName());
        Assert.assertEquals("Bacon", recipe6.getName());
        Assert.assertEquals("Hot Dogs", recipe9.getName());
        // Make sure Recipes all have appropriate ingredients (Just ingredient 1 for now)
        Assert.assertTrue(recipe1.hasIngredientForName("Ground Beef"));
        Assert.assertTrue(recipe1.hasIngredientForName("Cheese"));
        Assert.assertTrue(recipe1.hasIngredientForName("Bread"));
        // ... Can add more ingredient checks for other recipes if required
        // Assert meal types are correct
        Assert.assertEquals(Recipe.RecipeType.DINNER, recipe1.getRecipeType());
        Assert.assertEquals(Recipe.RecipeType.BREAKFAST, recipe6.getRecipeType());
        Assert.assertEquals(Recipe.RecipeType.DINNER, recipe9.getRecipeType());
    }

    @org.junit.jupiter.api.Test
    void readProfile() {
        Profile testProfile = testIO.readProfile(TEST_PROFILE_PATH);
        Assert.assertFalse(testProfile.allowsIdenticalConsecutiveMeals());
        Assert.assertFalse(testProfile.allowsIdenticalConsecutiveMeals(Recipe.RecipeType.BREAKFAST));
        Assert.assertFalse(testProfile.allowsIdenticalConsecutiveMeals(Recipe.RecipeType.LUNCH));
        Assert.assertFalse(testProfile.allowsIdenticalConsecutiveMeals(Recipe.RecipeType.DINNER));
        Assert.assertTrue(testProfile.followsDailySchedule());
        Assert.assertTrue(testProfile.getDietaryRestrictions().isEmpty());
    }
}
