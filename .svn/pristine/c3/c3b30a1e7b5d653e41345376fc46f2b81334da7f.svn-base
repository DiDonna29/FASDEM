/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.persit.administrador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.NamingException;
import org.apache.log4j.Logger;

import ve.gob.dem.fasdem.bean.Banco;
import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.bean.Cuenta;
import ve.gob.dem.fasdem.bean.Especialidad;
import ve.gob.dem.fasdem.bean.EstatusPreOrden;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Organo;
import ve.gob.dem.fasdem.bean.TipoEmpleado;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Errores;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("unchecked")
public class CargaThread  extends Thread {
 
	public interface ResultSetter {  
        public void setResult(int result);  
      }  
	
	  private ResultSetter setter;  

	  public void setResultSetter(ResultSetter setter) {  
	    this.setter = setter;  
	  }  

	
	
	
	
Mapa mapa;
	public CargaThread(Mapa mapa){
	this.mapa = mapa;	
	}

	public void run(){
		PerSiniestro ps = new PerSiniestro();
	    int result = ps.insert(mapa); 
	    // do something that calculates "result"  
	    setter.setResult(result);  

	}
}