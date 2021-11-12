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
	private Airplane planeOnRunway;
	private int currentTime = 0;
	
	public Airport() {
		
	}
	
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
		
		if (planeOnRunway != null) {
			planeOnRunway.setLanding(true);
			planeOnRunway.update(timestep);
		}
		
		if (planeOnRunway == null || planeOnRunway != null && planeOnRunway.getLandingTime() < 1)
			planeOnRunway = readyToLand.poll();
	}
	
	public void printInfo() {
		System.out.print("\033[H\033[2J");
		System.out.println("Airport Simulation");
		System.out.printf("Time elapsed: %02d:%02d:%02d\n", currentTime / 3600,
						   currentTime % 3600 / 60, currentTime % 60);
		System.out.println();
		
		if (planeOnRunway == null)
			System.out.println("There are no planes on the runway.");
		else
			System.out.printf("Plane %03d is currently on the runway. It will leave in %d:%02d.\n",
							   planeOnRunway.getUid(), planeOnRunway.getLandingTime() / 60,
							   planeOnRunway.getLandingTime() % 60);
		
		for (Airplane plane : readyToLand) {
			System.out.printf("Plane %03d is ready to land.", plane.getUid());
			if (plane.hasEmergency())
				System.out.print(" It has an emergency!");
			
			System.out.println();
		}
		
		for (Airplane plane : approaching) {
			System.out.printf("Plane %03d is approaching. It is %dkm away from the airport.", plane.getUid(), plane.getDistance() / 1000);
			if (plane.hasEmergency())
				System.out.print(" It has an emergency!");
			
			System.out.println();
		}
	}
	
}
