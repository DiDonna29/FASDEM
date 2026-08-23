/*
 * Created on 20-abr-2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package ve.gob.dem.framework.seguridad.exp;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;


import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.seguridad.bean.Accion;
import ve.gob.dem.framework.seguridad.bean.Usuario;
import ve.gob.dem.framework.seguridad.persistencia.PerAccion;
/**
 * @author hvazquez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
@SuppressWarnings("rawtypes")
public class ExpAccion implements Serializable {
  /**
     * 
     */
    private static final long serialVersionUID = -7693433314304597612L;

public static Accion buscarPorId(String id) throws NamingException, SQLException, PersonalNotFoundException, IOException {
	Connection cnx = null;
	try {
	  cnx = Conexion.getConexionSec();
	  PerAccion perAccionPunto = new PerAccion(cnx);
	  return perAccionPunto.buscarPorId(id);
	}finally{
	  if (cnx!=null) Conexion.closeConexion(cnx);
	}
  }
	 
  private static void incluirAccion(Accion p) throws NamingException, SQLException{
	Connection cnx = null;
	try {
	  cnx = Conexion.getConexionSec();
	  PerAccion pp = new PerAccion(cnx);
	  pp.incluirAccion(p);
	}finally {
	  if (cnx!=null) Conexion.closeConexion(cnx);
	}
  }	
	 
  public static void incluirAccion(String id_tipo,String descripcion,String identificador,Usuario usu) throws PersonalNotFoundException, NamingException, SQLException, IOException{
	Accion acc = new Accion();
	acc.setTipo(ExpTipoAccion.buscarPorId(id_tipo));
	acc.setIdentificador(identificador);
	acc.setDescripcion(descripcion);
	acc.setUsuario(usu);
	ExpAccion.incluirAccion(acc);
  }	

  public static void incluirAccion(String id_tipo,String identificador,Usuario usu) throws PersonalNotFoundException, NamingException, SQLException, IOException{
	ExpAccion.incluirAccion(id_tipo,null,identificador,usu);
  }	
		 

public static ArrayList buscarPorTipoIdentificador(String idTipo, String id) throws NamingException, SQLException, PersonalNotFoundException, IOException{
    Connection cnx = null;
	try {
	  cnx = Conexion.getConexionSec();
	  PerAccion perAccion= new PerAccion(cnx);
	  return perAccion.buscarPorTipoIdentificador(idTipo, id);
	}finally{
	  if (cnx!=null) Conexion.closeConexion(cnx);
	}
  }	 
  

  public static ArrayList buscarPorIdentificador(String id) throws NamingException, SQLException, PersonalNotFoundException, IOException {
	Connection cnx = null;
	try {
	  cnx = Conexion.getConexionSec();
	  PerAccion perAccion= new PerAccion(cnx);
	  return perAccion.buscarPorIdentificador(id);
	}finally{
	  if (cnx!=null) Conexion.closeConexion(cnx);
	}
  }
  

  public static ArrayList buscarPorTipoIdentificador(String sql) throws NamingException, SQLException, PersonalNotFoundException, IOException{
      Connection cnx = null;
  	try {
  	  cnx = Conexion.getConexionSec();
  	  PerAccion perAccion= new PerAccion(cnx);
  	  return perAccion.buscarPorTipoIdentificador(sql);
  	}finally{
  	  if (cnx!=null) Conexion.closeConexion(cnx);
  	}
    }	   

}
