module GameOfDennis {
    requires MainMenu;
    requires Engine;
    requires Logger;
    requires javafx.graphics;
    requires kotlin.stdlib;
    requires jlayer;
    requires Actor;
    requires gson;
    requires lwbd;
    requires javafx.fxml;
    requires javafx.controls;
    exports de.hsh.dennis.model.NpcLogic;
    exports de.hsh.dennis;
    exports de.hsh.dennis.model;
}
