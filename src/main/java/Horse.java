import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.util.Objects.isNull;

public class Horse {

    private static final Logger logger = LogManager.getLogger(Horse.class);
    private final String name;
    private final double speed;
    private double distance;

    public Horse(String name, double speed, double distance) {
        if (isNull(name)) {
            // 2022-05-31 17:34:59,483 ERROR Horse: Name is null
            logger.error("Name is null");
            throw new IllegalArgumentException("Name cannot be null.");
        } else if (name.isBlank()) {
            // 2022-05-31 17:36:44,196 ERROR Horse: Name is blank
            logger.error("Name is blank");
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if (speed < 0) {
            // 2022-05-31 17:40:27,267 ERROR Horse: Speed is negative
            logger.error("Speed is negative");
            throw new IllegalArgumentException("Speed cannot be negative.");
        }
        if (distance < 0) {
            // 2022-05-31 17:41:21,938 ERROR Horse: Distance is negative
            logger.error("Distance is negative");
            throw new IllegalArgumentException("Distance cannot be negative.");
        }

        this.name = name;
        this.speed = speed;
        this.distance = distance;
        // 2022-05-31 17:15:25,842 DEBUG Horse: Создание Horse, имя [Лобстер], скорость [2.8]
        logger.debug("Создание Horse, имя [{}], скорость [{}]", name, speed);
    }

    public Horse(String name, double speed) {
        this(name, speed, 0);
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDistance() {
        return distance;
    }

    public void move() {
        distance += speed * getRandomDouble(0.2, 0.9);
    }

    public static double getRandomDouble(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }
}
