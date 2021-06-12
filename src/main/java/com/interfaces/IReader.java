package com.interfaces;

public interface IReader
{
    /**
     * This interface to enforce basic configuration of the application to be implemented by the class which will read data from properties
     */
    public String getApplicationUrl();
    public String getBrowser();
    public String getDriver();
}
