package com.sch.igor;

import java.util.Arrays;
/*Методы для работы с массивами:
* Сортировка
* Удаление null
* Проверка количества студентов*/

public class SortAndWorkWithArray {
    //Метод сортировки, даем массив и опцию. Возвращаем отсортированный массив
    public static Student[] sort(Student[] students, int opt) {
        if (opt == 1) { //по имени
            Arrays.sort(students, (first, second) -> CheckNull.checkNull(first, second) != CheckNull.NOT_NULL ?
                    CheckNull.checkNull(first, second) : first.getName().compareTo(second.getName()));
        } else if (opt == 2) { //по возрасту (по возрастанию)
            Arrays.sort(students, (first, second) -> CheckNull.checkNull(first, second) != CheckNull.NOT_NULL ?
                    CheckNull.checkNull(first, second) : (first.getAge() - second.getAge()));
        } else if (opt == 3) { //по успеваемости (по убыванию)
            Arrays.sort(students, (first, second) -> CheckNull.checkNull(first, second) != CheckNull.NOT_NULL ?
                    CheckNull.checkNull(first, second) : (-1) * Double.compare(first.getAverageScore(), second.getAverageScore()));
        }
        return students;
    }

    //Удаляем правые null из списка
    public static Student[] deleteRightNull(Student[] students) {
        int k = checkAmountStudents(students);
        Student[] newGroup = new Student[k];
        Student[] sort = sort(students, 1);
        System.arraycopy(students, 0, newGroup, 0, k);
        return newGroup;
    }

    //Проверяет количество студентов в списке.
    public static int checkAmountStudents(Student[] students) {
        int amountStudents = 0;
        for (int k = 0; k < students.length; k++) {
            if (students[k] != null) {
                amountStudents++;
            }
        }
        return amountStudents;
    }
}
