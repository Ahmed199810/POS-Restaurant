package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class utils {

    public static void AlertMSG(String msg){
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Button btn = new Button("OK");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialogStage.hide();
            }
        });
        VBox vbox = new VBox(new Text(msg), btn);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));
        dialogStage.setScene(new Scene(vbox));
        dialogStage.show();


    }

    public static void PrinterDialog(Node node, String msg, String printer_name){
        Printer savedPrinter = null;
        ObservableSet<Printer> printers = Printer.getAllPrinters();
        LinkedList<Printer> printerList = new LinkedList<>();
        List<String> strList = new ArrayList();
        PrinterJob job = PrinterJob.createPrinterJob();
        ListView list = new ListView();
        for(Printer printer : printers){
            strList.add(printer.getName());
            printerList.add(printer);
            if (printer_name.equals(printer.getName())){
                savedPrinter = printer;
                break;
            }
        }
        ObservableList<String> items = FXCollections.observableArrayList (strList);
        list.setItems(items);
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);

        list.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Printer printer = printerList.get(list.getSelectionModel().getSelectedIndex());
                PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
                job.setPrinter(printer);
                if (job != null) {
                    boolean success = job.printPage(pageLayout, node);
                    if (success) {
                        job.endJob();
                    }
                }

                dialogStage.hide();
                AlertMSG(msg);
            }
        });

        VBox vbox = new VBox(list);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));
        dialogStage.setTitle("طباعه تقرير");
        dialogStage.setScene(new Scene(vbox));


        if (printer_name.equals("default") || savedPrinter == null){
            dialogStage.setTitle("طباعه");
            dialogStage.show();
        }else {
            PageLayout pageLayout = savedPrinter.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
            job.setPrinter(savedPrinter);
            if (job != null) {
                boolean success = job.printPage(pageLayout, node);
                if (success) {
                    job.endJob();
                }
            }

            AlertMSG(msg);
        }


    }


    public static Node ReportForm(String title, Image logo, String current_date, String start_date, String end_date, String body,
                                  String nums){

        VBox vBox = new VBox();
        vBox.setSpacing(10);

        ImageView ImgLogo = new ImageView(logo);
        ImgLogo.setFitWidth(100);
        ImgLogo.setFitHeight(100);

        Label ReportTitle = new Label("   *****                    " + title + "                    *****   ");
        ReportTitle.setFont(new Font(25));

        Label date1 = new Label("بتاريخ");
        date1.setFont(new Font("Arabic Typesetting", 20));
        Label ReportDate = new Label(current_date);
        Label date2 = new Label("تم طباعه هذا التقرير");
        date2.setFont(new Font("Arabic Typesetting", 20));

        Label extra1 = new Label("ابتداءا من تاريخ : ");
        extra1.setFont(new Font("Arabic Typesetting", 20));
        Label ReportStart = new Label(start_date);

        Label extra2 = new Label("الي تاريخ : ");
        extra2.setFont(new Font("Arabic Typesetting", 20));
        Label ReportEnd = new Label(end_date);

        Label extra3 = new Label("تم بيع : ");
        extra3.setFont(new Font("Arabic Typesetting", 20));
        Label bills_nums = new Label(nums);
        bills_nums.setFont(new Font("Arabic Typesetting", 20));

        Label extra4 = new Label("فاتوره باجمالي مبلغ : ");
        extra4.setFont(new Font("Arabic Typesetting", 20));
        Label price = new Label(body);
        price.setFont(new Font("Arabic Typesetting", 20));


        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(
                extra3,
                bills_nums,
                extra4,
                price
        );
        hBox.setAlignment(Pos.CENTER);
        hBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        vBox.getChildren().addAll(
                ReportTitle,
                ImgLogo,
                date1,
                ReportDate,
                date2,
                extra1,
                ReportStart,
                extra2,
                ReportEnd,
                hBox
        );
        vBox.setPadding(new Insets(20));
        vBox.setAlignment(Pos.CENTER);

        return  vBox;
    }

}