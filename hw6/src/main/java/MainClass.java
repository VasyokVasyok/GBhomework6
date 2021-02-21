import okhttp3.*;
import java.io.*;

public class MainClass {
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String LOCATIONS = "locations";
    private static final String TOP_CITIES = "topcities";
    private static final String API_VERSION = "v1";
    private static final String SCHEME = "http";
    private static final String CITIES_NUMBER = "50";
    private static final String API_KEY = "cK9CR3YnbTLoqHYoCdPLCi1sADHVqbzA";

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        HttpUrl urlForCity = new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(API_VERSION)
                .addPathSegment(TOP_CITIES)
                .addPathSegment(CITIES_NUMBER)
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("language", "ru-ru")
                .addQueryParameter("details", "false")
                .build();
        System.out.println(urlForCity.toString());

        Request requestForCity = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(urlForCity)
                .build();

        String jsonResponse = client.newCall(requestForCity).execute().body().string();
        System.out.println(jsonResponse);


        HttpUrl urlWeather = new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(BASE_HOST)
                .addPathSegment("forecasts")
                .addPathSegment(API_VERSION)
                .addPathSegment("daily")
                .addPathSegment("5day")
                .addPathSegment("328328")
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("language", "ru-ru")
                .addQueryParameter("details", "false")
                .addQueryParameter("metric", "false")
                .build();

        System.out.println(urlWeather.toString());

        Request requestWeather = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(urlWeather)
                .build();

        String jsonResponseW = client.newCall(requestWeather).execute().body().string();
        System.out.println("Прогноз погоды в Лондоне:");
        System.out.println(jsonResponseW);
    }
}