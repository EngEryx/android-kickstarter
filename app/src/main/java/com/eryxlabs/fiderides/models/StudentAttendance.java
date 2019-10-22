package com.eryxlabs.fiderides.models;

import java.io.Serializable;

import nl.qbusict.cupboard.annotation.Column;
import nl.qbusict.cupboard.annotation.Ignore;

public class StudentAttendance implements Serializable {
    @Column("id")
    public int id;

    @Column("status")
    private int status;

    @Column("attendance_shift_id")
    private int attendance_shift_id;

    @Column("stream_id")
    private int stream_id;

    @Column("reason")
    private String reason;

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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
