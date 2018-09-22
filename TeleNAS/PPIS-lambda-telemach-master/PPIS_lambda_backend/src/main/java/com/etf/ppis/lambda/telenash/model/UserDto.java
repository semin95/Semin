package com.etf.ppis.lambda.telenash.model;

public class UserDto
{
    private String username;
    private String password;

    // dodati ostale atribute

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
}