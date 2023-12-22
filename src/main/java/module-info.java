module com.example.courtreservetionjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.courtreservetionjavafx to javafx.fxml;
    exports com.example.courtreservetionjavafx;
}