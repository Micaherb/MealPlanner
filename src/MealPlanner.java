public class MealPlanner{
    //private static Algorithms algorithms = new Algorithms();

    public MealPlanner() {}

    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("Usage:");
            System.out.println("./MealPlanner [profile pathname] [meal #]");
            return;
        }
        //Read in files
        Planner planner = new Planner();
        IO io = new IO();
        io.readIngredients(planner);
        io.readRecipes(planner);
        //Setup user settings
        Profile user = io.readProfile(args[0]);
        planner.filterRecipes(user);
        //Run algorithm
        planner.generatePlans(user, Integer.parseInt(args[1]));
    }
}
