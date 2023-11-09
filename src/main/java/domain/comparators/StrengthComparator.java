package domain.comparators;

import domain.Superhero;
import java.util.Comparator;

public class StrengthComparator implements Comparator<Superhero> {

    @Override
    public int compare(Superhero o1, Superhero o2) {
        int hero1 = o1.getStrength();
        int hero2 = o2.getStrength();
        return Integer.compare(hero1, hero2);
    }
}
