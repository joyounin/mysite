package com.douzone.mysite.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public void join(UserVo vo) {
		userRepository.insert(vo);
	}

	public UserVo getUser(UserVo vo) {
		return userRepository.findByEmailandPassword(vo);
	}

	// 2023.01.30
//	public UserVo findByno(Long no) {
//		return userRepository.findByNo(no);
//	}
	
	public void update(HttpSession session, UserVo vo) {
		if(vo.getPassword().equals("")) {
			userRepository.userUpdate(vo);
		} else {
			userRepository.userpUpdate(vo);
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		authUser.setName(vo.getName());
	}

	public UserVo getUser(Long no) {
		return userRepository.findByNo(no);
	}

	public void updateUser(UserVo vo) {
		if(vo.getPassword().equals("")) {
			userRepository.userUpdate(vo);
		} else {
			userRepository.userpUpdate(vo);
		}
		
	}

	
}
