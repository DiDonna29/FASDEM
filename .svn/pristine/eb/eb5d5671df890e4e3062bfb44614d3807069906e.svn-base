/**
 * 02/03/2011 17:58:51
 * marcenrl
 * 2011
 */
package ve.gob.dem.fasdem.action.aps.liquidacion;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import ve.gob.dem.fasdem.bean.DetalleFactura;
import ve.gob.dem.fasdem.bean.Factura;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.per.PerDetalleFactura;
import ve.gob.dem.fasdem.per.PerFactura;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.fasdem.valores.CargaValores;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

/**
 * @author marcenrl
 * 
 */
public class CargarDetalleFactura extends GenericAction {

	protected static Logger log = Logger.getLogger(CargarDetalleFactura.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_DETALLE_FACTURA);
		DynaActionForm dForm = (DynaActionForm) form;
		List<DetalleFactura> detalleFacturas = null;
		// request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_APS);
		Mapa mapa = new Mapa();
		getValores(mapa, request);
		PerSiniestro ps = new PerSiniestro();
		PerFactura pf = new PerFactura();
		PerDetalleFactura pdf = new PerDetalleFactura();
		Siniestro s = null;
		Factura f = null;
		log.info("anio request " + request.getParameter("anioBusqueda"));

		

		log.info("mapa " + mapa);
		s = ps.search(mapa);
		f = pf.search(mapa);
		request.setAttribute("idSini", mapa.getIdSiniestro());
		request.setAttribute("idFact", mapa.getIdFactura());
		request.setAttribute("siniestro", s);
		request.setAttribute("factura", f);
		try {
			detalleFacturas = pdf.searchByFactura(mapa);
			request.setAttribute("totalIva", calcularIva(detalleFacturas, f.getPorcentajeIva()));
			request.setAttribute("totalFactura", calcularTotal(detalleFacturas));
			request.setAttribute("detalleFactura", detalleFacturas);
		} catch (PersonalNotFoundException e) {
			request.setAttribute("detalleFactura", null);
		} catch (Exception e) {
			log.error("error", e);
			request.setAttribute("detalleFactura", null);
		}
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		mapa = getDForm(request, form, ent);
		mapa.setAnioSiniestro(getAnioBusqueda(request));
		getValores(mapa, request);
		DecimalFormat formateador = new DecimalFormat("########.##");
		double totalMoNToFactura = Double.valueOf(formateador.format(f.getMontoFactura()).replace(",", "."));
		log.info("totalMoNToFactura" + totalMoNToFactura);
		if (montoDisponible(mapa, totalMoNToFactura)) {
			int df = pdf.insert(mapa);
			incluirTraza(TR_LIQUIDACION_CARGAR_DETALLEFACTURA, String.valueOf(df), "Cargar Detalle Factura", usuarioSession(request));
			mapa.setIdTipoGasto(CargaValores.getInstance().getValores().getTipoGastoIva());
			mapa.setMonto(calcularIva(pdf.searchByFactura(mapa), f.getPorcentajeIva()));
			pdf.insertIva(mapa);
			dForm.set("tipoGasto", "-1");
			dForm.set("monto", "0.00");
		} else {
			am.add(ALERT_AVISOS, new ActionMessage("general.monto.excedefactura", mapa.getMonto()));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		try {
			detalleFacturas = pdf.searchByFactura(mapa);
			request.setAttribute("totalIva", calcularIva(detalleFacturas, f.getPorcentajeIva()));
			request.setAttribute("totalFactura", calcularTotal(detalleFacturas));
			request.setAttribute("detalleFactura", detalleFacturas);
		} catch (PersonalNotFoundException e) {
			request.setAttribute("detalleFactura", null);
		} catch (Exception e) {
			log.error("error", e);
			request.setAttribute("detalleFactura", null);
		}
		return mapping.findForward(FWD_INPUT);
	}

