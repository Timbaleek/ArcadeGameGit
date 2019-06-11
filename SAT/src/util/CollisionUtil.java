package util;

import java.util.ArrayList;

public class CollisionUtil {
	public static Pair[] getMinMax(ArrayList<Pair> absPoints, Vec axis){
		float minProjectedLength = axis.dotProduct(new Vec(new Pair(0,0),absPoints.get(0))), 
			  maxProjectedLength = minProjectedLength;
		Pair minPoint = absPoints.get(0),
			 maxPoint = minPoint;
		
		for(Pair p:absPoints){
			float curProjectedLength = axis.dotProduct(new Vec(new Pair(0,0),p));
			if(curProjectedLength<minProjectedLength){
				minProjectedLength = curProjectedLength;
				minPoint = p;
			} else if(curProjectedLength>maxProjectedLength){
				maxProjectedLength = curProjectedLength;
				maxPoint = p;
			}
		}
		return new Pair[]{new Pair(minProjectedLength, maxProjectedLength), minPoint, maxPoint};
	}
	
	public static float getIntervalDistance(float minA,float maxA,float minB,float maxB){
		float intervalDistance;
		//x=min, y=max
		if(minA<minB){
			intervalDistance = minB - maxA;
		} else {
			intervalDistance = minA - maxB;
		}
		return intervalDistance;
	}
}