package gui.controller;

import be.Case;
import be.Citizen;
import be.User;
import gui.Facade.DataModelFacade;
import gui.controller.Interface.IController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentViewController implements IController, Initializable {


    @FXML
    private TableView<Case> tvCases;
    @FXML
    private TableColumn<Case, Integer> tcCaseID;
    @FXML
    private TableColumn<Case, String> tcCaseName;
    @FXML
    private TableColumn<Case, String> tcCaseDate;
    @FXML
    private TableColumn<Case, String> tcCaseInfo;

    @FXML
    private TableView<Citizen> tvCitizens;
    @FXML
    private TableColumn<Citizen, Integer> tcCitizenID;
    @FXML
    private TableColumn<Citizen, String> tcCitizenFirstName;
    @FXML
    private TableColumn<Citizen, String> tcCitizenLastName;
    @FXML
    private TableColumn<Citizen, String> tcCitizenSSN;
    @FXML
    private TableColumn<Citizen, String> tcCitizenAddress;
    @FXML
    private TableColumn<Citizen, String> tcCitizenSex;

    @FXML
    private ComboBox<Citizen> comboBoxCitizen;
    @FXML
    private TextField txtFieldCitizenID;
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

    private ObservableList<Citizen> allCitizens = FXCollections.observableArrayList();
    private ObservableList<Case> allCasesOnCitizen = FXCollections.observableArrayList();

    private Citizen selectedCitizen;
    private Citizen selectedCitizenOnComboBox;

    DataModelFacade dataModelFacade;

    public StudentViewController() throws IOException {
        this.dataModelFacade = new DataModelFacade();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAnchorPanesVisibility();
        selectedCitizen();
        try {
            initializeTables();
            initializeCitizenComboBox();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeTables() throws Exception {
        //Initialize the citizens table
        tcCitizenID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcCitizenFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcCitizenLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcCitizenSSN.setCellValueFactory(new PropertyValueFactory<>("SSN"));
        tcCitizenAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tcCitizenSex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        try {
            allCitizens = FXCollections.observableList(dataModelFacade.getCitizens());
            tableViewLoadCitizens(allCitizens);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    /**
     * Loads all data in tableview, from the selected current citizen ID.
     */
    public void seeCasesOnCitizen() {
        //Initialize the cases on citizen table
        tcCaseID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcCaseName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcCaseDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tcCaseInfo.setCellValueFactory(new PropertyValueFactory<>("info"));
        try {
            allCasesOnCitizen = FXCollections.observableList(dataModelFacade.getCasesOnCitizen(selectedCitizen.getId()));
            tableViewLoadCasesOnCitizen(allCasesOnCitizen);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initializeCitizenComboBox() throws SQLException {
        //Initialize the citizens in the dropdown menu
        allCitizens = FXCollections.observableList(dataModelFacade.getCitizens());
        tableViewLoadCitizens(allCitizens);
        comboBoxCitizen.setItems(allCitizens);
    }

    @FXML
    private void onActionComboClicked(ActionEvent actionEvent) {
        Citizen comboBox = comboBoxCitizen.getSelectionModel().getSelectedItem();
        txtFieldCitizenID.setText(String.valueOf(comboBox.getId()));

    }

    /**
     * loads the Citizens table view
     * @param allCitizens
     */
    private void tableViewLoadCitizens(ObservableList<Citizen> allCitizens) {
        tvCitizens.setItems(getCitizenData());
    }

    /**
     * Gets the data for citizens
     * @return ObservableList<Citizen>
     */
    private ObservableList<Citizen> getCitizenData() {
        return allCitizens;
    }

    /**
     * loads the casesOnCitizen tableview.
     *
     * @param allCasesOnCitizen
     */
    private void tableViewLoadCasesOnCitizen(ObservableList<Case> allCasesOnCitizen) {
        tvCases.setItems(getCasesOnCitizenData());
    }

    /**
     * Get the data for casesOnCitizens
     *
     * @return ObservableList<Citizen>
     */
    private ObservableList<Case> getCasesOnCitizenData() {
        return allCasesOnCitizen;
    }

    /**
     * Selects a citizen from the citizens TableView
     */
    private void selectedCitizen() {
        this.tvCitizens.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((Citizen) newValue != null) {
                this.selectedCitizen = (Citizen) newValue;
                seeCasesOnCitizen();
            }
        }));
    }

    private void selectedCitizenOnComboBox() {
        this.comboBoxCitizen.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((Citizen) newValue != null) {
                this.selectedCitizenOnComboBox = (Citizen) newValue;
                seeCasesOnCitizen();
            }
        }));
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
    private void btnClickCitizensAndCases() {
        labelTitle.setText("Borgere og Sager");
        labelInfo.setText("Overblik over alle borgere, samt hvilke sager de er tildelt");
        labelInfoNewLine.setText("Klik på en borger for at se hvilke sager der er tildelt den valgte borger");
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizens.setVisible(true);
        anchorPaneFS3.setVisible(false);
        anchorPaneGeneralInformation.setVisible(false);
        anchorPaneHealthConditions.setVisible(false);
        anchorPaneFunctionalAbility.setVisible(false);
        anchorPaneCitizenAssessment.setVisible(false);
    }


    @FXML
    private void btnClickFS3() {
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
    private void btnClickGeneralInformation() {
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
    private void btnClickHealthCondition() {
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
    private void btnClickFunctionalCondition() {
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
    private void btnClickCitizenAssessment() {
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
    private void btnClickLogout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/LoginView.fxml"));
        Scene scene = new Scene(loader.load());
        Stage switcher = (Stage) btnLogOut.getScene().getWindow();
        switcher.setScene(scene);
        switcher.setTitle("Login");
        switcher.show();
    }


}