	@SuppressWarnings("rawtypes")
	protected boolean montoDisponible(Mapa mapa, double montoFactura) throws PersonalNotFoundException, SQLException {
		PerDetalleFactura pdf = new PerDetalleFactura();
		PerFactura pf = new PerFactura();
		double total = 0.000;
		double totalgc = 0.000;
		double montoIva = 0.000;
		double monto = 0.0;
		double montogc = 0.0;
		double montoFact = 0.0;
		PerSiniestro ps = new PerSiniestro();
		Siniestro s = null;
		List list = null;
		Factura f = null;
		
		s = ps.search(mapa);
		DecimalFormat formateador = new DecimalFormat("########.##");
		try {
			f = pf.search(mapa);
		} catch (Exception e) {
			log.error("error", e);
		}
		try {
			list = pdf.searchByFactura(mapa);
		} catch (PersonalNotFoundException e) {
			list = new ArrayList();
		} catch (SQLException e) {
			log.error("error", e);
			list = new ArrayList();
		}
		for (int i = 0; i < list.size(); i++) {
			DetalleFactura df = (DetalleFactura) list.get(i);
			if (!df.getTipoGasto().isDeducible()) {
				total = total + df.getMonto();
			}
		}
		
		try {
			montoFact = Double.valueOf(formateador.format(f.getPorcentajeIva()).replace(",", "."));
			montoIva = calcularIva(list, montoFact);
			montoIva = Double.valueOf(formateador.format(montoIva).replace(",", "."));
		} catch (Exception e) {
			log.error("error", e);
		}
		/*determino si es GASTO CLÍNICO y si es tramite EMERGENCIA y lo sumo*/
		
		log.info("mapa.getIdTipoGasto()"+mapa.getIdTipoGasto());
		log.info("s.getTipoTramite()"+ s.getTipoTramite().getId() );
		
		if(mapa.getIdTipoGasto()==2 && s.getTipoTramite().getId() == Integer.valueOf(TIPO_TRAMITE_EMERGENCIA))
		{
			montogc = Double.valueOf(formateador.format(mapa.getMonto()).replace(",", "."));
			totalgc = totalgc + montogc + montoIva;
			log.info("tipogastodosdos");
		}else{
			monto = Double.valueOf(formateador.format(mapa.getMonto()).replace(",", "."));
			 total = total + monto + montoIva;
		}
		log.info("totaltotalmonto"+total);
		/*determino si es gasto clinico y lo sumo*/
		double totalF = Double.valueOf(formateador.format(total).replace(",", "."));
		if (totalF > montoFactura) {
			log.info("gastos monto" + totalF);
			log.info("gastos monto" + monto);
			log.info("gastos montoFactura" + montoFactura);
			return false;
		}else{
				log.info("totalF monto" + totalF);
				log.info("montoFactura monto" + montoFactura);
				log.info("total monto" + total);
				log.info("monto monto" + monto);
				log.info("montoIva monto" + montoIva);
				return true;
			 }
	}

	protected static void getValores(Mapa m, HttpServletRequest request) {
		int idSini = -1;
		int idFact = -1;
		if (request.getParameter("idSini") != null) {
			idSini = Integer.parseInt(request.getParameter("idSini"));
		} else {
			try {
				idSini = Integer.parseInt((String) request.getAttribute("idSini"));
			} catch (Exception e) {
				idSini = -1;
			}
		}
		if (request.getParameter("idFact") != null) {
			idFact = Integer.parseInt(request.getParameter("idFact"));
		} else {
			try {
				idFact = Integer.parseInt((String) request.getAttribute("idFact"));
			} catch (Exception e) {
				idFact = -1;
			}
		}
		m.setIdSiniestro(idSini);
		m.setIdFactura(idFact);
		if (request.getParameter("anioSiniestro") != null && !"".equals(request.getParameter("anioSiniestro"))) {
			m.setAnioSiniestro(Integer.parseInt(request.getParameter("anioSiniestro")));
		} else {
			// si se redirecciona desde eliminar factura, el parametro se llama
			// anioBusqueda
			if (request.getParameter("anioBusqueda") != null) {
				log.info("anioSini request " + request.getParameter("anioBusqueda"));
				request.getSession().setAttribute("anioSiniestro", request.getParameter("anioBusqueda"));
				m.setAnioSiniestro(Integer.parseInt(request.getParameter("anioBusqueda")));
			}
			if (request.getSession().getAttribute("anioBusqueda") != null && !"".equals(String.valueOf(request.getSession().getAttribute("anioBusqueda")))) {
				m.setAnioSiniestro(Integer.parseInt(String.valueOf(request.getSession().getAttribute("anioBusqueda"))));
			}
		}
	}

	@SuppressWarnings("rawtypes")
	protected double calcularIva(List list, double valorIva) throws Exception {
		DetalleFactura df = null;
		Double total = 0.0;
		Double totalF = 0.0;
		Double totalCalculo = 0.0;
		DecimalFormat formateador = new DecimalFormat("########.##");
		for (int i = 0; i < list.size(); i++) {
			df = (DetalleFactura) list.get(i);
			if (df.getTipoGasto().isIva() && !df.getTipoGasto().isDeducible()) {
				total = total + df.getMonto();
				totalF = Double.valueOf(formateador.format(total).replace(",", "."));
			}
		}
		totalCalculo = (totalF * valorIva) / 100;
		totalCalculo = Double.valueOf(formateador.format(totalCalculo).replace(",", "."));
		return (totalCalculo);
	}

	@SuppressWarnings("rawtypes")
	protected double calcularTotal(List list) {
		DetalleFactura df = null;
		double total = 0.0;
		double totalDeducible = 0.0;
		for (int i = 0; i < list.size(); i++) {
			df = (DetalleFactura) list.get(i);
			if (!df.getTipoGasto().isDeducible()) {
				total = total + df.getMonto();
			} else {
				totalDeducible = totalDeducible + df.getMonto();
			}
		}
		return total - totalDeducible;
	}
}
