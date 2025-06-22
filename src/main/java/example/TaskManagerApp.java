package example;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class TaskManagerApp extends Application {
    private ToDoList toDoList;
    private ListView<ToDoTask> taskListView;
    private TextField nameField;
    private TextArea descriptionArea;
    private DatePicker datePicker;
    private CheckBox completedCheckbox;

    public TaskManagerApp() {
        toDoList = new ToDoList();
        toDoList.loadFromFile("myFile.txt");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Task Manager");

        taskListView = new ListView<>();
        taskListView.setPrefSize(400, 300);

        // Bind the task list in the to-do list to the task list view
        taskListView.setItems(toDoList.getTasks());

        VBox formContainer = new VBox(10);
        formContainer.setPadding(new Insets(10));

        nameField = new TextField();
        nameField.setPromptText("Task Name");

        descriptionArea = new TextArea();
        descriptionArea.setPromptText("Task Description");

        datePicker = new DatePicker();
        datePicker.setPromptText("Due Date");
        datePicker.setConverter(new StringConverter<>() {
            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        completedCheckbox = new CheckBox("Completed");

        Button addButton = new Button("Add Task");
        addButton.setOnAction(e -> addTask());

        Button editButton = new Button("Edit Task");
        editButton.setOnAction(e -> editTask());

        Button deleteButton = new Button("Delete Task");
        deleteButton.setOnAction(e -> deleteTask());

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> saveTasks());

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> primaryStage.close());

        formContainer.getChildren().addAll(
                nameField,
                descriptionArea,
                datePicker,
                completedCheckbox,
                addButton,
                editButton,
                deleteButton,
                saveButton,
                closeButton
        );

        HBox root = new HBox(10);
        root.getChildren().addAll(taskListView, formContainer);

        Scene scene = new Scene(root, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Load tasks into the task list view
        taskListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        nameField.setText(newValue.getName());
                        descriptionArea.setText(newValue.getDescription());
                        datePicker.setValue(newValue.getDueDate());
                        completedCheckbox.setSelected(newValue.isCompleted());
                    }
                }
        );

        // Save tasks to file when the application is closed
        primaryStage.setOnCloseRequest(event -> {
            saveTasks();
        });
    }

    private void addTask() {
        String name = nameField.getText();
        String description = descriptionArea.getText();
        LocalDate date = datePicker.getValue();

        if (!name.isEmpty() && date != null) {
            toDoList.addTask(name, description, date);
            clearFields();
        } else {
            showAlert("Invalid Input", "Please enter a name and a due date for the task.");
        }
    }

    private void editTask() {
        int selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String name = nameField.getText();
            String description = descriptionArea.getText();
            LocalDate date = datePicker.getValue();
            boolean completed = completedCheckbox.isSelected();

            if (!name.isEmpty() && date != null) {
                toDoList.editTask(selectedIndex, name, description, date, completed);
                clearFields();
            } else {
                showAlert("Invalid Input", "Please enter a name and a due date for the task.");
            }
        } else {
            showAlert("No Selection", "Please select a task to edit.");
        }
    }

    private void deleteTask() {
        int selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            toDoList.deleteTask(selectedIndex);
            clearFields();
        } else {
            showAlert("No Selection", "Please select a task to delete.");
        }
    }

    private void clearFields() {
        nameField.clear();
        descriptionArea.clear();
        datePicker.setValue(null);
        completedCheckbox.setSelected(false);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void saveTasks() {
        toDoList.saveToFile("myFile.txt");
        showAlert("Save", "Tasks saved to file.");
    }

    private static class ToDoList {
        private ObservableList<ToDoTask> tasks;

        public ToDoList() {
            tasks = FXCollections.observableArrayList();
        }

        public ObservableList<ToDoTask> getTasks() {
            return tasks;
        }

        public void addTask(String name, String description, LocalDate dueDate) {
            ToDoTask task = new ToDoTask(name, description, dueDate);
            tasks.add(task);
        }

        public void editTask(int index, String name, String description, LocalDate dueDate, boolean completed) {
            ToDoTask task = tasks.get(index);
            task.setName(name);
            task.setDescription(description);
            task.setDueDate(dueDate);
            task.setCompleted(completed);
        }

        public void deleteTask(int index) {
            tasks.remove(index);
        }

        public void loadFromFile(String filename) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] taskData = line.split(";");
                    if (taskData.length == 4) {
                        String name = taskData[0];
                        String description = taskData[1];
                        LocalDate dueDate = LocalDate.parse(taskData[2]);
                        boolean completed = Boolean.parseBoolean(taskData[3]);
                        tasks.add(new ToDoTask(name, description, dueDate, completed));
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading tasks from file: " + e.getMessage());
            }
        }

        public void saveToFile(String filename) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                for (ToDoTask task : tasks) {
                    String taskString = task.getName() + ";" + task.getDescription() + ";" + task.getDueDate() + ";" + task.isCompleted();
                    writer.write(taskString);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error saving tasks to file: " + e.getMessage());
            }
        }
    }

    private static class ToDoTask {
        private String name;
        private String description;
        private LocalDate dueDate;
        private boolean completed;

        public ToDoTask(String name, String description, LocalDate dueDate) {
            this.name = name;
            this.description = description;
            this.dueDate = dueDate;
            this.completed = false;
        }

        public ToDoTask(String name, String description, LocalDate dueDate, boolean completed) {
            this.name = name;
            this.description = description;
            this.dueDate = dueDate;
            this.completed = completed;
        }

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

        public LocalDate getDueDate() {
            return dueDate;
        }

        public void setDueDate(LocalDate dueDate) {
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
            return name;
        }
    }
}
