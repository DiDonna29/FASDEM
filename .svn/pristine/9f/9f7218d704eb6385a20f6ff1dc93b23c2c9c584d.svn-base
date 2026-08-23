/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.persit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.bean.HojaRuta;
import ve.gob.dem.fasdem.bean.PreOrdenPago;
import ve.gob.dem.fasdem.bean.SiniestroBandeja;
import ve.gob.dem.fasdem.exp.ExpHojaRuta;
import ve.gob.dem.fasdem.exp.administrador.ExpCuenta;
import ve.gob.dem.fasdem.exp.pagos.ExpEstatusHojaRuta;
import ve.gob.dem.fasdem.exp.pagos.ExpPreOrdenPago;
import ve.gob.dem.fasdem.exp.pagos.ExpTipoHojaRuta;
import ve.gob.dem.fasdem.exp.pagos.ExpTipoProveedor;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Errores;
import ve.gob.dem.framework.seguridad.exp.ExpUsuario;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("unchecked")
public class PerHojaRuta {

	
    private Connection cnx = null;
    static protected Logger log = Logger.getLogger(PerHojaRuta.class);

    public PerHojaRuta(Connection c) {
    	this.cnx = c;
    }
    
 
    
    		
   public int buscarCantidadPagos(int hoja) throws SQLException, PersonalNotFoundException, NamingException  {
		
    	ArrayList array = new ArrayList();
		ResultSet rs = null;
		Statement sentencia = null;
		String sql = " select count(*) as cantidad from hoja_ruta_preorden where id_hoja_ruta= " + hoja;
		int cant=0;

	
	log.info(sql);
	try {
	    sentencia = cnx.createStatement();
	    rs = sentencia.executeQuery(sql);
	    while (rs.next()) {
		 cant = rs.getInt("cantidad");
	    }
	    
	    return cant;
	} finally {
	    try {
		if (rs != null)
		    rs.close();
		if (sentencia != null)
		    sentencia.close();
	    } catch (SQLException e) {
		log.error(e);
	    }
	}
    }		
    		
    		
    		
    		
    		


    private ArrayList buscar(String arg) throws SQLException, PersonalNotFoundException, NamingException, NumberFormatException, IOException  {
		
    	ArrayList array = new ArrayList();
		ResultSet rs = null;
		Statement sentencia = null;
		String sql = "SELECT * FROM hoja_ruta ";
		if (arg!=null){
		    sql = sql + " " + arg;
		}

	
	log.info(sql);
	try {
	    sentencia = cnx.createStatement();
	    rs = sentencia.executeQuery(sql);
	    while (rs.next()) {
		HojaRuta c = new HojaRuta();
		
			c.setId(rs.getInt("id_hoja_ruta"));
			c.setDescripcion(rs.getString("descripcion"));
			c.setNumero(rs.getString("numero"));
			c.setFecha(rs.getDate("fecha"));
			c.setCantidad(ExpHojaRuta.BuscarCantidadPagos(rs.getInt("id_hoja_ruta")));
		    c.setStatus(ExpEstatusHojaRuta.buscarpoID(rs.getInt("estatus")));
		    c.setAnalista(ExpUsuario.buscarDatosP(String.valueOf(rs.getInt("analista"))));
		    c.setPeriodo_fiscal(rs.getInt("periodo_fiscal"));
		    c.setTipo(ExpTipoHojaRuta.buscarpoID(rs.getInt("tipo_hoja")));
		    c.setSecuencia_hoja(rs.getInt("secuencia_hoja"));
		    
		    array.add(c);
	    }
	    
	    return array;
	} finally {
	    try {
		if (rs != null)
		    rs.close();
		if (sentencia != null)
		    sentencia.close();
	    } catch (SQLException e) {
		log.error(e);
	    }
	}
    }
   
    
    public HojaRuta buscarporId(int id) throws SQLException, PersonalNotFoundException, NamingException, NumberFormatException, IOException  {
 	   return (HojaRuta) this.buscar("  WHERE ID_HOJA_RUTA=" + id +" ").get(0);
    }
    
    public HojaRuta buscarporIdStatus(int id) throws SQLException, PersonalNotFoundException, NamingException, NumberFormatException, IOException  {
  	   return (HojaRuta) this.buscar("  WHERE ID_HOJA_RUTA=" + id +" and ESTATUS=4").get(0);
     }
    
    public ArrayList buscarporNumeroUsuario(String numero, int anio, int usuario) throws SQLException, PersonalNotFoundException, NamingException, NumberFormatException, IOException  {
  	   return this.buscar("  WHERE ANALISTA=" + usuario + " and  numero='" + numero +"' and  anio_hoja=" + anio);
     }
    
