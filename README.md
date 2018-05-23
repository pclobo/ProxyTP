# ProxyTP

El presente es un software proxy http cuyo objetivo es capturar el tráfico HTTP
y loguearlo siempre y cuando el usuario haya configurado el proxy en el
navegador(Chrome o Firefox).
Dentro del programa se encuentra configurado para que el proxy escuche por el puerto 
9800, una vez ejecutado el software accederá a la función run cuyo objetivo es
obtener el request del usuario y enviarlo al servidor mientras va guardando informacion
en una archivo llamado "LogFile_dd_mm_yyyy.txt", se le agrega la fecha para que guarde
información por día, luego obtiene la respuesta del servidor, la guarda en el mismo
archivo de log y envía la respuesta al usuario.
Para ser utilizado el programa, se debe descargar el archivo "AIT2018.jar" que se encuentra
en éste proyecto dentro de la carpeta "out/artifacts/AIT2018_jar".
Ingresar por consola a la carpeta en la que fue descargado el archivo .jar y 
ejecutar el siguiente comando "java -jar ./AIT2018.jar".
Tambien se requiere la configuración correspondiente en el navegador a utilizar:
-FireFox:
Dirigirse a Opciones->Preferencias->
Al final de la ventana aparecerá un titulo "Proxy de Red" seleccionar "Configuración".
tildar la opción "Configuración Manual del Proxy" 
Ingresar en Proxy HTTP "localhost" y en Puerto "9800" 
Seleccionar Aceptar.
-Google Chrome:
en MacOS dirigirse a Preferencias del Sistema y seleccionar
en la parte inferior derecha donde dice "Avanzado..." se abrirá una nueva ventana
en la que aparecerá seleccionada la plantilla "Wifi", se deberá seleccionar 
"Proxies" y luego tildar "Proxy de Web (HTTP)", en servidor proxy de web 
ingresar "localhost" y en puerto "9800", seleccionar Aceptar y luego Aplicar y
ya se puede comenzar a validar de la misma forma previamente detallada.

Luego ya se puede validar ingresando a "example.com" y una vez abierta la pagina
se puede dirigir a la carpeta en la que se encuentra el .jar donde se habrá generado 
un nuevo archivo de texto nombrado LogFile_dd_mm_yyyy (fecha actual), se lo puede abrir y se podrá apreciar 
el trafico de datos detallando Request y Response. 
