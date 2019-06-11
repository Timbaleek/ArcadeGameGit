package util;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

public class CollisionLoader {
	public static ArrayList<Pair> load(String name, float scale){
		ArrayList<Pair> points = new ArrayList<>();
		
		Path path = Paths.get("res/"+name+".txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Reading Collider: " + name);
		//read line by line
		while(scanner.hasNextLine()){
		    //process each line
		    String line = scanner.nextLine();
		    String[]object = line.split(",");
		    points.add(new Pair(Float.parseFloat(object[0])*scale,Float.parseFloat(object[1])*scale));
		}
		return points;
	}
}
