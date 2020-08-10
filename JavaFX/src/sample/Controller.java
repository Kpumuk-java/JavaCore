package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Controller {

    @FXML
    TextField textMessage;


    @FXML
    VBox boxField;

    @FXML
    TextArea chatField;

    public void sendMessage(ActionEvent actionEvent) {
        if (!textMessage.getText().isEmpty()) {
            chatField.appendText(textMessage.getText() + "\n");
            textMessage.clear();
        }
    }
}
