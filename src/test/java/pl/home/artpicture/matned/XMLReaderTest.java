package pl.home.artpicture.matned;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;


class XMLReaderTest {

    @Test
    void Should_ReturnData_From_icConfigTest() throws URISyntaxException {
        EmployeesList config = EmployeesXMLReader.read();

        System.out.println(config);
    }


}