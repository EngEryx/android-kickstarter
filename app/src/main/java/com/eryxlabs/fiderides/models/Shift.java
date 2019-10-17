package com.eryxlabs.fiderides.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import nl.qbusict.cupboard.annotation.Column;

public class Shift implements Serializable {
    @Column("id")
    public int id;

    @Column("description")
    public String description;

    public String getDescription() {
        return description;
    }
}
