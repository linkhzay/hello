package PSO;

public class function {
	/*
	 * Rastrigin is used to calculate fitness
	 */
	public double Rastrigin(double[] position, int dimension) {
		double result = 0;
		result = 10*dimension;
		for (int i = 0; i<dimension;i++) {
			double temp = 0;
			temp = position[i]*position[i]-10*Math.cos(2*Math.PI*position[i]);
			result = result+temp;
		}
		return result;
	}
}
