/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorialsackets2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Alumno
 */
public class SocketServidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        BufferedReader entrada = null;
        PrintWriter salida = null;

        double factorial = 1;

        double numero;
        Socket socket = null;
        //se crea una instancia de ServerSocket que estara atendiendo en el puerto 1234
        ServerSocket serverSocket = new ServerSocket(6000);
        System.out.println("Esperando conexion de cliente en el puerto...");
        
        while (true) {
            try {
                //el ServerSocket da el acceso Socket al cliente que lo solicito
                socket = serverSocket.accept();
                
                //se obtiene informacion(IP) del cliente
                System.out.println("Conexion establecida desde la IP: " + socket.getInetAddress());
                
               //obtengo la entrada y la salida de bytes 
               entrada = new BufferedReader( new InputStreamReader(socket.getInputStream()));
               salida = new PrintWriter( new OutputStreamWriter(socket.getOutputStream()), true);
               
               //leo el nombre que envia el cliente
               
               numero= Integer.parseInt(entrada.readLine());
               while ( numero!=0) {
            factorial=factorial*numero;
                     numero--;
                }
            
               
               //regreso un saludo como respuesta al cliente
                String saludoServer = factorial + "!!";
                salida.println(saludoServer);
                socket.close();

            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
    
}
