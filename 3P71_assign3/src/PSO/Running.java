package PSO;

import java.util.Scanner;

public class Running {
	Scanner sc = new Scanner(System.in);
	public int particles_size = 0;
	public int dimension = 0;
	public int iterations = 0;
	public static double w = 0;
	public static double c1 = 0;
	public static double c2 = 0;

	public void getInput() {
		System.out.println("Please choose mode(1:Random search/2:Input Parameter):");
		int mode = sc.nextInt();
		if (mode == 1) {
			RandomPatten();
		} else if (mode == 2) {
			InputPatten();
		}
	}

	// for people to run the PSO
	private void InputPatten() {
		// TODO Auto-generated method stub
		System.out.println("Please enter the particles number(20-40):");
		particles_size = sc.nextInt();
		System.out.println("Please enter the dimension:");
		dimension = sc.nextInt();
		System.out.println("Please enter the iterations");
		iterations = sc.nextInt();
		System.out.println("Please enter inertia weight(w)");
		w = sc.nextDouble();
		System.out.println("Please enter cognitive accelaration coefficient(c1):");
		c1 = sc.nextDouble();
		System.out.println("Please enter social accelaration coefficient(c2):");
		c2 = sc.nextDouble();
		System.out.println("Start? 1 for run 2 for end");
		int temp = sc.nextInt();
		recurrence_running(temp);
	}

	// recurrence_running is used to do the multiple test
	private void recurrence_running(int temp) {
		if (temp == 1) {
			Start_type1(particles_size, dimension, iterations, w, c1, c2);
			System.out.println("Start? 1 for run 2 for end");
			recurrence_running(temp = sc.nextInt());
		} else if (temp == 2) {
			System.exit(0);
		}
	}

	// start_type1 is used to run PSO
	private void Start_type1(int particles_size, int dimension, int iterations, double w, double c1, double c2) {
		// TODO Auto-generated method stub
		int max = 0; // when max is bigger or equal than iterations number, the loop will end
		// create a new swarm to
		Swarm swarm = new Swarm(particles_size, dimension).initialize();
		// the base case is max >= iterations or best fitness get zero
		while (max < iterations && swarm.bestfitness() != 0) {
			/*
			 * evaluate each particles' swarm which is the function to get fitness and best
			 * fitness
			 */
			for (int i = 0; i < particles_size; i++) {
				swarm.particle[i].Evaluate();
			}
			/*
			 * read all the particles' personal best fitness and position and get the global
			 * best fitness and position
			 */
			for (int i = 0; i < particles_size; i++) {
				if (swarm.bestfitness() > swarm.particle[i].getBestFitness()) {
					swarm.BestParticle(swarm.particle[i].getBestPosition(), swarm.particle[i].getBestFitness());
				}
			}
			/*
			 * update particles' velocity and get the new position
			 */
			for (int i = 0; i < particles_size; i++) {
				swarm.particle[i].Update(swarm.bestPosition());
			}
			System.out.println(swarm.bestfitness());
			max++;
		}
	}

	/*
	 * Random_Patten is used for random search it can get particle number, dimension
	 * and iterations in this test, I only took 30 particles, 30 dimensions and 500
	 * iterations
	 */
	private void RandomPatten() {
		// TODO Auto-generated method stub
		System.out.println("Please enter the particles number(20-40):");
		int particles_size = sc.nextInt();
		System.out.println("Please enter the dimension:");
		int dimension = sc.nextInt();
		System.out.println("Please enter the iterations");
		int iterations = sc.nextInt();
		Start_type2(particles_size, dimension, iterations);

	}

	/*
	 * Random search main part
	 */
	private void Start_type2(int particles_size, int dimension, int iterations) {
		int max = 0;
		// used to return the best fitness
		double bestfitness = Double.MAX_VALUE;
		Swarm swarm = new Swarm(particles_size, dimension).initialize();
		while (max < iterations && swarm.bestfitness() != 0) {
			for (int i = 0; i < particles_size; i++) {
				swarm.particle[i].Evaluate(); // get fitness
			}
			for (int i = 0; i < particles_size; i++) {
				// choose global best
				if (swarm.bestfitness() > swarm.particle[i].getBestFitness()) {
					swarm.BestParticle(swarm.particle[i].getBestPosition(), swarm.particle[i].getBestFitness());
				}
			}
			// compare the new global best with the old one
			// only if the new one is smaller than the old one
			// the best fitness can be changed
			if (bestfitness > swarm.bestfitness()) {
				bestfitness = swarm.bestfitness();
			}
			System.out.println(bestfitness);
			swarm = new Swarm(particles_size, dimension).initialize();
			max++;
		}
	}
}
