package com.employee.management.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employee.management.model.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void saveEmployee(Employee employee) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(employee);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<Employee> employeeList() {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Employee> employeeList = session.createCriteria(Employee.class).list();
		tx.commit();
		session.close();
		return employeeList;
	}

	public Employee getEmployee(int id) {
		Session session = sessionFactory.openSession();
		return (Employee) session.get(Employee.class, id);
	}

	public String updatePassword(int id, String password, String confirmedPassword) {
		final String HQL_QRY_FOR_UPDATE = "update Employee e set e.password=?,e.confirmPassword=? where e.id=?";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(HQL_QRY_FOR_UPDATE);
		query.setParameter(2, id);
		query.setParameter(0, password);
		query.setParameter(1, confirmedPassword);
		Transaction tx = session.beginTransaction();
		int i = query.executeUpdate();
		tx.commit();
		session.close();
		return i + " Record Update Successfull..";
	}

	public String deleteEmployee(int id) {
		final String HQL_QRY_FOR_DELETE = "delete from Employee e where e.id=?";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(HQL_QRY_FOR_DELETE);
		query.setParameter(0, id);
		Transaction tx = session.beginTransaction();
		int i = query.executeUpdate();
		tx.commit();
		session.close();
		return i + " Record Deleted";
	}

}
