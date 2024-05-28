# NBA - JDBC



## Ejemplos de funcionamiento
### Llistar tots els jugadors d'un equip.
![imagen](src\Assets\Ejemplo 1.png)

### Calcular la mitjana de punts, rebots, assistències d'un jugador.
![imagen](src\Assets\Ejemplo 2.png)

### Llistar tots els partits jugats per un equip amb el seu resultat.
![imagen](src\Assets\Ejemplo 3.png)

### Inserir un nou jugador a un equip.
![imagen](src\Assets\Ejemplo 4.png)

### Si el jugador ja existeix a la base de dades llavors demanem si vol traspassar-lo.
![imagen](src\Assets\Ejemplo 4.1.png)

### Traspassar un judador a un altra equip.
![imagen](src\Assets\Ejemplo 5.png)

### Actualitzar les dades de jugadors despres del partit.
![imagen](src\Assets\Ejemplo 6.png)

### ...
![imagen](src\Assets\Ejemplo 6.1.png)

### Modificar les estadístiques d’un jugador a un partit.
![imagen](src\Assets\Ejemplo 7.png)

### Retirar un jugador.
![imagen](src\Assets\Ejemplo 8.png)

### Canviar nom franquícia d’un equip.
![imagen](src\Assets\Ejemplo 9.png)






## Consideraciones
### BD 🔤

La base de datos se puede generar con [script base de datos nba.sql](src%2FDataBase%2Fscript%20base%20de%20datos%20nba.sql)
este script tiene todo lo necesario para que la aplicacion funcione:

- Creacion de las tablas
- Carga de datos inicial
- Triggers para la actualizacion de los datos
- Procedures para optimizar los tiempos de la aplicacion

### Controlador ⚙️

#### Conexion 🛜

hemos decidido hacer una clase conexion con los metodos de conexion y cierre para poder reutilizarlos a lo largo de todo el programa
tenemos uno que cierra obtejos de tipo ```conexion``` y otro de tipo ```statement```

```java
 public static Connection connection()

```
```java
public static void close(Connection con)
public static void close(PreparedStatement smt)
```

### Menu 🟰


La clase menu tiene ```menuPrincipal()``` que se encarga de llamar las funciones en funcion de lo que escoge el usuario

y ```confirmMenu()``` que elabora usando una funcion de la libreria [Libreriascustom.jar](librerias%2FLibreriascustom_jar%2FLibreriascustom.jar)
un menu con un titulo, las opciones que se le pasan y retorna el input del usuario ya verificado.

esta funcion la usamos en varias ocasiones para pedir confirmacion sobre acciones trascendentales en la base de datos como por ejemplo el *retirar un jugador* o *traspasarlo*


### Controlador 🕹️

esta case contiene toda la logica del programa y se encarga de llamar a los metods de la clase model para conectarse a la bd y obtener los datos








### Model ✍️

Cada objeto corresponde a una tabla que hay en la base de datos y tiene los atributos correspondientes para poder trabajar con ellos guardando objetos 

#### DAO 🧩

En este package estan todas las clases que tienen implementados los metodos CRUD

En el caso de ```DaoPlayerMatches``` los metodos de create y delete no estan implementados debido a que en la base de datos se crean y se elimina los datos mediante un tigger

Sin embargo en ```DaoHistoricPlayers``` no estan implementados los metodos RUD porque no son necesarios en este programa, pero podrian serlo entonces estan puestos en la clase.

### Vista 🕶️

En esta clase se encuentran todos los metodos de imprimir por pantalla usados en la aplicacion, hay metodos para mostrar arrays personalizados y genericos 
