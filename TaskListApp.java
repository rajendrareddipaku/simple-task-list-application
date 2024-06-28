import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskListApp {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    taskList.addTask(getTaskName(scanner));
                    break;
                case 2:
                    removeTask(scanner, taskList);
                    break;
                case 3:
                    listTasks(taskList);
                    break;
                case 4:
                    System.out.println("Exiting the application.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Task List Application");
        System.out.println("1. Add Task");
        System.out.println("2. Remove Task");
        System.out.println("3. List Tasks");
        System.out.println("4. Quit");
        System.out.print("Select an option: ");
    }

    private static int getUserChoice(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    private static String getTaskName(Scanner scanner) {
        System.out.print("Enter task name: ");
        scanner.nextLine(); // Consume the leftover newline
        return scanner.nextLine();
    }

    private static void removeTask(Scanner scanner, TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("No tasks to remove.");
        } else {
            taskList.listTasks();
            int taskNumber = getUserInput(scanner, "Enter the task number to remove: ");
            if (taskList.isValidTaskNumber(taskNumber)) {
                taskList.removeTask(taskNumber);
            } else {
                System.out.println("Invalid task number.");
            }
        }
    }

    private static int getUserInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    private static void listTasks(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("No tasks to list.");
        } else {
            taskList.listTasks();
        }
    }
}

class TaskList {
    private ArrayList<String> tasks = new ArrayList<>();

    public void addTask(String name) {
        tasks.add(name);
        System.out.println("Task added.");
    }

    public void removeTask(int taskNumber) {
        tasks.remove(taskNumber - 1);
        System.out.println("Task removed.");
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public boolean isValidTaskNumber(int taskNumber) {
        return taskNumber >= 1 && taskNumber <= tasks.size();
    }
}
