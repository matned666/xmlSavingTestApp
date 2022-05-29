package pl.home.artpicture.matned;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class XmlReaderView implements Initializable {

    private ChangeListener<Employee> employeeChangeListener;

    public static XmlReaderView create() {
        XmlReaderView xmlReaderView = new XmlReaderView();
        xmlReaderView.initFXML();
        return xmlReaderView;
    }

    private Scene scene;

    @FXML
    private ImageView imageView;
    @FXML
    private TextField employeeIdLabel;
    @FXML
    private TextField employeeNameTextField;
    @FXML
    private TextField employeeSurnameTextField;
    @FXML
    private TextField employeeProfessionTextField;
    @FXML
    private ListView<Employee> objectsFromXMLListView;
    @FXML
    private ListView<Achievement> achievementsListView;

    private final EmployeesList employeesList;

    private String actuallySelectedId = "";

    public XmlReaderView() {
        employeesList = EmployeesXMLReader.read();
    }

    private void initFXML() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/xmlReaderView.fxml"));
        try {
            HBox viewRoot = loader.load();
            scene = new Scene(viewRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initAchList();
        initObjList();
    }

    private void initAchList() {
        achievementsListView.setCellFactory(list -> new AchievementCell());
    }

    private void initObjList() {
        ObservableList<Employee> items = FXCollections.observableArrayList(employeesList.getEmployees());
        objectsFromXMLListView.setCellFactory(list -> new EmployeeCell());
        employeeChangeListener = (observableValue, oldEmp, newEmp) -> onEmployeeSelection(newEmp);
        objectsFromXMLListView.getSelectionModel().selectedItemProperty().addListener(
                employeeChangeListener);
        objectsFromXMLListView.setItems(items);
        if (!employeesList.getEmployees().isEmpty()) {
            onEmployeeSelection(employeesList.getEmployees().get(0));
        }
    }

    @FXML
    private void onSave(ActionEvent actionEvent) {
        objectsFromXMLListView.getSelectionModel().selectedItemProperty().removeListener(
                employeeChangeListener);
        saveAndRefresh();
        objectsFromXMLListView.getSelectionModel().selectedItemProperty().addListener(
                employeeChangeListener);
    }

    @FXML
    private void onCancel(ActionEvent actionEvent) {
        Employee oldEmp = employeesList.getEmployees().stream()
                .filter(emp -> emp.getId().equals(actuallySelectedId))
                .findFirst()
                .get();
        onEmployeeSelection(oldEmp);
    }

    private void saveAndRefresh() {
        saveEmployee();
        objectsFromXMLListView.setItems(FXCollections.observableArrayList(employeesList.getEmployees()));
    }

    private void saveEmployee() {
        Employee employee = employeesList.getEmployees().stream()
                .filter(e -> e.getId().equals(actuallySelectedId))
                .findFirst()
                .orElse(new Employee());
        employee.setName(employeeNameTextField.getText());
        employee.setSurname(employeeSurnameTextField.getText());
        employee.setProfession(employeeProfessionTextField.getText());
        if (employee.getId() == null || !employeesList.getEmployees().contains(employee)) {
            employee.setId(employeeIdLabel.getText());
            actuallySelectedId = employeeIdLabel.getText();
            employeesList.getEmployees().add(employee);
        }
        EmployeesXMLReader.save(employeesList);
    }

    private void onEmployeeSelection(Employee newEmp) {
        setImage(newEmp.getImg());
        setAchievements(newEmp.getAchievements());
        employeeNameTextField.setText(newEmp.getName());
        employeeSurnameTextField.setText(newEmp.getSurname());
        employeeProfessionTextField.setText(newEmp.getProfession());
        employeeIdLabel.setText(newEmp.getId());
        actuallySelectedId = newEmp.getId();
    }

    private void setImage(String url) {
        Image img;
        try {
            img = new Image("img/" + url);
        } catch (IllegalArgumentException e) {
            img = null;
        }
        imageView.setImage(img);
    }

    private void setAchievements(List<Achievement> achievements) {
        achievementsListView.getItems().clear();
        if (achievements != null) {
            achievementsListView.setItems(FXCollections.observableArrayList(achievements));
        }
    }

    public Scene get() {
        return scene;
    }

    private static class EmployeeCell extends ListCell<Employee> {

        @Override
        public void updateItem(Employee item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) {
                Label lab = new Label(item.getId() + ": " + item.getName() + " " + item.getSurname());
                setGraphic(lab);
            } else {
                setGraphic(null);
            }
        }

    }

    private static class AchievementCell extends ListCell<Achievement> {

        @Override
        public void updateItem(Achievement item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) {
                Label lab = new Label(item.toString());
                Tooltip.install(lab, new Tooltip(item.getDescription()));
                setGraphic(lab);
            } else {
                setGraphic(null);
            }
        }

    }


}
