package com.team.studentapp.service;

import com.team.studentapp.model.Student;
import java.util.List;

public interface FileServiceInt { 

    //  Sauvegarde un seul étudiant ---
    void saveStudent(Student student);

    //  Sauvegarde plusieurs étudiants (ajout à la fin du fichier) ---
    void saveStudents(List<Student> students);

    //  Sauvegarde plusieurs étudiants (choix d'ajouter ou réécrire le fichier) ---
    void saveStudents(List<Student> students, boolean append);

    //  Lecture du fichier CSV et reconstruction des étudiants ---
    List<Student> loadStudents();

    //  Met à jour un étudiant existant ---
    void updateStudent(Student student);

    //  Supprime un étudiant par ID ---
    void deleteStudent(int idStudent);
}
