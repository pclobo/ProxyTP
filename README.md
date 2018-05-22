# ProxyTP
Para ser utilizado el programa, se debe descargar el archivo "AIT2018.jar".
Ingresar por consola a la carpeta en la que se encuentra el archivo .jar y 
ejecutar el siguiente comando "java -jar ./AIT2018.jar"
lo que ejecutará el programa.
Tambien se requiere la configuración correspondiente en FireFox:
Dirigirse a Opciones->Preferencias->
al final de la ventana aparecerá un titulo "Proxy de Red" seleccionar "Configuración".
tildar la opción " Configuración Manual del Proxy" 
Ingresar en Proxy HTTP "localhost" y en Puerto "9800" 
Seleccionar Aceptar.
Luego ya se puede validar ingresando a "example.com" y una vez abierta la pagina
se puede dirigir a la carpeta en la que se encuentra el .jar y se habrá generado 
un nuevo archivo de texto llamado LogFile, se lo puede abrir y se podrá apreciar 
el trafico de datos detallando Request y Response. 

Para GoogleChrome en MacOS dirigirse a Preferencias del Sistema y seleccionar
en la parte inferior derecha donde dice "Avanzado..." se abrirá una nueva ventana
en la que aparecerá seleccionada la plantilla "Wifi", se deberá seleccionar 
"Proxies" y luego tildar "Proxy de Web (HTTP)", en servidor proxy de web 
ingresar "localhost" y en puerto "9800", seleccionar Aceptar y luego Aplicar y
ya se puede comenzar a validar de la misma forma previamente detallada.
