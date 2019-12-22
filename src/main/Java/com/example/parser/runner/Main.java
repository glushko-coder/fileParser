package com.example.parser.runner;

import com.example.parser.util.ParcerUtil;
import org.jsoup.nodes.Document;

public class Main {
    public static void main(String[] args) {

/** Get document from file and save to database */
        ParcerUtil parcerUtil = new ParcerUtil();
        Document doc = parcerUtil.getDocumentFromFile("SberParser.xml");

/** Get and print attributes */
        parcerUtil.getAttrFromDoc(doc);

/** Print from db */
        parcerUtil.printFromDb();

    }
}
