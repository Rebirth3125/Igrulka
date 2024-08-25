package Characters.Levels;

import java.util.List;

public class Level {
    private final String name;
    private final List<Location> locations;

    public Level(String name, List<Location> locations) {
        this.name = name;
        this.locations = locations;
    }

    public String getName() {
        return name;
    }

    public List<Location> getLocations() {
        return locations;
    }
}
