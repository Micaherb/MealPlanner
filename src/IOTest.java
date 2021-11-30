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
        assertNotNull(ingredient1);
        assertNotNull(ingredient6);
        assertNotNull(ingredient14);
        // Make sure all the units are correct
        assertEquals("1/4 Pound", ingredient1.getUnit());
        assertEquals("Egg", ingredient6.getUnit());
        assertEquals("Box", ingredient14.getUnit());
        // Make sure all the counts are correct
        assertEquals(8, ingredient1.getCount());
        assertEquals(3, ingredient6.getCount());
        assertEquals(1, ingredient14.getCount());
        // Make sure restrictions are present
        Assertions.assertTrue(ingredient6.hasRestriction("Vegetarian"));
        Assertions.assertTrue(ingredient14.hasRestriction("Vegetarian"));
        Assertions.assertTrue(ingredient14.hasRestriction("Vegan"));
    }

    @org.junit.jupiter.api.Test
    void readRecipes() {
        testIO.readRecipes(testPlanner);
        Recipe recipe1 = testPlanner.getRecipes().get(0);
        Recipe recipe6 = testPlanner.getRecipes().get(5);
        Recipe recipe9 = testPlanner.getRecipes().get(8);
        // Make sure all recipes exist
        assertNotNull(recipe1);
        assertNotNull(recipe6);
        assertNotNull(recipe9);
        // Make sure all recipes have the appropriate name
        assertEquals("Burger", recipe1.getName());
        assertEquals("Bacon", recipe6.getName());
        assertEquals("Hot Dogs", recipe9.getName());
        // Make sure Recipes all have appropriate ingredients (Just ingredient 1 for now)
        Assertions.assertTrue(recipe1.hasIngredientForName("Ground Beef"));
        Assertions.assertTrue(recipe1.hasIngredientForName("Cheese"));
        Assertions.assertTrue(recipe1.hasIngredientForName("Bread"));
        // ... Can add more ingredient checks for other recipes if required
        // Assert meal types are correct
        Assertions.assertEquals(Recipe.RecipeType.DINNER, recipe1.getRecipeType());
        Assertions.assertEquals(Recipe.RecipeType.BREAKFAST, recipe6.getRecipeType());
        Assertions.assertEquals(Recipe.RecipeType.DINNER, recipe9.getRecipeType());
    }

    @org.junit.jupiter.api.Test
    void readProfile() {
        Profile testProfile = testIO.readProfile(TEST_PROFILE_PATH);
        assertFalse(testProfile.allowsIdenticalConsecutiveMeals());
        assertFalse(testProfile.allowsIdenticalConsecutiveMeals(Recipe.RecipeType.BREAKFAST));
        assertFalse(testProfile.allowsIdenticalConsecutiveMeals(Recipe.RecipeType.LUNCH));
        assertFalse(testProfile.allowsIdenticalConsecutiveMeals(Recipe.RecipeType.DINNER));
        Assertions.assertTrue(testProfile.followsDailySchedule());
        Assertions.assertTrue(testProfile.getDietaryRestrictions().isEmpty());
    }
}