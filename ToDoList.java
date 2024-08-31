import java.util.*;
public class ToDoList {
    // Initialize ArrayLists and Random
    private ArrayList<String> formalTasks;
    private ArrayList<String> informalTasks;
    private ArrayList<String> suggestedInformalTasks;
    private Random random;

    // Define Constructor
    public ToDoList() {
        formalTasks = new ArrayList<>();
        informalTasks = new ArrayList<>();
        suggestedInformalTasks = new ArrayList<>();
        random = new Random();
    }

    // Method to add multiple tasks
    public void AddMultipleTasks(String[] tasks, String type) {
        for (String task : tasks) {
            if (type.equalsIgnoreCase("work")) {
                formalTasks.add(task);
            } else if (type.equalsIgnoreCase("informal")) {
                informalTasks.add(task);
            } else {
                System.out.println("Invalid task type. Please enter 'work' or 'informal'.");
            }
        }
    }

    // Method to complete a work task and suggest an informal task
    public void completeTask() {
        if (!formalTasks.isEmpty()) {
            formalTasks.remove(0);
            System.out.println("Work task completed!");

            if (!informalTasks.isEmpty()) {
                // Check if there are suggested tasks left
                if (suggestedInformalTasks.size() == informalTasks.size()) {
                    // All informal tasks have been suggested, reset the list
                    suggestedInformalTasks.clear();
                }

                String informalTask;
                do {
                    informalTask = informalTasks.get(random.nextInt(informalTasks.size()));
                } while (suggestedInformalTasks.contains(informalTask));

                suggestedInformalTasks.add(informalTask);
                System.out.println("Suggested informal activity: " + informalTask);
            } else {
                System.out.println("No informal tasks available.");
            }
        } else {
            System.out.println("No work tasks to complete.");
        }
    }

    public static void main(String[] args) {
        ToDoList app = new ToDoList();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the To-Do List App!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Multiple Work Tasks");
            System.out.println("2. Add Multiple Informal Tasks");
            System.out.println("3. Complete Work Task");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter multiple work tasks, separated by commas (e.g., task1,task2,task3): ");
                    String formalTasksInput = scanner.nextLine();
                    String[] formalTasks = formalTasksInput.split(",");
                    app.AddMultipleTasks(formalTasks, "work");
                    break;
                case 2:
                    System.out.println("Enter multiple informal tasks, separated by commas (e.g., task1,task2,task3): ");
                    String informalTasksInput = scanner.nextLine();
                    String[] informalTasks = informalTasksInput.split(",");
                    app.AddMultipleTasks(informalTasks, "informal");
                    break;
                case 3:
                    app.completeTask();
                    break;
                case 4:
                    System.out.println("Exiting the app. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

