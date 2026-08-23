package ve.gob.dem.framework.grafico;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imagen {
	static private Imagen img=null;
	static private BufferedImage loadImg=null;

	private Imagen(){
	}

	static public Imagen getSingleton(String path) throws IOException{

		if(img == null) {
			img=new Imagen();
			loadImg=ImageIO.read(new File(path + "images/fondo.gif"));
		}
		return img;
	}

	 public BufferedImage getLoadImg(){
		return loadImg;
	}
}
