module java_project.space_invaders {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires com.almasb.fxgl.all;

    opens java_project.space_invaders to javafx.fxml;
    exports java_project.space_invaders;
}