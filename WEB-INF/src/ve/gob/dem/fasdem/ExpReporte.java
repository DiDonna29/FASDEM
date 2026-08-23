package ve.gob.dem.fasdem;

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalCriticalException;
import ve.gob.dem.framework.exception.PersonalException;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.seguridad.bean.Usuario;
import ve.gob.dem.framework.seguridad.exp.ExpUsuario;
import ve.gob.dem.fasdem.bean.Cobertura;
import ve.gob.dem.fasdem.bean.Cuenta;
import ve.gob.dem.fasdem.bean.CuentaBenef;
import ve.gob.dem.fasdem.bean.Dependencia;
import ve.gob.dem.fasdem.bean.EstatusTipoTramite;
import ve.gob.dem.fasdem.bean.Factura;
import ve.gob.dem.fasdem.bean.Mapa;
import ve.gob.dem.fasdem.bean.NotaTecnica;
import ve.gob.dem.fasdem.bean.Reporte;
import ve.gob.dem.fasdem.bean.Siniestro;
import ve.gob.dem.fasdem.bean.TipoEnfermedad;
import ve.gob.dem.fasdem.exp.administrador.ExpCuenta;
import ve.gob.dem.fasdem.exp.pagos.ExpPreOrdenPago;
import ve.gob.dem.fasdem.per.PerCobertura;
import ve.gob.dem.fasdem.per.PerCuenta;
import ve.gob.dem.fasdem.per.PerDependencia;
import ve.gob.dem.fasdem.per.PerEstatusTipoTramite;
import ve.gob.dem.fasdem.per.PerFactura;
import ve.gob.dem.fasdem.per.PerNotaTecnica;
import ve.gob.dem.fasdem.per.PerReporte;
import ve.gob.dem.fasdem.per.PerSiniestro;
import ve.gob.dem.fasdem.per.PerTipoEnfermedad;
import ve.gob.dem.framework.recursos.Utilidad;
import ve.gob.dem.framework.recursos.Encriptar;

