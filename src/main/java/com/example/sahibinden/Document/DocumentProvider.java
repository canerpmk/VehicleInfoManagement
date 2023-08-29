package com.example.sahibinden.Document;

import org.jsoup.nodes.Document;

import java.io.IOException;

public interface DocumentProvider {
    Document getDocument(String url) throws IOException;
}
