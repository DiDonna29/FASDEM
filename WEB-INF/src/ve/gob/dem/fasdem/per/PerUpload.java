package ve.gob.dem.fasdem.per;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;


import ve.gob.dem.fasdem.bean.Upload;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.seguridad.bean.Usuario;
import ve.gob.dem.framework.seguridad.exp.ExpUsuario;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerUpload extends PerGeneric {
	static protected Logger log = Logger.getLogger(PerUpload.class);
	private static final String T_TABLA = "Upload.";
	private SqlMapClient cnx = null;

	public PerUpload() {
		this.cnx = FasdemSqlConfig.getSqlMapInstance();
	}

	public Upload search(HashMap mapa) throws PersonalNotFoundException, SQLException {
		Upload obj;
		obj = (Upload) cnx.queryForObject(T_TABLA.concat(KEY_SEARCH), mapa);
		if (obj != null)
			return obj;
		else
			throw new PersonalNotFoundException(" ");
	}

	public int insert(Upload upload) throws SQLException {
		return (Integer) cnx.insert(T_TABLA.concat(KEY_INSERT), upload);
	}

	public void delete(HashMap mapa) throws SQLException {
		cnx.delete(T_TABLA.concat("delete"), mapa);
	}

	public List listBySiniestro(HashMap mapa) throws PersonalNotFoundException, SQLException {
		List list = new ArrayList();
		list = cnx.queryForList(T_TABLA.concat("listBySiniestro"), mapa);
		if (list.size() != 0){
			
			Upload up;
			for(int i = 0; i<list.size();i++){
				up = (Upload)list.get(i);
				try {
					Usuario usu = ExpUsuario.buscarDatosP(up.getIdUsuario());
					up.setDesUsuario(usu.getNombre() + " " + usu.getApellido());
				} catch (Exception e) {
					log.error("error", e);
					up.setDesUsuario("No disponible");
				} 
			}
			
			return list;
		}else{
			throw new PersonalNotFoundException(" ");}
	}
}