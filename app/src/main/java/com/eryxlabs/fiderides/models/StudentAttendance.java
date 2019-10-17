package com.eryxlabs.fiderides.models;

import java.io.Serializable;

import nl.qbusict.cupboard.annotation.Column;

public class StudentAttendance implements Serializable {
    @Column("id")
    public int id;

    @Column("status")
    public int status;

    @Column("attendance_shift_id")
    public int attendance_shift_id;

    @Column("stream_id")
    public int stream_id;

    @Column("Student")
    public Student student;
}
