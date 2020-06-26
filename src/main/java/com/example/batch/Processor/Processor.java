package com.example.batch.Processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.stereotype.Component;

import com.example.batch.Model.User;
@Component
public class Processor implements ItemProcessor <User,User> {
	
	private static final Map<String,String> Dept = new HashMap<>();
	
    public Processor()
    {
    	Dept.put("ec", "lol");
    	Dept.put("cs", "pol");
    	Dept.put("ee", "tol");
    }
	@Override
	public User process(User user) throws Exception {
		
		String code=user.getDept();
		String dcode =Dept.get(code);
		user.setDept(dcode);
		System.out.println("items processor");
		return user;
		
	}

	

}
