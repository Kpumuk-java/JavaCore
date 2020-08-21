package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Controller {

    private User user;

    public Controller() {
        this.user = new User();
        new Thread(() -> {
            user.openConnection();
        }).start();
        user.setController(Controller.this);
    }

    @FXML
    TextField textMessage;

    @FXML
    VBox boxField;

    @FXML
    TextArea chatField;

    public void sendMessage(ActionEvent actionEvent) {

        if (!textMessage.getText().isEmpty()) {
            String str = textMessage.getText();
            if (str.startsWith("/auth")) {
                user.sendMsg(str);
                textMessage.clear();
            } else if (!user.isAuthorized()){
                chatField.appendText("Incorrected command (/auth login pass)\n");
                textMessage.clear();
            } else {
                user.sendMsg(str);
                textMessage.clear();
            }
        }
    }
}
