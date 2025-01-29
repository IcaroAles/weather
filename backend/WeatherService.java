package backend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService {
    private static final String API_KEY = "19c1775d0ddc3573971e3f9e7fdaa364"; // 🔥 Replace this with your real API key
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=";

    public static String getWeather(String city) {
        try {
            String urlString = BASE_URL + city + "&appid=" + API_KEY + "&units=metric";
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString(); // Returns the JSON response from OpenWeatherMap

        } catch (Exception e) {
            return "{ \"error\": \"Failed to fetch weather data\" }";
        }
    }
}
