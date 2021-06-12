package com.fileUtils;

public class ReferencePath
{
    private static String currentDir = System.getProperty("user.dir");
    private static String basePath = "config.properties";
    public static String config = currentDir+"\\src\\test\\resources\\config.properties";
}
