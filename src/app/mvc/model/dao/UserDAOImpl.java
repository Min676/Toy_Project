package app.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.mvc.model.dto.Users;
import app.mvc.util.DbManager;

public class UserDAOImpl implements UserDAO {

	/**
	 * 회원가입 구현부
	 * */
	@Override
	public int insertUser(Users usesr) throws SQLException {
	
		return 0;
	}

	/**
	 * 로그인 구현부
	 * */
	@Override
	public Users login(String userId, String pw) throws SQLException {

		Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  Users user=null;
		 try {
		   con = DbManager.getConnection();
		   ps= con.prepareStatement("select * from users where user_id=? and pw=?");
		   ps.setString(1, userId);
		   ps.setString(2, pw);
		   
		   System.out.println(userId);

		   System.out.println(pw);
		   
	        rs = ps.executeQuery(); 
	        
	        if(rs.next()) {
	        	System.out.println("check 1 anchor : "+rs.getInt(1));
	        	user= new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),rs.getInt(6),rs.getInt(7));
	        }
	        System.out.println("this anchor!!");
      }finally {
      	DbManager.close(con, ps, rs);
      }
		return user;

	}
	/**
	 * 회원정보 수정
	 * */
	@Override
	public int selectUser(String userId, String pw) throws SQLException {
	
		return 0;
	}
	/**
	 * 회원정보 삭제
	 * */
	@Override
	public int cancleUser(String userId, String pw) throws SQLException {
		
		return 0;
	}

}
