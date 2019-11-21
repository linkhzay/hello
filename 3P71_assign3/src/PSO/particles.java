package PSO;

import java.util.Random;

public class particles {
	public static double upperBond = 5.12;
	public static double lowerBond = -5.12;
	private double[] position;
	private double[] velocity;
	private double fitness;
	private double bestFitness;
	private double[] bestPosition;
	private int dimensions;
	Random random = new Random();
	function getfitness = new function();
	/*
	 * particles need to get dimension(dimensions)
	 * array position(position[])
	 * array best position(bestPosition[])
	 * velocity(velocity[])
	 */
	public particles(int d) {
		dimensions = d;
		position = new double[dimensions];
		bestPosition = new double[dimensions];
		velocity = new double[dimensions];
		fitness = 0;
		bestFitness = 0;
	}
	/*
	 * initialize the particles the velocity is 0
	 * position is in range -5.12 to 5.12
	 * and initialize bestFitness to double.max_value
	 */
	public particles initialize() {
		for (int i = 0; i < velocity.length; i++) {
			velocity[i] = 0;
		}
		for (int i = 0; i < position.length; i++) {
			position[i] = lowerBond + (upperBond - lowerBond) * random.nextDouble();
		}
		bestFitness = Double.MAX_VALUE;
		return this;
	}
	/*
	 * Evaluate function need to check the new position
	 * if the new position is over range
	 * it will be ignored
	 * only the in range position and better fitness will make bestFitness change 
	 */
	public void Evaluate() {
		fitness = getfitness.Rastrigin(position, dimensions);
		Boolean change = false;
		for (int i = 0; i < position.length; i++) {
			if (Math.abs(position[i]) > 5.12) {
				change = true;
			}
		}
		if (change == false) {
			if (Math.abs(bestFitness) > Math.abs(fitness)) {
				bestFitness = fitness;
				for (int i = 0; i < dimensions; i++) {
					bestPosition[i] = position[i];
				}
			}
		}
	}
	/*
	 * update the velocity
	 */
	public void Update(double[] NebbestPosition) {
		for (int i = 0; i < dimensions; i++) {
			velocity[i] = (Running.w * velocity[i]) + Running.c1 * random.nextDouble() * (bestPosition[i] - position[i])
					+ Running.c2 * random.nextDouble() * (NebbestPosition[i] - position[i]);
			position[i] += velocity[i];
		}
	}

	public double getBestFitness() {
		return bestFitness;
	}

	public double[] getBestPosition() {
		return bestPosition;
	}
}
