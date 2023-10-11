package com.cinemamanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.cinemamanage.dto.SeatRequestDTO;
import com.cinemamanage.dto.SeatResponseDTO;

@Service("seatDAO")
public class SeatDAO {
	public static Connection con = null;
	static {
		con=MyConncection.getConnection();
	}


	
	public int updateData(SeatRequestDTO dto) {
		String sql = "UPDATE seat SET seat_price = ? WHERE seat_row LIKE CONCAT(?, '%')";

		int result = 0;
		
		try {
		    PreparedStatement ps = con.prepareStatement(sql);
		    ps.setInt(1, dto.getSeatPrice());
		    ps.setString(2, dto.getSeatName());
		    result = ps.executeUpdate();
		} catch (SQLException e) {
		    System.out.println("Database Error " + e); 
		
		
		}return result;
		 
	}
	

	
	public SeatResponseDTO selectOne(SeatRequestDTO dto) {
	    SeatResponseDTO res = new SeatResponseDTO();
	    String sql = "SELECT * FROM seat WHERE  id = ?";

	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	       // ps.setString(1, dto.getSeatName());
	        ps.setInt(1, dto.getSeatID());

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            res.setSeatRow(rs.getString("seat_row"));
	            res.setSeatPrice(rs.getInt("seat_price"));
	        }
	    } catch (SQLException e) {
	        System.out.println("DataBase Error "+e+" from one"); 
	    }

	    return res;
	}


	
	public ArrayList<SeatResponseDTO> selectAll() {
		ArrayList<SeatResponseDTO> list = new ArrayList();
		String sql= "select * from seat";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				SeatResponseDTO res = new SeatResponseDTO();
				res.setSeatID(rs.getInt("id"));
				res.setSeatName(rs.getString("seat_name"));
				res.setSeatPrice(rs.getInt("seat_price"));
				list.add(res);  
			}
			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}
		return list;
	}
}
