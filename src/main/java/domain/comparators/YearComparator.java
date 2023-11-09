package domain.comparators;

import domain.Superhero;
import java.util.Comparator;

public class YearComparator implements Comparator<Superhero> {

    @Override
    public int compare(Superhero o1, Superhero o2) {
        int n1 = o1.getYearCreated();
        int n2 = o2.getYearCreated();
        return Integer.compare(n1, n2);
    }
}
