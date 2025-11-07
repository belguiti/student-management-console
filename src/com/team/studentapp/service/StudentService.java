package com.team.studentapp.service;

import com.team.studentapp.model.Course;
import com.team.studentapp.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentService {
    private List<Student>students=new ArrayList<>();
    private FileService fileService=FileService.getInstance();

    public StudentService(){
        students=fileService.loadStudents();
    }

    public void addStudent(Student student){
        students.add(student);
        fileService.saveStudent(student); // save in file
    }

    public List<Student>getAllStudents(){
       return  students;
    }

    public Student getStudentbyId(int id){

        for (Student student:students){
            if (student.getId()==id){
                return student;
            }
        }
        return null;
    }

    public void updateStudent(Scanner sc, int id) {
        Student s = getStudentbyId(id);

        if (s == null) {
            System.out.println("⚠️ Aucun étudiant trouvé avec cet ID !");
            return;
        }

        System.out.println("✅ Étudiant trouvé : " + s);

        // Boucle pour saisir un nouveau nom valide ou laisser inchangé
        while (true) {
            System.out.print("Entrez le nouveau nom (laisser vide pour ne pas changer) : ");
            String newName = sc.nextLine().trim();

            if (newName.isEmpty()) {
                System.out.println("ℹ️ Nom inchangé.");
                break;
            } else if (newName.length() < 2) {
                System.out.println("❌ Le nom doit contenir au moins 2 caractères.");
            } else {
                s.setName(newName);
                System.out.println("✅ Nom mis à jour !");
                break;
            }
        }

        // Boucle pour saisir un nouvel email valide ou laisser inchangé
        while (true) {
            System.out.print("Entrez le nouvel email (laisser vide pour ne pas changer) : ");
            String newEmail = sc.nextLine().trim();

            if (newEmail.isEmpty()) {
                System.out.println("ℹ️ Email inchangé.");
                break;
            } else if (!newEmail.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                System.out.println("❌ Email invalide ! Exemple : exemple@mail.com.");
            } else {
                s.setEmail(newEmail);
                System.out.println("✅ Email mis à jour !");
                break;
            }
        }

        // Sauvegarde avec gestion des erreurs
        try {
            fileService.updateStudent(s);
            System.out.println("✅ Étudiant mis à jour avec succès !");
        } catch (Exception e) {
            System.out.println("⚠️ Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    public boolean deleteStudent(int id) {
        try {
            Student student = getStudentbyId(id);

            if (student != null) {
                students.remove(student);
                try {
                    fileService.deleteStudent(id); // Supprime dans le fichier CSV
                } catch (Exception e) {
                    System.out.println("⚠️ Erreur lors de la suppression dans le fichier : " + e.getMessage());
                }
                System.out.println("✅ Étudiant supprimé avec succès !");
                return true;
            } else {
                System.out.println("⚠️ Aucun étudiant trouvé avec cet ID.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("❌ Une erreur inattendue est survenue : " + e.getMessage());
            return false;
        }
    }

    public void addCourseToStudent(Student student,List<Course> newCourses){
        try {
            List<Course> existingCourses = student.getCourses();
            if (existingCourses == null) {
                existingCourses = new ArrayList<>();
            }
            existingCourses.addAll(newCourses); // ajouter les nouveau courser a les enciens courses
            student.setCourses(existingCourses);
            student.setCourses(existingCourses);
            fileService.updateStudent(student);
        }catch (Exception e){
            System.err.println("Erreur addCourseToStudent :"+e.getMessage());
        }
    }

    public double calculateAVGStudent(Student student){
        try {
            if(student.getCourses().isEmpty()) {
                System.out.println("No courses pour etudient : " + student.getName());
                return 0;
            }
            return student.calculateAverage();
        } catch (Exception e) {
            System.err.println("Erreur calculateAVGStudent : "+e.getMessage());
            return 0;
        }
    }

    public Student bestStudent() {

        if (students == null || students.isEmpty()) {
            System.out.println("Aucun étudiant disponible");
            return null;
        }

        Student best = null;
        double bestAvg = -1; // On part de -1 pour gérer le cas où toutes les moyennes sont 0

        for (Student s : students) {
            double avg = calculateAVGStudent(s);
            if (avg > bestAvg) {
                bestAvg = avg;
                best = s;
            }
        }

        return best;
    }

    public List<Student> failingStudents() {
        return students.stream()
                .filter(student -> student.calculateAverage() < 10)
                .toList();
    }


}
