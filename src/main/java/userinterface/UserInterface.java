package userinterface;

import domain.Controller;
import domain.Superhero;
import domain.comparators.*;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// attributes
public class UserInterface {
    private final Controller ctrl;
    Scanner input;

    // konstruktør
    public UserInterface() {
        ctrl = new Controller();
        input = new Scanner(System.in);
    }

    // tjekker om der er tal eller symboler i et string request
    public boolean stringTester(String string) {

        Pattern pattern = Pattern.compile("^[A-Za-z]+( [A-Za-z]+)?$");
        Matcher matcher = pattern.matcher(string);
        return !matcher.find();
    }

    // tilføj superhelt til database
    public void addSuperhero() {

        if (ctrl.getDatabaseSize() < ctrl.getMaxDatabaseSize()) {
            System.out.println("Indtast data:\n");
            System.out.print("Superheltenavn (skriv 'n' hvis de ikke har et): ");
            String name = input.nextLine();
            if  (name.equals("n")) {
                name = "";
            }

            System.out.print("\nRigtige navn: ");
            String realName = input.nextLine();

            System.out.print("\nSuperkræft: ");
            String superPower = input.nextLine();

            int yearCreated = 0;
            System.out.print("\nÅrstal for skabelse: ");
            while (true) {
                if (input.hasNextInt()) {
                    yearCreated = input.nextInt();
                    break; // Exit the loop once a valid integer is entered
                } else {
                    System.out.println("Du skal indtaste et tal.");
                    input.next(); // Consume the invalid input to avoid an infinite loop
                }
            }



            System.out.print("\nEr superhelten et menneske? [y/n]: ");
            input.nextLine();
            String svar = input.nextLine();
            String isHuman;
            while (!svar.equals("y") && !svar.equals("n")) {
                System.out.println("Ugyldigt svar.");
                svar = input.nextLine();
            }
            if (svar.equals("y")) {
                isHuman = "JA";
            }
            else {
                isHuman = "NEJ";
            }

            System.out.print("\nStyrke: ");
            while (!input.hasNextInt()) {
                System.out.println("Du skal indtaste et tal.");
                input.next();
            }
            int strength = input.nextInt();
            System.out.println();

            ctrl.addSuperhero(name, realName, superPower, yearCreated, isHuman, strength);
            System.out.println("Superhelt tilføjet til databasen.\n");
            input.nextLine();
        } else System.out.println("Database er fuld.\n");
    }

    // redigerer en superhelt
    public void edit() {

        System.out.println("Superhelte i database:");
        System.out.println(ctrl.showList());

        String search;
        System.out.print("Hvilken superhelt vil du redigere?: ");
        search = input.nextLine();
        Superhero chosenSuperhero = null;
        for (Superhero hero : ctrl.getHeroList()) {
            if (hero.getSuperName().equalsIgnoreCase(search) ||
                    hero.getRealName().equalsIgnoreCase(search)) {
                chosenSuperhero = hero;
            }
        }

        if (chosenSuperhero != null) {
            String changeValueMessage = "Indtast ny værdi: ";
            System.out.println(ctrl.showInfo(chosenSuperhero));
            System.out.print("Hvad vil du ændre?: ");
            System.out.println();
            int choice = 0;
            while (true) {
                if (input.hasNextInt()) {
                    choice =  input.nextInt();
                    break;
                } else {
                    System.out.println("Du skal indtaste et tal.");
                    input.next();
                }
            }

            switch (choice) {
                case 1 -> {
                    System.out.print(changeValueMessage);
                    input.nextLine();
                    chosenSuperhero.setName(input.nextLine());
                }
                case 2 -> {
                    System.out.print(changeValueMessage);
                    input.nextLine();
                    chosenSuperhero.setRealName(input.nextLine());
                }
                case 3 -> {
                    System.out.print(changeValueMessage);
                    input.nextLine();
                    chosenSuperhero.setSuperPower(input.nextLine());
                }
                case 4 -> {
                    System.out.println(changeValueMessage);
                    chosenSuperhero.setYearCreated(input.nextInt());
                }
                case 5 -> {
                    System.out.println(changeValueMessage);
                    input.nextLine();
                    chosenSuperhero.setHuman(input.nextLine());
                }
                case 6 -> {
                    System.out.println(changeValueMessage);
                    chosenSuperhero.setStrength(input.nextInt());
                }
                case 0 -> { }
                default -> System.out.println("Ugyldigt svar.");
            }
            System.out.println("Superhelt opdateret:");
            System.out.println(ctrl.showInfo(chosenSuperhero));
            input.nextLine();
        } else System.out.println("Superhelt ikke fundet.");
    }

