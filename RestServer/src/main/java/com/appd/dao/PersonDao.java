package com.appd.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.appd.pojo.Person;
import com.appd.util.JdbcUtil;

public class PersonDao {

	
	
	public List<Person> getPersonList() {
		List<Person> listOfPerson = new ArrayList<>();
		
		try (JdbcUtil jdbc = new JdbcUtil();){
            jdbc.open();
            jdbc.getConnection();
            Statement stmt = jdbc.con.createStatement();
            String sql = "select id,email_id,name from Person";
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
				Person p = new Person();
				p.setEmail_id(rs.getString("email_id"));
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				listOfPerson.add(p);
			}
            
        } catch (Exception e){
        		e.printStackTrace();
        }
		return listOfPerson;
	}

	public Person getPerson(int id) {
		// TODO Auto-generated method stub
		 Person p =null;
		try (JdbcUtil jdbc = new JdbcUtil();){
            jdbc.open();
            jdbc.getConnection();
            Statement stmt = jdbc.con.createStatement();
            String sql = "select id,email_id,name from Person where id = "+id;
            
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(sql);
            while (rs.next()) {
           p = new Person();
			p.setEmail_id(rs.getString("email_id"));
			p.setId(rs.getInt("id"));
			p.setName(rs.getString("name"));
            }
            rs.close();
        } catch (Exception e){
        		e.printStackTrace();
        		return null;
        }
		//System.out.println(p.getName());
		return p;
	}
}
