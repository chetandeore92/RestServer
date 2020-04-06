package com.appd.pojo;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "person")
public class Person {
int id;
String email_id,name;

public Person() {
	//System.out.println("Inside Person ctor");
}


public Person(int id, String email_id, String name) {
	
	this.id = id;
	this.email_id = email_id;
	this.name = name;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getEmail_id() {
	return email_id;
}
public void setEmail_id(String email_id) {
	this.email_id = email_id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((email_id == null) ? 0 : email_id.hashCode());
	result = prime * result + id;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Person other = (Person) obj;
	if (email_id == null) {
		if (other.email_id != null)
			return false;
	} else if (!email_id.equals(other.email_id))
		return false;
	if (id != other.id)
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	return true;
}


@Override
public String toString() {
	return "Person [id=" + id + ", email_id=" + email_id + ", name=" + name + "]";
}



}
