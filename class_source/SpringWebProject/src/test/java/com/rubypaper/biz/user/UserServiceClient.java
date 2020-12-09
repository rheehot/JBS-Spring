package com.rubypaper.biz.user;

import org.springframework.context.support.GenericXmlApplicationContext;

public class UserServiceClient {
	public static void main(String[] args) {
		GenericXmlApplicationContext container =
				new GenericXmlApplicationContext("business-*.xml");
		
		UserService userService = (UserService) container.getBean("userService");
		
		UserVO vo = new UserVO();

//		vo.setId("Spring");
//		vo.setPassword("Test");
//		vo.setName("����");
//		vo.setRole("ģ��");
//		
//		userService.insertUser(vo);
	
		vo.setId("admin");
		vo.setPassword("admin");
		UserVO user = userService.getUser(vo);
		if(user != null) {
			System.out.println(user.getName() + "�� ȯ���մϴ�.");
		} else {
			System.out.println("ȸ�� id�� Ȯ�����ּ���");
		}
		
		container.close();
	
	}
	
}