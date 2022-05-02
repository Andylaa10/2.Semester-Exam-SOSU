package gui.controller;

import be.SuperAdmin;
import be.User;
import be.enums.UserType;
import gui.Facade.DataModelFacade;
import gui.controller.Interface.IController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class LoginViewController {

    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtFieldUsername;
    @FXML
    private PasswordField pField;

    private DataModelFacade facade;

    public LoginViewController() throws IOException {
        facade = new DataModelFacade();
    }

    public void Login() throws IOException, SQLException {
        String username = txtFieldUsername.getText();
        String password = pField.getText();
        User user = facade.userLogin(username, password);
        SuperAdmin superAdmin = facade.superAdminLogin(username, password);
        if (user != null && user.getUsertype() == UserType.STUDENT) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/StudentView.fxml"));
            Scene scene = new Scene(loader.load());
            Stage switcher = (Stage) btnLogin.getScene().getWindow();
            switcher.setScene(scene);
            IController controller = loader.getController();
            controller.setUser(user);
            switcher.setTitle("Student");
            switcher.show();
            switcher.centerOnScreen();
        } else if (user != null && user.getUsertype() == UserType.TEACHER){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/TeacherView.fxml"));
            Scene scene = new Scene(loader.load());
            Stage switcher = (Stage) btnLogin.getScene().getWindow();
            switcher.setScene(scene);
            IController controller = loader.getController();
            controller.setUser(user);
            switcher.setTitle("Teacher");
            switcher.show();
            switcher.centerOnScreen();
        } else if (user != null && user.getUsertype() == UserType.ADMINISTRATOR) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/AdminView.fxml"));
            Scene scene = new Scene(loader.load());
            Stage switcher = (Stage) btnLogin.getScene().getWindow();
            switcher.setScene(scene);
            IController controller = loader.getController();
            controller.setUser(user);
            switcher.setTitle("Admin");
            switcher.show();
            switcher.centerOnScreen();
        } else if (superAdmin != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/SuperAdminView.fxml"));
            Scene scene = new Scene(loader.load());
            Stage switcher = (Stage) btnLogin.getScene().getWindow();
            switcher.setScene(scene);
            switcher.setTitle("Super Admin");
            switcher.show();
            switcher.centerOnScreen();
        } else {
            //TODO ADD PROPER ERROR HANDLING
            System.out.println("Something probably went wrong lol");
        }
    }

    @FXML
    private void onActionLoginWithEnter() throws SQLException, IOException {
        Login();
    }
}
