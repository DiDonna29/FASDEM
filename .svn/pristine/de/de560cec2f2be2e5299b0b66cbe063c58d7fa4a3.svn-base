/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.exp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.naming.NamingException;
import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.bean.SiniestroBandeja;
import ve.gob.dem.fasdem.per.PerPersona;
import ve.gob.dem.fasdem.persit.PerSiniestroBandeja;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalException;
import ve.gob.dem.framework.exception.PersonalNotFoundException;


/**
 * @author marcenrl
 * 
 */

public class ExpSiniestroBandeja {

	public static void InsertarSiniestroBandeja(SiniestroBandeja sin) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException{
		Connection cnx = null;
		 try {
		   cnx = Conexion.getConexion();
		   PerSiniestroBandeja ps = new PerSiniestroBandeja(cnx);
		   ps.InsertarSiniestroBandeja(sin);
			}
		 finally{
			   if (cnx!=null) Conexion.closeConexion(cnx);
		 }
	 }	
	
	public static void InsertarSiniestroBandejaMedicos(SiniestroBandeja sin) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException{
		Connection cnx = null;
		 try {
		   cnx = Conexion.getConexion();
		   PerSiniestroBandeja ps = new PerSiniestroBandeja(cnx);
		   ps.InsertarSiniestroBandejaMedico(sin);
			}
		 finally{
			   if (cnx!=null) Conexion.closeConexion(cnx);
		 }
	 }	
    
	
	 public static ArrayList buscarListaEstatusTipo(String estatus, String tipo) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    	    PerSiniestroBandeja ps = new PerSiniestroBandeja(cnx);
	    	    return ps.BuscarListaEstatusTipo(estatus, tipo);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 
	 public static ArrayList BuscarListaPendientesyAtencion(String tipo, String anio) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    	    PerSiniestroBandeja ps = new PerSiniestroBandeja(cnx);
	    	    return ps.BuscarListaPendientesyAtencion(tipo, anio);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 public static ArrayList BuscarListaPendientesyAtencionMedico(String fecha) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    	    PerSiniestroBandeja ps = new PerSiniestroBandeja(cnx);
	    	    return ps.BuscarListaPendientesyAtencionMedico(fecha);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 public static ArrayList BuscarListaAtendidosyAtencion(String tipo,String analista,String fecha,String fecha2) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    	    PerSiniestroBandeja ps = new PerSiniestroBandeja(cnx);
	    	    return ps.BuscarListaAtendidosyAtencion(tipo,analista,fecha,fecha2);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 public static String BuscarCantidadEstatusTipo(String estatus, String tipo) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    	    PerSiniestroBandeja ps = new PerSiniestroBandeja(cnx);
	    	    return ps.BuscarCantidadEstatusTipo(estatus, tipo);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 public static String BuscarCantidadEstatusTipoMedico(String estatus,String fecha) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    	    PerSiniestroBandeja ps = new PerSiniestroBandeja(cnx);
	    	    return ps.BuscarCantidadEstatusTipoMedico(estatus,fecha);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 public static String BuscarCantidadEstatusTipoFecha(String estatus, String tipo, String fecha) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    	    PerSiniestroBandeja ps = new PerSiniestroBandeja(cnx);
	    	    return ps.BuscarCantidadEstatusTipoFecha(estatus, tipo,fecha);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 public static String BuscarCantidadEstatusTipoFechaMedico(String estatus, String fecha) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    	    PerSiniestroBandeja ps = new PerSiniestroBandeja(cnx);
	    	    return ps.BuscarCantidadEstatusTipoFechaMedico(estatus,fecha);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
 
	 
	 public static ArrayList BuscarListaEstatusTipoClinicaFecha(String estatus, String tipo,String clinica, String fecha_hoy) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    	    PerSiniestroBandeja ps = new PerSiniestroBandeja(cnx);
	    	    return ps.BuscarListaEstatusTipoClinicaFecha(estatus, tipo,clinica,fecha_hoy);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 }
	 
	 
	 public static ArrayList BuscarListaCedula(String cedula) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    	    PerSiniestroBandeja ps = new PerSiniestroBandeja(cnx);
	    	    return ps.BuscarListaCedula(cedula);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 } 
	 
	 public static SiniestroBandeja BuscarpoId(int id) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    	    PerSiniestroBandeja ps = new PerSiniestroBandeja(cnx);
	    	    return ps.BuscarporId(id);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 } 
	 public static SiniestroBandeja BuscarpoIdMedico(int id,String fecha) throws NamingException, SQLException, PersonalNotFoundException  {
		 Connection cnx = null;
	    	try {
	    		cnx = Conexion.getConexion();
	    	    PerSiniestroBandeja ps = new PerSiniestroBandeja(cnx);
	    	    return ps.BuscarporIdMedico(id,fecha);
	    	} finally {
	    	    Conexion.closeConexion(cnx);
	    	}
	 } 
	
	
	 public static void CambiarEstatusSiniestro(int id_siniestro, int estatus, String Login,String Datos_analista,String Observacion) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException{
			Connection cnx = null;
			 try {
			   cnx = Conexion.getConexion();
			   PerSiniestroBandeja ps = new PerSiniestroBandeja(cnx);
			   ps.CambiarEstatusSiniestro(id_siniestro,estatus,Login,Datos_analista,Observacion);
				 }
			 finally{
				   if (cnx!=null) Conexion.closeConexion(cnx);
			 }
		 }	
	
	 public static void CambiarEstatusSiniestroMedico(int id_siniestro,int estatus, String Login,String Datos_analista) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException{
			Connection cnx = null;
			 try {
			   cnx = Conexion.getConexion();
			   PerSiniestroBandeja ps = new PerSiniestroBandeja(cnx);
			   ps.CambiarEstatusSiniestroMedico(id_siniestro,estatus,Login,Datos_analista);
				 }
			 finally{
				   if (cnx!=null) Conexion.closeConexion(cnx);
			 }
		 }	
	
	 public static void TomarSiniestro(int id_siniestro, int estatus, String Login,String Datos_analista) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException{
			Connection cnx = null;
			 try {
			   cnx = Conexion.getConexion();
			   PerSiniestroBandeja ps = new PerSiniestroBandeja(cnx);
			   ps.TomarSiniestro(id_siniestro,estatus,Login,Datos_analista);
				 }
			 finally{
				   if (cnx!=null) Conexion.closeConexion(cnx);
			 }
		 }
	 public static void TomarSiniestroMedico(int id_siniestro, int estatus, String Login,String Datos_analista) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException{
			Connection cnx = null;
			 try {
			   cnx = Conexion.getConexion();
			   PerSiniestroBandeja ps = new PerSiniestroBandeja(cnx);
			   ps.TomarSiniestroMedico(id_siniestro,estatus,Login,Datos_analista);
				 }
			 finally{
				   if (cnx!=null) Conexion.closeConexion(cnx);
			 }
		 }
	 
	 
	
    
}
