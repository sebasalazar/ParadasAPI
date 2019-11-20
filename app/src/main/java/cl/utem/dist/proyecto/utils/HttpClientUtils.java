package cl.utem.dist.proyecto.utils;

import cl.utem.dist.proyecto.persistencia.modelo.BaseBean;
import cl.utem.dist.proyecto.vo.HttpVO;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.entity.StringEntity;

/**
 *
 * @author Sebastián Salazar Molina.
 */
public class HttpClientUtils implements Serializable {

    private static final long serialVersionUID = 4248381502250151936L;
    private static final String USER_AGENT = "Sebastian/1.0";
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

    public static HttpVO post(final String url) {
        HttpVO response = null;
        try {
            if (StringUtils.isNotBlank(url)) {
                LOGGER.debug("Posteando: '{}'", url);
                HttpClient client = HttpClientBuilder.create().build();
                HttpPost post = new HttpPost(url);

                // add request header
                post.addHeader("User-Agent", USER_AGENT);
                HttpResponse responseHttp = client.execute(post);

                // Estado de respuesta
                Integer statusCode = responseHttp.getStatusLine().getStatusCode();

                // Mensaje
                BufferedReader rd = new BufferedReader(new InputStreamReader(responseHttp.getEntity().getContent()));
                StringBuilder result = new StringBuilder();
                String line = StringUtils.EMPTY;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                String bodyResponse = StringUtils.trimToEmpty(result.toString());

                response = new HttpVO(statusCode, bodyResponse);
            }
        } catch (Exception e) {
            response = new HttpVO(500, e.getMessage());
            LOGGER.error("Error al POSTEAR: {}", e.getMessage());
            LOGGER.debug("Error al POSTEAR: {}", e.getMessage(), e);
        }
        return response;
    }

    public static HttpVO postJson(final String url, final BaseBean baseBean) {
        HttpVO response = null;
        try {
            if (StringUtils.isNotBlank(url)) {
                LOGGER.debug("Posteando: '{}'", url);
                HttpClient client = HttpClientBuilder.create().build();
                HttpPost post = new HttpPost(url);

                // add request header
                String json = JsonUtils.parse(baseBean);
                post.addHeader("User-Agent", USER_AGENT);
                post.addHeader("Content-Type", "application/json; charset=UTF-8");
                post.addHeader("accept", "application/json; charset=UTF-8");
                post.setEntity(new StringEntity(json, StandardCharsets.UTF_8));

                HttpResponse responseHttp = client.execute(post);

                // Estado de respuesta
                Integer statusCode = responseHttp.getStatusLine().getStatusCode();

                // Mensaje
                BufferedReader rd = new BufferedReader(new InputStreamReader(responseHttp.getEntity().getContent()));
                StringBuilder result = new StringBuilder();
                String line = StringUtils.EMPTY;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                String bodyResponse = StringUtils.trimToEmpty(result.toString());

                response = new HttpVO(statusCode, bodyResponse);
            }
        } catch (Exception e) {
            response = new HttpVO(500, e.getMessage());
            LOGGER.error("Error al POSTEAR: {}", e.getMessage());
            LOGGER.debug("Error al POSTEAR: {}", e.getMessage(), e);
        }
        return response;
    }

    public static HttpVO post(final String url, final Map<String, String> data) {
        HttpVO response = null;
        try {
            if (StringUtils.isNotBlank(url)) {
                LOGGER.debug("Posteando: '{}'", url);
                HttpClient client = HttpClientBuilder.create().build();
                HttpPost post = new HttpPost(url);

                // add request header
                post.addHeader("User-Agent", USER_AGENT);

                List<NameValuePair> urlParameters = new ArrayList<>();
                if (data != null && !data.isEmpty()) {
                    data.entrySet().stream().map((entry) -> {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        BasicNameValuePair basicValuePair = new BasicNameValuePair(key, value);
                        return basicValuePair;
                    }).forEachOrdered((basicValuePair) -> {
                        urlParameters.add(basicValuePair);
                    });
                }

                post.setEntity(new UrlEncodedFormEntity(urlParameters));
                HttpResponse responseHttp = client.execute(post);

                // Estado de respuesta
                Integer statusCode = responseHttp.getStatusLine().getStatusCode();

                // Mensaje
                BufferedReader rd = new BufferedReader(new InputStreamReader(responseHttp.getEntity().getContent()));
                StringBuilder result = new StringBuilder();
                String line = StringUtils.EMPTY;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                String bodyResponse = StringUtils.trimToEmpty(result.toString());

                response = new HttpVO(statusCode, bodyResponse);
            }
        } catch (Exception e) {
            response = new HttpVO(500, e.getMessage());
            LOGGER.error("Error al POSTEAR: {}", e.getMessage());
            LOGGER.debug("Error al POSTEAR: {}", e.getMessage(), e);
        }
        return response;
    }

    public static HttpVO get(final String url) {
        HttpVO response = null;
        try {
            if (StringUtils.isNotBlank(url)) {
                LOGGER.debug("Getando: '{}'", url);
                HttpClient client = HttpClientBuilder.create().build();
                HttpGet get = new HttpGet(url);

                // add request header
                get.addHeader("User-Agent", USER_AGENT);
                HttpResponse responseHttp = client.execute(get);

                // Estado de respuesta
                Integer statusCode = responseHttp.getStatusLine().getStatusCode();

                // Mensaje
                BufferedReader rd = new BufferedReader(new InputStreamReader(responseHttp.getEntity().getContent()));
                StringBuilder result = new StringBuilder();
                String line = StringUtils.EMPTY;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                String bodyResponse = StringUtils.trimToEmpty(result.toString());

                response = new HttpVO(statusCode, bodyResponse);
            }
        } catch (Exception e) {
            response = new HttpVO(500, e.getMessage());
            LOGGER.error("Error en GET: {}", e.getMessage());
            LOGGER.debug("Error en GET: {}", e.getMessage(), e);
        }
        return response;
    }

    public static HttpVO get(final String url, final String jwt) {
        HttpVO response = null;
        try {
            if (StringUtils.isNotBlank(url) && StringUtils.isNotBlank(jwt)) {

                LOGGER.debug("Getando: '{}'", url);
                HttpClient client = HttpClientBuilder.create().build();
                HttpGet get = new HttpGet(url);
                get.setHeader("Authorization", StringUtils.trimToEmpty(jwt));

                // add request header
                get.addHeader("User-Agent", USER_AGENT);
                HttpResponse responseHttp = client.execute(get);

                // Estado de respuesta
                Integer statusCode = responseHttp.getStatusLine().getStatusCode();

                // Mensaje
                BufferedReader rd = new BufferedReader(new InputStreamReader(responseHttp.getEntity().getContent()));
                StringBuilder result = new StringBuilder();
                String line = StringUtils.EMPTY;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                String bodyResponse = StringUtils.trimToEmpty(result.toString());

                response = new HttpVO(statusCode, bodyResponse);
            }
        } catch (Exception e) {
            response = new HttpVO(500, e.getMessage());
            LOGGER.error("Error en GET: {}", e.getMessage());
            LOGGER.debug("Error en GET: {}", e.getMessage(), e);
        }
        return response;
    }
}
