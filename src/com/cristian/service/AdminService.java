package com.cristian.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristian.beans.dao.AdminDao;
import com.cristian.beans.pojo.Admin;

@Service
public class AdminService {

	@Autowired
	
	private AdminDao dao;
	
	
	public boolean save(Admin admin) {
		
		admin.setFechaCreacion(new Timestamp(new Date().getTime()));
		
		return dao.save(admin);
		
	}


	public List<Admin> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}


	public Admin findById(int id) {
		// TODO Auto-generated method stub
		return dao.findId(id);
	}


	public boolean saveOrUpdate(Admin admin) {
		// TODO Auto-generated method stub
		if (admin.getId()==0) {
			//insert
			admin.setFechaCreacion(new Timestamp(new Date().getTime()));
			return dao.save(admin);
		}else {
			//update
			return dao.update(admin);
		}
		
	}


	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return dao.delete(id);
	}
}
