    package com.example.oop_project_semester2.Controller;

    import javafx.scene.image.ImageView;
    import javafx.scene.shape.Rectangle;

    public class CollisionDetection {
        // Method to check if the ball is colliding with the racket
        public static boolean isCollidingWithRacket(ImageView ball, Rectangle racket) {
            //Calculate The dimensions of the ball
            double ballLeft = ball.getX();
            double ballRight = ballLeft + ball.getFitWidth();
            double ballTop = ball.getY();
            double ballBottom = ballTop +ball.getFitHeight();

            // Calculate the edges of the racket
            double racketLeft = racket.getBoundsInParent().getMinX();
            double racketRight = racket.getBoundsInParent().getMaxX();
            double racketTop = racket.getBoundsInParent().getMinY();
            double racketBottom = racket.getBoundsInParent().getMaxY();

            // Check for collision with the racket
            return ballRight >= racketLeft &&
                    ballLeft <= racketRight &&
                    ballBottom >= racketTop &&
                    ballTop <= racketBottom;
        }
    }
