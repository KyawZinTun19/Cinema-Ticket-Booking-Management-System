package com.cinemamanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cinemamanage.dto.TimeTableRequestDTO;
import com.cinemamanage.dto.TimeTableResponseDTO;



public class TimetableDAO {
	public static Connection con = null;
	static {
		con=MyConncection.getConnection();
	}

	public int insertData(TimeTableRequestDTO dto) {
		int result=0;
		String sql="insert into timetable(start_date,start_time,movie_id) values(?,?,?)";

		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getStart_date());
			ps.setString(2, dto.getStart_time());
			ps.setInt(3, dto.getMovie_id());
			result=ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}
		 return result;
	}
	
	public int updateData(TimeTableRequestDTO dto) {
		String sql="update timetable set start_date=?,start_time=?,movie_id=? where id=?";
		int result = 0;
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, dto.getStart_date());
			ps.setString(2, dto.getStart_time());
			ps.setInt(3, dto.getMovie_id());
			ps.setInt(4, dto.getTimeTableID());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}return result;
		 
	}
	
	public int deleteData(String timetableID) {
		int result = 0;
		String sql="delete from timetable where id=?";

		
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setNString(1, timetableID);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}return result;
		 
	}
	
	public TimeTableResponseDTO selectOne(TimeTableRequestDTO dto) {
		TimeTableResponseDTO res = new TimeTableResponseDTO();
		String sql="select * from timetable where id=?";

		
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, dto.getTimeTableID());
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				res.setTimeTableID(rs.getInt("id"));
				res.setStart_date(rs.getString("start_date"));
				res.setStart_time(rs.getString("start_time"));
				res.setMovie_id(rs.getInt("movie_id"));
			}
			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}
		return res;
	}
	
	public ArrayList<TimeTableResponseDTO> selectAll() {
		ArrayList<TimeTableResponseDTO> list = new ArrayList();
		String sql= "select * from account";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				TimeTableResponseDTO res = new TimeTableResponseDTO();
				res.setTimeTableID(rs.getInt("id"));
				res.setStart_date(rs.getString("start_date"));
				res.setStart_time(rs.getString("start_time"));
				res.setMovie_id(rs.getInt("movie_id"));
				list.add(res);  
			}
			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}
		return list;
	}
}
