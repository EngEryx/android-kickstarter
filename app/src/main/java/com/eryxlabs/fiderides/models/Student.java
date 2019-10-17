package com.eryxlabs.fiderides.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import nl.qbusict.cupboard.annotation.Column;

public class Student implements Serializable {
    @Column("student_id")
    @SerializedName("id")
    public int student_id;

    @Column("stream_id")
    public int stream_id;

    @Column("gender")
    public String gender;

    @Column("reg_no")
    public String reg_no;

    @Column("first_name")
    public String first_name;

    @Column("last_name")
    public String last_name;

    @Column("class_stream")
    public String class_stream;

    @Column("admission_date")
    public String admission_date;

    @Column("status")
    public int status;

    public String getFullName() {
        return first_name +" "+ last_name;
    }
}
