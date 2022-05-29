package pl.home.artpicture.matned;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "employeesList")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeesList implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Employee> employees;

    public EmployeesList() {
        super();
    }

    public EmployeesList(List<Employee> employees) {
        super();
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "IcConfig{" +
                "employees=" + employees +
                '}';
    }
}
