package com.charlie.tools.xml;

import org.xml.sax.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Created by charlie on 16/07/2017.
 */
public class SAXClient {

    public void doJob() throws ParserConfigurationException, SAXException, IOException {
        SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
        XMLReader reader = saxParser.getXMLReader();

        reader.setContentHandler(new ContentHandler() {
            @Override
            public void setDocumentLocator(Locator locator) {

            }

            @Override
            public void startDocument() throws SAXException {
                System.out.println("startDocument...");
            }

            @Override
            public void endDocument() throws SAXException {
                System.out.println("endDocument...");
            }

            @Override
            public void startPrefixMapping(String prefix, String uri) throws SAXException {

            }

            @Override
            public void endPrefixMapping(String prefix) throws SAXException {

            }

            @Override
            public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
                System.out.println("startElement..." + localName + ", qName = " + qName);
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                System.out.println("endElement..." + localName + ", qName = " + qName);
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {

            }

            @Override
            public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

            }

            @Override
            public void processingInstruction(String target, String data) throws SAXException {

            }

            @Override
            public void skippedEntity(String name) throws SAXException {

            }
        });
        reader.parse(new InputSource(SAXClient.class.getResourceAsStream("/xml/account.xml")));
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        SAXClient sc = new SAXClient();
        sc.doJob();
    }
}
