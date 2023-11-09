import data.FileHandler;
import domain.Database;
import domain.Superhero;

import domain.comparators.SuperNameComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import domain.comparators.SuperNameComparator;
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

    //Hvorfor virker det ikke? Resultat er ens
    @Test
    void showList() {
        StringBuilder expected = new StringBuilder();
        for (Superhero i : list) {
            expected.append(i.getSuperName()).append("\n");
        }
        expected.toString();
        String actual = db.showList();
        assertEquals(actual, expected);
    }

    @Test
    void testSort() {
        // Arrange: Create a sorted copy of the list
        ArrayList<Superhero> sortedList = new ArrayList<>(list);
        sortedList.sort(new SuperNameComparator());

        // Act: Sort the original list
        db.sort(1); // Sort by real name (you can replace 2 with the appropriate choice)

        // Assert: Compare the sorted list with the list in the database
        assertEquals(sortedList, db.getHeroList());
    }
    @Test
    void testSort2Parameters() {
        // Arrange: Create a sorted copy of the list using two comparators
        ArrayList<Superhero> sortedList = new ArrayList<>(list);
        sortedList.sort(new SuperNameComparator().thenComparing(new SuperNameComparator()));

        // Act: Sort the original list using two comparators
        db.sort2Parameters(new RealNameComparator(), new SuperNameComparator());

        // Assert: Compare the sorted list with the list in the database
        assertEquals(sortedList, db.getHeroList());
    }
}


