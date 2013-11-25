package org.jmanderson.sonata.da;

/**
 * New DAO for the Users table.
 */


import org.hibernate.Session;
import org.jmanderson.sonata.hibernate.SessionFactory;
import org.jmanderson.sonata.hibernate.Users;

public class UsersDA {

	private UsersDA() {
	}

	public static Users getUser(String username) {

		Users user = null;
		try {
			Session session = SessionFactory.getSession();
			user = (Users) session.load(Users.class, username);
		} catch (Exception e) {
			// TODO logging
			System.out.println(e);
		} finally {
//			SessionFactory.closeSession();
		}

		return user;
	}

}
