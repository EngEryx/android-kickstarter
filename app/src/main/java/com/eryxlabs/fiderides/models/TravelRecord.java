package com.eryxlabs.fiderides.models;

import com.google.gson.annotations.SerializedName;

public class TravelRecord {

    @SerializedName("id")
    private Integer id;

    @SerializedName("school_id")
    private Integer schoolId;

    @SerializedName("road_id")
    private Integer roadId;

    @SerializedName("student_id")
    private Integer studentId;

    @SerializedName("picked_home")
    private Integer pickedHome;

    @SerializedName("dropped_home")
    private Integer droppedHome;

    @SerializedName("picked_school")
    private Integer pickedSchool;

    @SerializedName("dropped_school")
    private Integer droppedSchool;

    @SerializedName("date")
    private String date;

    @SerializedName("kind")
    private String kind;

    @SerializedName("created_by")
    private Integer createdBy;

    @SerializedName("deleted_at")
    private String deletedAt;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("student")
    private Student student;

    @SerializedName("updated_at")
    private String updatedAt;

    public TravelRecord() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getRoadId() {
        return roadId;
    }

    public void setRoadId(Integer roadId) {
        this.roadId = roadId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Student getStudent() {
        return student;
    }

    public Integer getDroppedSchool() {
        return droppedSchool;
    }

    public void setDroppedSchool(Integer droppedSchool) {
        this.droppedSchool = droppedSchool;
    }

    public Integer getPickedSchool() {
        return pickedSchool;
    }

    public void setPickedSchool(Integer pickedSchool) {
        this.pickedSchool = pickedSchool;
    }

    public Integer getDroppedHome() {
        return droppedHome;
    }

    public Integer getPickedHome() {
        return pickedHome;
    }

    public void setDroppedHome(Integer droppedHome) {
        this.droppedHome = droppedHome;
    }

    public void setPickedHome(Integer pickedHome) {
        this.pickedHome = pickedHome;
    }
}
