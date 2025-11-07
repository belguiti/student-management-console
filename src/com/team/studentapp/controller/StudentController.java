package com.team.studentapp.controller;

import com.team.studentapp.model.Course;
import com.team.studentapp.model.Student;
import com.team.studentapp.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentController {

    private final StudentService studentService;
    private Scanner sc = new Scanner(System.in);

    public StudentController(){
        this.studentService=new StudentService();
    }

    public void addStudent() {
        try {
            int id = 0;
            boolean validId = false;
            while (!validId) {
                System.out.print("Entrez l'Id de l'étudiant : ");
                String idInput = sc.nextLine();
                try {
                    id = Integer.parseInt(idInput);
                    if (id <= 0) {
                        System.out.println("❌ L'ID doit être un nombre positif !");
                    } else {
                        validId = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("❌ Erreur : veuillez entrer un nombre valide pour l'ID.");
                }
            }

            String name = "";
            while (true) {
                System.out.print("Entrez le nom : ");
                name = sc.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println("❌ Le nom ne peut pas être vide !");
                } else {
                    break;
                }
            }

            String email = "";
            while (true) {
                System.out.print("Entrez l'email : ");
                email = sc.nextLine().trim();
                if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                    System.out.println("❌ Email invalide ! Exemple : exemple@mail.com");
                } else {
                    break;
                }
            }

            Student student = new Student(id, name, email);
            studentService.addStudent(student);
            System.out.println("✅ Étudiant ajouté avec succès !");
        } catch (Exception e) {
            System.out.println("⚠️ Une erreur est survenue lors de l'ajout de l'étudiant : " + e.getMessage());
        }
    }


    public void showAllStudents(){
        System.out.println("\n Liste des etudiants");
       for(Student s: studentService.getAllStudents()){
           System.out.println(s);
       }
    }

    public void updateStudent() {
        System.out.println("\n===== MISE À JOUR D'UN ÉTUDIANT =====");
        int id = -1;

        // Boucle pour saisir un ID valide
        while (true) {
            System.out.print("Entrez l'ID de l'étudiant : ");
            String input = sc.nextLine().trim();
            try {
                id = Integer.parseInt(input);
                if (id <= 0) {
                    System.out.println("❌ L'ID doit être un nombre positif.");
                    continue;
                }
                break; // ID valide
            } catch (NumberFormatException e) {
                System.out.println("❌ Erreur : l'ID doit être un nombre entier.");
            }
        }

        // Appel du service
        studentService.updateStudent(sc, id);
    }



    public Student getStudentbyId(){
        Student foundStudent = null;
        int idStudent;

        //  Boucle pour redemander l'ID si non trouvé
        while (true) {
            System.out.print(" Entrez l'ID de l'étudiant : ");
            while (!sc.hasNextInt()) { // Vérifie que l'entrée est bien un entier
                System.out.print(" Veuillez entrer un ID valide (nombre) : ");
                sc.next();
            }
            idStudent = sc.nextInt();
            sc.nextLine();

            // Rechercher l'étudiant
            foundStudent=studentService.getStudentbyId(idStudent);

            if (foundStudent != null) {
                System.out.println(" Étudiant trouvé : " + foundStudent.getName() + " (" + foundStudent.getEmail() + ")");
                return  foundStudent;
            } else {
                System.out.print(" Aucun étudiant trouvé avec l'ID " + idStudent + ". Voulez-vous réessayer ? (o/n) : ");
                String retry = sc.nextLine().trim().toLowerCase();
                if (!retry.equals("o")) {
                    System.out.println(" Retour au menu principal...");
                    return foundStudent;
                }
            }
        }

    }

    public void deleteStudent() {
        try {
            System.out.println("\n===== SUPPRESSION D'UN ÉTUDIANT =====");
            System.out.print("Entrez l'ID de l'étudiant à supprimer : ");
            String input = sc.nextLine().trim();

            // Vérification que l'ID est bien un entier
            int id = Integer.parseInt(input);
            boolean result = studentService.deleteStudent(id);

            if (result) {
                System.out.println("✅ Étudiant supprimé avec succès !");
            } else {
                System.out.println("⚠️ Aucun étudiant trouvé avec cet ID.");
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Erreur : l'ID doit être un nombre entier valide !");
        } catch (Exception e) {
            System.out.println("❌ Une erreur est survenue lors de la suppression : " + e.getMessage());
        }
    }


    public void calculateAVGStudent(){
        Student student=getStudentbyId();
        if (student == null) {
            System.out.println("Aucun étudiant sélectionné. Moyenne non calculée.");
            return;
        }
        System.out.println("Moyenne des notes etudiant "+student.getName()+" : "+studentService.calculateAVGStudent(student));
    }

    public void addCoursesToStudent() {
        System.out.println("\n===== AJOUT DE COURS À UN ÉTUDIANT =====");

        Student foundStudent = getStudentbyId();

        if (foundStudent == null) {
            System.out.println("⚠️ Aucun étudiant trouvé avec cet ID !");
            return;
        }

        List<Course> newCourses = new ArrayList<>();
        String choice;

        while (true) {
            System.out.print("\nSouhaitez-vous ajouter un cours à cet étudiant ? (o/n) : ");
            choice = sc.nextLine().trim().toLowerCase();

            if (choice.equals("n")) {
                System.out.println("ℹ️ Aucun cours ajouté à l'étudiant : " + foundStudent.getName());
                return;
            } else if (!choice.equals("o")) {
                System.out.println("❌ Réponse invalide ! Tapez 'o' pour oui ou 'n' pour non.");
                continue;
            } else {
                break;
            }
        }

        do {
            String courseName;
            while (true) {
                System.out.print("\nEntrez le nom du cours : ");
                courseName = sc.nextLine().trim();
                if (courseName.isEmpty()) {
                    System.out.println("❌ Le nom du cours ne peut pas être vide !");
                } else {
                    break;
                }
            }

            double grade = -1;
            while (true) {
                System.out.print("Entrez la note du cours (0-20) : ");
                String input = sc.nextLine().trim();
                try {
                    grade = Double.parseDouble(input);
                    if (grade >= 0 && grade <= 20) {
                        break;
                    } else {
                        System.out.println("⚠️ La note doit être comprise entre 0 et 20 !");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("❌ Erreur : veuillez entrer un nombre valide !");
                }
            }

            newCourses.add(new Course(courseName, grade));

            System.out.print("Voulez-vous ajouter un autre cours ? (o/n) : ");
            choice = sc.nextLine().trim().toLowerCase();

        } while (choice.equals("o"));

        // Enregistrement final
        if (!newCourses.isEmpty()) {
            studentService.addCourseToStudent(foundStudent, newCourses);
            System.out.println("\n✅ Les cours ont été ajoutés avec succès à l'étudiant : " + foundStudent.getName());
        } else {
            System.out.println("\nℹ️ Aucun cours n’a été ajouté.");
        }
    }


    public void bestStudent(){
        Student bestStudent=studentService.bestStudent();
        System.out.println("Meilleur étudiant : " + (bestStudent != null ? bestStudent.getName() : "aucun") + " avec moyenne = " + bestStudent);
    }

    public void failingStudents() {
        List<Student> failing = studentService.failingStudents();

        if (failing.isEmpty()) {
            System.out.println("Aucun étudiant en échec");
        } else {
            System.out.println("Liste des étudiants en échec :");
            for (Student s : failing) {
                System.out.println("- " + s.getName() + " | Moyenne : " + s.calculateAverage());
            }
        }
    }


}
