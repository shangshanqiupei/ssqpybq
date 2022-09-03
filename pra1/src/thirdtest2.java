import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
class student{
    String name;
    int number;
    int age;
    String gender;
    int grade;
    public student(String gender, int grade,String name,int age)//
    {
        this.gender= gender;
        this.grade = grade;
        this.name = name;
        this.age = age;
    }
    public String information()//
    {
        return "\t年级："+grade+"\t姓名："+name+"\t年龄"+age;
    }
}

public class thirdtest2 {
    public static void main(String[] args) throws IOException{
        student []st = new student[2];
        student s = new student("男",2,"xx1",24);
        st[0] = s ;
        student s1 = new student("女",2,"xx2",21);
        st[1] = s1;
        File file =new File("Student.txt");
        WriteFile(file, st);
        ReadFile(file);

    }

    public static void WriteFile(File file, student[] stu) throws IOException {
        FileWriter fw =new FileWriter(file,false);
        BufferedWriter bw =new BufferedWriter(fw);
        for(student s : stu)
        {
            bw.write(s.information());
            bw.newLine();
        }
        bw.close();
        fw.close();

    }
    public static void ReadFile(File file) throws IOException{
        FileReader fr =new FileReader(file);
        BufferedReader br =new BufferedReader(fr);
        String sin;
        while((sin = br.readLine())!= null)
        {
            System.out.printf(sin);
        }
        br.close();
        fr.close();
    }

}


