package ve.gob.dem.fasdem.action.aps.liquidacion;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.DetalleFactura;
import ve.gob.dem.fasdem.bean.Estatus;
import ve.gob.dem.fasdem.bean.Factura;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.MotivoEstatus;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.pagos.ExpPreOrdenPago;
import ve.gob.dem.fasdem.per.PerDetalleFactura;
import ve.gob.dem.fasdem.per.PerEstatus;
import ve.gob.dem.fasdem.per.PerFactura;
import ve.gob.dem.fasdem.per.PerMotivoEstatus;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.framework.exception.CoberturaNotDisponibleException;
import ve.gob.dem.framework.exception.MontoSiniestroExcedidoException;
import ve.gob.dem.framework.exception.PersonalFacturaNoFillException;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;

public class CambioEstatusLiquidado extends GenericAction {

	protected static Logger log = Logger.getLogger(CambioEstatusLiquidado.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		Mapa mapa = new Mapa();
		int idSini = Integer.parseInt(request.getParameter("id"));
		Siniestro s = null;
		Factura factura;
		PerSiniestro ps = new PerSiniestro();
		MotivoEstatus me = new MotivoEstatus();
		PerFactura pf = new PerFactura();
		PerMotivoEstatus pme = new PerMotivoEstatus();
		List<Factura> facturas = null;
		PerMotivoEstatus perMotEst = new PerMotivoEstatus();
		MotivoEstatus motEst = new MotivoEstatus();
		List<Factura> listFactura = new ArrayList<Factura>();
		request.setAttribute(KEY_TIPO_TRAMITE, TIPO_TRAMITE_APS);
		mapa.setIdSiniestro(idSini);

		if (request.getParameter("anioSiniestro") != null && !"".equals(request.getParameter("anioSiniestro"))) {
			log.info("anioSini request " + request.getParameter("anioSiniestro"));
			request.getSession().setAttribute("anioSiniestro", request.getParameter("anioSiniestro"));
			mapa.setAnioSiniestro(Integer.parseInt(request.getParameter("anioSiniestro")));
		}

		if (request.getSession().getAttribute("anioSiniestro") != null) {
			mapa.setAnioSiniestro(Integer.parseInt(String.valueOf(request.getSession().getAttribute("anioSiniestro"))));
		}

		log.info("anio en map " + mapa.getAnioSiniestro());
		
		// mapa.setAnioSiniestro(Integer.parseInt(request.getParameter(("anioSiniestro"))));
		// mapa.setAnioSiniestro(getAnioBusqueda(request));

		s = ps.search(mapa);
		request.setAttribute("siniestro", s);
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		// En vez de tomar los datos del formilario, tomo los del siniestro
		// mapa = getDForm(request, form, ent);
		mapa.setAnioSiniestro(s.getAnioSiniestro());
		mapa.setIdSiniestro(s.getId());
		mapa.setIdProveedor(s.getProveedor().getId());
		try {

			log.info("mapa antes fac " + mapa);
			facturas = pf.listSearchIdSiniestro(mapa);
		} catch (PersonalNotFoundException e) {
			log.info("notfound", e);
		} catch (Exception e) {
			log.error("error", e);
		}
		if (facturas == null) {
			am.add(ALERT_VALIDACION, new ActionMessage("env.general.sinfacturas"));
			saveMessages(request, am);
			return mapping.findForward(FWD_INPUT);
		}
		mapa.setIdEstatus(COD_ESTATUS_LIQUIDADO);
		mapa.setFechaLiquidacion(new Date());
		mapa.setMontoSiniestro(s.getMontoNegociado());
		// Solo carta Aval y Emergencia
		try {
			/*no valida el monto del detalle de las facturas contra el monto general de  la factura SOLO PARA EL TRÁMITE EMERGENCIAS*/
			 /*if (s.getTipoTramite().getId() == COD_TIPO_TRAMITE_CARTAAVAL || s.getTipoTramite().getId() == COD_TIPO_EMERGENCIA || s.getTipoTramite().getId() == COD_TIPO_TRAMITE_REEMBOLSO) {*/
			if (s.getTipoTramite().getId() == COD_TIPO_TRAMITE_CARTAAVAL ||  s.getTipoTramite().getId() == COD_TIPO_TRAMITE_REEMBOLSO) {
		// cambio que da error		
//			if (s.getTipoTramite().getId() == COD_TIPO_TRAMITE_CARTAAVAL ||  s.getTipoTramite().getId() == COD_TIPO_TRAMITE_REEMBOLSO) {
				mapa.setMontoAmparado(calcularAmparado(facturas, mapa, true, s.getMontoMaximoAutorizado()));
			} else {
				mapa.setMontoAmparado(calcularAmparado(facturas, mapa, false, s.getMontoMaximoAutorizado()));
				mapa.setMontoAmparado(mapa.getMontoAmparado() - s.getMontoAmparado());
				mapa.setMontoSiniestro(s.getMontoAmparado());
				mapa.setCedula(s.getCedula());
				mapa.setCedulaBeneficiario(s.getCedulaBeneficiario());
				mapa.setAnioSiniestro(s.getAnioSiniestro());
				mapa.setIdCobertura(s.getCobertura().getId());
				mapa.setPatologiaOrganoTratamiento(s.getPatologiaOrganoTratamiento().getId());
				try {
					log.info("sdkfhkashdfahsldfhals " + mapa);
					findDisponible(mapa, s.getFechaCreado());
				} catch (CoberturaNotDisponibleException e) {
					am.add(ALERT_AVISOS, new ActionMessage("env.general.coberuragotada"));
					saveMessages(request, am);
					return mapping.findForward(FWD_INPUT);
				}
				
				mapa.setAnioSiniestro(s.getAnioSiniestro());
				mapa.setMontoAmparado(calcularAmparado(facturas, mapa, false, s.getMontoMaximoAutorizado()));
			}
		} catch (MontoSiniestroExcedidoException e) {
			am.add(ALERT_AVISOS, new ActionMessage(e.getMessage()));
			saveMessages(request, am);
			request.setAttribute(KEY_ENTORNO, new Entorno(Entorno.MOD_APS_LIQUIDAR));
			try {
				listFactura = pf.listSearchIdSiniestro(mapa);
				request.setAttribute("facturas", listFactura);
			} catch (Exception e1) {
				log.error("error", e1);
			}
			return mapping.findForward(FWD_INPUT);
		} catch (PersonalFacturaNoFillException e) {
			log.info("errorororororororo ", e);
			am.add(ALERT_AVISOS, new ActionMessage("env.factura.nofill", e.getMessage()));
			saveMessages(request, am);
			request.setAttribute(KEY_ENTORNO, new Entorno(Entorno.MOD_APS_LIQUIDAR));
			try {
				listFactura = pf.listSearchIdSiniestro(mapa);
				request.setAttribute("facturas", listFactura);
			} catch (Exception e1) {
				log.error("error", e1);
			}
			return mapping.findForward(FWD_INPUT);
		}
		mapa.setMontoNegociado(calcularNegociado(facturas, mapa, false, s.getMontoMaximoAutorizado()));
		// VALIDAR MONTOS

		mapa.setMontoNoAmparado(mapa.getMontoNegociado() - mapa.getMontoAmparado());
		mapa.setIdUsuario(usuarioSession(request).getCedula());
		
		log.info("mapa antes de liquidar id usuariooooooo " + mapa);
		ps.updateLiquidacion(mapa);
		incluirTraza(TR_LIQUIDACION_LIQUIDAR_SINIESTRO, String.valueOf(mapa.getIdSiniestro()), "Liquidar Siniestro", usuarioSession(request));
		// motivo estatus
		me.setDescripcion(request.getParameter("observacion"));
		me.setFechaInicio(new Date());
		me.setIdDependencia(usuarioSession(request).getIdDependencia());
		me.setIdEstatus(COD_ESTATUS_LIQUIDADO);
		me.setIdUsuario(usuarioSession(request).getLogin());
		me.setLoginUsuario(usuarioSession(request).getLogin());
		me.setIdSiniestro(mapa.getIdSiniestro());
		// VERIFICO SI TENIA UN ESTATUS LIQUIDADO
		try {
			pme.searchByEstatus(mapa);
			me.setId(pme.searchByEstatus(mapa).getId());
			// Si consigo un estatus de ese tipo, modifico la decripción y los
			// datos del usuario
			pme.update(me);
		} catch (PersonalNotFoundException e) {
			// inserta
			pme.insert(me);
		}
		incluirTraza(TR_LIQUIDACION_ASIGNAR_ESTATUS, String.valueOf(me.getIdSiniestro()), "Liquidar Siniestro", usuarioSession(request));
		// busco el siniestro con los nuevos valores
		s = ps.search(mapa);
		request.setAttribute("siniestro", s);
		PerEstatus perEst = new PerEstatus();
		Estatus est = new Estatus();
		est = perEst.buscar(s.getEstatus().getId());
		if (s.getEstatus().getId() == 4) {
			try {
				mapa.setIdEstatus(est.getId());
				mapa.setIdSiniestro(s.getId());
				motEst = perMotEst.searchByEstatus(mapa);
				request.setAttribute("motEst", motEst);
			} catch (Exception e) {
				motEst = new MotivoEstatus();
			}

		}

		try {
			mapa.setIdSiniestro(s.getId());
			mapa.setAnioSiniestro(s.getAnioSiniestro());
			listFactura = pf.listSearchIdSiniestro(mapa);
			for (int r = 0; r <= listFactura.size(); r++) {
				factura = (Factura) listFactura.get(r);
				factura.getId();
				if (factura.getPreOrden() != null && !"".equals(factura.getPreOrden())) {
					try {
						factura.setEstatusPreOrden(ExpPreOrdenPago.estatusPreOrden(factura.getPreOrden()));
					} catch (Exception e) {
						log.error("error", e);
						factura.setEstatusPreOrden("");
					}
				}
				mapa.setIdFactura(factura.getId());
				mapa.setAnioSiniestro(s.getAnioSiniestro());
				listFactura.set(r, factura);
			}
		} catch (Exception e1) {
			factura = new Factura();
			request.setAttribute("factura", factura);
			request.setAttribute("listFactura", listFactura);
		}
		return mapping.findForward(FWD_SUCCESS);
	}

