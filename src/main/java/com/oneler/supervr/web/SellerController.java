package com.oneler.supervr.web;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SellerController {

    OkHttpClient client = new OkHttpClient();

    String run(String url, String cookie) throws IOException {
        Request request = new Request.Builder()
                .addHeader("Cookie", cookie)
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String result(String cookie) {
        while (true) {
            try {
                run("", cookie);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
