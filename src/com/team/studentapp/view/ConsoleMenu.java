package com.team.studentapp.view;


import com.team.studentapp.controller.StudentController;

import java.util.Scanner;

/**
 * Author : BELGUITI
 * Classe ConsoleMenu : interface console pour gérer les étudiants et leurs cours.
 */
public class ConsoleMenu {

    StudentController controller=new StudentController();

    /**
     * Lance le menu principal.
     */
    public void start() {
        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT MENU =====");
            System.out.println("1. Ajouter un étudiant");
            System.out.println("2. Afficher tous les étudiants");
            System.out.println("3. Modifier un étudiant");
            System.out.println("4. Supprimer un étudiant");
            System.out.println("5. Ajouter un cours et une note");
            System.out.println("6. Afficher la moyenne d’un étudiant");
            System.out.println("7. Meilleur étudiant");
            System.out.println("8. Étudiants en échec");
            System.out.println("9. Quitter");
            System.out.println("===================================");
            System.out.print("Entrez votre choix : ");

            choice = getIntInput();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> showAllStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> addCourseToStudent();
                case 6 -> showAverage();
                case 7 -> showBestStudent();
                case 8 -> showFailingStudents();
                case 9 -> System.out.println("👋 Merci d'avoir utilisé l'application !");
                default -> System.out.println("❌ Choix invalide. Réessayez.");
            }

        } while (choice != 9);
    }

    // ------------------ ACTIONS MENU ------------------

    private void addStudent() {
        controller.addStudent();
    }

    private void showAllStudents() {
        controller.showAllStudents();
    }

    private void updateStudent() {
        controller.updateStudent();
    }

    private void deleteStudent() {
        controller.deleteStudent();
    }

    private void addCourseToStudent() {
        controller.addCoursesToStudent();
    }

    private void showAverage() {
        controller.calculateAVGStudent();
    }

    private void showBestStudent() {
        controller.bestStudent();
    }

    private void showFailingStudents() {
        controller.failingStudents();
    }

    // ------------------ UTILITAIRES ------------------

    private int getIntInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    private double getDoubleInput() {
        return 0;
    }
}