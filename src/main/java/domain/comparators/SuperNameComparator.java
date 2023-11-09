package domain.comparators;

import domain.Superhero;

import java.util.Comparator;

public class SuperNameComparator implements Comparator<Superhero> {

        @Override
        public int compare (Superhero hero1, Superhero hero2){
            return hero1.getSuperName().compareTo(hero2.getSuperName());
        }
}
