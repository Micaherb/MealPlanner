import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Plans out meals
 */
// Test change
public class Planner {
    private RecipeList recipes;
    private HashMap<String, Ingredient> totalIngredients;
    public Planner() {
        recipes = new RecipeList();
        totalIngredients = new HashMap<String, Ingredient>();
    }

    public void addIngredient(Ingredient ingredient){
        totalIngredients.put(ingredient.getName().toLowerCase(), ingredient);
    }

    public Ingredient getIngredient(String i){
        return totalIngredients.get(i.toLowerCase());
    }

    public void addRecipe(Recipe recipe){
        recipes.add(recipe);
    }

    public HashMap<String, Ingredient> getIngredients(){
        return totalIngredients;
    }

    public ArrayList<Recipe> getRecipes(){
        return recipes.getAll();
    }

    public void filterRecipes(Profile user) {
        for(int i = 0; i < user.getDietaryRestrictions().size(); i++) {
            String r = user.getDietaryRestrictions().get(i);
            RecipeList.filterRestriction(r);
        }
    }

    public void generatePlans(Profile user, int mealCount) {
        //Create Graph
        MealPlanGraph mealPlan = new MealPlanGraph(recipes.getAll());
        //Create Vertices
        for (int i = 0; i < mealCount; i++) {
            mealPlan.addVertex(i, null, recipes.getAll());
        }
        //allowsConsecutiveIdenticalMeals Edges
        if (!user.allowsIdenticalConsecutiveMeals()) {
            for (int i = 0; i < mealCount-1; i++) {
                mealPlan.createNotEqualEdge(i, i+1);
            }
        }
        //allowsConsecutiveIdenticalBreakfast Edges
        if (!user.allowsIdenticalConsecutiveMeals(Recipe.RecipeType.BREAKFAST)) {
            for (int i = 0; i < mealCount; i+=3) {
                if(i+3 < mealCount) {
                    mealPlan.createNotEqualEdge(i, i+3);
                }
            }
        }
        //allowsConsecutiveIdenticalLunch Edges
        if (!user.allowsIdenticalConsecutiveMeals(Recipe.RecipeType.LUNCH)) {
            for (int i = 1; i < mealCount; i+=3) {
                if(i+3 < mealCount) {
                    mealPlan.createNotEqualEdge(i, i+3);
                }
            }
        }
        //allowsConsecutiveIdenticalDinner Edges
        if (!user.allowsIdenticalConsecutiveMeals(Recipe.RecipeType.DINNER)) {
            for (int i = 2; i < mealCount; i+=3) {
                if(i+3 < mealCount) {
                    mealPlan.createNotEqualEdge(i, i+3);
                }
            }
        }
        //followsDailySchedule Edges
        if (user.followsDailySchedule()) {
            for (int i = 0; i < mealCount; i++) {
                Recipe.RecipeType type;
                if(i%3 == 0 ) {
                    type = Recipe.RecipeType.BREAKFAST;
                } else if(i%3 == 1 ) {
                    type = Recipe.RecipeType.LUNCH;
                } else {
                    type = Recipe.RecipeType.DINNER;
                }

                if(i+3 < mealCount) {
                    mealPlan.createBothGivenRecipeTypeEdge(i, i+3, type);
                } else {
                    mealPlan.createBothGivenRecipeTypeEdge(i, i, type);
                }
            }
        }
        //Run recursive algorithm
        ArrayList<String> solutionStrings = recursivePlan(mealPlan);

        // If no solutions, print out no solutions
        if (solutionStrings.isEmpty()) {
            System.out.println("No meal plan solutions possible");
        }
        else {
            if(solutionStrings.size() < 5) {
                for (String solution : solutionStrings) {
                    // Print all solutions
                    System.out.println(solution);
                }
            } else {
                ArrayList<Integer> chosen = new ArrayList<Integer>();
                for(int i = 0; i < 5; i++) {
                    // Print 5 different random solutions
                    chosen.add((int) (Math.random() * (solutionStrings.size())));
                    System.out.println(solutionStrings.get(chosen.get(i)).substring(0,solutionStrings.get(chosen.get(i)).length()-2));
                }
            }
        }
    }

    private ArrayList<String> recursivePlan(MealPlanGraph mealPlan) {
        ForwardChecking forwardCheckInference = new ForwardChecking();
        BackTrackingAlgorithm backtrackSolver = new BackTrackingAlgorithm(mealPlan, forwardCheckInference, totalIngredients);
        return backtrackSolver.run();
    }

}
