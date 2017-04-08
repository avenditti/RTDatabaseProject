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

import Datatypes.ActorResult;
import Datatypes.DirectorResult;
import Datatypes.Movie;
import Datatypes.TagResult;

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

	public boolean initializeDatabase() {
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

	public ArrayList<TagResult> getMovieTags(String movieTitle) {
        String sql =
        		"SELECT M.title, T.tagValue " +
        		"FROM movies M, tags T " +
        		"WHERE M.id = T.id AND lower(M.title) LIKE '%" + movieTitle.toLowerCase() + "%' " +
        		"ORDER BY M.title DESC";
        ArrayList<TagResult> tags = new ArrayList<TagResult>();
		try {
			ResultSet rs = db.executeQuery(sql);
			while(rs.next()) {
				tags.add(new TagResult(rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return tags;
	}

	public ArrayList<ActorResult> getTop10ActorsByAvgMovieScore(int moviesActedIn) {
        String sql =
        		"SELECT MA.actorName, AVG(rtAudienceNumRating), COUNT(*) " +
        		"FROM movie_actors MA, movies M " +
        		"WHERE MA.movieID = M.id AND M.id IN " +
        			"(SELECT MIN(id) " +
        			"FROM movies M " +
        			"GROUP BY M.title) " +
        		"GROUP BY MA.actorName " +
        		"HAVING COUNT(*) > " + moviesActedIn + " " +
        		"ORDER BY AVG(rtAudienceNumRating) DESC " +
        		"LIMIT 10";
        ArrayList<ActorResult> actors = new ArrayList<ActorResult>();
		try {
			ResultSet rs = db.executeQuery(sql);
			while(rs.next()) {
				actors.add(new ActorResult(rs.getString(1), rs.getInt(2), rs.getInt(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return actors;
	}

	public ArrayList<DirectorResult> getTop10DirectorsByAvgMovieScore(int moviesDirected) {
        String sql =
        		"SELECT MD.directorID, MD.directorName, COUNT(*), AVG(rtAudienceNumRating) " +
        		"FROM movie_directors MD, movies M " +
        		"WHERE MD.movieID = M.id AND M.id IN " +
        			"(SELECT MIN(id) " +
        			"FROM movies M " +
        			"GROUP BY M.title) " +
        		"GROUP BY MD.directorName " +
        		"HAVING COUNT(*) > " + moviesDirected + " " +
        		"ORDER BY AVG(rtAudienceNumRating) DESC " +
        		"LIMIT 10";
        ArrayList<DirectorResult> directors = new ArrayList<DirectorResult>();
		try {
			ResultSet rs = db.executeQuery(sql);
			while(rs.next()) {
				directors.add(new DirectorResult(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return directors;
	}

	public ArrayList<Movie> getMoviesByTag(String tagName) {
        String sql =
        		"SELECT M.*" +
        		"FROM movies M, tags T " +
        		"WHERE M.id = T.id AND lower(T.tagValue) LIKE '%" + tagName.toLowerCase() + "%'";
        ArrayList<Movie> movies = new ArrayList<Movie>();
		try {
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				movies.add(new Movie(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7),
						rs.getInt(8),
						rs.getInt(9),
						rs.getInt(10),
						rs.getInt(11),
						rs.getInt(12),
						rs.getInt(13),
						rs.getInt(14),
						rs.getInt(15),
						rs.getInt(16),
						rs.getInt(17),
						rs.getInt(18),
						rs.getInt(19),
						rs.getInt(20),
						rs.getString(21)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return movies;
	}

	public ArrayList<Movie> getTopMovies(int n) {
         String sql =
        		 "SELECT * " +
        		 "FROM movies " +
        		 "WHERE id IN  " +
        		 	"(SELECT MIN(id) " +
        		 	"FROM movies " +
        		 	"GROUP BY title) " +
        		 "ORDER BY rtAudienceNumRating DESC " +
        		 "LIMIT 100 ";
         ArrayList<Movie> movies = new ArrayList<Movie>();
		try {
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				movies.add(new Movie(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7),
						rs.getInt(8),
						rs.getInt(9),
						rs.getInt(10),
						rs.getInt(11),
						rs.getInt(12),
						rs.getInt(13),
						rs.getInt(14),
						rs.getInt(15),
						rs.getInt(16),
						rs.getInt(17),
						rs.getInt(18),
						rs.getInt(19),
						rs.getInt(20),
						rs.getString(21)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
         return movies;
	}

	public ArrayList<Movie> getMovies(String name) {
        String sql =
        		"SELECT * " +
        		"FROM movies " +
        		"WHERE lower(title) LIKE " + "\"%"+ name.toLowerCase() + "%\"";
        ArrayList<Movie> movies = new ArrayList<Movie>();
		try {
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				movies.add(new Movie(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7),
						rs.getInt(8),
						rs.getInt(9),
						rs.getInt(10),
						rs.getInt(11),
						rs.getInt(12),
						rs.getInt(13),
						rs.getInt(14),
						rs.getInt(15),
						rs.getInt(16),
						rs.getInt(17),
						rs.getInt(18),
						rs.getInt(19),
						rs.getInt(20),
						rs.getString(21)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return movies;
	}

	public ArrayList<Movie> getMoviesByGenre(String genre, int num) {
        String sql =
        		"SELECT * " +
        		"FROM movies M, movie_genres MG " +
        		"WHERE MG.movieID = M.id AND lower(MG.genre) LIKE '%" + genre.toLowerCase() + "%' " +
        		"ORDER BY rtAudienceNumRating DESC " +
        		"LIMIT " + num;
        ArrayList<Movie> movies = new ArrayList<Movie>();
		try {
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				movies.add(new Movie(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7),
						rs.getInt(8),
						rs.getInt(9),
						rs.getInt(10),
						rs.getInt(11),
						rs.getInt(12),
						rs.getInt(13),
						rs.getInt(14),
						rs.getInt(15),
						rs.getInt(16),
						rs.getInt(17),
						rs.getInt(18),
						rs.getInt(19),
						rs.getInt(20),
						rs.getString(21)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return movies;
	}

	public ArrayList<DirectorResult> getMovieDirector(String name) {
        String sql =
        		"SELECT MD.*, M.title " +
        		"FROM movie_directors MD, movies M " +
        		"WHERE lower(M.title) LIKE '%" + name.toLowerCase() +"%' AND M.id = MD.movieID";
        ArrayList<DirectorResult> directors = new ArrayList<DirectorResult>();
		try {
			ResultSet rs = db.executeQuery(sql);
			while(rs.next()) {
				directors.add(new DirectorResult(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return directors;
	}

	public ArrayList<Movie> getMoviesByDirector(String name) {
        String sql =
        		"SELECT * " +
           		"FROM movies M, movie_directors MD " +
           		"WHERE MD.movieID = M.id AND lower(MD.directorName) LIKE '%" + name.toLowerCase() + "%' AND id IN " +
					"(SELECT MIN(id) " +
					"FROM movies " +
					"GROUP BY title) " +
           		"ORDER BY rtAudienceNumRating DESC ";
        ArrayList<Movie> movies = new ArrayList<Movie>();
		try {
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				movies.add(new Movie(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7),
						rs.getInt(8),
						rs.getInt(9),
						rs.getInt(10),
						rs.getInt(11),
						rs.getInt(12),
						rs.getInt(13),
						rs.getInt(14),
						rs.getInt(15),
						rs.getInt(16),
						rs.getInt(17),
						rs.getInt(18),
						rs.getInt(19),
						rs.getInt(20),
						rs.getString(21)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return movies;
	}

	public ArrayList<ActorResult> getActorMovies(String name) {
        String sql =
				"SELECT M.*, MA.actorName " +
				"FROM movie_actors MA, movies M " +
				"WHERE MA.movieID = M.id AND lower(MA.actorName) LIKE '%"+ name.toLowerCase() +"%' AND id IN " +
					"(SELECT MIN(id) " +
					"FROM movies " +
				    "GROUP BY title) " +
				"ORDER BY rtAudienceNumRating DESC ";
        ArrayList<ActorResult> actorResults = new ArrayList<ActorResult>();
		try {
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				actorResults.add(new ActorResult(rs.getString(22), new Movie(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7),
						rs.getInt(8),
						rs.getInt(9),
						rs.getInt(10),
						rs.getInt(11),
						rs.getInt(12),
						rs.getInt(13),
						rs.getInt(14),
						rs.getInt(15),
						rs.getInt(16),
						rs.getInt(17),
						rs.getInt(18),
						rs.getInt(19),
						rs.getInt(20),
						rs.getString(21))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return actorResults;
	}

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
