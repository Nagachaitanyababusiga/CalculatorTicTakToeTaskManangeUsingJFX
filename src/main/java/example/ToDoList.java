package example;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

public class ToDoList extends Application{
    Label welcomemessage;
    Stage mainwindow;
    Button edit,view,delete,create;
    ListView<String> listView;
    Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        mainwindow=arg0;
        mainwindow.setTitle("ToDoList");

        //all buttons;
        edit=new Button("edit");
        create=new Button("create");
        delete=new Button("delete");
        view=new Button("view");

        //adding colours to buttons
        edit.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        create.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        delete.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        view.setStyle("-fx-background-color: orange; -fx-text-fill: white;");

        //welcome message;
        Label label=new Label("Welcome to Taskmanger (thenewboston ytchannel has helped me alot)");
        label.setStyle("-fx-text-fill: blue;");

        //topmenu
        VBox vBox=new VBox(5);
        vBox.setPadding(new Insets(5,5,5,5));
        vBox.getChildren().addAll(label);

        //bottommenu
        HBox hBox=new HBox(5);
        hBox.setPadding(new Insets(5,5,5,5));
        hBox.getChildren().addAll(view,edit,create,delete);
        
        //centre menu
        listView=new ListView<>();
        TaskList.showTask();
        for (String st : TaskList.taskList) {
            listView.getItems().add(st);
        }
        listView.setStyle("-fx-control-inner-background: black;");
 
