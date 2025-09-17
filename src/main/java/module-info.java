module com.scheduler {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.scheduler to javafx.fxml;
    exports com.scheduler;
}
