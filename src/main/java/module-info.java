module org.example.todoapp1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.todoapp1 to javafx.fxml;
    exports org.example.todoapp1;
}