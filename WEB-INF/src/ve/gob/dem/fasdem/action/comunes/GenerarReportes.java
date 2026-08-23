package ve.gob.dem.fasdem.action.comunes;

import java.sql.Connection;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ve.gob.dem.fasdem.ExpReporte;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Reporte;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Parametros;
import ve.gob.dem.framework.seguridad.bean.Usuario;

public class GenerarReportes extends Action {
	static Logger log = Logger.getLogger(GenerarReportes.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession httpsc = request.getSession();
			Usuario usuario = (Usuario) httpsc.getAttribute("usuario");
			Connection db = null;
			String id;
			String alias="";
			String sql="";
			String anio="";
			Mapa map=new Mapa();
			String fechaInicio;
			String fechaFin;
			String idFactura;
			int anioo=0;
			String cedula;
			
			id = Parametros.getStrParameter(request, "id", "0");
			idFactura = Parametros.getStrParameter(request, "idFactura", "0");
			anio = Parametros.getStrParameter(request, "anio", "0");
			sql=Parametros.getStrParameter(request, "sql", "");
			alias=Parametros.getStrParameter(request, "alias", "");
			fechaInicio = Parametros.getStrParameter(request, "fechaInicio", "");
			fechaFin = Parametros.getStrParameter(request, "fechaFin", "");
			cedula=Parametros.getStrParameter(request, "cedula", "");
		//***carta de asegurabilidad***//
	          if(anio.equals("0")){
	                Calendar calendar = Calendar.getInstance();
	                int year = calendar.get(Calendar.YEAR);
	                String anio_actual = Integer.toString(year);
	                anio = anio_actual;
	            }
	        //***carta de asegurabilidad***//	
			
			 try {	
			map.put("anio", anio);
			anioo=Integer.parseInt(anio);
			 db =   Conexion.getConexion();
			switch (anioo)
			{
				case 2010:
				alias="000";
				break;
				case 2011:
					alias="010";
					break;
				case 2012:
					alias="020";
					break;
				case 2013:
					alias="030";
					break;
				case 2014:
					alias="040";
					break;
				case 2015:
					alias="050";
					break;
				case 2016:
					alias="060";
					break;
				case 2017:
					alias="070";
					break;
				case 2018:
					alias="080";
					break;
				case 2019:
					alias="090";
					break;
				case 2020:
					alias="100";
					break;
				case 2021:
					alias="110";
					break;
				case 2022:
					alias="120";
					break;
				case 2023:
					alias="130";
					break;
				case 2024:
					alias="140";
					break;
				case 2025:
					alias="150";
					break;
				case 2026:
					alias="160";
					break;
				case 2027:
					alias="170";
					break;
				default:
				break;
			}
			 } finally {
				    Conexion.closeConexion(db);
				}
			map.setId(Integer.parseInt(id));
			map.setIdFactura(Integer.parseInt(idFactura));
			map.setFechaInicio(fechaInicio);
			map.setFechaFin(fechaFin);
			map.setCedula(cedula);
			map.setIdNotaMedica(Parametros.getStrParameter(request, "idNotaMedica", ""));
			map.setIdReporte(request.getParameter("id_reporte"));
			map.setDescripcionMedicinas(request.getParameter("descripcionMedicinas"));
			map.setIdUsuario(usuario.getLogin());
			map.setDescripcionUsuario(usuario.getNombre());
			map.setIdDependencia(usuario.getIdDependencia());
			map.setAnioSiniestro(Integer.parseInt(anio));
			map.setAlias(Integer.parseInt(alias));
			map.setSql(sql);
			
			
			log.info("mapa antes de buscar reporte "+map);
			
			Reporte rep = ExpReporte.EjecucionReporte(map);

			request.setAttribute("reporte", rep);
			request.setAttribute("id", id);
			request.setAttribute("idFactura", idFactura);
			request.setAttribute("cedula", cedula);
			request.setAttribute("fechaInicio", fechaInicio);
			request.setAttribute("fechaFin", fechaFin);
			request.setAttribute("id_reporte", request.getParameter("id_reporte"));
			request.setAttribute("anio", anio);
			request.setAttribute("mp", map);
			request.setAttribute("alias", alias);
			request.setAttribute("sql", sql);
			
			return (mapping.findForward(GenericAction.FWD_INPUT));
		} catch (Exception e) {
			log.error("erradoooooo nuevo",e);
			return (mapping.findForward(GenericAction.FWD_INPUT));
		}
	}
}
