import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.PrintStream;


public class Database {

    // attributes
    private ArrayList<Superhero> heroList;
    private int size;
    private final int maxSize;

    // konstruktør
    public Database() {
        heroList = new ArrayList<>();
        size = 0;
        maxSize = 10;
        // TODO: skal nok fjernes når vi skal bruge printstream
        // temp superhelte for testing
        Superhero superhero = new Superhero("Batman", "Bruce Wayne", "Money", 1980, "JA", 200);
        Superhero superhero1 = new Superhero("Superman", "Clark Kent", "Flight", 1990, "NEJ", 2000);
        Superhero superhero2 = new Superhero("Martian", "Jon", "Flight, Strength", 2000, "NEJ", 500);
        heroList.add(superhero);
        heroList.add(superhero1);
        heroList.add(superhero2);
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
    public void edit(Superhero hero, int choice) {

    }

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
