package gui.controller;

import be.School;
import be.SuperAdmin;
import be.User;
import be.enums.UserType;
import gui.Facade.DataModelFacade;
import gui.controller.Interface.IController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {

    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtFieldUsername;
    @FXML
    private PasswordField pField;
    @FXML
    private ComboBox<School> comboBoxSchool;
    @FXML
    private TextField txtFieldSchoolId;

    private final DataModelFacade facade = DataModelFacade.getInstance();

    private ObservableList<School> allSchools = FXCollections.observableArrayList();

    private School selectedSchoolOnComboBox;


    public LoginViewController() throws IOException, SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedSchoolOnComboBox();
        initializeCombo();
    }

    /**
     * Initializing the combobox
     */
    private void initializeCombo() {
        try {
            allSchools = FXCollections.observableArrayList(facade.getSchools());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        comboBoxSchool.setItems(allSchools);
    }

    @FXML
    private void Login() throws Exception {
        String username = txtFieldUsername.getText();
        String password = pField.getText();
        SuperAdmin superAdmin = facade.getHashedPasswordSuperAdmin(username, password);
        //SuperAdmin superAdmin = facade.superAdminLogin(username, password);
        if (superAdmin != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/SuperAdminView.fxml"));
            Scene scene = new Scene(loader.load());
            Stage switcher = (Stage) btnLogin.getScene().getWindow();
            switcher.setScene(scene);
            switcher.setTitle("Super Admin");
            switcher.show();
            switcher.centerOnScreen();
        } else {
            if (comboBoxSchool.getSelectionModel().getSelectedItem() == null) {
                ErrorHandlerController.createWarning("Vælg skole", "For at logge ind husk at vælge skole");
            } else {
                int school = Integer.parseInt(txtFieldSchoolId.getText());
                User user = facade.getHashedPassword(username, password, school);
                //User user = facade.userLogin(username, password, school);
                if (user != null && user.getUsertype() == UserType.STUDENT && user.getSchoolId() == school) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/StudentView.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage switcher = (Stage) btnLogin.getScene().getWindow();
                    switcher.setScene(scene);
                    IController controller = loader.getController();
                    controller.setUser(user);
                    switcher.setTitle("Student");
                    switcher.show();
                    switcher.centerOnScreen();
                } else if (user != null && user.getUsertype() == UserType.TEACHER && user.getSchoolId() == school) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/TeacherView.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage switcher = (Stage) btnLogin.getScene().getWindow();
                    switcher.setScene(scene);
                    IController controller = loader.getController();
                    controller.setUser(user);
                    switcher.setTitle("Teacher");
                    switcher.show();
                    switcher.centerOnScreen();
                } else if (user != null && user.getUsertype() == UserType.ADMINISTRATOR && user.getSchoolId() == school) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/AdminView.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage switcher = (Stage) btnLogin.getScene().getWindow();
                    switcher.setScene(scene);
                    IController controller = loader.getController();
                    controller.setUser(user);
                    switcher.setTitle("Admin");
                    switcher.show();
                    switcher.centerOnScreen();
                } else {
                    ErrorHandlerController.createWarning("Fejl i login", "Forkert brugernavn, adgangskode eller skole");
                }
            }
        }
    }

    /**
     * When press enter, we can log in if the credentials is valid
     */
    @FXML
    private void onActionLoginWithEnter() throws Exception {
        Login();
    }

    @FXML
    private void onEnterPressedLogin(KeyEvent keyEvent) throws Exception {
        if (keyEvent.getCode().getName().equals("Enter")) {
            Login();
        }
    }

    /**
     * Select a school on combobox
     */
    private void selectedSchoolOnComboBox() {
        this.comboBoxSchool.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.selectedSchoolOnComboBox = newValue;
                comboBoxSchool.getSelectionModel().getSelectedItem();
                txtFieldSchoolId.setText(String.valueOf(selectedSchoolOnComboBox.getId()));
                txtFieldSchoolId.setDisable(false);
            }
        });
    }
}
