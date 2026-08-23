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

import org.apache.log4j.Logger;
import ve.gob.dem.fasdem.bean.SiniestroBandeja;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Errores;


/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("unchecked")
public class PerSiniestroBandeja {

    private Connection cnx = null;
    static protected Logger log = Logger.getLogger(PerSiniestroBandeja.class);

    public PerSiniestroBandeja(Connection c) {
	this.cnx = c;
    }

    private ArrayList buscar(String arg) throws SQLException, PersonalNotFoundException  {
    	ArrayList array = new ArrayList();
    	ResultSet rs = null;
    	Statement sentencia = null;
    	String sql = 
    			"SELECT " +
		    			"ID, " + 
		    			"CRIF_BENEFICIARIO, " + 
		    			"CRIF_TITULAR, " + 
		    			"CED_BENEFICIARIO, " + 
		    			"CED_TITULAR, " + 
		    			"BENEFICIARIO, " + 
		    			"TITULAR, " + 
		    			"RIF_CLINICA, " + 
		    			"CLINICA, " + 
		    			"FECHA_REGISTRO, " + 
		    			"ID_ESTATUS, " + 
		    			"TELEFONO, " + 
		    			"TIPO_SINIESTRO, " + 
		    			"MONTO, " + 
		    			"CAUSA_INGRESO, " + 
		    			"ID_ESTATUS, " + 
		    			"LOGIN, " + 
		    			"DATOS_ANALISTA, " + 
		    			"FECHA_TOMADO, " + 
		    			"FECHA_PROCESADO, " + 
		    			"OBSERVACION " +
    			"FROM " +
    				"solicitudes_en_linea ";
    	if (arg!=null){
    	    sql = sql + " " + arg;
    	}
    	log.info("sql " + sql);
    	try {
    	    sentencia = cnx.createStatement();
    	    rs = sentencia.executeQuery(sql);
    	    while (rs.next()) {
    		SiniestroBandeja sin = new SiniestroBandeja();
    		
    		
    		sin.setId(rs.getInt("ID"));
    		sin.setCrifBeneficiario(rs.getString("CRIF_BENEFICIARIO"));
    		sin.setCrifTitular(rs.getString("CRIF_TITULAR"));
    		sin.setCedBeneficiario(rs.getInt("CED_BENEFICIARIO"));
    		sin.setCedTitular(rs.getInt("CED_TITULAR"));
    		sin.setBeneficiario(rs.getString("BENEFICIARIO"));
    		sin.setTitular(rs.getString("TITULAR"));
    		sin.setRifClinica(rs.getString("RIF_CLINICA"));
    		sin.setClinica(rs.getString("CLINICA"));
    		sin.setFechaIngreso(rs.getDate("FECHA_REGISTRO"));
    		sin.setHoraIngreso(rs.getTime("FECHA_REGISTRO"));
    		sin.setId_estatus(rs.getInt("ID_ESTATUS"));
    		sin.setTlf(rs.getString("TELEFONO"));
    	
    		
    		if(rs.getInt("TIPO_SINIESTRO")==1){
    			sin.setMonto(rs.getString("MONTO"));
    			sin.setCausaIngreso(rs.getString("CAUSA_INGRESO"));
    		}
    		
    		
    	
    		
    		if(rs.getInt("ID_ESTATUS")==0){
    			
    			sin.setLoginProcesa(rs.getString("LOGIN"));
    			sin.setDatosProcesa(rs.getString("DATOS_ANALISTA"));
    			sin.setFechaTomado(rs.getDate("FECHA_TOMADO"));
    			sin.setHoraTomado(rs.getTime("FECHA_TOMADO"));
    		}
    		
    		
    		if(rs.getInt("ID_ESTATUS")==2){
    			
    			sin.setLoginProcesa(rs.getString("LOGIN"));
    			sin.setDatosProcesa(rs.getString("DATOS_ANALISTA"));
    			sin.setFechaProcesa(rs.getDate("FECHA_PROCESADO"));
    			sin.setHoraProcesa(rs.getTime("FECHA_PROCESADO"));
    			sin.setObservacion(rs.getString("OBSERVACION"));
    			sin.setFechaTomado(rs.getDate("FECHA_TOMADO"));
    			sin.setHoraTomado(rs.getTime("FECHA_TOMADO"));
    		}
    		
    		array.add(sin);
    	    
    	    
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
       /////////////
    private ArrayList buscarMedicosSiniestro(String arg) throws SQLException, PersonalNotFoundException  {
    	ArrayList array = new ArrayList();
    	ResultSet rs = null;
    	Statement sentencia = null;
    	String sql = " SELECT a.*, s.observacion,s.anio_siniestro,e.descripcion || '-' || o.descripcion||'-'||p.descripcion||'-'||t.descripcion as causa_ingreso," +
    			" tt.descripcion as tipo_tramite,  s.aniomes_codigo|| '-' ||s.codigo_siniestro || '-' || s.subcodigo_siniestro as numero" +
    			" FROM solicitudes_medicos a, siniestro s, patologia_organo_tratamiento pot, organo o, patologias p, especialidad e, " +
    			" tratamiento t, tipo_tramite tt " +
    			" where (a.id_siniestro=s.id_siniestro " +
    			" and a.anio_siniestro=s.anio_siniestro" +
    			" and pot.id_pat_org_trat_esp = s.id_patologia_organo_tratamiento" +
    			" and o.id_organo = pot.id_organo " +
    			" AND p.id_patologia = pot.id_patologia " +
    			" AND e.id_especialidad = pot.id_especialidad " +
    			" and tt.id_tipo_tramite = s.id_tipo_tramite " + 
    			" AND t.id_tratamiento = pot.id_tratamiento ";
    	if (arg!=null){
    	    sql = sql + " " + arg;
    	}
    	

    	
    	try {
    	    sentencia = cnx.createStatement();
    	    rs = sentencia.executeQuery(sql);
    	    while (rs.next()) {
    		SiniestroBandeja sin = new SiniestroBandeja();
    		
    		
    		sin.setId(rs.getInt("ID"));
    		sin.setId_siniestro(rs.getInt("ID_siniestro"));
    		sin.setAnio_siniestro(rs.getInt("ANIO_SINIESTRO"));
    		sin.setFechaIngreso(rs.getDate("FECHA_REGISTRO"));
    		sin.setHoraIngreso(rs.getTime("FECHA_REGISTRO"));
    		sin.setId_estatus(rs.getInt("ID_ESTATUS"));
    		sin.setNumero(rs.getString("numero"));
    		sin.setIdCausaIngreso(rs.getString("causa_ingreso"));
    		sin.setTipoTramite(rs.getString("tipo_tramite"));
    		sin.setObservacion(rs.getString("observacion"));
    		
    	
    		
    		
    	
    		
    		if(rs.getInt("ID_ESTATUS")==0){
    			
    			sin.setLoginProcesa(rs.getString("LOGIN"));
    			sin.setDatosProcesa(rs.getString("DATOS_ANALISTA"));
    			sin.setFechaTomado(rs.getDate("FECHA_TOMADO"));
    			sin.setHoraTomado(rs.getTime("FECHA_TOMADO"));
    		}
    		
    		
    		if(rs.getInt("ID_ESTATUS")==2){
    			
    			sin.setLoginProcesa(rs.getString("LOGIN"));
    			sin.setDatosProcesa(rs.getString("DATOS_ANALISTA"));
    			sin.setFechaProcesa(rs.getDate("FECHA_PROCESADO"));
    			sin.setHoraProcesa(rs.getTime("FECHA_PROCESADO"));
    			sin.setFechaTomado(rs.getDate("FECHA_TOMADO"));
    			sin.setHoraTomado(rs.getTime("FECHA_TOMADO"));
    		}
    		
    		array.add(sin);
    	    
    	    
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
    
   
    /////////
    private ArrayList buscarMedicos(String arg) throws SQLException, PersonalNotFoundException  {
	ArrayList array = new ArrayList();
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "SELECT * FROM solicitudes_medicos ";
	if (arg!=null){
	    sql = sql + " " + arg;
	}
	
	log.info("bandeja de siniestros" + sql ) ;
	
	try {
	    sentencia = cnx.createStatement();
	    rs = sentencia.executeQuery(sql);
	    while (rs.next()) {
		SiniestroBandeja sin = new SiniestroBandeja();
		
		
		sin.setId_siniestro(rs.getInt("ID_SINIESTRO"));
		sin.setId(rs.getInt("ID"));
		sin.setAnio_siniestro(rs.getInt("ANIO_SINIESTRO"));
		sin.setFechaIngreso(rs.getDate("FECHA_REGISTRO"));
		sin.setHoraIngreso(rs.getTime("FECHA_REGISTRO"));
		sin.setId_estatus(rs.getInt("ID_ESTATUS"));
	
		
		
	
		
		if(rs.getInt("ID_ESTATUS")==0){
			
			sin.setLoginProcesa(rs.getString("LOGIN"));
			sin.setDatosProcesa(rs.getString("DATOS_ANALISTA"));
			sin.setFechaTomado(rs.getDate("FECHA_TOMADO"));
			sin.setHoraTomado(rs.getTime("FECHA_TOMADO"));
		}
		
		
		if(rs.getInt("ID_ESTATUS")==2){
			
			sin.setLoginProcesa(rs.getString("LOGIN"));
			sin.setDatosProcesa(rs.getString("DATOS_ANALISTA"));
			sin.setFechaProcesa(rs.getDate("FECHA_PROCESADO"));
			sin.setHoraProcesa(rs.getTime("FECHA_PROCESADO"));
			sin.setFechaTomado(rs.getDate("FECHA_TOMADO"));
			sin.setHoraTomado(rs.getTime("FECHA_TOMADO"));
		}
		
		array.add(sin);
	    
	    
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
    
    
    private String buscarCantidad(String arg) throws SQLException, PersonalNotFoundException  {
    	String cantidad = "0";
    	ResultSet rs = null;
    	Statement sentencia = null;
    	String sql = 
    	" SELECT COUNT(id) AS CANTIDAD from solicitudes_en_linea ";
    	if (arg!=null){
    	    sql = sql + " " + arg;
    	}
    	
    	
    	
    	try {
    	    sentencia = cnx.createStatement();
    	    rs = sentencia.executeQuery(sql);
    	    while (rs.next()) {
    		
    	    	cantidad=rs.getString("CANTIDAD");
    		
    	    }
    	  
    	    
    	    return cantidad;
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
    
    
    
   
    private String buscarCantidadMedico(String arg) throws SQLException, PersonalNotFoundException  {
    	String cantidad = "0";
    	ResultSet rs = null;
    	Statement sentencia = null;
    	String sql = 
        	" SELECT COUNT(a.id) AS CANTIDAD " +
    		" FROM solicitudes_medicos a, siniestro s, patologia_organo_tratamiento pot, organo o, patologias p, especialidad e, " +
    		" tratamiento t, tipo_tramite tt " +
    		" where (a.id_siniestro=s.id_siniestro " +
    		" and a.anio_siniestro=s.anio_siniestro" +
    		" and pot.id_pat_org_trat_esp = s.id_patologia_organo_tratamiento" +
    		" and o.id_organo = pot.id_organo " +
    		" AND p.id_patologia = pot.id_patologia " +
    		" AND e.id_especialidad = pot.id_especialidad " +
    		" and tt.id_tipo_tramite = s.id_tipo_tramite " + 
    		" AND t.id_tratamiento = pot.id_tratamiento ";
    	if (arg!=null){
    	    sql = sql + " " + arg;
    	    log.info("este es cantidad medico"+sql);
    	}
    	
    	
    	
    	try {
    	    sentencia = cnx.createStatement();
    	    rs = sentencia.executeQuery(sql);
    	    while (rs.next()) {
    		
    	    	cantidad=rs.getString("CANTIDAD");
    		
    	    }
    	  
    	    
    	    return cantidad;
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
    
    private String buscarCantidadMed(String arg) throws SQLException, PersonalNotFoundException  {
    	String cantidad = "0";
    	ResultSet rs = null;
    	Statement sentencia = null;
    	String sql = 
        	" SELECT COUNT(a.id) AS CANTIDAD FROM solicitudes_medicos a ";
    	if (arg!=null){
    	    sql = sql + " " + arg;
    	    log.info("este es cantidad medico"+sql);
    	}
    	
    	
    	
    	try {
    	    sentencia = cnx.createStatement();
    	    rs = sentencia.executeQuery(sql);
    	    while (rs.next()) {
    		
    	    	cantidad=rs.getString("CANTIDAD");
    		
    	    }
    	  
    	    
    	    return cantidad;
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
    
    
   
    
    
    
    
    public String BuscarCantidadEstatusTipo(String estatus, String tipo) throws SQLException, PersonalNotFoundException  {
    	return this.buscarCantidad(" WHERE ID_ESTATUS= " + estatus + " AND TIPO_SINIESTRO=" + tipo );
    } 
    public String BuscarCantidadEstatusTipoMedico(String estatus,String fecha) throws SQLException, PersonalNotFoundException  {
    	return this.buscarCantidadMedico(" and a.id_estatus="+ estatus+" and to_char(a.FECHA_REGISTRO,'yyyy-MM-dd')='"+fecha+"' and s.anio_siniestro='"+fecha.substring(0,4)+"' and a.anio_siniestro='"+fecha.substring(0,4)+"'   and monto_amparado > 3000 "+
    			"and not (a.id in (select distinct sm.id from nota_tecnica nt, solicitudes_medicos sm where  " +
    	    			"nt.anio_siniestro='"+fecha.substring(0,4)+"' and sm.anio_siniestro='"+fecha.substring(0,4)+"' and sm.anio_siniestro=nt.anio_siniestro " +
    	    					"and nt.id_siniestro=sm.id_siniestro and nt.is_medica=true and nt.id_siniestro=sm.id_siniestro ))"+
    	    					")  and (s.id_tipo_tramite = 1 or (s.id_tipo_tramite= 2 and (s.id_estatus=8 or s.id_estatus=9))) ");
    }
    
  
    
    
    public String BuscarCantidadEstatusTipoFecha(String estatus, String tipo,String fecha_hoy) throws SQLException, PersonalNotFoundException  {
    	return this.buscarCantidad(" WHERE ID_ESTATUS= " + estatus + " AND TIPO_SINIESTRO=" + tipo  + " AND TO_CHAR(FECHA_PROCESADO,'DD/MM/YYYY')='" + fecha_hoy + "'" );
    } 
    
    public String BuscarCantidadEstatusTipoFechaMedico(String estatus, String fecha) throws SQLException, PersonalNotFoundException  {
    	return this.buscarCantidadMed(" where a.id_estatus="+ estatus+" and to_char(a.FECHA_PROCESADO,'yyyy-MM-dd')='"+fecha+"' and a.anio_siniestro='"+fecha.substring(0,4)+"'");
}
    
    
    
    
    
    

    
    public void InsertarSiniestroBandeja(SiniestroBandeja sin) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;
		  

		  String sql="INSERT INTO solicitudes_en_linea (CRIF_BENEFICIARIO,CED_BENEFICIARIO,CRIF_TITULAR,CED_TITULAR,RIF_CLINICA,CLINICA,MONTO,CAUSA_INGRESO,TIPO_SINIESTRO,BENEFICIARIO,TITULAR,TELEFONO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		 
		  try{
			    sentencia = cnx.prepareStatement(sql);
			   
			   
			    
			    sentencia.setString(1,sin.getCrifBeneficiario()); 
			    sentencia.setInt(2,sin.getCedBeneficiario());
			    sentencia.setString(3,sin.getCrifTitular());
			    sentencia.setInt(4,sin.getCedTitular());
			    sentencia.setString(5,sin.getRifClinica());
			    sentencia.setString(6,sin.getClinica());
			    sentencia.setString(7,sin.getMonto());
			    sentencia.setString(8,sin.getCausaIngreso());
			    sentencia.setInt(9,sin.getTipo_siniestro());
			    sentencia.setString(10,sin.getBeneficiario());
			    sentencia.setString(11,sin.getTitular());
			    sentencia.setString(12,sin.getTlf());
			    
			    sentencia.executeUpdate();
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }
	
		
	
	 }
    
    public void InsertarSiniestroBandejaMedico(SiniestroBandeja sin) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;
		  

		  String sql="INSERT INTO solicitudes_medicos (id_siniestro,id_estatus,anio_siniestro) VALUES (?,?,?)";
		 
		  try{
			    sentencia = cnx.prepareStatement(sql);
			   
			   
			    
			    sentencia.setInt(1,sin.getId_siniestro()); 
			    
			    sentencia.setInt(2,sin.getId_estatus());
			   
			    sentencia.setInt(3,sin.getAnio_siniestro());
			    
			    
			    sentencia.executeUpdate();
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }
	
		
	
	 }
    
    
    public void CambiarEstatusSiniestro(int id_siniestro, int estatus, String Login,String Datos_analista,String obs) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;
		  

		  String sql="UPDATE solicitudes_en_linea SET ID_ESTATUS=?, LOGIN=?, DATOS_ANALISTA=?,FECHA_PROCESADO=now(),OBSERVACION=? WHERE ID=?";
		 
		  try{
			    sentencia = cnx.prepareStatement(sql);

			    sentencia.setInt(1,estatus); 
			    sentencia.setString(2,Login); 
			    sentencia.setString(3,Datos_analista); 
			    sentencia.setString(4,obs); 
			    sentencia.setInt(5,id_siniestro);
			    sentencia.executeUpdate();
			    
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }
	
		
	
	 }
    public void CambiarEstatusSiniestroMedico(int id_siniestro,int estatus, String Login,String Datos_analista) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;
		  

		  String sql="UPDATE solicitudes_medicos SET ID_ESTATUS=?, LOGIN=?, DATOS_ANALISTA=?,FECHA_PROCESADO=now() WHERE ID=?";
		 
		  try{
			    sentencia = cnx.prepareStatement(sql);

			    sentencia.setInt(1,estatus); 
			    sentencia.setString(2,Login); 
			    sentencia.setString(3,Datos_analista); 
			    sentencia.setInt(4,id_siniestro);
			   
			    sentencia.executeUpdate();
			    
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }
	
		
	
	 }
    
    
    
    public void TomarSiniestro(int id_siniestro, int estatus, String Login,String Datos_analista) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;
		  

		  String sql="UPDATE solicitudes_en_linea SET ID_ESTATUS=?, LOGIN=?, DATOS_ANALISTA=?,FECHA_TOMADO=now() WHERE ID=?";
		 
		  try{
			    sentencia = cnx.prepareStatement(sql);

			    sentencia.setInt(1,estatus); 
			    sentencia.setString(2,Login); 
			    sentencia.setString(3,Datos_analista); 
			    sentencia.setInt(4,id_siniestro);
			    sentencia.executeUpdate();
			    
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }
	
		
	
	 }
    public void TomarSiniestroMedico(int id_siniestro, int estatus, String Login,String Datos_analista) throws SQLException, ClassNotFoundException, IOException{
		  PreparedStatement sentencia = null;
		  

		  String sql="UPDATE solicitudes_medicos SET ID_ESTATUS=?, LOGIN=?, DATOS_ANALISTA=?,FECHA_TOMADO=now() WHERE ID=?";
		 
		  try{
			    sentencia = cnx.prepareStatement(sql);

			    sentencia.setInt(1,estatus); 
			    sentencia.setString(2,Login); 
			    sentencia.setString(3,Datos_analista); 
			    sentencia.setInt(4,id_siniestro);
			    sentencia.executeUpdate();
			    
		  }
		  finally {
	   
			 if (sentencia != null) sentencia.close();
		 
		 }
	
		
	
	 }
    
    
    


    public ArrayList BuscarListaEstatusTipo(String estatus, String tipo) throws SQLException, PersonalNotFoundException  {
    	return this.buscar(" WHERE ID_ESTATUS= " + estatus + " AND TIPO_SINIESTRO=" + tipo +  " ORDER BY FECHA_REGISTRO ASC ");
    }
    
    
    public ArrayList BuscarListaPendientesyAtencion(String tipo, String anio) throws SQLException, PersonalNotFoundException  {
    	return this.buscar(" WHERE ID_ESTATUS!=2 AND TIPO_SINIESTRO=" + tipo + " AND anio_registro = "+anio+" ORDER BY FECHA_REGISTRO ASC ");
    	
    }
    public ArrayList BuscarListaPendientesyAtencionMedico(String fecha) throws SQLException, PersonalNotFoundException  {
    	return this.buscarMedicosSiniestro(" and a.id_estatus!=2 and to_char(a.FECHA_REGISTRO,'yyyy-MM-dd')='"+fecha+"' and s.anio_siniestro='"+fecha.substring(0,4)+"'  and monto_amparado > 3000 and not (a.id in (select distinct sm.id from nota_tecnica nt, solicitudes_medicos sm where  " +
    			"nt.anio_siniestro='"+fecha.substring(0,4)+"' and sm.anio_siniestro='"+fecha.substring(0,4)+"' and sm.anio_siniestro=nt.anio_siniestro " +
    					"and nt.id_siniestro=sm.id_siniestro and nt.is_medica=true and nt.id_siniestro=sm.id_siniestro )) ) and (s.id_tipo_tramite = 1 or (s.id_tipo_tramite= 2 and (s.id_estatus=8 or s.id_estatus=9))) ORDER BY FECHA_REGISTRO ASC ");
    }
    
    
    public ArrayList BuscarListaAtendidosyAtencion(String tipo,String analista,String fecha,String fecha2) throws SQLException, PersonalNotFoundException  {
    	return this.buscar(" WHERE LOGIN='" + analista + "' AND ID_ESTATUS!=1 AND TIPO_SINIESTRO=" + tipo +  " AND ((TO_DATE(TO_CHAR(FECHA_PROCESADO,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE('" + fecha + "','DD/MM/YYYY') AND TO_DATE(TO_CHAR(FECHA_PROCESADO,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE('" + fecha2 + "','DD/MM/YYYY')) OR FECHA_PROCESADO IS NULL ) ORDER BY FECHA_PROCESADO ASC ");
    }
    
    public ArrayList BuscarListaEstatusTipoClinicaFecha(String estatus, String tipo, String clinica, String fecha_hoy) throws SQLException, PersonalNotFoundException  {
    	return this.buscar(" WHERE ID_ESTATUS= " + estatus + " AND TIPO_SINIESTRO=" + tipo + " AND  RIF_CLINICA='"  + clinica + "' AND TO_CHAR(FECHA_REGISTRO,'DD/MM/YYYY')='" + fecha_hoy + "' ORDER BY FECHA_REGISTRO ASC" );
    }
    
    public ArrayList BuscarListaCedula(String cedula) throws SQLException, PersonalNotFoundException  {
        return this.buscar(" WHERE (CED_BENEFICIARIO=" + cedula + " OR CED_TITULAR=" + cedula + ") ORDER BY FECHA_REGISTRO ASC" );
    }
    
    public SiniestroBandeja BuscarporId(int id) throws SQLException, PersonalNotFoundException  {
        return (SiniestroBandeja)this.buscar(" WHERE ID=" + id ).get(0);
    }
    public SiniestroBandeja BuscarporIdMedico(int id,String fecha) throws SQLException, PersonalNotFoundException  {
        return (SiniestroBandeja)this.buscarMedicos(" WHERE ID=" + id +" and anio_siniestro='"+fecha.substring(0,4)+"'" ).get(0);
    }
   
}
