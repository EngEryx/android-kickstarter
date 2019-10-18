package com.eryxlabs.fiderides.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Assessment implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("school_id")
    @Expose
    private int schoolId;

    @SerializedName("subject_id")
    @Expose
    private int subjectId;

    @SerializedName("school_session_id")
    @Expose
    private int schoolSessionId;

    @SerializedName("stream_id")
    @Expose
    private int streamId;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("type")
    @Expose
    private int type;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("updated_at")
    @Expose
    private String updated_at;

    @SerializedName("deleted_at")
    @Expose
    private String deleted_at;

    @SerializedName("date_formatted")
    @Expose
    private String date_formatted;

    @SerializedName("describe_type")
    @Expose
    private String describe_type;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getSchoolSessionId() {
        return schoolSessionId;
    }

    public void setSchoolSessionId(int schoolSessionId) {
        this.schoolSessionId = schoolSessionId;
    }

    public int getStreamId() {
        return streamId;
    }

    public void setStreamId(int streamId) {
        this.streamId = streamId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getDate_formatted() {
        return date_formatted;
    }

    public void setDate_formatted(String date_formatted) {
        this.date_formatted = date_formatted;
    }

    public String getDescribe_type() {
        return describe_type;
    }

    public void setDescribe_type(String describe_type) {
        this.describe_type = describe_type;
    }


}
