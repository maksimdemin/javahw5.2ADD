import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //ToDoList.tryUseMyListCases(); // чтобы не создавать новый список с нуля, можно активировать эту строку и загружать сразу шаблон
        ToDoList.welcomeHelpList(); // вначале запускаем информационный метод с разделом справки, если список пуст, то запускается другой метод

            while (true) {

                    Scanner scan = new Scanner(System.in);
                    System.out.println("\nEnter the command: ");
                    String command = scan.nextLine();

                    command = command.replaceFirst("^\\s+", "");
                    command = command.replaceAll("\\s+", " ");
                    String[] arrayCommands = command.split(" ");

                if (!command.matches("EXIT")) {

                    ToDoList.checkCommand(arrayCommands, command); // вызов метода из класса ToDoList (отсюда начинается проверка введенных команд)
                } else {
                    ToDoList.printListForExit(); // запускаем специальный метод для команды EXIT
                    System.out.println("Goodbye! See you later.");
                    break;
                }
            }
        }
    }
