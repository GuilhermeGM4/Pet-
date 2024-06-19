module PetPlus {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens org.controller.Cliente;

    opens org.model;

    exports org.controller.Cliente;

    exports org.model;
}