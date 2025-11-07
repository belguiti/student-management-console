package com.team.studentapp.model;

import java.util.Objects;

/**
 * Author : BELGUITI
 * Représente un cours suivi par un étudiant.
 * Chaque cours a un nom et une note (entre 0 et 20).
 */

public class Course {
    private String name;
    private double grade;

    // Constructeur
    public Course(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    // Constructeur vide (utile pour lecture de fichier ou JSON)
    public Course() {
    }

    // Getters et Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
    public void setGradetest(double grade) {
        this.grade = grade;
    }

    // Affichage formaté (ex: Math: 15.0)
    @Override
    public String toString() {
        return name + ": " + grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Double.compare(course.grade, grade) == 0 && Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, grade);
    }
}
