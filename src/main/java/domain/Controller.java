package domain;

import domain.comparators.StrengthComparator;

import java.util.ArrayList;
import java.util.Collections;

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

    public void saveList() {
        db.saveList();
    }

    public void sortListAfterRealName() {
        Collections.sort(db.getHeroList());
    }

    public void sortListStrength() {
        Collections.sort(db.getHeroList(), new StrengthComparator());

    }



}
