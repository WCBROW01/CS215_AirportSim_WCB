import java.util.Timer;
import java.util.TimerTask;

public class Application {

	static final int TIMESTEP = 30;
	static final int UPDATERATE = 1250;

	public static void main(String[] args) {
		Timer airportTimer = new Timer();
		Airport simAirport = new Airport();
		
		TimerTask updateAirport = new TimerTask() {
			public void run() {
				simAirport.update(TIMESTEP);
				simAirport.printInfo();
			}
		};
		
		airportTimer.scheduleAtFixedRate(updateAirport, 0, UPDATERATE);
	}

}
