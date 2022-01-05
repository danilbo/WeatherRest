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
    public String getTemperature() throws IOException {
        Document doc = Jsoup.connect("https://yandex.ru/").get();
        String t = doc.select("div.weather__temp").text();
        return t;
    }
}
