import com.example.oop_project_semester2.Controller.CollisionDetection;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CollisionDetectionTest {

    private ImageView ball;
    private Rectangle racket;

    @Before
    public void initialize() {
        // Create a sample ball and racket for testing
        ball = new ImageView();
        ball.setX(150);  // Set ball position
        ball.setY(150);
        ball.setFitWidth(10);  // Set ball dimensions
        ball.setFitHeight(10);

        racket = new Rectangle();
        racket.setX(150);  // Set racket position
        racket.setY(150);
        racket.setWidth(50);  // Set racket dimensions
        racket.setHeight(10);
    }

    @Test
    public void testCollisionWithRacket() {
        // Test case where the ball collides with the racket
        assertTrue(CollisionDetection.isCollidingWithRacket(ball, racket));
    }

    @Test
    public void testCollisionWithRacketFail() {

        // Test case where the ball does not collide with the racket
        ball.setY(200);  // Move the ball outside the range of the racket
        assertFalse(CollisionDetection.isCollidingWithRacket(ball, racket));
    }
}
