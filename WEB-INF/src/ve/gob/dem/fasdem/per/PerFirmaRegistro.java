package ve.gob.dem.fasdem.per;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ve.gob.dem.framework.recursos.Utilidad;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.jfree.util.Log;

import ve.gob.dem.fasdem.action.upload.formbeam.UploadForm;
import ve.gob.dem.fasdem.bean.FirmaRegistro;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.bean.Upload;
import ve.gob.dem.framework.cnx.FasdemSqlConfig;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PerFirmaRegistro extends PerGeneric {
	private static final String T_TABLA = "FirmaRegistro.";
	private SqlMapClient cnx = null;

	public PerFirmaRegistro() {
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

	public int insert(byte[] pdfasbytes,Mapa mapa, String token) throws SQLException {

		FirmaRegistro up = new FirmaRegistro();

		if (pdfasbytes != null && pdfasbytes.length != 0) {
			up.setContentType("application/pdf");
			up.setFileName(token);
			up.setDescripcion("prueba de archivo");
			up.setIdSiniestro(mapa.getId());
			up.setData(pdfasbytes);
			up.setUploadLenght(Integer.valueOf(pdfasbytes.length));
			up.setIdUsuario(mapa.getIdUsuario());
			up.setIdNotaMedica(mapa.getIdNotaMedica());
			up.setAnioSiniestro(mapa.getAnioSiniestro());
			try{
				return (Integer) cnx.insert(T_TABLA.concat(KEY_INSERT), up);
			}catch (com.ibatis.common.jdbc.exception.NestedSQLException e){
				return 0;			
			}

		}else{
			return 0;
		}

	}


}