package com.team.studentapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Author : BELGUITI
 * Représente un étudiant dans le système.
 * Contient ses informations de base et ses cours avec leurs notes.
 */
public class Student {

    private int id;
    private String name;
    private String email;
    private List<Course> courses; // Liste des cours de l'étudiant

    // Constructeur complet
    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.courses = new ArrayList<>();
    }

    // Constructeur vide (utile pour lecture de fichier ou JSON)
    public Student() {
        this.courses = new ArrayList<>();
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    // Ajouter un cours à la liste
    public void addCourse(Course course) {
        this.courses.add(course);
    }

    // Calculer la moyenne de l'étudiant
    public double calculateAverage() {
        if (courses.isEmpty()) return 0.0;

        double sum = 0;
        for (Course c : courses) {
            sum += c.getGrade();
        }
        return sum / courses.size();
    }

    // Affichage formaté de l'étudiant
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id)
                .append(" | Nom: ").append(name)
                .append(" | Email: ").append(email)
                .append(" | Moyenne: ").append(String.format("%.2f", calculateAverage()))
                .append("\nCours: ");

        if (courses.isEmpty()) {
            sb.append("Aucun cours enregistré.");
        } else {
            for (Course c : courses) {
                sb.append(c.toString()).append("; ");
            }
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && Objects.equals(name, student.name) && Objects.equals(email, student.email) && Objects.equals(courses, student.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, courses);
    }
}