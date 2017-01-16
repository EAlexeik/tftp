package tftp;

import java.util.ArrayList;

public class PaqueteTFTP {
	
	//Definicion de espacio
	static final byte CERO=0x00;
	
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
		return opcode+nomFichero+CERO+modo+CERO;
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
		return OPCODE_ERROR+numeroError+mensajeError+CERO;
	}
	
	//Metodo de desgloce en estructura de cada Opcode
	public ArrayList RecibirPeticion(String peticion){
		ArrayList<Character> aux=new ArrayList<Character>();
		ArrayList arreglo=new ArrayList();
		char opcode=peticion.charAt(0);
		int tamanio;
		arreglo.add(opcode);
		
		switch(opcode){
		//Peticion de lectura y escritura
		case 0x01: case 0x02:
			ArrayList<Byte> bytesFichero=new ArrayList<Byte>();
			int encontrado=0;
			byte[] b=peticion.getBytes();
			tamanio=b.length;
			for(int i=2;i<tamanio;i++){
				bytesFichero.add(b[i]);
				if(b[i]==CERO){
					encontrado=i;
					break;
				}
			}
			bytesFichero.clear();
			arreglo.add(bytesFichero.toString());
			for(int i=encontrado;i<tamanio;i++){
				bytesFichero.add(b[i]);
				if(b[i]==CERO){
					encontrado=i;
					break;
				}
			}
			bytesFichero.clear();
			arreglo.add(bytesFichero.toString());
			break;
		//Recepcion de datos
		case 0x03:
			char[] charACK=peticion.toCharArray();
			tamanio=charACK.length;
			arreglo.add(charACK[1]);
			for(int i=2;i<tamanio;i++){
				aux.add(charACK[i]);
			}
			arreglo.add(charACK.toString());
			break;
		//Recepcion de contador
		case 0x04:
			arreglo.add(peticion.charAt(1));
			break;
		//Lectura de error
		case 0x05:
			char[] charError=peticion.toCharArray();
			tamanio=charError.length;
			arreglo.add(charError[1]);
			for(int i=2;i<tamanio;i++){
				aux.add(charError[i]);
			}
			arreglo.add(charError.toString());
			break;
		}
		return arreglo;
	}
}
