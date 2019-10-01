import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //ToDoList.tryUseMyListCases(); // чтобы не создавать новый список с нуля, можно активировать эту строку и загружать сразу шаблон
        ToDoList.welcomeHelpList(); // вначале запускаем информационный метод с разделом справки, если список пуст, то запускается другой метод

            while (true) {

                    Scanner scan = new Scanner(System.in);
                    System.out.println("\nEnter the command: ");
                    String command = scan.nextLine();

                    command = command.replaceFirst("^\\s+", ""); // удаляем все пробелы в начале введенной пробелы
                    command = command.replaceAll("\\s+", " "); // ищем все пробелы и заменяем их на один пробел
                    String[] arrayCommands = command.split(" "); // введеную команду разделяем методом split() и сохраняем всё в массив строк

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
