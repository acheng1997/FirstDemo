package com.cc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static Connection connection;
	private static String url = "jdbc:mysql:///demo?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
	private static String user = "root";
	private static String password = "123456";

	static {
		try {
			// 加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("驱动加载成功！");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("驱动加载失败！");
		}
	}

	private DBUtil() {
	}

	public static Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection(url, user, password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
}
