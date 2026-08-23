package ve.gob.dem.fasdem.action.administrador.poliza;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Poliza;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.administrador.ExpPoliza;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

/**
 * @author marcenrl
 * 
 */
public class adminPoliza extends GenericAction {
	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.apache.struts.action.Action#execute(org.apache.struts.action.
	 * ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	static protected Logger log = Logger.getLogger(adminPoliza.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		String accion = request.getParameter("funcion");
		// request.setAttribute("botonera","1");
		if (accion == null || "".equals(accion)) // INGRESO A LA PAGINA POR
													// PRIMERA VEZ
		{
			request.setAttribute("listPoliza", ExpPoliza.BuscarLista());
			request.setAttribute("botonera", "1");
			return mapping.findForward("input");
		} else {
			if (accion.equals("3")) {
				log.info("info poliza 3.0");
				request.setAttribute("id_especialidad", request.getParameter("crea"));
				request.setAttribute("botonera", "2");
				Poliza pol = ExpPoliza.buscarporID(Integer.valueOf(request.getParameter("crea")));
				request.setAttribute("activo", pol.isActivo());
				request.setAttribute("fechaInicio", pol.getFechaInicio());
				request.setAttribute("fechaFin", pol.getFechaFin());
				request.setAttribute("busca", pol.getDescripcion());
			}
			if (accion.equals("2")) {
				log.info("info poliza 2.0");
				Poliza pol = new Poliza();
				pol.setId(Integer.valueOf(request.getParameter("crea")));
				pol.setDescripcion(request.getParameter("busca"));
				pol.setFechaInicioString(convertFecha(request.getParameter("fechaInicio")));
				pol.setFechaFinString(convertFecha(request.getParameter("fechaFin")));
				pol.setActivo(Boolean.valueOf(request.getParameter("activo")));
				// ExpPoliza.modificarPoliza(pol);
				request.setAttribute("botonera", "1");
				
				try {
					ExpPoliza.modificarPoliza(pol);
	    	    	incluirTraza( TR_ADMPOLIZA_MODIFICAR, request.getParameter("crea"),"ID DE LA POLIZA A LA CUAL SE LE MODIFICO DESPCRIP:"+request.getParameter("busca")+", FECHA INICIO:"+request.getParameter("fechaInicio")+", FECHA FIN:"+request.getParameter("fechaFin")+", ESTATUS:"+request.getParameter("activo"), usuarioSession(request));
				} catch (Exception e) {
					log.info(e);
				}
			}
			if (accion.equals("1")) {
				log.info("info poliza 1.0");
				Poliza pol = new Poliza();
				pol.setDescripcion(request.getParameter("busca"));
				pol.setFechaInicio(parseFecha(request.getParameter("fechaInicio")));
				pol.setFechaFin(parseFecha(request.getParameter("fechaFin")));
				if (ExpPoliza.validarFechaPolizaInicio(pol) == true || ExpPoliza.validarFechaPolizaFin(pol) == true || ExpPoliza.validarNombrePoliza(pol) == true) {
					request.setAttribute("mensaje", "Ya Existe una poliza con estas caracteristicas");
					request.setAttribute("botonera", "1");
				} else {
					request.setAttribute("botonera", "1");
					try {
						ExpPoliza.crearPoliza(pol);
						
		    	    	incluirTraza( TR_ADMPOLIZA_CREAR, String.valueOf(ExpPoliza.buscarPoliza(pol).getId()),"ID DE LA POLIZA QUE SE CREO DESPCRIP:"+request.getParameter("busca")+", FECHA INICIO:"+request.getParameter("fechaInicio")+", FECHA FIN:"+request.getParameter("fechaFin"), usuarioSession(request));
					} catch (Exception e) {
						log.info(e);
					}
				}
			}
			log.info("info poliza 0.0");
			request.setAttribute("listPoliza", ExpPoliza.BuscarLista());
			request.setAttribute("resp", "paso" + accion + "" + request.getParameter("busca"));
			return mapping.findForward("input");
		}
	}
	
	private Date parseFecha(String fecha){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(fecha.substring(0,2)));
		c.set(Calendar.MONTH, Integer.parseInt(fecha.substring(3,5)));
		c.set(Calendar.YEAR, Integer.parseInt(fecha.substring(6,10)));
		return c.getTime();
	}
	private String convertFecha(String fecha){
		String fechaResult[];
		String fechaconv;
		fechaResult = fecha.split("/");
		fechaconv=fechaResult[2]+"-"+fechaResult[1]+"-"+fechaResult[0];
		return fechaconv;
		}
	}