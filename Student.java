package com.sch.igor;

import java.io.Serializable;

public class Student extends  Human implements Serializable {
    private static final long serialVersionUID = 1L;
    private int numCertificate;
    private double averageScore;

    public Student(String name, Sex sex, int age, int numCertificate, double averageScore) {
        super(name, sex, age);
        this.numCertificate = numCertificate;
        this.averageScore = averageScore;
    }

    public Student() {

    }

    public int getNumCertificate() {
        return numCertificate;
    }

    public void setNumCertificate(int numCertificate) {
        this.numCertificate = numCertificate;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }


    @Override
    public String toString() {
        return "Name - " +this.getName() + "; Age - " +this.getAge()+ "; Average score - " + this.getAverageScore() +";\n";
    }
}

