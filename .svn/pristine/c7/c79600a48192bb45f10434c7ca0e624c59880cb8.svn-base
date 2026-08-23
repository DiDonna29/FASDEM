/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.persit.pagos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import ve.gob.dem.fasdem.bean.Factura;
import ve.gob.dem.fasdem.bean.TipoEmpleado;
import ve.gob.dem.fasdem.bean.TipoProveedor;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Errores;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("unchecked")
public class PerTipoProveedor {

	
    private Connection cnx = null;
    static protected Logger log = Logger.getLogger(PerTipoProveedor.class);

    public PerTipoProveedor(Connection c) {
	this.cnx = c;
    }


    private ArrayList buscar(String arg) throws SQLException, PersonalNotFoundException, NamingException  {
	ArrayList array = new ArrayList();
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "SELECT tipo_proveedor.id_tipo_proveedor,descripcion,id_tipo_pre_orden_pago FROM tipo_proveedor LEFT JOIN ( SELECT id_tipo_pre_orden_pago,id_tipo_proveedor FROM tipo_pre_orden_pago_tipo_proveedor re) relacion ON tipo_proveedor.id_tipo_proveedor = relacion.id_tipo_proveedor ";
	if (arg!=null){
	    sql = sql + " " + arg;
	    log.info("sql " + sql);
	}
	
	
	try {
	    sentencia = cnx.createStatement();
	    rs = sentencia.executeQuery(sql);
	    while (rs.next()) {
		TipoProveedor e = new TipoProveedor();
		e.setId(rs.getInt("id_tipo_proveedor"));
		e.setDescripcion(rs.getString("descripcion"));
		e.setId_tipo_preOrden(rs.getInt("id_tipo_pre_orden_pago"));
		

		array.add(e);
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
    
    


    public TipoProveedor buscarPorId(int id) throws SQLException, PersonalNotFoundException, NamingException  {
	       return (TipoProveedor) this.buscar("  WHERE tipo_proveedor.id_tipo_proveedor=" + id ).get(0);
    }
    
    public ArrayList buscarLista() throws SQLException, PersonalNotFoundException, NamingException  {
    	   return (ArrayList) this.buscar("WHERE  tipo_proveedor.ID_TIPO_PROVEEDOR!=2 and tipo_proveedor.ID_TIPO_PROVEEDOR!=17 and tipo_proveedor.ID_TIPO_PROVEEDOR!=18 and tipo_proveedor.ID_TIPO_PROVEEDOR!=22 and tipo_proveedor.ID_TIPO_PROVEEDOR!=32 and tipo_proveedor.ID_TIPO_PROVEEDOR!=0 ");
    }
    
    
    public ArrayList buscarRetencion(int mes, int id, int periodo) throws SQLException, PersonalNotFoundException, NamingException  {
    	ArrayList array = new ArrayList();
    	ResultSet rs = null;
    	Statement sentencia = null;
    	String sql = "select DISTINCT ON (facturas.pre_orden) siniestro.aniomes_codigo||'-'||siniestro.codigo_siniestro as siniestro, "
    			+ "facturas.fecha_factura,facturas.pre_orden from facturas facturas,siniestro siniestro,pre_orden_pago pre_orden_pago "
    			+ " where  facturas.pre_orden=pre_orden_pago.pre_orden and  to_char(facturas.fecha_factura,'MM')::int="+mes+" "
    			+ "and facturas.id_siniestro=siniestro.id_siniestro and  is_factura is true and facturas.anio_siniestro="+periodo+" "
    			+ "and siniestro.anio_siniestro="+periodo+" and pre_orden_pago.id_estatus = 4 and pre_orden_pago.id_proveedor="+ id+" ORDER BY facturas.pre_orden";

    	
    	log.info("sql " + sql);
    	
    	try {
    	    sentencia = cnx.createStatement();
    	    rs = sentencia.executeQuery(sql);
    	    while (rs.next()) {
    		Factura e = new Factura();
    		e.setPreOrden(rs.getString("pre_orden"));
    		e.setFechaFactura(rs.getDate("fecha_factura"));
    		e.setNumeroFactura("nro_factura");

    		array.add(e);
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

   
}
