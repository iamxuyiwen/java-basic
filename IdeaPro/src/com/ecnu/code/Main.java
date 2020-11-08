package com.ecnu.code;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

class Student{
    private String sid; // 学号
    private String name; // 姓名
    private int age; // 年龄
    private String birthday; // 生日

    public Student() {}

    public Student(String sid, String name, int age, String birthday) {
        this.sid = sid;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return this.sid + "\t\t" + this.name + "\t" + this.age + "\t\t" + this.birthday;
    }
}

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();

        outer:
        while(true){
            System.out.println("--------欢迎来到学生管理系统--------");
            System.out.println("1 添加学生");
            System.out.println("2 删除学生");
            System.out.println("3 修改学生");
            System.out.println("4 查看学生");
            System.out.println("5 退出");
            System.out.println("请输入您的选择:");

            String choice = sc.next();

            switch (choice) {
                case "1":
                    //System.out.println("添加学生");
                    addStudent(list);
                    break;
                case "2":
                    //System.out.println("删除学生");
                    deleteStudent(list);
                    break;
                case "3":
                    //System.out.println("修改学生");
                    updateStudent(list);
                    break;
                case "4":
                    // System.out.println("查看学生");
                    queryStudents(list);
                    break;
                case "5":
                    System.out.println("感谢您的使用");
                    break outer;
                default:
                    System.out.println("您的输入有误");
                    break;
            }
        }
    }

    private static void queryStudents(ArrayList<Student> list) {
        // 1. 判断集合中是否存在数据, 如果不存在直接给出提示
        if(list.size() == 0){
            System.out.println("无信息, 请添加后重新查询");
            return;
        }

        System.out.println("学号\t姓名\t年龄\t\t生日");;
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            System.out.println(stu);
        }
    }

    private static void updateStudent(ArrayList<Student> list) {
        System.out.println("请输入您要修改的学生学号:");
        Scanner sc = new Scanner(System.in);
        String updateSid = sc.next();
        // 3. 调用getIndex方法, 查找该学号在集合中出现的索引位置
        int index = getIndex(list,updateSid);
        // 4. 根据索引判断, 学号在集合中是否存在
        if(index == -1){
            // 不存在: 给出提示
            System.out.println("查无信息, 请重新输入");
        }else{
            // 存在: 接收新的学生信息
            System.out.println("请输入新的学生姓名:");
            String name = sc.next();
            System.out.println("请输入新的学生年龄:");
            int age = sc.nextInt();
            System.out.println("请输入新的学生生日:");
            String birthday = sc.next();
            // 封装为新的学生对象
            Student stu = new Student(updateSid, name, age, birthday);
            list.set(index, stu);
            System.out.println("修改成功!");
        }
    }

    private static void deleteStudent(ArrayList<Student> list) {
        // 1. 给出提示信息 (请输入您要删除的学号)
        System.out.println("请输入您要删除的学生学号:");
        // 2. 键盘接收要删除的学号
        Scanner sc = new Scanner(System.in);
        String deleteSid = sc.next();
        // 3. 调用getIndex方法, 查找该学号在集合中出现的索引位置
        int index = getIndex(list,deleteSid);
        if(index == -1){
            // 不存在: 给出提示
            System.out.println("查无信息, 请重新输入");
        }else{
            list.remove(index);
            System.out.println("删除成功!");
        }
    }

    private static void addStudent(ArrayList<Student> list) {
        String sid;
        while (true){
            System.out.println("请输入学号:");
            sid = sc.next();
            int index = getIndex(list, sid);
            if(index == -1){
                break;
            }
        }
        System.out.println("请输入姓名:");
        String name = sc.next();
        System.out.println("请输入年龄:");
        int age = sc.nextInt();
        System.out.println("请输入生日:");
        String birthday = sc.next();
        // 2. 将键盘录入的信息封装为学生对象
        Student stu = new Student(sid,name,age,birthday);
        list.add(stu);
        System.out.println("添加成功!");
    }

    private static int getIndex(ArrayList<Student> list, String sid) {
        String id;
        for (int i = 0; i < list.size(); i++) {
            id = list.get(i).getSid();
            if(id.equals(sid))
                return i;
        }
        return -1;
    }
}
