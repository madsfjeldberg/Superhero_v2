package domain;

public class Superhero implements Comparable<Superhero> {

    // attributes
    private String name;
    private String realName;
    private String superPower;
    private int yearCreated;
    private String isHuman;
    private int strength;

    // konstruktør
    public Superhero(String name, String realName, String superPower, int yearCreated, String isHuman, int strength) {
        this.name = name;
        this.realName = realName;
        this.superPower = superPower;
        this.yearCreated = yearCreated;
        this.isHuman = isHuman;
        this.strength = strength;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setSuperPower(String superPower) {
        this.superPower = superPower;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    public void setHuman(String human) {
        isHuman = human;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public String getRealName() {
        return realName;
    }

    public String getSuperPower() {
        return superPower;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public String isHuman() {
        return isHuman;
    }

    public int getStrength() {
        return strength;
    }

    public int compareTo(Superhero hero2) {
        return name.compareTo(hero2.getName());
    }

    @Override
    public String toString() {
        return "Superheltenavn: " + superName + "\n" +"Rigtigt navn: " + realName + "\n" +
                "Superkræft" + superPower + "\n" + "Årstal: " + yearCreated + "\n" + "Er menneske: "
                + isHuman + "\n" + "Styrke: " + strength + "\n";
    }
}



