package dbtools;

import java.util.ArrayList;
import java.util.HashMap;

public class UserTimeline {

	ArrayList<UserResult> ur;
	HashMap<String, Integer> gb;

	public UserTimeline(ArrayList<UserResult> ur, HashMap<String, Integer> gb) {
		super();
		this.ur = ur;
		this.gb = gb;
	}
	public ArrayList<UserResult> getUserResults() {
		return ur;
	}
	public HashMap<String, Integer> getGenreBreakdown() {
		return gb;
	}
}
