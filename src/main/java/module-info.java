module Breakout {
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.media;
    requires javafx.swing;
    exports breakout;
    exports breakout.SoundPlayer;
    exports breakout.Sprites;
}