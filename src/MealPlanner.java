public class MealPlanner{
    //private static Algorithms algorithms = new Algorithms();

    public MealPlanner() {}

    public static void main(String[] args) {
        Planner planner = new Planner();
        IO io = new IO();
        io.readIngredients(planner);
        io.readRecipes(planner);
        //Stuff Happens
    }
}
