package gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ErrorHandlerController {

    @FXML
    private TextArea textArea;
    @FXML
    private AnchorPane root;
    @FXML
    private Label lblTitle;

    /**
     * Method for setting text in the error messsage
     * @param text
     */
    public void setText(String text) {
        textArea.setText(text);
    }

    /**
     * Method for setting the text for the title in the error message.
     * @param title
     */
    public void setTitle(String title) {
        lblTitle.setText(title);
    }

    /**
     * On action method for the ok button, that closes the window.
     */
    public void handleBtnOk() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    /**
     * Create a warning dialog with the specified message.
     *
     * @param message The message to show.
     * @return Returns the created WarningController instance.
     */
    public static ErrorHandlerController createWarning(String message) {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ErrorHandlerController.class.getResource("/gui/view/ErrorHandlerView.fxml"));

        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        ErrorHandlerController errorHandlerViewController = fxmlLoader.getController();
        errorHandlerViewController.setText(message);
        stage.initStyle(StageStyle.DECORATED);
        stage.show();
        stage.setAlwaysOnTop(true);
        return errorHandlerViewController;
    }

    /**
     * Create a warning dialog with the specified message.
     *
     * @param message The message to show.
     * @return Returns the created WarningController instance.
     */
    public static ErrorHandlerController createWarning(String title, String message) {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ErrorHandlerController.class.getResource("/gui/view/ErrorHandlerView.fxml"));

        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        ErrorHandlerController errorHandlerViewController = fxmlLoader.getController();
        errorHandlerViewController.setTitle(title);
        errorHandlerViewController.setText(message);
        stage.initStyle(StageStyle.DECORATED);
        stage.show();
        stage.setAlwaysOnTop(true);
        return errorHandlerViewController;
    }
}
