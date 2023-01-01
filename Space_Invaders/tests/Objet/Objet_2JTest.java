package Objet;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class Objet_2JTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane(); //investigate Group root
        Scene scene = new Scene(root, 10, 10, Color.BLACK);
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @Test
    @DisplayName("init_aliens créé les aliens (3 lignes de 8 unités) au sein du groupe qu'on lui donne en paramètre.")
    public void init_aliens() {
        // Initialisation des groupes alien_1 et alien_2.
        Group aliens_1 = new Group();
        Group aliens_2 = new Group();
        // Initalisation des aliens au sein de chaque groupe.
        Objet_2J.init_aliens(aliens_1, "DOWN");
        Objet_2J.init_aliens(aliens_2, "UP");
        // Variables prises durant l'appel aux fonctions testées
        int a_down = 355;
        int b_down = 35;
        int a_up = 325;
        int b_up = -35;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                // Pour chaque alien du groupe, on isole ses caractéristiques.
                Node alien_du_gr_1 = aliens_1.getChildren().get(3*i + j);
                Node alien_du_gr_2 = aliens_2.getChildren().get(3*i + j);
                // Les coordonnées horizontales sont-elles celles attendues ?
                assertEquals(10d + 50 * i, alien_du_gr_1.getLayoutX(), 0.1);
                assertEquals(10d + 50 * i, alien_du_gr_2.getLayoutX(), 0.1);
                // Les coordonnées verticales sont-elles celles attendues ?
                assertEquals(a_down + b_down * j, alien_du_gr_1.getLayoutY(), 0.1);
                assertEquals(a_up + b_up * j, alien_du_gr_2.getLayoutY(), 0.1);
            }
        }
    }
}