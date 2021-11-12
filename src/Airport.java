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
	
	public Airport() {
		approaching.add(new Airplane());
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
		
		for (int runway = 0; runway < planesOnRunways.length; runway++) {
			if (planesOnRunways[runway] != null) {
				planesOnRunways[runway].setLanding(true);
				planesOnRunways[runway].update(timestep);
			}
			
			if (planesOnRunways[runway] == null || planesOnRunways[runway] != null && planesOnRunways[runway].getLandingTime() < 1)
				planesOnRunways[runway] = readyToLand.poll();
		}
	}
	
	public void printInfo() {
		System.out.print("\033[H\033[2J");
		System.out.println("Airport Simulation");
		System.out.printf("Time elapsed: %02d:%02d:%02d\n", currentTime / 3600,
						   currentTime % 3600 / 60, currentTime % 60);
		System.out.println();
		
		System.out.println("Runways");
		System.out.println("+--------+--------+----------------+");
		System.out.println("| Runway | Flight | Time to Depart |");
		System.out.println("+--------+--------+----------------+");
		for (int runway = 0; runway < planesOnRunways.length; runway++) {
			if (planesOnRunways[runway] == null)
				System.out.printf("| %02d     | None   | None           |\n", runway);
			else
				System.out.printf("| %02d     | %03X    | %d:%02d           |\n",
								   runway, planesOnRunways[runway].getUid(),
								   planesOnRunways[runway].getLandingTime() / 60,
								   planesOnRunways[runway].getLandingTime() % 60);
		}
		
		System.out.println("+--------+--------+----------------+");
		
		System.out.println();
		System.out.println("Approaching planes");
		System.out.println("+--------+---------------+-----------+");
		System.out.println("| Flight | Distance      | Status    |");
		System.out.println("+--------+---------------+-----------+");
		for (Airplane plane : readyToLand) {
			System.out.printf("| %03X    | Ready to land |", plane.getUid());
			if (plane.hasEmergency())
				System.out.println(" Emergency |");
			else
				System.out.println(" OK        |");
		}
		
		for (Airplane plane : approaching) {
			System.out.printf("| %03X    | %02dkm away     |", plane.getUid(), plane.getDistance() / 1000);
			if (plane.hasEmergency())
				System.out.println(" Emergency |");
			else
				System.out.println(" OK        |");
		}
		
		System.out.println("+--------+---------------+-----------+");
		System.out.flush();
	}
	
}
