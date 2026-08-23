/**
 * 18/02/2011
 * marcenrl
 * 2011
 */
package ve.gob.dem.fasdem.action.upload.formbeam;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * @author marcenrl
 * 
 */
public class UploadForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1195108336656432819L;
	private FormFile theFile;
	private String descripcion;
	private String anioSiniestro;

	/**
	 * @return the theFile
	 */
	public FormFile getTheFile() {
		return theFile;
	}

	/**
	 * @param theFile
	 *            the theFile to set
	 */
	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.descripcion ="";
		this.theFile=null;
	}

	/**
	 * @return the anioSiniestro
	 */
	public String getAnioSiniestro() {
		return anioSiniestro;
	}

	/**
	 * @param anioSiniestro the anioSiniestro to set
	 */
	public void setAnioSiniestro(String anioSiniestro) {
		this.anioSiniestro = anioSiniestro;
	}
	
	
}
