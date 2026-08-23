/*
 * Created on 02/09/2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ve.gob.dem.fasdem.valores;

import java.io.InputStream;

import org.apache.commons.digester.Digester;
import org.apache.log4j.Logger;

/**
 * @author lmontanez
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CargaValores {
	private static Logger log = Logger.getLogger(CargaValores.class);
	private Valores valores;
	private static CargaValores instance; // Para crear un singleton

	private CargaValores() throws Exception {
		InputStream is;
		is = getClass().getClassLoader().getResourceAsStream("valores-fasdem.xml");
		if (is == null) {
			is = getClass().getClassLoader().getResourceAsStream("/valores-fasdem.xml");
		}
		valores = new Valores();
		try {
			Digester digester = new Digester();
			digester.push(valores);
			digester.setValidating(false);
			// rules
			digester.addCallMethod("fasdem-config/valor-impuestos/valor-iva", "setIva", 0);
			digester.addCallMethod("fasdem-config/valor-impuestos/valor-islr", "setIslr", 0);
			digester.addCallMethod("fasdem-config/valor-impuestos/valor-timbrefiscal", "setTimbreFiscal", 0);
			digester.addCallMethod("fasdem-config/valores-estaticos/tipogasto-iva", "setTipoGastoIva", 0);
			
			// parse
			digester.parse(is);
			is.close();
			// ------------------
			log.info("load config :" + valores.toString());
		} catch (Exception e) {
			log.error("cargando configuracion ", e);
			throw e;
		}
	}

	public static synchronized CargaValores getInstance() throws Exception {
		if (instance == null) {
			instance = new CargaValores();
		}
		return instance;
	}

	public Valores getValores() {
		return valores;
	}
}
