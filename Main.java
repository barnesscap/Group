package com.sch.igor;

import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        //ManualInput();
        AutoInput();


    }

    public static void ManualInput() {

        Student stOne = new Student("Bob", Sex.MALE, 17, 67026912, 3);
        Student stTwo = new Student("Mike", Sex.MALE, 22, 67026912, 4.2);
        Student stThree = new Student("Anna", Sex.FEMALE, 24, 68314223, 4.3);
        Student stFour = new Student("Bill", Sex.MALE, 19, 67005321, 2.6);
        Student stFive = new Student("Monica", Sex.FEMALE, 18, 67042121, 2.9);
        Student stSix = new Student("Alex", Sex.MALE, 22, 69041234, 3.4);
        Student stSeven = new Student("Rose", Sex.FEMALE, 17, 66023313, 4.7);
        Student stEight = new Student("Logan", Sex.MALE, 16, 66708412, 4);
        Student stNight = new Student("Kate", Sex.FEMALE, 18, 67042132, 2.8);
        Student stTen = new Student("Pol", Sex.MALE, 22, 60212345, 3.3);
        Student stEleven = new Student("Diana", Sex.FEMALE, 21, 67084214, 4.1);
        Student stTwelve = new Student("Bill", Sex.MALE, 19, 670742127, 3.7);

        Group groupOne = new Group();

        groupOne.addStudent(null);
        groupOne.addStudent(stTwo);
        groupOne.addStudent(stThree);
        groupOne.addStudent(stFour);
        groupOne.addStudent(stFive);
        groupOne.addStudent(stSix);
        groupOne.addStudent(stSeven);
        groupOne.addStudent(stEight);
        groupOne.addStudent(stNight);
        groupOne.addStudent(stTen);
        groupOne.addStudent(stEleven);
        groupOne.addStudent(stTwelve);

        //Проверяем удаление по номеру аттестата
        System.out.println(groupOne.toString());
        groupOne.delStudent(68314223);
        System.out.println(groupOne.toString());
        System.out.println();

        //Проверяем поиск студента по номеру аттестата
        System.out.println();
        System.out.println("Найденный студент - " + groupOne.searchStudent(67042121));

        //Проверяем сортировку разными способами
        System.out.println("Сортировка по имени:" + "\n" + Arrays.toString(SortAndWorkWithArray.sort(groupOne.getGroup(), 1)));
        System.out.println();
        System.out.println("Сортировка по возрасту:" + "\n" + Arrays.toString(SortAndWorkWithArray.sort(groupOne.getGroup(), 2)));
        System.out.println();
        System.out.println("Сортировка по оценке:" + "\n" + Arrays.toString(SortAndWorkWithArray.sort(groupOne.getGroup(), 3)));

        /*Проверяем отбор студентов для военкомата по возрасту, полу и специальности
         (только те, кто 18+, имеют средний бал ниже 3.5 и мужчины) */
        System.out.println();
        System.out.println("Список призывников:" + "\n" + Arrays.toString(groupOne.draftStudents()));

        File file = new File("D:\\HomeWorkOOP\\IO0_003\\Group.txt") ;
        groupOne.addToFile(file);
    }

    public static void AutoInput() {
        Group groupOne = new Group();
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите команду:");
            System.out.println(" add - создать и добавить студента в список");
            System.out.println(" del - удалить студента из списка по номеру аттестата");
            System.out.println(" search - найти студента в списке");
            System.out.println(" sort - сортировать список по параметрам");
            System.out.println(" print - напечатать список студентов");
            System.out.println(" printA - напечатать список призывников");
            System.out.println(" save - сохранить группу студентов в файл");
            System.out.println(" load - загрузить группу студентов из файла");
            System.out.println(" stop - завершить выполнение программы");

            String value = sc.nextLine();
            if (value.equals("add")) {
                groupOne.inputData();
            }
            if (value.equals("del")) {
                System.out.println(" Введите номер аттестата: ");
                int valueDel = sc.nextInt();
                groupOne.delStudent(valueDel);
            }
            if (value.equals("search")) {
                System.out.println(" Введите номер аттестата: ");
                int valueSearch = sc.nextInt();
                System.out.println();
                System.out.println("Найденный студент - " + groupOne.searchStudent(valueSearch));
            }
            if (value.equals("sort")) {
                System.out.println("Введите команду:");
                System.out.println(" 1- Сортировка по алфавиту");
                System.out.println(" 2- Сортировка по возрасту");
                System.out.println(" 3- Сортировка по успеваемости");

                int valueSort = sc.nextInt();
                System.out.println("Отсортированный список :" + "\n" + Arrays.toString(SortAndWorkWithArray.sort(groupOne.getGroup(), valueSort)));
            }
            if (value.equals("print")) {
                System.out.println(groupOne.toString());
            }
            if (value.equals("printA")) {
                System.out.println("Список призывников:" + "\n" + Arrays.toString(groupOne.draftStudents()));
            }
            if (value.equals("search")) {
                System.out.println(" Введите номер аттестата: ");
                int valueSearch = sc.nextInt();
                System.out.println();
                System.out.println("Найденный студент - " + groupOne.searchStudent(valueSearch));
            }
            if (value.equals("save")) {
                File file = new File("D:\\HomeWorkOOP\\IO0_003\\Group.txt") ;
                groupOne.addToFile(file);
                System.out.println("Файл сохранен в - D:\\HomeWorkOOP\\IO0_003\\Group.txt");
            }
            if (value.equals("load")) {
                System.out.println("Загрузите данные в файл - D:\\HomeWorkOOP\\IO0_003\\GroupAdd.txt и введите ok");
                System.out.println("В формате (обратите внимания на знаки): имя;возраст;пол;номер_аттестата;средний балл;");
                value = sc.nextLine();
                if (value.equals("ok")) {
                    File file = new File("D:\\HomeWorkOOP\\IO0_003\\GroupAdd.txt");
                    groupOne.loadWithFile(file);
                    System.out.println("Список студентов загружен!");
                }
            }
            if (value.equals("stop")) {
                System.out.println("Программа выполнена успешно");
                break;

            }
        }
    }
}
