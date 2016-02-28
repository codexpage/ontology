package tju.edu.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tju.edu.db.DBConnection;
import tju.edu.model.temperaturesensor;

public class temperaturesensorDAO {
	/**
	 * 增加
	 * @param dept
	 */
	public void insert(temperaturesensor temp) {
	    int sensorid=temp.getSensorid();
		double temperature=temp.getTemperature();
		
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = DBConnection.getConnection();
			String sql = 
				"INSERT INTO tempsensor(sensorid,temperature) " + 
				"VALUES (?, ?)";
//			System.out.println(sql);
			System.out.println("sensor"+sensorid+":"+temperature);
			stat = conn.prepareStatement(sql);
			stat.setInt(1, sensorid);
			stat.setDouble(2,temperature);			
			int res = stat.executeUpdate();			
			assert res == 1;
			
		} catch (SQLException e) {
		   e.printStackTrace();
		   
		} finally {
			try {
				if (stat != null)
					stat.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}
	
	public void read(temperaturesensor temp) {
		int sensorid=temp.getSensorid();
		
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = DBConnection.getConnection();
			String sql = 
					"select * from tempsensor where sensorid=? and id >= all(select id from tempsensor where sensorid=?);";
			stat = conn.prepareStatement(sql);
			stat.setInt(1, sensorid);
			stat.setInt(2, sensorid);
			int res = stat.executeUpdate();			
			//todo...
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	public int findAll(String database,String relation,int id) {		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		int tmp = 0;
		try {
			DBConnection.setDbUrl(database);
			conn = DBConnection.getConnection();
			String sql = 
					"select * from "+relation+" where sensorid="+id
					+ " and id >= all(select id from "+relation+" where sensorid="+id+");";
			

			System.out.println(sql);
			stat = conn.createStatement();	
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				tmp=rs.getInt("temperature");
				
			}
			
		} catch (SQLException e) {
		   e.printStackTrace();
		   
		} finally {
			try {
				if (stat != null)
					stat.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return tmp;
	}
}
