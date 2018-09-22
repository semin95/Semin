package com.etf.ppis.lambda.telenash.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
public class Incident
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 2, max = 100)
    private String name;

    @Size(min = 2, max = 1000)
    private String description;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Size(min = 2, max = 1000)
    private String radniNalog;

    @Size(min = 2, max = 50)
    private String wayOfSubmission;

    @Size(max = 50)
    private String wayOfResponse;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userPriority_id")
    private Priority userPriority;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "adminPriority_id")
    private Priority adminPriority;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    @JsonIgnoreProperties("incidents")
    private Department department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("incidents")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("incidents")
    private Product product;

    @OneToMany(mappedBy = "incident", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<FeedbackIncident> feedbackIncidents;


    public Incident()
    {}

    public Incident(String name, String description, Date date, String wayOfSubmission, String wayOfResponse, Status status, Priority userPriority, Priority adminPriority, Department department, User user, Product product, Set<FeedbackIncident> feedbackIncidents) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.wayOfSubmission = wayOfSubmission;
        this.wayOfResponse = wayOfResponse;
        this.status = status;
        this.userPriority = userPriority;
        this.adminPriority = adminPriority;
        this.department = department;
        this.user = user;
        this.product = product;
        this.feedbackIncidents = feedbackIncidents;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRadniNalog() {
        return radniNalog;
    }

    public void setRadniNalog(String radniNalog) {
        this.radniNalog = radniNalog;
    }

    public String getWayOfSubmission() {
        return wayOfSubmission;
    }

    public void setWayOfSubmission(String wayOfSubmission) {
        this.wayOfSubmission = wayOfSubmission;
    }

    public String getWayOfResponse() {
        return wayOfResponse;
    }

    public void setWayOfResponse(String wayOfResponse) {
        this.wayOfResponse = wayOfResponse;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getUserPriority() {
        return userPriority;
    }

    public void setUserPriority(Priority userPriority) {
        this.userPriority = userPriority;
    }

    public Priority getAdminPriority() {
        return adminPriority;
    }

    public void setAdminPriority(Priority adminPriority) {
        this.adminPriority = adminPriority;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Set<FeedbackIncident> getFeedbackIncidents() {
        return feedbackIncidents;
    }

    public void setFeedbackIncidents(Set<FeedbackIncident> feedbackIncidents) {
        this.feedbackIncidents = feedbackIncidents;
    }

    @Override
    public String toString() {
        return "Incident{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", wayOfSubmission='" + wayOfSubmission + '\'' +
                ", wayOfResponse='" + wayOfResponse + '\'' +
                ", status=" + status.toString() +
                ", userPriority=" + userPriority.toString() +
                ", adminPriority=" + adminPriority.toString() +
                ", department=" + department.toString() +
                ", user=" + user.toString() +
                ", product=" + product.toString() +
                '}';
    }
}