package com.cinemamanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cinemamanage.dto.AccountRequestDTO;
import com.cinemamanage.dto.SeatHasTableRequestDTO;
import com.cinemamanage.dto.SeatHasTableResponseDTO;

public class SeatHasTimetableDAO {
  public static Connection con = null;
  static {
    con=MyConncection.getConnection();
  }
  public int insert(SeatHasTableRequestDTO dto) {
    int result=0;
    String sql="insert into seat_has_timetable(seat_id,timetable_id,payment_id,status) values(?,?,?,?)";
    try {
      PreparedStatement ps=con.prepareStatement(sql);
      ps.setInt(1, dto.getSeatID());
      ps.setInt(2, dto.getTimetableID());
      ps.setInt(3, dto.getPaymentID());
      ps.setString(4, dto.getStatus());
      result=ps.executeUpdate();
    } catch (SQLException e) {
      System.out.println("Database error " + e);
    }
    return result;
    
  }
  
	public int updateData(SeatHasTableRequestDTO dto) {
		String sql="update seat_has_timetable set status=? where payment_id=?";
		int result = 0;
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
		      ps.setString(1, dto.getStatus());
		      ps.setInt(2 ,dto.getPaymentID());
		     
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}return result;
		 
	}
  

  public SeatHasTableResponseDTO selectOne(SeatHasTableRequestDTO dto) {
    SeatHasTableResponseDTO res = new SeatHasTableResponseDTO();
    String sql="select * from seat_has_timetable where id=?";

    
    try {
      PreparedStatement ps = con.prepareStatement(sql);

      ps.setInt(1,dto.getId()) ;
      ResultSet rs= ps.executeQuery();
    while(rs.next()) {
      res.setId(rs.getInt("id"));  
      res.setSeatID(rs.getInt("seat_id"));
      res.setPaymentID(rs.getInt("payment_id"));
      res.setTimetableID(rs.getInt("timetable_id"));
              }
      
    } catch (SQLException e) {
      System.out.println("DataBase Error "+e); 
    }
    return res;
  }
  
  public ArrayList<SeatHasTableResponseDTO> selectAll() {
    ArrayList<SeatHasTableResponseDTO> list = new ArrayList();
    String sql= "select * from seat_has_timetable";
    try {
      PreparedStatement ps = con.prepareStatement(sql);
      ResultSet rs= ps.executeQuery();
      while(rs.next()) {
        SeatHasTableResponseDTO res = new SeatHasTableResponseDTO();
        res.setId(rs.getInt("id"));  
        res.setSeatID(rs.getInt("seat_id"));
        res.setPaymentID(rs.getInt("payment_id"));
        res.setTimetableID(rs.getInt("timetable_id"));
        list.add(res);  
      }
      
    } catch (SQLException e) {
      System.out.println("DataBase Error "+e); 
    }
    return list;
  }
}