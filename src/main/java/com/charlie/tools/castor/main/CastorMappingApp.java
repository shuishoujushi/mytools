package com.charlie.tools.castor.main;

import com.charlie.tools.vo.PersonVO;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.*;
import org.xml.sax.InputSource;

import java.io.*;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Mapping mode
 * Created by charlie on 16/07/2017.
 */

public class CastorMappingApp {

    private XMLContext context;
    private Mapping mapping;
    private String PERSON_XML = "person2.xml";

    private void init() {
        mapping = new Mapping();
        context = new XMLContext();
        try {
            InputStream is = this.getClass().getResourceAsStream("/mapping/mapping-person.xml");
            mapping.loadMapping(new InputSource(is));
            context.addMapping(mapping);
        } catch (MappingException e) {
            System.err.println("Error in mapping: " + e);
            e.printStackTrace();
        }
    }

    public void unmarshal() {
        init();
        try {
            FileReader reader = new FileReader(PERSON_XML);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            unmarshaller.setClass(PersonVO.class);

            PersonVO personVO = (PersonVO) unmarshaller.unmarshal(reader);
            System.out.println(personVO);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MarshalException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public void marshal() {
        init();
        try {
            Writer writer = new FileWriter(PERSON_XML);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setWriter(writer);

            PersonVO personVO = new PersonVO("Charlie");
            personVO.setId(BigDecimal.ONE);
            Calendar calendar = Calendar.getInstance();
            calendar.set(1987, 2, 29);
            personVO.setDob(calendar.getTime());

            marshaller.marshal(personVO);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MarshalException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws MarshalException, IOException, ValidationException {
        CastorMappingApp ca = new CastorMappingApp();
        ca.marshal();
        ca.unmarshal();
    }
}
