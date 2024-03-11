module com.example.oop_project_semester2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.oop_project_semester2 to javafx.fxml;
    exports com.example.oop_project_semester2.Model;
    opens com.example.oop_project_semester2.Model to javafx.fxml;
    exports com.example.oop_project_semester2.Controller;
    opens com.example.oop_project_semester2.Controller to javafx.fxml;
    exports com.example.oop_project_semester2.View;
    opens com.example.oop_project_semester2.View to javafx.fxml;
}