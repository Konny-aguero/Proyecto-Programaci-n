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
                size = getBoardSize(size, flag, sc);
                Players player1 = new Players("Player 1");
                System.out.println("Digite el nombre del ejercito del jugador 1 (no se permiten espacios)");
                player1.setNameArmy(sc.next());
                Hero[] hero1 = getHeroes();
                Players player2 = new Players("Player 2");
                System.out.println("Digite el nombre del ejercito del jugador 2 (no se permiten espacios)");
                player2.setNameArmy(sc.next());
                Hero[] hero2 = getHeroes(); System.out.println();
                System.out.println("COLOCACION DE HEROES EN EL TABLERO");
                heroesInitialization(flag, sc, hero1, hero2, size); System.out.println();
                System.out.println("COMIENZA LA BATALLA");
                System.out.println("Comienza el jugador: ");
                String activePlayer; activePlayer = randomPlayer(player1, player2); System.out.println();
                boolean gameOn = true;
                Hero[] activeHeroes;
                Hero[] enemyHeroes;
                if (activePlayer.equals(player1.getPlayerName())) {
                    activeHeroes = hero1; enemyHeroes = hero2;
                } else {
                    activeHeroes = hero2; enemyHeroes = hero1;
                }
                while (gameOn) {
                    int action = getActionOfGame(activePlayer, player1, player2, sc);
                    switch (action) {
                        case 1: // Move hero
                            boolean moved = false;
                            while (!moved) {
                                int heroe = chooseTheHeroToMove(activeHeroes, sc);
                                System.out.println("Casillas válidas para mover:");
                                showValidMoves(size, activeHeroes[heroe]);
                                System.out.println("Ingrese la fila: ");
                                int row1 = sc.nextInt() - 1;
                                System.out.println("Ingrese la columna: ");
                                int colum1 = sc.nextInt() - 1;
                                int oldRow = activeHeroes[heroe].getRow();
                                int oldCol = activeHeroes[heroe].getCol();
                                if (activeHeroes[heroe].move(row1, colum1, size)) {
                                    replaceHeroOnBoard(oldRow, oldCol, size, activeHeroes[heroe]);
                                    if (activePlayer.equals(player1.getPlayerName())) {
                                        activePlayer = player2.getPlayerName();
                                        activeHeroes = hero2; enemyHeroes = hero1;
                                    } else {
                                        activePlayer = player1.getPlayerName();
                                        activeHeroes = hero1; enemyHeroes = hero2;
                                    }
                                    System.out.println("Paso de turno");
                                    moved = true;
                                } else {
                                    showBoard(size);
                                }
                            }
                            break;
                        case 2: // Attack with hero
                            Integer heroe1 = chooseHeroToAttack(activeHeroes, sc);
                            Integer enemy = chooseHeroToHit(enemyHeroes, sc);
                            int oldRow1 = activeHeroes[heroe1].getRow();
                            int oldCol1 = activeHeroes[heroe1].getCol();
                            Hero heroe = activeHeroes[heroe1];
                            boolean SuccefullAttack = ifTheHeroIsAWizard(heroe, enemyHeroes, enemy);
                            if (SuccefullAttack){
                                board[oldRow1][oldCol1] = GameConfig.EMPTY;
                                int newrow = activeHeroes[heroe1].getRow();
                                int newcol = activeHeroes[heroe1].getCol();
                                board[newrow][newcol] = "[" + activeHeroes[heroe1].getSymbol() + "]";
                                confirmDeath(enemyHeroes, enemy);
                                boolean enemyAlive = false;
                                enemyAlive = isEnemyAlive(enemyHeroes, enemyAlive);
                                gameOn = ThePlayerWin(enemyAlive, activePlayer);
                                if (!gameOn) {
                                    break;
                                }
                            }
                            showBoard(size);
                            if (activePlayer.equals(player1.getPlayerName())) { //Cambio de turno
                                activePlayer = player2.getPlayerName();
                                activeHeroes = hero2; enemyHeroes = hero1;
                            } else {
                                activePlayer = player1.getPlayerName();
                                activeHeroes = hero1; enemyHeroes = hero2;
                            }
                            System.out.println("Paso de turno");
                            break;
                        case 3: // Show stats
                            Board.showAvailableHeroes(activeHeroes);
                            Integer heroe2 = getHeroe2(sc, activeHeroes);
                            Stats.showStats(activeHeroes[heroe2]);
                            break;
                        case 4:// skip turn
                            if (activePlayer.equals(player1.getPlayerName())){
                                activePlayer = player2.getPlayerName();
                                activeHeroes = hero2; enemyHeroes = hero1;
                            }else{
                                activePlayer = player1.getPlayerName();
                                activeHeroes = hero1; enemyHeroes = hero2;
                            }
                            System.out.println("Paso de turno");
                            break;

                        case 5: //Retirada
                            System.out.print(activePlayer +" se ha retirado, gana: ");
                            if (activePlayer.equals(player1.getPlayerName())){
                                activePlayer = player2.getPlayerName();
                            }else{
                                activePlayer = player1.getPlayerName();
                            }
                            System.out.print(activePlayer);
                            gameOn = false;
                            break;
                        default:
                            System.out.println("Opción inválida.");
                    }
                }
                break;
            case (2):
                showInstructions(); //show instructions of game
                break;
            case(3):
                showHistory(); //show history of game
                break;
            case (4):
                break; //finish code
            default:
                System.out.println("opcion no valida");
        }
    }

    private static Integer getHeroe2(Scanner sc, Hero[] activeHeroes) {
        boolean valid = false;
        int heroe2 = 0;
        while (!valid) {
            System.out.print("Escoja un heroe:");
            heroe2 = sc.nextInt() - 1;
            System.out.println();
            if (activeHeroes[heroe2] == null) {
                System.out.println("Héroe no disponible.");
            } else {
                valid = true;
            }
        }
        return heroe2;
    }

    private static boolean ThePlayerWin(boolean enemyAlive, String activePlayer){
        if (!enemyAlive) {
            System.out.println("FELICIDADES! " + activePlayer + " ha ganado la partida!");
            return false;
        } else {
            return true;
        }
    }

    private static boolean isEnemyAlive(Hero[] enemyHeroes, boolean enemyAlive) {
        for (Hero hero : enemyHeroes) {
            if (hero != null && hero.isAlive()) {
                enemyAlive = true;
                break;
            }
        }
        return enemyAlive;
    }

    private static void confirmDeath(Hero[] enemyHeroes, Integer enemy) {
        if (!enemyHeroes[enemy].isAlive()) {
            System.out.println(enemyHeroes[enemy].getSymbol() + " ha sido eliminado!");
            board[enemyHeroes[enemy].getRow()][enemyHeroes[enemy].getCol()] = GameConfig.EMPTY;
            enemyHeroes[enemy] = null;
        }
    }

    private static boolean ifTheHeroIsAWizard(Hero heroe, Hero[] enemyHeroes, Integer enemy) {
        boolean SuccefullAttack;
        if (heroe instanceof Wizard) {
            SuccefullAttack = ((Wizard) heroe).attackArea(enemyHeroes); // ataque en área
        } else {
            SuccefullAttack = heroe.attack(enemyHeroes[enemy]); // ataque normal
        }
        return SuccefullAttack;
    }

    private static Integer chooseHeroToHit(Hero[] enemyHeroes, Scanner sc) {
        boolean valid = false;
        int enemy = 0;
        while (!valid) {
            System.out.println("Elige el héroe a atacar: ");
            Board.showAvailableHeroes(enemyHeroes);
            enemy = sc.nextInt() - 1;
            if (enemyHeroes[enemy] == null) {
                System.out.println("Enemigo no disponible.");
            } else {
                valid = true;
            }
        }
        return enemy;
    }

    private static Integer chooseHeroToAttack(Hero[] activeHeroes, Scanner sc) {
        boolean valid = false;
        int heroe1 = 0;
        while (!valid) {
            System.out.println("Seleccione el héroe con el que deseas atacar: ");
            Board.showAvailableHeroes(activeHeroes);
            heroe1 = sc.nextInt() - 1;
            if (activeHeroes[heroe1] == null) {
                System.out.println("Héroe no disponible, elige otro.");
            } else {
                valid = true;
            }
        }
        return heroe1;
    }

    private static void replaceHeroOnBoard(int oldRow, int oldCol, int size, Hero activeHeroes) {
        board[oldRow][oldCol] = "[ ]";
        int newrow = activeHeroes.getRow();
        int newcol = activeHeroes.getCol();
        board[newrow][newcol] = "[" + activeHeroes.getSymbol() + "]";
        showBoard(size);
    }

    private static void showValidMoves(int size, Hero activeHeroes) {
        int[][] moves = activeHeroes.validMoves(size);
        for (int[] m : moves) {
            System.out.print("(" + (m[0]+1) + "," + (m[1]+1) + ") ");
        }
        System.out.println();
    }

    private static int chooseTheHeroToMove(Hero[] activeHeroes, Scanner sc) {
        boolean valid = false;
        int heroe = 1;
        while (!valid){
            System.out.println("Seleccione el héroe a mover: ");
            Board.showAvailableHeroes(activeHeroes);
            heroe = sc.nextInt() - 1;
            if (activeHeroes[heroe] == null) {
                System.out.println("Héroe no disponible, elige otro.");
            } else {
                valid = true;
            }
        }
        return heroe;
    }

    private static void heroesInitialization(boolean flag, Scanner sc, Hero[] hero1, Hero[] hero2, int size) {
        while (flag){
            int option1 = getOption1(sc);
            if(option1 == 1){
                PlaceHeroesManually(hero1, hero2, Board.board, sc);
                flag = false;
            } else if (option1 == 2) {
                automaticHeroesInitialization(Board.board , size, hero1, hero2);
                System.out.println("Los heroes entraron a la arena de juego");
                showBoard(size);
                flag = false;
            } else {
                System.out.println("Opcion invalida");
            }
        }
    }

    private static int getActionOfGame(String activePlayer, Players player1, Players player2, Scanner sc) {
        System.out.println("Turno del jugador: " + activePlayer + "(" + (activePlayer.equals(player1.getPlayerName()) ? player1.getNameArmy() : player2.getNameArmy()) + ")");
        System.out.println("Seleccione una opción:");
        System.out.println("1. Mover héroe");
        System.out.println("2. Atacar con héroe");
        System.out.println("3. Ver estadísticas");
        System.out.println("4. Pasar turno");
        System.out.println("5. Retirada");
        return sc.nextInt();
    }

    private static String randomPlayer(Players player1, Players player2) {
        String activePlayer;
        if (Math.random() < 0.5) {
            activePlayer = player1.getPlayerName();
            System.out.println(activePlayer+ " con el ejercito: " + player1.getNameArmy());
        } else {
            activePlayer = player2.getPlayerName();
            System.out.println(activePlayer+ " con el ejercito: " + player2.getNameArmy());
        }
        return activePlayer;
    }

    private static int getOption1(Scanner sc) {
        System.out.println("1.Colocar heroes manualmente");
        System.out.println("2.Mantener posicion sugerida");
        System.out.print("Selecciona una opción: ");
        return sc.nextInt();
    }

    private static void initializerBoard(int size) {
        initBoard(size); //inicializa el tablero con null []
        System.out.println();
        System.out.println("Usted selecciono el siguiente tablero:");
        showBoard(size); //muestra el tablero inicializado
        System.out.println();
    }

    private static Hero[] getHeroes() {
        Hero[] hero = new Hero[5]; //5 heroes por persona
        hero[0] = new Archer(GameConfig.ARCHER_HP , GameConfig.ARCHER_SYMBOL);
        hero[1] = new Warrior(GameConfig.WARRIOR_HP , GameConfig.WARRIOR_SYMBOL);
        hero[2] = new Wizard(GameConfig.WIZARD_HP , GameConfig.WIZARD_SYMBOL);
        hero[3] = new Tank(GameConfig.TANK_HP , GameConfig.TANK_SYMBOL);
        hero[4] = new Killer(GameConfig.KILLER_HP , GameConfig.KILLER_SYMBOL);
        return hero;
    }

    private static int getBoardSize(int size, boolean flag, Scanner sc) {
        while (flag){
            System.out.print("digite el tamaño del tablero (5-20): ");
            size = sc.nextInt();
            if (size >= 5 && size <= 20) {
                initializerBoard(size);
                flag = false;
            } else {
                System.out.println("tamaño no valido");
            }
        }
        return size;
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
        System.out.println("""
                G = Guerrero
                A = Arquero
                M = Mago
                T = Tanque
                S = Asesino""");
    }
}