    public ArrayList buscarporNumeroGeneral(String numero, int anio) throws SQLException, PersonalNotFoundException, NamingException, NumberFormatException, IOException  {
   	   return this.buscar("  WHERE numero='" + numero +"' and  anio_hoja=" + anio);
      }
    
    
    public ArrayList buscarListaUsuario(int usuario,String anio_mes) throws SQLException, PersonalNotFoundException, NamingException, NumberFormatException, IOException  {
  	   return this.buscar("  WHERE ANALISTA=" + usuario + " and TO_DATE(TO_CHAR(fecha,'MM/YYYY'),'MM/YYYY')= TO_DATE('" + anio_mes + "','MM/YYYY') ORDER BY id_hoja_ruta ASC");
     }
    
    public ArrayList buscarListaGeneral(String anio_mes) throws SQLException, PersonalNotFoundException, NamingException, NumberFormatException, IOException  {
   	   return this.buscar(" WHERE TO_DATE(TO_CHAR(fecha,'MM/YYYY'),'MM/YYYY')= TO_DATE('" + anio_mes + "','MM/YYYY') ORDER BY id_hoja_ruta ASC");
      }
    
    public ArrayList buscarListaGeneralR(String anio_mes) throws SQLException, PersonalNotFoundException, NamingException, NumberFormatException, IOException  {
    	   return this.buscar(" WHERE tipo_hoja in (3,4) and TO_DATE(TO_CHAR(fecha,'MM/YYYY'),'MM/YYYY')= TO_DATE('" + anio_mes + "','MM/YYYY') ORDER BY id_hoja_ruta ASC");
       }
    
    
    public void InsertarPreOrdenHoja(int id_hoja,int pre,String causado,double pagar,int anio,String cod_pre,double fijo,double cont,double obr,double jub,double incap, double liqui, double isrl, double timbre, double iva, int rechazo, double base_iva,double monto_iva) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;
		  

