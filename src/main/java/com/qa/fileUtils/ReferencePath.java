package com.qa.fileUtils;

public class ReferencePath
{
    private static String currentDir = System.getProperty("user.dir");
    @SuppressWarnings("unused")
	private static String basePath = "config.properties";
    public static String config = currentDir+"\\resources\\config.properties";
}
