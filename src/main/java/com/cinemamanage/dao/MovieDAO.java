package com.cinemamanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.cinemamanage.dto.MovieRequestDTO;
import com.cinemamanage.dto.MovieResponseDTO;
import com.cinemamanage.dto.MovieAndTimetableRequestDTO;
import com.cinemamanage.dto.MovieAndTimetableResponseDTO;

@Service("movieDAO")
public class MovieDAO {
	public static Connection con = null;
	static {
		con=MyConncection.getConnection();
	}

	public int insertData(MovieRequestDTO dto) {
		int result=0;
		String sql="insert into movie(movie_name,movie_createtime,movie_genre,movie_duration,movie_description,created_person,movie_image) values(?,?,?,?,?,?,?)";

		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getMovieName());
			ps.setString(2, dto.getMovieCreateTime());
			ps.setString(3, dto.getMovieGenre());
			ps.setString(4, dto.getMovieDuration());
			ps.setString(5, dto.getMovieDescription());
			ps.setString(6, dto.getCreatedAdmin());
			ps.setString(7,dto.getImageData());
			
			result=ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}
		 return result;
	}
	
	public int updateData(MovieAndTimetableRequestDTO dto) {
		String sql= "UPDATE movie\r\n" + 
				"JOIN timetable ON movie.id = timetable.movie_id \r\n" + 
				"SET movie.movie_name = ?, movie_updatetime=?,movie.movie_genre=?,movie_duration=?,movie_description=?,updated_person=?,timetable.start_time = ?,timetable.start_date=?, movie.movie_image=? \r\n" + 
				"WHERE movie.id=?;\r\n" + 
				"";
		//String sql="update movie set movie_name=?,movie_updatetime=?,movie_genre=?,movie_duration=?,movie_discription=?,updated_person=? where id=?";
		int result = 0;
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);			
			ps.setString(1, dto.getMovieName());
			ps.setString(2,dto.getMovieUpdateTime());
			ps.setString(3, dto.getMovieGenre());
			ps.setString(4, dto.getMovieDuration());
			ps.setString(5, dto.getMovieDescription());
			ps.setString(6, dto.getUpdatedAdmin());
			//ps.setInt(7, dto());
			ps.setString(7, dto.getStartTime());
			ps.setString(8,dto.getStartDate());
			ps.setNString(9, dto.getMoviePoster());
			ps.setInt(10, dto.getMovieID());
			//System.out.print(dto.getMovieName()+","+dto.getMovieGenre()+","+dto.getMovieUpdateTime()+"," + dto.getMovieDuration()+","+dto.getMovieDescription()+","+dto.getUpdatedAdmin()+","+dto.getStartTime()+","+dto.getStartDate()+","+dto.getMovieID());
			result=ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}return result;
		 
	}
	
	public int deleteData(MovieRequestDTO dto) {
		int result = 0;
		String sql="update movie set movie_deletetime=?,deleted_person=? where id=?;";

		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, dto.getMovieDeleteTime());
			ps.setString(2, dto.getDeletedAdmin());
			ps.setInt(3,dto.getMovieID());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}return result;
		 
	}
	public ArrayList<MovieAndTimetableResponseDTO> checkingTooClose(String startDate) {
		MovieAndTimetableResponseDTO res = new MovieAndTimetableResponseDTO();
		String sql="SELECT *\r\n" + 
				"FROM movie m  \r\n" + 
				"JOIN timetable tt ON m.id = tt.movie_id WHERE tt.start_date=? and movie_deletetime IS NULL";
		ArrayList<MovieAndTimetableResponseDTO> list = new ArrayList<MovieAndTimetableResponseDTO>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, startDate);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				res.setMovieGenre(rs.getString("movie_genre"));
				res.setMovieDuration(rs.getString("movie_duration"));
				res.setMovieDescription(rs.getString("movie_description"));
				res.setCreatedAdmin(rs.getString("created_person"));
				res.setUpdatedAdmin(rs.getNString("updated_person"));
				res.setDeletedAdmin(rs.getString("deleted_person"));
				res.setStartDate(rs.getString("start_date"));
				res.setStartTime(rs.getString("start_time"));
				list.add(res);

			}
			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}return list;
		
	}
	
	
	public MovieAndTimetableResponseDTO selectOneMovieAndTime(MovieRequestDTO dto) {
		MovieAndTimetableResponseDTO res = new MovieAndTimetableResponseDTO();
		String sql="select * from movie m join timetable tt on m.id=tt.movie_id where m.id=?";

		
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, dto.getMovieID());
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				res.setMovieID(rs.getInt("id"));
				res.setMovieName(rs.getString("movie_name"));
				res.setMovieCreateTime(rs.getString("movie_createtime"));
				res.setMovieUpdateTime(rs.getString("movie_updatetime"));
				res.setMovieDeleteTime(rs.getString("movie_deletetime"));
				res.setMovieGenre(rs.getString("movie_genre"));
				res.setMovieDuration(rs.getString("movie_duration"));
				res.setMovieDescription(rs.getString("movie_description"));
				res.setCreatedAdmin(rs.getString("created_person"));
				res.setUpdatedAdmin(rs.getString("updated_person"));
				res.setDeletedAdmin(rs.getString("deleted_person"));
				res.setStartDate(rs.getString("start_date"));
				res.setStartTime(rs.getString("start_time"));
				res.setMoviePoster(rs.getString("movie_image"));
			}
			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}
		return res;
	}
	
	public ArrayList<MovieResponseDTO> selectAllAdmin() {
		ArrayList<MovieResponseDTO> list = new ArrayList();
		String sql= "select * from movie";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				MovieResponseDTO res = new MovieResponseDTO();
				res.setMovieID(rs.getInt("id"));
				res.setMovieName(rs.getString("movie_name"));
				res.setMovieCreateTime(rs.getString("movie_createtime"));
				res.setMovieUpdateTime(rs.getString("movie_updatetime"));
				res.setMovieDeleteTime((rs.getString("movie_deletetime")));
				res.setMovieGenre(rs.getString("movie_genre"));
				res.setMovieDuration(rs.getString("movie_duration"));
				res.setMovieDescription(rs.getString("movie_description"));
				res.setCreatedAdmin(rs.getString("created_person"));
				res.setUpdatedAdmin(rs.getNString("updated_person"));
				res.setDeletedAdmin(rs.getString("deleted_person"));
				list.add(res);  
			}
			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}
		return list;
	}
	public ArrayList<MovieResponseDTO> selectAllNonDeletedMovie() {
		ArrayList<MovieResponseDTO> list = new ArrayList();
		String sql= "SELECT * FROM movie WHERE movie_deletetime IS NULL";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				MovieResponseDTO res = new MovieResponseDTO();
				res.setMovieID(rs.getInt("id"));
				res.setMovieName(rs.getString("movie_name"));
				res.setMovieCreateTime(rs.getString("movie_createtime"));
				res.setMovieUpdateTime(rs.getString("movie_updatetime"));
				res.setMovieDeleteTime((rs.getString("movie_deletetime")));
				res.setMovieGenre(rs.getString("movie_genre"));
				res.setMovieDuration(rs.getString("movie_duration"));
				res.setMovieDescription(rs.getString("movie_description"));
				res.setImageData(rs.getString("movie_image"));
				list.add(res);  
			}
			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}
		return list;
	}
	
	public ArrayList<MovieResponseDTO> selectAllDeletedMovie() {
		ArrayList<MovieResponseDTO> list = new ArrayList();
		String sql= "SELECT * FROM movie WHERE movie_deletetime IS NOT NULL";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				MovieResponseDTO res = new MovieResponseDTO();
				res.setMovieID(rs.getInt("id"));
				res.setMovieName(rs.getString("movie_name"));
				res.setMovieCreateTime(rs.getString("movie_createtime"));
				res.setMovieUpdateTime(rs.getString("movie_updatetime"));
				res.setMovieDeleteTime((rs.getString("movie_deletetime")));
				res.setMovieGenre(rs.getString("movie_genre"));
				res.setMovieDuration(rs.getString("movie_duration"));
				res.setMovieDescription(rs.getString("movie_description"));
				list.add(res);  
			}
			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}
		return list;
	}
	
	public MovieResponseDTO selectOneUser(MovieRequestDTO dto) {
		MovieResponseDTO res = new MovieResponseDTO();
		String sql="select * from movie where id=?;";

		
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, dto.getMovieID());
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				res.setMovieID(rs.getInt("id"));
				res.setMovieName(rs.getString("movie_name"));
				res.setMovieGenre(rs.getString("movie_genre"));
				res.setMovieDuration(rs.getString("movie_duration"));
				res.setMovieDescription(rs.getString("movie_description"));
				res.setImageData(rs.getString("movie_image"));
			}

			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}
		return res;
	}
	
	public ArrayList<MovieResponseDTO> selectAllUser() {
		ArrayList<MovieResponseDTO> list = new ArrayList();
		String sql= "SELECT * FROM movie WHERE movie_deletetime IS NULL"  ;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				MovieResponseDTO res = new MovieResponseDTO();
				res.setMovieID(rs.getInt("id"));
				res.setMovieName(rs.getString("movie_name"));
				res.setMovieGenre(rs.getString("movie_genre"));
				res.setMovieDuration(rs.getString("movie_duration"));
				res.setMovieDescription(rs.getString("movie_description"));
				list.add(res);   
			}
			
		} catch (SQLException e) {
			System.out.println("DataBase Error "+e); 
		}
		return list;
	}
	
	public boolean isMovieExists(String movieName) {
	    boolean exists = false;
	    Connection conn = null;
	    ResultSet rs = null;
	    PreparedStatement ps = null;

	    try {
	        // Get a connection to the database
	        
	        // Prepare a SQL query to check if the movie already exists
	        String sql = "SELECT * FROM movie WHERE movie_name = ?";
	         ps = con.prepareStatement(sql);
	        ps.setString(1, movieName);
	        
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

	//-----------------------No Deleted Movie Search----------------------------------
			public ArrayList<MovieResponseDTO> searchByNonDeletedMovieId(MovieRequestDTO dto) {
				ArrayList<MovieResponseDTO> list = new ArrayList();
				String sql= "select * from movie where id=? AND movie_deletetime IS NULL;"  ;
				try {
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, dto.getMovieID());
					ResultSet rs= ps.executeQuery();
					while(rs.next()) {
						MovieResponseDTO res = new MovieResponseDTO();
						res.setMovieID(rs.getInt("id"));
						res.setMovieName(rs.getString("movie_name"));
						res.setMovieCreateTime(rs.getString("movie_createtime"));
						res.setMovieUpdateTime(rs.getString("movie_updatetime"));
						res.setMovieDeleteTime((rs.getString("movie_deletetime")));
						res.setMovieGenre(rs.getString("movie_genre"));
						res.setMovieDuration(rs.getString("movie_duration"));
						res.setMovieDescription(rs.getString("movie_description"));
						list.add(res);   
					}
					
				} catch (SQLException e) {
					System.out.println("DataBase Error "+e); 
				}
				return list;
			}
		
		  public ArrayList<MovieResponseDTO> searchByNonDeletedMovieName(MovieRequestDTO dto) {
			    ArrayList<MovieResponseDTO> datalist = new ArrayList<MovieResponseDTO>();
			    String sql="SELECT * FROM movie WHERE movie_name LIKE CONCAT('%',?, '%') AND movie_deletetime IS NULL;";	
			    try {
			        PreparedStatement ps = con.prepareStatement(sql);
			        ps.setString(1, dto.getMovieName());
			        ResultSet rs= ps.executeQuery();
					while(rs.next()) {
						MovieResponseDTO res = new MovieResponseDTO();
						res.setMovieID(rs.getInt("id"));
						res.setMovieName(rs.getString("movie_name"));
						res.setMovieCreateTime(rs.getString("movie_createtime"));
						res.setMovieUpdateTime(rs.getString("movie_updatetime"));
						res.setMovieDeleteTime((rs.getString("movie_deletetime")));
						res.setMovieGenre(rs.getString("movie_genre"));
						res.setMovieDuration(rs.getString("movie_duration"));
						res.setMovieDescription(rs.getString("movie_description"));
						datalist.add(res); 
			        }
			    } catch (SQLException e) {
			        System.out.println("DataBase Error "+e); 
			    }
			    return datalist;
			}
		  
		  public MovieResponseDTO selectOneMovieByName(MovieRequestDTO dto) {
			     MovieResponseDTO res = new MovieResponseDTO();
			    String sql="select * from movie where movie_name=?";

			    
			    try {
			      PreparedStatement ps = con.prepareStatement(sql);

			      ps.setString(1, dto.getMovieName());
			      ResultSet rs= ps.executeQuery();
			      while(rs.next()) {
			        res.setMovieID(rs.getInt("id"));
			        res.setMovieName(rs.getString("movie_name"));
			        res.setMovieCreateTime((rs.getString("movie_createtime")));
			        res.setMovieUpdateTime((rs.getString("movie_updatetime")));
			        res.setMovieDeleteTime((rs.getString("movie_deletetime")));
			        res.setMovieGenre(rs.getString("movie_genre"));
			        res.setMovieDuration(rs.getString("movie_duration"));
			        res.setMovieDescription(rs.getString("movie_description"));
			        res.setImageData(rs.getString("movie_image"));
			      }
			      
			    } catch (SQLException e) {
			      System.out.println("DataBase Error "+e); 
			    }
			    return res;
			   }
		  
		  public ArrayList<MovieResponseDTO> searchByNonDeletedMovieGenre(MovieRequestDTO dto) {
			    ArrayList<MovieResponseDTO> datalist = new ArrayList<MovieResponseDTO>();
			    String sql="SELECT * FROM movie WHERE movie_genre LIKE CONCAT('%',?, '%') AND movie_deletetime IS NULL;";	
			    try {
			        PreparedStatement ps = con.prepareStatement(sql);
			        ps.setString(1, dto.getMovieGenre());
			        ResultSet rs= ps.executeQuery();
					while(rs.next()) {
						MovieResponseDTO res = new MovieResponseDTO();
						res.setMovieID(rs.getInt("id"));
						res.setMovieName(rs.getString("movie_name"));
						res.setMovieCreateTime(rs.getString("movie_createtime"));
						res.setMovieUpdateTime(rs.getString("movie_updatetime"));
						res.setMovieDeleteTime((rs.getString("movie_deletetime")));
						res.setMovieGenre(rs.getString("movie_genre"));
						res.setMovieDuration(rs.getString("movie_duration"));
						res.setMovieDescription(rs.getString("movie_description"));
						datalist.add(res); 
					}
			    } catch (SQLException e) {
			        System.out.println("DataBase Error "+e); 
			    }
			    return datalist;
			}
		  
		  public ArrayList<MovieResponseDTO> searchByNonDeletedAllMovie(MovieRequestDTO dto) {
			    ArrayList<MovieResponseDTO> datalist = new ArrayList<MovieResponseDTO>();
			    String sql="SELECT * FROM movie WHERE id=? and movie_name LIKE CONCAT('%',?, '%') AND movie_deletetime IS NULL;";	
			    try {
			        PreparedStatement ps = con.prepareStatement(sql);
			        ps.setInt(1, dto.getMovieID());
			        ps.setString(2, dto.getMovieName());
			        ResultSet rs= ps.executeQuery();
					while(rs.next()) {
						MovieResponseDTO res = new MovieResponseDTO();
						res.setMovieID(rs.getInt("id"));
						res.setMovieName(rs.getString("movie_name"));
						res.setMovieCreateTime(rs.getString("movie_createtime"));
						res.setMovieUpdateTime(rs.getString("movie_updatetime"));
						res.setMovieDeleteTime((rs.getString("movie_deletetime")));
						res.setMovieGenre(rs.getString("movie_genre"));
						res.setMovieDuration(rs.getString("movie_duration"));
						res.setMovieDescription(rs.getString("movie_description"));
						datalist.add(res); 
					}
			    } catch (SQLException e) {
			        System.out.println("DataBase Error "+e); 
			    }
			    return datalist;
			}
		  
//-------------------------- Search Deleted Movie------------------------------------
		  
			public ArrayList<MovieResponseDTO> searchByDeletedMovieId(MovieRequestDTO dto) {
				ArrayList<MovieResponseDTO> list = new ArrayList();
				String sql= "select * from movie where id=? and movie_deletetime IS NOT NULL;"  ;
				try {
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, dto.getMovieID());
					ResultSet rs= ps.executeQuery();
					while(rs.next()) {
						MovieResponseDTO res = new MovieResponseDTO();
						res.setMovieID(rs.getInt("id"));
						res.setMovieName(rs.getString("movie_name"));
						res.setMovieCreateTime(rs.getString("movie_createtime"));
						res.setMovieUpdateTime(rs.getString("movie_updatetime"));
						res.setMovieDeleteTime((rs.getString("movie_deletetime")));
						res.setMovieGenre(rs.getString("movie_genre"));
						res.setMovieDuration(rs.getString("movie_duration"));
						res.setMovieDescription(rs.getString("movie_description"));
						list.add(res); 
					}
					
				} catch (SQLException e) {
					System.out.println("DataBase Error "+e); 
				}
				return list;
			}
		
		  public ArrayList<MovieResponseDTO> selectByDeletedMovieName(MovieRequestDTO dto) {
			    ArrayList<MovieResponseDTO> datalist = new ArrayList<MovieResponseDTO>();
			    String sql="SELECT * FROM movie WHERE movie_name LIKE CONCAT('%',?, '%') And movie_deletetime IS NOT NULL;";	
			    try {
			        PreparedStatement ps = con.prepareStatement(sql);
			        ps.setString(1, dto.getMovieName());
			        ResultSet rs= ps.executeQuery();
					while(rs.next()) {
						MovieResponseDTO res = new MovieResponseDTO();
						res.setMovieID(rs.getInt("id"));
						res.setMovieName(rs.getString("movie_name"));
						res.setMovieCreateTime(rs.getString("movie_createtime"));
						res.setMovieUpdateTime(rs.getString("movie_updatetime"));
						res.setMovieDeleteTime((rs.getString("movie_deletetime")));
						res.setMovieGenre(rs.getString("movie_genre"));
						res.setMovieDuration(rs.getString("movie_duration"));
						res.setMovieDescription(rs.getString("movie_description"));
						datalist.add(res); 
					}
			    } catch (SQLException e) {
			        System.out.println("DataBase Error "+e); 
			    }
			    return datalist;
			}
		  
		  public ArrayList<MovieResponseDTO> selectByDeletedMovieGenre(MovieRequestDTO dto) {
			    ArrayList<MovieResponseDTO> datalist = new ArrayList<MovieResponseDTO>();
			    String sql="SELECT * FROM movie WHERE movie_genre LIKE CONCAT('%',?, '%') And movie_deletetime IS NOT NULL;";	
			    try {
			        PreparedStatement ps = con.prepareStatement(sql);
			        ps.setString(1, dto.getMovieGenre());
			        ResultSet rs= ps.executeQuery();
					while(rs.next()) {
						MovieResponseDTO res = new MovieResponseDTO();
						res.setMovieID(rs.getInt("id"));
						res.setMovieName(rs.getString("movie_name"));
						res.setMovieCreateTime(rs.getString("movie_createtime"));
						res.setMovieUpdateTime(rs.getString("movie_updatetime"));
						res.setMovieDeleteTime((rs.getString("movie_deletetime")));
						res.setMovieGenre(rs.getString("movie_genre"));
						res.setMovieDuration(rs.getString("movie_duration"));
						res.setMovieDescription(rs.getString("movie_description"));
						datalist.add(res); 
					}
			    } catch (SQLException e) {
			        System.out.println("DataBase Error "+e); 
			    }
			    return datalist;
			}
		  
		  public ArrayList<MovieResponseDTO> selectByAllDeletedMovie(MovieRequestDTO dto) {
			    ArrayList<MovieResponseDTO> datalist = new ArrayList<MovieResponseDTO>();
			    String sql="SELECT * FROM movie WHERE id=? and movie_name LIKE CONCAT('%',?, '%') and movie_genre LIKE CONCAT('%',?, '%')  And movie_deletetime IS NOT NULL;";	
			    try {
			        PreparedStatement ps = con.prepareStatement(sql);
			        ps.setInt(1, dto.getMovieID());
			        ps.setString(2, dto.getMovieName());
			        ps.setString(3, dto.getMovieGenre());
			        ResultSet rs= ps.executeQuery();
					while(rs.next()) {
						MovieResponseDTO res = new MovieResponseDTO();
						res.setMovieID(rs.getInt("id"));
						res.setMovieName(rs.getString("movie_name"));
						res.setMovieCreateTime(rs.getString("movie_createtime"));
						res.setMovieUpdateTime(rs.getString("movie_updatetime"));
						res.setMovieDeleteTime((rs.getString("movie_deletetime")));
						res.setMovieGenre(rs.getString("movie_genre"));
						res.setMovieDuration(rs.getString("movie_duration"));
						res.setMovieDescription(rs.getString("movie_description"));
						datalist.add(res); 
					}
			    } catch (SQLException e) {
			        System.out.println("DataBase Error "+e); 
			    }
			    return datalist;
			}

}
