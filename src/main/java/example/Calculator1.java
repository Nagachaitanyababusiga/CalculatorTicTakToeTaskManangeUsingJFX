package example;
/*
 * Write a JavaFX program that works as a simple calculator. Use a grid layout to arrange buttons for the 
 * digits and for the +, -, *, % operations. Add a text field to display the result. 
 * Handle any possible exceptions like divide by zero.
 */

 import javafx.application.Application;
 import javafx.geometry.Insets;
 import javafx.scene.Scene;
 import javafx.scene.control.Button;
 import javafx.scene.control.TextField;
 import javafx.scene.layout.BorderPane;
 import javafx.scene.layout.GridPane;
 import javafx.scene.layout.VBox;
 import javafx.stage.Stage;
 
 public class Calculator1 extends Application{
     Stage calci;
     Button a1,a2,a3,a4,a5,a6,a7,a8,a9,a0,aequals,amul,asub,aadd,adiv,aclear;
     TextField display,comand_display;
 
     public static void main(String[] args) {
         launch(args);
     }
     @Override
     public void start(Stage arg0) throws Exception {
         calci=arg0;
 
         //creating buttons.
         a1=new Button("1");
         a2=new Button("2");
         a3=new Button("3");
         a4=new Button("4");
         a5=new Button("5");
         a6=new Button("6");
         a7=new Button("7");
         a8=new Button("8");
         a9=new Button("9");
         a0=new Button("0");
         aadd=new Button("+");
         asub=new Button("-");
         amul=new Button("*");
         adiv=new Button("%");
         aequals=new Button("=");
         aclear=new Button("clearAll");
 
         //creating labels.
         display =new TextField();
         display.setPromptText("Result:");
         display.setEditable(false);
         comand_display=new TextField();
         comand_display.setPromptText("command display:");
 
         //setting min sizes to buttons.
         a1.setMinSize(40, 40);
         a2.setMinSize(40, 40);
         a3.setMinSize(40, 40);
         a4.setMinSize(40, 40);
         a5.setMinSize(40, 40);
         a6.setMinSize(40, 40);
         a7.setMinSize(40, 40);
         a8.setMinSize(40, 40);
         a9.setMinSize(40, 40);
         a0.setMinSize(40, 40);
         aadd.setMinSize(40, 40);
         asub.setMinSize(40, 40);
         amul.setMinSize(40, 40);
         adiv.setMinSize(40, 40);
         aequals.setMinSize(40, 40);
         display.setMinSize(200, 30);
         aclear.setMinSize(40,40);
         comand_display.setMinSize(200, 30);
 
         //vbox.
         VBox vBox=new VBox(10);
         vBox.getChildren().addAll(display,comand_display);
 
         //Setting a padding on creating a grip pane.
         GridPane gridPane=new GridPane();
         gridPane.setPadding(new Insets(10,10,10,10));
         gridPane.setVgap(20);
         gridPane.setHgap(20);
 
         //Arranging numbers.
         GridPane.setConstraints(display,0,0);
         GridPane.setConstraints(comand_display,0,1);
         GridPane.setConstraints(a9, 0,2);
         GridPane.setConstraints(a8, 1,2);
         GridPane.setConstraints(a7, 2,2);
         GridPane.setConstraints(a6, 0,3);
         GridPane.setConstraints(a5, 1,3);
         GridPane.setConstraints(a4, 2,3);
         GridPane.setConstraints(a3, 0,4);
         GridPane.setConstraints(a2, 1,4);
         GridPane.setConstraints(a1, 2,4);
         GridPane.setConstraints(a0, 1,5);
         GridPane.setConstraints(aadd, 3,2);
         GridPane.setConstraints(asub, 3,3);
         GridPane.setConstraints(amul, 3,4);
         GridPane.setConstraints(adiv, 2,5);
         GridPane.setConstraints(aequals, 3,5);
         GridPane.setConstraints(aclear, 0, 5);
 
         //adding the numbers to the gridpane.
         gridPane.getChildren().addAll(a1,a2,a3,a4,a5,a6,a7,a8,a9,a0,aequals,amul,asub,aadd,adiv,aclear);
         
         //Border pane.
         BorderPane borderPane=new BorderPane();
         borderPane.setPadding(new Insets(10,10,10,10));
         borderPane.setTop(vBox);
         borderPane.setCenter(gridPane);
         
 
         //seting layout in scence.
         Scene calScene=new Scene(borderPane, 300, 400);
         calScene.getStylesheets().add("Styler.css");
         calci.setTitle("Afnan Calci");
         calci.setScene(calScene);
         calci.show();
 
         //for displaying numbers.
         a1.setOnAction(e->addNumber("1"));
         a2.setOnAction(e->addNumber("2"));
         a3.setOnAction(e->addNumber("3"));
         a4.setOnAction(e->addNumber("4"));
         a5.setOnAction(e->addNumber("5"));
         a6.setOnAction(e->addNumber("6"));
         a7.setOnAction(e->addNumber("7"));
         a8.setOnAction(e->addNumber("8"));
         a9.setOnAction(e->addNumber("9"));
         a0.setOnAction(e->addNumber("0"));

         //adding styles
         a1.getStyleClass().add("button-green");
         a2.getStyleClass().add("button-green");
         a3.getStyleClass().add("button-green");
         a4.getStyleClass().add("button-green");
         a5.getStyleClass().add("button-green");
         a6.getStyleClass().add("button-green");
         a7.getStyleClass().add("button-green");
         a8.getStyleClass().add("button-green");
         a9.getStyleClass().add("button-green");
         a0.getStyleClass().add("button-green");
         aclear.getStyleClass().add("button-red");
         adiv.getStyleClass().add("button-yellow");
         amul.getStyleClass().add("button-yellow");
         aadd.getStyleClass().add("button-yellow");
         asub.getStyleClass().add("button-yellow");
 
         //operations.
         aadd.setOnAction(e->addNumber("+"));
         asub.setOnAction(e->addNumber("-"));
         amul.setOnAction(e->addNumber("*"));
         adiv.setOnAction(e->addNumber("/"));
         aclear.setOnAction(e->clearAll());
 
         //equals operation.
         aequals.setOnAction(e->equalsControl());
     }
 
     void addNumber(String s){
         String test=comand_display.getText()+s;
         comand_display.setText(test);
         System.out.println(comand_display.getText());
     }
 
     void equalsControl(){
        boolean bool=true,tbool=false;
        
        String something=comand_display.getText();
        String[] tested={"/*","//","/+","/-","+-","++","+/","+*","--","-+","-/","-*","*/","*-","*+","**"};
        for(String oo:tested){
            System.out.println(something.contains(oo));
            bool=(something.contains(oo));
            if(bool) tbool=true;
        }
        if(comand_display.getText().isEmpty()|comand_display.getText().equals(null)){
            Alertbox.display("Empty Entry", "Enter something.");
        }
        else if(!comand_display.getText().matches(".*\\d.*")){
            display.setText("");
            comand_display.setText("");
            Alertbox.display("Invalid Input", "Invalid Input please re-enter");
        }
        else if(something.endsWith("*")|something.endsWith("/")|something.endsWith("+")|something.endsWith("-")){
            display.setText("");
            comand_display.setText("");
            Alertbox.display("Invalid Input", "Invalid Input please re-enter");
        }
        else if(tbool){
            display.setText("");
            comand_display.setText("");
            Alertbox.display("Invalid Input", "Invalid Input please re-enter");
        }
        else{
            double rum=Tester.calculate(comand_display.getText());
            display.setText(String.valueOf(rum));
        }    
    }

    void clearAll(){
        display.setText("");
        comand_display.setText("");
    }
 }
 