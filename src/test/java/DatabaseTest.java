import data.FileHandler;
import domain.Database;
import domain.Superhero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;



public class DatabaseTest {

    Database db;
    FileHandler fh;
    ArrayList<Superhero> list;

    @BeforeEach
    void setup() {
        db = new Database();
        fh = new FileHandler("herolistTEST.csv");
        list = new ArrayList<>();
        db.getHeroList().clear();
        Superhero hero = new Superhero("Hero", "hero1", "testkræft", 1, "JA", 100);
        Superhero hero2 = new Superhero("Hero2", "hero2", "testkræft2", 2, "NEJ", 200);
        Superhero hero3 = new Superhero("Hero3", "hero3", "testkræft3", 3, "JA", 300);
        list.add(hero);
        list.add(hero2);
        list.add(hero3);
        db.getHeroList().addAll(list);
    }

    @Test
    void saveList() {

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

        String result = db.search("hero1");

        assertEquals(result, expected);
    }

    @Test
    void indexedList() {
        String expected = """
                1. Hero
                2. Hero2
                3. Hero3
                """;
        String actual = db.indexedList();
        assertEquals(expected, actual);
    }
}
