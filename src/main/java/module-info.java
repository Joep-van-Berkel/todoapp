module org.example.todoapp {
    requires javafx.controls;
    requires org.controlsfx.controls;
    requires java.sql;
    requires mysql.connector.j;

    opens org.example.todoapp.model to javafx.base;

    exports org.example.todoapp;
}