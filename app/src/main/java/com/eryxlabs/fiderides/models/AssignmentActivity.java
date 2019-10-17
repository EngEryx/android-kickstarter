package com.eryxlabs.fiderides.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssignmentActivity {


    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("student_id")
    @Expose
    private int student_id;

    @SerializedName("assignment_id")
    @Expose
    private int assignment_id;

    @SerializedName("comments")
    @Expose
    private String  comments;

    @SerializedName("teachers_approval")
    @Expose
    private Boolean  teachers_approval;

    @SerializedName("grade")
    @Expose
    private String  grade;

    @SerializedName("teacher_comment")
    @Expose
    private String  teacherComment;

    @SerializedName("created_at")
    @Expose
    private String  created_at;


    @SerializedName("updated_at")
    @Expose
    private String  updated_at;

    @SerializedName("grade_formatted")
    @Expose
    private String  grade_formatted;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getAssignment_id() {
        return assignment_id;
    }

    public void setAssignment_id(int assignment_id) {
        this.assignment_id = assignment_id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getTeachers_approval() {
        return teachers_approval;
    }

    public void setTeachers_approval(Boolean teachers_approval) {
        this.teachers_approval = teachers_approval;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTeacherComment() {
        return teacherComment;
    }

    public void setTeacherComment(String teacherComment) {
        this.teacherComment = teacherComment;
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

    public String getGrade_formatted() {
        return grade_formatted;
    }

    public void setGrade_formatted(String grade_formatted) {
        this.grade_formatted = grade_formatted;
    }
}
