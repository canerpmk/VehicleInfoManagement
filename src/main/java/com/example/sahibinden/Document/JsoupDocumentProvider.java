package com.example.sahibinden.Document;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupDocumentProvider implements DocumentProvider {
    @Override
    public Document getDocument(String url) throws IOException {
    return Jsoup.connect(url).get();
}

}
