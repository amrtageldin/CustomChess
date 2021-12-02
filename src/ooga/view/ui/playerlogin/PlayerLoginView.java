package ooga.view.ui.playerlogin;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.Window;
import ooga.util.ResourceRetriever;
import ooga.view.PanelListener;

import java.util.ResourceBundle;

public class PlayerLoginView extends Application implements PlayerLoginInterface {

  PanelListener myPanelListener;
  Stage myStage;

  //General resource file structure
  private static final String FORMATTING_FILE = "PlayerLoginFormatting.css";
  private static final String PLAYER_LOGIN_VIEW_RESOURCES_FILE_PATH = "ooga.view.viewresources.PlayerLoginViewResources";
  private static final ResourceBundle playerLoginViewResources = ResourceBundle.getBundle(
          PLAYER_LOGIN_VIEW_RESOURCES_FILE_PATH);

  public PlayerLoginView() {

  }

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    myStage = primaryStage;
    myStage.setTitle(ResourceRetriever.getWord("login_modal_title_text"));

    // Create the registration form grid pane
    GridPane gridPane = createRegistrationFormPane();
    // Add UI controls to the registration form grid pane
    addUIControls(gridPane);
    // Create a scene with registration form grid pane as the root node
    Scene scene = new Scene(gridPane, 600, 300);

    scene.getStylesheets().add(
            PlayerLoginView.class.getResource(FORMATTING_FILE)
                    .toExternalForm());

    // Set the scene in primary stage
    myStage.setScene(scene);

    myStage.show();
  }

  @Override
  public void setPanelListener(PanelListener panelListener) {
    myPanelListener = panelListener;
  }

  private GridPane createRegistrationFormPane() {
    // Instantiate a new Grid Pane
    GridPane gridPane = new GridPane();

    // Position the pane at the center of the screen, both vertically and horizontally
    gridPane.setAlignment(Pos.CENTER);

    // Set a padding of 20px on each side
    gridPane.setPadding(new Insets(40, 40, 40, 40));

    // Set the horizontal gap between columns
    gridPane.setHgap(getInt("login_grid_pane_hgap"));

    // Set the vertical gap between rows
    gridPane.setVgap(getInt("login_grid_pane_vgap"));

    // Add Column Constraints

    // columnOneConstraints will be applied to all the nodes placed in column one.
    ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
    columnOneConstraints.setHalignment(HPos.RIGHT);

    // columnTwoConstraints will be applied to all the nodes placed in column two.
    ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, Double.MAX_VALUE);
    columnTwoConstrains.setHgrow(Priority.ALWAYS);

    gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

    return gridPane;
  }

  private void addUIControls(GridPane gridPane) {
    // Add Header
    Label headerLabel = new Label(ResourceRetriever.getWord("login_title_text"));
    headerLabel.setId("login-header-label");
    gridPane.add(headerLabel, 0, 0, 2, 1);

    GridPane.setHalignment(headerLabel, HPos.CENTER);
    GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

    // Add Name Label
    Label nameLabel = new Label(ResourceRetriever.getWord("name_label_text"));
    gridPane.add(nameLabel, 0, 1);

    // Add Name Text Field
    TextField nameField = new TextField();
    nameField.setPrefHeight(getInt("input_field_height"));
    gridPane.add(nameField, 1, 1);

    // Add Email Label
    Label emailLabel = new Label(ResourceRetriever.getWord("email_label_text"));
    gridPane.add(emailLabel, 0, 2);

    // Add Email Text Field
    TextField emailField = new TextField();
    emailField.setPrefHeight(getInt("input_field_height"));
    gridPane.add(emailField, 1, 2);

    // Add Password Label
    Label passwordLabel = new Label(ResourceRetriever.getWord("password_label_text"));
    gridPane.add(passwordLabel, 0, 3);

    // Add Password Field
    PasswordField passwordField = new PasswordField();
    passwordField.setPrefHeight(getInt("input_field_height"));
    gridPane.add(passwordField, 1, 3);

    //Add Team Label
    Label teamLabel = new Label(ResourceRetriever.getWord("team_label_text"));
    gridPane.add(teamLabel, 0, 4);

    //Add Team Field
    TextField teamField = new TextField();
    teamField.setPrefHeight(getInt("input_field_height"));
    gridPane.add(teamField, 1, 4);

    // Add Submit Button
    Button submitButton = new Button(ResourceRetriever.getWord("submit_button_text"));
    submitButton.setPrefHeight(getInt("submit_button_height"));
    submitButton.setDefaultButton(true);
    submitButton.setPrefWidth(getInt("submit_button_width"));
    gridPane.add(submitButton, 0, 5, 2, 1);
    GridPane.setHalignment(submitButton, HPos.CENTER);
    GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));

    submitButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (nameField.getText().isEmpty()) {
          showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), ResourceRetriever.getWord("login_form_error"),
                  ResourceRetriever.getWord("name_field_error"));
          return;
        }
        if (emailField.getText().isEmpty()) {
          showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), ResourceRetriever.getWord("login_form_error"),
                  ResourceRetriever.getWord("email_field_error"));
          return;
        }
        if (passwordField.getText().isEmpty()) {
          showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), ResourceRetriever.getWord("login_form_error"),
                  ResourceRetriever.getWord("password_field_error"));
          return;
        }
        if (teamField.getText().isEmpty()) {
          showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), ResourceRetriever.getWord("login_form_error"),
                  ResourceRetriever.getWord("team_field_error"));
        }

        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        int team = Integer.parseInt(teamField.getText());

        showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(),
                ResourceRetriever.getWord("login_form_success"), ResourceRetriever.getWord("login_welcome_message") + name);
        myPanelListener.setNewPlayer(name, email, password, team);
        myPanelListener.closePlayerLogin(myStage);
      }
    });
  }

  private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.initOwner(owner);
    alert.show();
  }

  //return the integer from the resource file based on the provided string
  private int getInt(String key){
    int value;
    try {
      value = Integer.parseInt(playerLoginViewResources.getString(key));
    } catch(Exception e){
      value =-1;
    }
    return value;
  }
}