package tftp;

public class PaqueteTFTP {
	
	//Definicion de OPCODE
	static final char OPCODE_RRQ=0x01;
	static final char OPCODE_WRQ=0x02;
	static final char OPCODE_DATA=0x03;
	static final char OPCODE_ACK=0x04;
	static final char OPCODE_ERROR=0x05;
	
	//Definicion de Errores
	static final String ERROR_TFTP_1="No definido";
	static final String ERROR_TFTP_2="Fichero no encontrado";
	static final String ERROR_TFTP_3="Violacion de permisos";
	static final String ERROR_TFTP_4="Disco lleno";
	static final String ERROR_TFTP_5="Operacion ilegal de TFTP";
	static final String ERROR_TFTP_6="Modo de transferencia desconocido";
	static final String ERROR_TFTP_7="Usuario inexistente";
	
	//Definicion de un metodo sobre la estructura Peticion para Lectura y Escritura de datos (RRQ/WRQ)
	public String PeticionEscrituraLectura(char opcode, String nomFichero, String modo){
		return opcode+nomFichero+0x0+modo+0x0;
	}
	
	//Definicion de la estructura del paquete para envio de datos (DATA)
	public String EnviarDatos(char numeroBlock, String datos){
		return OPCODE_DATA+numeroBlock+datos;
	}
	
	//Definicion de la estructura del Contador (ACK)
	public String Contador(char numeroBlock){
		return OPCODE_ACK+numeroBlock+"";
	}
	
	//Definicion de la estructura Error
	public String TipoError(short numeroError){
		String mensajeError;
		switch(numeroError){
			case 1:
				mensajeError=ERROR_TFTP_1;
				break;
			case 2:
				mensajeError=ERROR_TFTP_2;
				break;
			case 3:
				mensajeError=ERROR_TFTP_3;
				break;
			case 4:
				mensajeError=ERROR_TFTP_4;
				break;
			case 5:
				mensajeError=ERROR_TFTP_5;
				break;
			case 6:
				mensajeError=ERROR_TFTP_6;
				break;
			case 7:
				mensajeError=ERROR_TFTP_7;
				break;
			default:
				mensajeError="Error no definido";
		}
		return OPCODE_ERROR+numeroError+mensajeError+0x0;
	}
	
}
