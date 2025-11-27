
 "Arena de Héroes"

- Instrucciones de compilación/ejecución -

Requisitos:
- Java JDK instalado
- Windows 10 o 11
- IntelliJ IDEA o similares

- Antes de empezar descomprima el archivo zip que contenga el proyecto

Usando IntelliJ IDEA:
1. Abrir el proyecto en IntelliJ.
2. Asegurarse de que `src` está marcado como Source Root.
3. Ejecutar la clase `ucr.ac.cr.Main` desde el botón Run.

Notas:
- El programa es interactivo por consola; ingrese comandos cuando se indique.
- Si aparece error de "comando no encontrado", verifique que el JDK esté instalado.


- Descripción de nuevas clases v2 -

 * Command
 Agrega las variables y el constructor necesario para la construccion del comando lineal en consola

 * CommandParser
 Contiene el metodo con todas las reglas y restriccioes para poder escribir el comando lineal

 * Game
 Contiene los metodos y variables necesarios para exportar el HTML, guardar la partida en un JSON y poder cargar el JSON

- Ejemplos de uso (3 comandos típicos) -

-mover-
Para que el jugador pueda moverse puede hacerlo de las siguientes formas ( primero la accion a realizar, luego que heroe desea mover, luego que fila y por ultimo que columna)
1. mover A 2 3
2. move G 5 4
3. mv T 2 2

-atacar-
Para que el jugador pueda atacar puede hacerlo de las siguientes formas ( primero la accion a realizar, luego que heroe con el que desea atacar y luego a que heroe quiere atacar)
1. atacar G S
2. attack S A
3. atk A A

-ver estadisticas-
Para que el jugador pueda ver las estadisticas puede hacerlo de las siguientes formas ( primero la accion a realizar, luego que heroe desea inspeccionar)
1. estadisticas T
2. stats A

Ruta del archivo de guardado
Todos los JSON de las partidas son guardados automaticamente en el archivo raiz del proyecto (Proyecto-Programaci-n)

Ruta del HTML con el informe
El archivo HTML de las partidas es guardado automaticamente en el archivo raiz del proyecto (Proyecto-Programaci-n)
