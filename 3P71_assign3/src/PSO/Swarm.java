package PSO;

public class Swarm {
	/*
	 * Swarm is the object to make the particles Swarm is used to save the global
	 * best fitness and position
	 */
	public particles[] particle;
	private int size = 0;
	private int dimensions = 0;
	private double[] bestPosition;
	private double bestfitness = Double.MAX_VALUE;
	/*
	 * swarm needs to get particles' number(size)
	 * particles' dimensions(dimensions)
	 * make a double array for best position(bestPosition)
	 * make an array which type is particles(particle)
	 */
	public Swarm(int s, int d) {
		size = s;
		dimensions = d;
		particle = new particles[size];
		bestPosition = new double[dimensions];
	}
	/*
	 * put the particles into array particle[]
	 */
	public Swarm initialize() {
		for (int i = 0; i < particle.length; i++) {
			particle[i] = new particles(dimensions).initialize();
		}
		return this;
	}
	/*
	 * choose the best fitness
	 * this function can also check the best fitness is get better or not
	 * if the new only do not get better it will not change
	 */
	public void BestParticle(double[] Position, double f) {
		bestfitness = f;
		for (int i = 0; i < Position.length; i++) {
			bestPosition[i] = Position[i];
		}
	}
	
	public double bestfitness() {
		return bestfitness;
	}

	public double[] bestPosition() {
		return bestPosition;
	}

	public particles[] getSwarm() {
		return particle;
	}
}
