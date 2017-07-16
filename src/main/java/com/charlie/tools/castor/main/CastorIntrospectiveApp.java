package com.charlie.tools.castor.main;

import com.charlie.tools.vo.PersonVO;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Introspective mode
 * Created by charlie on 16/07/2017.
 */

public class CastorIntrospectiveApp {
    private String PERSON_XML = "person.xml";

    public void marshal() throws IOException, MarshalException, ValidationException {
        PersonVO personVO = new PersonVO("Charlie");
        personVO.setId(BigDecimal.ONE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(1987, 2, 29);
        personVO.setDob(calendar.getTime());

        FileWriter writer = new FileWriter(PERSON_XML);
        Marshaller.marshal(personVO, writer);
    }

    public void unmarshal() throws FileNotFoundException, MarshalException, ValidationException {
        PersonVO personVO = (PersonVO) Unmarshaller.unmarshal(PersonVO.class, new FileReader(PERSON_XML));
        System.out.println(personVO);
    }

    public static void main(String[] args) throws MarshalException, IOException, ValidationException {
        CastorIntrospectiveApp ca = new CastorIntrospectiveApp();
        ca.marshal();
        ca.unmarshal();
    }
}
