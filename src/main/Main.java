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

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	private static Connection con;
	private static Statement db;
	private static ResultSet rs;
	private static String sqlInitializeLocation = "initialize.sql";

	public static void main(String[] args) {
		if(!initializeDatabase()) {
			System.out.println("Error initializing database");
			return;
		}
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {



	}

	private static boolean executeSQLFile(Statement  db, String filename) {
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

	private static boolean initializeDatabase() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/databaseproject?useSSL=false","root", "");
			db = con.createStatement();
		} catch (SQLException e) {
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?useSSL=false","root", "");
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
	private static boolean buildDatabase() {
		BufferedReader br;
		String line;
		Scanner scan;
		String s = "";
		int i = 0;
		try {
			File[] dataFiles = new File("./Rotten_Tomatos_Dataset").listFiles();
			for(File f : dataFiles) {
				System.out.println("Reading file " + f.getName());
				br = new BufferedReader(new FileReader(f));
				line = "";
				switch(f.getName()) {
					case "movies.dat":
						br.readLine();
						while((line = br.readLine()) != null) {
							try {
							scan = new Scanner(line);
							scan.useDelimiter("\t");
							s = "INSERT INTO movies VALUES (" + scan.next()
							+ ",\"" + scan.next() + "\""
							+ "," + scan.next()
							+ ",\"" + scan.next() + "\""
							+ ",\"" + scan.next() + "\""
							+ "," + scan.next()
							+ ",\"" + scan.next() + "\""
							+ "," + scan.next()
							+ "," + scan.next()
							+ "," + scan.next()
							+ "," + scan.next()
							+ "," + scan.next()
							+ "," + scan.next()
							+ "," + scan.next()
							+ "," + scan.next()
							+ "," + scan.next()
							+ "," + scan.next()
							+ "," + scan.next()
							+ "," + scan.next()
							+ "," + scan.next()
							+ ",\"" + scan.next() + "\""
							+ ");"
							;
							db.executeUpdate(s);
							i++;
							if(i % 1000 == 0) {
								System.out.print("=");
							}
							scan.close();
							} catch(SQLException e) {
								s = s.replaceAll(",\"\",", ",'',");
								s = s.replaceAll("\"\"", "\"");
								try {
									db.execute(s);
								} catch (SQLException e1) {
									d.add(s);
									i++;
									if(i % 1000 == 0) {
										System.out.print("=");
									}
								}
							}
						}
						System.out.println();
						break;
					case "movie_actors.dat":
						br.readLine();
						while((line = br.readLine()) != null) {
							try {
							String[] p = line.split("\t");
							s = "INSERT INTO movie_actors VALUES (" + p[0] + ",\"" + p[1] + "\",\"" + p[2] + "\"," + p[3] + ");";
							db.executeUpdate(s);
							i++;
							if(i % 1000 == 0) {
								System.out.print("=");
							}
							} catch(SQLException e) {
								d.add(s);
							}
						}
						System.out.println();
						break;
					default:
						break;
				}

				br.close();
			}
			for(String p : d) {
				System.out.println( p);
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
