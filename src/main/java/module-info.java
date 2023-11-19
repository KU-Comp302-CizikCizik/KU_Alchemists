module com.KUAlchemists{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop; // add this line for java.awt
    requires java.sql;     // add this line for java.sql

    opens com.KUAlchemists.app to javafx.fxml;

    exports com.KUAlchemists.app;
}
