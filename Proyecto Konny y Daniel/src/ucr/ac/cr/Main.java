package ucr.ac.cr;
import java.util.Scanner;

public class Main extends Board {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = 1;
        boolean flag = true;
        showMainMenu();
        int option = sc.nextInt();

        switch (option) {
            case (1):

                while (flag){
                    System.out.print("digite el tamaño del tablero (5-20): ");
                    size = sc.nextInt();

                    if (size >= 5 && size <= 20) {
                        initBoard(size); //inicializa el tablero con puntos
                        System.out.println();
                        System.out.println("Usted selecciono el siguiente tablero:");
                        showBoard(size); //muestra el tablero inicializado
                        System.out.println();
                        flag = false;

                    } else {
                        System.out.println("tamaño no valido");
                    }
                }

                Players player1 = new Players();
                System.out.println("Digite el nombre del ejercito del jugador 1");
                player1.setNameArmy(sc.next()); //asigna el nombre del ejercito
                Hero[] hero1 = new Hero[5]; //5 heroes por persona
                hero1[0] = new Archer(10);
                hero1[1] = new Warrior(15);
                hero1[2] = new Wizard(8);
                hero1[3] = new Tank(20);
                hero1[4] = new Killer(9);

                Players player2 = new Players();
                System.out.println("Digite el nombre del ejercito del jugador 2");
                player2.setNameArmy(sc.next()); //asigna el nombre del ejercito
                Hero[] hero2 = new Hero[5]; //5 heroes por persona
                hero2[0] = new Archer(10);
                hero2[1] = new Warrior(15);
                hero2[2] = new Wizard(8);
                hero2[3] = new Tank(20);
                hero2[4] = new Killer(9);

                System.out.println();
                System.out.println("1.Colocar heroes manualmente"); //pendiente de programar
                System.out.println("2.Mantener posicion sugerida");
                System.out.print("Selecciona una opción: ");
                int option1 = sc.nextInt(); //decide si quiere colocar los heroes manualmente o automatico

                if(option1==2){
                    automaticHeroesInitialization(board,size); //coloca los heroes automaticamente
                    System.out.println("Los heroes entraron a la arena de juego");
                    showBoard(size); //muestra el tablero con los heroes ubicados
                }

                break;
            case (2):
                showInstructions(); //muestra las intrucciones del juego
                break;
            case(3):
                showHistory(); //muestra la historia del juego
                break;
            case (4):
                break; //finaliza el codigo
            default:
                System.out.println("opcion no valida");
        }
    }
    public static void showMainMenu(){
        System.out.println("              ▛▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝▝ ▜");
        System.out.println("                  ⚔  ARENA DE HÉROES  ⚔");
        System.out.println("              ▙ ▖▖▖▖▖▖▖▖▖▖▖▖▖▖▖▖▖▖▖▖▖▖▖▖▖▖▖▟");
        System.out.println();
        System.out.println("              1. Iniciar nueva partida");
        System.out.println("              2. Ver instrucciones del juego");
        System.out.println("              3. Ver historia");
        System.out.println("              4. Salir");
        System.out.println();
        System.out.print("Selecciona una opción: ");
    }

    public static void showHistory(){
        System.out.println("📖 Historia de Arena de Héroes");
        System.out.println("Hace siglos, el mundo estaba dividido en reinos que vivían en constante guerra.");
        System.out.println("Los más poderosos buscaban conquistar tierras, dominar recursos y someter a sus rivales.");
        System.out.println("Sin embargo, la guerra interminable estaba llevando a la destrucción del mundo conocido.");
        System.out.println();
        System.out.println("En ese caos, los Cinco Consejos de Sabiduría —guardianes ancestrales de la magia, la naturaleza y el acero— tomaron una decisión:");
        System.out.println("prohibieron las guerras abiertas y establecieron un lugar sagrado donde los conflictos se resolverían de manera controlada: la Arena de Héroes.");
        System.out.println();
        System.out.println("Desde entonces, cada reino elige a sus campeones más valientes:");
        System.out.println();
        System.out.println("Guerreros, símbolos del honor en la batalla.");
        System.out.println("Arqueros, maestros de la puntería y la paciencia.");
        System.out.println("Magos, guardianes del conocimiento arcano.");
        System.out.println("Tanques, murallas vivientes que protegen a los suyos.");
        System.out.println("Asesinos, sombras letales que aparecen y desaparecen entre susurros.");
        System.out.println();
        System.out.println("Estos héroes no luchan por simples conquistas.");
        System.out.println("En la Arena, se enfrentan para defender el honor de sus pueblos, obtener la bendición de los Consejos y escribir su nombre en la historia.");
        System.out.println();
        System.out.println("Cada partida en la Arena representa un duelo sagrado entre ejércitos.");
        System.out.println("El suelo mismo de la Arena cambia de forma para adaptarse al combate: a veces reducido, a veces extenso, pero siempre implacable.");
        System.out.println("Solo un ejército saldrá victorioso, y con ello el derecho a guiar el destino de su gente.");
        System.out.println();
        System.out.println("En esta era, vos tomás el papel de estratega. El destino de tus héroes está en tus manos.");
        System.out.println("¿Usarás la fuerza bruta del Guerrero, la precisión del Arquero, la astucia del Asesino, la magia del Mago o la resistencia del Tanque?");
        System.out.println("En la Arena, no hay segundas oportunidades. Cada movimiento, cada ataque, puede significar la victoria… o la derrota.");
        System.out.println();
        System.out.println("“Que los dioses de la Arena guíen tu estrategia… y que tu ejército sea recordado como leyenda.”");
    }

    public static void showInstructions(){
        System.out.println("Cada jugador controla un ejército de héroes que se colocan en un tablero cuadrado configurable (entre 5x5 y 20x20).");
        System.out.println("Los héroes se enfrentan por turnos, moviéndose y atacando según sus habilidades y debilidades.");
        System.out.println("El objetivo es eliminar todas las piezas rivales o lograr su rendición.");
        System.out.println();
        System.out.println("Recordar la sintaxis de los heroes:");
        System.out.println("G = Guerrero\n" +
                "A = Arquero\n" +
                "M = Mago\n" +
                "T = Tanque\n" +
                "S = Asesino");
    }
}

//Cambiar la forma en la que se inizializan los heroes
//bucle para confirmar tamano del tablero
//opcion para colocar heroes manualmente (usar el array remplazando los ya usados por un null)
//crear constructores necesarios
//crear las funciones de movimiento