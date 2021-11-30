import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertTrue;
class IOTest {
    Planner testPlanner;
    IO testIO;

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
        Recipe recipe2 = testPlanner.getRecipes().get(5);
        Recipe recipe3 = testPlanner.getRecipes().get(8);
        // ...
    }

    @org.junit.jupiter.api.Test
    void readProfile() {
    }
}