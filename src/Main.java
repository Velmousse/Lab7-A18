import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class Main extends Application {
    private Stage mainStage = new Stage();
    private BorderPane bp;
    private HBox hbox;

    private BufferedReader entree;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        stage = mainStage;

        hbox = new HBox();
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
        png.setOnAction(event -> export("png"));

        MenuItem gif = new MenuItem("GIF");
        gif.setOnAction(event -> export("gif"));

        importer.getItems().addAll(lignes, regions, barres);
        exporter.getItems().addAll(png, gif);

        return new MenuBar(importer, exporter);
    }

    private void setGraphique(int typeNumber, HBox box) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Veuillez sélectionner un fichier");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers DAT", "*.dat"));
        File fichier = fc.showOpenDialog(mainStage);

        List<String> content = null;

        try { entree = new BufferedReader(new FileReader(fichier)); }
        catch (FileNotFoundException e) { System.out.println("Impossible de trouver le fichier"); }

        try { content = Files.readAllLines(fichier.toPath()); }
        catch (IOException e) { System.out.println("Impossible de charger le fichier"); }

        String[] x = content.get(0).split(",");
        String[] y = content.get(1).split(",");

        XYChart.Series series = new XYChart.Series();
        series.setName("Données");

        XYChart chart = null;

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Mois");
        yAxis.setLabel("Température");

        for (int i = 0; i < x.length; i++)
            series.getData().add(new XYChart.Data<>(x[i], Float.parseFloat(y[i])));

        switch (typeNumber) {
            case 1: chart = new LineChart<>(xAxis, yAxis); break;
            case 2: chart = new AreaChart<>(xAxis, yAxis); break;
            case 3: chart = new BarChart<>(xAxis, yAxis);
        }

        chart.setTitle("Températures moyennes");
        chart.getData().add(series);
        box.getChildren().clear();
        box.getChildren().add(chart);
    }

    private void export(String format) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Enregistrer sous");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier image", "*." + format));
        File fichier = fc.showSaveDialog(mainStage);

        WritableImage image = hbox.snapshot(new SnapshotParameters(), null);

        try { ImageIO.write(SwingFXUtils.fromFXImage(image, null), format, fichier); }
        catch (IOException e) { e.printStackTrace(); }
        }
    }
