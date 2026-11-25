package marsrover;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RoverTest {

    @Test
    void exempleDuSujet() {
        Rover r = new Rover(0, 0, Direction.N, 100, 100, List.of());
        r.execute("ffrff".toCharArray());
        assertEquals(new Position(2, 2), r.getPosition());
        assertEquals(Direction.E, r.getDirection());
    }

    @Test
    void wrappingBord() {
        Rover r = new Rover(0, 0, Direction.N, 100, 100, List.of());
        r.execute("b".toCharArray());
        assertEquals(new Position(0, 99), r.getPosition());
    }

    @Test
    void obstacleStoppeLaSequence() {
        Rover r = new Rover(0, 0, Direction.N, 100, 100, List.of(new Position(0, 2)));
        boolean ok = r.execute("fffrf".toCharArray());
        assertFalse(ok);
        assertEquals(new Position(0, 1), r.getPosition());
        assertEquals(Direction.N, r.getDirection());
        assertEquals(new Position(0, 2), r.getObstacleHit().orElseThrow());
    }
}
