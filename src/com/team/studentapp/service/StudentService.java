package com.team.studentapp.service;

import com.team.studentapp.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentService {
    private List<Student>students=new ArrayList<>();
    public void addStudent(Student student){
        students.add(student);
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


           // fileService.saveToFile(students, FILE_NAME);
            System.out.println(" Étudiant mis à jour avec succès !");
        } else {
            System.out.println("Aucun étudiant trouvé avec cet ID !");
        }
    }

    public boolean deleteStudent(int id){
         Student   student=getStudentbyId(id);
         if(student!=null){
              students.remove(student);
              System.out.println("Etudiant supprimer avec succes ");
              return true;
         }
        System.out.println("Aucun etudiant a supprimmer ");
         return false;
    }


}
