package com.cristian.beans.dao;

import java.util.List;

import com.cristian.beans.pojo.Admin;

public interface AdminDao {
		public boolean save(Admin admin);

		public List<Admin> findAll();
		
		public Admin findId(int id);
		
		public List<Admin> findByNombre(String nombre);
		
		public boolean update(Admin admin);
		
		public boolean delete(int id);
		
		public int[] saveAll(List<Admin> admins);

}
