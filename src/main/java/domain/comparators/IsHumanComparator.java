package domain.comparators;

import domain.Superhero;

import java.util.Comparator;

public class IsHumanComparator implements Comparator<Superhero> {

    @Override
    public int compare(Superhero hero1, Superhero hero2) {
        return hero1.isHuman().compareTo(hero2.isHuman());
    }
}
