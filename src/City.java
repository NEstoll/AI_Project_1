import java.util.Objects;

public class City {
    String name;
    int steps;
    int distance;
    City parent;

    public City(String name, int steps, int distance) {
        this.name = name;
        this.steps = steps;
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return name.equals(city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String toString() {
        return name;
    }
}
