package com.example.jhyangnewthings.constructor.datasource.rootdatasource;

public class DataSourceContextHolder {


    private static final ThreadLocal<String> local = new ThreadLocal<>();

    public static String getDatabase() {
        return local.get();
    }

    public static void setDatabase(String dbType) {
        local.set(dbType);
    }

    public static void clear() {
        local.remove();
    }
}
