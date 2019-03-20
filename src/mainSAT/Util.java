package mainSAT;

import java.util.ArrayList;

public class Util {
	
	float getMin(ArrayList<Float> floats){
		float minimum = floats.get(0);
		
		for(float f : floats){
			if(f < minimum){
				minimum = f;
			}
		}
		return minimum;
	}
	
	float getMax(ArrayList<Float> floats){
		float maximum = floats.get(0);
		
		for(float f : floats){
			if(f > maximum){
				maximum = f;
			}
		}
		return maximum;
	}
}
