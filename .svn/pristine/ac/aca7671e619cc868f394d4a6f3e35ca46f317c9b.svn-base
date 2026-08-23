/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("rawtypes")
public class FasdemSession implements Serializable{

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 139810913803065278L;
	private String sessionid;
    private Date fecha;
    private Clinica clinica;
   
	private ArrayList opciones;
    private Persona titular;
    private String sha1;
    private byte[] pdf;

    /**
 * 
 */
    public FasdemSession() {
	sessionid = "";
	fecha = new Date();
	clinica = new Clinica();
	titular = null;
	sha1 = "";
    }

    public String getSessionid() {
	return sessionid;
    }

    public void setSessionid(String sessionid) {
	this.sessionid = sessionid;
    }

    public Date getFecha() {
	return fecha;
    }

    public void setFecha(Date fecha) {
	this.fecha = fecha;
    }

    public Clinica getClinica() {
	return clinica;
    }

    public void setClinica(Clinica clinica) {
	this.clinica = clinica;
    }

    public Persona getTitular() {
	return titular;
    }

    public void setTitular(Persona titular) {
	this.titular = titular;
    }

    public String getSha1() {
	return sha1;
    }

    public void setSha1(String sha1) {
	this.sha1 = sha1;
    }

    public byte[] getPdf() {
	return pdf;
    }

    public void setPdf(byte[] pdf) {
	this.pdf = pdf;
    }

	public ArrayList getOpciones() {
		return opciones;
	}

	public void setOpciones(ArrayList opciones) {
		this.opciones = opciones;
	}

	
}
