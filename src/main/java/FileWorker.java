import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileWorker {

    private final File file;
    private final Scanner reader;

    public FileWorker() {
        file = new File("herolist.txt");
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addSuperheroToFile(String name, String realName, String superPower, int yearCreated, String isHuman, int strength) {
        File file = new File("herolist.txt");
        try {
            PrintStream output = new PrintStream(file);
            output.println(name + ", " + realName + ", " + superPower + ", " + yearCreated + ", " + isHuman + ", " + strength);
            output.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("'herolist.txt' not found.");
        }
    }

    public void saveList(ArrayList<Superhero> list) {
        File file = new File("herolist.txt");
        PrintStream output = null;
        try {
            output = new PrintStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (Superhero hero: list) {
            output.println(hero);
        }

    }

    public ArrayList<Superhero> loadList() {

        ArrayList<Superhero> heroList = new ArrayList<>();
        String name;
        String realName;
        String superPower;
        int yearCreated;
        String isHuman;
        int strength;

        while (reader.hasNextLine()) {
            String[] heroValues = reader.nextLine().split(",");
            name = heroValues[0];
            realName = heroValues[1];
            superPower = heroValues[2];
            yearCreated = Integer.parseInt(heroValues[3]);
            isHuman = heroValues[4];
            strength = Integer.parseInt(heroValues[5]);
            Superhero hero = new Superhero(name, realName,superPower, yearCreated, isHuman, strength);
            heroList.add(hero);
        }
        return heroList;
    }
}
