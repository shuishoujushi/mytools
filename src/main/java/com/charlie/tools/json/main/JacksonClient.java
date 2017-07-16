package com.charlie.tools.json.main;

import com.charlie.tools.vo.PersonVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by charlie on 16/07/2017.
 */
public class JacksonClient {

    public void convert2Json() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        PersonVO personVO = new PersonVO("Charlie");
        personVO.setId(BigDecimal.ONE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(1987, 2, 29);
        personVO.setDob(calendar.getTime());

        String str = mapper.writeValueAsString(personVO);
        System.out.println(str);

        List<PersonVO> personVOs = new ArrayList<>();
        personVOs.add(personVO);
        String str2 = mapper.writeValueAsString(personVOs);
        System.out.println(str2);
    }

    public void convert2Object() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"id\":1,\"name\":\"Charlie\",\"dob\":544023139389}";
        PersonVO personVO = mapper.readValue(json, PersonVO.class);
        System.out.println(personVO);
    }


    public static void main(String[] args) throws IOException {
        JacksonClient jc = new JacksonClient();
        jc.convert2Json();
        jc.convert2Object();
    }
}
