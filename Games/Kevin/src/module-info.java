module GameOfKevin {
    requires kotlin.stdlib;
    requires Engine;
    requires MainMenu;
    requires javafx.graphics;
    requires Actor;
    requires javafx.media;
    requires javafx.fxml;
    requires javafx.controls;
    exports de.hsh.kevin.logic;
    exports de.hsh.kevin.controller;
}
