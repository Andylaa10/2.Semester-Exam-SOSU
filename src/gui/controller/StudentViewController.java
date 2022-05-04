package gui.controller;

import be.User;
import gui.controller.Interface.IController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentViewController implements IController, Initializable {


    @FXML
    private ComboBox comboBoxCitizen;
    @FXML
    private Button btnGeneralInformation;
    @FXML
    private Button btnHealthCondition;
    @FXML
    private Button btnFunctionalCondition;
    @FXML
    private Button btnCitizenAssessment;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnCitizens;
    @FXML
    private Button btnFS3;
    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnBack;
    @FXML
    private Label labelTitle;
    @FXML
    private Label labelInfo;
    @FXML
    private Label labelInfoNewLine;
    @FXML
    private AnchorPane topPane;
    @FXML
    private AnchorPane anchorPaneStudent;
    @FXML
    private AnchorPane anchorPaneFS3;
    @FXML
    private AnchorPane anchorPaneGeneralInformation;
    @FXML
    private AnchorPane anchorPaneHealthConditions;
    @FXML
    private AnchorPane anchorPaneCitizens;
    @FXML
    private AnchorPane anchorPaneCitizenAssessment;
    @FXML
    private AnchorPane anchorPaneOBS;
    @FXML
    private AnchorPane anchorPaneFunctionalAbility;


    public StudentViewController() {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAnchorPanesVisibility();
    }

    @Override
    public void setUser(User user) throws SQLException, IOException {
        labelTitle.setText("Elev");
        labelInfo.setText("Du er nu logget ind som Elev: " + user.getFirstName() + " " + user.getLastName());
        labelInfoNewLine.setText("");
    }

    private void setAnchorPanesVisibility() {
        labelInfoNewLine.setText("");
        anchorPaneStudent.setVisible(true);
        anchorPaneCitizens.setVisible(false);
        anchorPaneFS3.setVisible(false);
        anchorPaneOBS.setVisible(false);
        anchorPaneGeneralInformation.setVisible(false);
        anchorPaneHealthConditions.setVisible(false);
        anchorPaneFunctionalAbility.setVisible(false);
        anchorPaneCitizenAssessment.setVisible(false);
    }

    @FXML
    private void btnClickHome(ActionEvent actionEvent) {
        labelTitle.setText("Elev");
        labelInfo.setText("Du er nu logget ind som Elev");
        labelInfoNewLine.setText("");
        anchorPaneStudent.setVisible(true);
        anchorPaneCitizens.setVisible(false);
        anchorPaneFS3.setVisible(false);
        anchorPaneGeneralInformation.setVisible(false);
        anchorPaneHealthConditions.setVisible(false);
        anchorPaneFunctionalAbility.setVisible(false);
        anchorPaneCitizenAssessment.setVisible(false);
    }

    @FXML
    private void btnClickCitizensAndCases(ActionEvent actionEvent) {
        labelTitle.setText("Borgere og Sager");
        labelInfo.setText("Overblik over alle oprettede sager, samt alle oprettede borger der har fået sager tildelt");
        labelInfoNewLine.setText("Klik på en sag for at se hvilke borgere der er tildelt den valgte sag");
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizens.setVisible(true);
        anchorPaneFS3.setVisible(false);
        anchorPaneGeneralInformation.setVisible(false);
        anchorPaneHealthConditions.setVisible(false);
        anchorPaneFunctionalAbility.setVisible(false);
        anchorPaneCitizenAssessment.setVisible(false);
    }


    @FXML
    private void btnClickFS3(ActionEvent actionEvent) {
        labelTitle.setText("Rapporter og øv Fælles Sprog 3");
        labelInfo.setText("I dette vindue kan du vælge at se en borgers generelle info, helbredstilstande og funktionstilstand");
        labelInfoNewLine.setText("Med taskbaren under denne tekst, kan du vælge tre forskellige vinduer, hvor du kan rapportere og øve Fælles Sprog 3");
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizens.setVisible(false);
        anchorPaneFS3.setVisible(true);
        anchorPaneOBS.setVisible(true);
        anchorPaneGeneralInformation.setVisible(false);
        anchorPaneHealthConditions.setVisible(false);
        anchorPaneFunctionalAbility.setVisible(false);
        anchorPaneCitizenAssessment.setVisible(false);
    }

    @FXML
    private void btnClickGeneralInformation(ActionEvent actionEvent) {
        labelTitle.setText("Generelle Informationer for borgeren");
        labelInfo.setText("Overblik over borgerens generelle informationer, hvor det er muligt at redigere og gemme eventuelle ændringer");
        labelInfoNewLine.setText("Her kan du se alle informationer for borgeren");
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizens.setVisible(false);
        anchorPaneFS3.setVisible(true);
        anchorPaneOBS.setVisible(false);
        anchorPaneGeneralInformation.setVisible(true);
        anchorPaneHealthConditions.setVisible(false);
        anchorPaneFunctionalAbility.setVisible(false);
        anchorPaneCitizenAssessment.setVisible(false);

    }

    @FXML
    private void btnClickHealthCondition(ActionEvent actionEvent) {
        labelTitle.setText("Helbreds Tilstande for borgeren");
        labelInfo.setText("Overblik over borgerens helbredstilstande, hvor det er muligt at ændre og gemme eventuelle observationer og ændringer");
        labelInfoNewLine.setText("Her er det muligt at rapportere borgerens helbredstilstande og øve FS3. Der skal tages stilling til alle tilstande for at gemme.");
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizens.setVisible(false);
        anchorPaneFS3.setVisible(true);
        anchorPaneOBS.setVisible(false);
        anchorPaneGeneralInformation.setVisible(false);
        anchorPaneHealthConditions.setVisible(true);
        anchorPaneFunctionalAbility.setVisible(false);
        anchorPaneCitizenAssessment.setVisible(false);
    }

    @FXML
    private void btnClickFunctionalCondition(ActionEvent actionEvent) {
        labelTitle.setText("Funktionstilstanden for borgeren");
        labelInfo.setText("Overblik over borgerens funktionstilstand, hvor der skal tages stilling til den nuværende funktionstilstand");
        labelInfoNewLine.setText("Her er det muligt at se et skema over definitionen på funktionstilstandende, men der skal også tages stilling til den forventede funktionstilstand");
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizens.setVisible(false);
        anchorPaneFS3.setVisible(true);
        anchorPaneOBS.setVisible(false);
        anchorPaneGeneralInformation.setVisible(false);
        anchorPaneHealthConditions.setVisible(false);
        anchorPaneFunctionalAbility.setVisible(true);
        anchorPaneCitizenAssessment.setVisible(false);
    }

    @FXML
    private void btnClickCitizenAssessment(ActionEvent actionEvent) {
        labelTitle.setText("Borgerens egen vurdering af funktionstilstand");
        labelInfo.setText("Her er det muligt at rapportere på borgerens egen vurdering af sin funktionstilstand.");
        labelInfoNewLine.setText("Der skal tages stilling til udførelse, betydningen af udførelse og borgerens ønsker og mål");
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizens.setVisible(false);
        anchorPaneFS3.setVisible(true);
        anchorPaneOBS.setVisible(false);
        anchorPaneGeneralInformation.setVisible(false);
        anchorPaneHealthConditions.setVisible(false);
        anchorPaneFunctionalAbility.setVisible(false);
        anchorPaneCitizenAssessment.setVisible(true);
    }

    @FXML
    private void btnClickBack(ActionEvent actionEvent) {
        btnClickHome(actionEvent);
    }

    @FXML
    private void btnClickLogout(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/LoginView.fxml"));
        Scene scene = new Scene(loader.load());
        Stage switcher = (Stage) btnLogOut.getScene().getWindow();
        switcher.setScene(scene);
        switcher.setTitle("Login");
        switcher.show();
    }
}
