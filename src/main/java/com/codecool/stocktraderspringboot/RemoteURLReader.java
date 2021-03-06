package com.codecool.stocktraderspringboot;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class RemoteURLReader {

    @Autowired
    private Logger logger;

    public String readFromUrl(String endpoint) throws IOException {
        URL url = new URL(endpoint);
        URLConnection conn = url.openConnection();
        logger.info("connection created to url: " + url);
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String lines = reader.lines().collect(Collectors.joining("\n"));
        logger.info("response received: "+ lines.length());
        reader.close();
        return lines;
    }
}
