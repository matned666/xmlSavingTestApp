package pl.home.artpicture.matned;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        XmlReaderView xmlReaderView = XmlReaderView.create();
        primaryStage.setScene(xmlReaderView.get());
        primaryStage.show();
    }

}
