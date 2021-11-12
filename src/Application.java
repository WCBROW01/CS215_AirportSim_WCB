import java.util.Timer;
import java.util.TimerTask;

/**
 * Entrypoint for the airport simulator.
 * @author Will Brown
 * @version 1.0
 * Fall 2021
 */
public class Application {

	static final int UPDATERATE = 1250;
	static final int TIMESTEP   =   30;
	
	/**
	 * Creates a timer that runs every UPDATERATE ms
	 * and updates the airport as well as the screen.
	 */
	public static void main(String[] args) {
		Airport simAirport = new Airport();
		Timer airportTimer = new Timer();
		
		TimerTask updateAirport = new TimerTask() {
			public void run() {
				simAirport.update(TIMESTEP);
				simAirport.printInfo();
			}
		};
		
		airportTimer.scheduleAtFixedRate(updateAirport, 0, UPDATERATE);
	}

}
