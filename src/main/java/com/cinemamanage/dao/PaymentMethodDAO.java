package com.cinemamanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.cinemamanage.dto.AccountRequestDTO;
import com.cinemamanage.dto.AccountResponseDTO;
import com.cinemamanage.dto.PaymentMethodRequestDTO;
import com.cinemamanage.dto.PaymentMethodResponseDTO;

@Service("paymentMethodDAO")
public class PaymentMethodDAO {
	public static Connection con = null;
	static {
		con=MyConncection.getConnection();
	}

	public int insertData(PaymentMethodRequestDTO dto) {
		int result=0;
		String sql="insert into payment_method(payment_method_name,payment_method_phone) values(?,?)";

		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getPaymentMethodName());
			ps.setString(2, dto.getPaymentMethodPhone());
			result=ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}
		 return result;
	}
	
	public int updateData(PaymentMethodRequestDTO dto) {
		String sql="update payment_method set payment_method_name=?,payment_method_phone=? where id=?";
		int result = 0;
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setNString(1, dto.getPaymentMethodName());
			ps.setString(2, dto.getPaymentMethodPhone());
			ps.setInt(3, dto.getPaymentMethodID());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}return result;
		 
	}
	
	public int deleteData(String paymentMethodID) {
		int result = 0;
		String sql="delete from payment_method where id=?";

		
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setNString(1, paymentMethodID);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}return result;
		 
	}
	
	public PaymentMethodResponseDTO selectOne(PaymentMethodRequestDTO dto) {
		PaymentMethodResponseDTO res = new PaymentMethodResponseDTO();
		String sql="select * from payment_method where id=?";

		
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, dto.getPaymentMethodID());
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				res.setPaymentMethodID(rs.getInt("id"));
				res.setPaymentMethodName(rs.getString("payment_method_name"));
				res.setPaymentMethodPhone(rs.getString("payment_method_phone"));
			}
			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}
		return res;
	}
	
	public ArrayList<PaymentMethodResponseDTO> selectAll() {
		ArrayList<PaymentMethodResponseDTO> list = new ArrayList();
		String sql= "select * from payment_method";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				PaymentMethodResponseDTO res = new PaymentMethodResponseDTO();
				res.setPaymentMethodID(rs.getInt("id"));
				res.setPaymentMethodName(rs.getString("payment_method_name"));
				res.setPaymentMethodPhone(rs.getString("payment_method_phone"));
				list.add(res);  
			}
			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}
		return list;
	}
	
	public boolean isPhoneNoExists(String paymentName,String phoneNo) {
	    boolean exists = false;
	    Connection conn = null;
	    ResultSet rs = null;
	    PreparedStatement ps = null;

	    try {
	        // Get a connection to the database
	        
	        // Prepare a SQL query to check if the movie already exists
	        String sql = "SELECT * FROM payment_method WHERE payment_method_name=? and payment_method_phone = ? ";
	         ps = con.prepareStatement(sql);
	         ps.setString(1,paymentName);
	        ps.setString(2, phoneNo);
	        
	        // Execute the query and get the result set
	        rs = ps.executeQuery();

	        // Check if the movie already exists
	        if (rs.next()) {
	            int count = rs.getInt(1);
	           
	            exists = (count > 0);
	        }
	    } catch (SQLException e) {
	        // Handle the exception
	    } finally {
	        // Close the result set, statement, and connection
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                // Handle the exception
	            }
	        }
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                // Handle the exception
	            }
	        }
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                // Handle the exception
	            }
	        }
	    }
	    
	    return exists;
	}
	//-----------------------------------------------------------------------------------
	
	public ArrayList<PaymentMethodResponseDTO> searchByPaymentMethodId(PaymentMethodRequestDTO dto) {
		ArrayList<PaymentMethodResponseDTO> list = new ArrayList();
		String sql= "select * from payment_method where id=? ;"  ;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getPaymentMethodID());
			ResultSet rs= ps.executeQuery();

			while(rs.next()) {
				PaymentMethodResponseDTO res = new PaymentMethodResponseDTO();
				res.setPaymentMethodID(rs.getInt("id"));
				res.setPaymentMethodName(rs.getString("payment_method_name"));
				res.setPaymentMethodPhone(rs.getString("payment_method_phone"));
				list.add(res);
			}
			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}
		return list;
	}

  public ArrayList<PaymentMethodResponseDTO> selectByPaymentMethodName(PaymentMethodRequestDTO dto) {
	    ArrayList<PaymentMethodResponseDTO> list = new ArrayList<PaymentMethodResponseDTO>();
	    String sql="SELECT * FROM payment_method WHERE payment_method_name LIKE CONCAT('%',?, '%');";	
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, dto.getPaymentMethodName());
	        ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				PaymentMethodResponseDTO res = new PaymentMethodResponseDTO();
				res.setPaymentMethodID(rs.getInt("id"));
				res.setPaymentMethodName(rs.getString("payment_method_name"));
				res.setPaymentMethodPhone(rs.getString("payment_method_phone"));
				list.add(res);
			}
	    } catch (SQLException e) {
	        System.out.println("DataBase Error "+e); 
	    }
	    return list;
	}
  
  public ArrayList<PaymentMethodResponseDTO> selectByPaymentMethodPhone(PaymentMethodRequestDTO dto) {
	    ArrayList<PaymentMethodResponseDTO> list = new ArrayList<PaymentMethodResponseDTO>();
	    String sql="SELECT * FROM payment_method WHERE payment_method_phone LIKE CONCAT('%',?, '%');";	
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, dto.getPaymentMethodPhone());
	        ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				PaymentMethodResponseDTO res = new PaymentMethodResponseDTO();
				res.setPaymentMethodID(rs.getInt("id"));
				res.setPaymentMethodName(rs.getString("payment_method_name"));
				res.setPaymentMethodPhone(rs.getString("payment_method_phone"));
				list.add(res);
			}
	    } catch (SQLException e) {
	        System.out.println("DataBase Error "+e); 
	    }
	    return list;
	}
  
  public ArrayList<PaymentMethodResponseDTO> selectByPaymentMethodAll(PaymentMethodRequestDTO dto) {
	    ArrayList<PaymentMethodResponseDTO> list = new ArrayList<PaymentMethodResponseDTO>();
	    String sql="SELECT * FROM payment_method WHERE id=? and payment_method_name LIKE CONCAT('%',?, '%') and payment_method_phone LIKE CONCAT('%',?, '%');";	
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, dto.getPaymentMethodID());
	        ps.setString(2, dto.getPaymentMethodName());
	        ps.setString(3, dto.getPaymentMethodPhone());
	        ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				PaymentMethodResponseDTO res = new PaymentMethodResponseDTO();
				res.setPaymentMethodID(rs.getInt("id"));
				res.setPaymentMethodName(rs.getString("payment_method_name"));
				res.setPaymentMethodPhone(rs.getString("payment_method_phone"));
				list.add(res);
			}
	    } catch (SQLException e) {
	        System.out.println("DataBase Error "+e); 
	    }
	    return list;
	}
}
