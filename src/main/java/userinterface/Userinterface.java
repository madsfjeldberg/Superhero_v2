package userinterface;

import domain.Controller;
import domain.Superhero;
import domain.comparators.*;

import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// attributes
public class Userinterface {
    private final Controller ctrl;
    Scanner input;

    // konstruktør
    public Userinterface() {
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
            while (stringTester(name)) {
                System.out.println("Du skal indtaste et gyldigt navn.");
                name = input.nextLine();
            }

            System.out.print("\nRigtige navn: ");
            String realName = input.nextLine();
            while (stringTester(realName)) {
                System.out.println("Du skal indtaste et gyldigt navn.");
                input.nextLine();
            }

            System.out.print("\nSuperkræft: ");
            String superPower = input.nextLine();
            while (stringTester(superPower)) {
                System.out.println("Du skal indtaste en gyldig superkræft.");
                input.nextLine();
            }

            System.out.print("\nÅrstal for skabelse: ");
            while (!input.hasNextInt()) {
                System.out.println("Du skal indtaste et tal.");
                input.next();
            }
            int yearCreated = input.nextInt();

            System.out.print("\nEr superhelten et menneske? [y/n]: ");
            String svar = input.next();
            String isHuman;
            while (!svar.equals("y") && !svar.equals("n")) {
                System.out.println("Ugyldigt svar.");
                svar = input.next();
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
        } else System.out.println("domain.Database er fuld.\n");
    }

    // redigerer en superhelt
    public void edit() {

        System.out.println("Superhelte i database:");
        System.out.println(ctrl.showList());

        System.out.print("Hvilken superhelt vil du redigere?: ");
        String search = input.nextLine();
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
            int choice = input.nextInt();
            // ctrl.edit(chosenSuperhero, choice);
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
                input.next();
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

    public void sortMetode() {
        int choice;
        System.out.println("Hvad vil du sortere efter?");
        System.out.println
                ("1. Superheltenavn\n" +
                "2. Rigtige Navn\n" +
                "3. Superkræft\n" +
                "4. Årstal for skabelse\n" +
                "5. Er Menneske\n" +
                "6. Styrke\n");
        choice = input.nextInt();
        ctrl.sortListInput(choice);
    }

    public void sortMetode2Parametre() {
        Comparator choice1 = new SuperNameComparator();
        Comparator choice2 = new SuperNameComparator();
        System.out.println("Vælg 1. parameter:");
        System.out.println
                ("1. Superheltenavn\n" +
                        "2. Rigtige Navn\n" +
                        "3. Superkræft\n" +
                        "4. Årstal for skabelse\n" +
                        "5. Er Menneske\n" +
                        "6. Styrke\n");
        switch (input.nextInt()) {
            case 1 -> choice1 = new SuperNameComparator();
            case 2 -> choice1 = new RealNameComparator();
            case 3 -> choice1 = new SuperpowerComparator();
            case 4 -> choice1 = new YearComparator();
            case 5 -> choice1 = new IsHumanComparator();
            case 6 -> choice1 = new StrengthComparator();
        }
        System.out.println("Vælg 2. parameter:");
        System.out.println
                ("1. Superheltenavn\n" +
                        "2. Rigtige Navn\n" +
                        "3. Superkræft\n" +
                        "4. Årstal for skabelse\n" +
                        "5. Er Menneske\n" +
                        "6. Styrke\n");
        switch (input.nextInt()) {
            case 1 -> choice2 = new SuperNameComparator();
            case 2 -> choice2 = new RealNameComparator();
            case 3 -> choice2 = new SuperpowerComparator();
            case 4 -> choice2 = new YearComparator();
            case 5 -> choice2 = new IsHumanComparator();
            case 6 -> choice2 = new StrengthComparator();
        }
        ctrl.sortListInput2Parametre(choice1, choice2);
    }

    // viser database menu
    public void databaseMenu() {

        System.out.print("─".repeat(25) + "\n");
        System.out.println("MENU");
        System.out.print("─".repeat(25) + "\n");
        System.out.println("1. Opret superhelt");
        System.out.println("2. Vis liste");
        System.out.println("3. Søg efter superhelt");
        System.out.println("4. Rediger superhelt");
        System.out.println("5. Slet en superhelt");
        System.out.println("6. Gem liste");
        System.out.println("7. Sortér liste (ikke implementeret)");
        System.out.println("8. Sortér liste efter specifik parameter (ikke implementeret)");
        System.out.println("9. Sortér liste efter 2 parametre");
        System.out.println("10. Afslut");
        System.out.print("> ");
    }

    // TODO: exception handling ved indtastning af andet end int
    // viser menu, og kører alle metoder for databasen
    public void runDatabase() {

        boolean run = true;
        int choice;
        System.out.println("Velkommen til SUPERHERO UNIVERSET.");
        do {
            databaseMenu();
            choice = input.nextInt();
            input.nextLine(); // fjerner dead space hvis det eksisterer
            switch (choice) {
                case 1 -> addSuperhero();
                case 2 -> System.out.println(ctrl.showList());
                case 3 -> search();
                case 4 -> edit();
                case 5 -> delete();
                case 6 -> ctrl.saveList();
                case 7 -> ctrl.sortListAfterRealName();
                // TODO: print det pænt
                case 8 -> {
                    sortMetode();
                    System.out.println(ctrl.showList());
                }
                case 9 -> {
                    sortMetode2Parametre();
                    System.out.println(ctrl.getHeroList());
                }
                case 10 -> run = false;
                default -> System.out.println("\nUgyldigt input.\n");
            }
        } while (run);
    }
}
