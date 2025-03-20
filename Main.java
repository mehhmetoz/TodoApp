import java.util.Scanner;
import java.util.List;

public class Main {
    private static TodoList todoList = new TodoList();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Buffer temizleme

            switch (choice) {
                case 1:
                    addTodo();
                    break;
                case 2:
                    listTodos();
                    break;
                case 3:
                    toggleTodo();
                    break;
                case 4:
                    removeTodo();
                    break;
                case 5:
                    System.out.println("Program sonlandırılıyor...");
                    return;
                default:
                    System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Todo Uygulaması ===");
        System.out.println("1. Yeni görev ekle");
        System.out.println("2. Görevleri listele");
        System.out.println("3. Görev durumunu değiştir");
        System.out.println("4. Görev sil");
        System.out.println("5. Çıkış");
        System.out.print("Seçiminiz: ");
    }

    private static void addTodo() {
        System.out.print("Görev başlığı: ");
        String title = scanner.nextLine();
        System.out.print("Görev açıklaması: ");
        String description = scanner.nextLine();
        
        todoList.addTodo(title, description);
        System.out.println("Görev başarıyla eklendi!");
    }

    private static void listTodos() {
        List<Todo> todos = todoList.getAllTodos();
        if (todos.isEmpty()) {
            System.out.println("Henüz hiç görev eklenmemiş!");
            return;
        }
        
        System.out.println("\n=== Görevler ===");
        for (Todo todo : todos) {
            System.out.println(todo);
        }
    }

    private static void toggleTodo() {
        System.out.print("Durumu değiştirilecek görevin ID'si: ");
        int id = scanner.nextInt();
        todoList.toggleTodo(id);
        System.out.println("Görev durumu güncellendi!");
    }

    private static void removeTodo() {
        System.out.print("Silinecek görevin ID'si: ");
        int id = scanner.nextInt();
        todoList.removeTodo(id);
        System.out.println("Görev silindi!");
    }
} 