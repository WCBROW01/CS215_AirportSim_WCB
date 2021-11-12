import java.util.PriorityQueue;

/**
 * Airport for the airport simulation. Also contains display logic.
 * @author Will Brown
 * @version 1.0
 * Fall 2021
 */
public class Airport {
	
	final int TIMETOLAND = 300; // time to land in seconds

	private PriorityQueue<Airplane> approaching = new PriorityQueue<Airplane>();
	private PriorityQueue<Airplane> readyToLand = new PriorityQueue<Airplane>();
	private Airplane[] planesOnRunways = new Airplane[2];
	private int currentTime = 0;
	
	public void update(int timestep) {
		currentTime += timestep;
		
		if (Math.random() > 0.85)
			approaching.add(new Airplane());
		
		for (Airplane plane : approaching)
			plane.update(timestep);
		
		for (Airplane plane : readyToLand)
			plane.update(timestep);
		
		if (approaching.peek() != null && approaching.peek().getDistance() < 1) {
			approaching.peek().setReadyToLand(true);
			readyToLand.add(approaching.poll());
		}
		
		for (int runways = 0; runways < planesOnRunways.length; runways++) {
			if (planesOnRunways[runways] != null) {
				planesOnRunways[runways].setLanding(true);
				planesOnRunways[runways].update(timestep);
			}
			
			if (planesOnRunways[runways] == null || planesOnRunways[runways] != null && planesOnRunways[runways].getLandingTime() < 1)
				planesOnRunways[runways] = readyToLand.poll();
		}
	}
	
	public void printInfo() {
		System.out.print("\033[H\033[2J");
		System.out.println("Airport Simulation");
		System.out.printf("Time elapsed: %02d:%02d:%02d\n", currentTime / 3600,
						   currentTime % 3600 / 60, currentTime % 60);
		System.out.println();
		
		for (int runway = 0; runway < planesOnRunways.length; runway++) {
			if (planesOnRunways[runway] == null)
				System.out.printf("There are no planes on runway %d.\n", runway);
			else
				System.out.printf("Plane %03d is currently on runway %d. It will leave in %d:%02d.\n",
								   planesOnRunways[runway].getUid(), runway,
								   planesOnRunways[runway].getLandingTime() / 60,
								   planesOnRunways[runway].getLandingTime() % 60);
		}
		
		System.out.println();
		for (Airplane plane : readyToLand) {
			System.out.printf("Plane %03d is ready to land.", plane.getUid());
			if (plane.hasEmergency())
				System.out.print(" It has an emergency!");
			
			System.out.println();
		}
		
		for (Airplane plane : approaching) {
			System.out.printf("Plane %03d is approaching. It is %02dkm away from the airport.", plane.getUid(), plane.getDistance() / 1000);
			if (plane.hasEmergency())
				System.out.print(" It has an emergency!");
			
			System.out.println();
		}
	}
	
}
