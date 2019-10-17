
package com.eryxlabs.fiderides.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Stream implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    
    @SerializedName("students_count")
    @Expose
    private int studentsCount;
    
    @SerializedName("people_id")
    @Expose
    private Object personId;

    @SerializedName("class_stream")
    @Expose
    private String classStream;
    
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    
    @SerializedName("class_teacher")
    @Expose
    private Teacher teacher;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int students_count) {
        this.studentsCount = students_count;
    }

    public Object getPersonId() {
        return personId;
    }

    public void setPersonId(Object personId) {
        this.personId = personId;
    }

    public String getClassStream() {
        return classStream;
    }

    public void setClassStream(String classStream) {
        this.classStream = classStream;
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

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }


    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
