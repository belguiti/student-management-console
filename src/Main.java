
import com.team.studentapp.controller.StudentController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentController controller = new StudentController();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Gestion des étudiants ---");
            System.out.println("1. Ajouter un étudiant");
            System.out.println("2. Lister tous les étudiants");
            System.out.println("3. Mettre à jour un étudiant");
            System.out.println("4. Supprimer un étudiant");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    controller.addStudent();
                    break;
                case 2:
                    controller.showAllStudents();
                    break;
                case 3:
                    controller.updateStudent();
                    break;
                case 4:
                    controller.deleteStudent();
                    break;
                case 5:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        } while (choice != 5);

        sc.close();
    }
}
