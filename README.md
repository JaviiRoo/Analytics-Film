# 🎬 Analytics Film

Analytics Film es una aplicación de escritorio en **Java (Swing)** con conexión a **MySQL**, diseñada para gestionar y visualizar películas y series. Permite a los usuarios registrar, calificar y explorar su contenido favorito.

## 📌 Características
- Gestión de usuarios con registro e inicio de sesión.
- Visualización y gestión de películas y series.
- Calificación de contenido con un sistema de puntuación.
- Interfaz gráfica intuitiva y moderna.
- Almacenamiento de datos en una base de datos MySQL.
- Funcionalidades CRUD para películas y series.
- Visualización de trailers.

## 🛠️ Tecnologías utilizadas
- **Lenguaje:** Java (Swing)
- **Base de Datos:** MySQL
- **IDE recomendado:** Eclipse / IntelliJ IDEA
- **Arquitectura:** MVC (Modelo-Vista-Controlador)

## 🚀 Manual de instalación
### 1️⃣ Requisitos previos

• Java 8 o superior.
• XAMPP (Apache y MySQL).
• MySQL Workbench.
• Eclipse.
• JDBC.

## Instalar XAMPP:

•Descarga XAMPP desde su web oficial: (https://www.apachefriends.org/).
•Una vez descargado, cuando lo vayamos a instalar nos aseguramos de seleccionar las casillas de Apache y MySQL como servicios.
•Una vez instalado, abrimos el Panel de Control de XAMPP y hacemos clic en empezar junto a Apache y MySQL seleccionados para que inicien los servicios correspondientes.

## Descargar, Instalar y Configurar MySQL:

•Descargamos e instalamos MySQL en https://dev.mysql.com/downloads/ y es aquí donde se desarrolla el código de la base de datos.
•Abrimos MySQL Workbench y creamos una nueva base de datos.
•Importamos los archivos de la carpeta Drive de nuestra base de datos sql.

## Configurar Eclipse y la aplicación:
   
•Descargamos e instalamos Eclipse de https://www.eclipse.org/downloads/.
•Abrir Eclipse y crear un nuevo proyecto Java o importar el proyecto directamente.
•Abrimos la clase de ConexionBD que es la encargada de la conexión de Java con la base de datos y verificamos en los apartados de user, password y url que los datos están correctos y coinciden con los parámetros tuyos de tu ordenador.

## Descargar, instalar y configurar Java, Swing y JDBC:
   
•Descargamos e instalamos Java a través de https://www.java.com/es/download/ie_manual.jsp
•Necesitamos utilizar JDBC y añadirlo a nuestro proyecto. Su web oficial: https://dev.mysql.com/downloads/connector/j/
•Verificamos las dependencias de JDBC que estén en nuestro proyecto configuradas. Si necesitas añadir el conector, lo descargas de la web oficial → https://dev.mysql.com/downloads/connector/j/ y se añade al proyecto: Clic derecho sobre el proyecto → Build Path > Configure Build Path > Libraries > Add External JARs y seleccionamos el archivo .jar de MySQL JDBC que descargamos.

## Ejecutar aplicación con el Main:
   
•Abrimos y seleccionamos la clase Main.
•Clic derecho y seleccionamos Run As > Java Application.
•La aplicación procede a funcionar.

### 2️⃣ Clonar el repositorio
```bash
git clone https://github.com/JaviiRoo/Analytics-Film.git
