package framework.apiclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HTTPResponseClient {

    public static String getResponseBodyAsString(HttpResponse response){
        HttpEntity entity =response.getEntity();
        // Read the contents of an entity and return it as a String.
        String content = null;
        try {
            content = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
