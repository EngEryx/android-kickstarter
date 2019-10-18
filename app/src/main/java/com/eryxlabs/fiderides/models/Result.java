package com.eryxlabs.fiderides.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Result implements Serializable {


    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("school_id")
    @Expose
    private int schoolId;

    @SerializedName("assessment_id")
    @Expose
    private int assessment_id;

    @SerializedName("student_id")
    @Expose
    private int student_id;


    @SerializedName("description")
    @Expose
    private String description;


    @SerializedName("grade")
    @Expose
    private String grade;

    @SerializedName("assessment_group_id")
    @Expose
    private String assessment_group_id;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("updated_at")
    @Expose
    private String updated_at;

    @SerializedName("deleted_at")
    @Expose
    private String deleted_at;

    @SerializedName("grade_formatted")
    @Expose
    private String grade_formatted;

    @SerializedName("student")
    @Expose
    private Student student;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getAssessment_id() {
        return assessment_id;
    }

    public void setAssessment_id(int assessment_id) {
        this.assessment_id = assessment_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAssessment_group_id() {
        return assessment_group_id;
    }

    public void setAssessment_group_id(String assessment_group_id) {
        this.assessment_group_id = assessment_group_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getGrade_formatted() {
        return grade_formatted;
    }

    public void setGrade_formatted(String grade_formatted) {
        this.grade_formatted = grade_formatted;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
