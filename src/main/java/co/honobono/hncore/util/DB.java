package co.honobono.hncore.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.plugin.Plugin;

import co.honobono.hncore.HNCore;

public class DB {
	private static Plugin instance = HNCore.getInstance();
	private Connection c = null;
	private Statement st = null;

	public void create(String file) {
		if(!file.endsWith(".sqlite3")) {
			file = file +".sqlite3";
		}
		try {
			this.c = DriverManager.getConnection("jdbc:sqlite:" + instance + file);
			Statement statement = this.c.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			this.st = statement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Statement getStatement() {
		return st;
	}
 }
