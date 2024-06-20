module PetPlus {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens org.controller;
    opens org.controller.Cliente;

    opens org.model;
    opens org.Utils;

    exports org.controller.Cliente;

    exports org.model;
    exports org.controller;
    exports org.Utils;
}