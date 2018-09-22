package com.etf.ppis.lambda.telenash.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "feedback_incident")
public class FeedbackIncident
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 2, max = 200)
    private String comment;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("feedbackIncidents")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "incident_id")
    @JsonIgnoreProperties("feedbackIncidents")
    private Incident incident;


    public FeedbackIncident()
    {}

    public FeedbackIncident(String comment, Date date, User user, Incident incident) {
        this.comment = comment;
        this.date = date;
        this.user = user;
        this.incident = incident;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Incident getIncident() {
        return incident;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }

    @Override
    public String toString() {
        return "FeedbackIncident{" +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                ", user=" + user.toString() +
                ", incident=" + incident.toString() +
                '}';
    }
}