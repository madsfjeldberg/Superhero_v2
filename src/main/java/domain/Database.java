package domain;

import data.FileHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Database {

    // attributes
    private ArrayList<Superhero> heroList;
    private int size;
    private final int maxSize;
    FileHandler fh;

    // konstruktør
    public Database() {
        heroList = new ArrayList<>();
        size = 0;
        maxSize = 10;
        fh = new FileHandler();
        heroList = fh.loadList(); // .csv fil bliver loadet ind i arraylist i 'domain.Database'
    }

    public void saveList() {
        fh.saveList(getHeroList());
    }

    public ArrayList<Superhero> getHeroList() {
        return heroList;
    }

    public void setHeroList(ArrayList<Superhero> list) {
        this.heroList = list;
    }

    public int getSize() {
        return size;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public String showList() {
        StringBuilder output = new StringBuilder();
        for (Superhero i: heroList) {
            output.append(i.getName()).append("\n");
        }
        return output.toString();
    }

    // skal måske bruges på et tidspunkt?
    // public void edit(domain.Superhero hero, int choice) {}

    public String showInfo(Superhero hero) {
        String output = "";
        output += "1. Superheltenavn: " + hero.getName() + "\n";
        output += "2. Virkeligt navn: " + hero.getRealName() + "\n";
        output += "3. Superkræft: " + hero.getSuperPower() + "\n";
        output += "4. Oprindelsesår: " + hero.getYearCreated() + "\n";
        output += "5. Er menneske: " + hero.isHuman() + "\n";
        output += "6. Styrke: " + hero.getStrength();
        return output;
    }

    // viser en nummereret liste over alle superhelt
    public String indexedList() {
        int index = 1;
        StringBuilder output = new StringBuilder();
        for (Superhero hero: heroList) {
            output.append(index++).append(". ").append(hero.getName()).append("\n");
        }
        return output.toString();
    }

    public void delete(int choice) {
        heroList.remove(choice);
    }

    // viser en liste af superhelte der passer med 'query'
    public ArrayList<Superhero> search(String query) {
        ArrayList<Superhero> foundHeroes = new ArrayList<>();
        for (Superhero hero : heroList) {
            if (hero.getName().toLowerCase().contains(query.toLowerCase()) ||
                    hero.getRealName().toLowerCase().contains(query.toLowerCase())) {
                foundHeroes.add(hero);
            }
        }
        if (foundHeroes.isEmpty()) {
            System.out.println("No hero found");
        }
        return foundHeroes;
    }

    public void addSuperhero(String name, String realName, String superPower, int yearCreated, String isHuman, int strength) {
        Superhero superhero = new Superhero(name, realName, superPower, yearCreated, isHuman, strength);
        heroList.add(superhero);
        size++;
    }

}
