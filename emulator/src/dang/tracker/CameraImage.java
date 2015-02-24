package dang.tracker;



import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.FontMetrics;
import java.awt.Graphics;

// This class used to generate an image from the tracking information received from the camera.
public class CameraImage {
	private static final int WIDTH = 160;
	private static final int HEIGHT = 144;

	private volatile BufferedImage 			img;		// The image that the tracking data is drawn to
	private volatile ArrayList<Integer[]> 	blobs;		// Contains all the blobs that are being tracked (Useful for a camera that can track multiple colours)
	private Color 							blobColor;	// Contains the colour of the blob the camera is tracking

	// Constructor
	public CameraImage() {
		blobColor = new Color(0,0,0);
		blobs = new ArrayList<Integer[]>();
		resetImageToBlack();
	}

	// Reset the image but keep all tracking information.
	public void resetImage() {
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
	}

	// Reset the image and remove all tracking information.
	public void fullReset() {
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		blobs = new ArrayList<Integer[]>();
	}

	// Set tracking information for a blob on the image.  data contains the tracking
	// information (top left x, top left y, bottom right x, bottom right y).
	public void addBlob(ArrayList<Integer> data) {
		int r,g,b;
		r=0;
		b=0;
		g=0;
		Integer[] blob = null;
		Color blobColor = new Color(r,g,b);
		for (int i=0;i<blobs.size();i++ ) {
			if (blobColor.getRGB() == blobs.get(i)[0].intValue()) {
				blob = blobs.get(i);
				break;
			}
		}
		if (blob == null) {
			blob = new Integer[5];
			blobs.add(blob);
			blob[0] = blobColor.getRGB();
		} if (data.get(0)==0&&data.get(1)==0&&data.get(2)==0&&data.get(3)==0) {
			blobs.remove(blob);
		}
		blob[1] = data.get(0);
		blob[2] = data.get(1);
		blob[3] = data.get(2);
		blob[4] = data.get(3);
	}

	// Set the colour of the blob that is being tracked
	public void setColor(int r, int g, int b) {
		blobColor = new Color(r,g,b);
	}

	// Reset the image to black.
	public void resetImageToBlack() {
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		for (int x=0;x<WIDTH;x++) {
			for (int y=0;y<HEIGHT;y++) {
				img.setRGB(x,y,Color.BLACK.getRGB());
			}
		}
	}

	// Draw the tracking information on the image and return it.
	public Image getImage() {
		resetImage();
		BufferedImage image = img;
		for (int i=0;i<blobs.size();i++) {
			Integer[] blob = blobs.get(i);
			int x1 = blob[1];
			if (x1 < 0)
				x1 = 0;
			int y1 = blob[2];
			if (y1 < 0)
				y1 = 0;
			int x2 = blob[3]*2;
			if (x2 > WIDTH-1)
				x2 = WIDTH-1;
			int y2 = blob[4];
			if (y2 > HEIGHT-1)
				y2 = HEIGHT-1;
			for (int x=x1;x<=x2;x++) {
				for (int y=y1;y<=y2;y++) {
					image.setRGB(x,y,blobColor.getRGB());
				}
			}
		}
		if (blobs.size()==0) {
			Graphics g = image.getGraphics();
			g.setColor(Color.WHITE);
			FontMetrics m = g.getFontMetrics();
			String message = "[Tracking colour not found]";
			int width = m.stringWidth(message)/2;
			g.drawString(message, image.getWidth()/2-width, image.getHeight()/2);
			return image;
		}
		AffineTransform tx = AffineTransform.getScaleInstance(-1,-1);
		tx.translate(-image.getWidth(null), -image.getHeight());
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		image = op.filter(image, null);
		return image;
	}

	// Draw an image filled with one colour.
	public static BufferedImage getColorImaged(Color color) {
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		for (int x=0; x<WIDTH; x++) {
			for (int y=0; y<HEIGHT; y++) {
				int pixel = color.getRGB();
				image.setRGB(x,y,pixel);
			}
		}
		return image;
	}

	// Not used. (For dumping a frame from the camera).
	/*public void setImage(ArrayList<Integer> pixels) {
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		if (pixels.get(0) != 1) {
			System.out.println("[Error] Unable to create image from camera dump, dump invalid.");
			img = getColorImaged(Color.black);
		}
		int counter=1;
		int column = 0;
		int row = 0;
		while (counter+3 < pixels.size()) {
			if (pixels.get(counter)==2) {
				column += 2;
				row = 0;
				counter+=1;
			} else if (pixels.get(counter)==3) {
				break;
			} else {
				int pixel = 0;
				pixel += pixels.get(counter);
				pixel += pixels.get(counter+1)<<8;
				pixel += pixels.get(counter+2)<<16;
				Color color = new Color(pixel);
				img.setRGB(column, row, color.getRGB());
				img.setRGB(column+1, row, color.getRGB());
				row++;
				counter+=3;
			}
		}
	}*/
}