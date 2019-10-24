package com.eryxlabs.fiderides.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Trip implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("students_count")
    @Expose
    private int studentsCount;

    @SerializedName("name")
    @Expose
    public String name;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getStudentsCount() {
        return studentsCount;
    }
}
