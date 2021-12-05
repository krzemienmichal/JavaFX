module com.paoim.paoim_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.paoim.paoim_javafx to javafx.fxml;
    exports com.paoim.paoim_javafx;
    exports com.paoim.paoim_javafx.api;
    opens com.paoim.paoim_javafx.api to javafx.fxml;
    exports com.paoim.paoim_javafx.tablemodels;
    opens com.paoim.paoim_javafx.tablemodels to javafx.fxml;
}