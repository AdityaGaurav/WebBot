package framework.apiclient;

import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.List;

public class Service {

    public static String getResponseFromTheAPI(HTTPMethods httpMethods, String scheme, String hostname, String basePath, String path, List<String> queryParams, String payload, CookieBuilder cookieInfo) throws IOException {
        String responseAsString = null;
        switch (httpMethods) {
            case GET:
                HttpResponse response = HTTPRequestClient.doGETRequest(scheme, hostname, basePath, path, cookieInfo);
                responseAsString = HTTPResponseClient.getResponseBodyAsString(response);
                break;
            case POST:
            case PUT:
            case DELETE:
            default:
        }
        return responseAsString;
    }
}