    // TODO: virker ikke hvis listen er tom
    // sletter en superhelt fra databasen
    public void delete() {

        System.out.println(ctrl.indexedList());
        System.out.print("Hvem skal slettes fra databasen? ");
        while (true) {
            if (!input.hasNextInt()) {
                System.out.println("Du skal indtaste et tal.");
                input.nextLine();
            } else {
                int choice = input.nextInt();
                if (choice >= 1 && choice <= ctrl.getHeroList().size()) {
                    ctrl.delete(choice - 1); // kald til database for at slette
                    System.out.println("\nSletter fra database...");
                    System.out.println("Superhelt slettet.\n");
                    break;
                } else {
                    System.out.println("Du skal indtaste gyldigt tal mellem 1 og " + ctrl.getHeroList().size() + ".");
                }
            }
        }
    }

    public void search() {

        System.out.println("Søg efter superhelt: ");
        String search = input.nextLine();
        for (Superhero hero: ctrl.search(search)) {
            System.out.println(ctrl.showInfo(hero));
            System.out.println();
        }
    }

    public String numList() {
        return """
                1. Superheltenavn
                2. Rigtige Navn
                3. Superkræft
                4. Årstal for skabelse
                5. Er Menneske
                6. Styrke
                """;
    }

    public void sort() {
        int choice;
        System.out.println("Hvad vil du sortere efter?");
        System.out.println(numList());
        // TODO: try/catch blok
        choice = input.nextInt();
        ctrl.sort(choice);
    }

    public void sort2Parameters() {
        Map<Integer, Comparator<Superhero>> comparatorMap = Map.of(
                1, new SuperNameComparator(),
                2, new RealNameComparator(),
                3, new SuperpowerComparator(),
                4, new YearComparator(),
                5, new IsHumanComparator(),
                6, new StrengthComparator()
        );
        Comparator<Superhero>[] choices = new Comparator[2];

        for (int i=0; i < 2; i++) {
            System.out.println("Vælg " + (i+1) + ". parameter:");
            System.out.println(numList());
            // TODO: try/catch blok
            int choice = input.nextInt();

            choices[i] = comparatorMap.getOrDefault(choice, new SuperNameComparator());
        }
        ctrl.sort2Parameters(choices[0], choices[1]);
    }

    // viser database menu
    public void databaseMenu() {

        System.out.print("─".repeat(25) + "\n");
        System.out.println("MENU");
        System.out.print("─".repeat(25) + "\n");
        System.out.println("""
                1. Opret superhelt
                2. Vis liste
                3. Søg efter superhelt
                4. Rediger superhelt
                5. Slet en superhelt
                6. Gem liste
                7. Sortér liste
                8. Sortér liste efter parameter
                9. Sortér liste efter 2 parametre
                0. Afslut
                >
                """);
    }

    // viser menu, og kører alle metoder for databasen
    public void runDatabase() {

        boolean run = true;
        int choice = 99;
        System.out.println("Velkommen til SUPERHERO UNIVERSET.");
        do {
            databaseMenu();
            try {
                System.out.print("Vælg en funktion:");
                choice = Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Du skal indtaste et nummer.");
            }
            switch (choice) {
                case 1 -> addSuperhero();
                case 2 -> System.out.println(ctrl.showList());
                case 3 -> search();
                case 4 -> edit();
                case 5 -> delete();
                case 6 -> {
                    switch (ctrl.saveList()) {
                        case OK -> System.out.println("Liste gemt.");
                        case CANT -> System.out.println("Ingen ændringer at gemme.");
                    }
                }
                case 7 -> ctrl.sortDefault();
                // TODO: print det pænt
                case 8 -> {
                    sort();
                    System.out.println(ctrl.showList());
                }
                case 9 -> {
                    sort2Parameters();
                    System.out.println(ctrl.showList());
                }
                case 0 -> run = false;
            }
        } while (run);
    }
}
