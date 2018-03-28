package com.sch.igor;

import java.io.*;

public class SaveLoader {
    private String sName = new String();
    private String sAge = new String();
    private String sSex = new String();
    private String sNumCertificate = new String();
    private String sAverageScore = new String();
    private StringBuffer result = new StringBuffer();

    public SaveLoader(String sName, String sAge, String sSex, String sNumCertificate, String sAverageScore, StringBuffer result) {
        this.sName = sName;
        this.sAge = sAge;
        this.sSex = sSex;
        this.sNumCertificate = sNumCertificate;
        this.sAverageScore = sAverageScore;
        this.result = result;
    }
    public SaveLoader() {

    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsAge() {
        return sAge;
    }

    public void setsAge(String sAge) {
        this.sAge = sAge;
    }

    public String getsSex() {
        return sSex;
    }

    public void setsSex(String sSex) {
        this.sSex = sSex;
    }

    public String getsNumCertificate() {
        return sNumCertificate;
    }

    public void setsNumCertificate(String sNumCertificate) {
        this.sNumCertificate = sNumCertificate;
    }

    public String getsAverageScore() {
        return sAverageScore;
    }

    public void setsAverageScore(String sAverageScore) {
        this.sAverageScore = sAverageScore;
    }

    public StringBuffer getResult() {
        return result;
    }

    public void setResult(StringBuffer result) {
        this.result = result;
    }

    /*Загружаем группу из файла
            Считываем все строки, разбиваем её на массив char и идем по массиву, отбирая все значение.
            Понимаю что можно было решить задачу намного проще (через регулярки), но решил помозговать и написать такой себе парсер
            */
    public void loadWithFile(File file, Group group) {
        try (BufferedReader out = new BufferedReader(new
                FileReader(file))) {
            String str;
            while ((str = out.readLine()) != null) {
                result.append(str);
            }
            char[] res = new char[result.length()];
            result.getChars(0, result.length(), res, 0);

            int next = 0;
            for (int k = 0; k < res.length; k++) {
                String s = Character.toString(res[k]);
                if (s.equals(";")) {
                    if (next >= 0 && next <= 4) {
                        next++;
                    }
                    if (next == 5) {
                        next = 0;
                        transferStudent(group);
                        getNullStr();
                    }
                } else {
                    if (next >= 0 && next <= 4) {
                        getValueStr(next, k, res);
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


    //Сохраняем группу в файл
    public void addToFile(File file, Student group[]) {
        try (PrintWriter writer = new PrintWriter(file)) {
            for (int k = 0; k < group.length; k++) {
                if (group[k] != null) {
                    writer.println(group[k].toString());
                }
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    // Передаем студента в группу
    private void transferStudent(Group group) {
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
        group.addStudent(student);
    }

    //Задаем переменным пустые значения
    private void getNullStr() {
        sName = "";
        sAge = "";
        sSex = "";
        sNumCertificate = "";
        sAverageScore = "";
    }

    //Задаем значение переменной в зависимости от полученной инструкции
    private void getValueStr(int next, int k, char[] res) {
        if (next == 0) {
            sName += "" + res[k];
        }
        if (next == 1) {
            sAge += "" + res[k];
        }
        if (next == 2) {
            sSex += "" + res[k];
        }
        if (next == 3) {
            sNumCertificate += "" + res[k];
        }
        if (next == 4) {
            sAverageScore += "" + res[k];
        }
    }
}



