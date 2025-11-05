import com.team.studentapp.model.Student;
import com.team.studentapp.service.FileService;
import com.team.studentapp.service.StudentService;
import com.team.studentapp.view.ConsoleMenu;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        FileService s=new FileService("resources/students.csv");
        List <Student> students=s.loadStudents();
//        for(int i=0;i<3;i++)
//            students.add(new Student(i,"name"+i,"email"+i));
//        s.saveStudents(students);
        System.out.println(students);

    }
}