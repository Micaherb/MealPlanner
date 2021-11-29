public class MealPlanner{
    //private static Algorithms algorithms = new Algorithms();

    public MealPlanner() {}

    public static void main(String[] args) {
        if(args.length != 3) {
            System.out.println("Usage:");
            System.out.println("./MealPlanner [days] [profile pathname]");
            return;
        }
        //Read in files
        Planner planner = new Planner();
        IO io = new IO();
        io.readIngredients(planner);
        io.readRecipes(planner);
        //Setup user settings
        Profile user = io.readProfile(args[1]);
        planner.filterRecipes(user);
        //Run algorithm
        planner.generatePlans(user);
    }
}
