/*
 * creado el 20/06/2003
 * autor lmontanez
 */
package ve.gob.dem.framework.recursos;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import ve.gob.dem.framework.exception.PersonalException;

/**
 * Clase de utilidad.
 * 
 * @author lmontanez
 */
public class Utilidad {
	/**
	 * Determina si un String de fecha es valida o no
	 * 
	 * @param patron
	 *            el patron del String de la fecha
	 * @param fecha
	 *            la fecha a verificar.
	 * @return true si el String de fecha es valido, false de lo contrario.
	 */
	public static boolean esFechaValida(String argPatron, String argFecha) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(argPatron, new Locale("es", "VE"));
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(argFecha);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Convierte un String en una fecha tipo java.util.Date
	 * 
	 * @param argFecha
	 *            La fecha a convertir.
	 * @param argPatron
	 *            El patr�n en el que esta el String fecha.
	 * @return una objeto tipo java.util.Date si la fecha es valida
	 * @throws PersonalException
	 *             Si hay un error en la conversi�n de la fecha.
	 */
	public static Date StringToDate(String argFecha, String argPatron) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(argPatron, new Locale("es", "VE"));
		dateFormat.setLenient(false);
		return dateFormat.parse(argFecha);
	}

	/**
	 * Convierte un objeto Date a String con un patron de salida determinado.
	 * 
	 * @param argFecha
	 * @param argPatron
	 *            ejem 'dd/MM/yyyy'
	 * @return la fecha seg�n el patron especificado.
	 * @throws PersonalException
	 *             Si ocurre un error parseando la fecha.
	 */
	public static String DateToString(Date argFecha, String argPatron) throws PersonalException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(argPatron, new Locale("es", "VE"));
		dateFormat.setLenient(false);
		try {
			return dateFormat.format(argFecha);
		} catch (Exception e) {
			throw new PersonalException("app.date.parsing");
		}
	}

	/**
	 * Determina si un String es n�merico valido o no.
	 * 
	 * @param argNumero
	 * @return
	 */
	public static boolean esNumerico(String argNumero) {
		try {
			double temp = Double.parseDouble(argNumero);
			if (Double.isNaN(temp) || Double.isInfinite(temp))
				return false;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Determina si la aplicaci�n puede o no mostrar los mensajes de error
	 * originales o solo mostrar los estandars.
	 * 
	 * @param contexto
	 * @return true si puede mostrar los mensaje de error estandar o false si
	 *         tiene que mostrar los mensajes estandar.
	 */
	/**
	 * Realza palabras en un string.
	 * 
	 * @param string
	 *            El String donde se va a realzar la(s) palabra(s).
	 * @param palabras
	 *            un arreglo de palabras que contiene las palabras que deber�an
	 *            ser realzadas.
	 * @param startEtiqueta
	 *            la etiqueta que deberia ser insertada antes de la palabra a
	 *            realzar.
	 * @param endEtiqueta
	 *            la etiqueta que deberia ser insertada desues de la palabra a
	 *            realzar.
	 * @return un nuevo String con las etiquetas insertadas.
	 */
	public static final String realzarPalabras(String string, String[] palabras, String startEtiqueta, String endEtiqueta) {
		if (string == null || palabras == null || startEtiqueta == null || endEtiqueta == null) {
			return null;
		}
		// itera por cada palabra en el arreglo.
		for (int x = 0; x < palabras.length; x++) {
			// convertimos todo a miniscula.
			String lcString = string.toLowerCase();
			// el uso de un arreglo de char es m�s eficiente.
			char[] string2 = string.toCharArray();
			String palabra = palabras[x].toLowerCase();
			int i = 0;
			if ((i = lcString.indexOf(palabra, i)) >= 0) {
				int oLength = palabra.length();
				StringBuffer buf = new StringBuffer(string2.length);
				boolean startSpace = false;
				char startChar = ' ';
				if (i - 1 > 0) {
					startChar = string2[i - 1];
					if (!Character.isLetter(startChar)) {
						startSpace = true;
					}
				}
				boolean endSpace = false;
				char endChar = ' ';
				if (i + oLength < string2.length) {
					endChar = string2[i + oLength];
					if (!Character.isLetter(endChar)) {
						endSpace = true;
					}
				}
				if ((startSpace && endSpace) || (i == 0 && endSpace)) {
					buf.append(string2, 0, i);
					if (startSpace && startChar == ' ') {
						buf.append(startChar);
					}
					buf.append(startEtiqueta);
					buf.append(string2, i, oLength).append(endEtiqueta);
					if (endSpace && endChar == ' ') {
						buf.append(endChar);
					}
				} else {
					buf.append(string2, 0, i);
					buf.append(string2, i, oLength);
				}
				i += oLength;
				int j = i;
				while ((i = lcString.indexOf(palabra, i)) > 0) {
					startSpace = false;
					startChar = string2[i - 1];
					if (!Character.isLetter(startChar)) {
						startSpace = true;
					}
					endSpace = false;
					if (i + oLength < string2.length) {
						endChar = string2[i + oLength];
						if (!Character.isLetter(endChar)) {
							endSpace = true;
						}
					}
					if ((startSpace && endSpace) || i + oLength == string2.length) {
						buf.append(string2, j, i - j);
						if (startSpace && startChar == ' ') {
							buf.append(startChar);
						}
						buf.append(startEtiqueta);
						buf.append(string2, i, oLength).append(endEtiqueta);
						if (endSpace && endChar == ' ') {
							buf.append(endChar);
						}
					} else {
						buf.append(string2, j, i - j);
						buf.append(string2, i, oLength);
					}
					i += oLength;
					j = i;
				}
				buf.append(string2, j, string2.length - j);
				string = buf.toString();
			}
		}
		return string;
	}

	/**
	 * Corta un String a la longitud dada.
	 * 
	 * @param string
	 *            El string a cortar.
	 * @param length
	 *            La cantidad de caracteres que deber�a tener el String.
	 * @return un substring del <code>string</code> con la longitud indicada.
	 */
	public static final String limitarString(String string, int length) {
		if (string == null || string.trim().equals("")) {
			return string;
		}
		char[] charArray = string.toCharArray();
		int sLength = string.length();
		if (length < sLength) {
			sLength = length;
		}
		for (int i = 0; i < sLength - 1; i++) {
			// Windows
			if (charArray[i] == '\r' && charArray[i + 1] == '\n') {
				return string.substring(0, i);
			}
			// Unix
			else if (charArray[i] == '\n') {
				return string.substring(0, i);
			}
		}
		if (charArray[sLength - 1] == '\n') {
			return string.substring(0, sLength - 1);
		}
		if (string.length() < length) {
			return string;
		}
		for (int i = length - 1; i > 0; i--) {
			if (charArray[i] == ' ') {
				return string.substring(0, i).trim();
			}
		}
		return string.substring(0, length);
	}

	/**
	 * Convierte un String en un arreglo de String de minusculas. Separa las
	 * palabras delimitadas por los siguientes caracteres , .\r\n:/\+
	 * 
	 * @param text
	 *            el String a se comvertido en arreglo.
	 * @return text un arreglo de String, con todas las palabras en minusculas.
	 */
	public static final String[] toLowerCaseWordArray(String text) {
		if (text == null || text.length() == 0) {
			return new String[0];
		}
		StringTokenizer tokens = new StringTokenizer(text, " ,\r\n.:/\\+");
		String[] palabras = new String[tokens.countTokens()];
		for (int i = 0; i < palabras.length; i++) {
			palabras[i] = tokens.nextToken().toLowerCase();
		}
		return palabras;
	}

	public static String completaNumeroPunto(int numpunto) {
		String valor = String.valueOf(numpunto);
		for (int i = valor.length(); i != 3; i++) {
			valor = "0" + valor;
		}
		return valor;
	}

	/**
	 * Sustituye los acentos en un string por sus mismas letras, pero sin
	 * acentos.
	 * 
	 * @param argTexto
	 * @return
	 */
	public static final String sustituirAcentos(String argTexto) {
		if (argTexto != null && !argTexto.trim().equals("")) {
			String respuesta = argTexto;
			respuesta = respuesta.replace('Á', 'A');
			respuesta = respuesta.replace('á', 'a');
			respuesta = respuesta.replace('É', 'E');
			respuesta = respuesta.replace('é', 'e');
			respuesta = respuesta.replace('Í', 'I');
			respuesta = respuesta.replace('í', 'i');
			respuesta = respuesta.replace('Ó', 'O');
			respuesta = respuesta.replace('ó', 'o');
			respuesta = respuesta.replace('Ú', 'U');
			respuesta = respuesta.replace('ú', 'u');
			return respuesta;
		}
		return argTexto;
	}

	/**
	 * Determina si en un String existe una cantidad de caracteres.
	 * 
	 * @param argTexto
	 *            el texto donde se va a buscar.
	 * @param argCaracteres
	 *            los caracteres a buscar
	 * @return true si consigue algun character en el String, false de lo
	 *         contrario.
	 */
	public static boolean existeCharacter(String argTexto, char[] argCaracteres) {
		if (argTexto == null || argTexto.trim().equals("") || argCaracteres == null || argCaracteres.length <= 0) {
			return false;
		}
		for (int i = 0; i < argCaracteres.length; i++) {
			if (argTexto.indexOf(argCaracteres[i]) != -1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Agrega elementos a un arreglo de String
	 * 
	 * @param argArrayToAddTo
	 *            El arreglo al cual se le va a agregar los elementos
	 * @param argNumOfElementsToAdd
	 *            la cantidad de elementos a agregar.
	 * @return un arreglo redimensionado.
	 */
	public static String[] agregarElemento(String[] argArrayToAddTo, int argNumOfElementsToAdd) {
		String[] temp = new String[argArrayToAddTo.length + argNumOfElementsToAdd];
		System.arraycopy(argArrayToAddTo, 0, temp, 0, argArrayToAddTo.length);
		return temp;
	}

	/**
	 * Agrega un elemento a un arreglo de Object, es decir, a un arreglo en
	 * general
	 * 
	 * @param argArrayToAddTo
	 * @param argNumOfElementsToAdd
	 * @return
	 */
	public static Object[] agregarElemento(Object[] argArrayToAddTo, int argNumOfElementsToAdd) {
		Object[] temp = new Object[argArrayToAddTo.length + argNumOfElementsToAdd];
		System.arraycopy(argArrayToAddTo, 0, temp, 0, argArrayToAddTo.length);
		return temp;
	}

	public static String encriptar(String texto) throws UnsupportedEncodingException {
		String ini = String.valueOf(texto.length() * texto.length()) + "$$";
		String acum = "";
		for (int i = 0; i < texto.length(); i++) {
			char car = texto.charAt(i);
			acum = acum + String.valueOf(((int) car) + texto.length()) + "!";
		}
		return ini + acum;
	}

	public static String desencriptar(String texto) {
		int x = texto.indexOf("$$");
		x = x + 1;
		int i = (int) Math.sqrt(Double.parseDouble(texto.substring(0, x - 1)));
		String aux = texto.substring(x + 1, texto.length());
		String acum = "";
		for (int j = 0; j < i; j++) {
			x = aux.indexOf("!");
			char ca = (char) (Integer.parseInt(aux.substring(0, x)) - i);
			acum = acum + ca;
			aux = aux.substring(x + 1, aux.length());
		}
		return acum;
	}

	/**
	 * 
	 * @param str
	 * @param character
	 * @param length
	 * @return
	 */
	public static String lPadWithChar(String str, char character, int length) {
		while (str.length() < length) {
			str = character + str;
		}
		return str;
	}

	/**
	 * 
	 * @param str
	 * @param character
	 * @param length
	 * @return
	 */
	public static String rPadWithChar(String str, char character, int length) {
		while (str.length() < length) {
			str = str + character;
		}
		return str;
	}
}
