import java.util.*;

public class ToDoList {

    private static final String REGEX_TRY = "TRY"; // создаем константу для проверки команды TRY
    private static final String REGEX_HELP = "HELP"; // создаем константу для проверки команды HELP
    private static final String REGEX_LIST = "LIST"; // создаем константу для проверки команды LIST
    private static final String REGEX_CLEAR = "CLEAR"; // создаем константу для проверки команды CLEAR
    private static final String REGEX_ADD_CASE = "^(ADD)\\s*[^-?0-9]([^-?0-9].*)$"; // создаем константу для проверки регулярным выражением команды ADD "new case"
    private static final String REGEX_ADD_CASE_REPLACEALL = "(ADD)\\s*"; // создаем константу для вырезания нового кейса из введенной строки для команды ADD "new case"
    private static final String REGEX_ADD_N_CASE = "^(ADD)\\s+?-?\\d+\\s+?(.*)$"; // создаем константу для проверки регулярным выражением команды команды ADD N "new case"
    private static final String REGEX_ADD_N_CASE_REPLACEALL = "(ADD)\\s*-?\\d*\\s*"; // создаем константу для вырезания нового кейса из введенной строки для команды ADD N "new case"
    private static final String REGEX_EDIT_N_CASE = "^(EDIT)\\s*\\d+\\s*(.*)$"; // создаем константу для проверки регулярным выражением команды команды ADD N "new case"
    private static final String REGEX_EDIT_N_CASE_REPLACEALL = "(EDIT)\\s*\\d*\\s*"; // создаем константу для вырезания нового кейса из введенной строки для команды EDIT N "new case"
    private static final String REGEX_DELETE_N = "^(DELETE)\\s*\\d+\\s*$"; // создаем константу для проверки регулярным выражением команды DELETE N
    private static final String REGEX_EXIT = "EXIT"; // создаем константу для проверки команды EXIT

    public static ArrayList<String> myToDoList = new ArrayList<>(); // создаем список элементов myToDoList

    public static void welcomeHelpList() {
        if (myToDoList.isEmpty()) {
            System.out.println("\nYou are Wellcome! Your to do list is empty.\n" +
                    "To work with the list, please see the help section, the HELP command. " +
                    "\nAvailable commands:\n" +
                    "  command <TRY>   -> Command allows you to use an already prepared to-do list for training\n" +
                    "  command <LIST>  -> List of all my cases.\n" +
                    "  command <CLEAR> -> Delete all cases from list.\n" +
                    "  command <ADD \"new case\">     -> Add a new case to the end of the list.\n" +
                    "  command <ADD N  \"new case\">  -> Add a new case anywhere in the list. N - number of the case in the list.\n" +
                    "  command <EDIT N \"new case\">  -> Change (replace) an existing case. N - number of the case in the list.\n" +
                    "  command <REMOVE N> -> Delete case. N - number of the case in the list.\n" +
                    "  command <EXIT>   -> STOP program.\n" +
                    "All commands enter without < > and new case without \" \"! For example:  ADD 2 Go home.");
        } else
            printListEntry();
    }

    public static void main(String[] args) {


        // Готовый список дел, который можно загрузить в начале работы и использовать готовый шаблон, не используя команду TRY
//        myToDoList.add("Go to office.");
//        myToDoList.add("Create new project.");
//        myToDoList.add("Call the manager.");
//        myToDoList.add("Visit the Bank.");
//        myToDoList.add("Meeting with the contractor.");
//        myToDoList.add("Go to dinner.");
//        myToDoList.add("Sleeping.");
//        myToDoList.add("Visit the swimming pool.");

        //При запуске программы проверяется список элементов (пуст он или нет) и выводится информация о списке дел и предложение ознакомиться с разделом справки.



        //Бесконечный цикл, для того чтобы можно было постоянно вводить командый
        while (true) {

            Scanner scan = new Scanner(System.in);
            System.out.println("\nEnter the command: ");
            String command = scan.nextLine();

            command = command.replaceFirst("^\\s+", "");
            command = command.replaceAll("\\s+", " ");
            String[] arrayCommands = command.split(" ");

            for (String i : arrayCommands) {
                System.out.println(i);
            }

            //System.out.println();

            if (arrayCommands[0].equals(REGEX_TRY)) {
                tryUseMyListCases();
                printList();
            }

            if (arrayCommands[0].equals(REGEX_HELP)) {
                printHelp();
            }

            if (arrayCommands[0].equals(REGEX_LIST)) {
                printList();
            }

            if (arrayCommands[0].equals(REGEX_CLEAR)) {
                clearList();
            }

            if (command.matches(REGEX_ADD_CASE)) {
                addNewCaseToTail(command);
                //myToDoList.add(myToDoList.size(), command.replaceAll(REGEX_ADD_CASE_REPLACEALL, ""));
                printList();
            }

            //&& Integer.parseInt(arrayCommands[1]) > 0 && Integer.parseInt(arrayCommands[1]) <= myToDoList.size()
            if (command.matches(REGEX_ADD_N_CASE)) {
                //myToDoList.add(Integer.parseInt(arrayCommands[1].trim()) - 1, command.replaceAll(REGEX_ADD_N_CASE_REPLACEALL, ""));
                addNewCaswAnywhere(arrayCommands, command);
                printList();
            }

            if (command.matches(REGEX_EDIT_N_CASE) && Integer.parseInt(arrayCommands[1]) > 0 && Integer.parseInt(arrayCommands[1]) <= myToDoList.size()) {
                System.out.println("My new list of cases:");
                String changeddCase = myToDoList.get(Integer.parseInt(arrayCommands[1].trim()) - 1);
                int numberChangedCase = Integer.parseInt(arrayCommands[1].trim());

                myToDoList.set(Integer.parseInt(arrayCommands[1].trim()) - 1, command.replaceAll(REGEX_EDIT_N_CASE_REPLACEALL, ""));
                for (int i = 0; i < myToDoList.size(); i++) {
                    System.out.println((i + 1) + ". " + myToDoList.get(i));
                }
                System.out.println("\nChanged case: " + numberChangedCase + ". " + changeddCase);
            }

            if (command.matches(REGEX_DELETE_N) && Integer.parseInt(arrayCommands[1]) > 0 && Integer.parseInt(arrayCommands[1]) <= myToDoList.size()) {
                System.out.println("My new list of cases:");
                String deletedCase = myToDoList.remove(Integer.parseInt(arrayCommands[1].trim()) - 1);
                int numberDeletedCase = Integer.parseInt(arrayCommands[1].trim());
                for (int i = 0; i < myToDoList.size(); i++) {
                    System.out.println((i + 1) + ". " + myToDoList.get(i));
                }
                System.out.println("Deleted case: " + numberDeletedCase + ". " + deletedCase);
            }

            if (arrayCommands[0].equals(REGEX_EXIT)) {
                System.out.println("My list of cases:");
                for (int i = 0; i < myToDoList.size(); i++) {
                    System.out.println((i + 1) + ". " + myToDoList.get(i));
                }
                System.out.println("\nGoodbye!");
                break;
            }
            System.out.println(myToDoList.size());
        }
    }



