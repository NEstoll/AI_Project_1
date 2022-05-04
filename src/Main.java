import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner reader = new Scanner(new File(args[0]));
        Map<String, Map<String, Integer>> cities = new HashMap<>();
        String orgin = args[1];
        String destination = args[2];
        String next = reader.nextLine();
        while (!next.equals("END OF INPUT")) {
            String[] split = next.split(" ");
            if (!cities.containsKey(split[0])) {
                cities.put(split[0], new HashMap<>());
            }
            cities.get(split[0]).put(split[1], Integer.parseInt(split[2]));
            if (!cities.containsKey(split[1])) {
                cities.put(split[1], new HashMap<>());
            }
            cities.get(split[1]).put(split[0], Integer.parseInt(split[2]));
            next = reader.nextLine();
        }
        City path = findPath2(cities, orgin, destination);
        if (path == null) {
            System.out.println("distance: infinity\n" +
                    "route:\n" +
                    "none");
            return;
        }
        String route = "";
        if (path.parent == null) {
            route = path.name +  " to " + path.name +  ", " + (path.distance) + " km";
        }
        while (path.parent != null) {
            route = path.parent.name + " to " + path.name +  ", " + Math.abs(path.parent.distance-path.distance) + " km\n" + route;
            path = path.parent;
        }
        System.out.println("route:");
        System.out.println(route);
    }

    private static City findPath2(Map<String, Map<String, Integer>> cities, String orgin, String destination) {
        PriorityQueue<City> queue = new PriorityQueue<>(new CityComparator());
        queue.add(new City(orgin, 0, 0));
        int maxSteps = cities.size();
        while (queue.size() != 0) {
           City current = queue.poll();
            if (current.steps > maxSteps) {
                //too deep
                //in undirected graph, there can be no optimal path longer than the number of nodes
                continue;
            }
            if (current.name.equals(destination)) {
                //route found - print path
                System.out.println("distance: " + current.distance + " km");
                return current;
            }
            for (Map.Entry<String, Integer> path : cities.get(current.name).entrySet()) {
                City next = new City(path.getKey(), current.steps + 1, path.getValue() + current.distance);
                next.parent = current;
                if (!queue.contains(next)) {
                    queue.add(next);
                }
            }
        }
        return null;
    }
}
