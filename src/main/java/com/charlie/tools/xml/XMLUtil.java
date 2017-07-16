package com.charlie.tools.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by charlie on 04/04/2017.
 */
public class XMLUtil {
    public static final String DATA_TYPE_STRING = "String";
    public static final String DATA_TYPE_BIGDECIMAL = "BigDecimal";
    public static final String DATA_TYPE_DATE = "Date";
    public static final String SIMPLE_DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static SimpleDateFormat simpleDateFormat = null;

    public static SimpleDateFormat getSimpleDateFormat(){
        if(null == simpleDateFormat){
            simpleDateFormat = new SimpleDateFormat(SIMPLE_DATE_FORMAT_PATTERN);
        }
        return simpleDateFormat;
    }

    public static void init(String fileName) throws IOException {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String str = reader.readLine();
        while(null != str){
            System.out.println(str);
            str = reader.readLine();
        }
    }

    @SuppressWarnings("unchecked")
    public static void doJob() throws ParserConfigurationException, SAXException, IOException, ParseException {
        //build document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        factory.setIgnoringComments(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(ClassLoader.getSystemResourceAsStream("xml/account.xml"));

        //whole response
        DataObject dataObject = new DataObject();
        //row record
        DataObject rowObject = new DataObject();

        Element root = document.getDocumentElement();
        NodeList nodeList = root.getChildNodes();
        List<DataObject> list = new ArrayList<DataObject>();
        for(int i = 0, j = nodeList.getLength(); i < j; i++){
            Node node = nodeList.item(i);
            if(node instanceof Element){
                if(!node.getNodeName().equals("data")){
                    //fetch all nodes under root
                    processDataType(dataObject, node.getAttributes().getNamedItem("type").getNodeValue(), node.getNodeName(), node.getFirstChild().getNodeValue());
//					dataObject.put(node.getNodeName(), node.getFirstChild().getNodeValue());
                }else{
                    //fetch the list object under data
                    NodeList subNodeList = node.getChildNodes();
                    for(int m = 0, n = subNodeList.getLength(); m < n; m++){
                        Node subNode = subNodeList.item(m);
                        if(subNode instanceof Element){
                            NodeList nodes = subNode.getChildNodes();
                            for(int x = 0, y = nodes.getLength(); x < y; x++){
                                Node data = nodes.item(x);
                                if(data instanceof Element){
//									rowObject.put(data.getNodeName(), data.getFirstChild().getNodeValue());
                                    processDataType(rowObject, data.getAttributes().getNamedItem("type").getNodeValue(), data.getNodeName(), data.getFirstChild().getNodeValue());
                                }
                            }
                            list.add(rowObject);
                            rowObject = new DataObject();
                        }
                    }
                }
            }
        }
        @SuppressWarnings("rawtypes")
        Hashtable[] ht = new Hashtable[list.size()];
        list.toArray(ht);
        dataObject.put("data", ht);
        System.out.println(ht);
        System.out.println(list);
        System.out.println(dataObject);
    }

    @SuppressWarnings("unchecked")
    public  static void processDataType(DataObject data, String type, String key, String value) throws ParseException{
        if(DATA_TYPE_BIGDECIMAL.equals(type)){
            data.put(key, new BigDecimal(value));
        }else if(DATA_TYPE_DATE.equals(type)){
            data.put(key, getSimpleDateFormat().parse(value));
        }else{
            data.put(key, value);
        }
    }

}
