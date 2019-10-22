package com.eryxlabs.fiderides.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import nl.qbusict.cupboard.annotation.Column;

public class Teacher implements Serializable {
    @SerializedName("id")
    @Column("teacher_id")
    public int teacher_id;

    @Column("first_name")
    public String first_name;

    @Column("surname")
    public String surname;

    public String getFullName() {
        return first_name + " " + surname;
    }
}
