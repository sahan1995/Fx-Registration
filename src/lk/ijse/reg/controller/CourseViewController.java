/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.reg.controller;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.reg.business.FactorBO;
import lk.ijse.reg.business.custom.impl.CourseBOCustomImpl;
import lk.ijse.reg.dto.CourseDTO;
import lk.ijse.reg.view.tblmodel.CourseTbModel;

/**
 * FXML Controller class
 *
 * @author Sahan Rajakaruna
 */
public class CourseViewController implements Initializable {

    @FXML
    private JFXTextField txtCourseId;
    @FXML
    private JFXTextField txtCourseTitle;
    @FXML
    private TableView<CourseTbModel> tblCourse;
    @FXML
    private JFXTextField txtDuration;

    private boolean allready = false;
    @FXML
    private AnchorPane root;
    @FXML
    private ImageView imgHome;

    
   private CourseBOCustomImpl course = (CourseBOCustomImpl) FactorBO.getinstace().getBO(FactorBO.BOtypes.course);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblCourse.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("cid"));
        tblCourse.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCourse.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("duration"));
        allCourses();
        txtCourseTitle.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue == true) {
                    try {
                        CourseDTO findbyID = course.findbyID(txtCourseId.getText());
                        if (findbyID != null) {
                            txtCourseTitle.setText(findbyID.getName());
                            txtDuration.setText(findbyID.getDuration());
                            allready = true;
                        } else {

                            allready = false;
                        }

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }
            }
        });

    }

    @FXML
    private void txtCourseIdOnkeyPressed(KeyEvent event) {

    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        if (allready == true || tblCourse.getSelectionModel().getSelectedIndex() >= 0) {
            updateCourse();
            allCourses();
        } else {
            addCourse();
            allCourses();
        }

    }

    private void addCourse() {

        try {

            boolean result = course.addCourse(new CourseDTO(txtCourseId.getText(), txtCourseTitle.getText(), txtDuration.getText()));
            if (result == true) {

                Alert a = new Alert(Alert.AlertType.INFORMATION, "Course Added ! ");
                a.show();
                allCourses();
                txtCourseId.setText("");
                txtCourseTitle.setText("");
                txtDuration.setText("");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void allCourses() {

        try {
            ArrayList<CourseDTO> allCourses = course.allCourses();
            ArrayList<CourseTbModel> tblCourseA = new ArrayList<>();
            for (CourseDTO allCourse : allCourses) {
                CourseTbModel courseTbModel = new CourseTbModel(allCourse.getCid(), allCourse.getName(), allCourse.getDuration());
                tblCourseA.add(courseTbModel);
            }

            tblCourse.setItems(FXCollections.observableArrayList(tblCourseA));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void updateCourse() {
        try {

            boolean UpdateCourse = course.UpdateCourse(new CourseDTO(txtCourseId.getText(), txtCourseTitle.getText(), txtDuration.getText()));
            if (UpdateCourse == true) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Course Updated ! ");
                a.show();
                allCourses();
                txtCourseId.setText("");
                txtCourseTitle.setText("");
                txtDuration.setText("");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        try {
            boolean DeleteCourse = course.DeleteCourse(txtCourseId.getText());
            if (DeleteCourse == true) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Course Deleted ! ");
                a.show();
                allCourses();
                txtCourseId.setText("");
                txtCourseTitle.setText("");
                txtDuration.setText("");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void tblMouseClick(MouseEvent event) {

        txtCourseId.setText(tblCourse.getSelectionModel().getSelectedItem().getCid());

        txtCourseTitle.setText(tblCourse.getSelectionModel().getSelectedItem().getName());

        txtDuration.setText(tblCourse.getSelectionModel().getSelectedItem().getDuration());

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
}
