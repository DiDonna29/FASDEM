/**
 * 18/02/2011
 * marcenrl
 * 2011
 */
package ve.gob.dem.fasdem.bean;

import java.io.Serializable;

/**
 * @author marcenrl 
 * 
 */
public class FirmaRegistro implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5729798685650719716L;
	/**
	 * 
	 */
	private int id;
	private int idSiniestro;
	private String fileName;
	private String contentType;
	private byte[] data;
	private int uploadLenght;
	private String descripcion;
	private String idNotaMedica;
	private int anioSiniestro;
	private String idUsuario;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the idSiniestro
	 */
	public int getIdSiniestro() {
		return idSiniestro;
	}

	/**
	 * @param idSiniestro
	 *            the idSiniestro to set
	 */
	public void setIdSiniestro(int idSiniestro) {
		this.idSiniestro = idSiniestro;
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
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType
	 *            the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(byte[] data) {
		this.data = data;
	}

	/**
	 * @return the uploadLenght
	 */
	public int getUploadLenght() {
		return uploadLenght;
	}

	/**
	 * @param uploadLenght
	 *            the uploadLenght to set
	 */
	public void setUploadLenght(int uploadLenght) {
		this.uploadLenght = uploadLenght;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIdNotaMedica() {
		return idNotaMedica;
	}

	public void setIdNotaMedica(String idNotaMedica) {
		this.idNotaMedica = idNotaMedica;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setAnioSiniestro(int anioSiniestro) {
		this.anioSiniestro = anioSiniestro;
	}

	public int getAnioSiniestro() {
		return anioSiniestro;
	}

}
