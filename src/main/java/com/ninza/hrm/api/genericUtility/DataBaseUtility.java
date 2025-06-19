package com.ninza.hrm.api.genericUtility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
	static FileUtility fLib = new FileUtility();
	static Connection con = null;
	static ResultSet result = null;
	static Statement stat = null;

	public void getDBConnection() throws SQLException, IOException {
		Driver driverref = new Driver();
		DriverManager.registerDriver(driverref);

		try {
			con = DriverManager.getConnection(fLib.getDataFromPropertyFile("DBUrl"),
					fLib.getDataFromPropertyFile("DB_UserName"), fLib.getDataFromPropertyFile("DB_Password"));

		} catch (SQLException e) {
		}

	}

	public void closeDBConnection() {
		try {
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public ResultSet executeselectquery(String query) {

		try {
			stat = con.createStatement();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {
			result = stat.executeQuery(query);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;

	}

	public boolean executeQueryAndVerify(String query, int columnindex, String expectedData) throws SQLException {

		boolean flag = false;
		result = con.createStatement().executeQuery(query);
		while (result.next()) {
			if (result.getString(columnindex).equals(expectedData)) {
				System.out.println(expectedData +" project verified in DB");
				flag = true;
				break;
			}
		}
		return flag;
	}

}
