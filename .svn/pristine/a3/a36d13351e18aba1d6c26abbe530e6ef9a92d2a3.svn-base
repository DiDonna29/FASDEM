/*
 * creado el 16/06/2003
 * autor lmontanez
 */
package ve.gob.dem.framework.recursos;

import java.io.Serializable;
import java.util.Hashtable;


/**
 * Clase que se encarga del manejo de los mensajes de errores
 * en el sistema
 * @author lmontanez
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class Errores implements Serializable {
	/**
   * 
   */
  private static final long serialVersionUID = 3482687715644739848L;
	
	private static Hashtable listaErrores 	= null;
	private static String SEPARADOR_LINEA	= null;
	public static String ERR_PROYECTO_NOFOUND= null;;
	private String codigo					= null;
	private String nombreClase				= null;
	private String nombreMetodo				= null;
	private String mensaje					= null;


	//--------------------------------------------------------------------------------
	//Definici�n de codigo de errores
	//nombre maximo 20 caracteres, no mas de dos separadores (_)
	//	--------------------------------------------------------------------------------
	public static final String ERR_NODOCUMENTADO		= "1000";
	public static final String ERR_COMUN_SERVERDATA		= "1001";
	public static final String ERR_ARG_INVALIDOS		= "1002";
	public static final String ERR_ESTADO_NOFOUND		= "1003";
	public static final String ERR_COOKIE_NOFOUND		= "1004";
	public static final String ERR_CONVERSION_DATE		= "1005";
	public static final String ERR_USUARIO_NOFOUND		= "1006";
	public static final String ERR_SESIONES_NOFOUND		= "1007";
	public static final String ERR_PASSWD_NOFOUND		= "1008";
	public static final String ERR_DEPEN_NOFOUND		= "1009";
	public static final String ERR_NODO_NOFOUND		    = "1010";
	public static final String ERR_ENTIDAD_NOFOUND		= "1011";
	public static final String ERR_TIPOSOLI_NOFOUND	    = "1012";
	public static final String ERR_ESTASOLI_NOFOUND	    = "1013";
	public static final String ERR_UEL_NOFOUND	    	= "1014";
	public static final String ERR_CONCEPTO_NOFOUND    	= "1015";
	public static final String ERR_SOLI_NOFOUND		    = "1016";
	public static final String ERR_TIPOCOMP_NOFOUND	    = "1017";
	public static final String ERR_COMP_NOFOUND	        = "1018";
	public static final String ERR_IDACCESO_NOFOUND	    = "1019";
	public static final String ERR_TIPOACC_NOFOUND	    = "1020";
	public static final String ERR_ACC_NOFOUND	 	  	= "1021";
	public static final String ERR_IDCONCEPTOSOL_NOFOUND= "1022";
	public static final String ERR_UBICACION_NOFOUND	= "1023";
	public static final String ERR_EMPTY_REPORT			= "1024";
	public static final String ERR_SESION_VENCIDA     = "1025";
	public static final String ERR_NOTFOUND	 	  	= "1026";
	public static final String ERR_ELEMENTO_NOFOUND		= "0090";
	

	//	--------------------------------------------------------------------------------
	//Descripci�n de los codigos de errores
	//nombre maximo 23 caracteres, no mas de dos separadores (_)
	//	--------------------------------------------------------------------------------
	public static final String DES_ERR_NODOCUMENTADO	= "Error no documentado.";
	public static final String DES_ERR_COMUN_SERVERDATA	= "Error en la comunicaci�n con el servidor de datos.";
	public static final String DES_ERR_ARG_INVALIDOS	= "Argumentos inv�lidos.";
    public static final String DES_ERR_ESTADO_NOFOUND   = "Estado No Encontrado";
	public static final String DES_ERR_COOKIE_NOFOUND	= "Cookie no encontrado.";
	public static final String DES_ERR_CONVERSION_DATE	= "Conversi�n de fecha inv�lida.";
	public static final String DES_ERR_USUARIO_NOFOUND	= "Usuario(s) no encontrado(s).";
	public static final String DES_ERR_SESIONES_NOFOUND	= "Sesion(es) de usuario no encontrada(s).";
	public static final String DES_ERR_PASSWD_NOFOUND	= "Password de acceso inv�lido.";
	public static final String DES_ERR_DEPEN_NOFOUND	= "Dependencia no encontrada";
	public static final String DES_ERR_NODO_NOFOUND     = "Nodo no Encontrado";
	public static final String DES_ERR_ENTIDAD_NOFOUND	= "El usuario no posee Entidades Asociadas";
	public static final String DES_ERR_TIPOSOLI_NOFOUND	= "El Tipo de solicitud no esta registrado";
	public static final String DES_ERR_ESTASOLI_NOFOUND	= "El Estatus de solicitud no esta registrado";
	public static final String DES_ERR_UEL_NOFOUND		= "La UEL no esta registrado";
	public static final String DES_ERR_CONCEPTO_NOFOUND	= "El concepto no esta registrado";
	public static final String DES_ERR_SOLI_NOFOUND		= "La solicitud indicada no esta registrado";
	public static final String DES_ERR_TIPOCOMP_NOFOUND	= "El Tipo de comprobante no esta registrado";
	public static final String DES_ERR_COMP_NOFOUND	    = "El comprobante no esta registrado";
	public static final String DES_ERR_IDACCESO_NOFOUND	= "Error al obtener el Id de acceso";
	public static final String DES_ERR_TIPOACC_NOFOUND	= "Tipo de Acci�n en traza no encontrado o no perteneciente a este proyecto";
	public static final String DES_ERR_ACC_NOFOUND		= "Acci�n en traza no encontrada";
	public static final String DES_ERR_IDCONCEPTOSOL_NOFOUND		= "Acci�n en traza no encontrada";
	public static final String DES_ERR_UBICACION_NOFOUND= "Ubicaci�n no Encontrada.";	
	public static final String DES_ERR_EMPTY_REPORT		= "El Reporte Solicitado no ha devuelto informaci�n";
	public static final String DES_SESION_VENCIDA    = "Se ha vencido su sesión. Por favor, reingrese al sistema.";
	public static final String DES_ERR_NOTFOUND = "Elemento no encontrado";
        static{
		SEPARADOR_LINEA=System.getProperty("line.separator");
		listaErrores = new Hashtable();
		listaErrores.put(ERR_NODOCUMENTADO,    DES_ERR_NODOCUMENTADO);
		listaErrores.put(ERR_COMUN_SERVERDATA, DES_ERR_COMUN_SERVERDATA);
		listaErrores.put(ERR_ARG_INVALIDOS,    DES_ERR_ARG_INVALIDOS);
        listaErrores.put(ERR_ESTADO_NOFOUND,   DES_ERR_ESTADO_NOFOUND);
		listaErrores.put(ERR_COOKIE_NOFOUND,   DES_ERR_COOKIE_NOFOUND);
		listaErrores.put(ERR_CONVERSION_DATE,  DES_ERR_CONVERSION_DATE);
		listaErrores.put(ERR_USUARIO_NOFOUND,  DES_ERR_USUARIO_NOFOUND);
		listaErrores.put(ERR_SESIONES_NOFOUND, DES_ERR_SESIONES_NOFOUND);
		listaErrores.put(ERR_PASSWD_NOFOUND,   DES_ERR_PASSWD_NOFOUND);
		listaErrores.put(ERR_DEPEN_NOFOUND,    DES_ERR_DEPEN_NOFOUND);
		listaErrores.put(ERR_NODO_NOFOUND,     DES_ERR_NODO_NOFOUND);
		listaErrores.put(ERR_ENTIDAD_NOFOUND,  DES_ERR_ENTIDAD_NOFOUND);
		listaErrores.put(ERR_TIPOSOLI_NOFOUND, DES_ERR_TIPOSOLI_NOFOUND);
		listaErrores.put(ERR_ESTASOLI_NOFOUND, DES_ERR_ESTASOLI_NOFOUND);
		listaErrores.put(ERR_UEL_NOFOUND,      DES_ERR_UEL_NOFOUND);
		listaErrores.put(ERR_CONCEPTO_NOFOUND, DES_ERR_CONCEPTO_NOFOUND);
		listaErrores.put(ERR_SOLI_NOFOUND, 	   DES_ERR_SOLI_NOFOUND);
		listaErrores.put(ERR_TIPOCOMP_NOFOUND, DES_ERR_TIPOCOMP_NOFOUND);
		listaErrores.put(ERR_COMP_NOFOUND,     DES_ERR_COMP_NOFOUND);
		listaErrores.put(ERR_IDACCESO_NOFOUND, DES_ERR_IDACCESO_NOFOUND);
		listaErrores.put(ERR_TIPOACC_NOFOUND,  DES_ERR_TIPOACC_NOFOUND);
		listaErrores.put(ERR_ACC_NOFOUND, 	   DES_ERR_ACC_NOFOUND);
		listaErrores.put(ERR_IDCONCEPTOSOL_NOFOUND, 	   DES_ERR_IDCONCEPTOSOL_NOFOUND);
		listaErrores.put(ERR_UBICACION_NOFOUND,DES_ERR_UBICACION_NOFOUND);		
		listaErrores.put(ERR_EMPTY_REPORT,     DES_ERR_EMPTY_REPORT);		
		listaErrores.put(ERR_SESION_VENCIDA,   DES_SESION_VENCIDA);
		listaErrores.put(ERR_NOTFOUND,         DES_ERR_NOTFOUND);
		
	}
	
	
	/**
	 * Constructor
	 *
	 */
	public Errores() {
		this("", "", "", "");
	}

	public Errores(String argCodigo,
					String argMensaje,
					String argNombreClase,
					String argNombreMetodo) {
		super();
		this.codigo = argCodigo;
		this.mensaje = argMensaje;
		this.nombreClase = argNombreClase;
		this.nombreMetodo = argNombreMetodo;
	}

	public Errores(String argCodigo,
					String argMensaje){
		this(argCodigo, argMensaje, "" ,"");
	}

	public Errores(String argMensaje){
		this("", argMensaje, "", "");
	}

	/**
	 * Retorna el c�digo del Error.
	 * @return String
	 */
	public String getCodigo() {
		return codigo;
	}


	/**
	 * Retorna el Mensaje (tira de Excepci�n), si existe, del error.
	 * @return String
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Retorna el nombre del clase donde se origino el error.
	 * @return String
	 */
	public String getNombreClase() {
		return nombreClase;
	}

	/**
	 * Retorna el nombre del metodo donde se origino el error.
	 * @return String
	 */
	public String getNombreMetodo() {
		return nombreMetodo;
	}

	/**
	 * @param string
	 */
	public void setCodigo(String string) {
		codigo = string;
	}


	/**
	 * @param string
	 */
	public void setMensaje(String string) {
		mensaje = string;
	}

	/**
	 * @param string
	 */
	public void setNombreClase(String string) {
		nombreClase = string;
	}

	/**
	 * @param string
	 */
	public void setNombreMetodo(String string) {
		nombreMetodo = string;
	}

	/**
	 * @param string
	 */
	public static String buscarDescripcion(String argCodigo){
		String descripcion = (String)listaErrores.get(argCodigo);
		//if (descripcion==null) throw new PersonalNotFoundException(ERR_NODOCUMENTADO, DES_ERR_NODOCUMENTADO);
		return descripcion;

	}

	public String getMensajeError(){
		return "[" + this.codigo + "] " + this.mensaje + " Clase:[" + this.nombreClase + "] Metodo:[" + this.nombreMetodo + "]";
	}
	/**
	 * Retorna el error en String
	 */
	public String toString(){
		return ((this.codigo!=null && !this.codigo.trim().equals(""))? "["+ this.codigo +"]" + SEPARADOR_LINEA:"") +
				((this.mensaje!=null && !this.mensaje.trim().equals(""))? "[" + this.mensaje + "]" + SEPARADOR_LINEA: "") +
				((this.nombreClase!=null && !this.nombreClase.trim().equals(""))? " Clase:" + this.mensaje + SEPARADOR_LINEA: "") +
				((this.nombreMetodo!=null && !this.nombreMetodo.trim().equals(""))? " Metodo:" + this.nombreMetodo: "");
	}
}
