package com.etf.ppis.lambda.telenash.model;

public class StatisticsReport
{
    private int month;
    private long count;
    private String status;

    public StatisticsReport(int month, long count, String status)
    {
        this.month = month;
        this.count = count;
        this.status = status;
    }

    public int getMonth()
    {
        return month;
    }

    public long getCount()
    {
        return count;
    }

    public String getStatus()
    {
        return status;
    }
}