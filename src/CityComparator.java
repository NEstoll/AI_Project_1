import java.util.Comparator;

public class CityComparator implements Comparator<City> {

    @Override
    public int compare(City o1, City o2) {
        return o1.distance-o2.distance;
    }
}
