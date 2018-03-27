package com.sch.igor;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

public class Group implements Draft {
    private Student[] group = new Student[10];

    public Group(Student[] group) {

    }

    public Group() {

    }

    public Student[] getGroup() {
        return group;
    }


    public void setGroup(Student[] group) {
        this.group = group;
    }

    //Добавляем студентов в группу
    public void addStudent(Student stud) {
        try {
            if (stud == null) { //Если студент равен null, кидаем исключение.
                throw new NullStudentException();
            }
            if (SortAndWorkWithArray.checkAmountStudents(group) != 10) { //проверяем количество студентов в группе
                if (!checkNumSert(stud)) { //проверяем подлинность аттестата
                    for (int k = 0; k < group.length; k++) {
                        if (group[k] == null) {
                            group[k] = stud;
                            System.out.println(stud.getName() + " - cтудент успешно добавлен в список!");
                            break;
                        }
                    }
                } else {
                    System.out.println(stud.getName() + " - Номер аттестата присутствует в базе! Данный студент уже есть в группе или у Вас поддельный аттестат!");
                }
            } else {
                try {
                    throw new ExceededAmountException(); //При попытке добавить 11-го студента в группу, кидаем исключение.
                } catch (ExceededAmountException ex) {
                    System.out.println("В группе не может быть больше 10-ти человек");
                }
            }
        } catch (NullStudentException e) {
            System.out.println(stud + " Вы передаете null");
        }
    }

    //Удаляем студента по фамилии
    public String delStudent(int numCert) {
        for (int k = 0; k < group.length; k++) {
            if (group[k] != null && numCert == group[k].getNumCertificate()) {
                group[k] = null;
                return numCert + " - cтудент удален из группы";
            }
        }
        return numCert + " - cтудент не найден";
    }

    //Ищем студента в группе по номеру аттестата и возвращаем студента
    public Student searchStudent(int numCert) {
        for (int k = 0; k < group.length; k++) {
            if (group[k] == null) {
                continue;
            } else if (numCert == group[k].getNumCertificate()) {
                return group[k];
            }
        }
        return null;
    }

    //Проверяет есть ли студент в группе по аттестату.
    private boolean checkNumSert(Student student) {
        for (int k = 0; k < group.length; k++) {
            if (student != null) {
                if (group[k] != null) {
                    if (student.getNumCertificate() == group[k].getNumCertificate()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        group = SortAndWorkWithArray.sort(group, 1);
        group = SortAndWorkWithArray.deleteRightNull(group);
        return Arrays.toString(group);
    }

    // Отбор студентов по критериям для военкомата.
    @Override
    public Student[] draftStudents() {
        int i = 0;
        Student[] conscripts = new Student[group.length];
        for (int k = 0; k < group.length; k++) {
            if (group[k] != null && group[k].getAge() >= 18 && group[k].getSex() == Sex.MALE && group[k].getAverageScore() < 3.5) {
                conscripts[i] = group[k];
                i++;
            }
        }
        conscripts = SortAndWorkWithArray.sort(conscripts, 1);
        conscripts = SortAndWorkWithArray.deleteRightNull(conscripts);
        return conscripts;
    }

    //Ввод данных
    public void inputData() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите имя: ");
            String name = sc.nextLine();
            System.out.println("Введите пол (MALE/FEMALE): ");
            Sex sex = Sex.valueOf(Sex.class, sc.next());
            System.out.println("Введите возраст: ");
            int age = sc.nextInt();
            System.out.println("Введите среднюю оценку: ");
            double averageScore = sc.nextDouble();
            System.out.println("Введите номер аттестата: ");
            int numCertificate = sc.nextInt();

            if (SortAndWorkWithArray.checkAmountStudents(group) != 10) {
                Student student = new Student(name, sex, age, numCertificate, averageScore);
                this.addStudent(student);
            } else {
                throw new ExceededAmountException();
            }
        } catch (IllegalArgumentException | InputMismatchException ex) {
            System.out.println("Вы ввели неверное значение! Попробуйте ещё раз!");
        } catch (ExceededAmountException ex) {
            System.out.println("В группе не может быть больше 10-ти человек");
        }
    }
    //Сохраняем группу в файл
    public void addToFile(File file) {
         try (PrintWriter writer=new PrintWriter(file)) {
            for(int k=0; k<group.length;k++) {
                if (group[k]!=null) {
                    writer.println(group[k].toString());
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error");
        }
    }

    /*Загружаем группу из файла
    Считываем все строки, разбиваем её на массив char и идем по массиву, отбирая все значение.
    Понимаю что можно было решить задачу намного проще (через регулярки), но решил помозговать и написать такой себе парсер
    */
    public void loadWithFile(File file) {
        String sName = new String();
        String sAge = new String();
        String sSex = new String();
        String sNumCertificate = new String();
        String sAverageScore = new String();
        StringBuffer  result = new StringBuffer ();
        int next=0;

        try (BufferedReader out = new BufferedReader(new
                FileReader(file))) {
            String str;
            while ((str = out.readLine()) != null) {
                result.append(str);
            }
            char[] res = new char[result.length()];
            result.getChars(0, result.length(), res, 0);

            for (int k = 0; k < res.length; k++) {
                String s = Character.toString(res[k]);
                if (s.equals(";")) {

                    if (next == 0) {
                        next++;
                    }
                    else if (next == 1) {
                        next++;
                    }
                    else if (next == 2) {
                        next++;
                    }
                    else if (next == 3) {
                        next++;
                    }
                    else if (next == 4) {
                        next++;
                    }
                    if (next == 5) {
                        next = 0;
                        String name = sName.toString();
                        Sex sex;
                        //Тут не знал как сделать с Enum, поэтому придумал костыль
                        if (sSex.toString().equals("M")) {
                            sex = Sex.MALE;
                        } else {
                            sex = Sex.FEMALE;
                        }
                        int age = Integer.parseInt(sAge.toString());
                        int numCertificate = Integer.parseInt(sNumCertificate.toString());
                        double averageScore = Double.parseDouble(sAverageScore.toString());

                        Student student = new Student(name, sex, age, numCertificate, averageScore);
                        addStudent(student);
                        sName = "";
                        sAge =  "";
                        sSex =  "";
                        sNumCertificate =  "";
                        sAverageScore =  "";

                    }
                } else {
                    if (next == 0) {
                        sName+=""+res[k];
                    }
                    if (next == 1) {
                        sAge+=""+res[k];
                    }
                    if (next == 2) {
                        sSex+=""+res[k];
                    }
                    if (next == 3) {
                        sNumCertificate+=""+res[k];
                    }
                    if (next == 4) {
                        sAverageScore+=""+res[k];
                    }
                    if (next == 5) {
                        next = 0;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR " + e);
        }
    }
}

