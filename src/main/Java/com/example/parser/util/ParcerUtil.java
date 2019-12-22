package com.example.parser.util;

import com.example.parser.dao.DocumentHibernateDao;
import com.example.parser.model.Doc;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ParcerUtil {
    DocumentHibernateDao dh = new DocumentHibernateDao();

    public Document getDocumentFromFile(String nameFile) {
        Document doc = null;
        try {
            File htmlFile = new File(nameFile);
            doc = Jsoup.parse(htmlFile, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements = doc.getElementsByTag("par_list");

        Set<Doc> documents = new TreeSet<>();
        for (Element e : elements) {
            String tempValue = e.attr("value");
            if (tempValue != null && tempValue.length() > 0) {
                documents.add(new Doc(tempValue));
            }
        }
        saveToDb(documents);
        return doc;
    }


    public void getAttrFromDoc(Document doc) {
        Elements el = doc.select("par[step=1][name=ГРАЖДАНСТВО]");
        for (Element e : el) {
            for (Attribute att : e.attributes().asList()) {
                System.out.println(att.getKey() + " : " + att.getValue());
            }
        }
    }


    public void saveToDb(Set<Doc> documents) {
        for (Doc d : documents) {
            dh.addDocument(new Doc(d.getName()));
        }
    }


    public void printFromDb() {
        List<Doc> docs = dh.getAll();
        for (Doc d : docs) {
            System.out.println(d.getName());
        }
    }

}
