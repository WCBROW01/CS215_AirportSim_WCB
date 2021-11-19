import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Viewport extends JPanel {

	final Color GRASS = new Color(0, 128, 0);
	final Color SKY = new Color(0, 180, 255);
	
	AirplaneGraphic plane;
	
	/**
	 * Create the panel.
	 */
	public Viewport() {
		//setLayout(null);
		Timer updateTimer = new Timer();
		plane = new AirplaneGraphic();
		add(plane);
		
		TimerTask updateWindow = new TimerTask() {
			public void run() {
				if (plane.created) {
					Rectangle bounds = plane.getBounds();
					plane.setSize(bounds.width * 2, bounds.height * 2);
				} else {
					plane.setBounds(getWidth() / 2 - 20, getHeight() / 2, 40, 60);
					plane.created = true;
				}
			}
		};
		
		updateTimer.schedule(updateWindow, 1000, 1000);
	}
	
	/**
	 * Initialization routine for any possible custom polygons
	 */
	private void drawRunway(Graphics g) {
		float[] runwayXoffsets = { -1/4f, -1/8f, 1/8f, 1/4f };
		int[] runwayX = ArrayMath.offsetFromCenter(getWidth(), getHeight(), runwayXoffsets);
		int[] runwayY = { getHeight(), getHeight() / 2, getHeight() / 2, getHeight() };
		Polygon runway = new Polygon(runwayX, runwayY, runwayX.length);
		g.setColor(Color.GRAY);
		g.drawPolygon(runway);
		g.fillPolygon(runway);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(SKY);
		g.drawRect(0, 0, getWidth(), getHeight() / 2);
		g.fillRect(0, 0, getWidth(), getHeight() / 2);
		g.setColor(GRASS);
		g.drawRect(0, getHeight() / 2, getWidth(), getHeight() / 2);
		g.fillRect(0, getHeight() / 2, getWidth(), getHeight() / 2);
		drawRunway(g);
	}

}
