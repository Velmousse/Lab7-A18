import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main extends Application {
    private Stage mainStage = new Stage();
    private BorderPane bp;

    private BufferedReader entree;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        stage = mainStage;
        bp = new BorderPane();
        bp.setTop(setMenu());

        mainStage.setTitle("Graphiques");
        mainStage.setWidth(510);
        mainStage.setHeight(550);
        mainStage.setScene(new Scene(bp));
        mainStage.show();
    }

    private MenuBar setMenu() {
        Menu importer = new Menu("Importer");
        Menu exporter = new Menu("Exporter");

        MenuItem lignes = new MenuItem("Lignes");
        lignes.setOnAction(event -> setGraphique(1));

        MenuItem regions = new MenuItem("Régions");
        regions.setOnAction(event -> setGraphique(2));

        MenuItem barres = new MenuItem("Barres");
        barres.setOnAction(event -> setGraphique(3));

        MenuItem png = new MenuItem("PNG");
        png.setOnAction(event -> export(1));

        MenuItem tiff = new MenuItem("JPEG");
        tiff.setOnAction(event -> export(2));

        importer.getItems().addAll(lignes, regions, barres);
        exporter.getItems().addAll(png, tiff);

        return new MenuBar(importer, exporter);
    }

    private void setGraphique(int typeNumber) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Veuillez sélectionner un fichier");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers DAT", "*.dat"));
        File fichier = fc.showOpenDialog(mainStage);

        try { entree = new BufferedReader(new FileReader(fichier)); }
        catch (FileNotFoundException e) { }

        switch (typeNumber) {
            case 1:

                break;

            case 2:

                break;

            case 3:
        }
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
