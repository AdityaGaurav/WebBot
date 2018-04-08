package framework.apiclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

class HTTPRequestClient {

    /**
     * @param scheme
     * @param hostName
     * @param basePath
     * @return
     * @throws URISyntaxException
     */
    private static HttpGet buildGetRequest(String scheme, String hostName, String basePath, String path) throws URISyntaxException {
        Objects.requireNonNull(scheme);
        Objects.requireNonNull(hostName);
        Objects.requireNonNull(basePath);
        HttpGet httpGet = new HttpGet(buildURI(scheme, hostName, basePath, path));
//        int hardTimeout = 10; // seconds
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                if (httpGet != null) {
//                    httpGet.abort();
//                }
//            }
//        };
//        new Timer(true).schedule(task, hardTimeout * 1000);
        return httpGet;
    }

    /**
     * @param scheme
     * @param hostName
     * @param basePath
     * @return
     * @throws URISyntaxException
     */
    private static URI buildURI(String scheme, String hostName, String basePath, String path) throws URISyntaxException {
        Objects.requireNonNull(scheme);
        Objects.requireNonNull(hostName);
        Objects.requireNonNull(basePath);
        return new URIBuilder()
                .setScheme(scheme)
                .setHost(hostName)
                .setPath(basePath).setPath(path).build();
        //.setParameter("q", "httpclient")
    }

    /**
     * @param scheme
     * @param hostName
     * @param basePath
     * @param cookieInfo
     * @return
     * @throws URISyntaxException
     */
    @SuppressWarnings("deprecation")
    static HttpResponse doGETRequest(String scheme, String hostName, String basePath, String path, CookieBuilder cookieInfo) {
        HttpClient client = null;
        HttpGet httpGet = null;
        HttpResponse response = null;
        try {
            client = getCookieBuilder(cookieInfo);
            httpGet = buildGetRequest(scheme, hostName, basePath, path);
            response = client.execute(httpGet);
            //client.getConnectionManager();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * @param cookieInfo
     * @return
     */
    private static BasicCookieStore prepareCookie(CookieBuilder cookieInfo) {
        BasicCookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie(cookieInfo.getSessionID(), cookieInfo.getSessionIDValue());
        cookie.setDomain("wavemaker-apps.pramati.com");
        cookie.setPath("/wm_myprofile");
        cookie.setSecure(true);
        cookieStore.addCookie(cookie);
        return cookieStore;
    }

    /**
     * @param cookieInfo
     * @return
     */
    private static CloseableHttpClient getCookieBuilder(CookieBuilder cookieInfo) {
        return HttpClientBuilder.create().
                setDefaultCookieStore(prepareCookie(cookieInfo)).build();
    }

    /**
     * @param scheme
     * @param hostName
     * @param basePath
     * @param cookieBuilder
     * @param payLoad
     * @return
     */
    static HttpResponse doPutRequest(String scheme, String hostName, String basePath, CookieBuilder cookieBuilder, String payLoad) {
        return null;
    }

}
