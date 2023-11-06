package domain.comparators;

import domain.Superhero;
import java.util.Comparator;

public class StrengthComparator implements Comparator<Superhero> {

    @Override
    public int compare(Superhero o1, Superhero o2) {
        int hero1 = o1.getStrength();
        int hero2 = o2.getStrength();
        if (hero1 < hero2) {
            return -1;
        } else if (hero1 > hero2) {
            return 1;
        } else return 0;
    }
}
