package tju.edu.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
