package cl.utem.dist.proyecto.servicio;

import cl.utem.dist.proyecto.vo.GeoVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.vo.google.GeoCodeGoogleVO;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("googleService")
public class GoogleService implements Serializable {

    private static final long serialVersionUID = 1578075642023571456L;

    @Value("${GEOCODE_API_URL}")
    private String geocodeApiUrl;
    @Value("${GEOCODE_API_KEY}")
    private String geocodeApiKey;
    @Value("${GEOCODE_MAP_KEY}")
    private String mapsKey;
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleService.class);

    public String getAddress(final Double latitude, final Double longitude) {
        String address = StringUtils.EMPTY;
        try {
            if (latitude != null && longitude != null) {
                String geoCodeUri = String.format(Locale.US, "%s?key=%s&latlng=%f,%f", geocodeApiUrl, geocodeApiKey, latitude, longitude);
                LOGGER.debug("Accediendo a: '{}'", geoCodeUri);

                HttpClient client = HttpClientBuilder.create().build();
                HttpGet get = new HttpGet(geoCodeUri);

                // add request header
                get.addHeader("User-Agent", "Sebastian/1.0");

                HttpResponse response = client.execute(get);
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    StringBuilder result = new StringBuilder();
                    String line = StringUtils.EMPTY;
                    while ((line = rd.readLine()) != null) {
                        result.append(line);
                    }

                    String json = StringUtils.trimToEmpty(result.toString());
                    if (StringUtils.isNotBlank(json)) {
                        ObjectMapper mapper = new ObjectMapper();
                        GeoCodeGoogleVO resultGoogle = (GeoCodeGoogleVO) mapper.readValue(json, GeoCodeGoogleVO.class);
                        address = resultGoogle.getResults().get(0).getFormattedAddress();

                        LOGGER.debug("Se encontro una coincidencia: '{}'", address);
                    }
                }
            }
        } catch (Exception e) {
            address = StringUtils.EMPTY;
            LOGGER.error("Error obteniendo dirección: {}", e.getMessage());
            LOGGER.debug("Error obteniendo dirección: {}", e.getMessage(), e);
        }
        return address;
    }

    public GeoVO getAddress(final String address) {
        GeoVO geo = null;
        try {
            if (StringUtils.isNotBlank(address)) {
                String geoCodeUri = String.format(Locale.US, "%s?key=%s&address=%s", geocodeApiUrl, geocodeApiKey, address);
                LOGGER.debug("Accediendo a: '{}'", geoCodeUri);

                HttpClient client = HttpClientBuilder.create().build();
                HttpGet get = new HttpGet(geoCodeUri);

                // add request header
                get.addHeader("User-Agent", "Sebastian/1.0");

                HttpResponse response = client.execute(get);
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    StringBuilder result = new StringBuilder();
                    String line = StringUtils.EMPTY;
                    while ((line = rd.readLine()) != null) {
                        result.append(line);
                    }

                    String json = StringUtils.trimToEmpty(result.toString());
                    if (StringUtils.isNotBlank(json)) {
                        ObjectMapper mapper = new ObjectMapper();
                        GeoCodeGoogleVO resultGoogle = (GeoCodeGoogleVO) mapper.readValue(json, GeoCodeGoogleVO.class);
                        Double lat = resultGoogle.getResults().get(0).getGeometry().getLocation().getLat();
                        Double lng = resultGoogle.getResults().get(0).getGeometry().getLocation().getLng();

                        geo = new GeoVO();
                        geo.setLatitude(lat);
                        geo.setLongitude(lng);

                        LOGGER.debug("Se encontro una coincidencia: '{}'", geo.toString());
                    }
                }
            }
        } catch (Exception e) {
            geo = null;
            LOGGER.error("Error obteniendo dirección: {}", e.getMessage());
            LOGGER.debug("Error obteniendo dirección: {}", e.getMessage(), e);
        }
        return geo;
    }

    public String getCommune(Double latitude, Double longitude) {
        String commune = StringUtils.EMPTY;
        try {
            if (latitude != null && longitude != null) {
                String geoCodeUri = String.format(Locale.US, "%s?key=%s&latlng=%f,%f", geocodeApiUrl, geocodeApiKey, latitude, longitude);
                LOGGER.debug("Accediendo a: '{}'", geoCodeUri);

                HttpClient client = HttpClientBuilder.create().build();
                HttpGet get = new HttpGet(geoCodeUri);

                // add request header
                get.addHeader("User-Agent", "Sebastian/1.0");

                HttpResponse response = client.execute(get);
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    StringBuilder result = new StringBuilder();
                    String line = StringUtils.EMPTY;
                    while ((line = rd.readLine()) != null) {
                        result.append(line);
                    }

                    String json = StringUtils.trimToEmpty(result.toString());
                    if (StringUtils.isNotBlank(json)) {
                        ObjectMapper mapper = new ObjectMapper();
                        GeoCodeGoogleVO resultGoogle = (GeoCodeGoogleVO) mapper.readValue(json, GeoCodeGoogleVO.class);
                        commune = StringUtils.trimToEmpty(resultGoogle.getResults().get(0).getAddressComponents().get(2).getLongName());

                        LOGGER.debug("Se encontro una coincidencia: '{}'", commune);
                    }
                }
            }
        } catch (Exception e) {
            commune = StringUtils.EMPTY;
            LOGGER.error("Error obteniendo dirección: {}", e.getMessage());
            LOGGER.debug("Error obteniendo dirección: {}", e.getMessage(), e);
        }
        return commune;
    }

}
