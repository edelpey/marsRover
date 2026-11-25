package marsrover;

import java.util.*;

public class Rover {
    private final int width, height;
    private final Set<Position> obstacles;
    private Position position;
    private Direction direction;
    private Position obstacleHit;

    public Rover(int x, int y, Direction direction,
                 int width, int height, Collection<Position> obstacles) {
        this.position = new Position(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.obstacles = new HashSet<>(obstacles);
    }

    public boolean execute(char[] commands) {
        obstacleHit = null;
        for (char c : commands) {
            switch (Character.toLowerCase(c)) {
                case 'f' -> { if (!move(1))  return false; }
                case 'b' -> { if (!move(-1)) return false; }
                case 'l' -> direction = direction.left();
                case 'r' -> direction = direction.right();
                default -> throw new IllegalArgumentException("Commande inconnue : " + c);
            }
        }
        return true;
    }

    private boolean move(int step) {
        int nx = Math.floorMod(position.x() + step * direction.dx(), width);
        int ny = Math.floorMod(position.y() + step * direction.dy(), height);
        Position next = new Position(nx, ny);
        if (obstacles.contains(next)) {
            obstacleHit = next;
            return false;
        }
        position = next;
        return true;
    }

    public Position getPosition()     { return position; }
    public Direction getDirection()   { return direction; }
    public Optional<Position> getObstacleHit() { return Optional.ofNullable(obstacleHit); }
}
