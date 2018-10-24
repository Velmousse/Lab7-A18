import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Main extends Application {
    private Stage mainStage = new Stage();
    private BorderPane bp;

    private BufferedReader entree;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        stage = mainStage;

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);

        bp = new BorderPane();
        bp.setTop(setMenu(hbox));
        bp.setCenter(hbox);

        mainStage.setTitle("Graphiques");
        mainStage.setWidth(510);
        mainStage.setHeight(550);
        mainStage.setScene(new Scene(bp));
        mainStage.show();
    }

    private MenuBar setMenu(HBox box) {
        Menu importer = new Menu("Importer");
        Menu exporter = new Menu("Exporter");

        MenuItem lignes = new MenuItem("Lignes");
        lignes.setOnAction(event -> setGraphique(1, box));

        MenuItem regions = new MenuItem("Régions");
        regions.setOnAction(event -> setGraphique(2, box));

        MenuItem barres = new MenuItem("Barres");
        barres.setOnAction(event -> setGraphique(3, box));

        MenuItem png = new MenuItem("PNG");
        png.setOnAction(event -> export(1));

        MenuItem tiff = new MenuItem("JPEG");
        tiff.setOnAction(event -> export(2));

        importer.getItems().addAll(lignes, regions, barres);
        exporter.getItems().addAll(png, tiff);

        return new MenuBar(importer, exporter);
    }

    private void setGraphique(int typeNumber, HBox box) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Veuillez sélectionner un fichier");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers DAT", "*.dat"));
        File fichier = fc.showOpenDialog(mainStage);

        try { entree = new BufferedReader(new FileReader(fichier)); }
        catch (FileNotFoundException e) { System.out.println("Impossible de trouver le fichier"); }

        ArrayList<String> content = null;

        XYChart chart = null;

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Mois");
        yAxis.setLabel("Température");

        switch (typeNumber) {
            case 1:
                chart = new LineChart<String, Number>(xAxis, yAxis);
                break;

            case 2:

                break;

            case 3:
        }

        box.getChildren().add(chart);
    }

    private void export(int type) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Enregistrer sous");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier image", "*.*"));
        fc.showSaveDialog(mainStage);

        switch (type) {
            case 1:


                break;

            case 2:

        }

    }

    private void readData() {

    }
}
