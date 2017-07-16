package com.charlie.tools.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;

/**
 * Created by charlie on 16/07/2017.
 */
public class Dom4JClient {

    public void getDocument1() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(Dom4JClient.class.getResourceAsStream("/xml/account.xml"));
    }

    public void getDocument2() throws DocumentException {
        String text = "<account></account>";
        Document document = DocumentHelper.parseText(text);
    }

    public void getDocument3() {
        Document document = DocumentHelper.createDocument();
        document.addElement("account");
    }
}
