import data.FileHandler;
import domain.Database;
import domain.Superhero;

import domain.comparators.StrengthComparator;
import domain.comparators.SuperNameComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import domain.comparators.RealNameComparator;


public class DatabaseTest {

    Database db;
    FileHandler fh;
    ArrayList<Superhero> list;
    Superhero hero;
    Superhero hero2;
    Superhero hero3;

    @BeforeEach
    void setup() {
        db = new Database();
        fh = new FileHandler("herolistTEST.csv");
        list = new ArrayList<>();
        db.getHeroList().clear();
        hero = new Superhero("Hero", "hero1", "testkræft", 1, "JA", 100);
        hero2 = new Superhero("Hero2", "hero2", "testkræft2", 2, "NEJ", 200);
        hero3 = new Superhero("Hero3", "hero3", "testkræft3", 3, "JA", 300);
        list.add(hero);
        list.add(hero2);
        list.add(hero3);
        db.getHeroList().addAll(list);
    }

    @Test
    void saveList() {
        ArrayList<Superhero> list2 = new ArrayList<>(list);

        ArrayList<Superhero> list3 = new ArrayList<>();
        list3.add(hero);
        list3.add(hero2);

        assertAll("Tester på true og false",
                () -> assertEquals(list, list2),
                () -> assertNotEquals(list, list3)
        );
    }

    @Test
    void search() {
        String expected = """ 
                Superheltenavn: Hero
                Rigtigt navn: hero1
                Superkræft: testkræft
                Årstal: 1
                Er menneske: JA
                Styrke: 100
                """;
        String actual = db.search("hero1");
        assertEquals(actual, expected);
    }

    @Test
    void indexedList() {
        String expected = """
                1. Hero
                2. Hero2
                3. Hero3
                """;
        String actual = db.indexedList();
        assertEquals(actual, expected);
    }

   @Test
    void showInfo() {
        String expected = "";
        expected += "1. Superheltenavn: " + hero.getSuperName() + "\n";
        expected += "2. Virkeligt navn: " + hero.getRealName() + "\n";
        expected += "3. Superkræft: " + hero.getSuperPower() + "\n";
        expected += "4. Oprindelsesår: " + hero.getYearCreated() + "\n";
        expected += "5. Er menneske: " + hero.isHuman() + "\n";
        expected += "6. Styrke: " + hero.getStrength();
        String actual = db.showInfo(hero);
        assertEquals(actual, expected);
    }


    @Test
    void showList() {
        StringBuilder expected = new StringBuilder();
        for (Superhero i : list) {
            expected.append(i.getSuperName()).append("\n");
        }

        String actual = db.showList();
        assertEquals(actual, expected.toString());
    }

    @Test
    void testSort() {
        // Arrange: Create a sorted copy of the list
        ArrayList<Superhero> sortedList = new ArrayList<>(list);
        sortedList.sort(new StrengthComparator());

        // Act: Sort the original list
        db.sort(1); // Sort by real name (you can replace 2 with the appropriate choice)

        // Assert: Compare the sorted list with the list in the database
        assertEquals(sortedList, db.getHeroList());
    }

    @Test
    void testSort2Parameters() {
        // Arrange: Create a sorted copy of the list using two comparators
        ArrayList<Superhero> sortedList = new ArrayList<>(list);
        sortedList.sort(new SuperNameComparator().thenComparing(new RealNameComparator()));

        // Act: Sort the original list using two comparators
        // den er ændret, tager kun int input
        db.sort2Parameters(1, 2);

        // Assert: Compare the sorted list with the list in the database
        assertEquals(sortedList, db.getHeroList());
    }

    @Test
    void testIdenticalCheck() {
        Database db = new Database();

        //Hvis lister matcher
        ArrayList<Superhero> list1 = new ArrayList<>();
        list1.add(new Superhero("Batman", "Bruce Wayne", "Rich", 1939, "YES", 200));
        list1.add(new Superhero("Superman", "Jenny", "Power2", 2010, "NO", 150));

        ArrayList<Superhero> list2 = new ArrayList<>();
        list2.add(new Superhero("Batman", "Bruce Wayne", "Rich", 1939, "YES", 200));
        list2.add(new Superhero("Superman", "Jenny", "Power2", 2010, "NO", 150));

        //Hvis listen ikke matcher
        ArrayList<Superhero> list3 = new ArrayList<>();
        list3.add(new Superhero("Batman", "Bruce Wayne", "Rich", 1939, "YES", 200));
        list3.add(new Superhero("Superman", "John", "Power2", 2010, "NO", 150));
        list3.add(new Superhero("Hero3", "Mike", "Power3", 2020, "YES", 120));

        assertAll( "Double test",
                () -> assertTrue(db.identicalCheck(list1, list2), "Case 1: Lists are identical"),
                () -> assertFalse(db.identicalCheck(list1, list3), "Case 2: Lists are not identical")
        );
    }
    @Test
    void testDelete() {
        Superhero heroToDelete = new Superhero("ToDelete", "John", "Power", 2000, "YES", 100);

        db.addSuperhero(heroToDelete.getSuperName(), heroToDelete.getRealName(),
                heroToDelete.getSuperPower(), heroToDelete.getYearCreated(),
                heroToDelete.isHuman(), heroToDelete.getStrength());
        db.delete(0);

        assertFalse(db.getHeroList().contains(heroToDelete));
    }

    @Test
    void testAddSuperhero() {
        Database db = new Database();
        String name = "BatKarl";
        String realName = "Karl";
        String superPower = "Falling down stairs";
        int yearCreated = 1928;
        String isHuman = "YES";
        int strength = 150;

        db.addSuperhero(name, realName, superPower, yearCreated, isHuman, strength);

        ArrayList<Superhero> heroList = db.getHeroList();
        assertTrue(heroList.stream().anyMatch(hero ->
                hero.getSuperName().equals(name) &&
                        hero.getRealName().equals(realName) &&
                        hero.getSuperPower().equals(superPower) &&
                        hero.getYearCreated() == yearCreated &&
                        hero.isHuman().equals(isHuman) &&
                        hero.getStrength() == strength));
    }
}


