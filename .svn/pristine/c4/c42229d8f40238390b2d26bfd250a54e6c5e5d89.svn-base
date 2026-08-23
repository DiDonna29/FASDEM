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
public class SingletonNodos {
	static private SingletonNodos singleton = null;
	static private Properties proper = null;

	private SingletonNodos() throws IOException {
		InputStream input = getClass().getResourceAsStream("/resources/Nodos.properties");
		proper = new Properties();
		proper.load(input);
		input.close();
	}

	static public Properties getSingleton() throws IOException {
		if (singleton == null) {
			singleton = new SingletonNodos();
		}
		return proper;
	}


	
	
}
