package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    NetworkManager nm;

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane mainChoice = new GridPane();
        Button host = new Button("Host");
        Button connect = new Button("Connect");
        mainChoice.add(host,0,0);
        mainChoice.add(connect,0,1);
        nm = new NetworkManager(new NetworkDelegate() {
            @Override
            public void onConnect() {
                System.out.println("connected");
                greet();
            }

            @Override
            public void onData(byte[] data) {
                System.out.println(new String(data));
            }
        });
        
        primaryStage.setTitle("Commander");
        primaryStage.setScene(new Scene(mainChoice, 300, 275));
        primaryStage.show();
    }

    private void greet(){
        String s = "hello";
        nm.write(s.getBytes());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
