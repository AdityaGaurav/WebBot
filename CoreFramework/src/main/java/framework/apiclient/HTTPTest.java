package framework.apiclient;

import org.apache.http.HttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;

public class HTTPTest {

    public static void employeeOfficeDetails(){
        CookieBuilder cookieBuilder = new CookieBuilder();
        cookieBuilder.setSessionID("JSESSIONID");
        cookieBuilder.setSessionIDValue("E3DF5F13703E3E832B1A927DD54E1A82");
        String scheme = "https";
        String hostName = "wavemaker-apps.pramati.com/wm_myprofile/services";
        String basePath = "/wm_myprofile/services";
        String path = "/userDetails/loggedInUserDetails";
        HttpResponse response = HTTPRequestClient.doGETRequest(scheme, hostName, basePath, path, cookieBuilder);
        String result = HTTPResponseClient.getResponseBodyAsString(response);
        System.out.println(result);
    }
    public static void main(String[] args) throws IOException, URISyntaxException {
        employeeOfficeDetails();
    }
}
