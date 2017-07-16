package com.charlie.tools.castor.main;

import com.charlie.tools.vo.MyOrder;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.xml.sax.InputSource;

import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStreamWriter;

/**
 * Created by charlie on 16/07/2017.
 */
public class ClientApp {
    public static void main(String args[]) {

        Mapping mapping = new Mapping();

        try {
            // 1. Load the mapping information from the file
            InputStream is = ClientApp.class.getResourceAsStream("/mapping/mapping-order.xml");
            mapping.loadMapping(new InputSource(is));

            // 2. Unmarshal the data
            Unmarshaller unmar = new Unmarshaller(mapping);
            MyOrder order = (MyOrder) unmar.unmarshal(new InputSource(new FileReader("order.xml")));

            // 3. Do some processing on the data
            float total = order.getTotalPrice();
            System.out.println("Order total price = " + total);
            order.setTotal(total);

            // 4. marshal the data with the total price back and print the XML in the console
            Marshaller marshaller = new Marshaller(new OutputStreamWriter(System.out));
            marshaller.setMapping(mapping);
            marshaller.marshal(order);

        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
