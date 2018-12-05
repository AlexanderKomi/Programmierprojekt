package de.hsh.daniel;


import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;


public class CardFlip extends Application {

    private ArrayList images = new ArrayList();

    private Image CARD_IMAGE = new Image(
            "de/hsh/daniel/images/1.png"
            // sourced from: http://obviouschild.deviantart.com/art/Vitam-et-Mortem-189267194
    );

    final int W = (int) (CARD_IMAGE.getWidth() / 2);
    final int H = (int) CARD_IMAGE.getHeight() ;

    @Override
    public void start(Stage stage) {
        Node card = createCard();

        stage.setScene(createScene(card));
        stage.show();

        RotateTransition rotator = createRotator(card);
        rotator.play();
    }

    private Scene createScene(Node card) {
        StackPane root = new StackPane();
        root.getChildren().addAll(card, new AmbientLight(Color.WHITE));

        Scene scene = new Scene(root, W + 200, H + 200, true, SceneAntialiasing.BALANCED);
        scene.setFill(Color.MIDNIGHTBLUE.darker().darker().darker().darker());
        scene.setCamera(new PerspectiveCamera());

        return scene;
    }

    private RotateTransition createRotator(Node card) {
        RotateTransition rotator = new RotateTransition(Duration.millis(700), card);
        rotator.setAxis(Rotate.Y_AXIS);
        rotator.setFromAngle(0);
        rotator.setToAngle(180);
        rotator.setInterpolator(Interpolator.LINEAR);
        rotator.setCycleCount(1);

        return rotator;
    }

    private Node createCard() {
        MeshView card = new MeshView(createCardMesh());

        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(CARD_IMAGE);
        card.setMaterial(material);

        return card;
    }

    private TriangleMesh createCardMesh() {
        TriangleMesh mesh = new TriangleMesh();

        mesh.getPoints().addAll(-1 * W / 2, -1 * H / 2, 0, 1 * W / 2, -1 * H / 2, 0, -1 * W / 2, 1 * H / 2, 0, 1 * W / 2, 1 * H / 2, 0);
        mesh.getFaces().addAll(0, 0, 2, 2, 3, 3, 3, 3, 1, 1, 0, 0);
        mesh.getFaces().addAll(0, 4, 3, 7, 2, 6, 3, 7, 0, 4, 1, 5);
        mesh.getTexCoords().addAll(0, 0, 0.5f, 0, 0, 1, 0.5f, 1, 0.5f, 0, 1, 0, 0.5f, 1, 1, 1);

        return mesh;
    }

    public static void main(String[] args) {
        launch();
    }
}