package com.example.itzpulu.assignment_3;

/**
 * Created by iTz Pulu on 10/2/2016.
 */
public class Student {
    int roll_no,semester;
    String name;
    public Student(){}
    public Student(int roll_no,String name,int semester)
    {
        this.roll_no=roll_no;
        this.name=name;
        this.semester=semester;
    }
    public int getRoll_no()
    {
        return this.roll_no;
    }
    public int getSemester()
    {
        return this.semester;
    }
    public String getName(){
        return this.name;
    }
    public void setRoll_no(int roll_no)
    {
        this.roll_no=roll_no;
    }
    public void setSemester(int semester)
    {
        this.semester=semester;
    }
    public void setName(String name)
    {
        this.name=name;
    }
}
