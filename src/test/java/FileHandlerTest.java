import data.FileHandler;
import domain.Superhero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.ArrayList;

public class FileHandlerTest {

    FileHandler fh;
    ArrayList<Superhero> list;
    Superhero hero;

    @BeforeEach
    void setup() {
        fh = new FileHandler("herolistTEST.csv");
        list = new ArrayList<>();
        hero = new Superhero("Hero", "hero1", "testkræft", 1, "JA", 100);
        Superhero hero2 = new Superhero("Hero2", "hero2", "testkræft2", 2, "NEJ", 200);
        //Superhero hero3 = new Superhero("Hero3", "hero3", "testkræft3", 3, "JA", 300);
        list.add(hero);
        list.add(hero2);
        //list.add(hero3);
    }

    // kunne evt. gøres mere robust med et 'for' loop
    @Test
    void saveList() throws IOException {
        BufferedReader brTest = new BufferedReader(new FileReader("herolistTEST.csv"));
        fh.getFile().delete();
        fh.saveList(list);
        String text = brTest.readLine();
        text += brTest.readLine();
        assertEquals(text, "Hero,hero1,testkræft,1,JA,100Hero2,hero2,testkræft2,2,NEJ,200");
    }

    @Test
    void loadList() {
        assertEquals(list.toString(), fh.loadList().toString());
    }

}
