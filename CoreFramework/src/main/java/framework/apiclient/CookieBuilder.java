package framework.apiclient;

public class CookieBuilder {
    //    private String domain;
//    private String path;
//    private String secure;
    private String sessionID;
    private String sessionIDValue;

    public CookieBuilder() {
    }

    public CookieBuilder(String sessionID, String SessionValue) {
        this.setSessionID(sessionID);
        this.setSessionIDValue(SessionValue);
    }

//    public String getDomain() {
//        return domain;
//    }
//
//    public void setDomain(String domain) {
//        this.domain = domain;
//    }
//
//    public String getPath() {
//        return path;
//    }
//
//    public void setPath(String path) {
//        this.path = path;
//    }
//
//    public String getSecure() {
//        return secure;
//    }
//
//    public void setSecure(String secure) {
//        this.secure = secure;
//    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getSessionIDValue() {
        return sessionIDValue;
    }

    public void setSessionIDValue(String sessionIDValue) {
        this.sessionIDValue = sessionIDValue;
    }
}
