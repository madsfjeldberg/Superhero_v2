import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileWorker {

    private final File file;
    private final Scanner reader;

    public FileWorker() {
        file = new File("herolist.csv");
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addSuperheroToFile(String name, String realName, String superPower, int yearCreated, String isHuman, int strength) {
        File file = new File("herolist.csv");
        try {
            PrintStream output = new PrintStream(file);
            output.println(name + ", " + realName + ", " + superPower + ", " + yearCreated + ", " + isHuman + ", " + strength);
            output.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("'herolist.csv' not found.");
        }
    }


    public void saveList(ArrayList<Superhero> list) {
        File file = new File("herolist.csv");
        PrintStream output = null;
        try {
            output = new PrintStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // TODO: split hver attribut til substring, print individuelt
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
            name = trimString(heroValues[0]);
            realName = trimString(heroValues[1]);
            superPower = trimString(heroValues[2]);
            yearCreated = parseTrim(heroValues[3]);
            isHuman = trimString(heroValues[4]);
            strength = parseTrim(heroValues[5]);
            Superhero hero = new Superhero(name, realName,superPower, yearCreated, isHuman, strength);
            heroList.add(hero);
        }
        return heroList;
    }

    private String trimString(String input) {
        return input.trim();
    }

    private int parseTrim(String input) {
        return Integer.parseInt(input.trim());
    }
}
