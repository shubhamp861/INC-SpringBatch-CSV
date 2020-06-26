package com.example.batch.DBWriter;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.batch.Model.User;

@Component
public class DBWriter implements ItemWriter<User> {

	@Override
	public void write(List<? extends User> user) throws Exception {

		System.out.println("item writer running");

	
		System.out.println("item writer save" + user);

	}

}
