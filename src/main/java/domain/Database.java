package domain;

import data.FileHandler;
import domain.comparators.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Database {

    // attributes
    private ArrayList<Superhero> loadedList;
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
        loadedList = new ArrayList<>(heroList);

    }

    public boolean identicalCheck(ArrayList<Superhero> one, ArrayList<Superhero> two) {
        // sætter lister til ny variable så vi ikke sorterer
        // på de originale lister
        one = new ArrayList<>(one);
        two = new ArrayList<>(two);
        // sorterer midlertidige lister, og checker om de er ens
        Collections.sort(one);
        Collections.sort(two);
        return one.equals(two);

    }

    public ReturnValue saveList() {
        if (!identicalCheck(heroList, loadedList)) {
            fh.saveList(heroList);
            loadedList = new ArrayList<>(heroList);
            return ReturnValue.OK;
        } else return ReturnValue.CANT;

    }

    public void sort2Parameters(Comparator<Superhero> choice1, Comparator<Superhero> choice2) {
        Collections.sort(heroList, choice1.thenComparing(choice2));
        }

    public void sort(int choice) {
        switch (choice) {
            case 1 -> Collections.sort(heroList, new SuperNameComparator());
            case 2 -> Collections.sort(heroList, new RealNameComparator());
            case 3 -> Collections.sort(heroList, new SuperpowerComparator());
            case 4 -> Collections.sort(heroList, new YearComparator());
            case 5 -> Collections.sort(heroList, new IsHumanComparator());
            case 6 -> Collections.sort(heroList, new StrengthComparator());
        }
    }

    public ArrayList<Superhero> getHeroList() {
        return heroList;
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
            output.append(i.getSuperName()).append("\n");
        }
        return output.toString();
    }

    public String showInfo(Superhero hero) {
        String output = "";
        output += "1. Superheltenavn: " + hero.getSuperName() + "\n";
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
            output.append(index++).append(". ").append(hero.getSuperName()).append("\n");
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
            if (hero.getSuperName().toLowerCase().contains(query.toLowerCase()) ||
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
