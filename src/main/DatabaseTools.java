package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
		int i = 0;
		int datalength;
		try {
			File[] dataFiles = new File("./Rotten_Tomatos_Dataset").listFiles();
			for(File f : dataFiles) {
				System.out.println("Reading file " + f.getName().substring(1));
				br = new BufferedReader(new FileReader(f));
				datalength = br.readLine().split("\t").length;
				String name = f.getName().substring(1, f.getName().length()-4);
				i = 0;
				System.out.println(datalength);
				while((line = br.readLine()) != null) {
					try {
						s = "INSERT INTO " + name + " VALUES (";
						lines = line.split("\t");
						for(int g = 0; g < datalength; g++) {
							try {
								Double.parseDouble(lines[g]);
								s += lines[g] + ",";
							} catch(NumberFormatException e)  {
								s += "\"" + lines[g] + "\",";
							} catch(IndexOutOfBoundsException e) {
								s += "'',";
							}
						}
						i++;
						if(i % 5000 == 0) {
							System.out.print("=");;
						}
						s = s.substring(0,s.length()-1) + ");";
						db.executeUpdate(s);
					} catch(SQLException e) {
						try {
							s = s.replaceAll(",\"\",", ",'',");
							s = s.replaceAll("\"\"", "\"");
							db.executeUpdate(s);
						} catch (SQLException e1) {
							try {
								s = s.replaceAll(" \"", " ");
								s = s.replaceAll("\" ", " ");
								s = s.replace("\"\\N\"", "\\N");
								db.executeUpdate(s);
							} catch(SQLException e2) {
								try {
									s = s.replace("\", ", ", ");
									s = s.replace("a\".", "a.");
									s = s.replace("'\"", "");
									s = s.replace("2d", "\"2d\"");
									s = s.replace("3d", "\"3d\"");
									s = s.replace("Infinity", "\"Infinity\"");
									db.executeUpdate(s);
								} catch(SQLException e3) {
									e.printStackTrace();
									System.out.println(s);
								}
							}
						}
					}
				}
				System.out.println();
				br.close();
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
