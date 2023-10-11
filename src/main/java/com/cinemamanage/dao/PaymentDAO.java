package com.cinemamanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.cinemamanage.dto.PaymentRequestDTO;
import com.cinemamanage.dto.PaymentResponseDTO;



public class PaymentDAO {
	public static Connection con = null;
	static {
		con=MyConncection.getConnection();
	}

	public int insertData(PaymentRequestDTO dto) {
		int result=0;
		String sql="insert into payment(transaction_image,phone,time,account_id,payment_method_id,payment_status,movieid,amount) values(?,?,?,?,?,?,?,?)";

		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getTransctionImage());
			ps.setString(2, dto.getPhone());
			ps.setTimestamp(3, Timestamp.valueOf(dto.getPaymentTime()));
			ps.setInt(4,dto.getAccountID());
			ps.setInt(5,dto.getPaymentMethodID());
			ps.setString(6,dto.getPaymentStatus());
			ps.setInt(7,dto.getMovieID());
			ps.setInt(8,dto.getAmount());
			result=ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}
		 return result;
	}
	
	public int updateData(PaymentRequestDTO dto) {
		String sql="update payment set transaction_image=?,phone=?,time=?,account_id=?,payment_method_id=?,payment_status=?,movieid=?,amount=? where id=?";
		int result = 0;
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getTransctionImage());
			ps.setString(2, dto.getPhone());
			ps.setTimestamp(3, Timestamp.valueOf(dto.getPaymentTime()));
			ps.setInt(4,dto.getAccountID());
			ps.setInt(5,dto.getPaymentMethodID());
			ps.setInt(6,dto.getPaymentID());
			ps.setString(7,dto.getPaymentStatus());
			ps.setInt(8,dto.getMovieID());
			ps.setInt(9,dto.getAmount());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}return result;
		 
	}
	
	public int deleteData(String paymentID) {
		int result = 0;
		String sql="delete from payment where id=?";

		
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, paymentID);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}return result;
		 
	}
	
	public PaymentResponseDTO selectOne(PaymentRequestDTO dto) {
		PaymentResponseDTO res = new PaymentResponseDTO();
		String sql="select * from payment where id=?";

		
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, dto.getAccountID());
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				res.setPaymentID(rs.getInt("id"));
				res.setTransctionImage(rs.getString("transaction_image"));
				res.setPhone(rs.getString("phone"));
				res.setPaymentTime(rs.getTimestamp("time").toLocalDateTime());
				res.setAccountID(rs.getInt("account_id"));
				res.setPaymentMethodID(rs.getInt("payment_method_id"));
				res.setMovieID(rs.getInt("movieid"));
				res.setAmount(rs.getInt("amount"));
			}
			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}
		return res;
	}
	
	public ArrayList<PaymentResponseDTO> selectAll() {
		ArrayList<PaymentResponseDTO> list = new ArrayList();
		String sql= "select * from payment";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				PaymentResponseDTO res = new PaymentResponseDTO();
				res.setPaymentID(rs.getInt("id"));
				res.setTransctionImage(rs.getString("transaction_image"));
				res.setPhone(rs.getString("phone"));
				res.setPaymentTime(rs.getTimestamp("time").toLocalDateTime());
				res.setAccountID(rs.getInt("account_id"));
				res.setPaymentMethodID(rs.getInt("payment_method_id"));
				res.setMovieID(rs.getInt("movieid"));
				res.setAmount(rs.getInt("amount"));
				list.add(res);  
			}
			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}
		return list;
	}
	
	public ArrayList<PaymentResponseDTO> selectAllPendingPayment(){
		ArrayList<PaymentResponseDTO> datalist = new ArrayList<PaymentResponseDTO>();
		String str = "select * from payment where payment_status in ('pending')";
		try {
			PreparedStatement ps = con.prepareStatement(str);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				PaymentResponseDTO res = new PaymentResponseDTO();
				res.setPaymentID(rs.getInt("id"));
				res.setTransctionImage(rs.getString("transaction_image"));
				res.setPhone(rs.getString("phone"));
				res.setPaymentTime(rs.getTimestamp("time").toLocalDateTime());
				res.setAccountID(rs.getInt("account_id"));
				res.setPaymentMethodID(rs.getInt("payment_method_id"));
				res.setPaymentStatus(rs.getString("payment_status"));
				res.setMovieID(rs.getInt("movieid"));
				res.setAmount(rs.getInt("amount"));
				datalist.add(res);  
			}
			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e);
		}
		return datalist;
	}
	
	public PaymentResponseDTO selectOnePayment(int id) {
		PaymentResponseDTO data = new PaymentResponseDTO();
		String str = "select * from payment where id=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(str);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				data.setPaymentID(rs.getInt("id"));
				data.setPhone(rs.getString("phone"));
				data.setTransctionImage(rs.getString("transaction_image"));
				data.setPaymentTime(rs.getTimestamp("time").toLocalDateTime());
				data.setPaymentMethodID(rs.getInt("payment_method_id"));
				data.setMovieID(rs.getInt("movieid"));
				data.setAmount(rs.getInt("amount"));
				data.setAccountID(rs.getInt("account_id"));
				data.setPaymentStatus(rs.getNString("payment_status"));
			
			}
			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e);
		}
		return data;
	}
	
	public int updatePaymentStatus(PaymentRequestDTO dto) {
		String str = "update payment set payment_status=? where id=?";
		int rs = 0;
		try {
			PreparedStatement ps = con.prepareStatement(str);
			ps.setString(1,dto.getPaymentStatus());
			ps.setInt(2,dto.getPaymentID());
			
			rs=ps.executeUpdate();
		} catch (SQLException e) {
			 System.out.print(e);
		}
		return rs;
	}
	public PaymentResponseDTO selectOneLast() {
		PaymentResponseDTO data = new PaymentResponseDTO();
		String str = "SELECT * FROM payment ORDER BY id DESC LIMIT 1;";
		
		try {
			PreparedStatement ps = con.prepareStatement(str);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				data.setPaymentID(rs.getInt("id"));
				data.setPhone(rs.getString("phone"));
				data.setTransctionImage(rs.getString("transaction_image"));
				data.setPaymentTime(rs.getTimestamp("time").toLocalDateTime());
				data.setPaymentMethodID(rs.getInt("payment_method_id"));
				data.setMovieID(rs.getInt("movieid"));
				data.setAmount(rs.getInt("amount"));
				data.setAccountID(rs.getInt("account_id"));
			
			}
			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e);
		}
		return data;
	}
	
	public ArrayList<PaymentResponseDTO> selectAllNonPending() {
		ArrayList<PaymentResponseDTO> list = new ArrayList();
		String sql= "SELECT * FROM payment WHERE payment_status = 'confirmed';";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				PaymentResponseDTO res = new PaymentResponseDTO();
				res.setPaymentID(rs.getInt("id"));
				res.setTransctionImage(rs.getString("transaction_image"));
				res.setPhone(rs.getString("phone"));
				res.setPaymentTime(rs.getTimestamp("time").toLocalDateTime());
				res.setAccountID(rs.getInt("account_id"));
				res.setPaymentMethodID(rs.getInt("payment_method_id"));
				res.setMovieID(rs.getInt("movieid"));
				res.setAmount(rs.getInt("amount"));
				list.add(res);  
			}
			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}
		return list;
	}
	
	public ArrayList<PaymentResponseDTO> selectAllConfirmedPayment(){
		ArrayList<PaymentResponseDTO> datalist = new ArrayList<PaymentResponseDTO>();
		String str = "select * from payment where payment_status in ('confirmed')";
		try {
			PreparedStatement ps = con.prepareStatement(str);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				PaymentResponseDTO res = new PaymentResponseDTO();
				res.setPaymentID(rs.getInt("id"));
				res.setTransctionImage(rs.getString("transaction_image"));
				res.setPhone(rs.getString("phone"));
				res.setPaymentTime(rs.getTimestamp("time").toLocalDateTime());
				res.setAccountID(rs.getInt("account_id"));
				res.setPaymentMethodID(rs.getInt("payment_method_id"));
				res.setPaymentStatus(rs.getString("payment_status"));
				res.setMovieID(rs.getInt("movieid"));
				res.setAmount(rs.getInt("amount"));
				datalist.add(res);  
			}
			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e);
		}
		return datalist;
	}
	
	public ArrayList<PaymentResponseDTO> selectAllRejectedPayment(){
		ArrayList<PaymentResponseDTO> datalist = new ArrayList<PaymentResponseDTO>();
		String str = "select * from payment where payment_status in ('rejected')";
		try {
			PreparedStatement ps = con.prepareStatement(str);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				PaymentResponseDTO res = new PaymentResponseDTO();
				res.setPaymentID(rs.getInt("id"));
				res.setTransctionImage(rs.getString("transaction_image"));
				res.setPhone(rs.getString("phone"));
				res.setPaymentTime(rs.getTimestamp("time").toLocalDateTime());
				res.setAccountID(rs.getInt("account_id"));
				res.setPaymentMethodID(rs.getInt("payment_method_id"));
				res.setPaymentStatus(rs.getString("payment_status"));
				res.setMovieID(rs.getInt("movieid"));
				res.setAmount(rs.getInt("amount"));
				datalist.add(res);  
			}
			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e);
		}
		return datalist;
	}
	
}
