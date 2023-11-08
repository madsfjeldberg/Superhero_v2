import data.FileHandler;
import domain.Database;
import domain.Superhero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;



public class DatabaseTest {

    @BeforeEach
    void setup() {
        Database db = new Database();
        FileHandler fh = new FileHandler("herolistTEST.csv");
        ArrayList<Superhero> list = new ArrayList<>();
        Superhero hero = new Superhero("Hero", "hero1", "testkræft", 1, "JA", 100);
        Superhero hero2 = new Superhero("Hero2", "hero2", "testkræft2", 2, "NEJ", 200);
        Superhero hero3 = new Superhero("Hero3", "hero3", "testkræft3", 3, "JA", 300);
        list.add(hero);
        list.add(hero2);
        list.add(hero3);
    }

    @Test
    void saveList() {

    }

    @Test
    void search() {

    }

    @Test
    void indexedList() {

    }
}
