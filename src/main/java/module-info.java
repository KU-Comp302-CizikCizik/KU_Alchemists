module com.KUAlchemists{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    opens com.KUAlchemists.ui to javafx.fxml;
    exports com.KUAlchemists.ui;
    exports com.KUAlchemists.ui.controllers;
    opens com.KUAlchemists.ui.controllers;
    exports com.KUAlchemists;
}
