package com.cristian.beans.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cristian.beans.pojo.Admin;
import com.cristian.beans.pojo.AdminRowMapper;

@Component("adminDao")
public class AdminDaoImpl implements AdminDao {
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
    private void setDataSource(DataSource data){
        this.jdbcTemplate= new NamedParameterJdbcTemplate(data);
    }

	@Override
	public boolean save(Admin admin) {
//		  MapSqlParameterSource p= new MapSqlParameterSource();
//	        p.addValue("nombre", admin.getNombre());
//	        p.addValue("cargo", admin.getCargo());
//	        p.addValue("fechaCreacion", admin.getFechaCreacion());
	        
		
		// esta linea permite optimizar  las lineas anteriores con el uso de beans, 
		//que construye automáticamente un Map con los nombres de las propiedades y sus correspondientes valores
		//para que este comando funcione los atributos de la clase deben llamarse igual a los campos 
		//de la base de datos
	        BeanPropertySqlParameterSource map= new BeanPropertySqlParameterSource(admin);
	        
	        return jdbcTemplate.update("insert into Admin (nombre, cargo,fechaCreacion)"
	                + "values(:nombre,:cargo, :fechaCreacion)",map)==1;
	}
	
	@Override
	
	public List<Admin> findAll(){
		
		/* 
		 * rowMapper es una interfaz que se encarga de asociar todas las tuplas a los camoos del objeto
		 */
		return jdbcTemplate.query("select * from Admin", new RowMapper<Admin>() {

			@Override
			public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				Admin admin= new Admin();
				admin.setId(rs.getInt("id"));
				admin.setCargo(rs.getString("cargo"));
				admin.setNombre(rs.getString("nombre"));
				admin.setFechaCreacion(rs.getTimestamp("fechaCreacion"));;
				return admin;
			}
		});
	}

	@Override
	public Admin findId(int id) {
		// MapSqlParameterSurce() evita la inyección por sql
		return  jdbcTemplate.queryForObject("select * from Admin where id=:id",
				new MapSqlParameterSource("id",id), new AdminRowMapper());
	}

	@Override
	public List<Admin> findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from Admin where nombre like :nombre"
				+ "", new MapSqlParameterSource("nombre", "%"+nombre+"%"), new AdminRowMapper());
	} 

	@Override
	public boolean update(Admin admin) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("update Admin set nombre=:nombre, cargo=:cargo, fechaCreacion"
				+ "=:fechaCreacion where id=:id", new BeanPropertySqlParameterSource(admin))==1;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("delete from Admin where id=:id", new MapSqlParameterSource("id",id))==1;
	}
	
    @Transactional
	@Override
	public int [] saveAll(List<Admin> admins) {
		// TODO Auto-generated method stub
		SqlParameterSource [] batcArrays= SqlParameterSourceUtils.createBatch(admins.toArray());
		return jdbcTemplate.batchUpdate("insert into Admin (nombre, cargo,fechaCreacion) values(:nombre,:cargo, :fechaCreacion)", batcArrays);
		
	}

}