	@SuppressWarnings("rawtypes")
	private double calcularAmparado(List facturas, Mapa mapa, boolean validar, double maximoAutorizado) throws PersonalFacturaNoFillException, SQLException, MontoSiniestroExcedidoException, PersonalNotFoundException {
		List detalle = new ArrayList();
		Factura f = null;
		DetalleFactura df = null;
		PerSiniestro ps = new PerSiniestro();
		Siniestro s = null;
		PerDetalleFactura pdf = new PerDetalleFactura();
		double totalFactura = 0.0;
		double totalNoAmparado = 0.0;
		double totalAmparado = 0.0;
		double totalLiquidado = 0.0;
		double total = 0.0;
		
		s = ps.search(mapa);
		if (facturas != null) {

			for (int i = 0; i < facturas.size(); i++) {
				f = (Factura) facturas.get(i);
				totalFactura = totalFactura + f.getMontoFactura();
				mapa.setIdFactura(f.getId());
				try {
					detalle = pdf.searchByFacturaIva(mapa);
				} catch (PersonalNotFoundException e) {
					log.info("notfound", e);
				}
				double subTotal = 0.0;
				double subTotalAmparado = 0.0;
				double subTotalNoAmparado = 0.0;
				double subTotalDeducible = 0.0;

				DecimalFormat formateador = new DecimalFormat("########.##");
				for (int j = 0; j < detalle.size(); j++) {

					df = (DetalleFactura) detalle.get(j);

					if (!df.getTipoGasto().isDeducible()) {
						if (df.getTipoGasto().getId() == COD_TIPO_GASTO_NO_AMPARADO) {
							subTotalNoAmparado = subTotalNoAmparado + Double.valueOf(formateador.format(df.getMonto()).replace(",", "."));
						} else {
						subTotalAmparado = subTotalAmparado + Double.valueOf(formateador.format(df.getMonto()).replace(",", "."));
						subTotalAmparado = Double.valueOf(formateador.format(subTotalAmparado).replace(",", "."));
						}
					} else {
						subTotalDeducible = subTotalDeducible + Double.valueOf(formateador.format(df.getMonto()).replace(",", "."));
					}

				}
				subTotal = subTotalAmparado + subTotalNoAmparado - subTotalDeducible;
				log.info("bbbb " + subTotalAmparado + " rdthdrth " + subTotalDeducible);

				double subtotalF = Double.valueOf(formateador.format(subTotal).replace(",", "."));

				log.info("validar BELGICA ES " + f.getMontoFactura() + " subtotalF " + subtotalF);
				log.info("rrrrrr " + s.getTipoTramite().getId() + " rdthdrth " + Integer.valueOf(TIPO_TRAMITE_EMERGENCIA));
				/*determino si es tramite EMERGENCIA y valido*/
				if (f.getMontoFactura() != subtotalF && s.getTipoTramite().getId()!= Integer.valueOf(TIPO_TRAMITE_EMERGENCIA)) { 

					throw new PersonalFacturaNoFillException(f.getNumeroFactura());
				}
				total = total + subTotal;

				totalAmparado = totalAmparado + subTotalAmparado;
				totalNoAmparado = totalNoAmparado + subTotalNoAmparado;
				totalLiquidado = totalAmparado;

			}

		}
		DecimalFormat formateador = new DecimalFormat("########.##");
		double montoSiniestro = Double.valueOf(formateador.format(mapa.getMontoSiniestro()).replace(",", "."));
		double totalAmparadoF = Double.valueOf(formateador.format(totalAmparado).replace(",", "."));
		
		log.info("validar comienzo " + validar);
		
		if (validar) {
			log.info("validar");
			if (maximoAutorizado == 0) {
				log.info("mapa.getMontoSiniestro() " + montoSiniestro);
				log.info("totalAmparado " + totalAmparadoF);
				if (montoSiniestro < totalAmparadoF) {
					throw new MontoSiniestroExcedidoException("monto.siniestro.excedido");
				}
			} else {
				log.info("validar else");				
				double totalLiq = Double.valueOf(formateador.format(totalLiquidado).replace(",", "."));
				log.info("totalLiquidado " + totalLiq);
				if (maximoAutorizado < totalLiq) {
					log.info("montoautorizadoa " + maximoAutorizado + "monto factura total " + totalLiquidado);
					throw new MontoSiniestroExcedidoException("monto.autorizado.excedido");
				}
			}
		}
		log.info("no valida");
		double totalLiquidadoF = Double.valueOf(formateador.format(totalLiquidado).replace(",", "."));
		return totalLiquidadoF;
	}

	@SuppressWarnings("rawtypes")
	private double calcularNegociado(List facturas, Mapa mapa, boolean validar, double maximoAutorizado) throws PersonalFacturaNoFillException, SQLException, MontoSiniestroExcedidoException {
		Factura f = null;
		double totalFactura = 0.0;
		if (facturas != null) {
			for (int i = 0; i < facturas.size(); i++) {
				f = (Factura) facturas.get(i);
				totalFactura = totalFactura + f.getMontoFactura();
			}
			if (validar) {
				log.info("validar nego");
				if (maximoAutorizado == 0) {
					if (mapa.getMontoSiniestro() < totalFactura) {
						throw new MontoSiniestroExcedidoException("monto.siniestro.excedido");
					}
				} else {
					log.info("montoautorizado " + maximoAutorizado + "monto factura total " + totalFactura);
					if (maximoAutorizado < totalFactura) {
						throw new MontoSiniestroExcedidoException("monto.siniestro.excedido");
					}
				}
			}
		}
		return totalFactura;
	}
}
