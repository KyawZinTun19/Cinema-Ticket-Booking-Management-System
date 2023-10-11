package com.cinemamanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.cinemamanage.dto.DetailTicketRequestDTO;
import com.cinemamanage.dto.DetailTicketResponseDTO;
import com.cinemamanage.dto.MovieRequestDTO;
import com.cinemamanage.dto.SeatHasTableRequestDTO;
import com.cinemamanage.dto.SeatHasTableResponseDTO;
import com.cinemamanage.dto.SeatRequestDTO;
import com.cinemamanage.dto.SeatResponseDTO;
import com.cinemamanage.dto.TimeTableRequestDTO;
import com.cinemamanage.dto.UserMovieDetailResponseDTO;

@Service("homePageDAO")
public class HomePageDAO {
	
	public static Connection con = null;
	static {
		con=MyConncection.getConnection();
	}

	public UserMovieDetailResponseDTO selectDetails(MovieRequestDTO dto) {
		UserMovieDetailResponseDTO res = new UserMovieDetailResponseDTO();
		String sql="SELECT tt.id AS id, m.movie_name as movie_name,"
				+ "tt.start_date AS start_date ,tt.start_time As start_time\r\n" + 
				"FROM movie m \r\n" + 
				"JOIN timetable tt ON m.id = tt.movie_id WHERE m.id=?;";

		
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, dto.getMovieID());
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				res.setTimetableID(rs.getInt("id"));
				res.setMovieName(rs.getNString("movie_name"));
				res.setStartDate(rs.getNString("start_date"));
				res.setStartTime(rs.getNString("start_time"));
				
			}
			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}
		return res;
	}
	
	public ArrayList<SeatHasTableResponseDTO> selectForSeat(TimeTableRequestDTO dto) {
		ArrayList<SeatHasTableResponseDTO> list = new ArrayList<SeatHasTableResponseDTO>();
		String sql="SELECT t.id AS id,s.seat_name, t.id\r\n" + 
				"FROM seat s\r\n" + 
				"JOIN seat_has_timetable sht ON s.id = sht.seat_id \r\n" + 
				"JOIN timetable t ON sht.timetable_id = t.id where t.id=?  and sht.status='occupied';";

		
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, dto.getTimeTableID());
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				SeatHasTableResponseDTO res = new SeatHasTableResponseDTO();

				res.setSeatName(rs.getString("seat_name"));
				//res.setTimetableID(rs.getInt("id"));
				System.out.print(res.getSeatName());
				list.add(res);
			}
