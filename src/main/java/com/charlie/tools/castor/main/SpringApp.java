package com.charlie.tools.castor.main;

import com.charlie.tools.vo.CustomerVO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by charlie on 15/07/2017.
 */
public class SpringApp {
    private static final String FILE_NAME = "customer.xml";
    private CustomerVO customerVO = new CustomerVO(BigDecimal.ONE, "charlie");
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public void saveCustomer() {
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(FILE_NAME);
            this.marshaller.marshal(customerVO, new StreamResult(os));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadCustomer() {
        FileInputStream is = null;
        try {
            is = new FileInputStream(FILE_NAME);
            this.customerVO = (CustomerVO) this.unmarshaller.unmarshal(new StreamSource(is));
            System.out.println("customerVO = " + customerVO);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-context.xml");
        SpringApp springApplication = (SpringApp) context.getBean("springApplication");
        springApplication.saveCustomer();
        springApplication.loadCustomer();
    }
}
