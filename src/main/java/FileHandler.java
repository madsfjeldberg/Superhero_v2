import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private final File file;

    public FileHandler() {
        file = new File("herolist.csv");
    }

    public void saveList(ArrayList<Superhero> list) {

        try (PrintStream output = new PrintStream(file)) {
            for (Superhero hero : list) {
                String out;
                out = hero.getName() + "," + hero.getRealName() + "," + hero.getSuperPower()
                        + "," + hero.getYearCreated() + "," + hero.isHuman() + "," + hero.getStrength();
                output.println(out);
                System.out.println("Superhelt gemt.");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
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

        try (Scanner reader = new Scanner(file)) {
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
        }
        catch (FileNotFoundException e) {
            System.out.println("Fil ikke fundet.");
        }
        return heroList;
    }

    private String trimString(String input) {
        return input.trim();
    }

    private int parseTrim(String input) {
        return Integer.parseInt(trimString(input));
    }
}
