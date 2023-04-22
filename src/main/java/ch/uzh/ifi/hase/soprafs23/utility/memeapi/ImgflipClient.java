package ch.uzh.ifi.hase.soprafs23.utility.memeapi;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.uzh.ifi.hase.soprafs23.entity.Template;

public class ImgflipClient implements IMemeApi {

    private static final String ENDPOINT = "https://api.imgflip.com/get_memes";

    public ApiResponse getTemplates() {

        try {
            URL url = new URL(ENDPOINT);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Failed with HTTP error code: " + responseCode);
            }

            Scanner scanner = new Scanner(url.openStream());
            String response = scanner.useDelimiter("\\A").next();

            ObjectMapper mapper = new ObjectMapper();
            ApiResponse apiResponse = mapper.readValue(response, ApiResponse.class);

            scanner.close();

            return apiResponse;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static class ApiResponse {
        @JsonProperty("success")
        public boolean success;

        @JsonProperty("data")
        public Data data;
    }

    public static class Data {
        @JsonProperty("memes")
        public List<Meme> memes;
    }

    public static class Meme {
        @JsonProperty("id")
        public String id;

        @JsonProperty("name")
        public String name;

        @JsonProperty("url")
        public String url;

        @JsonProperty("width")
        public int width;

        @JsonProperty("height")
        public int height;

        @JsonProperty("box_count")
        public int boxCount;

        @JsonProperty("captions")
        public int captions;

    }
}