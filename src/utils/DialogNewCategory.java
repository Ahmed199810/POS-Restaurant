package utils;

import database.DBHelper;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.Printer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Department;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.apache.http.util.TextUtils;
import sample.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

public class DialogNewCategory {

    private static int ADD_CATEGORY = 0;
    private static int EDIT_CATEGORY = 1;
    private static int DELETE_CATEGORY = 2;

    public static void AlertMSG(String msg, Stage stage, int type, Department department){
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);

        TextField textField = new TextField();
        Image img = new Image("/res/images/break.png");
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setOpacity(0.5);

        ComboBox<String> comboPrinters = new ComboBox();
        if (department != null){
            comboPrinters.getItems().add(department.getPrinter());
            comboPrinters.getSelectionModel().select(0);
        }
        comboPrinters.getItems().addAll(getPrinters());
        comboPrinters.getSelectionModel().select(0);

        final File[] selectedImage = {null};


        Button btnSave = new Button("حفظ");
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(textField.getText().toString());
                String path = "";
                if (selectedImage[0] != null){
                    path = "C:\\xampp\\htdocs\\cafeApi\\images\\categories\\" + selectedImage[0].getName();
                }
                File targetPath = new File(path);
                if (!validateInputs(textField)){
                    return;
                }
                RequestBody formBody = new FormBody.Builder()
                        .add("name", textField.getText().toString())
                        .add("printer", comboPrinters.getSelectionModel().getSelectedItem())
                        .add("img_url", targetPath.toURI().toString())
                        .build();
                String url = APIRequests.INSERT_CATEGORY;
                if (type == EDIT_CATEGORY){
                    url = APIRequests.UPDATE_CATEGORY;
                    formBody = new FormBody.Builder()
                            .add("id", department.getId() + "")
                            .add("name", textField.getText().toString())
                            .add("printer", comboPrinters.getSelectionModel().getSelectedItem())
                            .add("img_url", targetPath.toURI().toString())
                            .build();
                    if (selectedImage[0] == null){
                        formBody = new FormBody.Builder()
                                .add("id", department.getId() + "")
                                .add("name", textField.getText().toString())
                                .add("printer", comboPrinters.getSelectionModel().getSelectedItem())
                                .add("img_url", "")
                                .build();
                        System.out.println("NO SEL");
                    }
                }

                try {
                    if(DBHelper.postData(url, formBody)){
                        System.out.println("INSERTED CATEGORY SUCCESS");
                        if (type == EDIT_CATEGORY){
                            if (selectedImage[0] == null){
                                try {
                                    FXMLLoader fxmlLoader = new FXMLLoader(DialogNewCategory.class.getResource("../res/fxml/Departments.fxml"));
                                    Parent root = (Parent) fxmlLoader.load();
                                    Main.setContent(root);
                                } catch (Exception e) {

                                }
                                dialogStage.hide();
                                utils.AlertMSG("تم التعديل بنجاح");
                            }else {
                                Files.copy(selectedImage[0].toPath(), targetPath.toPath(), StandardCopyOption.REPLACE_EXISTING);
                                try {
                                    FXMLLoader fxmlLoader = new FXMLLoader(DialogNewCategory.class.getResource("../res/fxml/Departments.fxml"));
                                    Parent root = (Parent) fxmlLoader.load();
                                    Main.setContent(root);
                                } catch (Exception e) {

                                }
                                dialogStage.hide();
                                utils.AlertMSG("تم التعديل بنجاح");
                            }
                        }else {
                            if (selectedImage[0] != null){
                                Files.copy(selectedImage[0].toPath(), targetPath.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            }
                        }
                        dialogStage.hide();
                        // refresh UI
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../res/fxml/Departments.fxml"));
                            Parent root = (Parent) fxmlLoader.load();
                            Main.setContent(root);

                        } catch (Exception e) {

                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button btnImg = new Button("اختر صوره");
        btnImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                selectedImage[0] = fileChooser.showOpenDialog(stage);
                if (selectedImage[0] != null){
                    imageView.setImage(new Image(selectedImage[0].toURI().toString()));
                }
                if (type == EDIT_CATEGORY){
                    imageView.setId("id"+department.getId());
                }else {
                    imageView.setId("idx");
                }
                imageView.setOpacity(1);
            }
        });

        Button btnDel = new Button("حذف القسم");
        btnDel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    deleteDepartment(department, dialogStage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        HBox hBox = new HBox();
        if (type == EDIT_CATEGORY){
            btnSave.setText("حفظ التعديل");
            hBox.getChildren().addAll(btnImg, btnSave, btnDel);
            imageView.setImage(new Image(department.getImg_url()));
            textField.setText(department.getName());
        }else {
            hBox.getChildren().addAll(btnImg, btnSave);
        }
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(imageView, new Text(msg), textField, new Text("Printer"), comboPrinters, hBox);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));
        dialogStage.setScene(new Scene(vbox));
        dialogStage.show();

    }

    private static List<String> getPrinters() {
        ObservableSet<Printer> printers = Printer.getAllPrinters();
        return printers.stream().map(Printer::getName).collect(Collectors.toList());
    }

    private static void deleteDepartment(Department department, Stage dialogStage) throws IOException {
        String url = APIRequests.DELETE_ITEM;
        RequestBody formBody = new FormBody.Builder()
                .add("id", department.getId() + "")
                .add("table", "categories")
                .build();
        if(DBHelper.postData(url, formBody)){
            utils.AlertMSG("تم الحذف بنجاح");
            // refresh UI
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(DialogNewCategory.class.getResource("../res/fxml/Departments.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Main.setContent(root);
            } catch (Exception e) {

            }
            dialogStage.hide();

        }else{
            utils.AlertMSG("لم يتم الحذف ");
        }
    }

    private static boolean validateInputs(TextField textField) {
        if (TextUtils.isEmpty(textField.getText().toString())){
            textField.setPromptText("يجب ادخال الاسم");
            textField.setStyle("-fx-padding: 1,1,1,1;-fx-border-color: #f7000c;-fx-border-width: 1;");
            return false;
        }else {
            textField.setStyle(null);
        }
        return true;
    }

}