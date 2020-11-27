package com.rubypaper.biz.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Service ����Ŭ����
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void insertUser(UserVO vo) {
		System.out.println("===> JDBC ������� insertUser() ��� ó��");
		userDAO.insertUser(vo);
	}

	@Override
	public UserVO getUser(UserVO vo) {
		System.out.println("===> JDBC ������� getUser() ��� ó��");
		return userDAO.getUser(vo);
	}

}