/*
 * Created on 10-may-2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ve.gob.dem.framework.recursos;

/**
 * @author hvazquez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Cifrado {

	public static String cifrar(String texto){
		String textosalida = null;
		textosalida = texto.replaceAll("'","chr(32)");
		textosalida = texto.replaceAll("@","chr(34)");
		return textosalida;
	}

	public static String decifrar(String texto){
		String textosalida = null;
		textosalida = texto.replaceAll("chr(32)","'");
		textosalida = texto.replaceAll("chr(34)","@");
		return textosalida;
	}

}
