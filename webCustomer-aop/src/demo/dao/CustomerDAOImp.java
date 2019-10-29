package demo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import demo.entity.Customer;

@Repository
public class CustomerDAOImp implements CustomerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	//@Transactional
	public List<Customer> getCustomers() {
		//get current hibernet session
		Session currentSession = sessionFactory.getCurrentSession();
		//create query
		Query<Customer> query = currentSession.createQuery("from Customer order by lastName", Customer.class);
		//execute query and get result
		List<Customer> customers = query.getResultList();
		//return result
 		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Customer theCustomer = currentSession.get(Customer.class, theId);
 		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", theId);
		query.executeUpdate();
	}

}
