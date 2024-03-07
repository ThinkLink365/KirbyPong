    package com.example.oop_project_semester2;

    import javafx.scene.image.ImageView;
    import javafx.scene.layout.VBox;
    import javafx.scene.shape.Rectangle;

    public class CollisionDetection {
        public static boolean checkCollisionWithLeftRacket(ImageView pongball, VBox leftRacketPane) {
            return pongball.getX() + pongball.getFitWidth() >= leftRacketPane.getBoundsInParent().getMinX()  &&
                    pongball.getX() <= leftRacketPane.getBoundsInParent().getMaxX() &&
                    pongball.getY()  >= leftRacketPane.getTranslateY() &&
                    pongball.getY() + pongball.getFitHeight()<= leftRacketPane.getTranslateY() + ((Rectangle)leftRacketPane.getChildren().get(0)).getHeight();
        }


        public static boolean checkCollisionWithRightRacket(ImageView pongball, VBox rightRacketPane) {
            return  pongball.getX() + pongball.getFitWidth() >= rightRacketPane.getBoundsInParent().getMinX() &&
                    pongball.getX() <= rightRacketPane.getBoundsInParent().getMaxX() &&
                    pongball.getY() >= rightRacketPane.getTranslateY() &&
                    pongball.getY() + pongball.getFitHeight() <= rightRacketPane.getTranslateY() + ((Rectangle)rightRacketPane.getChildren().get(0)).getHeight();
        }
    }
