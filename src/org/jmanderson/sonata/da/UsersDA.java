package org.jmanderson.sonata.da;

/**
 * New DAO for the Users table.
 */


import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.jmanderson.sonata.hibernate.Users;

public class UsersDA {

	private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();
	
	private UsersDA() {
	}

	public static Users getUser(String username) {

		Users user = null;
		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			user = (Users) session.load(Users.class, username);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO logging
			System.out.println(e);
			factory.getCurrentSession().getTransaction().rollback();
		} finally {
			factory.getCurrentSession().close();
		}

		return user;
	}

}
