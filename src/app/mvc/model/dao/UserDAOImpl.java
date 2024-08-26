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
	public int insertUser(Users user) throws SQLException { //user인수를 받는다.  예외는 sqlexception으로 던진다.
		
		  Connection con=null;
		  PreparedStatement ps=null;
		  int result = 0;	//초기화
		 try {
			 String sql = "inset into USERS (userid, name, pw) values (?,?,?) "; //inset into 로 테이블에 회원가입정보를 입력 하는 지역변수
		   con = DbManager.getConnection();	//db에 연결
		   ps= con.prepareStatement(sql);		
		   ps.setString(1, user.getUserId());	//회원가입 유저 id 인풋
		   ps.setString(2, user.getName());		//회원가입 이름 인풋 	
		   ps.setString(3, user.getPw());		//회원가입 비밀번호 인풋
		   
	        result = ps.executeUpdate(); //저장
	        
	        System.out.println("회원가입 try 끝부분 체크용");
	        
    }finally {
    	DbManager.close(con, ps, null); //자원반환
    }
		return result;
		
	
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
