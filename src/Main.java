import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        GridPane gridPane = new GridPane();

        BorderPane borderPaneOne = new BorderPane();
        gridPane.add(borderPaneOne,0,0);

        Label userNameLabelOne = new Label();
        borderPaneOne.setTop(userNameLabelOne);

        ListView listView1 = new ListView();
        borderPaneOne.setCenter(listView1);
        Label label1 = new Label("USER_1");
        borderPaneOne.setBottom(label1);

        BorderPane borderPaneTwo = new BorderPane();
        gridPane.add(borderPaneTwo,1,0);

        Label userNameLabelTwo = new Label();
        borderPaneTwo.setTop(userNameLabelTwo);

        ListView listView2 = new ListView();
        borderPaneTwo.setCenter(listView2);
        Label label2 = new Label("USER_2");
        borderPaneTwo.setBottom(label2);

        BorderPane borderPaneThree = new BorderPane();
        gridPane.add(borderPaneThree,2,0);

        Label userNameLabelThree = new Label();
        borderPaneThree.setTop(userNameLabelThree);

        ListView listView3 = new ListView();
        borderPaneThree.setCenter(listView3);
        Label label3 = new Label("USER_3");
        borderPaneThree.setBottom(label3);

        BorderPane borderPaneFour = new BorderPane();
        gridPane.add(borderPaneFour,3,0);

        Label userNameLabelFour = new Label();
        borderPaneFour.setTop(userNameLabelFour);

        ListView listView4 = new ListView();
        borderPaneFour.setCenter(listView4);
        Label label4 = new Label("USER_4");
        borderPaneFour.setBottom(label4);

        BorderPane borderPaneFive = new BorderPane();
        gridPane.add(borderPaneFive,4,0);

        Label userNameLabelFive = new Label();
        borderPaneFive.setTop(userNameLabelFive);

        ListView listView5 = new ListView();
        borderPaneFive.setCenter(listView5);
        Label label5 = new Label("USER_5");
        borderPaneFive.setBottom(label5);

        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
        //Koniec przygotowywania wyglądu

        //Żaby można było zamykać "x" w prawym górnym rogu
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

        new Thread(() -> {
            SeverSocet serverSocket1 = new SeverSocet();
            serverSocket1.run();
            SeverSocet serverSocket2 = new SeverSocet();
            serverSocket2.run();
            SeverSocet serverSocket3 = new SeverSocet();
            serverSocket3.run();
            SeverSocet serverSocket4 = new SeverSocet();
            serverSocket4.run();
            SeverSocet serverSocket5 = new SeverSocet();
            serverSocket5.run();
            while(true) {
                if (serverSocket1.isConnected())
                    Platform.runLater(() -> label1.setText(serverSocket1.getUserName()));
            }
        }).start();
    }
}
