package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseTools {

	private Connection con;
	private Statement db;
	private ResultSet rs;
	private String sqlInitializeLocation = "initialize.sql";

	private boolean executeSQLFile(Statement  db, String filename) {
		String in = "";
		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
			scan.useDelimiter("/n");
			while(scan.hasNextLine()) {
				in = scan.nextLine();
				while(!in.endsWith(";") && scan.hasNext()) {
					in += " " + scan.nextLine();
				}
				try {
					db.execute(in);
				} catch (SQLException e) {
					System.out.println("Error in syntax on line: \n" + in);
				}
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Couldnt find db initialize sql file");
			return false;
		}
		return true;
	}

	boolean initializeDatabase() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/databaseproject?useSSL=false","root", "Mikedu(00");
			db = con.createStatement();
		} catch (SQLException e) {
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?useSSL=false","root", "Mikedu(00");
				db = con.createStatement();
				db.execute("create schema databaseproject");
				db.execute("use databaseproject");
			} catch (SQLException e1) {
				e1.printStackTrace();
				return false;
			}
		}
		try {
			rs = db.executeQuery("select count(*) from information_schema.tables where table_schema = 'databaseproject';");
			rs.next();
			if(rs.getInt(1) < 12) {
				executeSQLFile(db, sqlInitializeLocation);
				buildDatabase();
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	static ArrayList<String> d = new ArrayList<String>();
	private boolean buildDatabase() {
		BufferedReader br;
		String line;
		String[] lines;
		String s = "";
		String g = "";
		int i = 0;
		try {
			File[] dataFiles = new File("./Rotten_Tomatos_Dataset").listFiles();
			for(File f : dataFiles) {
				System.out.println("Reading file " + f.getName());
				br = new BufferedReader(new FileReader(f));
				br.readLine();
				String name = f.getName().substring(0, f.getName().length()-4);
				g = "INSERT INTO " + name + " VALUES ";
				while((line = br.readLine()) != null) {
					s = "(";
					lines = line.split("\t");
					for(String p : lines) {
						try {
							Double.parseDouble(p);
							s += p + ",";
						} catch(NumberFormatException e) {
							s += "\"" + p + "\",";
						}
					}
					i++;

					s = s.substring(0,s.length()-1) + ")";
					s = s.replaceAll(",\"\",", ",'',");
					s = s.replaceAll("\"\"", "\"");
					g += s + ",";
					if(i % 1000 == 0) {
						System.out.print("=");
						try {
							db.executeUpdate(g.substring(0,g.length()-1) + ";");
						} catch (SQLException e) {
							System.out.println(g.substring(0,g.length()-1) + ";");
							e.printStackTrace();
						}
						br.close();
					}
				}
				System.out.println();

			}

			for(String p : d) {
				System.out.println(p);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error reading file");
			e.printStackTrace();
		}
		return false;
	}
}
