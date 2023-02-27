package skypro.service;

import org.springframework.stereotype.Service;
import skypro.dao.EmployeeDAO;
import skypro.entity.Employee;
import skypro.exception.EmployeeException;
import skypro.service.EmployeeService;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    @Transactional
    public List<Employee> getEmployees() {
        return employeeDAO.getEmployees();
    }

    @Override
    @Transactional
    public Employee getEmployeeById(int id) {
        Employee employee = employeeDAO.getEmployeeById(id);

        if (employee == null) {
            throw new EmployeeException("Person with id = " + id + " doesn't exist");
        }
        return employee;
    }

    @Override
    @Transactional
    public void addEmployee(Employee employee) {
        employeeDAO.addEmployee(employee);
    }

    @Override
    @Transactional
    public void updateEmployee(Employee employee) {
        employeeDAO.updateEmployee(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(int id) {
        Employee employee = employeeDAO.getEmployeeById(id);

        if (employee == null) {
            throw new EmployeeException("Person with id = " + id + " doesn't exist");
        }
        employeeDAO.deleteEmployee(id);
    }
}
