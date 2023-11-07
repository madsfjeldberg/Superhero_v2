import data.FileHandler;
import domain.Database;
import domain.Superhero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    @BeforeEach
    void setup() {
        Database db = new Database();
        FileHandler fh = new FileHandler();
        ArrayList<Superhero> list = new ArrayList<>();
        Superhero hero = new Superhero("Hero", "herohero", "testkræft", 2000, "JA", 100);
        list.add(hero);
    }



}
