module com.example.creditcardfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.creditcardfx to javafx.fxml;
    exports com.example.creditcardfx;
}