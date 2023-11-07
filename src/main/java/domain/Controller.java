package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Controller {

    private final Database db;

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

    public int getDatabaseSize() {
        return db.getSize();
    }

    public int getMaxDatabaseSize() {
        return db.getMaxSize();
    }

    public void addSuperhero(String name, String realName, String superPower, int yearCreated, String isHuman, int strength) {
        db.addSuperhero(name, realName, superPower, yearCreated, isHuman, strength);
    }

    public ReturnValue saveList() {
        return db.saveList();
    }

    public void sortDefault() {
        Collections.sort(db.getHeroList());
    }

    public void sort(int choice) {
        db.sort(choice);
    }

    public void sort2Parameters(Comparator<Superhero> choice1, Comparator<Superhero> choice2) {
        db.sort2Parameters(choice1, choice2);
    }


}
