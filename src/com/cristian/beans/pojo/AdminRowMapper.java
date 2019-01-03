package com.cristian.beans.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AdminRowMapper implements RowMapper<Admin>{

	@Override
	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		Admin admin= new Admin();
		admin.setId(rs.getInt("id"));
		admin.setCargo(rs.getString("cargo"));
		admin.setNombre(rs.getString("nombre"));
		admin.setFechaCreacion(rs.getTimestamp("fechaCreacion"));;
		return admin;
	}

}
