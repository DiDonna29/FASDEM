/*
 * Created on 16/01/2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ve.gob.dem.framework.recursos;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author marcenrl
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SingletonValores {
	static private SingletonValores singleton = null;
	static private Properties proper = null;

	private SingletonValores() throws IOException {
		InputStream input = getClass().getResourceAsStream("/resources/Valores.properties");
		proper = new Properties();
		proper.load(input);
		input.close();
	}

	static public Properties getSingleton() throws IOException {
		if (singleton == null) {
			singleton = new SingletonValores();
		}
		return proper;
	}


	
	
}
