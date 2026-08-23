/*
 * Created on 11-may-2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ve.gob.dem.framework.seguridad.bean;

import java.util.ArrayList;


import java.io.Serializable;

/**
 * @author hvazquez
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
@SuppressWarnings("rawtypes")
public class Usuario  implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5753094606684426291L;
	private String login = "";
    private String nombre = "";
    private String apellido = "";
    private String cedula = "";
    private String userRolApp = null;
    private boolean valido = false;

	private ArrayList permisos = new ArrayList();
    private Proyecto proyecto = new Proyecto();
    private String acceso = "";
    private String descripcion_usuario = "";
    private Integer idDependencia = 0;
  // private Dependencia dependencia = new Dependencia();
    private ArrayList detalle = new ArrayList();
    private String userRolAppDescripcion = "";
  //*******************DESCOMENTAR ESTA LÍNEA ANTES DE SUBIR LA APLICACIÓN*********************
     private Integer id_estado = 0;
  //*******************DESCOMENTAR ESTA LÍNEA ANTES DE SUBIR LA APLICACIÓN*********************
    
  //*******************COMENTAR ESTA LÍNEA ANTES DE SUBIR LA APLICACIÓN*********************
   // private Integer id_estado = 11;
  //*******************COMENTAR ESTA LÍNEA ANTES DE SUBIR LA APLICACIÓN*********************
    /**
     * @return
     */
    public String getApellido() {
	return apellido;
    }

    /**
     * @return
     */
    public String getCedula() {
	return cedula;
    }

    /**
     * @return
     */
    public String getLogin() {
	return login;
    }

    /**
     * @return
     */
    public String getNombre() {
	return nombre;
    }

    /**
     * @return
     */
    public ArrayList getPermisos() {
	return permisos;
    }

    /**
     * @return
     */
    public Proyecto getProyecto() {
	return proyecto;
    }

    /**
     * @return
     */
    public boolean isValido() {
	return valido;
    }

    /**
     * @param string
     */
    public void setApellido(String string) {
	apellido = string;
    }

    /**
     * @param string
     */
    public void setCedula(String string) {
	cedula = string;
    }

    /**
     * @param string
     */
    public void setLogin(String string) {
	login = string;
    }

    /**
     * @param string
     */
    public void setNombre(String string) {
	nombre = string;
    }

    /**
     * @param list
     */
    public void setPermisos(ArrayList list) {
	permisos = list;
    }

    /**
     * @param proyecto
     */
    public void setProyecto(Proyecto proyecto) {
	this.proyecto = proyecto;
    }

    /**
     * @param b
     */
    public void setValido(boolean b) {
	valido = b;
    }

    public boolean tieneAcceso(String pagina) {
	ArrayList permi = this.getPermisos();
	Nodo nod = null;
	for (int i = 0; i != permi.size(); i++) {
	    nod = (Nodo) permi.get(i);
	    if (nod.getPagina().equals(pagina)) {
		return true;
	    }
	}
	return false;
    }

    /**
     * @return
     */
    public String getAcceso() {
	return acceso;
    }

    /**
     * @param string
     */
    public void setAcceso(String string) {
	acceso = string;
    }

    public Integer getIdDependencia() {
	return idDependencia;
    }

    public void setIdDependencia(Integer idDependencia) {
	this.idDependencia = idDependencia;
    }



    public ArrayList getDetalle() {
	return detalle;
    }

    public void setDetalle(ArrayList detalle) {
	this.detalle = detalle;
    }

	public String getUserRolApp() {
		return userRolApp;
	}

	public void setUserRolApp(String userRolApp) {
		this.userRolApp = userRolApp;
	}

	public String getUserRolAppDescripcion() {
		return userRolAppDescripcion;
	}

	public void setUserRolAppDescripcion(String userRolAppDescripcion) {
		this.userRolAppDescripcion = userRolAppDescripcion;
	}

	public void setDescripcion_usuario(String descripcion_usuario) {
		this.descripcion_usuario = descripcion_usuario;
	}

	public String getDescripcion_usuario() {
		return descripcion_usuario;
	}

	public void setId_estado(Integer id_estado) {
		this.id_estado = id_estado;
	}

	public Integer getId_estado() {
		return id_estado;
	}
}
