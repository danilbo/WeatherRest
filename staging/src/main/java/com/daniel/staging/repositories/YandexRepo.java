package com.daniel.staging.repositories;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Repository;
import org.jsoup.select.Elements;

import java.awt.*;
import java.io.IOException;
import java.util.List;


/**
 * Class for connection with yandex.ru
 */


@Repository
public class YandexRepo {

    /**
     * parse web page with Jsoup
     * getting element by classname
     * getting temperature
     */
    public int getTemperature() throws IOException {
        Document doc = Jsoup.connect("https://yandex.ru/").get();
        String t = doc.select("div.weather__temp").text();
        if (t != null && t.length() > 0 && t.charAt(t.length() - 1) == '°') {
            t = t.substring(0, t.length() - 1);
            if (t.charAt(0) != '+') t = "-" + t.replaceAll("\\D+", "");
        }
        int temperature = -999;
        try {
            temperature = Integer.parseInt(t);
        } catch (NumberFormatException e) {
            System.out.println("Parse error!");
        }

        return temperature;
    }
}
