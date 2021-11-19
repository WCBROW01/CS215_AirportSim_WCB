import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

public class Viewport extends JPanel {

	final Color GRASS = new Color(0, 128, 0);
	final Color SKY = new Color(0, 180, 255);
	
	Polygon runway;
	
	/**
	 * Create the panel.
	 */
	public Viewport() {
		runway = new Polygon();
	}
	
	/**
	 * Initialization routine for any possible custom polygons
	 */
	private void initPolygons() {
		float[] runwayX = { getWidth() * (1/4f), getWidth() * (7/16f), getWidth() * (9/16f), getWidth() * (3/4f) };
		int[] runwayY = { getHeight(), getHeight() / 2, getHeight() / 2, getHeight() };
		runway = new Polygon(ArrayMath.ftoi(runwayX), runwayY, runwayX.length);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		initPolygons();
		g.setColor(SKY);
		g.drawRect(0, 0, getWidth(), getHeight());
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(GRASS);
		g.drawRect(0, getHeight() / 2, getWidth(), getHeight() / 2);
		g.fillRect(0, getHeight() / 2, getWidth(), getHeight() / 2);
		g.setColor(Color.GRAY);
		g.drawPolygon(runway);
		g.fillPolygon(runway);
	}

}
