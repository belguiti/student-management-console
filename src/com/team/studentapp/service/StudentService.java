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

    public void addStudent(Student student) {
        Scanner sc = new Scanner(System.in);
        students.add(student);
        fileService.saveStudent(student);
        System.out.println(" Étudiant ajouté avec succès !");


        System.out.print("Souhaitez-vous ajouter des cours pour cet étudiant ? (oui/non) : ");
        String reponse = sc.nextLine().trim().toLowerCase();

        if (reponse.equals("o")) {
            List<Course> newCourses = new ArrayList<>();

            while (true) {
                System.out.print("Entrez le nom du cours : ");
                String courseName = sc.nextLine();

                System.out.print("Entrez la note du cours : ");
                double grade;
                try {
                    grade = Double.parseDouble(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Note invalide, valeur par défaut 0 attribuée.");
                    grade = 0.0;
                }

                newCourses.add(new Course(courseName, grade));

                System.out.print("Voulez-vous ajouter un autre cours ? (oui/non) : ");
                String more = sc.nextLine().trim().toLowerCase();
                if (!more.equals("o")) break;
            }


            addCourseToStudent(student, newCourses);
            System.out.println("Cours ajoutés avec succès !");
        }
    }


    public List<Student> getAllStudents() {
        students = fileService.loadStudents();
        return students;
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

        if (s != null) {
            System.out.println(" Etudiant trouvé : " + s);
            System.out.print("Entrez le nouveau nom  : ");
            String newName = sc.nextLine();
            if (!newName.isEmpty()) {
                s.setName(newName);
            }

            System.out.print("Entrez le nouvel email  : ");
            String newEmail = sc.nextLine();
            if (!newEmail.isEmpty()) {
                s.setEmail(newEmail);
            }

            fileService.updateStudent(s);
            System.out.println(" Étudiant mis à jour avec succès !");
        } else {
            System.out.println("Aucun étudiant trouvé avec cet ID !");
        }
    }

    public boolean deleteStudent(int id){
         Student   student=getStudentbyId(id);
         if(student!=null){
              students.remove(student);
              fileService.deleteStudent(id);
              System.out.println("Etudiant supprimer avec succes ");
              return true;
         }
        System.out.println("Aucun etudiant a supprimmer ");
         return false;
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

    public double calculateAverage(Student student) {
        if (student.getCourses() == null || student.getCourses().isEmpty()) {
            return 0.0;
        }

        double sum = 0;
        for (Course c : student.getCourses()) {
            sum += c.getGrade();
        }
        return sum / student.getCourses().size();
    }

    public void displayAllAverages() {
        System.out.println("\n📊 Moyenne de chaque étudiant :");
        for (Student s : students) {
            double avg = calculateAverage(s);
            System.out.printf(" - %s : %.2f / 20%n", s.getName(), avg);
        }
    }

    // 🔹 Trouver et afficher le meilleur étudiant
    public void displayBestStudent() {
        if (students.isEmpty()) {
            System.out.println("Aucun étudiant enregistré ");
            return;
        }

        Student best = null;
        double bestAverage = -1;

        for (Student s : students) {
            double avg = calculateAverage(s);
            if (avg > bestAverage) {
                bestAverage = avg;
                best = s;
            }
        }


        if (best != null) {
            System.out.printf("\n Meilleur étudiant : %s (%.2f / 20)%n",
                    best.getName(), bestAverage);
        }
    }

    // 🔹 Afficher les étudiants en échec (moyenne < 10)
    public void displayFailingStudents() {
        System.out.println("\nEtudiants en échec (moyenne < 10) :");
        boolean found = false;

        for (Student s : students) {
            double avg = calculateAverage(s);
            if (avg < 10) {
                System.out.printf(" - %s (%.2f / 20)%n", s.getName(), avg);
                found = true;
            }
        }

        if (!found) {
            System.out.println("✅ Aucun étudiant en échec !");
        }
    }


}
