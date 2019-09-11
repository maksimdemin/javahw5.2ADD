import java.util.*;

public class ToDoList {

    public static void main(String[] args) {
        //При запуске программы выводится информация о списке дел и предложение ознакомиться с разделом справки.
        System.out.println("\nThis is my list of cases.\n" +
                "To manage the list of cases, please, see the help section.\n" +
                "For this, enter the <HELP> command.\n");

        ArrayList<String> myToDoList = new ArrayList<>();

        myToDoList.add("Go to office.");
        myToDoList.add("Craete new project.");
        myToDoList.add("Call the manager.");
        myToDoList.add("Visit the Bank.");
        myToDoList.add("Meeting with the contractor.");
        myToDoList.add("Go to dinner.");
        myToDoList.add("Sleeping.");
        myToDoList.add(0, "Visit the swimming pool.");

        for (int i = 0; i < myToDoList.size(); i++) {
            System.out.println((i + 1) + ". " + myToDoList.get(i));
        }
        System.out.println();

        //Бесконечный цикл, для того чтобы можно было постоянно вводить командый
        while (true) {

            Scanner scan = new Scanner(System.in);
            System.out.println("Enter the command: ");
            String command = scan.nextLine();

            command = command.replaceFirst("^\\s+", "");
            command = command.replaceAll("\\s+", " ");
            String[] arrayCommands = command.split(" ");

            for (String i : arrayCommands) {
                System.out.println(i);
            }

            System.out.println();

            if (arrayCommands[0].equals("HELP")) {

                System.out.println("command <LIST>  -> List of all my cases.\n" +
                        "command <ADD>   -> Add a new case to the end of the list.\n" +
                        "command <ADD N  \"new case\">  -> Add a new case anywhere in the list. N - number of the case in the list.\n" +
                        "command <EDIT N \"new case\">  -> Change (replace) an existing case. N - number of the case in the list.\n" +
                        "command <REMOVE N> -> Delete case. N - number of the case in the list.\n" +
                        "command <EXIT>   -> STOP pragramm.\n" +
                        "All commands enter without < > !");
            }


            if (arrayCommands[0].equals("LIST")) {
                System.out.println("My list of cases:");
                for (int i = 0; i < myToDoList.size(); i++) {
                    System.out.println((i + 1) + ". " + myToDoList.get(i));
                }
            }

            if (command.matches("^(ADD)\\s*[^0-9]([^0-9].*)$")) {
                myToDoList.add(myToDoList.size(), command.replaceAll("(ADD)\\s*", ""));
                for (int i = 0; i < myToDoList.size(); i++) {
                    System.out.println((i + 1) + ". " + myToDoList.get(i));
                }
            }

            if (command.matches("^(ADD)\\s+?\\d+\\s+?(.*)$") && Integer.parseInt(arrayCommands[1]) > 0 && Integer.parseInt(arrayCommands[1]) <= myToDoList.size()) {
                myToDoList.add(Integer.parseInt(arrayCommands[1].trim()) - 1, command.replaceAll("(ADD)\\s*\\d*\\s*", ""));
                for (int i = 0; i < myToDoList.size(); i++) {
                    System.out.println((i + 1) + ". " + myToDoList.get(i));
                }
            }

            if (command.matches("^(EDIT)\\s*\\d+\\s*(.*)$") && Integer.parseInt(arrayCommands[1]) > 0) {
                System.out.println("My new list of cases:");
                String changeddCase = myToDoList.get(Integer.parseInt(arrayCommands[1].trim()) - 1);
                int numberChangedCase = Integer.parseInt(arrayCommands[1].trim());

                myToDoList.add(Integer.parseInt(arrayCommands[1].trim()) - 1, command.replaceAll("(EDIT)\\s*\\d*\\s*", ""));
                for (int i = 0; i < myToDoList.size(); i++) {
                    System.out.println((i + 1) + ". " + myToDoList.get(i));
                }
                System.out.println("Changed case: " + numberChangedCase + ". " + changeddCase);
            }

            if (command.matches("^(DELETE)\\s*\\d+$") && Integer.parseInt(arrayCommands[1]) > 0 && Integer.parseInt(arrayCommands[1]) > 0) {
                System.out.println("My new list of cases:");
                String deletedCase = myToDoList.remove(Integer.parseInt(arrayCommands[1].trim()) - 1);
                int numberDeletedCase = Integer.parseInt(arrayCommands[1].trim());
                for (int i = 0; i < myToDoList.size(); i++) {
                    System.out.println((i + 1) + ". " + myToDoList.get(i));
                }
                System.out.println("Deleted case: " + numberDeletedCase + ". " + deletedCase);
            }

            if (arrayCommands[0].equals("EXIT")) {
                System.out.println("My list of cases:");
                for (int i = 0; i < myToDoList.size(); i++) {
                    System.out.println((i + 1) + ". " + myToDoList.get(i));
                }
                break;
            }
            System.out.println(myToDoList.size());
        }
    }
}

