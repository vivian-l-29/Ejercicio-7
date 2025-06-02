// Importa clases necesarias para leer datos desde una conexión HTTP línea por línea
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Consulta {
    
    // Constructor de la clase Consulta que recibe una ruta como parámetro
    public Consulta(String ruta) {
        try {
            // Se construye la URL completa concatenando la ruta recibida al final del dominio base
            String URLUsuario = "https://semaforo-706f7-default-rtdb.firebaseio.com/" + ruta;
            
            // Se crea un objeto URL con la dirección formada anteriormente
            URL url = new URL(URLUsuario);

            // Se abre la conexión HTTP a esa URL y se convierte a tipo HttpURLConnection
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // Se indica que el tipo de solicitud será GET (obtener datos)
            con.setRequestMethod("GET");    
            
            // Se crea un lector que convierte los datos binarios recibidos en texto (línea por línea)
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            
            // Se utilizará para ir armando el contenido completo recibido desde el servidor
            StringBuilder content = new StringBuilder();
            
            // Variable para almacenar cada línea leída
            String inputLine;

            // Mientras haya líneas por leer, se agregan al contenido
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            // Se cierra el lector ya que se terminó de leer la respuesta
            in.close();

            // Se desconecta la conexión HTTP
            con.disconnect();

            // Se imprime en consola el contenido completo recibido desde el servidor
            System.out.println(content.toString());
    
        } catch (Exception e) {
            // En caso de que ocurra algún error (como que no haya internet, o que la URL esté mal), se muestra el error
            System.out.println("Error" + e);
        } 
    }
}
