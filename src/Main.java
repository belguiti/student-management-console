import com.team.studentapp.model.Student;
import com.team.studentapp.service.FileService;
import com.team.studentapp.service.StudentService;
import com.team.studentapp.view.ConsoleMenu;

import java.util.ArrayList;
import java.util.List;


import com.team.studentapp.controller.StudentController;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        StudentController c=new StudentController();
        c.calculateAVGStudent();

    }
}