        public static void tryUseMyListCases ()
        { // метод, который позволяет использовать для тренировки уже готовый список дел
            System.out.println("\nYou are welcome! This is a ready template. You can practice.");
            myToDoList.add("Go to office.");
            myToDoList.add("Craete new project.");
            myToDoList.add("Call the manager.");
            myToDoList.add("Visit the Bank.");
            myToDoList.add("Meeting with the contractor.");
            myToDoList.add("Go to dinner.");
            myToDoList.add("Sleeping.");
            myToDoList.add(0, "Visit the swimming pool.");
            //printList();
        }

        public static void clearList() { // метод для удаления всего списка дел. Если надо начать весь список сначала.
            myToDoList.clear();
            System.out.println("Your list is empty. You can create your list again or use a ready-made template (command TRY).");
        }

    private static void printHelp() { // метод для вывода в консоль информации о команде HELP
        System.out.println("List of available commands:\n" +
                "command <TRY>  -> Command allows you to use an already prepared to-do list for training.\n" +
                "command <LIST>  -> List of all my cases.\n" +
                "command <CLEAR> -> Delete all cases from list.\n" +
                "command <ADD \"new case\">     -> Add a new case to the end of the list.\n" +
                "command <ADD N  \"new case\">  -> Add a new case anywhere in the list. N - number of the case in the list.\n" +
                "command <EDIT N \"new case\">  -> Change (replace) an existing case. N - number of the case in the list.\n" +
                "command <REMOVE N> -> Delete case. N - number of the case in the list.\n" +
                "command <EXIT>   -> STOP pragramm.\n" +
                "All commands enter without < > and new case without \" \"! For example:  ADD 2 Go home.");
    }

    public static void printListEntry() { // метод для печати полного списка дел, если список изначально задан правилами
        System.out.println("\nThis is my list of cases.\n" +
                "To manage the list of cases, please, see the help section.\n" +
                "For this, enter the <HELP> command.\n");
        for (int i = 0; i < myToDoList.size(); i++) {
            System.out.println((i + 1) + ". " + myToDoList.get(i));
        }
    }

    public static void printList() { // метод для печати полного списка дел
        if (myToDoList.isEmpty()) {
            System.out.println("List is empty.");
        } else
        System.out.println("Your list of cases:");
        for (int i = 0; i < myToDoList.size(); i++) {
            System.out.println((i + 1) + ". " + myToDoList.get(i));
        }

    }

    public static void addNewCaseToTail(String command) { //метод для добавления нового дела в конец списка командой ADD
        myToDoList.add(myToDoList.size(), command.replaceAll(REGEX_ADD_CASE_REPLACEALL, ""));
    }

    public static void addNewCaswAnywhere(String[] arrayCommands, String command) { //метод для добавления нового дела в любую позицию в пределах списка, используя команду "ADD N new case"
        if (Integer.parseInt(arrayCommands[1]) > 0 && Integer.parseInt(arrayCommands[1]) <= myToDoList.size()) { // проверка команды (является ли позиця дела в пределах списка или нет)
            myToDoList.add(Integer.parseInt(arrayCommands[1].trim()) - 1, command.replaceAll(REGEX_ADD_N_CASE_REPLACEALL, ""));
        } else if (Integer.parseInt(arrayCommands[1]) <= 0) {
            System.out.println("\nВы ввели недопустимый номер дела. Номер позиции не может быть отрицательным числом.");
        }
        else if (myToDoList.isEmpty()) {
            addNewCaseToTail(command);
        }
        else System.out.println("Вы вышли за пределы списка. Существующий список дел имеет " + myToDoList.size() + " записи -(ей). Ознакомьтесь со списком еще раз");
    }

}

