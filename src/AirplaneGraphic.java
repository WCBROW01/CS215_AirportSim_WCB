import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Random;

import javax.swing.JComponent;

public class AirplaneGraphic extends JComponent {
	
	public boolean created = false;
	private Polygon planePolygon;
	private final Color planeColor;

	public AirplaneGraphic() {
		Random colorGen = new Random();
		planeColor = new Color(colorGen.nextInt(0x1000000));
	}
	
	private void drawAirplane(Graphics g) {
		int[] airplaneX = { 0, getWidth() / 2, getWidth() };
		int[] airplaneY = { 0, getHeight(), 0 };
		planePolygon = new Polygon(airplaneX, airplaneY, airplaneX.length);
		g.setColor(planeColor);
		g.drawPolygon(planePolygon);
		g.fillPolygon(planePolygon);
	}
	
	protected void paintComponent(Graphics g) {
		drawAirplane(g);
	}

}
