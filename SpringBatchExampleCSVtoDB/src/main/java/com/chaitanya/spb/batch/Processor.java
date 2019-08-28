package com.chaitanya.spb.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.chaitanya.spb.model.User;

@Component
public class Processor implements ItemProcessor<User,User>{
private static final Map<String,String> DEPT_NAMES = new HashMap<>();

public Processor()
{
	DEPT_NAMES.put("001", "Technology");
	DEPT_NAMES.put("002", "Operations");
	DEPT_NAMES.put("003", "Accounts");
}
	
	@Override
	public User process(User item) throws Exception {
		// TODO Auto-generated method stub
		String deptCode=item.getDept();
		String dept = DEPT_NAMES.get(deptCode);
		item.setDept(dept);
		return null;
	}
	

}
