/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.persit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import ve.gob.dem.fasdem.bean.UsuarioEstadistica;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Errores;


@SuppressWarnings("unchecked")
public class PerUsuarioEstadistica {

	
    private Connection cnx = null;
    static protected Logger log = Logger.getLogger(PerUsuarioEstadistica.class);

    public PerUsuarioEstadistica(Connection c) {
	this.cnx = c;
    }


    private ArrayList buscarEstadistica(String arg) throws SQLException, PersonalNotFoundException, NamingException  {
    	ArrayList array = new ArrayList();
    	ResultSet rs = null;
    	Statement sentencia = null;
    	String sql = "SELECT A.DATOS_ANALISTA,A.LOGIN,COUNT(*) AS CANTIDAD,SUM((extract(day from (A.FECHA_PROCESADO-A.FECHA_REGISTRO))*24*60)+(extract(min from (A.FECHA_PROCESADO-A.FECHA_REGISTRO)))) AS MINUTOS FROM solicitudes_en_linea A ";
    	if (arg!=null){
    	    sql = sql + " " + arg;
    	}
    	
    	
    	log.info("ESTADISTICA SQL " + sql);

    	try {
    	    
    		
    		sentencia = cnx.createStatement();
    	    rs = sentencia.executeQuery(sql);
    	    while (rs.next()) {
    		UsuarioEstadistica c = new UsuarioEstadistica();
    		c.setLogin(rs.getString("LOGIN"));
    		c.setNombre(rs.getString("DATOS_ANALISTA"));
    		c.setCantidadAtendidos(rs.getString("CANTIDAD"));
    		c.setTiempoPromedio(rs.getString("MINUTOS"));
    		array.add(c);
    	    
    	    
    	    
    	    }
    	    if (array.size() == 0) {
    		throw new PersonalNotFoundException(new Errores(Errores.ERR_ELEMENTO_NOFOUND, Errores.buscarDescripcion(Errores.ERR_ELEMENTO_NOFOUND), "PerElemento", "buscar(argumento)"));
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


 
    public ArrayList buscarListaPorTipoFechaEstadistica(String estatus,String tipo,String fechaEstadistica,String fechaEstadisticaHasta) throws SQLException, PersonalNotFoundException, NamingException  {
    	 //return this.buscarEstadistica("WHERE A.ID_ESTATUS=" + estatus + " AND TIPO_SINIESTRO=" + tipo + " AND TO_CHAR(FECHA_PROCESADO,'DD/MM/YYYY')='" + fechaEstadistica + "' GROUP BY A.DATOS_ANALISTA,A.LOGIN ORDER BY CANTIDAD DESC, MINUTOS ASC" );
    	 return this.buscarEstadistica("WHERE A.ID_ESTATUS=" + estatus + " AND TIPO_SINIESTRO=" + tipo + " AND TO_DATE(TO_CHAR(FECHA_TOMADO,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE('" + fechaEstadistica + "','DD/MM/YYYY') AND TO_DATE(TO_CHAR(FECHA_TOMADO,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE('" + fechaEstadisticaHasta + "','DD/MM/YYYY') GROUP BY A.DATOS_ANALISTA,A.LOGIN ORDER BY CANTIDAD DESC, MINUTOS ASC" );
    }
    
    
    
   
}
