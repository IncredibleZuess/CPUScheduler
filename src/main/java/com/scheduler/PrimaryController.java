package com.scheduler;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void makeGrid() throws Exception {
        GridPane grid = new GridPane();
        grid.add(new Label("Name:"),0,0);
    }


}
