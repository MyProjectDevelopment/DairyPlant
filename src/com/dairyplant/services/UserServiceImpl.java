package com.dairyplant.services;

import java.util.List;

import com.dairyplant.dao.UserDAO;
import com.dairyplant.model.User;

public class UserServiceImpl implements GenericServiceProvider<User> {

	private UserDAO userDAO = new UserDAO();

	@Override
	public User add(User user) {
		return userDAO.add(user);
	}

	public void delete(String id) {
		userDAO.delete(id);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public User update(User user) {
		return userDAO.update(user);
	}

	@Override
	public List<User> retrieveAll() {
		return userDAO.getAllUsers();
	}

	public User retrieve(String id) {
		return userDAO.getUser(id);
	}

	@Override
	public User retrieve(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
