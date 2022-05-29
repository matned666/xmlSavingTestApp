package pl.home.artpicture.matned;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URL;

public class EmployeesXMLReader {

    public static EmployeesList read() {
        URL resource = EmployeesList.class.getClassLoader().getResource("employeesList.xml");
        try {
            File xmlFile = new File(resource.toURI());
            JAXBContext jaxbContext = JAXBContext.newInstance(EmployeesList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (EmployeesList) jaxbUnmarshaller.unmarshal(xmlFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void save(EmployeesList employeesList) {
        URL resource = EmployeesList.class.getClassLoader().getResource("employeesList.xml");
        try {
            File xmlFile = new File(resource.toURI());
            JAXBContext jaxbContext = JAXBContext.newInstance(EmployeesList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.marshal(employeesList, xmlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
