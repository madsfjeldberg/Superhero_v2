import java.util.ArrayList;

// TODO: hele molevitten
public class Controller {

    private Database db;

    public Controller() {
        db = new Database();
    }

    public void delete(int choice) {
        db.delete(choice);
    }

    public String indexedList() {
        return db.indexedList();
    }

    public String showInfo(Superhero hero) {
        return db.showInfo(hero);
    }

    public ArrayList<Superhero> getHeroList() {
        return db.getHeroList();
    }

    public ArrayList<Superhero> search(String query) {
        return db.search(query);
    }

    public String showList() {
        return db.showList();
    }

    public int getSize() {
        return db.getSize();
    }

    public int getMaxSize() {
        return db.getMaxSize();
    }

    public void addSuperhero(String name, String realName, String superPower, int yearCreated, String isHuman, int strength) {
        db.addSuperhero(name, realName, superPower, yearCreated, isHuman, strength);
    }

    public void edit(Superhero hero, int choice) {
        db.edit(hero, choice);
    }
}