@SuppressWarnings("rawtypes")
public class ExpReporte implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4058432745352400393L;
	static Logger log = Logger.getLogger(ExpReporte.class);

	@SuppressWarnings({ "static-access", "unchecked" })
	public static Reporte EjecucionReporte(Mapa map) throws PersonalCriticalException, PersonalException, PersonalNotFoundException, SQLException, NumberFormatException, IOException, NamingException {
		Connection cnx = null;
		try {
			try {
				cnx = Conexion.getConexion();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Reporte rep = new Reporte();
			HashMap params = new HashMap();
			PerSiniestro perSin = new PerSiniestro();
			PerEstatusTipoTramite perEstTipTra = new PerEstatusTipoTramite();
			PerDependencia perDepCrea = new PerDependencia();
			PerDependencia perDepEmi = new PerDependencia();
			PerTipoEnfermedad perTipoEnfermedad = new PerTipoEnfermedad();
			ExpUsuario expUsu = new ExpUsuario();
			TipoEnfermedad mesMedicinasCronicas = new TipoEnfermedad();
			EstatusTipoTramite estTipTra = new EstatusTipoTramite();
			Siniestro sin = new Siniestro();
			Dependencia depCrea = new Dependencia();
			Dependencia depEmi = new Dependencia();
			Usuario usuCreador = new Usuario();
			Usuario usuEmisor = new Usuario();
			String descripcion_usuario = "";
			String miFecha = "";
			if (map.getId() != 0) {
				// Verifico el siniestro
				map.setIdSiniestro(map.getId());
				sin = perSin.search(map);
				map.setAnioSiniestro(sin.getAnioSiniestro());
				// Datos del los usuarios para generar el pie de página
				depCrea = perDepCrea.search(Integer.parseInt(sin.getIdDependencia()));
				depEmi = perDepEmi.search(map.getIdDependencia());
				// Verifico los datos de los usuario creador
				try {
					usuCreador = expUsu.buscarDatosP(String.valueOf(sin.getIdUsuario()));
				} catch (Exception e) {
					usuCreador.setDescripcion_usuario("SIN NOMBRE DE USUARIO");
				}
				// Usuario emisor
				try {
					usuEmisor = expUsu.buscarDatosP(String.valueOf(map.getIdUsuario()));
				} catch (Exception e) {
					usuEmisor.setNombre("SIN NOMBRE DE USUARIO");
				}
				if (!map.getIdUsuario().equals(sin.getIdUsuario())) {
					if (depEmi.getId() == depCrea.getId()) {
						descripcion_usuario = usuCreador.getDescripcion_usuario() + "/ " + usuEmisor.getDescripcion_usuario() + " (" + depEmi.getDescripcion() + ")";
					} else {
						descripcion_usuario = usuCreador.getDescripcion_usuario() + " (" + depCrea.getDescripcion() + ")" + "/ " + usuEmisor.getDescripcion_usuario() + " (" + depEmi.getDescripcion() + ")";
					}
				} else {
					depEmi = perDepEmi.search(map.getIdDependencia());
					descripcion_usuario = usuEmisor.getDescripcion_usuario() + " (" + depEmi.getDescripcion() + ")";
				}
				log.info("mapa " + map);
			} else {
				try {
					usuEmisor = expUsu.buscarDatosP(String.valueOf(map.getIdUsuario()));
				} catch (Exception e) {
					usuEmisor.setNombre("SIN NOMBRE DE USUARIO");
				}
				try {
					depEmi = perDepEmi.search(usuEmisor.getIdDependencia());
				} catch (Exception e) {
					depEmi.setDescripcion("N/P");
				}
				descripcion_usuario = usuEmisor.getDescripcion_usuario() + " (" + depEmi.getDescripcion() + ")";
			}
			// Parametro para todos los reportes
			params.put("REPORT_LOCALE", new Locale("es"));
			// Parametros según cada reporte
			if (GenericAction.COD_TIPO_REPORTE_ORDEN_MEDICINA.equals(map.getIdReporte())) {
				String codigo = "";
				try {
					codigo = Encriptar.encriptar("OM" + descripcion_usuario + Utilidad.DateToString(new Date(), "ddMMyyyyHHmmss"));
				} catch (IllegalStateException e) {
					codigo = "Código no generado, Orden invalida";
				} catch (NoSuchAlgorithmException e) {
					codigo = "Código no generado, Orden invalida";
				}
				Date ahora = new Date();
			    SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
			    formateador.format(ahora);
			    log.info("----------------------->>>> "+formateador.format(ahora));
			    miFecha=formateador.format(ahora);
			    log.info("fecha de hoyyyyyyyyyyy " + miFecha);
				mesMedicinasCronicas= perTipoEnfermedad.searchmesCronica(miFecha);
				params.put("id", String.valueOf(map.getId()));
				params.put("cedula_usuario", map.getIdUsuario());
				params.put("nombres_usuario", descripcion_usuario);
				params.put("anio", String.valueOf(map.getAnioSiniestro()));
				params.put("codigo", codigo);
				params.put("descripcionMedicinas", map.getDescripcionMedicinas());
				 log.info("getidtipoenfermedad      " + sin.getTipoEnfermedad().getId());
				 log.info("getidmedicinas      " + sin.getTipoEnfermedad().getIdMedicinas());

				if (GenericAction.COD_TIPO_ENFERMEDAD_CRONICA.equals(String.valueOf(sin.getTipoEnfermedad().getId()))) {
					params.put("vaelmesNvo",mesMedicinasCronicas.getMesmedicina());
				}
				rep.setArchivoReporte("OrdenDeMedicinas.jasper"); // nombre del
			}
			if (GenericAction.COD_TIPO_REPORTE_CARTA_AVAL.equals(map.getIdReporte())) {
				// busco el reporte que corresponde segun su estatus
				map.setIdEstatus(sin.getEstatus().getId());
				map.setIdTipoTramite(sin.getTipoTramite().getId());
				estTipTra = perEstTipTra.searchByEstatusTipoTramite(map);
				rep.setArchivoReporte(estTipTra.getReporte() + ".jasper");
				params.put("id", String.valueOf(map.getId()));
				params.put("anio", String.valueOf(map.getAnioSiniestro()));
				params.put("descripcion_usuario", descripcion_usuario);
			}
			if (GenericAction.COD_TIPO_REPORTE_REEMBOLSO.equals(map.getIdReporte())) {
				map.setIdEstatus(sin.getEstatus().getId());
				map.setIdTipoTramite(sin.getTipoTramite().getId());
				estTipTra = perEstTipTra.searchByEstatusTipoTramite(map);
				rep.setArchivoReporte(estTipTra.getReporte() + ".jasper");
				params.put("id", String.valueOf(map.getId()));
				params.put("anio", String.valueOf(map.getAnioSiniestro()));
				params.put("nombres_usuario", descripcion_usuario);
				// rep.setArchivoReporte("ResRmbls.jasper"); // nombre del
				// archivo
			}
			if (GenericAction.COD_TIPO_REPORTE_RECHAZO_REEMBOLSO.equals(map.getIdReporte())) {
				map.setIdEstatus(sin.getEstatus().getId());
				map.setIdTipoTramite(sin.getTipoTramite().getId());
				estTipTra = perEstTipTra.searchByEstatusTipoTramite(map);
				rep.setArchivoReporte(estTipTra.getReporte() + ".jasper");
				params.put("id", String.valueOf(map.getId()));
				params.put("anio", String.valueOf(map.getAnioSiniestro()));
				params.put("descripcion_usuario", descripcion_usuario);
				// rep.setArchivoReporte("RechazoRmbls.jasper");
				log.info("usuariooooooo" + descripcion_usuario);
			}
			if (GenericAction.COD_TIPO_REPORTE_RECAUDOS_REEMBOLSO.equals(map.getIdReporte())) {
				map.setIdEstatus(sin.getEstatus().getId());
				map.setIdTipoTramite(sin.getTipoTramite().getId());
				estTipTra = perEstTipTra.searchByEstatusTipoTramite(map);
				rep.setArchivoReporte(estTipTra.getReporte() + ".jasper");
				params.put("id", String.valueOf(map.getId()));
				params.put("anio", String.valueOf(map.getAnioSiniestro()));
				params.put("descripcion_usuario", descripcion_usuario);
				// rep.setArchivoReporte("RechazoRmbls.jasper");
				log.info("usuariooooooo" + descripcion_usuario);
			}
			if (GenericAction.COD_TIPO_REPORTE_ESTA_REEMBOLSO.equals(map.getIdReporte())) {
				params.put("fechaInicio", map.getFechaInicio());
				params.put("fechaFin", map.getFechaFin());
				rep.setArchivoReporte("Estadistica_reembolso.jasper"); // nombre
				// del
				// archivo
			}
			if (GenericAction.COD_TIPO_REPORTE_USUARIO.equals(map.getIdReporte())) {
				params.put("fechaInicio", map.getFechaInicio());
				params.put("fechaFin", map.getFechaFin());
				rep.setArchivoReporte("Usuarios.jasper"); // nombre del archivo
			}
			if (GenericAction.COD_TIPO_ESTA_GENERAL.equals(map.getIdReporte())) {
				params.put("fechaInicio", map.getFechaInicio());
				params.put("fechaFin", map.getFechaFin());
				rep.setArchivoReporte("Estadistica.jasper"); // nombre del
				// archivo
			}
			if (GenericAction.COD_TIPO_REPORTE_NOTA_COBERTURA.equals(map.getIdReporte())) {
				// aqui es donde va a pintar la nota de cobertura
				List facturas = new ArrayList();
				PerFactura perFactura = new PerFactura();
				Factura factura = new Factura();
				Factura factura2 = new Factura();
				Mapa mapa = new Mapa();
				String fac = "";
				String pre = "Según los siguientes números de facturas: ";
				log.info("antes del try");
				try {
					mapa.setIdSiniestro(map.getId());
					mapa.setAnioSiniestro(map.getAnioSiniestro());
					mapa.setAlias(map.getAnioSiniestro());
					mapa.setSql(map.getSql());
					facturas = perFactura.listSearchIdSiniestro(mapa);
					
					log.info("facturas size " + facturas.size());
					
					if (facturas.size() == 1) {
						pre = "Según el siguiente número de factura: ";
						factura = (Factura) facturas.get(0);
						fac = factura.getNumeroFactura() + ".";
					}
					if (facturas.size() == 2) {
						factura = (Factura) facturas.get(0);
						factura2 = (Factura) facturas.get(1);
						fac = factura.getNumeroFactura() + " y " + factura2.getNumeroFactura() + ".";
					}
					if (facturas.size() > 2) {
						for (int i = 0; i < facturas.size(); i++) {
							factura = (Factura) facturas.get(i);
							log.info("i " + i);
							if (i + 1 == facturas.size()) {
								fac = fac + " y " + factura.getNumeroFactura() + ".";
							}
							if (i+2  == facturas.size()) {
								fac = fac + factura.getNumeroFactura();
							}
							if ( facturas.size()>=i+3 ) {
								fac = fac + factura.getNumeroFactura() + ", ";
							}
							
						}
					}
				} catch (PersonalNotFoundException e) {
					log.info("No tiene");
				}
				params.put("id", String.valueOf(map.getId()));
				params.put("anio", String.valueOf(map.getAnioSiniestro()));
				params.put("alias", String.valueOf(map.getAlias()));
				params.put("sql", String.valueOf(map.getSql()));
				params.put("descripcion_usuario", descripcion_usuario);
				
				//map.setSql("SELECT 	montos.*,	montos.nombres_beneficiario || ' ' || montos.apellidos_beneficiario as beneficiario,	montos.nombres || ' ' ||montos.apellidos as titular,	pot.*,	ts.id_tipo_siniestro,	ts.descripcion as tipo_siniestro,	te.descripcion as tipo_empleado, 	tt.descripcion as tipo_tramite,	p.descripcion || ' - '||t.descripcion as tratamiento,	tg.descripcion as tipo_gasto,	dc.descripcion as dependencia,	me.descripcion as motivo FROM	(SELECT				amparado.id_siniestro,				amparado.aniomes_codigo,				amparado.codigo_siniestro,				amparado.subcodigo_siniestro,				amparado.fecha_ocurrencia,				amparado.fecha_notificacion,				to_char(amparado.fecha_ocurrencia, 'dd/MM/yyyy') as fecha_ocurrencia2,				amparado.fecha_liquidacion,				amparado.fecha_ingreso,				amparado.id_patologia_organo_tratamiento,				amparado.id_proveedor,				amparado.id_tipo_tramite,				amparado.id_tipo_siniestro,				amparado.observacion,				amparado.id_tipo_empleado,				amparado.cedula_titular,				amparado.fecha_nacimiento,				amparado.sexo,				amparado.cedula_beneficiario,				amparado.sexo_beneficiario,				amparado.fecha_nacimiento_beneficiario,				amparado.parentesco,				amparado.nombres,				amparado.apellidos,				amparado.nombres_beneficiario,				amparado.apellidos_beneficiario,				amparado.id_factura,				amparado.nro_factura,				amparado.nro_control,				amparado.monto_factura,				coalesce(amparado.id_tipo_gasto,no_amparado.id_tipo_gasto_noamp) as id_tipo_gasto,				coalesce(amparado.monto_amparado, 0) as monto_amparado,				no_amparado.id_factura_noamp,				no_amparado.id_tipo_gasto_noamp,				coalesce(no_amparado.noamp, 0) as noamp	FROM		(SELECT			facturas.*,			detalle.*		FROM			(SELECT				s.id_siniestro,				s.aniomes_codigo,				s.codigo_siniestro,				s.subcodigo_siniestro,				s.fecha_ocurrencia,				s.fecha_notificacion,				s.fecha_liquidacion,				s.fecha_ingreso,				s.id_patologia_organo_tratamiento,				s.id_proveedor,				s.id_tipo_tramite,				s.id_tipo_siniestro,				s.observacion,				s.id_tipo_empleado,				s.cedula as cedula_titular,				s.fecha_nacimiento,				s.sexo,				s.cedula_beneficiario,				s.sexo_beneficiario,				s.fecha_nacimiento_beneficiario,				s.parentesco,				s.nombres,				s.apellidos,				s.nombres_beneficiario,				s.apellidos_beneficiario,				f.id_factura,				f.nro_factura,				f.nro_control,				f.monto_total as monto_factura							FROM				public.facturas"+map.getAlias()+" f,				public.siniestro0"+map.getAlias()+" s			WHERE				s.id_siniestro = f.id_siniestro AND				f.anio_siniestro = "+map.getAnioSiniestro()+" AND				s.anio_siniestro ="+map.getAnioSiniestro()+" AND				s.id_siniestro="+map.getId()+"				) AS facturas		LEFT JOIN			(SELECT				df.id_factura as id_facturadf,				df.id_tipo_gasto,				df.monto_amparado			FROM				public.detalles_factura0"+map.getAlias()+" df			WHERE				df.anio_siniestro = "+map.getAnioSiniestro()+" AND				df.id_tipo_gasto != 34 AND				df.id_tipo_gasto != 35				) as detalle		ON			facturas.id_factura = detalle.id_facturadf) as amparado	LEFT JOIN		(SELECT			detalle.*		FROM			(SELECT				f.*			FROM				public.facturas"+map.getAlias()+" f,				public.siniestro0"+map.getAlias()+" s			WHERE				s.id_siniestro = f.id_siniestro AND				f.anio_siniestro = "+map.getAnioSiniestro()+" AND				s.anio_siniestro = "+map.getAnioSiniestro()+" AND				s.id_siniestro="+map.getId()+"				) AS facturas		LEFT JOIN			(SELECT				df.id_factura as id_factura_noamp,				df.id_tipo_gasto as id_tipo_gasto_noamp,				df.monto_amparado as noamp			FROM				public.detalles_factura0"+map.getAlias()+" df			WHERE				df.anio_siniestro = "+map.getAnioSiniestro()+" AND				df.id_tipo_gasto != 34 AND				df.id_tipo_gasto = 35				) as detalle		ON			facturas.id_factura = detalle.id_factura_noamp) as no_amparado	ON		amparado.id_factura = no_amparado.id_factura_noamp) as montos,		public.patologia_organo_tratamiento pot,		public.patologias p,		public.tipo_siniestro ts,		public.tipo_empleado te,		public.tipo_tramite tt,		public.tratamiento t,		public.dependencia dc,		public.tipo_gasto tg,		public.motivo_estatus me WHERE		  montos.id_patologia_organo_tratamiento = pot.id_pat_org_trat_esp AND		  montos.id_tipo_gasto = tg.id_tipo_gasto AND		  pot.id_patologia = p.id_patologia AND		  ts.id_tipo_siniestro = montos.id_tipo_siniestro AND		  te.id_tipo_empleado = montos.id_tipo_empleado AND		  tt.id_tipo_tramite = montos.id_tipo_tramite AND		  t.id_tratamiento = pot.id_tratamiento AND		  dc.id = "+map.getIdDependencia()+" AND		  me.id_siniestro  = montos.id_siniestro AND		  me.fecha_fin is null ");
				
				if (map.getAlias() >= 100){
					map.setSql("SELECT 	montos.*,	montos.nombres_beneficiario || ' ' || montos.apellidos_beneficiario as beneficiario,	montos.nombres || ' ' ||montos.apellidos as titular,	pot.*,	ts.id_tipo_siniestro,	ts.descripcion as tipo_siniestro,	te.descripcion as tipo_empleado, upper (tcn.descripcion) as tipo_cobertura,	tt.descripcion as tipo_tramite,	p.descripcion || ' - '||t.descripcion as tratamiento,	tg.descripcion as tipo_gasto,	dc.descripcion as dependencia,	me.descripcion as motivo FROM	(SELECT				amparado.id_siniestro,				amparado.aniomes_codigo,				amparado.codigo_siniestro,				amparado.subcodigo_siniestro,				amparado.fecha_ocurrencia,				amparado.fecha_notificacion,				to_char(amparado.fecha_ocurrencia, 'dd/MM/yyyy') as fecha_ocurrencia2,				amparado.fecha_liquidacion,				amparado.fecha_ingreso,				amparado.id_patologia_organo_tratamiento,				amparado.id_proveedor,				amparado.id_tipo_tramite,				amparado.id_tipo_siniestro,				amparado.observacion,				amparado.id_tipo_empleado,	amparado.id_cobertura,				amparado.cedula_titular,				amparado.fecha_nacimiento,				amparado.sexo,				amparado.cedula_beneficiario,				amparado.sexo_beneficiario,				amparado.fecha_nacimiento_beneficiario,				amparado.parentesco,				amparado.nombres,				amparado.apellidos,				amparado.nombres_beneficiario,				amparado.apellidos_beneficiario,				amparado.id_factura,				amparado.nro_factura,				amparado.nro_control,				amparado.monto_factura,				coalesce(amparado.id_tipo_gasto,no_amparado.id_tipo_gasto_noamp) as id_tipo_gasto,				coalesce(amparado.monto_amparado, 0) as monto_amparado,				no_amparado.id_factura_noamp,				no_amparado.id_tipo_gasto_noamp,				coalesce(no_amparado.noamp, 0) as noamp	FROM		(SELECT			facturas.*,			detalle.*		FROM			(SELECT				s.id_siniestro,				s.aniomes_codigo,				s.codigo_siniestro,				s.subcodigo_siniestro,				s.fecha_ocurrencia,				s.fecha_notificacion,				s.fecha_liquidacion,				s.fecha_ingreso,				s.id_patologia_organo_tratamiento,				s.id_proveedor,				s.id_tipo_tramite,				s.id_tipo_siniestro,				s.observacion,				s.id_tipo_empleado,	s.id_cobertura,			s.cedula as cedula_titular,				s.fecha_nacimiento,				s.sexo,				s.cedula_beneficiario,				s.sexo_beneficiario,				s.fecha_nacimiento_beneficiario,				s.parentesco,				s.nombres,				s.apellidos,				s.nombres_beneficiario,				s.apellidos_beneficiario,				f.id_factura,				f.nro_factura,				f.nro_control,				f.monto_total as monto_factura							FROM				public.facturas"+map.getAlias()+" f,				public.siniestro"+map.getAlias()+" s			WHERE				s.id_siniestro = f.id_siniestro AND				f.anio_siniestro = "+map.getAnioSiniestro()+" AND				s.anio_siniestro ="+map.getAnioSiniestro()+" AND				s.id_siniestro="+map.getId()+"				) AS facturas		LEFT JOIN			(SELECT				df.id_factura as id_facturadf,				df.id_tipo_gasto,				df.monto_amparado			FROM				public.detalles_factura"+map.getAlias()+" df			WHERE				df.anio_siniestro = "+map.getAnioSiniestro()+" AND				df.id_tipo_gasto != 34 AND				df.id_tipo_gasto != 35				) as detalle		ON			facturas.id_factura = detalle.id_facturadf) as amparado	LEFT JOIN		(SELECT			detalle.*		FROM			(SELECT				f.*			FROM				public.facturas"+map.getAlias()+" f,				public.siniestro"+map.getAlias()+" s			WHERE				s.id_siniestro = f.id_siniestro AND				f.anio_siniestro = "+map.getAnioSiniestro()+" AND				s.anio_siniestro = "+map.getAnioSiniestro()+" AND				s.id_siniestro="+map.getId()+"				) AS facturas		LEFT JOIN			(SELECT				df.id_factura as id_factura_noamp,				df.id_tipo_gasto as id_tipo_gasto_noamp,				df.monto_amparado as noamp			FROM				public.detalles_factura"+map.getAlias()+" df			WHERE				df.anio_siniestro = "+map.getAnioSiniestro()+" AND				df.id_tipo_gasto != 34 AND				df.id_tipo_gasto = 35				) as detalle		ON			facturas.id_factura = detalle.id_factura_noamp) as no_amparado	ON		amparado.id_factura = no_amparado.id_factura_noamp) as montos,		public.patologia_organo_tratamiento pot,		public.patologias p,		public.tipo_siniestro ts,		public.tipo_empleado te,	public.tipo_cobertura tcn ,	public.cobertura cn,	public.tipo_tramite tt,		public.tratamiento t,		public.dependencia dc,		public.tipo_gasto tg,		public.motivo_estatus me WHERE		  montos.id_patologia_organo_tratamiento = pot.id_pat_org_trat_esp AND		  montos.id_tipo_gasto = tg.id_tipo_gasto AND		  pot.id_patologia = p.id_patologia AND		  ts.id_tipo_siniestro = montos.id_tipo_siniestro AND		  te.id_tipo_empleado = montos.id_tipo_empleado AND	  cn.id_cobertura=montos.id_cobertura AND 	tcn.id_tipo_cobertura = cn.id_tipo_cobertura  AND  tt.id_tipo_tramite = montos.id_tipo_tramite AND		  t.id_tratamiento = pot.id_tratamiento AND		  dc.id = "+map.getIdDependencia()+" AND		  me.id_siniestro  = montos.id_siniestro AND		  me.fecha_fin is null ");
					}
					else{
						map.setSql("SELECT 	montos.*,	montos.nombres_beneficiario || ' ' || montos.apellidos_beneficiario as beneficiario,	montos.nombres || ' ' ||montos.apellidos as titular,	pot.*,	ts.id_tipo_siniestro,	ts.descripcion as tipo_siniestro,	te.descripcion as tipo_empleado, upper (tcn.descripcion) as tipo_cobertura,	tt.descripcion as tipo_tramite,	p.descripcion || ' - '||t.descripcion as tratamiento,	tg.descripcion as tipo_gasto,	dc.descripcion as dependencia,	me.descripcion as motivo FROM	(SELECT				amparado.id_siniestro,				amparado.aniomes_codigo,				amparado.codigo_siniestro,				amparado.subcodigo_siniestro,				amparado.fecha_ocurrencia,				amparado.fecha_notificacion,				to_char(amparado.fecha_ocurrencia, 'dd/MM/yyyy') as fecha_ocurrencia2,				amparado.fecha_liquidacion,				amparado.fecha_ingreso,				amparado.id_patologia_organo_tratamiento,				amparado.id_proveedor,				amparado.id_tipo_tramite,				amparado.id_tipo_siniestro,				amparado.observacion,				amparado.id_tipo_empleado,	amparado.id_cobertura,				amparado.cedula_titular,				amparado.fecha_nacimiento,				amparado.sexo,				amparado.cedula_beneficiario,				amparado.sexo_beneficiario,				amparado.fecha_nacimiento_beneficiario,				amparado.parentesco,				amparado.nombres,				amparado.apellidos,				amparado.nombres_beneficiario,				amparado.apellidos_beneficiario,				amparado.id_factura,				amparado.nro_factura,				amparado.nro_control,				amparado.monto_factura,				coalesce(amparado.id_tipo_gasto,no_amparado.id_tipo_gasto_noamp) as id_tipo_gasto,				coalesce(amparado.monto_amparado, 0) as monto_amparado,				no_amparado.id_factura_noamp,				no_amparado.id_tipo_gasto_noamp,				coalesce(no_amparado.noamp, 0) as noamp	FROM		(SELECT			facturas.*,			detalle.*		FROM			(SELECT				s.id_siniestro,				s.aniomes_codigo,				s.codigo_siniestro,				s.subcodigo_siniestro,				s.fecha_ocurrencia,				s.fecha_notificacion,				s.fecha_liquidacion,				s.fecha_ingreso,				s.id_patologia_organo_tratamiento,				s.id_proveedor,				s.id_tipo_tramite,				s.id_tipo_siniestro,				s.observacion,				s.id_tipo_empleado,	s.id_cobertura,			s.cedula as cedula_titular,				s.fecha_nacimiento,				s.sexo,				s.cedula_beneficiario,				s.sexo_beneficiario,				s.fecha_nacimiento_beneficiario,				s.parentesco,				s.nombres,				s.apellidos,				s.nombres_beneficiario,				s.apellidos_beneficiario,				f.id_factura,				f.nro_factura,				f.nro_control,				f.monto_total as monto_factura							FROM				public.facturas0"+map.getAlias()+" f,				public.siniestro0"+map.getAlias()+" s			WHERE				s.id_siniestro = f.id_siniestro AND				f.anio_siniestro = "+map.getAnioSiniestro()+" AND				s.anio_siniestro ="+map.getAnioSiniestro()+" AND				s.id_siniestro="+map.getId()+"				) AS facturas		LEFT JOIN			(SELECT				df.id_factura as id_facturadf,				df.id_tipo_gasto,				df.monto_amparado			FROM				public.detalles_factura0"+map.getAlias()+" df			WHERE				df.anio_siniestro = "+map.getAnioSiniestro()+" AND				df.id_tipo_gasto != 34 AND				df.id_tipo_gasto != 35				) as detalle		ON			facturas.id_factura = detalle.id_facturadf) as amparado	LEFT JOIN		(SELECT			detalle.*		FROM			(SELECT				f.*			FROM				public.facturas"+map.getAlias()+" f,				public.siniestro0"+map.getAlias()+" s			WHERE				s.id_siniestro = f.id_siniestro AND				f.anio_siniestro = "+map.getAnioSiniestro()+" AND				s.anio_siniestro = "+map.getAnioSiniestro()+" AND				s.id_siniestro="+map.getId()+"				) AS facturas		LEFT JOIN			(SELECT				df.id_factura as id_factura_noamp,				df.id_tipo_gasto as id_tipo_gasto_noamp,				df.monto_amparado as noamp			FROM				public.detalles_factura0"+map.getAlias()+" df			WHERE				df.anio_siniestro = "+map.getAnioSiniestro()+" AND				df.id_tipo_gasto != 34 AND				df.id_tipo_gasto = 35				) as detalle		ON			facturas.id_factura = detalle.id_factura_noamp) as no_amparado	ON		amparado.id_factura = no_amparado.id_factura_noamp) as montos,		public.patologia_organo_tratamiento pot,		public.patologias p,		public.tipo_siniestro ts,		public.tipo_empleado te,	public.tipo_cobertura tcn ,	public.cobertura cn,	public.tipo_tramite tt,		public.tratamiento t,		public.dependencia dc,		public.tipo_gasto tg,		public.motivo_estatus me WHERE		  montos.id_patologia_organo_tratamiento = pot.id_pat_org_trat_esp AND		  montos.id_tipo_gasto = tg.id_tipo_gasto AND		  pot.id_patologia = p.id_patologia AND		  ts.id_tipo_siniestro = montos.id_tipo_siniestro AND		  te.id_tipo_empleado = montos.id_tipo_empleado AND	  cn.id_cobertura=montos.id_cobertura AND 	tcn.id_tipo_cobertura = cn.id_tipo_cobertura  AND  tt.id_tipo_tramite = montos.id_tipo_tramite AND		  t.id_tratamiento = pot.id_tratamiento AND		  dc.id = "+map.getIdDependencia()+" AND		  me.id_siniestro  = montos.id_siniestro AND		  me.fecha_fin is null ");
					}
				
				params.put("sql", String.valueOf(map.getSql()));
			
				params.put("facturas", pre+fac);
				rep.setArchivoReporte("NotaCobertura.jasper"); // nombre del
				// archivo
			}
			if (GenericAction.COD_TIPO_REPORTE_NOTA_COBERTURA_REEMBOLSO.equals(map.getIdReporte())) {
				//
				log.info("cedula " + sin.getCedula());
				ExpCuenta cb = new ExpCuenta();
				Cuenta cob = new Cuenta();
				String cuenta = "";
				try {
					cob = cb.buscarCuentaBeneficiario(sin.getCedula());
					log.info("cuentaliderdespues" + map.getAlias());
					cuenta = cob.getCuenta();
				}  catch (Exception e) {
					log.error("error", e);
					cuenta = "No disponible";
				}
				params.put("id_dependencia", String.valueOf(depEmi.getId()));
				params.put("cuenta", cuenta);
				params.put("id", String.valueOf(map.getId()));
				params.put("anio", String.valueOf(map.getAnioSiniestro()));
				params.put("alias", String.valueOf(map.getAlias()));
				params.put("descripcion_usuario", descripcion_usuario);
				
				if (map.getAlias() >= 100){
				map.setSql("SELECT 	montos.*,	montos.nombres_beneficiario || ' ' || montos.apellidos_beneficiario as beneficiario,	montos.nombres || ' ' ||montos.apellidos as titular,	pot.*,	ts.id_tipo_siniestro,	ts.descripcion as tipo_siniestro,	te.descripcion as tipo_empleado, upper (tcn.descripcion) as tipo_cobertura,	tt.descripcion as tipo_tramite,	p.descripcion || ' - '||t.descripcion as tratamiento,	tg.descripcion as tipo_gasto,	dc.descripcion as dependencia,	me.descripcion as motivo FROM	(SELECT				amparado.id_siniestro,				amparado.aniomes_codigo,				amparado.codigo_siniestro,				amparado.subcodigo_siniestro,				amparado.fecha_ocurrencia,				amparado.fecha_notificacion,				to_char(amparado.fecha_ocurrencia, 'dd/MM/yyyy') as fecha_ocurrencia2,				amparado.fecha_liquidacion,				amparado.fecha_ingreso,				amparado.id_patologia_organo_tratamiento,				amparado.id_proveedor,				amparado.id_tipo_tramite,				amparado.id_tipo_siniestro,				amparado.observacion,				amparado.id_tipo_empleado,	amparado.id_cobertura,				amparado.cedula_titular,				amparado.fecha_nacimiento,				amparado.sexo,				amparado.cedula_beneficiario,				amparado.sexo_beneficiario,				amparado.fecha_nacimiento_beneficiario,				amparado.parentesco,				amparado.nombres,				amparado.apellidos,				amparado.nombres_beneficiario,				amparado.apellidos_beneficiario,				amparado.id_factura,				amparado.nro_factura,				amparado.nro_control,				amparado.monto_factura,				coalesce(amparado.id_tipo_gasto,no_amparado.id_tipo_gasto_noamp) as id_tipo_gasto,				coalesce(amparado.monto_amparado, 0) as monto_amparado,				no_amparado.id_factura_noamp,				no_amparado.id_tipo_gasto_noamp,				coalesce(no_amparado.noamp, 0) as noamp	FROM		(SELECT			facturas.*,			detalle.*		FROM			(SELECT				s.id_siniestro,				s.aniomes_codigo,				s.codigo_siniestro,				s.subcodigo_siniestro,				s.fecha_ocurrencia,				s.fecha_notificacion,				s.fecha_liquidacion,				s.fecha_ingreso,				s.id_patologia_organo_tratamiento,				s.id_proveedor,				s.id_tipo_tramite,				s.id_tipo_siniestro,				s.observacion,				s.id_tipo_empleado,	s.id_cobertura,			s.cedula as cedula_titular,				s.fecha_nacimiento,				s.sexo,				s.cedula_beneficiario,				s.sexo_beneficiario,				s.fecha_nacimiento_beneficiario,				s.parentesco,				s.nombres,				s.apellidos,				s.nombres_beneficiario,				s.apellidos_beneficiario,				f.id_factura,				f.nro_factura,				f.nro_control,				f.monto_total as monto_factura							FROM				public.facturas"+map.getAlias()+" f,				public.siniestro"+map.getAlias()+" s			WHERE				s.id_siniestro = f.id_siniestro AND				f.anio_siniestro = "+map.getAnioSiniestro()+" AND				s.anio_siniestro ="+map.getAnioSiniestro()+" AND				s.id_siniestro="+map.getId()+"				) AS facturas		LEFT JOIN			(SELECT				df.id_factura as id_facturadf,				df.id_tipo_gasto,				df.monto_amparado			FROM				public.detalles_factura"+map.getAlias()+" df			WHERE				df.anio_siniestro = "+map.getAnioSiniestro()+" AND				df.id_tipo_gasto != 34 AND				df.id_tipo_gasto != 35				) as detalle		ON			facturas.id_factura = detalle.id_facturadf) as amparado	LEFT JOIN		(SELECT			detalle.*		FROM			(SELECT				f.*			FROM				public.facturas"+map.getAlias()+" f,				public.siniestro"+map.getAlias()+" s			WHERE				s.id_siniestro = f.id_siniestro AND				f.anio_siniestro = "+map.getAnioSiniestro()+" AND				s.anio_siniestro = "+map.getAnioSiniestro()+" AND				s.id_siniestro="+map.getId()+"				) AS facturas		LEFT JOIN			(SELECT				df.id_factura as id_factura_noamp,				df.id_tipo_gasto as id_tipo_gasto_noamp,				df.monto_amparado as noamp			FROM				public.detalles_factura"+map.getAlias()+" df			WHERE				df.anio_siniestro = "+map.getAnioSiniestro()+" AND				df.id_tipo_gasto != 34 AND				df.id_tipo_gasto = 35				) as detalle		ON			facturas.id_factura = detalle.id_factura_noamp) as no_amparado	ON		amparado.id_factura = no_amparado.id_factura_noamp) as montos,		public.patologia_organo_tratamiento pot,		public.patologias p,		public.tipo_siniestro ts,		public.tipo_empleado te,	public.tipo_cobertura tcn ,	public.cobertura cn,	public.tipo_tramite tt,		public.tratamiento t,		public.dependencia dc,		public.tipo_gasto tg,		public.motivo_estatus me WHERE		  montos.id_patologia_organo_tratamiento = pot.id_pat_org_trat_esp AND		  montos.id_tipo_gasto = tg.id_tipo_gasto AND		  pot.id_patologia = p.id_patologia AND		  ts.id_tipo_siniestro = montos.id_tipo_siniestro AND		  te.id_tipo_empleado = montos.id_tipo_empleado AND	  cn.id_cobertura=montos.id_cobertura AND 	tcn.id_tipo_cobertura = cn.id_tipo_cobertura  AND  tt.id_tipo_tramite = montos.id_tipo_tramite AND		  t.id_tratamiento = pot.id_tratamiento AND		  dc.id = "+map.getIdDependencia()+" AND		  me.id_siniestro  = montos.id_siniestro AND		  me.fecha_fin is null ");
				}
				else{
					map.setSql("SELECT 	montos.*,	montos.nombres_beneficiario || ' ' || montos.apellidos_beneficiario as beneficiario,	montos.nombres || ' ' ||montos.apellidos as titular,	pot.*,	ts.id_tipo_siniestro,	ts.descripcion as tipo_siniestro,	te.descripcion as tipo_empleado, upper (tcn.descripcion) as tipo_cobertura,	tt.descripcion as tipo_tramite,	p.descripcion || ' - '||t.descripcion as tratamiento,	tg.descripcion as tipo_gasto,	dc.descripcion as dependencia,	me.descripcion as motivo FROM	(SELECT				amparado.id_siniestro,				amparado.aniomes_codigo,				amparado.codigo_siniestro,				amparado.subcodigo_siniestro,				amparado.fecha_ocurrencia,				amparado.fecha_notificacion,				to_char(amparado.fecha_ocurrencia, 'dd/MM/yyyy') as fecha_ocurrencia2,				amparado.fecha_liquidacion,				amparado.fecha_ingreso,				amparado.id_patologia_organo_tratamiento,				amparado.id_proveedor,				amparado.id_tipo_tramite,				amparado.id_tipo_siniestro,				amparado.observacion,				amparado.id_tipo_empleado,	amparado.id_cobertura,				amparado.cedula_titular,				amparado.fecha_nacimiento,				amparado.sexo,				amparado.cedula_beneficiario,				amparado.sexo_beneficiario,				amparado.fecha_nacimiento_beneficiario,				amparado.parentesco,				amparado.nombres,				amparado.apellidos,				amparado.nombres_beneficiario,				amparado.apellidos_beneficiario,				amparado.id_factura,				amparado.nro_factura,				amparado.nro_control,				amparado.monto_factura,				coalesce(amparado.id_tipo_gasto,no_amparado.id_tipo_gasto_noamp) as id_tipo_gasto,				coalesce(amparado.monto_amparado, 0) as monto_amparado,				no_amparado.id_factura_noamp,				no_amparado.id_tipo_gasto_noamp,				coalesce(no_amparado.noamp, 0) as noamp	FROM		(SELECT			facturas.*,			detalle.*		FROM			(SELECT				s.id_siniestro,				s.aniomes_codigo,				s.codigo_siniestro,				s.subcodigo_siniestro,				s.fecha_ocurrencia,				s.fecha_notificacion,				s.fecha_liquidacion,				s.fecha_ingreso,				s.id_patologia_organo_tratamiento,				s.id_proveedor,				s.id_tipo_tramite,				s.id_tipo_siniestro,				s.observacion,				s.id_tipo_empleado,	s.id_cobertura,			s.cedula as cedula_titular,				s.fecha_nacimiento,				s.sexo,				s.cedula_beneficiario,				s.sexo_beneficiario,				s.fecha_nacimiento_beneficiario,				s.parentesco,				s.nombres,				s.apellidos,				s.nombres_beneficiario,				s.apellidos_beneficiario,				f.id_factura,				f.nro_factura,				f.nro_control,				f.monto_total as monto_factura							FROM				public.facturas0"+map.getAlias()+" f,				public.siniestro0"+map.getAlias()+" s			WHERE				s.id_siniestro = f.id_siniestro AND				f.anio_siniestro = "+map.getAnioSiniestro()+" AND				s.anio_siniestro ="+map.getAnioSiniestro()+" AND				s.id_siniestro="+map.getId()+"				) AS facturas		LEFT JOIN			(SELECT				df.id_factura as id_facturadf,				df.id_tipo_gasto,				df.monto_amparado			FROM				public.detalles_factura0"+map.getAlias()+" df			WHERE				df.anio_siniestro = "+map.getAnioSiniestro()+" AND				df.id_tipo_gasto != 34 AND				df.id_tipo_gasto != 35				) as detalle		ON			facturas.id_factura = detalle.id_facturadf) as amparado	LEFT JOIN		(SELECT			detalle.*		FROM			(SELECT				f.*			FROM				public.facturas"+map.getAlias()+" f,				public.siniestro0"+map.getAlias()+" s			WHERE				s.id_siniestro = f.id_siniestro AND				f.anio_siniestro = "+map.getAnioSiniestro()+" AND				s.anio_siniestro = "+map.getAnioSiniestro()+" AND				s.id_siniestro="+map.getId()+"				) AS facturas		LEFT JOIN			(SELECT				df.id_factura as id_factura_noamp,				df.id_tipo_gasto as id_tipo_gasto_noamp,				df.monto_amparado as noamp			FROM				public.detalles_factura0"+map.getAlias()+" df			WHERE				df.anio_siniestro = "+map.getAnioSiniestro()+" AND				df.id_tipo_gasto != 34 AND				df.id_tipo_gasto = 35				) as detalle		ON			facturas.id_factura = detalle.id_factura_noamp) as no_amparado	ON		amparado.id_factura = no_amparado.id_factura_noamp) as montos,		public.patologia_organo_tratamiento pot,		public.patologias p,		public.tipo_siniestro ts,		public.tipo_empleado te,	public.tipo_cobertura tcn ,	public.cobertura cn,	public.tipo_tramite tt,		public.tratamiento t,		public.dependencia dc,		public.tipo_gasto tg,		public.motivo_estatus me WHERE		  montos.id_patologia_organo_tratamiento = pot.id_pat_org_trat_esp AND		  montos.id_tipo_gasto = tg.id_tipo_gasto AND		  pot.id_patologia = p.id_patologia AND		  ts.id_tipo_siniestro = montos.id_tipo_siniestro AND		  te.id_tipo_empleado = montos.id_tipo_empleado AND	  cn.id_cobertura=montos.id_cobertura AND 	tcn.id_tipo_cobertura = cn.id_tipo_cobertura  AND  tt.id_tipo_tramite = montos.id_tipo_tramite AND		  t.id_tratamiento = pot.id_tratamiento AND		  dc.id = "+map.getIdDependencia()+" AND		  me.id_siniestro  = montos.id_siniestro AND		  me.fecha_fin is null ");
				}
				params.put("sql", String.valueOf(map.getSql()));
				rep.setArchivoReporte("NotadeCoberturaRmbls.jasper"); // nombre
																		// del
				// archivo
			}
			if (GenericAction.COD_TIPO_REPORTE_NOTA_TECNICA_USUARIO_ACTIVO.equals(map.getIdReporte())) {
				params.put("id", String.valueOf(map.getId()));
				params.put("anio", String.valueOf(map.getAnioSiniestro()));
				params.put("nombres_usuario", descripcion_usuario);
				params.put("cedula_usuario", map.getIdUsuario());
				rep.setArchivoReporte("NotaTecnicaUsuarioActivo.jasper"); // nombre
																			// del
				// archivo
			}
			if (GenericAction.COD_TIPO_REPORTE_NOTA_TECNICA_SINIESTRO.equals(map.getIdReporte())) {
				params.put("id", String.valueOf(map.getId()));
				params.put("anio", String.valueOf(map.getAnioSiniestro()));
				params.put("nombres_usuario", descripcion_usuario);
				params.put("cedula_usuario", map.getIdUsuario());
				rep.setArchivoReporte("NotaTecnicaSiniestro.jasper"); // nombre
																		// del
				// archivo
			}
			if (GenericAction.COD_TIPO_REPORTE_NOTA_TECNICA_SINIESTRORMBLS.equals(map.getIdReporte())) {
				params.put("id", String.valueOf(map.getId()));
				params.put("anio", String.valueOf(map.getAnioSiniestro()));
				params.put("nombres_usuario", descripcion_usuario);
				params.put("cedula_usuario", map.getIdUsuario());
				rep.setArchivoReporte("NotaTecnicaSiniestroRmbls.jasper"); // nombre
																			// del
				// archivo
			}
			if (GenericAction.COD_TIPO_REPORTE_NOTA_COBERTURA_EGRESO.equals(map.getIdReporte())) {
				params.put("id", String.valueOf(map.getId()));
				params.put("anio", String.valueOf(map.getAnioSiniestro()));
				params.put("descripcion_usuario", descripcion_usuario);
				rep.setArchivoReporte("NotaCoberturaEgreso.jasper"); // nombre
																		// del
				// archivo
			}
			if (GenericAction.COD_TIPO_REPORTE_LIQUIDACION.equals(map.getIdReporte())) {
				params.put("idSiniestro", map.getIdSiniestro());
				params.put("anio", map.getAnioSiniestro());
				params.put("descripcion_usuario", descripcion_usuario);
				params.put("fecha", map.getFechaInicio());
				log.info(sin.getTipoTramite().getId() + "que es");
				if (sin.getTipoTramite().getId()==6){
					rep.setArchivoReporte("liquidacionMedicina.jasper");
				}else{
					rep.setArchivoReporte("liquidacion.jasper");
				}
				 // nombre del
				// archivo
			}
			if (GenericAction.COD_TIPO_REPORTE_LIQUIDACION_POR_FACTURA.equals(map.getIdReporte())) {
				params.put("idSiniestro", map.getIdSiniestro());
				params.put("idFactura", map.getIdFactura());
				params.put("anio", map.getAnioSiniestro());
				params.put("descripcion_usuario", descripcion_usuario);
				params.put("fecha", map.getFechaInicio());
				rep.setArchivoReporte("liquidacionPorFactura.jasper"); // nombre del
				// archivo
			}
			if (GenericAction.COD_TIPO_REPORTE_NOTAMEDICA.equals(map.getIdReporte())) {
				PerNotaTecnica pnt = new PerNotaTecnica();
				NotaTecnica nt = new NotaTecnica();
				map.setIdNota(Integer.parseInt(map.getIdNotaMedica()));
				try {
					nt = pnt.searchMedica(map);
				} catch (Exception e) {
					log.error("error", e);
				}
				params.put("idSiniestro", map.getIdSiniestro());
				params.put("anio", String.valueOf(map.getAnioSiniestro()));
				params.put("descripcion_usuario", descripcion_usuario);
				params.put("medico", nt.getDesUsuario());
				// params.put("fecha", map.getFechaInicio());
				params.put("idNotaMedica", map.getIdNotaMedica());
				rep.setArchivoReporte("nota_medica.jasper"); // nombre del
				// archivo
			}
			if (GenericAction.COD_TIPO_REPORTE_CARTA_ASEGURABILIDAD.equals(map.getIdReporte())) {
				log.info("cedula " + map.getCedula());
				PerCobertura perCobertura = new PerCobertura();
				Cobertura cob = new Cobertura();
				String codigo = "";
				try {
					codigo = Encriptar.encriptar(map.getCedula() + Utilidad.DateToString(new Date(), "ddMMyyyyHHmmss"));
				} catch (IllegalStateException e) {
					codigo = "Código no generado, Carta de asegurabilidad invalida";
				} catch (NoSuchAlgorithmException e) {
					codigo = "Código no generado, Carta de asegurabilidad invalida";
				}
				try {
					cob = perCobertura.searchCobertura1(GenericAction.COD_TIPO_COBERTURA_EMERGENCIA);
				} catch (Exception e) {
					log.error("error", e);
				}
				params.put("cedula", map.getCedula());
				params.put("descripcion_usuario", descripcion_usuario);
				params.put("monto_cobertura", String.valueOf(cob.getMonto()));
				params.put("token", codigo);
				rep.setArchivoReporte("CartaAsegurabilidad.jasper"); // nombre
																		// del
			}
			if (GenericAction.COD_TIPO_REPORTE_FINIQUITO_COBERTURA.equals(map.getIdReporte())) {
				String codigo = "";
				try {
					codigo = Encriptar.encriptar(String.valueOf(map.getId()) + Utilidad.DateToString(new Date(), "ddMMyyyyHHmmss"));
				} catch (IllegalStateException e) {
					codigo = "Código no generado, Carta de asegurabilidad invalida";
				} catch (NoSuchAlgorithmException e) {
					codigo = "Código no generado, Carta de asegurabilidad invalida";
				}
				params.put("id", String.valueOf(map.getId()));
				params.put("anio", String.valueOf(map.getAnioSiniestro()));
				params.put("descripcion_usuario", descripcion_usuario);
				params.put("token", codigo);
				rep.setArchivoReporte("FiniquitoCobertura.jasper"); // nombre
																	// del
				// archivo
			}
			rep.setMapa(params);
			return rep;
		} finally {
			if (cnx != null)
				try {
					Conexion.closeConexion(cnx);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public static ArrayList buscarDatosReporte(int id) throws PersonalException, PersonalNotFoundException, ClassNotFoundException, IOException {
		Connection cnx = null;
		try {
			try {
				cnx = Conexion.getConexion();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PerReporte perReporte = new PerReporte(cnx);
			return perReporte.buscarDatosReporte(id);
		} catch (PersonalCriticalException e) {
			e.printStackTrace();
		} finally {
			if (cnx != null)
				try {
					Conexion.closeConexion(cnx);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return null;
	}
}
