/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.exp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import org.jfree.util.Log;

import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.persit.PerClinica;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFoundException;


/**
 * @author marcenrl
 * 
 */

public class ExpClinica {

	
    public static Clinica validar(String rif) throws NamingException, SQLException, PersonalNotFoundException {
	Connection c = null;
	try {
	    c = Conexion.getConexion();
	    
	    PerClinica p = new PerClinica(c);
	    return p.validar(rif);
	} finally {
	    Conexion.closeConexion(c);
	}
    }

    public static Clinica BuscarPorRif(String rif) throws NamingException, SQLException, PersonalNotFoundException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerClinica p = new PerClinica(c);
    	    return p.buscarporRif(rif);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
        }
   
    public static boolean ExisteRif(String rif) throws NamingException, SQLException, PersonalNotFoundException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerClinica p = new PerClinica(c);
    	    return p.ExisteRif(rif);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
        }

    
    

    public static ArrayList BuscarPorRifParcial(String rif) throws NamingException, SQLException, PersonalNotFoundException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerClinica p = new PerClinica(c);
    	    return p.buscarporRifParcial(rif);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
        }

    public static ArrayList BuscarPorRifCuenta(String rif) throws NamingException, SQLException, PersonalNotFoundException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerClinica p = new PerClinica(c);
    	    return p.buscarporRifCuenta(rif);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
        }

    public static ArrayList BuscarPorNombreCuenta(String nombre) throws NamingException, SQLException, PersonalNotFoundException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerClinica p = new PerClinica(c);
    	    return p.buscarporNombreCuenta(nombre);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
        }
    
    
    public static int crearProveedor(Clinica proveedor) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException, IOException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerClinica p = new PerClinica(c);
    	    return p.crearProveedor(proveedor);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
        }

    public static void crearProveedorTipoProv(int id_prov,String [] listTipoProv) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException, IOException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerClinica p = new PerClinica(c);
    	    p.crearProveedorTipoProv(id_prov,listTipoProv);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
        }

    public static void crearTipoTramitePortal(int id_proveedor,String [] listTipoProv) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException, IOException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerClinica p = new PerClinica(c);
    	    p.crearTipoTramitePortal(id_proveedor,listTipoProv);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
        }
    
    public static void modificarTipoTramitePortal(int id_proveedor,String [] listTipoProv) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException, IOException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerClinica p = new PerClinica(c);
    	    p.modificarTipoTramitePortal(id_proveedor,listTipoProv);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
        }
  
    public static void modificarTipoTramitePortalTodos(int id_proveedor) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException, IOException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerClinica p = new PerClinica(c);
    	    p.modificarTipoTramitePortaltodos(id_proveedor);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
        }
   
    
    public static void crearProveedorTipoProvCre(int id_prov,String [] listTipoProv) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException, IOException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerClinica p = new PerClinica(c);
    	    p.crearProveedorTipoProvCre(id_prov,listTipoProv);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
        }

    
    public static void modificarProveedor(Clinica prov) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException, IOException {
    	Connection c = null;
    	try { 
    	    c = Conexion.getConexion();
    	    PerClinica p = new PerClinica(c);
    	    p.modificarProveedor(prov);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
        }

    
    public static ArrayList buscarProvTipoProvID(int id_prov) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException, IOException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerClinica p = new PerClinica(c);
    	    return p.buscarProvTipoProvID(id_prov);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
        }

    public static ArrayList buscarProvTipoServPortal(int id_prov) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException, IOException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerClinica p = new PerClinica(c);
    	    return p.buscarProvTipoServPortal(id_prov);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
        }

    
    
    
    public static ArrayList BuscarPorNombreParcial(String nombre) throws NamingException, SQLException, PersonalNotFoundException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerClinica p = new PerClinica(c);
    	    return p.buscarporNombreParcial(nombre);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
        }

    
    public static Clinica BuscarPorid(int id) throws NamingException, SQLException, PersonalNotFoundException {
    	Connection c = null;
    	try {
    	    c = Conexion.getConexion();
    	    PerClinica p = new PerClinica(c);
    	    return p.buscarporId(id);
    	} finally {
    	    Conexion.closeConexion(c);
    	}
        }
    
    
    
   
    
    
}
