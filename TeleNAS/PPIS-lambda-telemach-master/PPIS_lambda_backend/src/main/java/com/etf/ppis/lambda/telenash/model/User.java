package com.etf.ppis.lambda.telenash.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class User
{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (unique = true)
    @Size (min = 3, max = 50)
    private String username;

    @JsonIgnore
    @Size (min = 6, max = 64)
    private String password;

    @Size (min = 2, max = 50)
    private String firstName;

    @Size (min = 2, max = 50)
    private String lastName;

    @Size (max = 50)
    private String email;

    @Size (max = 20)
    private String phone;

    @Size (max = 50)
    private String address;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "role_id")
    @JsonIgnoreProperties ("users")
    private UserRole userRole;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Request> requests;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Incident> incidents;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<FeedbackRequest> feedbackRequests;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<FeedbackIncident> feedbackIncidents;


    public User()
    {
    }

    public User(String username, String password, String firstName, String lastName, String email, String phone, String address, UserRole userRole, Set<Request> requests,
                Set<Incident> incidents, Set<FeedbackRequest> feedbackRequests, Set<FeedbackIncident> feedbackIncidents)
    {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.userRole = userRole;
        this.requests = requests;
        this.incidents = incidents;
        this.feedbackRequests = feedbackRequests;
        this.feedbackIncidents = feedbackIncidents;
    }

    public Integer getId()
    {
        return id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public UserRole getUserRole()
    {
        return userRole;
    }

    public void setUserRole(UserRole userRole)
    {
        this.userRole = userRole;
    }

    public Set<Request> getRequests()
    {
        return requests;
    }

    public void setRequests(Set<Request> requests)
    {
        this.requests = requests;
    }

    public Set<Incident> getIncidents()
    {
        return incidents;
    }

    public void setIncidents(Set<Incident> incidents)
    {
        this.incidents = incidents;
    }

    public Set<FeedbackRequest> getFeedbackRequests()
    {
        return feedbackRequests;
    }

    public void setFeedbackRequests(Set<FeedbackRequest> feedbackRequests)
    {
        this.feedbackRequests = feedbackRequests;
    }

    public Set<FeedbackIncident> getFeedbackIncidents()
    {
        return feedbackIncidents;
    }

    public void setFeedbackIncidents(Set<FeedbackIncident> feedbackIncidents)
    {
        this.feedbackIncidents = feedbackIncidents;
    }

    @Override
    public String toString()
    {
        return "User{" +
                ", id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", userRole=" + userRole.toString() +
                '}';
    }
}