package com.nnoco.golbang.example;

import com.nnoco.golbang.annotation.Configuration;
import com.nnoco.golbang.annotation.Request;

@Configuration
public class ApiClient {
	@Request("/user")
	public void getUser(String id) {
		
	}
}
