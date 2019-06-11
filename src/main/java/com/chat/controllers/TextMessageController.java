package com.chat.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TextMessageController {

    private String userName;
    private String message;
    private Date date;
    private MsgType type;

    private final SimpleStringProperty userProperty = new SimpleStringProperty();
    private final SimpleStringProperty msgProperty = new SimpleStringProperty();
    private final SimpleStringProperty dateProperty = new SimpleStringProperty();

    @FXML
    private HBox msgContainer;
    @FXML
    private VBox elementContainer;
    @FXML
    private TextFlow textFlowUser;
    @FXML
    private Text textUser;
    @FXML
    private TextFlow textFlowMsg;
    @FXML
    private Text textMsg;
    @FXML
    private TextFlow textFlowDate;
    @FXML
    private Text textDate;

    public TextMessageController(String msg, Date date) {
        this.date = date;
        this.message = msg;
        type = MsgType.OUR;
    }

    public TextMessageController(String userName, String msg, Date date) {
        this(msg, date);
        this.userName = userName;
        type = MsgType.SOMEONE;
    }

    @FXML
    public void initialize() {
        assignValueToProperty();
        setCorrectOutlook();
        textUser.textProperty().bindBidirectional(userProperty);
        textMsg.textProperty().bindBidirectional(msgProperty);
        textDate.textProperty().bindBidirectional(dateProperty);
    }


    private void assignValueToProperty() {
        userProperty.setValue(userName);
        msgProperty.setValue(message);
        String time = getTimeFromDate();
        dateProperty.setValue(time);
    }

    private String getTimeFromDate() {
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
        return localDateFormat.format(date);

    }

    private void setCorrectOutlook() {
        if (type == MsgType.OUR) {
            msgContainer.setAlignment(Pos.TOP_RIGHT);
            elementContainer.getChildren().remove(textFlowUser);
            textFlowMsg.setTextAlignment(TextAlignment.RIGHT);
            textFlowMsg.getStyleClass().clear();
            textFlowMsg.getStyleClass().add("chat--text-message--my");
            textFlowDate.setTextAlignment(TextAlignment.RIGHT);
            VBox.setMargin(textFlowDate, new Insets(5, 20, 0, 0));
        }

        if (type == MsgType.SOMEONE) {
            msgContainer.setAlignment(Pos.TOP_LEFT);
            textFlowUser.setTextAlignment(TextAlignment.LEFT);
            VBox.setMargin(textFlowUser, new Insets(0, 0, 5, 5));
            textFlowMsg.setTextAlignment(TextAlignment.LEFT);
            textFlowMsg.getStyleClass().clear();
            textFlowMsg.getStyleClass().add("chat--text-message--their");
            textFlowDate.setTextAlignment(TextAlignment.LEFT);
            VBox.setMargin(textFlowDate, new Insets(5, 0, 0, 20));
        }
    }
}

enum MsgType {
    OUR,
    SOMEONE
}
