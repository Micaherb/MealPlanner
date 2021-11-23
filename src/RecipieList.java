import java.util.ArrayList;

/**
 * Maintains list of all available recipies
 */
public class RecipieList {
    private ArrayList<Recipie> recipies;
    public RecipieList() {
        recipies = new ArrayList<Recipie>();
    }

    public void add(Recipie recipie){
        recipies.add(recipie);
    }

    public ArrayList<Recipie> getAll(){
        return recipies;
    }
}
