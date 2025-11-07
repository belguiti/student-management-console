package com.team.studentapp.service;

import com.team.studentapp.model.Course;
import com.team.studentapp.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileService implements  FileServiceInt {

    private String filePath;

    private static FileService instance;

    private FileService() {
        this.filePath = "resources/students.csv";
    }

    //synchronized pour garanti qu'en cas d'accès simultané par plusieurs threads,
    // une seule instance de FileService est créée (thread-safe)
    public static synchronized FileService getInstance() {
        if (instance == null) {
            instance = new FileService();
        }
        return instance;
    }


    //  Sauvegarde un seul étudiant 
    public void saveStudent(Student student) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { // true = append
            writer.write(studentToCsv(student));
            writer.newLine();
            System.out.println("Étudiant sauvegardé dans : " + filePath);
        } catch (IOException e) {
            System.err.println("Erreur de sauvegarde : " + e.getMessage());
        }
    }

    //  Sauvegarde plusieurs étudiants 
    // Si append = true → ajoute à la fin du fichier
    // Si append = false → réécrit tout le fichier
    public void saveStudents(List<Student> students,boolean append) { // réécrit tout le fichier append=false
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,append))) {
            for (Student s : students) {
                writer.write(studentToCsv(s));
                writer.newLine();
            }
            System.out.println(" Liste d'étudiants sauvegardée dans : " + filePath);
        } catch (IOException e) {
            System.err.println(" Erreur lors de l'enregistrement : " + e.getMessage());
        }
    }
    public void saveStudents(List<Student> students){ // ajoute à la fin du fichier
        saveStudents(students,true);
    }

    //  Convertit un étudiant en ligne CSV 
    private String studentToCsv(Student student) {
        // Format : id,name,email,course1:grade1;course2:grade2
        StringBuilder sb = new StringBuilder();
        sb.append(student.getId()).append(",");
        sb.append(student.getName()).append(",");
        sb.append(student.getEmail()).append(",");

        List<Course> courses = student.getCourses();
        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);
            sb.append(c.getName()).append(":").append(c.getGrade());
            if (i < courses.size() - 1) sb.append(";");
        }

        return sb.toString();
    }

    //  Lecture du fichier CSV et reconstruction des étudiants 
    public List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                students.add(csvToStudent(line));
            }
        } catch (IOException e) {
            System.err.println("❌ Erreur de lecture du fichier : " + e.getMessage());
        }

        return students;
    }

    private Student csvToStudent(String line) {
        // Format attendu : id,name,email,course1:grade1;course2:grade2
        if (line == null || line.trim().isEmpty()) {
            System.out.println("⚠️ Ligne vide ignorée.");
            return null;
        }

        // Séparer les 4 premiers champs seulement (pour ne pas couper les cours)
        String[] parts = line.split(",", 4);
       // System.out.println("parts : " + Arrays.toString(parts)); // ✅ affichage lisible

        if (parts.length < 3) {
            System.out.println("❌ Ligne invalide : " + line);
            return null;
        }

        int id;
        try {
            id = Integer.parseInt(parts[0].trim());
        } catch (NumberFormatException e) {
            System.out.println("⚠️ ID invalide dans la ligne : " + line);
            id = 0;
        }

        String name = parts[1].trim();
        String email = parts[2].trim();

        List<Course> courses = new ArrayList<>();

        // Vérifier s'il y a des cours dans le 4e champ
        if (parts.length == 4 && !parts[3].trim().isEmpty()) {
            String[] courseParts = parts[3].split(";");
            for (String c : courseParts) {
                String[] cg = c.split(":");
                if (cg.length == 2) {
                    String courseName = cg[0].trim();
                    double grade;
                    try {
                        grade = Double.parseDouble(cg[1].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Note invalide pour le cours '" + courseName + "', valeur par défaut -1.");
                        grade = -1;
                    }
                    courses.add(new Course(courseName, grade));
                } else {
                    System.out.println("⚠️ Format de cours invalide : " + c);
                }
            }
        }

        // Créer l'objet étudiant
        Student s = new Student(id, name, email);
        s.setCourses(courses);

        return s;
    }

    public void updateStudent(Student student){
        try {
            List<Student> existingStudent=loadStudents();
            boolean isExist=false;
            for(Student s:existingStudent) {
                if (s.getId() == student.getId()){
                    s.setName(student.getName());
                    s.setEmail(student.getEmail());
                    s.setCourses(student.getCourses());
                    isExist=true;
                }
            }
            if(isExist){
                saveStudents(existingStudent,false);
                System.out.println("Student Exist");
            }
        }catch (Exception e){
            System.out.println("Erreur update");
        }
    }

    public void deleteStudent(int idStudent){
        try {
            List<Student> existingStudent=loadStudents();
            boolean isRemoved=existingStudent.removeIf(s -> s.getId()==idStudent);
            if(isRemoved){
                saveStudents(existingStudent,false);
                System.out.println("Student Exist");
            }
        }catch (Exception e){
            System.out.println("Erreur update");
        }
    }
}
