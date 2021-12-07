/**
 * Data for airplanes in the airport simulator.
 * @author Will Brown
 * @version 1.0
 * Fall 2021
 */
public class Airplane implements Comparable<Airplane> {
	
	public final int INITIAL_LANDING_TIME =    300;
	public final int INITIAL_DISTANCE     = 100000;
	public final int SPEED                =    200; // speed in m/s
	
	private boolean hasEmergency;
	private boolean readyToLand;
	private boolean isLanding;
	private int landingTime; // time left to spend at runway in seconds
	private int distance; // distance from airport in meters
	private int uid;
	
	/**
	 * Set all values to defaults and generate a unique identifier.
	 */
	public Airplane() {
		hasEmergency = false;
		readyToLand = false;
		isLanding = false;
		landingTime = INITIAL_LANDING_TIME;
		distance = INITIAL_DISTANCE;
		uid = (int) (Math.random() * 0x1000);
	}
	
	/**
	 * Update method for airplanes. Steps the time,
	 * moves the plane closer to the airport,
	 * and randomly triggers an emergency.
	 * @param timestep amount of time to step forward in seconds
	 */
	public void update(int timestep) {
		if (distance > 0)
			distance -= SPEED * timestep;
		
		if (distance < 0)
			distance = 0;
		
		if (isLanding)
			landingTime -= timestep;
		
		if (!readyToLand && !hasEmergency)
			hasEmergency = Math.random() > 0.985;
	}
	
	/**
	 * Priority is based on the distance of the plane from the airport
	 * and whether it has an emergency. A lower number means higher priority.
	 * Once all planes are ready to land, they will have a priority of 0
	 * unless they have an emergency, in which case their priority is -1.
	 * @return the priority of the airplane
	 */
	public Integer getPriority() {
		return hasEmergency ? distance - 1 : distance;
	}
	
	/**
	 * @return whether the airplane has an emergency
	 */
	public boolean hasEmergency() {
		return hasEmergency;
	}

	/**
	 * @return whether the plane is ready to land
	 */
	public boolean isReadyToLand() {
		return readyToLand;
	}

	/**
	 * @param whether the plane is ready to land
	 */
	public void setReadyToLand(boolean readyToLand) {
		this.readyToLand = readyToLand;
	}

	/**
	 * @return time left to spend at runway in seconds
	 */
	public int getLandingTime() {
		return landingTime;
	}

	/**
	 * @return whether the plane is landing
	 */
	public boolean isLanding() {
		return isLanding;
	}

	/**
	 * @param whether the plane is landing
	 */
	public void setLanding(boolean isLanding) {
		this.isLanding = isLanding;
	}

	/**
	 * @return the distance from the airport
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * @return the uid of the plane
	 */
	public int getUid() {
		return uid;
	}

	/**
	 * Compares the priority of planes. Required for the queue to work.
	 */
	@Override
	public int compareTo(Airplane o) {
		return getPriority().compareTo(o.getPriority());
	}
	
}
