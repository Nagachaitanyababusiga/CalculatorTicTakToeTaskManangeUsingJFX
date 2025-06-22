package example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ToDoListManager3 extends Application {

    private ObservableList<Task> tasks;
    private ListView<Task> taskListView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        tasks = FXCollections.observableArrayList();
        loadTasks(); // Load tasks from file

        primaryStage.setTitle("To-Do List App");

        // Create task list view
        taskListView = new ListView<>(tasks);
        taskListView.setPrefWidth(300);
        taskListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Create buttons
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");
        Button markCompleteButton = new Button("Mark Complete");
        Button viewButton = new Button("View");

        // Create form controls
        TextField nameField = new TextField();
        TextArea descriptionArea = new TextArea();
        TextField dueDateField = new TextField();
        Button saveButton = new Button("Save");

        // Add event handlers
        addButton.setOnAction(e -> addTask(nameField.getText(), descriptionArea.getText(), dueDateField.getText()));
        deleteButton.setOnAction(e -> deleteTask());
        markCompleteButton.setOnAction(e -> markTaskComplete());
        viewButton.setOnAction(e -> viewTask());

        saveButton.setOnAction(e -> saveTasks());

        // Create task form
        VBox form = new VBox(10);
        form.setPadding(new Insets(10));
        form.getChildren().addAll(
                new Label("Name:"),
                nameField,
                new Label("Description:"),
                descriptionArea,
                new Label("Due Date:"),
                dueDateField,
                saveButton
        );

        // Create button bar
        HBox buttonBar = new HBox(10);
        buttonBar.setPadding(new Insets(10));
        buttonBar.getChildren().addAll(addButton, deleteButton, markCompleteButton, viewButton);

        // Create layout
        BorderPane layout = new BorderPane();
        layout.setLeft(taskListView);
        layout.setCenter(form);
        layout.setBottom(buttonBar);

        primaryStage.setScene(new Scene(layout, 600, 400));
        primaryStage.show();
    }

    private void addTask(String name, String description, String dueDate) {
        try {
            LocalDate.parse(dueDate); // Validate the date format
            Task newTask = new Task(name, description, dueDate);
            tasks.add(newTask);
        } catch (DateTimeParseException e) {
            showAlert("Error", "Invalid date format. Please enter the date in the format yyyy-MM-dd.", Alert.AlertType.ERROR);
        }
    }

    private void deleteTask() {
        int selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tasks.remove(selectedIndex);
        }
    }

    private void markTaskComplete() {
        int selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Task selectedTask = tasks.get(selectedIndex);
            selectedTask.setCompleted(true);
            taskListView.refresh();
        }
    }

    private void viewTask() {
        int selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Task selectedTask = tasks.get(selectedIndex);
            showAlert("Task Details", selectedTask.toString(), Alert.AlertType.INFORMATION);
        }
    }

    private void saveTasks() {
        try (PrintWriter writer = new PrintWriter("tasks.txt")) {
            for (Task task : tasks) {
                writer.println(task.getName());
                writer.println(task.getDescription());
                writer.println(task.getDueDate());
                writer.println(task.isCompleted());
                writer.println("---"); // Add a separator between tasks
            }
        } catch (IOException e) {
            showAlert("Error", "Failed to save tasks.", Alert.AlertType.ERROR);
        }
    }

    private void loadTasks() {
        tasks.clear(); // Clear existing tasks before loading

        try (BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"))) {
            String line;
            String name = null;
            String description = null;
            String dueDate = null;
            boolean completed = false;

            while ((line = reader.readLine()) != null) {
                if (line.equals("---")) {
                    // Create a new task when encountering the separator
                    if (name != null && description != null && dueDate != null) {
                        Task task = new Task(name, description, dueDate);
                        task.setCompleted(completed);
                        tasks.add(task);
                    }

                    // Reset variables for the next task
                    name = null;
                    description = null;
                    dueDate = null;
                    completed = false;
                } else if (name == null) {
                    name = line;
                } else if (description == null) {
                    description = line;
                } else if (dueDate == null) {
                    dueDate = line;
                } else {
                    completed = Boolean.parseBoolean(line);
                }
            }

            // Create the last task encountered
            if (name != null && description != null && dueDate != null) {
                Task task = new Task(name, description, dueDate);
                task.setCompleted(completed);
                tasks.add(task);
            }
        } catch (IOException e) {
            showAlert("Error", "Failed to load tasks.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public class Task {
        private String name;
        private String description;
        private String dueDate;
        private boolean completed;

        public Task(String name, String description, String dueDate) {
            this.name = name;
            this.description = description;
            this.dueDate = dueDate;
            this.completed = false;
        }

        // Getters and setters

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDueDate() {
            return dueDate;
        }

        public void setDueDate(String dueDate) {
            this.dueDate = dueDate;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        @Override
        public String toString() {
            return "Name: " + name + "\nDescription: " + description + "\nDue Date: " + dueDate + "\nCompleted: " + completed;
        }
    }
}
