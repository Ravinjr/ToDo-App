package src.main;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new     ToDoListGui().setVisible(true);
            }
        });
        BackgroundFill bgFill=new BackgroundFill(Color.valueOf("2C4E80"),new CornerRadii(1),new Insets(0));
        Background bg=new Background(bgFill);

    }
}