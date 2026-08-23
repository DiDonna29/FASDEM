package ve.gob.dem.fasdem.action.consulta;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Utilidad;

public class ConsultaBeneficiario extends GenericAction {
         
     
        public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws  Exception {
                ActionMessages am = new ActionMessages();
                Entorno ent = new Entorno(Entorno.MOD_BUSCA_BENEFICIARIO_CONSULTA);
                PerSiniestro ps = new PerSiniestro();
                DynaActionForm dForm = (DynaActionForm) form;
                try {
                        dForm.set("anioBusqueda", Utilidad.DateToString(new Date(), "yyyy"));
                } catch (Exception e) {
                }
                try {
                        validarAction(request, form, ent, am, this.getClass());
                } catch (PersonalNotFillItems e) {
                        return mapping.findForward(FWD_INPUT);
                }
                 String cedula = request.getParameter("cedula");
                 String nombres = request.getParameter("nombres");
                 String codigo = request.getParameter("codigo");
                 String rif = request.getParameter("proveedor");
                log.info("cedula" + cedula + "proveedor" + rif);
                 String fecha = request.getParameter("fecha");
                if (cedula == null) {
                        cedula = "";
                }
                if (nombres == null) {
                        nombres = "";
                }
                if (codigo == null) {
                        codigo = "";
                }
                if (rif == null) {
                        rif = "";
                        fecha = "";
                }
                if (fecha == null) {
                        rif = "";
                        fecha = "";
                }
                if ((("".equals(cedula) && "".equals(nombres)) && "".equals(codigo)) && "".equals(rif)) {
                        am.add(ALERT_VALIDACION, new ActionMessage("env.criterio.requerido"));
                        saveMessages(request, am);
                        return mapping.findForward(FWD_INPUT);
                } else {
                        log.info("el codigo [] " + codigo);
                        if (!"".equals(codigo)) {
                                if (codigo.length() < 4) {
                                        am.add(ALERT_VALIDACION, new ActionMessage("env.codigo.corto"));
                                        saveMessages(request, am);
                                        return mapping.findForward(FWD_INPUT);
                                }
                        }
                        request.setAttribute("cedula", cedula);
                        request.setAttribute("nombres", nombres);
                        request.setAttribute("codigo", codigo);
                        request.setAttribute("rif", rif);
                        request.setAttribute("fecha", fecha);
                }
                Mapa m = new Mapa();
                m.setCedula(cedula);
                m.setNombres(nombres);
                m.setCodigo(codigo);
                m.setRif(rif);
                if (rif != "" && fecha == "") {
                        am.add(ALERT_AVISOS, new ActionMessage("env.general.rifObligatorio"));
                        saveMessages(request, am);
                        return mapping.findForward(FWD_INPUT);
                }
                if (fecha != "") {
                        m.setFecha(Utilidad.StringToDate(fecha, "dd/MM/yyyy"));
                }
                m.setAnioSiniestro( Integer.parseInt(request.getParameter("anioBusqueda")));
                // Nunca Ejecutaria el else por la condicion de la linea 57 aprox.
                //if (rif == null) {
                // rif = "";
                // fecha = "";
                // }
                if (rif != null) {
                        try {
                                log.info("este es nmwmckwke " + m);
                                 List resultado = ps.searchMultiple(m);
                                request.setAttribute("resultado", resultado);
                        } catch ( Exception e) {
                                log.error("este es el error ", e);
                                am.add(ALERT_AVISOS, new ActionMessage("list.notfound"));
                                saveMessages(request, am);
                                return mapping.findForward(FWD_INPUT);
                        }
                } /*
                 * else {
                 *
                 * try { List resultado = ps.searchMultiple(m);
                 * request.setAttribute("resultado", resultado); } catch (Exception e) {
                 * am.add(ALERT_AVISOS, new ActionMessage("list.notfound"));
                 * saveMessages(request, am); return mapping.findForward(FWD_INPUT); } }
                 */
                return mapping.findForward(FWD_INPUT);
        }
}
  
