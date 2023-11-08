import data.FileHandler;
import domain.Superhero;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.ArrayList;

public class FileHandlerTest {

    FileHandler fh;
    ArrayList<Superhero> list;
    Superhero hero;
    File f;

    @BeforeEach
    void setup() throws IOException {
        f = new File("herolistTEST.csv");
        if (!f.exists()) {
            f.createNewFile();
        }
        fh = new FileHandler("herolistTEST.csv");
        list = new ArrayList<>();
        hero = new Superhero("Hero", "hero1", "testkræft", 1, "JA", 100);
        Superhero hero2 = new Superhero("Hero2", "hero2", "testkræft2", 2, "NEJ", 200);
        list.add(hero);
        list.add(hero2);

    }
    @AfterEach
    void teardown() {
        if (f.exists()) {
            f.delete();
        }
    }

    // kunne evt. gøres mere robust med et 'for' loop
    @Test
    void saveList() throws IOException {

        fh.saveList(list);

        BufferedReader brTest = new BufferedReader(new FileReader("herolistTEST.csv"));
        String text = brTest.readLine();
        text += brTest.readLine();

        assertEquals(text, "Hero,hero1,testkræft,1,JA,100Hero2,hero2,testkræft2,2,NEJ,200");
    }

    @Test
    void loadList() throws IOException {

        fh.saveList(list); // ved godt det en dårlig idé, kigger på det senere
        assertEquals(list.toString(), fh.loadList().toString());
    }

}
