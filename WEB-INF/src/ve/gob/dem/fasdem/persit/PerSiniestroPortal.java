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
import java.util.Date;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import ve.gob.dem.fasdem.bean.SiniestroPortal;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Constantes;
import ve.gob.dem.framework.recursos.Errores;



/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("unchecked")
public class PerSiniestroPortal {

    private Connection cnx = null;
    static protected Logger log = Logger.getLogger(PerSiniestroPortal.class);

    public PerSiniestroPortal(Connection c) {
	this.cnx = c;
    }


    private ArrayList buscar(String arg) throws SQLException, PersonalNotFoundException, NamingException  {
	ArrayList array = new ArrayList();
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "select * from view_siniestros_portal ";
	if (arg!=null){
	    sql = sql + " " + arg;
	}
	
	log.info("LISTA DE SINIESTROS SQL " + sql);
	
	
	try {
	    sentencia = cnx.createStatement();
	    
	    try{
	    rs = sentencia.executeQuery(sql);
	    } catch (Exception e) {log.info(e);}
	   
	    while (rs.next()) {
		SiniestroPortal p = new SiniestroPortal();

				
					
					
				p.setTipoTramite(rs.getString("TIPO_TRAMITE"));
				p.setIdtipoTramite(rs.getInt("ID_TIPO_TRAMITE"));
				p.setFecha(rs.getDate("FECHA_NOTIFICACION"));	
				p.setId_estatus(rs.getInt("ID_ESTATUS"));
				p.setDescripcion_estatus(rs.getString("ESTATUS"));
				p.setMonto(rs.getFloat("MONTO_PRESUPUESTADO"));
				p.setCedBeneficiario(rs.getString("CEDULA_BENEFICIARIO"));
				p.setNombreBeneficiario(rs.getString("NOMBRES_BENEFICIARIO") + " " + rs.getString("APELLIDOS_BENEFICIARIO"));
			    p.setNroPago(rs.getString("SUBCODIGO_SINIESTRO"));
				p.setNroSiniestro(rs.getString("ANIOMES_CODIGO") + " " + rs.getString("CODIGO_SINIESTRO"));
				p.setC1(rs.getString("RIF"));
				p.setS1(rs.getString("ANIOMES_CODIGO"));
				p.setS2(rs.getString("CODIGO_SINIESTRO"));
				p.setS3(rs.getString("SUBCODIGO_SINIESTRO"));
				p.setCedTitular(rs.getString("CEDULA"));
				p.setNombreTitular(rs.getString("NOMBRES") + " " + rs.getString("APELLIDOS"));
				
				p.setNTitular(rs.getString("NOMBRES"));
				p.setATitular(rs.getString("APELLIDOS"));
				p.setNBeneficiario(rs.getString("NOMBRES_BENEFICIARIO"));
				p.setABeneficiario(rs.getString("APELLIDOS_BENEFICIARIO"));
				
				p.setSexoBeneficiario(rs.getString("SEXO_BENEFICIARIO"));
				p.setParentescoBeneficiario(rs.getString("PARENTESCO"));
				p.setEdadBeneficiario(rs.getString("EDAD_BENEFICIARIO"));
				

		array.add(p);
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


    
    //public EtapaSiniestro buscarporId (String id, String pag, String clin) throws SQLException, PersonalNotFoundException, NamingException  {
    	//return (EtapaSiniestro) this.buscar("  WHERE SIPF01.CLIASG=CLPF01.CODCLI AND SIPF08.NROSIN=SIPF01.NROSIN AND SIPF08.ETPSIN=TAB_ETAPA_STRO.ETPSIN AND SIPF08.NROSIN='" + id +"' AND SIPF08.NROPAG='" + pag + "' AND CLIBEN='" + clin + "'",true).get(0);
    //}

    
    
    public ArrayList buscarListaPorFecha(String fecha, String clin) throws SQLException, PersonalNotFoundException, NamingException  {
    	return this.buscar("  WHERE TO_CHAR(FECHA_REGISTRO,'dd/mm/yyyy')='" + fecha + "' AND RIF='" + clin + "' AND ID_TIPO_TRAMITE='" + Constantes.TramiteAPS + "'  ORDER BY FECHA_REGISTRO ASC" );
    }

    public SiniestroPortal buscarNroSiniestroClinica(String cod,String subCod,String mesanio, String clin) throws SQLException, PersonalNotFoundException, NamingException  {
    	return (SiniestroPortal) this.buscar("  WHERE  RIF='" + clin + "' AND CODIGO_SINIESTRO='" + cod + "' AND 	SUBCODIGO_SINIESTRO='" + subCod + "' AND ANIOMES_CODIGO='" + mesanio + "'").get(0);
    }
    
   
    //public EtapaSiniestro buscarClavePorClinicaFechaBenef(String fecha, String clin,String benef) throws SQLException, PersonalNotFoundException, NamingException  {
    	//return (EtapaSiniestro)this.buscar("  WHERE SIPF01.CLIASG=CLPF01.CODCLI AND SIPF08.NROSIN=SIPF01.NROSIN AND  SIPF08.ETPSIN=TAB_ETAPA_STRO.ETPSIN AND FORMAT(SIPF08.FECADD,'dd/mm/yyyy')='" + fecha + "' AND CLIBEN='" + clin + "' AND TIPTRM='" + Constantes.TramiteAPS + "' AND CLIASG='" + benef + "' AND TAB_ETAPA_STRO.ETPSIN <> '" + Constantes.EtapaSiniestroAnuladoError + "' AND TAB_ETAPA_STRO.ETPSIN <> '" + Constantes.EtapaSiniestroAnuladoNoProcedente + "' AND TAB_ETAPA_STRO.ETPSIN <> '" + Constantes.EtapaSiniestroAnuladoRechazado + "'  ORDER BY SIPF08.FECADD ASC" ,false).get(0);
    //}
    
    
    
    public void ActualizarEtapa(String aniomes_codigo,String nro,String pag) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;
		  

		  String sql="UPDATE siniestro SET id_estatus=8, fecha_ingreso_clinica=? WHERE aniomes_codigo='" + aniomes_codigo + "' AND codigo_siniestro='" + nro  + "' AND subcodigo_siniestro ='" + pag + "'";
		  
		  log.info("ACTUALIZACION DE ETAPA  " + sql);
		  
		  Statement st;	
		  try{
			    sentencia = cnx.prepareStatement(sql);
			    sentencia.setDate(1,new java.sql.Date(new Date().getTime()));   
			    sentencia.executeUpdate();
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }

	 }
    
    
    public SiniestroPortal buscarClavePorClinicaFechaBenef(String fecha,String clin,int cedula) throws SQLException, PersonalNotFoundException, NamingException  {
    	return (SiniestroPortal) this.buscar("  WHERE ID_ESTATUS <> '" + Constantes.EtapaSiniestroAnuladoRechazado + "'  AND ID_ESTATUS <> '" + Constantes.EtapaSiniestroAnuladoNoProcedente + "'  AND ID_ESTATUS <> '" + Constantes.EtapaSiniestroAnuladoError + "'  AND ID_TIPO_TRAMITE='" + Constantes.TramiteAPS + "' AND RIF='" + clin + "' AND TO_CHAR(FECHA_REGISTRO,'DD/MM/YYYY')='" + fecha + "' AND CEDULA_BENEFICIARIO='" + cedula + "'").get(0);
    }

    
    

    
   
}
