module com.example.space_invaders {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.space_invaders to javafx.fxml;
    exports com.example.space_invaders;
}