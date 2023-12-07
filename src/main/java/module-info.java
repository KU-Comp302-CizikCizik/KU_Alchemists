module com.KUAlchemists{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    exports com.KUAlchemists.ui;
    exports com.KUAlchemists.ui.controllers;
    opens com.KUAlchemists.ui.controllers;
    exports com.KUAlchemists;
    opens com.KUAlchemists.ui;

}
