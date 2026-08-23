/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.exp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import ve.gob.dem.fasdem.action.pago.AnulacionHojaRutaONT;
import ve.gob.dem.fasdem.bean.HojaRuta;
import ve.gob.dem.fasdem.bean.SiniestroBandeja;
import ve.gob.dem.fasdem.persit.PerHojaRuta;
import ve.gob.dem.fasdem.persit.PerSiniestroBandeja;
import ve.gob.dem.fasdem.persit.pagos.PerPreOrdenPago;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalException;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

public class ExpHojaRuta {
	 static protected Logger log = Logger.getLogger(ExpHojaRuta.class);

    
    public static HojaRuta BuscarPorid(int id) throws NamingException, SQLException, PersonalNotFoundException, NumberFormatException, IOException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerHojaRuta p = new PerHojaRuta(c);
    	    return p.buscarporId(id);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
   }
    
    public static HojaRuta buscarporIdStatus(int id) throws NamingException, SQLException, PersonalNotFoundException, NumberFormatException, IOException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerHojaRuta p = new PerHojaRuta(c);
    	    return p.buscarporIdStatus(id);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
   }
    
    
    public static int BuscarRechazo(String id) throws NamingException, SQLException, PersonalNotFoundException, NumberFormatException, IOException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerHojaRuta p = new PerHojaRuta(c);
    	    return p.buscarRechazo(id);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
   } 
    
    
    public static void UpdateRechazo(String id) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException {
		Connection cnx = null;
 		 try {
 		   cnx = Conexion.getConexion();
 		   PerHojaRuta ps = new PerHojaRuta(cnx);
 		   ps.UpdateRechazo(id);

 			}
 		 finally{
 			   if (cnx!=null) Conexion.closeConexion(cnx);
 		 }
		
	}
    
    
    public static ArrayList BuscarPorNumeroUsuario(String numero, int anio,int usuario) throws NamingException, SQLException, PersonalNotFoundException, NumberFormatException, IOException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerHojaRuta p = new PerHojaRuta(c);
    	    return p.buscarporNumeroUsuario(numero, anio,usuario);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
   }
    
    
    
    public static ArrayList BuscarPorNumeroGeneral(String numero, int anio) throws NamingException, SQLException, PersonalNotFoundException, NumberFormatException, IOException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerHojaRuta p = new PerHojaRuta(c);
    	    return p.buscarporNumeroGeneral(numero, anio);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
   }
    
    public static ArrayList BuscarListaUsuario(int usuario,String anio_mes) throws NamingException, SQLException, PersonalNotFoundException, NumberFormatException, IOException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerHojaRuta p = new PerHojaRuta(c);
    	    return p.buscarListaUsuario(usuario,anio_mes);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
   }
    
    public static ArrayList BuscarListaGeneral(String anio_mes) throws NamingException, SQLException, PersonalNotFoundException, NumberFormatException, IOException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerHojaRuta p = new PerHojaRuta(c);
    	    return p.buscarListaGeneral(anio_mes);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
   } 
    
    public static ArrayList BuscarListaGeneralR(String anio_mes) throws NamingException, SQLException, PersonalNotFoundException, NumberFormatException, IOException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerHojaRuta p = new PerHojaRuta(c);
    	    return p.buscarListaGeneralR(anio_mes);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
   } 
    
    public static int buscarCodigoNuevo(int anio,String aniomes) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerHojaRuta po = new PerHojaRuta(cnx);
			return po.buscarCodigoNuevo(anio,aniomes);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}
    
    
    public static ArrayList buscarDetalleHoja(int hoja) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerHojaRuta p = new PerHojaRuta(c);
    	    return p.buscarDetalleHoja(hoja);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
   }
    
    public static int BuscarCantidadPagos(int hoja) throws NamingException, SQLException, PersonalNotFoundException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerHojaRuta p = new PerHojaRuta(c);
    	    return p.buscarCantidadPagos(hoja);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
   }
    
    
    public static void InsertarPreOrdenHoja(int id_hoja,int pre,String causado,double pagar,int anio,String cod_pre,double fijo,double cont,double obr,double jub,double incap,double liqui,double isrl,double timbre,double iva,int rechazo,double base_iva,double monto_iva) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException{
		Connection cnx = null;
		 try {
		   cnx = Conexion.getConexion();
		   PerHojaRuta ps = new PerHojaRuta(cnx);
		   ps.InsertarPreOrdenHoja(id_hoja,pre,causado,pagar,anio,cod_pre,fijo,cont,obr,jub,incap,liqui,isrl,timbre,iva,rechazo,base_iva,monto_iva);
			}
		 finally{
			   if (cnx!=null) Conexion.closeConexion(cnx);
		 }
	 }	
    
    
    public static void CrearHojaRuta(String numero,int analista,int anio,int secuencia,String anio_mes,int periodo,int tipo) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException{
  		Connection cnx = null;
  		 try {
  		   cnx = Conexion.getConexion();
  		   PerHojaRuta ps = new PerHojaRuta(cnx);
  		   ps.CrearHojaRuta(numero,analista,anio,secuencia,anio_mes,periodo,tipo);
  			}
  		 finally{
  			   if (cnx!=null) Conexion.closeConexion(cnx);
  		 }
  	 }	
    
    public static void EliminarPreOrdenHoja(int id_pre_orden,int id_hoja,int anio) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException{
		Connection cnx = null;
		 try {
		   cnx = Conexion.getConexion();
		   PerHojaRuta ps = new PerHojaRuta(cnx);
		   ps.EliminarPreOrdenHoja(id_pre_orden,id_hoja,anio);
			}
		 finally{
			   if (cnx!=null) Conexion.closeConexion(cnx);
		 }
	 }	
    
    
    
    public static void DesAsociarPreOrdenHoja(String pre_orden,int anio,int estatus) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException{
		
    	log.info("ENTRA A AL EXPERTO");
    	
    	Connection cnx = null;
		 try {
		   cnx = Conexion.getConexion();
		   PerHojaRuta ps = new PerHojaRuta(cnx);
		   ps.DesAsociarPreOrdenHoja(pre_orden, anio,estatus);
			}
		 finally{
			   if (cnx!=null) Conexion.closeConexion(cnx);
		 }
	 }	
    
    
    
     public static void DesAsociarPreOrdenHojaTesoreria(String pre_orden,int anio,int estatus) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException{
		
    
    	Connection cnx = null;
		 try {
		   cnx = Conexion.getConexionTesoreria();
		   PerHojaRuta ps = new PerHojaRuta(cnx);
		   ps.DesAsociarPreOrdenHojaTesoreria(pre_orden, anio,estatus);
			}
		 finally{
			   if (cnx!=null) Conexion.closeConexion(cnx);
		 }
	 }	
    
    
    
    public static void CambiarEstatusHoja(int id_hoja,int status,int anio) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException{
  		Connection cnx = null;
  		 try {
  		   cnx = Conexion.getConexion();
  		   PerHojaRuta ps = new PerHojaRuta(cnx);
  		   ps.CambiarEstatusHoja(id_hoja,status,anio);

  			}
  		 finally{
  			   if (cnx!=null) Conexion.closeConexion(cnx);
  		 }
  	 }



	public static void GuardarCausado(int id_hoja, String causado) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException {
		Connection cnx = null;
 		 try {
 		   cnx = Conexion.getConexion();
 		   PerHojaRuta ps = new PerHojaRuta(cnx);
 		   ps.AgregarCausado(id_hoja,causado);

 			}
 		 finally{
 			   if (cnx!=null) Conexion.closeConexion(cnx);
 		 }
		
	}		
	
	public static Double contratados(int id_hoja) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
 		 try {
 		   cnx = Conexion.getConexion();
 		   PerHojaRuta ps = new PerHojaRuta(cnx);
 		   return ps.MontoC(id_hoja);
 
 			}
 		 finally{
 			   Conexion.closeConexion(cnx);
 		 }		
	}	
	
	public static Double fijos(int id_hoja) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
 		 try {
 		   cnx = Conexion.getConexion();
 		   PerHojaRuta ps = new PerHojaRuta(cnx);
 		   return ps.MontoF(id_hoja);
 
 			}
 		 finally{
 			   Conexion.closeConexion(cnx);
 		 }		
	}	
	
	public static Double obreros(int id_hoja) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
 		 try {
 		   cnx = Conexion.getConexion();
 		   PerHojaRuta ps = new PerHojaRuta(cnx);
 		   return ps.MontoO(id_hoja);
 
 			}
 		 finally{
 			   Conexion.closeConexion(cnx);
 		 }		
	}
	
	public static Double jubilados(int id_hoja) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
 		 try {
 		   cnx = Conexion.getConexion();
 		   PerHojaRuta ps = new PerHojaRuta(cnx);
 		   return ps.MontoJ(id_hoja);

 			}
 		 finally{
 			   Conexion.closeConexion(cnx);
 		 }
		
	}	
    
	public static Double incapacitados(int id_hoja) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
 		 try {
 		   cnx = Conexion.getConexion();
 		   PerHojaRuta ps = new PerHojaRuta(cnx);
 		   return ps.MontoI(id_hoja);

 			}
 		 finally{
 			   Conexion.closeConexion(cnx);
 		 }
		
	}
}