		  String sql="INSERT INTO hoja_ruta_preorden (id_hoja_ruta,id_preorden,causado,monto_pre_orden,anio_preorden,codigo,monto_fijo,monto_contratado,monto_obrero,monto_jubilado,monto_incapacitado, monto_liquidado,monto_isrl,monto_timbre,iva,rechazo,base_iva,monto_iva) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		 
		  try{
			    sentencia = cnx.prepareStatement(sql);

			    sentencia.setInt(1,id_hoja); 
			    sentencia.setInt(2,pre);
			    sentencia.setString(3,causado);
			    sentencia.setDouble(4,pagar);
			    sentencia.setInt(5,anio);
			    sentencia.setString(6,cod_pre);
			    
			    sentencia.setDouble(7,fijo);
			    sentencia.setDouble(8,cont);
			    sentencia.setDouble(9,obr);
			    sentencia.setDouble(10,jub);
			    sentencia.setDouble(11,incap);
			    sentencia.setDouble(12,liqui);
			    sentencia.setDouble(13,isrl);
			    sentencia.setDouble(14,timbre);
			    sentencia.setDouble(15,iva);
			    sentencia.setInt(16,rechazo);
			    sentencia.setDouble(17,base_iva);
			    sentencia.setDouble(18,monto_iva);
			    
                sentencia.executeUpdate();
                
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }
	
		
	
	 }
    
    
    public int buscarCodigoNuevo(int anio,String aniomes) throws SQLException, PersonalNotFoundException, NamingException {
		ResultSet rs = null;
		Statement sentencia = null;
		String sql = "select COALESCE(max(secuencia_hoja),0) as codigo from hoja_ruta where anio_hoja=" + anio + " and aniomes='"  + aniomes + "'";
		int cod = 0;
		
		log.info("SQL CODIGO NUEVO " + sql);
		
		try {
			sentencia = cnx.createStatement();
			rs = sentencia.executeQuery(sql);
			while (rs.next()) {
				cod = rs.getInt("codigo");
			}
			return cod;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (sentencia != null)
					sentencia.close();
			} catch (SQLException e) {
				log.error(e);
			}
		}
	}
    
    
    
    public void CrearHojaRuta(String numero,int analista,int anio,int secuencia,String anio_mes,int periodo,int tipo) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;
		  

		  String sql="INSERT INTO hoja_ruta (numero,analista,anio_hoja,secuencia_hoja,aniomes,periodo_fiscal,tipo_hoja) VALUES (?,?,?,?,?,?,?)";
		 
		  try{
			    sentencia = cnx.prepareStatement(sql);

			    sentencia.setString(1,numero); 
			    sentencia.setInt(2,analista);
			    sentencia.setInt(3,anio);
			    sentencia.setInt(4,secuencia);
			    sentencia.setString(5,anio_mes);
			    sentencia.setInt(6,periodo);
			    sentencia.setInt(7,tipo);
			    
			    sentencia.executeUpdate();
              
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }
	
		
	
	 }
    

    public void EliminarPreOrdenHoja(int id_pre_orden,int id_hoja,int anio) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;
		  

		  String sql="DELETE FROM hoja_ruta_preorden WHERE id_preorden=? and id_hoja_ruta=? and anio_preorden=? ";
		 log.info("SQL ELEIMINAR " + sql);
		  try{
			    sentencia = cnx.prepareStatement(sql);
         log.info("ID_PREORDEN " + id_pre_orden ) ;
         log.info("id_hoja_ruta " + id_hoja ) ;
         log.info("anio_preorden " + anio ) ;
			    sentencia.setInt(1,id_pre_orden); 
			    sentencia.setInt(2,id_hoja);
			    sentencia.setInt(3,anio);
			    
			    
              sentencia.executeUpdate();
              
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }

	
	 }
    
    
    public void DesAsociarPreOrdenHoja(String pre_orden,int anio,int estatus) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;
		 
		  log.info("SQL codigo " + pre_orden);
		  log.info("SQL anio " + anio);

		  String sql="UPDATE hoja_ruta_preorden SET ESTATUS=" + estatus + " WHERE codigo=? and anio_preorden=? ";
		 
		  log.info("SQL DESASOCIAR " + sql);
		  log.info("SQL codigo " + pre_orden);
		  log.info("SQL anio " + anio);
		  
		  try{
			    sentencia = cnx.prepareStatement(sql);

			    sentencia.setString(1,pre_orden); 
			    sentencia.setInt(2,anio);
			    sentencia.executeUpdate();
            
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }

	
	 }
    
    
    
    public void DesAsociarPreOrdenHojaTesoreria(String pre_orden,int anio,int estatus) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;
		 
	

		  String sql="UPDATE bandeja_recepcion_fasdem SET ESTATUS=" + estatus + " WHERE id_preorden=? and anio=? ";
		 
		  
		  try{
			    sentencia = cnx.prepareStatement(sql);
                sentencia.setString(1,pre_orden); 
			    sentencia.setInt(2,anio);
			    sentencia.executeUpdate();
          
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }

	
	 }
    

    
 public ArrayList buscarDetalleHoja(int hoja) throws SQLException, PersonalNotFoundException, NamingException, ClassNotFoundException  {
		
    	ArrayList array = new ArrayList();
		ResultSet rs = null;
		Statement sentencia = null;
		String sql = "SELECT * FROM hoja_ruta_preorden WHERE id_hoja_ruta= " + hoja;
		

	
	log.info(sql);
	try {
	    sentencia = cnx.createStatement();
	    rs = sentencia.executeQuery(sql);
	    while (rs.next()) {
	      PreOrdenPago p =new PreOrdenPago();
	     
	      p=ExpPreOrdenPago.buscarPorIdPreOrdenHojaRuta(rs.getInt("id_preorden"), rs.getInt("anio_preorden"));
	      p.setCod_completo(rs.getString("codigo"));
		  p.setMonto(rs.getDouble("monto_pre_orden"));
		  p.setMontotxt(rs.getString("monto_pre_orden"));
	      p.setCausado(rs.getString("causado"));
	      array.add(p); 
	    }
	    
	    return array;
	} finally {
	    try {
		if (rs != null)
		    rs.close();
		if (sentencia != null)
		    sentencia.close();
	    } catch (SQLException e) {
		log.error(e);
	    }
	}
    }
    
    
    
    
    
 public void CambiarEstatusHoja(int id_hoja,int status,int anio) throws SQLException, ClassNotFoundException, IOException{
	  PreparedStatement sentencia = null;
	  

	  String sql="UPDATE hoja_ruta SET estatus=? WHERE id_hoja_ruta=? AND anio_hoja=? ";
	 
	  try{
		    sentencia = cnx.prepareStatement(sql);

		    sentencia.setInt(1,status); 
		    sentencia.setInt(2,id_hoja);
            sentencia.setInt(3,anio);
            sentencia.executeUpdate();
           
	  }
	  finally {
  
		 if (sentencia != null) sentencia.close();
	 
	 }

	

}




