package com.cartoni.bucketlist;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.Set;

@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private String title;
    private String description;
    private Date executionDate;
    private Time executionTime;
    private Date executionFromDate;
    private Date executionToDate;
    private Time executionFromTime;
    private Time executionToTime;
    private String location;
    private String duration;
    @OneToMany
    @JoinColumn(name = "takeaway_id")
    Set<Takeaway> takeaways;
    @Column(columnDefinition = "boolean default false")
    private boolean done;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    public Time getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Time executionTime) {
        this.executionTime = executionTime;
    }

    public Date getExecutionFromDate() {
        return executionFromDate;
    }

    public void setExecutionFromDate(Date executionFromDate) {
        this.executionFromDate = executionFromDate;
    }

    public Date getExecutionToDate() {
        return executionToDate;
    }

    public void setExecutionToDate(Date executionToDate) {
        this.executionToDate = executionToDate;
    }

    public Time getExecutionFromTime() {
        return executionFromTime;
    }

    public void setExecutionFromTime(Time executionFromTime) {
        this.executionFromTime = executionFromTime;
    }

    public Time getExecutionToTime() {
        return executionToTime;
    }

    public void setExecutionToTime(Time executionToTime) {
        this.executionToTime = executionToTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
