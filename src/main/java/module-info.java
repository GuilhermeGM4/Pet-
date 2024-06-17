module PetPlus {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens org.controller.Cliente;
    exports org.controller.Cliente;
}