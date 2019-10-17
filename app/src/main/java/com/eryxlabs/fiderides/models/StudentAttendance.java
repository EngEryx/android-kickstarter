package com.eryxlabs.fiderides.models;

import java.io.Serializable;

import nl.qbusict.cupboard.annotation.Column;

public class StudentAttendance implements Serializable {
    @Column("id")
    public int id;

    @Column("status")
    private int status;

    @Column("attendance_shift_id")
    private int attendance_shift_id;

    @Column("stream_id")
    private int stream_id;

    @Column("Student")
    private Student student;

    public Student getStudent() {
        return student;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
