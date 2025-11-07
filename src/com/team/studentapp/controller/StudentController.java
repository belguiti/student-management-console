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
        System.out.print("Entrez l'Id de l'étudiant : ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Entrez le nom : ");
        String name = sc.nextLine();
        System.out.print("Entrez l'email : ");
        String email = sc.nextLine();

        Student student = new Student(id, name, email);
        studentService.addStudent(student);
        System.out.println("Étudiant ajouté avec succès !");
    }
    public void showAllStudents(){
        System.out.println("\n Liste des etudiants");
        for(Student s: studentService.getAllStudents()){
            System.out.println(s);
        }
    }
    public void updateStudent(){
        System.out.println("\n Entrez l'id de l'etudiant");
        int id=Integer.parseInt(sc.nextLine());
        studentService.updateStudent(sc,id);

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

    public void deleteStudent(){
        System.out.println("\n Entrez l'id de l'etudiant a supprimer");
        int id=Integer.parseInt(sc.nextLine());
        studentService.deleteStudent(id);
    }

    public void calculateAVGStudent(){
        Student student=getStudentbyId();
        if (student == null) {
            System.out.println("Aucun étudiant sélectionné. Moyenne non calculée.");
            return;
        }
        System.out.println("Moyenne des notes etudiant "+student.getName()+" : "+studentService.calculateAverage(student));
    }

    public void addCoursesToStudent() {
        System.out.println("\n===== AJOUT DE COURS À UN ÉTUDIANT =====");

        Student foundStudent=getStudentbyId();

        if(foundStudent==null)
            return;

        // --- Saisie des cours ---
        List<Course> newCourses = new ArrayList<>();
        String choice;

        do {
            System.out.print("\n Entrez le nom du cours : ");
            String courseName = sc.nextLine();

            double grade = -1;
            //  Validation de la note (entre 0 et 20)
            while (true) {
                System.out.print(" Entrez la note du cours (0-20) : ");
                if (sc.hasNextDouble()) {
                    grade = sc.nextDouble();
                    sc.nextLine();
                    if (grade >= 0 && grade <= 20) {
                        break;
                    } else {
                        System.out.println(" La note doit être comprise entre 0 et 20 !");
                    }
                } else {
                    System.out.println(" Veuillez entrer une valeur numérique valide !");
                    sc.next();
                }
            }

            newCourses.add(new Course(courseName, grade));

            System.out.print("Voulez-vous ajouter un autre cours ? (o/n) : ");
            choice = sc.nextLine().trim().toLowerCase();

        } while (choice.equals("o"));

        // --- Enregistrement ---
        studentService.addCourseToStudent(foundStudent, newCourses);
        System.out.println("\n Les cours ont été ajoutés avec succès à l'étudiant : " + foundStudent.getName());
    }
    public void showAverage() {
        System.out.print("\n➡️ Entrez l'ID de l'étudiant : ");
        int id = Integer.parseInt(sc.nextLine());


        Student student = studentService.getStudentbyId(id);

        if (student == null) {
            System.out.println("❌ Aucun étudiant trouvé avec cet ID.");
            return;
        }


        double average = studentService.calculateAverage(student);

        System.out.println("La moyenne de " + student.getName() + " est : " + String.format("%.2f", average));
    }
    public void displayAllAverages() {
        System.out.println("\n");
        System.out.println("LISTE DES MOYENNES DES ÉTUDIANTS");
        System.out.println("==============================");

        studentService.displayAllAverages();

        System.out.println("==============================");

    }

    // 🔹 Afficher le meilleur étudiant
    public void displayBestStudent() {
        System.out.println("\n");
        System.out.println("Meilleur étudiant");
        System.out.println("==============================");

        studentService.displayBestStudent();

        System.out.println("==============================");

    }

    // 🔹 Afficher les étudiants en échec (moyenne < 10)
    public void displayFailingStudents() {
        System.out.println("\n==============================");
        System.out.println(" Étudiants en échec  (moyenne < 10)");
        System.out.println("==============================");

        studentService.displayFailingStudents();

        System.out.println("==============================");
    }



}
