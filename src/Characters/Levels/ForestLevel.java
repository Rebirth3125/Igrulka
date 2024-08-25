package Characters.Levels;

import Characters.Enemies.Goblin;
import Characters.Enemies.Wolf;

import java.util.Arrays;
import java.util.List;

public class ForestLevel {

    public static List<Location> createForestLocations() {
        return Arrays.asList(
                new Location("Поляна", List.of(new Wolf(10))),
                new Location("Глубокий лес", Arrays.asList(new Wolf(10), new Wolf(10))),
                new Location("Лесная тропа", List.of(new Goblin(10))),
                new Location("Опушка леса", Arrays.asList(new Wolf(10), new Goblin(10))),
                new Location("Пещера", List.of(new Goblin(20))) // Этот участок будет иметь переход на Кладбище
        );
    }
}
