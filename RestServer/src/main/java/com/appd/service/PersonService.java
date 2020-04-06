package com.appd.service;

import java.util.List;

import com.appd.dao.PersonDao;
import com.appd.pojo.Person;

public class PersonService {

	public List<Person> getPersonList() {
		return new PersonDao().getPersonList();
	}

	public Person getPerson(int id) {
		// TODO Auto-generated method stub
		return new PersonDao().getPerson(id);
	}
}
