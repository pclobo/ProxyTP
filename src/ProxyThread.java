import java.net.*;
import java.io.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.nio.file.StandardOpenOption;

public class ProxyThread extends Thread {

    private Socket socket = null;
    private static final int BUFFER_SIZE = 32768;

    public ProxyThread(Socket socket) {
            super("ProxyThread");
            this.socket = socket;
        }

    //La función run obtiene el request del usuario y lo envía al servidor guardando informacion en LogFile.txt
    // Obtiene la respuesta del servidor, la guarda en LogFile.txt y la envía al usuario
    public void run() {
        try {
            DataOutputStream out =
                    new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            //Se comienza a generar el request del usuario.
            String inputLine;
            int cnt = 0;
            String urlToCall = "";
            while ((inputLine = in.readLine()) != null) {
                try {
                    StringTokenizer tok = new StringTokenizer(inputLine);
                    tok.nextToken();
                } catch (Exception e) {
                    break;
                }
                if (cnt == 0) {
                    String[] tokens = inputLine.split(" ");
                    urlToCall = tokens[1];
                }

                cnt++;
            }
            //Se comienza a enviar el request al servidor y a guardar información del mismo.
            BufferedReader rd = null;
            try {
                URL url = new URL(urlToCall);
                URLConnection conn = url.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(false);
                StringBuilder content = new StringBuilder();
                content.append("================================\nREQUEST INFO:\n================================\n");
                content.append("\nSending request to real server for url: " + urlToCall);
                content.append("\nType is: " + conn.getContentType());
                content.append("\nContent length: " + conn.getContentLength());
                content.append("\nAllowed user interaction: " + conn.getAllowUserInteraction());
                content.append("\nContent encoding: " + conn.getContentEncoding());
                content.append("\nContent type: " + conn.getContentType());
                content.append("\nContent HeaderFields: " + conn.getHeaderFields());
                writeOnFile(content.toString());

                //Se obtiene la respuesta del servidor y se la almacena en LogFile.txt
                InputStream is = null;
                HttpURLConnection huc = (HttpURLConnection)conn;
                if (conn.getContentLength() > 0) {
                    try {
                        is = conn.getInputStream();
                        rd = new BufferedReader(new InputStreamReader(is));
                        System.out.println(getUrlContents(urlToCall));
                        writeOnFile("================================\nRESPONSE INFO:\n================================\n");
                        writeOnFile(getUrlContents(urlToCall));
                    } catch (IOException ioe) {
                        writeOnFile(
                                "********* IO EXCEPTION **********: " + ioe);
                    }
                }
                //Se envía la respuesta al usuario.
                byte by[] = new byte[ BUFFER_SIZE ];
                int index = is.read( by, 0, BUFFER_SIZE );
                while ( index != -1 )
                {
                    out.write( by, 0, index );
                    index = is.read( by, 0, BUFFER_SIZE );
                }
                out.flush();
            } catch (Exception e) {
                writeOnFile("Encountered exception: " + e);
                out.writeBytes("");
            }

            //Se cierran todos los recursos utilizados.
            if (rd != null) {
                rd.close();
            }
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            if (socket != null) {
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getUrlContents(String theUrl)
    {
        StringBuilder content = new StringBuilder();
        try
        {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setRequestProperty("Accept", "*/*");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }

    protected static void writeOnFile(String output) {
        Path path = Paths.get(ProxyMain.nameLog);
        try (BufferedWriter br = Files.newBufferedWriter(path,
                Charset.defaultCharset(), StandardOpenOption.APPEND)) {
                try {
                    br.write(output);
                    br.newLine();
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createFile(String nameFile) {
        Path path = Paths.get(nameFile);
        try (BufferedWriter br = Files.newBufferedWriter(path,
                Charset.defaultCharset(), StandardOpenOption.CREATE)) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
