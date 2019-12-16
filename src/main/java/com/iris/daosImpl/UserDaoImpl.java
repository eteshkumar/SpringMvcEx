package com.iris.daosImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iris.daos.UserDao;
import com.iris.model.User;

@Repository("userdao")
@Transactional
public class UserDaoImpl implements UserDao{

	@Autowired
	SessionFactory sessionFactory ;
	
	@Transactional
	public boolean registerUser(User userObj) {
		try {
		Session session=sessionFactory.getCurrentSession();
		userObj.setRole("user");
		session.saveOrUpdate(userObj);
		return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public User validateUser(int Id, String password) {
		try {
			Session session=sessionFactory.getCurrentSession();
			User uobj=session.get(User.class, Id);
			
			if(uobj!=null) {
			if(uobj.getPassword().equals(password)) {
				return uobj;
			}
		}
			}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Transactional
	public List<User> getAllUser() {
		Session session=sessionFactory.getCurrentSession();
		Query q=session.createQuery("from com.iris.model.User where role='user'");
		List<User> Userlist=q.list();
		return Userlist;
	}

	public boolean deleteUser(User userObj) {
		try {
			Session session=sessionFactory.getCurrentSession();
			session.delete(userObj);
			return true;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return false;
		}
		

	public boolean updateUser(User userObj) {
		try {
			Session session=sessionFactory.getCurrentSession();
			session.update(userObj);
			return true;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return false;
		
	}

	public User getUserById(int userId) {
		try {
			Session session=sessionFactory.getCurrentSession();
			User userObj=session.get(User.class, userId);
			return userObj;
			
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
	}
	
	


