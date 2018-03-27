package com.html;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class linkDB3 {

	// ����������        
    String driver = "com.mysql.jdbc.Driver";  
    // URLָ��Ҫ���ʵ����ݿ���world        
    String url = "jdbc:mysql://rm-bp1s2659n28d2t858xo.mysql.rds.aliyuncs.com:3306/aircondition";  
    // MySQL����ʱ���û���           
    String user = "root";           
    // MySQL����ʱ������          
    String password = "Qaz123456!";  
    
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet ret = null;
    
    //�������ݿ�
    public linkDB3(){
    	try {
			Class.forName(driver);
			try {
				conn = DriverManager.getConnection(url, user, password);
				
				if(!conn.isClosed()) {
					System.out.println("Succeeded connecting to the Database!");
				}
				else {
					System.out.println("Fail");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    //�ر����ݿ�
    public void close() {
    	try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    //���ݸ���
    public void updateDB(String sql) {
    	try {
			pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    //��pm2�в���������ƣ���γ�ȵ���Ϣ
    public void insertDB(Collection<Locate> locs, linkDB3 db) {
    	//������ȥ��(ֱ����ձ�������) ������ȥ�ػ����ǰ�ж�
    	String sql1 = "TRUNCATE map";
    	updateDB(sql1);
    	
    	
		String sql2 = "INSERT INTO map (province, area, aqi, time_point) VALUES(?,?,?,?)";
		
		for(Locate loc : locs) {
			try {
				pst = (PreparedStatement) conn.prepareStatement(sql2);
				pst.setString(1, loc.getProvince());
				pst.setString(2, loc.getArea());
				pst.setString(3, loc.getApi());
				pst.setString(4, loc.getDate());

				pst.executeUpdate();
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
    //��ѯ���
    public void sqlSelect(String sql) {
    	try {
    		//ִ�����
			pst = (PreparedStatement) conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
		try {
			ret = pst.executeQuery();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
    }
    
    //����ȫ��������Ϣ��pm2��
    public void searchDB(Collection<Locate> locs, linkDB3 db) {
    	String sql1 = "SELECT time_point FROM showdata ORDER BY id DESC LIMIT 0,5";
    	String selectTime = "";
    	
    	sqlSelect(sql1);
    	try {
    		while (ret.next()) {
				String time = db.ret.getString(1);
			    selectTime = time;
			    
			}
    	}catch (SQLException e) {
			e.printStackTrace();
		}
    	
		String sql2 = "SELECT province, area, aqi, time_point FROM showdata WHERE time_point = '" + selectTime +"' ORDER BY CAST(aqi AS SIGNED)" ;
		
		db.sqlSelect(sql2);
		
		try {
			while (ret.next()) {
				String area = db.ret.getString(1);
			    String province = db.ret.getString(2);
			    String aqi = db.ret.getString(3);
			    String time = db.ret.getString(4);
			    
			    if(aqi == null) {
			    	continue;
			    }
			    
			    Locate loc = new Locate(area, province, aqi, time);
			    
			    locs.add(loc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