public void AgregarCausado(int id_hoja, String causado) throws SQLException, ClassNotFoundException, IOException{
	PreparedStatement sentencia = null;
	  

	  String sql="UPDATE hoja_ruta_preorden set causado=? where ID_HOJA_RUTA=? ";
	  

	 
	  try{
		    sentencia = cnx.prepareStatement(sql);

		    sentencia.setString(1,causado); 
		    sentencia.setInt(2,id_hoja);
          sentencia.executeUpdate();
         
	  }
	  finally {

		 if (sentencia != null) sentencia.close();
	 
	 }
	  
	  
	
}
    
    
public double MontoC(int id) throws SQLException, PersonalNotFoundException, NamingException {
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "select sum(monto_contratado) as contratado from hoja_ruta_preorden where id_HOJA_RUTA='" + id + "'";
	double cod = 0;
	
	log.info("SQL CODIGO NUEVO " + sql);
	
	try {
		sentencia = cnx.createStatement();
		rs = sentencia.executeQuery(sql);
		while (rs.next()) {
			cod = rs.getDouble("contratado");
		}
		return cod;
	} finally {
		try {
			if (rs != null)
				rs.close();
			if (sentencia != null)
				sentencia.close();
		} catch (SQLException e) {
			log.error(e);
		}
	}
}

public double MontoJ(int id) throws SQLException, PersonalNotFoundException, NamingException {
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "select sum(monto_jubilado) as jubilados from hoja_ruta_preorden where id_HOJA_RUTA='" + id + "'";
	double cod = 0;
	
	log.info("SQL CODIGO NUEVO " + sql);
	
	try {
		sentencia = cnx.createStatement();
		rs = sentencia.executeQuery(sql);
		while (rs.next()) {
			cod = rs.getDouble("jubilados");
		}
		return cod;
	} finally {
		try {
			if (rs != null)
				rs.close();
			if (sentencia != null)
				sentencia.close();
		} catch (SQLException e) {
			log.error(e);
		}
	}
}

public double MontoO(int id) throws SQLException, PersonalNotFoundException, NamingException {
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "select sum(monto_obrero) as obreros from hoja_ruta_preorden where id_HOJA_RUTA='" + id + "'";
	double cod = 0;
	
	log.info("SQL CODIGO NUEVO " + sql);
	
	try {
		sentencia = cnx.createStatement();
		rs = sentencia.executeQuery(sql);
		while (rs.next()) {
			cod = rs.getDouble("obreros");
		}
		return cod;
	} finally {
		try {
			if (rs != null)
				rs.close();
			if (sentencia != null)
				sentencia.close();
		} catch (SQLException e) {
			log.error(e);
		}
	}
}


public double MontoF(int id) throws SQLException, PersonalNotFoundException, NamingException {
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "select sum(monto_fijo) as fijos from hoja_ruta_preorden where id_HOJA_RUTA='" + id + "'";
	double cod = 0;
	
	log.info("SQL CODIGO NUEVO " + sql);
	
	try {
		sentencia = cnx.createStatement();
		rs = sentencia.executeQuery(sql);
		while (rs.next()) {
			cod = rs.getDouble("fijos");
		}
		return cod;
	} finally {
		try {
			if (rs != null)
				rs.close();
			if (sentencia != null)
				sentencia.close();
		} catch (SQLException e) {
			log.error(e);
		}
	}
}


public double MontoI(int id) throws SQLException, PersonalNotFoundException, NamingException {
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "select sum(monto_incapacitado) as incapacitados from hoja_ruta_preorden where id_HOJA_RUTA='" + id + "'";
	double cod = 0;
	
	log.info("SQL CODIGO NUEVO " + sql);
	
	try {
		sentencia = cnx.createStatement();
		rs = sentencia.executeQuery(sql);
		while (rs.next()) {
			cod = rs.getDouble("incapacitados");
		}
		return cod;
	} finally {
		try {
			if (rs != null)
				rs.close();
			if (sentencia != null)
				sentencia.close();
		} catch (SQLException e) {
			log.error(e);
		}
	}
}

public int buscarRechazo(String id) throws SQLException, PersonalNotFoundException, NamingException {
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "select rechazo from hoja_ruta_preorden where codigo='" + id + "' and rechazo='2'";
	int cod = 0;
	
	try {
		sentencia = cnx.createStatement();
		rs = sentencia.executeQuery(sql);
		while (rs.next()) {
			cod = rs.getInt("rechazo");
		}
		return cod;
	} finally {
		try {
			if (rs != null)
				rs.close();
			if (sentencia != null)
				sentencia.close();
		} catch (SQLException e) {
			log.error(e);
		}
	}
}

public void UpdateRechazo(String id) throws SQLException, ClassNotFoundException, IOException{
	PreparedStatement sentencia = null;

	  String sql="UPDATE hoja_ruta_preorden set rechazo=0 where codigo=? and rechazo=2 ";
	  log.info("cambio 0 " + sql);

	  try{
		  sentencia = cnx.prepareStatement(sql);

		  sentencia.setString(1,id); 
          sentencia.executeUpdate();       
	  }
	  finally {

		 if (sentencia != null) sentencia.close();	 
	 }	
}

}
