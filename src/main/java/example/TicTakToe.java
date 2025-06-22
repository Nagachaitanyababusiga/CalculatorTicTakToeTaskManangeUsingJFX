package example;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TicTakToe extends Application{

    Stage mainwindow,Notificationwindow;
    Scene scene1,scene2;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,commonbutton,back;
    Button quit,gotosecondwindow;
    boolean state_of_play1=true,state_of_play2=false;
    String player1="",player2="";
    ComboBox<String> player1choice;
    int count=0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        mainwindow=arg0;
        b1=new Button("    ");
        b2=new Button("    ");
        b3=new Button("    ");
        b4=new Button("    ");
        b5=new Button("    ");
        b6=new Button("    ");
        b7=new Button("    ");
        b8=new Button("    ");
        b9=new Button("    ");
        quit=new Button(" Quit ");
        back=new Button(" Back ");
        

        quit.setOnAction(e->{
            player1="";
            player2="";
            displayhome();
        });

        b1.setPrefSize(100,50);
        b2.setPrefSize(100,50);
        b3.setPrefSize(100,50);
        b4.setPrefSize(100,50);
        b5.setPrefSize(100,50);
        b6.setPrefSize(100,50);
        b7.setPrefSize(100,50);
        b8.setPrefSize(100,50);
        b9.setPrefSize(100,50);
        quit.setPrefSize(100,50);
        back.setPrefSize(100,200);
        back.setStyle("-fx-text-fill : #f7f0f0;-fx-background-radius : 15;-fx-background-color: #fc0303;");

        b1.setOnAction(e->{
            commonbutton=b1;
            playinglogic();
        });
        b2.setOnAction(e->{
            commonbutton=b2;
            playinglogic();
        });
        b3.setOnAction(e->{
            commonbutton=b3;
            playinglogic();
        });
        b4.setOnAction(e->{
            commonbutton=b4;
            playinglogic();
        });
        b5.setOnAction(e->{
            commonbutton=b5;
            playinglogic();
        });
        b6.setOnAction(e->{
            commonbutton=b6;
            playinglogic();
        });
        b7.setOnAction(e->{
            commonbutton=b7;
            playinglogic();
        });
        b8.setOnAction(e->{
            commonbutton=b8;
            playinglogic();
        });
        b9.setOnAction(e->{
            commonbutton=b9;
            playinglogic();
        });
        displayhome();
    }

    void playgame(){
        if(player1.isEmpty()){
            displayhome();
        }
        else{

            GridPane gp=new GridPane();
            GridPane.setConstraints(b1,0,0);
            GridPane.setConstraints(b2,1,0);
            GridPane.setConstraints(b3,2,0);
            GridPane.setConstraints(b4,0,1);
            GridPane.setConstraints(b5,1,1);
            GridPane.setConstraints(b6,2,1);
            GridPane.setConstraints(b7,0,2);
            GridPane.setConstraints(b8,1,2);
            GridPane.setConstraints(b9,2,2);
            GridPane.setConstraints(quit,1,5);
            gp.setVgap(10);
            gp.setHgap(10);
            gp.setPadding(new Insets(40,40,40,40));
            

            gp.getChildren().addAll(b1,b2,b3,b4,b5,b6,b7,b8,b9,quit);
            quit.setStyle("-fx-text-fill: #f7f0f0; -fx-background-radius: 15; -fx-background-color: #12b715;");

            scene2=new Scene(gp,350,350);
            scene2.getStylesheets().add(getClass().getResource("Tictaktoestylesecond.css").toExternalForm());


            mainwindow.setScene(scene2);
            mainwindow.show();
        }
    }

    void displayhome(){
        mainwindow.setTitle("TIC TAK TOE");

        b1.setText("     ");
        b2.setText("    ");
        b3.setText("    ");
        b4.setText("    ");
        b5.setText("    ");
        b6.setText("    ");
        b7.setText("    ");
        b8.setText("    ");
        b9.setText("    ");
        state_of_play1=true;
        state_of_play2=false;
        player1="";
        player2="";
        count=0;

        back.setFont(new Font(20));

        back.setOnAction(e->{
            mainwindow.close();
        });

        Label question =new Label("Choose Player-1 choice:");
        question.setFont(new Font(25));
        player1choice =new ComboBox<>();
        player1choice.setPromptText("player1 choice");
        player1choice.setStyle("-fx-font-size: 25px;");
        player1choice.getItems().addAll("X","O");
        gotosecondwindow=new Button("OK");
        gotosecondwindow.setFont(new Font(20));

        gotosecondwindow.setOnAction(e->{
            if(player1choice.getValue()=="X") {
            player1="X";
            player2="O";
        }else if(player1choice.getValue()=="O") {
            player1="O";
            player2="X";
        }
            playgame();
        });

        GridPane window1_gp=new GridPane();
        GridPane.setConstraints(question,0,0);
        GridPane.setConstraints(player1choice,0,1);
        GridPane.setConstraints(gotosecondwindow,0,2);
        GridPane.setConstraints(back,0,3);
        window1_gp.setVgap(30);
        window1_gp.setHgap(30);
        window1_gp.setPadding(new Insets(40,40,40,40));
        window1_gp.getChildren().addAll(question,player1choice,gotosecondwindow,back);
        scene1=new Scene(window1_gp,370,350);
        scene1.getStylesheets().add(getClass().getResource("Tictaktoestylehome.css").toExternalForm());
        mainwindow.setScene(scene1);
        mainwindow.show();
    }

    void playinglogic(){
        if(count<9){    
            count=count+1;
            if(state_of_play1==true) commonbutton.setText(player1);
            else commonbutton.setText(player2);
            if(count==9){
                System.out.println(commonbutton.getText());
                resetStyle();
                alertBox();
            }
        }
        if(count%2==0){
            state_of_play1=true;
            state_of_play2=false;
            commonbutton.getStyleClass().add("button-x");
        }else{
            state_of_play1=false;
            state_of_play2=true;
            if(count!=9) commonbutton.getStyleClass().add("button-o");
        }
        if(player1status()) {
            System.out.println("player1 won the match");
            resetStyle();
            alertBox();
        }
        if(player2status()){
            System.out.println("player2 won the match");
            resetStyle();
            alertBox();
        }
    }

    boolean player1status(){
        if((b1.getText()==b2.getText())&(b3.getText()==b2.getText())&(b2.getText()==player1)) return true;
        else if((b4.getText()==b5.getText())&(b6.getText()==b5.getText())&(b5.getText()==player1)) return true;
        else if((b7.getText()==b8.getText())&(b9.getText()==b8.getText())&(b8.getText()==player1)) return true;
        else if((b1.getText()==b4.getText())&(b7.getText()==b4.getText())&(b4.getText()==player1)) return true;
        else if((b2.getText()==b5.getText())&(b8.getText()==b5.getText())&(b5.getText()==player1)) return true;
        else if((b3.getText()==b6.getText())&(b9.getText()==b6.getText())&(b6.getText()==player1)) return true;
        else if((b1.getText()==b5.getText())&(b9.getText()==b5.getText())&(b5.getText()==player1)) return true;
        else if((b3.getText()==b5.getText())&(b7.getText()==b5.getText())&(b5.getText()==player1)) return true;
        else return false;
    }
    boolean player2status(){
        if((b1.getText()==b2.getText())&(b3.getText()==b2.getText())&(b2.getText()==player2)) return true;
        else if((b4.getText()==b5.getText())&(b6.getText()==b5.getText())&(b5.getText()==player2)) return true;
        else if((b7.getText()==b8.getText())&(b9.getText()==b8.getText())&(b8.getText()==player2)) return true;
        else if((b1.getText()==b4.getText())&(b7.getText()==b4.getText())&(b4.getText()==player2)) return true;
        else if((b2.getText()==b5.getText())&(b8.getText()==b5.getText())&(b5.getText()==player2)) return true;
        else if((b3.getText()==b6.getText())&(b9.getText()==b6.getText())&(b6.getText()==player2)) return true;
        else if((b1.getText()==b5.getText())&(b9.getText()==b5.getText())&(b5.getText()==player2)) return true;
        else if((b3.getText()==b5.getText())&(b7.getText()==b5.getText())&(b5.getText()==player2)) return true;
        else return false;
    }

    void alertBox(){
        Notificationwindow =new Stage();
        Notificationwindow.initModality(Modality.APPLICATION_MODAL);
        VBox container=new VBox(10);
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(15,15,15,15));
        Label info=new Label();
        info.setFont(new Font(17));
        if(player1status()==true){
            info.setText("player-1 has won the match");
        }
        else if(player2status()==true){
            info.setText("player-2 has won the match");
        }
        else if(player1status()==false&&player2status()==false) info.setText("Draw match");
        Button confirm=new Button("Ok");
        confirm.setFont(new Font(17));
        confirm.setOnAction(e->{
            Notificationwindow.close();
            displayhome();
        });
        container.getChildren().addAll(info,confirm);
        Scene scenealert=new Scene(container,300,150);
        scenealert.getStylesheets().add("Tictaktoelastpage.css");
        Notificationwindow.setTitle("!!!!!!!Winner!!!!!!");
        Notificationwindow.setScene(scenealert);
        Notificationwindow.show();
    }

    void resetStyle(){
        for(int i=1;i<=10;i++){
            Button certainbutton=new Button();
            if(i==1) certainbutton=b1;if(i==2) certainbutton=b2;
            if(i==3) certainbutton=b3;if(i==4) certainbutton=b4;
            if(i==5) certainbutton=b5;if(i==6) certainbutton=b6;
            if(i==7) certainbutton=b7;if(i==8) certainbutton=b8;
            if(i==9) certainbutton=b9;
            if(certainbutton.getStyleClass().contains("button-x")){
                certainbutton.getStyleClass().remove("button-x");
            }
            if(certainbutton.getStyleClass().contains("button-o")){
                certainbutton.getStyleClass().remove("button-o");
            }
        }
    }

}
