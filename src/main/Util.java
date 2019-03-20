package main;

public class Util {
	public static float getMin(float[] a){
		float min = a[0];
		for(float i : a){
			if(i < min){
				min = i;
			}
		}
		return min;
	}
	
	public static float getMax(float[] a){
		float max = a[0];
		for(float i : a){
			if(i > max){
				max = i;
			}
		}
		return max;
	}
}
