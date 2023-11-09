package domain;

import data.FileHandler;
import domain.comparators.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class Database {

    private ArrayList<Superhero> loadedList;
    private ArrayList<Superhero> heroList;
    private int size;
    private final int maxSize;
    FileHandler fh;

    public Database() {
        heroList = new ArrayList<>();
        size = 0;
        maxSize = 10;
        fh = new FileHandler("herolist.csv");
        heroList = fh.loadList(); // .csv fil bliver loadet ind i arraylist i 'domain.Database'
        loadedList = fh.loadList();

    }

    public boolean identicalCheck(ArrayList<Superhero> one, ArrayList<Superhero> two) {
        if (one.size() != two.size()) {
            return false;
        }

        for (int i = 0; i < one.size(); i++) {
            Superhero hero1 = one.get(i);
            Superhero hero2 = two.get(i);

            // Sammenlign attributerne af hero1 og hero2
            if (!areSuperheroesEqual(hero1, hero2)) {
                return false; // Hvis værdierne ikke er ens
            }
        }

        // Alle superhelte er ens i attributer
        return true;
    }

    // Hjælpe metode ti lat tjekke om to Superhelte har samme attributer
    private boolean areSuperheroesEqual(Superhero hero1, Superhero hero2) {
        return hero1.getSuperName().equals(hero2.getSuperName()) &&
                hero1.getRealName().equals(hero2.getRealName()) &&
                hero1.getSuperPower().equals(hero2.getSuperPower()) &&
                hero1.getYearCreated() == hero2.getYearCreated() &&
                hero1.isHuman().equals(hero2.isHuman()) &&
                hero1.getStrength() == hero2.getStrength();
    }

    public ReturnValue saveList() {
        if (!identicalCheck(heroList, loadedList)) {
            fh.saveList(heroList);
            loadedList = fh.loadList(); // resetter listen
            return ReturnValue.OK;
        } else return ReturnValue.CANT;
    }

    public void sort2Parameters(int choice1, int choice2) {
        Map<Integer, Comparator<Superhero>> comparatorMap = Map.of(
                1, new SuperNameComparator(),
                2, new RealNameComparator(),
                3, new SuperpowerComparator(),
                4, new YearComparator(),
                5, new IsHumanComparator(),
                6, new StrengthComparator()
        );
        Comparator<Superhero>[] choices = new Comparator[2];
        choices[0] = comparatorMap.getOrDefault(choice1, new SuperNameComparator());
        choices[1] = comparatorMap.getOrDefault(choice2, new SuperNameComparator());

        heroList.sort(choices[0].thenComparing(choices[1]));
        }

    public void sort(int choice) {
        switch (choice) {
            case 1 -> heroList.sort(new SuperNameComparator());
            case 2 -> heroList.sort(new RealNameComparator());
            case 3 -> heroList.sort(new SuperpowerComparator());
            case 4 -> heroList.sort(new YearComparator());
            case 5 -> heroList.sort(new IsHumanComparator());
            case 6 -> heroList.sort(new StrengthComparator());
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
    public String search(String query) {
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
        return Arrays.toString(foundHeroes.toArray()).replace("[", "").replace("]", "").replace(",", "");
    }

    public void addSuperhero(String name, String realName, String superPower, int yearCreated, String isHuman, int strength) {
        Superhero superhero = new Superhero(name, realName, superPower, yearCreated, isHuman, strength);
        heroList.add(superhero);
        size++;
    }

}
