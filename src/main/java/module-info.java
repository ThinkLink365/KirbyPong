module com.example.oop_project_semester2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.oop_project_semester2 to javafx.fxml;
    exports com.example.oop_project_semester2;
}