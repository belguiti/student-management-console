package com.team.studentapp.controller;

import com.team.studentapp.model.Student;
import com.team.studentapp.service.StudentService;

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
    public void getStudentbyId(){
        System.out.println("\n Entrez l'id de l'etudiant");

    }
    public void deleteStudent(){
        System.out.println("\n Entrez l'id de l'etudiant a supprimer");
        int id=Integer.parseInt(sc.nextLine());
        studentService.deleteStudent(id);
    }


}
