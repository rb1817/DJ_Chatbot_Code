package net.n1books.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class SQLiteTest {

	public static void main(String[] args) {
		Connection cn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			cn = DriverManager.getConnection("jdbc:sqlite:C:\\dev64\\sw\\sqlite-amalgamation-3220000\\lee.db");
			System.out.println("연결성공");
			st = cn.createStatement();
/*			rs = st.executeQuery("select eno,ename from t_emp");
			while(rs.next()) {
				System.out.print(rs.getInt("eno"));
				System.out.print(rs.getString("ename"));
				System.out.print(rs.getInt("sal")+ "\n");
			}*/
			rs= st.executeQuery(
					"select count(*) as cnt" + 
					"from sqlite_master where name = 'star'");
			if(rs.next()) {
				System.out.println(rs.getInt("cnt"));
				if(rs.getInt("cnt")==0) {
					st.execute("create table star(name text)");
				}
			}
			cn.prepareStatement("insert into star values(?)");
			ps.setString(1, "지드래곤");
			if(ps.executeUpdate()>0) {
				System.out.println("입력성공");
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("입력실패");
		}
	}
}
