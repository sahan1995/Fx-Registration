/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.reg.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.reg.business.FactorBO;
import lk.ijse.reg.business.custom.impl.RegistationBOCustomImpl;
import lk.ijse.reg.business.custom.impl.StudentBOCustomImpl;
import lk.ijse.reg.dto.RegDTO;
import lk.ijse.reg.dto.StudentDTO;
import lk.ijse.reg.view.tblmodel.StudentTbModel;

/**
 * FXML Controller class
 *
 * @author Sahan Rajakaruna
 */
public class StudentRegisterController implements Initializable {

    @FXML
    private JFXTextField txtStudentID;
    @FXML
    private JFXTextField txtStudentName;
    @FXML
    private JFXComboBox<String> cmbCourse;
    @FXML
    private ImageView imgHome;
    @FXML
    private JFXTextField txtCourseAmount;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private TableView<StudentTbModel> tblStudent;
    @FXML
    private Label lblCourseID;

    private boolean allready = false;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXListView<String> lstCourses;

    private StudentBOCustomImpl student;
    private RegistationBOCustomImpl reg;

    public StudentRegisterController() {
        student = (StudentBOCustomImpl) FactorBO.getinstace().getBO(FactorBO.BOtypes.student);
        reg = (RegistationBOCustomImpl) FactorBO.getinstace().getBO(FactorBO.BOtypes.regis);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        allCnames();
        txtStudentName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue == true) {
                    try {
                        StudentDTO findByid = student.findByid(txtStudentID.getText());
                        if (findByid != null) {
                            txtStudentName.setText(findByid.getName());
                            txtAddress.setText(findByid.getAddress());
                            getCourses();
                            allready = true;
                        } else {
                            allready = false;
                        }

                    } catch (Exception e) {
                    }

                }
            }
        });
        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("sid"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        loadAll();

    }

    private void allCnames() {
        try {

            ArrayList<String> allcNames = student.allcNames();
            cmbCourse.setItems(FXCollections.observableArrayList(allcNames));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void btnRegisterOnClick(ActionEvent event) {
        StudentDTO studentDTO = new StudentDTO(txtStudentID.getText(), txtStudentName.getText(), txtAddress.getText());

        if (allready == true || tblStudent.getSelectionModel().getSelectedIndex() >= 0) {
            try {
                if (txtCourseAmount.getText().equals("") || cmbCourse.getSelectionModel().getSelectedIndex() == -1) {

                    student.updateStudent(studentDTO);
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Updated! ");
                    a.show();
                    loadAll();
                    txtStudentID.setText("");
                    txtAddress.setText("");
                    txtCourseAmount.setText("");
                    txtStudentName.setText("");
                } else if (allready == true) {
                    RegDTO regDTO = new RegDTO(txtStudentID.getText(), lblCourseID.getText(), new BigDecimal(txtCourseAmount.getText()));
                    boolean result1 = reg.RegisterCourse(regDTO);
                    if (result1 == true) {

                        Alert a = new Alert(Alert.AlertType.INFORMATION, "Registerd For Course ! ");
                        a.show();
                        txtStudentID.setText("");
                        txtAddress.setText("");
                        txtCourseAmount.setText("");
                        txtStudentName.setText("");
                    }
                }

            } catch (Exception e) {
                System.out.println(e);
            }

        } else if (allready == false) {
            RegDTO regDTO = new RegDTO(txtStudentID.getText(), lblCourseID.getText(), new BigDecimal(txtCourseAmount.getText()));
            try {

                boolean result = reg.RegsiterStudent(studentDTO, regDTO);
                if (result == true) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Registerd ! ");
                    a.show();
                    loadAll();
                    txtStudentID.setText("");
                    txtAddress.setText("");
                    txtCourseAmount.setText("");
                    txtStudentName.setText("");

                }

            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

    @FXML
    private void cmbCourseOnAction(ActionEvent event) {
        try {
            lblCourseID.setText(student.getCourseID(cmbCourse.getValue()));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    private void OnClicked(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView img = (ImageView) event.getSource();
            Parent root = null;
            switch (img.getId()) {
                case "imgHome":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/reg/view/Main.fxml"));
                    break;

            }
            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();
                primaryStage.show();
                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }

        }

    }

    private void loadAll() {
        try {
            ArrayList<StudentDTO> allStudents = student.allStudents();
            ArrayList<StudentTbModel> tbModels = new ArrayList<>();
            for (StudentDTO allStudent : allStudents) {
                tbModels.add(new StudentTbModel(allStudent.getSid(), allStudent.getName(), allStudent.getAddress()));

            }
            tblStudent.setItems(FXCollections.observableArrayList(tbModels));
        } catch (Exception e) {
        }

    }

    @FXML
    private void btnDeleterOnClick(ActionEvent event) {
        try {
            boolean deleteStudent = student.deleteStudent(txtStudentID.getText());
            if (deleteStudent == true) {

                Alert a = new Alert(Alert.AlertType.INFORMATION, "Deleted ! ");
                a.show();
                loadAll();
                txtStudentID.setText("");
                txtAddress.setText("");
                txtCourseAmount.setText("");
                txtStudentName.setText("");
            } else {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Fail ! ");
                a.show();
                loadAll();
            }
        } catch (Exception e) {
        }
        System.out.println("adlm");
    }

    @FXML
    private void tblMouseClicked(MouseEvent event) {
        txtStudentID.setText(tblStudent.getSelectionModel().getSelectedItem().getSid());
        txtStudentName.setText(tblStudent.getSelectionModel().getSelectedItem().getName());
        txtAddress.setText(tblStudent.getSelectionModel().getSelectedItem().getAddress());
        allready = true;
    }

    public void getCourses() {
        try {
            ArrayList<String> courses = student.getCourses(txtStudentID.getText());
            lstCourses.setItems(FXCollections.observableArrayList(courses));

        } catch (Exception e) {
        }

    }
}
