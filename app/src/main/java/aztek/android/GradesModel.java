package aztek.android;

/**
 * Created by Aztek on 03.04.2017.
 */

public class GradesModel
{
    private String name;
    private int grade;

    GradesModel(String name, int grade){
        this.name = name;
        this.grade = grade;
    }

    GradesModel(String name){
        this.name = name;
        this.grade = 2;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setGrade(int grade){
        this.grade = grade;
    }

    public String getName(){
        return name;
    }
    public int getGrade(){
        return grade;
    }
}