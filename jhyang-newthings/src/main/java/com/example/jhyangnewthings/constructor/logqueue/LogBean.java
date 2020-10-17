package com.example.jhyangnewthings.constructor.logqueue;

/**
 * @Author jhYang
 * @Date 2020/4/21 0021 11:02
 * @Discription todo
 */
public class LogBean {
    private String userName;
    private String userId;
    private String FromIp;
    private String startTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFromIp() {
        return FromIp;
    }

    public void setFromIp(String fromIp) {
        FromIp = fromIp;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public LogBean(String userName, String userId, String fromIp, String startTime) {
        this.userName = userName;
        this.userId = userId;
        FromIp = fromIp;
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "LogBean{" +
                "userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", FromIp='" + FromIp + '\'' +
                ", startTime='" + startTime + '\'' +
                '}';
    }
}
