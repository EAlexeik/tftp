package tftp;

import java.net.*;
import java.io.*;

public class Cliente {
	private DatagramSocket ds;
	private int puerto_servidor;
	private String ip_servidor;
	
	private BufferedOutputStream bos;
	private BufferedInputStream bis;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public Cliente(int puerto_servidor, String ip_servidor) {
		super();
		this.puerto_servidor = puerto_servidor;
		this.ip_servidor = ip_servidor;
		
		try {
			ds=new DatagramSocket(puerto_servidor,InetAddress.getByName(ip_servidor));
		} catch (SocketException | UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
