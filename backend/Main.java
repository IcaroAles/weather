package backend;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        // Start HTTP Server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/weather", new WeatherHandler()); // Register endpoint
        server.setExecutor(null);
        server.start();

        System.out.println("Server started at http://localhost:8080");
    }

    // Handler to return weather data
    static class WeatherHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = WeatherService.getWeather("London"); // Default city: London

            // Fix CORS issue by allowing any origin to access the API
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
