module PetPlus {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires json.simple;
    requires java.desktop;

    opens org.controller;
    opens org.controller.Cliente;
    opens org.controller.Pet;
    opens org.controller.Menu;
    opens org.controller.Funcionario;
    opens org.model;
    opens org.Utils;

    exports org.controller;
    exports org.controller.Cliente;
    exports org.controller.Pet;
    exports org.controller.Menu;

    exports org.controller.Funcionario;

    exports org.model;
    exports org.Utils;
}