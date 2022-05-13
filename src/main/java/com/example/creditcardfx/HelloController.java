package com.example.creditcardfx;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController implements Initializable{
    @FXML
    private ImageView card_icon;

    @FXML
    private ImageView checkBox;

    @FXML
    private TextField text_num;

    @FXML
    private TextField text_expDate;

    @FXML
    private TextField text_cvv;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Not integer error message
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setTitle("Message Here...");
        errorAlert.setContentText("There should be numbers.");

        // card number error message
        Alert cardAlert = new Alert(Alert.AlertType.INFORMATION);
        cardAlert.setTitle("Message Here...");
        cardAlert.setContentText("This card number cannot be validated");

        // verification text_num and change card_icon && show checkBox
        int maxNum = 19;
        text_num.textProperty().addListener(
                (ObservableValue<? extends String>
                         observable, String oldValue, String newValue) -> {
                    // change logo icon according to start numbers
                    if (newValue.length() == 1 && newValue.matches("4")) {
                        Image image = new Image(Objects.requireNonNull(getClass().getResource("visa.png")).toExternalForm());
                        card_icon.setImage(image);
                        text_cvv.setPromptText("XXX");
                    }else if (newValue.length() == 1 && newValue.matches("5")) {
                        Image image = new Image(Objects.requireNonNull(getClass().getResource("MasterCard.jpeg")).toExternalForm());
                        card_icon.setImage(image);
                        text_cvv.setPromptText("XXX");
                    } else if (newValue.length() == 2 && newValue.matches("34|37")) {
                        Image image = new Image(Objects.requireNonNull(getClass().getResource("Amex.png")).toExternalForm());
                        card_icon.setImage(image);
                        text_cvv.setPromptText("XXXX");
                    } else if (newValue.length() == 2 && newValue.matches("35")) {
                        Image image = new Image(Objects.requireNonNull(getClass().getResource("JCB.jpeg")).toExternalForm());
                        card_icon.setImage(image);
                        text_cvv.setPromptText("XXX");
                    }

                    // set a space every four numbers
                    if ((newValue.length() == 4 && oldValue.length() != 5) ||
                            (newValue.length() == 9 && oldValue.length() != 10) ||
                            (newValue.length() == 14 && oldValue.length() != 15)) {
                        text_num.setText(newValue + " ");
                    }

                    // set card digits length
                    // input should be numbers or space
                    // if the number is correct, there will show tick sign
                    // otherwise there will show an error and cross sign
                    if (newValue.length() >= maxNum) {
                        if(newValue.length() > maxNum)
                            text_num.setText(oldValue);
                        if (newValue.matches("^(34|35|37|4|5)[0-9 ]+")) {
                            Image image = new Image(Objects.requireNonNull(getClass().getResource("tick.png")).toExternalForm());
                            checkBox.setImage(image);
                        } else {
                            Image image = new Image(Objects.requireNonNull(getClass().getResource("cross.png")).toExternalForm());
                            checkBox.setImage(image);
                            cardAlert.showAndWait();
                        }
                    }
                });


        //text_expDate
        int maxDate = 7;
        text_expDate.textProperty().addListener(
                (ObservableValue<? extends String>
                         observable, String oldValue, String newValue) -> {
                    if (newValue.length() == 2 && oldValue.length() != 3) {
                        text_expDate.setText(newValue + "/");
                    }
                    if (newValue.length() > maxDate) {
                        text_expDate.setText(oldValue);
                    }
                });

        //text_cvv
        int maxCVV = 4;
        text_cvv.textProperty().addListener(
                (ObservableValue<? extends String>
                         observable, String oldValue, String newValue) -> {
                    if (!newValue.matches("\\d*")) {
                        text_cvv.setText(oldValue);
                        errorAlert.showAndWait();
                        System.out.println("CVV should be integer.");
                    }
                    if (newValue.length() > maxCVV) {
                        text_cvv.setText(oldValue);
                    }
                });
    }


}