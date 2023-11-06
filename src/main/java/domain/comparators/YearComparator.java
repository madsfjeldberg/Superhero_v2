package domain.comparators;

import domain.Superhero;

import java.util.Comparator;

public class YearComparator implements Comparator<Superhero> {

    @Override
    public int compare(Superhero o1, Superhero o2) {
        int n1 = o1.getYearCreated();
        int n2 = o2.getYearCreated();
        if (n1 < n2) {
            return -1;
        }
        else if (n1 > n2) {
            return 1;
        }
        else return 0;
    }
}
