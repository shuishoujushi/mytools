package com.charlie.tools.xml;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by charlie on 16/07/2017.
 */
public class DOMClient {

    private Document document;

    public void init() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        document = builder.parse(DOMClient.class.getResourceAsStream("/xml/account.xml"));
    }

    public void doJob() throws ParserConfigurationException, IOException, SAXException {
        init();
        System.out.println(document.getElementsByTagName("relNo"));
    }

    public void writeToFile() throws IOException, SAXException, ParserConfigurationException, TransformerException {
        init();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        FileOutputStream outputStream = new FileOutputStream("account.xml");
        StreamResult result = new StreamResult(outputStream);
        transformer.transform(source, result);
        outputStream.close();
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        DOMClient util = new DOMClient();
        util.doJob();
        util.writeToFile();
    }
}
