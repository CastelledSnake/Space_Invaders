module com.example.space_invaders {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.graphics;


    opens com.example.space_invaders to javafx.fxml;
    exports com.example.space_invaders;
}