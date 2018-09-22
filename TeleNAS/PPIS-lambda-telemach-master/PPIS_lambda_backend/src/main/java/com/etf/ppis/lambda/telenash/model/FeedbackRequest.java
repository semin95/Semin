package com.etf.ppis.lambda.telenash.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "feedback_request")
public class FeedbackRequest
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
    @JsonIgnoreProperties("feedbackRequests")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "request_id",nullable = false)
    @JsonIgnoreProperties("feedbackRequests")
    private Request request;

    public FeedbackRequest()
    {}

    public FeedbackRequest(String comment, Date date, User user, Request request) {
        this.comment = comment;
        this.date = date;
        this.user = user;
        this.request = request;
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

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "FeedbackRequest{" +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                ", user=" + user.toString() +
                ", request=" + request.toString() +
                '}';
    }
}