//			 if (!rs.next()) {
//			        System.out.println("No results found.");}
			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}
		return list;
	}
	
	public SeatResponseDTO selectAllseatIDS(SeatRequestDTO dto) {
		ArrayList<SeatResponseDTO> list = new ArrayList();
		SeatResponseDTO res = new SeatResponseDTO();
		String sql= "select * from seat WHERE seat_name LIKE CONCAT('%',?, '%')";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getSeatName());
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				
				res.setSeatID(rs.getInt("id"));
				list.add(res);  
			}
			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}
		return res;
	}
	
	public ArrayList<DetailTicketResponseDTO> userTicketTable(DetailTicketRequestDTO dto){
		String sql = "SELECT \r\n" + 
				"    a.id AS account_id, \r\n" + 
				"    p.payment_status AS payment_status, \r\n" + 
				"    m.movie_name AS movie_name,\r\n" + 
				"    t.start_date AS start_date, \r\n" + 
				"    t.start_time AS start_time, \r\n" + 
				"    p.amount AS amount,\r\n" + 
				"    p.id as payment_id,\r\n" + 
				"    GROUP_CONCAT(DISTINCT s.seat_name ORDER BY s.seat_name SEPARATOR ', ') AS seat_names\r\n" + 
				"FROM \r\n" + 
				"    account a\r\n" + 
				"    JOIN payment p ON a.id = p.account_id\r\n" + 
				"    JOIN seat_has_timetable sht ON p.id = sht.payment_id\r\n" + 
				"    JOIN movie m ON m.id = p.movieid\r\n" + 
				"    JOIN seat s ON s.id = sht.seat_id \r\n" + 
				"    JOIN timetable t ON m.id = t.movie_id\r\n" + 
				"WHERE \r\n" + 
				"    a.id = ? \r\n" + 
				"    \r\n" + 
				"    AND p.payment_status = 'confirmed'\r\n" + 
				"GROUP BY \r\n" + 
				"    a.id, \r\n" + 
				"    p.payment_status,\r\n" + 
				"    m.movie_name, \r\n" + 
				"    t.start_date, \r\n" + 
				"    t.start_time, \r\n" + 
				"    p.id,\r\n" + 
				"    p.amount;";
		ArrayList<DetailTicketResponseDTO> list = new ArrayList<DetailTicketResponseDTO>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getAccountId());
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				DetailTicketResponseDTO res = new DetailTicketResponseDTO();
				res.setMovieName(rs.getNString("movie_name"));
				res.setAccountId(rs.getInt("account_id"));
				res.setPayment_id(rs.getInt("payment_id"));
				//res.setStartDate(rs.getNString("start_date"));
				//res.setStartTime(rs.getNString("start_time"));
				list.add(res);
			}
			
			
		}catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
	}return list;
	}
	
	public ArrayList<DetailTicketResponseDTO> userRejectedTicketTable(DetailTicketRequestDTO dto){
	    String sql = "SELECT \r\n" + 
	        "    a.id AS account_id, \r\n" + 
	        "    p.payment_status AS payment_status, \r\n" + 
	        "    m.movie_name AS movie_name,\r\n" + 
	        "    t.start_date AS start_date, \r\n" + 
	        "    t.start_time AS start_time, \r\n" + 
	        "    p.amount AS amount,\r\n" + 
	        "    p.id as payment_id,\r\n" + 
	        "    GROUP_CONCAT(DISTINCT s.seat_name ORDER BY s.seat_name SEPARATOR ', ') AS seat_names\r\n" + 
	        "FROM \r\n" + 
	        "    account a\r\n" + 
	        "    JOIN payment p ON a.id = p.account_id\r\n" + 
	        "    JOIN seat_has_timetable sht ON p.id = sht.payment_id\r\n" + 
	        "    JOIN movie m ON m.id = p.movieid\r\n" + 
	        "    JOIN seat s ON s.id = sht.seat_id \r\n" + 
	        "    JOIN timetable t ON m.id = t.movie_id\r\n" + 
	        "WHERE \r\n" + 
	        "    a.id = ? \r\n" + 
	        "    \r\n" + 
	        "    AND p.payment_status = 'rejected'\r\n" + 
	        "GROUP BY \r\n" + 
	        "    a.id, \r\n" + 
	        "    p.payment_status,\r\n" + 
	        "    m.movie_name, \r\n" + 
	        "    t.start_date, \r\n" + 
	        "    t.start_time, \r\n" + 
	        "    p.id,\r\n" + 
	        "    p.amount;";
	    ArrayList<DetailTicketResponseDTO> list = new ArrayList<DetailTicketResponseDTO>();
	    try {
	      PreparedStatement ps = con.prepareStatement(sql);
	      ps.setInt(1, dto.getAccountId());
	      ResultSet rs= ps.executeQuery();
	      while(rs.next()) {
	        DetailTicketResponseDTO res = new DetailTicketResponseDTO();
	        res.setMovieName(rs.getString("movie_name"));
	        res.setAccountId(rs.getInt("account_id"));
	        res.setPayment_id(rs.getInt("payment_id"));
	        //res.setStartDate(rs.getNString("start_date"));
	        //res.setStartTime(rs.getNString("start_time"));
	        list.add(res);
	      }
	      
	      
	    }catch (SQLException e) {
	      System.out.println("DataBase Error "+e); 
	  }return list;}
	  
	
	public ArrayList<DetailTicketResponseDTO> userPendingTicketTable(DetailTicketRequestDTO dto){
		String sql = "SELECT \r\n" + 
				"    a.id AS account_id, \r\n" + 
				"    p.payment_status AS payment_status, \r\n" + 
				"    m.movie_name AS movie_name,\r\n" + 
				"    t.start_date AS start_date, \r\n" + 
				"    t.start_time AS start_time, \r\n" + 
				"    p.amount AS amount,\r\n" + 
				"    p.id as payment_id,\r\n" + 
				"    GROUP_CONCAT(DISTINCT s.seat_name ORDER BY s.seat_name SEPARATOR ', ') AS seat_names\r\n" + 
				"FROM \r\n" + 
				"    account a\r\n" + 
				"    JOIN payment p ON a.id = p.account_id\r\n" + 
				"    JOIN seat_has_timetable sht ON p.id = sht.payment_id\r\n" + 
				"    JOIN movie m ON m.id = p.movieid\r\n" + 
				"    JOIN seat s ON s.id = sht.seat_id \r\n" + 
				"    JOIN timetable t ON m.id = t.movie_id\r\n" + 
				"WHERE \r\n" + 
				"    a.id = ? \r\n" + 
				"    \r\n" + 
				"    AND p.payment_status = 'pending'\r\n" + 
				"GROUP BY \r\n" + 
				"    a.id, \r\n" + 
				"    p.payment_status,\r\n" + 
				"    m.movie_name, \r\n" + 
				"    t.start_date, \r\n" + 
				"    t.start_time, \r\n" + 
				"    p.id,\r\n" + 
				"    p.amount;";
		ArrayList<DetailTicketResponseDTO> list = new ArrayList<DetailTicketResponseDTO>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getAccountId());
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				DetailTicketResponseDTO res = new DetailTicketResponseDTO();
				res.setMovieName(rs.getString("movie_name"));
				res.setAccountId(rs.getInt("account_id"));
				res.setPayment_id(rs.getInt("payment_id"));
				//res.setStartDate(rs.getNString("start_date"));
				//res.setStartTime(rs.getNString("start_time"));
				list.add(res);
			}
			
			
		}catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
	}return list;
	}
	
	
	
	public DetailTicketResponseDTO forDetailTicket(DetailTicketRequestDTO dto){
		String sql = "SELECT \r\n" + 
				"    a.id AS account_id, \r\n" + 
				"    p.payment_status AS payment_status, \r\n" + 
				"    m.movie_name AS movie_name,\r\n" + 
				"    t.start_date AS start_date, \r\n" + 
				"    t.start_time AS start_time, \r\n" + 
				"    p.amount AS amount,\r\n" + 
				"    p.id as payment_id,\r\n" + 
				"    GROUP_CONCAT(DISTINCT s.seat_name ORDER BY s.seat_name SEPARATOR ', ') AS seat_names\r\n" + 
				"FROM \r\n" + 
				"    account a\r\n" + 
				"    JOIN payment p ON a.id = p.account_id\r\n" + 
				"    JOIN seat_has_timetable sht ON p.id = sht.payment_id\r\n" + 
				"    JOIN movie m ON m.id = p.movieid\r\n" + 
				"    JOIN seat s ON s.id = sht.seat_id \r\n" + 
				"    JOIN timetable t ON m.id = t.movie_id\r\n" + 
				"WHERE \r\n" + 
				"    a.id = ? \r\n" + 
				"    AND p.id = ?\r\n" + 
				"    AND p.payment_status = 'confirmed'\r\n" + 
				"GROUP BY \r\n" + 
				"    a.id, \r\n" + 
				"    p.payment_status,\r\n" + 
				"    m.movie_name, \r\n" + 
				"    t.start_date, \r\n" + 
				"    t.start_time, \r\n" + 
				"    p.id,\r\n" + 
				"    p.amount;\r\n" + 
				"\r\n" + 
				"";
		//ArrayList<DetailTicketResponseDTO> list = new ArrayList<DetailTicketResponseDTO>();
		DetailTicketResponseDTO res = new DetailTicketResponseDTO();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getAccountId());
			ps.setInt(2, dto.getPayment_id());
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				res.setPayment_id(rs.getInt("payment_id"));
				res.setMovieName(rs.getNString("movie_name"));
				res.setAmount(rs.getInt("amount"));
				res.setSeatName(rs.getString("seat_names"));
				res.setStartDate(rs.getString("start_date"));
				res.setStartTime(rs.getString("start_time"));
				//list.add(res);
			}
			
			
		}catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
	}return res;
	}
	
	 
}
