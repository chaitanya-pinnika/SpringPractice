package com.chaitanya.spb.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chaitanya.spb.model.User;
import com.chaitanya.spb.repo.UserRepository;

@Component
public class DBWriter implements ItemWriter<User> {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void write(List<? extends User> users) throws Exception {
		
		userRepository.saveAll(users);
		System.out.println("date saved for:"+users);
		
	}
	

}
