package com.employee.management.service;

import java.io.File;
import java.util.List;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.dao.EmployeeDAO;
import com.employee.management.model.Employee;
import com.employee.management.model.EmployeeXMLData;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static String FILE_PATH = "C:/Users/BASANTA/Desktop/TEST/employee.xml";

	@Autowired
	EmployeeDAO employeeDAO;

	@Transactional
	public void saveEmployee(Employee employee) {
		convertJavaToXML(FILE_PATH, employee);
		employeeDAO.saveEmployee(convertXMLToJava(FILE_PATH));
	}

	private void convertJavaToXML(String FileNameToBeGenerate, Employee e) {
		EmployeeXMLData employeeXMLData = new EmployeeXMLData(
				e.getEmployeeId(), e.getFirstName(), e.getMiddleName(),
				e.getLastName(), e.getPassword(), e.getConfirmPassword(),
				e.getEmailId());
		try {
			JAXBContext context = JAXBContext
					.newInstance(EmployeeXMLData.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(employeeXMLData, new File(FileNameToBeGenerate));
		} catch (JAXBException ex) {
			ex.printStackTrace();
		}
	}

	public static Employee convertXMLToJava(String xmlFilePath) {
		Employee e = null;
		EmployeeXMLData employeeXMLData = null;
		try {
			File file = new File(xmlFilePath);
			JAXBContext jaxbContext = JAXBContext
					.newInstance(EmployeeXMLData.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			employeeXMLData = (EmployeeXMLData) jaxbUnmarshaller
					.unmarshal(file);
			e = new Employee(employeeXMLData.getEmployeeId(),
					employeeXMLData.getFirstName(),
					employeeXMLData.getMiddleName(),
					employeeXMLData.getLastName(),
					employeeXMLData.getPassword(),
					employeeXMLData.getConfirmPassword(),
					employeeXMLData.getEmailId());
		} catch (JAXBException ex) {
			ex.printStackTrace();
		}
		return e;

	}
}
