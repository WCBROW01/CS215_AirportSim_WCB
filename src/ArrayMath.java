/**
 * Library for doing mathematical operations on arrays.
 * @author Will Brown
 * @version 1.0
 * Fall 2021
 */
public class ArrayMath {
	
	private ArrayMath() {} // Block instantiation of class
	
	/**
	 * Convert an array of floats to ints.
	 * @param floatArray the input array
	 * @return the output array
	 */
	public static int[] ftoi(float[] floatArray) {
		int[] intArray = new int[floatArray.length];
		for (int i = 0; i < floatArray.length; i++)
			intArray[i] = (int) floatArray[i];
		
		return intArray;
	}
	
	/**
	 * Multiply every element of an array by a fixed value.
	 * @param array the input array
	 * @param multiplier the multiplier
	 * @return the array with every element multiplied
	 */
	public static float[] multiply(float[] array, float multiplier) {
		for (int i = 0; i < array.length; i++)
			array[i] *= multiplier;
		
		return array;
	}
	
	/**
	 * Multiply every element of an array by a fixed value.
	 * @param array the input array
	 * @param multiplier the multiplier
	 * @return the array with every element multiplied
	 */
	public static int[] multiply(int[] array, float multiplier) {
		for (int i = 0; i < array.length; i++)
			array[i] *= 1.5;
		
		return array;
	}
	
}
