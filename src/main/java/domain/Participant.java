package domain;

public class Participant {

    private final Name name;
    private Position position;

    public Participant(final Name name) {
        this.name = name;
    }

    public void move(final Position position) {
        this.position = position;
    }

    public boolean hasSameName(final Name name) {
        return this.name.equals(name);
    }

    public Name getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public int getX() {
        return position.getX();
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name=" + name +
                ", position=" + position +
                '}';
    }
}
