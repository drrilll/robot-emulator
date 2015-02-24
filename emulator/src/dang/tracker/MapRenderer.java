package dang.tracker;


import java.awt.Color;
import java.awt.image.*;

public class MapRenderer implements Runnable {

	private static final Color c1 = new Color(0,0,200,255);
	private static final Color c4 = new Color(255,0,0,255);
	private static final Color c3 = new Color(255,200,0,255);
	private static final Color c2 = new Color(0,255,255,255);

	private static Color[] greyScalePalette = new Color[256];
	private static Color[] colorPalette = new Color[512];

	private RobotTracker	 model;
	private ProgressMonitor progress;

	static {
		// Create Color Palette
		for (int i=0; i<256; i++) {
			double ratio = i/255.0;
			double invRatio = 1-ratio;
			int r = (int)(c2.getRed()*ratio   + c1.getRed()*invRatio);
			int g = (int)(c2.getGreen()*ratio + c1.getGreen()*invRatio);
			int b = (int)(c2.getBlue()*ratio  + c1.getBlue()*invRatio);
			int a = (int)(invRatio*255);
			colorPalette[i] = new Color(r,g,b,a);
		}
		for (int i=256; i<512; i++) {
			double ratio = (i-256)/255.0;
			double invRatio = 1-ratio;
			int r = (int)(c3.getRed()*ratio   + c4.getRed()*invRatio);
			int g = (int)(c3.getGreen()*ratio + c4.getGreen()*invRatio);
			int b = (int)(c3.getBlue()*ratio  + c4.getBlue()*invRatio);
			int a = (int)(ratio*255);
			colorPalette[i] = new Color(r,g,b,a);
		}

		// Create Grayscale Palette
		for (int i=0; i<128; i++) {
			double ratio = (127-i)/127.0;
			greyScalePalette[i] = new Color(0,0,0,(int)(ratio*255.0));
		}
		for (int i=128; i<256; i++) {
			double ratio = (i-128)/127.0;
			ratio *= ratio;
			greyScalePalette[i] = new Color(255,255,255,(int)(ratio*255.0));
		}
	}


	public MapRenderer(RobotTracker model) {
		this.model = model;
	}

	public void run() {
		boolean useAngleGauss = (model.errorSetting == 2) || (model.errorSetting == 4);
		boolean useDistGauss = (model.errorSetting == 3) || (model.errorSetting == 4);

		progress = model.trackingPanel;
		model.trackingPanel.setProgress(0);

		int maxProgress = 50;
		for (int i=0; i<model.dataSets.length; i++) {
			if (model.dataSets[i].enabled)
				maxProgress += model.dataSets[i].getDataSize();
		}

		model.trackingPanel.setMaxProgress(maxProgress);

		for (int i=0; i<model.dataSets.length; i++) {
			if (model.dataSets[i].enabled) {
				model.trackingPanel.setProgressMessage("Processing data set " + i + " ...");
				model.dataSets[i].applyToGrid(useAngleGauss, useDistGauss, model.resolution, model.errorSetting==0,progress);
			}
		}

		progress.advance(10);

		model.trackingPanel.grid.clear();
		model.trackingPanel.setProgressMessage("Fusing sensor data ...");

		fuseSensorData();
		progress.advance(10);

		model.trackingPanel.setProgressMessage("Applying color map ...");
		computeMap();

		progress.advance(30);

		model.trackingPanel.setProgress(100);
		model.update();
	}

	public void fuseSensorData() {

		double totalWeight = 0;
		for (int i=0; i<model.dataSets.length; i++) {
			if (model.dataSets[i].enabled)
				totalWeight += model.dataSets[i].weight;
		}


		boolean first = true;
		for (int i=0; i<model.dataSets.length; i++) {
			double normalizedWeight = model.dataSets[i].weight/totalWeight;
			double invNormalizedWeight = 1 - normalizedWeight;
			if (model.dataSets[i].enabled && normalizedWeight > 0) {
				model.trackingPanel.grid.weightedFuse(model.dataSets[i].getLatestMap(), first?0:invNormalizedWeight, first?1:normalizedWeight);
				first = false;
			}
		}
	}

	public void computeMap() {
		int[] TempRGBArray = new int[model.trackingPanel.WIDTH * model.trackingPanel.HEIGHT];

		double max = model.trackingPanel.grid.maxValue();
		double min = model.trackingPanel.grid.minValue();
		double min2 = Math.max(min,0.5);
		double max2 = Math.min(max,0.5);

		int index = 0;
		int color = 0;
		for (int x=0; x<model.trackingPanel.grid.getWidth(); x++) {
			for (int y=model.trackingPanel.grid.getHeight()-1; y>=0; y--) {

				// Normal
				if (model.trackingPanel.grid.COLOR) {
					double val = 0;
					index = 0;

					if (model.trackingPanel.grid.prob[x][y] > 0.5) {
						val = 256 + (((model.trackingPanel.grid.prob[x][y]-min2) / (max-min2)) * 255);
					} else if (model.trackingPanel.grid.prob[x][y] <= 0.5) {
						val = ((model.trackingPanel.grid.prob[x][y]-min) / (max2-min)) * 255;
					}

					index = Math.min(511 - (int)val, 511);
					color = colorPalette[index&0x1ff].getRGB();
				}
				else {
					double val = 0;

					if (model.trackingPanel.grid.prob[x][y] > 0.5) {
						val = 128 + (((model.trackingPanel.grid.prob[x][y]-min2) / (max-min2)) * 127);
					} else if (model.trackingPanel.grid.prob[x][y] <= 0.5) {
						val = ((model.trackingPanel.grid.prob[x][y]-min) / (max2-min)) * 127;
					}

					index = Math.min(255 - (int)val, 255);
					color = greyScalePalette[index&0xff].getRGB();
				}
				TempRGBArray[(model.trackingPanel.grid.getHeight()-1-y) * model.trackingPanel.grid.getWidth() + x] = color;
			}
		}
		model.trackingPanel.mapImage.setRGB(0, 0, model.trackingPanel.grid.getWidth(),
												model.trackingPanel.grid.getHeight(),
												TempRGBArray, 0, model.trackingPanel.grid.getWidth());
	}
}
