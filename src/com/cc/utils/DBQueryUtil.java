package com.cc.utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//通用查询（结果集的封装）
public class DBQueryUtil {
	// 得到数据库连接
	private static Connection connection = DBUtil.getConnection();
	/*
	 * 	功能：通用查询
	 * 		@t : 需要查询的返回值类型
	 * 		@sql : 查询的SQL语句
	 * 		@paras : 查询语句需要的参数
	 * */
	public static <T> List<T> executeQuery(Class<T> t, String sql, Object... paras) {
		//定义一个链表，保存查询到的结果
		List<T> result = new ArrayList<T>();
		//用于保存数据库句柄
		PreparedStatement sta = null;
		//查询到的结果
		ResultSet set = null;
		
		try {
			//得到数据库句柄
			sta = connection.prepareStatement(sql);
			//设置参数
			for (int i = 1; i <= paras.length; i++) {
				sta.setObject(i, paras[i-1]);
			}
			//执行SQL语句，得到查询结果
			set = sta.executeQuery();
			//结果封装（反射）
			while (set.next()) {
				//通过类对象创建对象实例
				T tObj = t.newInstance();
				//得到查询的元数据
				ResultSetMetaData rsmd = set.getMetaData();
				//循环出所有字段信息
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					//中间过度变量
					String cName = rsmd.getColumnName(i);
					//数据库字段名称（以下划线分割 user_name USER_NAME）
					String coName = cName;
					//用于保存转换后的属性名称(userName)
					String fName = "";
					
					//把字段名转换为属性名（创建类和数据库表时必须按照规范定义）
					//把所有字母转为小写
					cName = cName.toLowerCase();
					//根据下划线分割字段名
					String[] cns = cName.split("_");
					for (int j = 0; j < cns.length; j++) {
						//把字段名转为属性名
						if(j == 0) {
							fName += cns[j];
						}else {
							fName += cns[j].substring(0,1).toUpperCase() + cns[j].substring(1);
						}
					}
					//最终的属性名保存到了fName
					
					//根据属性名得到属性对象
					Field field = t.getDeclaredField(fName);
					//设置属性的私有访问权限
					field.setAccessible(true);
					//根据 字段名 得到当前行的 字段值 例 ：stu.phone = set.getString("phone");
					Object value = set.getObject(coName);
					//把得到的值设置到类对象中
					field.set(tObj, value);
				}
				
				//把当前行对象添加到最终结果链表中
				result.add(tObj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				//释放资源
				close(sta,set);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public static void executeUpdate(String sql,Object... paras) {
		PreparedStatement sta = null;
		try {
			sta = connection.prepareStatement(sql);
			
			for (int i = 1; i <= paras.length; i++) {
				sta.setObject(i, paras[i-1]);
			}
			
			sta.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				close(sta, null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	// 用于关闭资源
	private static void close(PreparedStatement ps, ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (ps != null) {
			ps.close();
		}
	}
}
