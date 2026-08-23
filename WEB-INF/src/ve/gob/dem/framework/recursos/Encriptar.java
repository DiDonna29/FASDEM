/*
 * Created on 31/05/2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ve.gob.dem.framework.recursos;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;





public final class Encriptar {

	public static String encriptar(String textoplano) throws IllegalStateException, NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = null;

		md = MessageDigest.getInstance("SHA"); // Instancia de generador SHA-1
		md.update(textoplano.getBytes("UTF-8")); // Generaci�n de resumen de
													// mensaje
		byte raw[] = md.digest(); // Obtenci�n del resumen de mensaje
		String hash = (new BASE64Encoder()).encode(raw); // Traducci�n a BASE64
		return hash;
	}

}