        //complete layout to set all menu to scene
        BorderPane borderPane=new BorderPane();
        borderPane.setTop(vBox);
        borderPane.setBottom(hBox);
        borderPane.setCenter(listView);
        borderPane.setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));

        //setting the layout to scene
        scene=new Scene(borderPane, 300,300);
        mainwindow.setScene(scene);
        mainwindow.show();

        //operations need to be performed by different buttons
        view.setOnAction(e->{
            if((listView.getSelectionModel().getSelectedItem()!=null &&  !listView.getSelectionModel().getSelectedItem().isEmpty())) ViewTask();
            else Alertbox.display("Selection Error", "Please selecte a Task to view!!");
        });
        edit.setOnAction(e->{
            if((listView.getSelectionModel().getSelectedItem()!=null &&  !listView.getSelectionModel().getSelectedItem().isEmpty())) editTask();
            else Alertbox.display("Selection Error", "Please selecte a Task to edit!!");
        });
        create.setOnAction(e->addTask());
        delete.setOnAction(e->{
            if((listView.getSelectionModel().getSelectedItem()!=null &&  !listView.getSelectionModel().getSelectedItem().isEmpty())) deleteTask();
            else Alertbox.display("Selection Error", "Please selecte a Task to delete!!");
        });
    }

    void addTask(){
       Stage addWindow=new Stage();
       Scene addScene;
       Button save=new Button("save");
       save.setStyle("-fx-background-color: green; -fx-text-fill: white;"); 

       //for taking taskname as input
       Label taskLabel=new Label("Enter Task Name :");
       taskLabel.setStyle("-fx-text-fill: blue;");
       TextField taskinput=new TextField();
       taskinput.setStyle("-fx-text-fill: green;");
       taskinput.setPromptText("Task: ");

       //for taking taskdescription as input
       Label descripLabel=new Label("Enter Task Description :");
       descripLabel.setStyle("-fx-text-fill: blue;");
       TextField descripinput=new TextField();
       descripinput.setStyle("-fx-text-fill: green;");
       descripinput.setPrefHeight(200);
       descripinput.setPrefWidth(300);
       descripinput.setPromptText("Description: ");

       //for taking the taskDueDate as input
       Label DueDateLabel=new Label("Enter Task DueDate(dd/MM/yyyy) :");
       DueDateLabel.setStyle("-fx-text-fill: blue;");
       TextField duedateinput=new TextField();
       duedateinput.setStyle("-fx-text-fill: green;");
       duedateinput.setPromptText("DueDate(dd/MM/yyyy): ");

       //choice box to mark if a task is completed or not
       ComboBox<String> Statusvalue=new ComboBox<>();
       Statusvalue.setPromptText("Status");
       Statusvalue.setStyle("-fx-text-fill: blue; -fx-background-color: lightblue;");
       Statusvalue.getItems().addAll("Completed","Not Completed");

       addWindow.initModality(Modality.APPLICATION_MODAL);
       addWindow.setTitle("Task Adder");

       // setting structure or design of layout using gridPane
       GridPane layout=new GridPane();
       layout.setPadding(new Insets(10, 10, 10, 10));
       layout.setVgap(10);
       layout.setHgap(10);
       GridPane.setConstraints(taskLabel,0,0);
       GridPane.setConstraints(taskinput,0,1);
       GridPane.setConstraints(descripLabel,0,3);
       GridPane.setConstraints(descripinput,0,4);
       GridPane.setConstraints(DueDateLabel,0,6);
       GridPane.setConstraints(duedateinput,0,7);
       GridPane.setConstraints(Statusvalue,0,9);
       GridPane.setConstraints(save,3,11);
       layout.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));

       layout.getChildren().addAll(taskLabel,taskinput,descripLabel,descripinput,duedateinput,DueDateLabel,Statusvalue,save);
       addScene=new Scene(layout, 400,350);
       addWindow.setScene(addScene);
       addWindow.show();

       save.setOnAction(e -> {
        String dueDateString = duedateinput.getText();
        if (taskinput.getText().isEmpty()) {
            Alertbox.display("Error", "Taskname is empty. Please enter Taskname.");
            return; // Stop further execution of the code
        }
        if (descripinput.getText().isEmpty()) {
        Alertbox.display("Error", "description is empty. Please enter description.");
        return; // Stop further execution of the code
    }
    if (dueDateString.isEmpty()) {
        Alertbox.display("Error", "Due date is empty. Please enter a valid date.");
        return; // Stop further execution of the code
    }
    if (Statusvalue.getValue() == null || Statusvalue.getValue().isEmpty()) {//why ????
        Alertbox.display("Error", "Task Status is empty. Please select completion Status.");
        return; // Stop further execution of the code
    }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dueDate = dateFormat.parse(dueDateString);
            System.out.println(dueDate);
            TaskAdder addtasker = new TaskAdder(taskinput.getText(), descripinput.getText(), duedateinput.getText(), Statusvalue.getValue());
        addtasker.addTask();
        Alertbox.display("Saved File", "File saved successfully!!");
        listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
        listView.getItems().add(taskinput.getText());
        addWindow.close();
        
        } catch (ParseException err) {
            duedateinput.setText("");
            Alertbox.display("Error in date format ", "Please enter any valid date!!");
        }  
    });
    }

    void editTask(){
        Stage editWindow=new Stage();
        Scene editScene;
        Button save=new Button("save");
        save.setStyle("-fx-background-color: green; -fx-text-fill: white;"); 
 
        //for taking taskname as input
        Label taskLabel=new Label("Enter Task Name :");
        taskLabel.setStyle("-fx-text-fill: blue;");
        TextField taskinput=new TextField();
        taskinput.setStyle("-fx-text-fill: green;");
        taskinput.setPromptText("Task Name:");
 
        //for taking taskdescription as input
        Label descripLabel=new Label("Enter Task Description :");
        descripLabel.setStyle("-fx-text-fill: blue;");
        TextField descripinput=new TextField();
        descripinput.setPrefHeight(200);
        descripinput.setPrefWidth(300);
        descripinput.setStyle("-fx-text-fill: green;");
        descripinput.setPromptText("Description: ");
 
        //for taking the taskDueDate as input
        Label DueDateLabel=new Label("Enter Task DueDate(dd/MM/yyyy) :");
        DueDateLabel.setStyle("-fx-text-fill: blue;");
        TextField duedateinput=new TextField();
        duedateinput.setStyle("-fx-text-fill: green;");
        duedateinput.setPromptText("Due Date(dd/MM/yyyy): ");

        
 
        //choice box to mark if a task is completed or not
        ComboBox<String> Statusvalue=new ComboBox<>();
        Statusvalue.setPromptText("Status");
        Statusvalue.setStyle("-fx-text-fill: blue; -fx-background-color: lightblue;");
        Statusvalue.getItems().addAll("Completed","Not Completed");


        TaskViewer tkv=new TaskViewer();
        tkv.viewTask(listView.getSelectionModel().getSelectedItem());
        taskinput.setText(tkv.tName);
        descripinput.setText(tkv.tDescription);
        duedateinput.setText(tkv.tDueDate);
        Statusvalue.setValue(tkv.tStatus);
 
        editWindow.initModality(Modality.APPLICATION_MODAL);
        editWindow.setTitle("Task Editor");


        // setting structure or design of layout using gridPane
        GridPane layout=new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(10);
        layout.setHgap(10);
        GridPane.setConstraints(taskLabel,0,0);
        GridPane.setConstraints(taskinput,0,1);
        GridPane.setConstraints(descripLabel,0,3);
        GridPane.setConstraints(descripinput,0,4);
        GridPane.setConstraints(DueDateLabel,0,6);
        GridPane.setConstraints(duedateinput,0,7);
        GridPane.setConstraints(Statusvalue,0,9);
        GridPane.setConstraints(save,3,11);
        layout.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
 
        layout.getChildren().addAll(taskLabel,taskinput,descripLabel,descripinput,duedateinput,DueDateLabel,Statusvalue,save);
        editScene=new Scene(layout, 400,350);
        editWindow.setScene(editScene);
        editWindow.show();
 
        save.setOnAction(e -> {
            String dueDateString = duedateinput.getText();
        
            if (taskinput.getText().isEmpty()) {
                Alertbox.display("Error", "Taskname is empty. Please enter Taskname.");
                return; // Stop further execution of the code
            }
            if (descripinput.getText().isEmpty()) {
                Alertbox.display("Error", "description is empty. Please enter description.");
                return; // Stop further execution of the code
            }
            if (dueDateString.isEmpty()) {
                Alertbox.display("Error", "Due date is empty. Please enter a valid date.");
                return; // Stop further execution of the code
            }
            if (Statusvalue.getValue() == null || Statusvalue.getValue().isEmpty()) {//why ????
                Alertbox.display("Error", "Task Status is empty. Please select completion Status.");
                return; // Stop further execution of the code
            }
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date dueDate = dateFormat.parse(dueDateString);
                System.out.println(dueDate);
        
                TaskEditor tasker = new TaskEditor(taskinput.getText(), descripinput.getText(), duedateinput.getText(), Statusvalue.getValue());
                tasker.editTask(listView.getSelectionModel().getSelectedItem());
                Alertbox.display("Saved File", "File saved successfully!!");
                listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
                listView.getItems().add(taskinput.getText());
                editWindow.close();
            } catch (ParseException err) {
                duedateinput.setText("");
                Alertbox.display("Error in date format ", "Please enter any valid date!!");
            } 
     });
     }
     void ViewTask(){
        Stage viewWindow=new Stage();
        Scene addScene;
        Button close=new Button("close");
        close.setStyle("-fx-background-color: red; -fx-text-fill: white;"); 
 
        //for taking taskname as input
        Label taskLabel=new Label("Enter Task Name :");
        taskLabel.setStyle("-fx-text-fill: blue;");
        TextField taskinput=new TextField();
        taskinput.setEditable(false);
        taskinput.setStyle("-fx-text-fill: green;");
 
        //for taking taskdescription as input
        Label descripLabel=new Label("Enter Task Description :");
        descripLabel.setStyle("-fx-text-fill: blue;");
        TextField descripinput=new TextField();
        descripinput.setEditable(false);
        descripinput.setPrefHeight(200);
        descripinput.setPrefWidth(300);
        descripinput.setStyle("-fx-text-fill: green;");
 
        //for taking the taskDueDate as input
        Label DueDateLabel=new Label("Enter Task DueDate(dd/MM/yyyy) :");
        DueDateLabel.setStyle("-fx-text-fill: blue;");
        TextField duedateinput=new TextField();
        duedateinput.setEditable(false);
        duedateinput.setStyle("-fx-text-fill: green;");
        duedateinput.setPromptText("Due Date :");
 
        //choice box to mark if a task is completed or not
        ComboBox<String> Statusvalue=new ComboBox<>();
        Statusvalue.setStyle("-fx-text-fill: blue; -fx-background-color: lightblue;");

 
        viewWindow.initModality(Modality.APPLICATION_MODAL);
        viewWindow.setTitle("Task Viewer");


        TaskViewer tkv=new TaskViewer();
        tkv.viewTask(listView.getSelectionModel().getSelectedItem());
        taskinput.setText(tkv.tName);
        descripinput.setText(tkv.tDescription);
        duedateinput.setText(tkv.tDueDate);
        Statusvalue.setValue(tkv.tStatus);
 
        // setting structure or design of layout using gridPane
        GridPane layout=new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(10);
        layout.setHgap(10);
        GridPane.setConstraints(taskLabel,0,0);
        GridPane.setConstraints(taskinput,0,1);
        GridPane.setConstraints(descripLabel,0,3);
        GridPane.setConstraints(descripinput,0,4);
        GridPane.setConstraints(DueDateLabel,0,6);
        GridPane.setConstraints(duedateinput,0,7);
        GridPane.setConstraints(Statusvalue,0,9);
        GridPane.setConstraints(close,3,11);
        layout.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
 
        layout.getChildren().addAll(taskLabel,taskinput,descripLabel,descripinput,duedateinput,DueDateLabel,Statusvalue,close);
        addScene=new Scene(layout, 400,350);
        viewWindow.setScene(addScene);
        viewWindow.show();
 
        close.setOnAction(e -> {
            viewWindow.close();
        });
     }

     void deleteTask(){
        TaskDeletor td=new TaskDeletor(listView.getSelectionModel().getSelectedItem());
        td.deleteTask();
        listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
        Alertbox.display("Deleted Task", "The Task got Deleted!");
     }
}