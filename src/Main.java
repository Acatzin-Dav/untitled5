
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import java.io.*;

public class Main {

    static Scanner sc =
            new Scanner(System.in);

    static Random random =
            new Random();

    // ==========================
    // MAIN
    // ==========================

    public static void main(
            String[] args) {

        ArrayList<Pokemon>
                equipoJugador;

        // POKEDEX

        ArrayList<Pokemon>
                pokedex =
                crearPokedex();

        // MENU

        System.out.println(
                "1. Nueva partida"
        );

        System.out.println(
                "2. Cargar partida"
        );

        int opcion =
                sc.nextInt();

        // ==========================
        // NUEVA PARTIDA
        // ==========================

        if (opcion == 1) {

            equipoJugador =
                    elegirEquipo(
                            pokedex
                    );
        }

        // ==========================
        // CARGAR PARTIDA
        // ==========================

        else {

            equipoJugador =
                    cargarPartida();

            // SI NO EXISTE SAVE

            if (equipoJugador
                    == null) {

                System.out.println(
                        "NO EXISTE PARTIDA"
                );

                equipoJugador =
                        elegirEquipo(
                                pokedex
                        );
            }
        }
        System.out.println(
                "1. VS IA"
        );

        System.out.println(
                "2. VS JUGADOR"
        );
        int modo = sc.nextInt();
        // ==========================
        // IA
        // ==========================

        // VS IA

        if (modo == 1) {

            ligaIA(equipoJugador, pokedex
            );
        }

// VS JUGADOR

        else {

            System.out.println(
                    "\nJUGADOR 2 ELIGE EQUIPO"
            );

            ArrayList<Pokemon>
                    equipoJugador2 =
                    elegirEquipo(
                            pokedex
                    );

            batallaJugador(
                    equipoJugador,
                    equipoJugador2
            );
        }
        // ==========================
        // GUARDAR
        // ==========================

        guardarPartida(
                equipoJugador
        );
    }
    public static void batallaJugador(

            ArrayList<Pokemon> jugador1,

            ArrayList<Pokemon> jugador2) {

        int indice1 = 0;

        int indice2 = 0;

        while (indice1 < jugador1.size()

                &&

                indice2 < jugador2.size()) {

            Pokemon poke1 =
                    jugador1.get(indice1);

            Pokemon poke2 =
                    jugador2.get(indice2);

            System.out.println(
                    "\n================"
            );

            System.out.println(

                    poke1.nombre
                            + " VS "
                            + poke2.nombre
            );

            // ==========================
            // TURNO JUGADOR 1
            // ==========================

            System.out.println(
                    "\nTURNO JUGADOR 1"
            );

            System.out.println(
                    "1. Atacar"
            );

            System.out.println(
                    "2. Cambiar Pokemon"
            );

            int accion1 =
                    sc.nextInt();

// CAMBIAR

            if (accion1 == 2) {

                indice1 =
                        cambiarPokemon(
                                jugador1,
                                indice1
                        );

                continue;
            }

// ATAQUES

            poke1.mostrarAtaques();

            int ataque1 =
                    sc.nextInt() - 1;

            // ==========================
// TURNO JUGADOR 2
// ==========================

            System.out.println(
                    "\nTURNO JUGADOR 2"
            );

            System.out.println(
                    "1. Atacar"
            );

            System.out.println(
                    "2. Cambiar Pokemon"
            );

            int accion2 =
                    sc.nextInt();

// CAMBIAR

            if (accion2 == 2) {

                indice2 =
                        cambiarPokemon(
                                jugador2,
                                indice2
                        );

                continue;
            }

// ATAQUES

            poke2.mostrarAtaques();

            int ataque2 =
                    sc.nextInt() - 1;

// ==========================
// VELOCIDAD
// ==========================

// JUGADOR 1 MAS RAPIDO

            if (poke1.velocidad
                    >=
                    poke2.velocidad) {

                poke1.atacar(
                        poke2,
                        ataque1
                );

                // SI SOBREVIVE

                if (poke2.hp > 0) {

                    poke2.atacar(
                            poke1,
                            ataque2
                    );
                }
            }

// JUGADOR 2 MAS RAPIDO

            else {

                poke2.atacar(
                        poke1,
                        ataque2
                );

                // SI SOBREVIVE

                if (poke1.hp > 0) {

                    poke1.atacar(
                            poke2,
                            ataque1
                    );
                }
            }

// ==========================
// DERROTAS
// ==========================

// JUGADOR 2

            if (poke2.hp <= 0) {

                System.out.println(

                        poke2.nombre +
                                " derrotado"
                );

                repartirExperiencia(
                        jugador1,
                        120
                );

                indice2++;

                continue;
            }

// JUGADOR 1

            if (poke1.hp <= 0) {

                System.out.println(

                        poke1.nombre +
                                " derrotado"
                );

                repartirExperiencia(
                        jugador2,
                        120
                );

                indice1++;
            }
        }

        // RESULTADO

        if (indice1 >= jugador1.size()) {

            System.out.println(
                    "\nGANA JUGADOR 2"
            );
        }

        else {

            System.out.println(
                    "\nGANA JUGADOR 1"
            );
        }
    }
    // ==========================
    // CREAR POKEDEX
    // ==========================

    public static ArrayList<Pokemon>
    crearPokedex() {

        ArrayList<Pokemon> lista =
                new ArrayList<>();

        Pokemon froakie = new Pokemon(
                "froakie", Tipo.AGUA, 41, 56, 40, 71, 1
        );
        froakie.agregarAtaque(
                new Ataque("azote", 25, Tipo.AGUA)
        );
        froakie.agregarAtaquePorNivel(2, new Ataque("chorro agua", 40, Tipo.AGUA));
        froakie.agregarAtaquePorNivel(3, new Ataque("hidrobomba", 60, Tipo.AGUA));
        froakie.agregarAtaquePorNivel(4, new Ataque("shuriken de agua", 100, Tipo.AGUA));
        froakie.evolucion = "frogadier";
        froakie.nivelEvolucion = 2;
        froakie.evolucion2 = "greninja";
        froakie.NivelEvolucion2 = 3;
        lista.add(froakie);
        Pokemon oshawott = new Pokemon(
                "oshawott", Tipo.AGUA, 55, 55, 45, 45, 1
        );
        oshawott.agregarAtaque(
                new Ataque("remolino", 35, Tipo.AGUA)
        );
        oshawott.agregarAtaquePorNivel(2, new Ataque("pistola agua", 50, Tipo.AGUA));
        oshawott.agregarAtaquePorNivel(3, new Ataque("hidropulso", 65, Tipo.AGUA));
        oshawott.agregarAtaquePorNivel(4, new Ataque("acuacola", 90, Tipo.AGUA));
        oshawott.evolucion = "dewott";
        oshawott.nivelEvolucion = 2;
        oshawott.evolucion2 = "samurott";
        oshawott.NivelEvolucion2 = 3;
        lista.add(oshawott);

        Pokemon fennekin =
                new Pokemon(
                        "fennekin",
                        Tipo.FUEGO,
                        40,
                        45,
                        40,
                        60,
                        1
                );

        fennekin.agregarAtaque(

                new Ataque(
                        "ascuas",
                        30,
                        Tipo.FUEGO
                )
        );

        // ATAQUE NIVEL

        fennekin.agregarAtaquePorNivel(

                2,

                new Ataque(
                        "brasas",
                        40,
                        Tipo.FUEGO
                )
        );
        fennekin.agregarAtaquePorNivel(

                3,

                new Ataque(
                        "lanzallamas",
                        90,
                        Tipo.FUEGO
                )
        );
        fennekin.agregarAtaquePorNivel(

                4,

                new Ataque(
                        "fuego mistico",
                        110,
                        Tipo.FUEGO
                )
        );

        // EVOLUCION

        fennekin.evolucion =
                "braixen";

        fennekin.nivelEvolucion =
                2;
        fennekin.evolucion2 = "delphox";
        fennekin.NivelEvolucion2 = 3;
        lista.add(fennekin);
        Pokemon litten =
                new Pokemon("litten", Tipo.FUEGO, 45, 65, 40, 70, 1);
        litten.evolucion = "torracat";
        litten.nivelEvolucion = 2;
        litten.evolucion2 = "incineroar";
        litten.NivelEvolucion2 = 3;
        litten.agregarAtaque(new Ataque("colmillo igneo", 25, Tipo.FUEGO));

        litten.agregarAtaquePorNivel(
                2,
                new Ataque("lariat oscuro", 50, Tipo.FUEGO)
        );
        litten.agregarAtaquePorNivel(
                3,
                new Ataque("evite igneo", 60, Tipo.FUEGO)
        );
        litten.agregarAtaquePorNivel(
                4,
                new Ataque("nitrocarga", 100, Tipo.FUEGO)
        );
        lista.add(litten);
        Pokemon snivy = new Pokemon(
                "snivy", Tipo.PLANTA, 45, 45, 55, 63, 1
        );
        snivy.agregarAtaque(
                new Ataque("latigo cepa", 20, Tipo.PLANTA)
        );
        snivy.agregarAtaquePorNivel(2, new Ataque("hoja magica", 40, Tipo.PLANTA));
        snivy.agregarAtaquePorNivel(3, new Ataque("gigadrenado", 57, Tipo.PLANTA));
        snivy.agregarAtaquePorNivel(4, new Ataque("lluevehojas", 99, Tipo.PLANTA));
        snivy.evolucion = "servine";
        snivy.nivelEvolucion = 2;
        snivy.evolucion2 = "serperior";
        snivy.NivelEvolucion2 = 3;
        lista.add(snivy);
        Pokemon ferroverdor = new Pokemon(
                "ferroverdor", Tipo.PLANTA, 90, 130, 88, 104, 1
        );
        ferroverdor.agregarAtaque(
                new Ataque("hoja afilada", 50, Tipo.PLANTA)
        );
        ferroverdor.agregarAtaquePorNivel(2, new Ataque("hoja aguda", 60, Tipo.PLANTA));
        ferroverdor.agregarAtaquePorNivel(3, new Ataque("psicohojas", 65, Tipo.PSIQUICO));
        ferroverdor.agregarAtaquePorNivel(4, new Ataque("cuchillasolar", 90, Tipo.PLANTA));
        lista.add(ferroverdor);
        Pokemon capsakid = new Pokemon(
                "capsakid", Tipo.PLANTA, 50, 62, 40, 50, 1
        );
        capsakid.agregarAtaque(
                new Ataque("follaje", 15, Tipo.PLANTA)
        );
        capsakid.agregarAtaquePorNivel(2, new Ataque("bala semilla", 30, Tipo.PLANTA));
        capsakid.agregarAtaquePorNivel(3, new Ataque("mordida", 45, Tipo.PLANTA));
        capsakid.agregarAtaquePorNivel(4, new Ataque("extracto picante", 87, Tipo.FUEGO));
        capsakid.evolucion = "scovillain";
        capsakid.nivelEvolucion = 2;
        lista.add(capsakid);
        Pokemon falinks = new Pokemon(
                "falinks", Tipo.LUCHA, 65, 100, 100, 75, 1
        );
        falinks.agregarAtaque(
                new Ataque("chiquipatada", 20, Tipo.LUCHA)
        );
        falinks.agregarAtaquePorNivel(2, new Ataque("GOLPE ROCA", 45, Tipo.LUCHA));
        falinks.agregarAtaquePorNivel(3, new Ataque("KARATAZO", 66, Tipo.LUCHA));
        falinks.agregarAtaquePorNivel(4, new Ataque("CUERPO A CUERPO", 90, Tipo.LUCHA));
        lista.add(falinks);
        Pokemon tauros = new Pokemon(
                "tauros", Tipo.LUCHA, 75, 100, 95, 110, 1
        );
        tauros.agregarAtaque(
                new Ataque("doble patada", 30, Tipo.LUCHA)
        );
        tauros.agregarAtaquePorNivel(2, new Ataque("golpe cabeza", 55, Tipo.PLANTA));
        tauros.agregarAtaquePorNivel(3, new Ataque("doble filo", 70, Tipo.PLANTA));
        tauros.agregarAtaquePorNivel(4, new Ataque("a bocajarro", 110, Tipo.PLANTA));
        lista.add(tauros);
        Pokemon tyrogue = new Pokemon(
                "tyrogue", Tipo.LUCHA, 35, 35, 35, 35, 1
        );
        tyrogue.agregarAtaque(
                new Ataque("amago", 30, Tipo.LUCHA)
        );
        tyrogue.agregarAtaquePorNivel(2, new Ataque("ultra puño", 50, Tipo.LUCHA));
        tyrogue.agregarAtaquePorNivel(3, new Ataque("deteccion", 70, Tipo.LUCHA));
        tyrogue.agregarAtaquePorNivel(4, new Ataque("puño certero", 99, Tipo.LUCHA));
        tyrogue.evolucion = "hitmonchan";
        tyrogue.nivelEvolucion = 2;
        lista.add(tyrogue);
        Pokemon pancham = new Pokemon(
                "pancham", Tipo.LUCHA, 67, 82, 62, 43, 1
        );
        pancham.agregarAtaque(
                new Ataque("tacleada", 35, Tipo.LUCHA)
        );
        pancham.agregarAtaquePorNivel(2, new Ataque("provocacion", 60, Tipo.LUCHA));
        pancham.agregarAtaquePorNivel(3, new Ataque("triturar", 76, Tipo.LUCHA));
        pancham.agregarAtaquePorNivel(4, new Ataque("enfado", 100, Tipo.LUCHA));
        pancham.evolucion = "pangoro";
        pancham.nivelEvolucion = 2;
        lista.add(pancham);

        Pokemon zorua = new Pokemon(
                "zorua", Tipo.SINIESTRO, 40, 65, 40, 65, 1
        );
        zorua.agregarAtaque(
                new Ataque("tajo umbrio", 25, Tipo.SINIESTRO)
        );
        zorua.agregarAtaquePorNivel(2, new Ataque("tormento", 66, Tipo.SINIESTRO));
        zorua.agregarAtaquePorNivel(3, new Ataque("desarme", 88, Tipo.SINIESTRO));
        zorua.agregarAtaquePorNivel(4, new Ataque("juego sucio", 110, Tipo.SINIESTRO));
        zorua.evolucion = "zoroark";
        zorua.nivelEvolucion = 2;
        lista.add(zorua);

        Pokemon absol =
                new Pokemon(
                        "absol",
                        Tipo.SINIESTRO,
                        65,
                        110,
                        60,
                        75,
                        1
                );

        absol.agregarAtaque(

                new Ataque(
                        "pulso oscuro",
                        35,
                        Tipo.SINIESTRO
                )
        );
        absol.agregarAtaquePorNivel(2, new Ataque("sombra vil", 60, Tipo.SINIESTRO));
        absol.agregarAtaquePorNivel(3, new Ataque("garra sombre", 65, Tipo.SINIESTRO));
        absol.agregarAtaquePorNivel(4, new Ataque("premonicion", 80, Tipo.SINIESTRO));
        lista.add(absol);
        Pokemon inkay = new Pokemon(
                "inkay", Tipo.SINIESTRO, 53, 54, 53, 45, 1
        );
        inkay.agregarAtaque(
                new Ataque("picotazo", 26, Tipo.SINIESTRO)
        );
        inkay.agregarAtaquePorNivel(2, new Ataque("llanto falso", 39, Tipo.SINIESTRO));
        inkay.agregarAtaquePorNivel(3, new Ataque("reversion", 50, Tipo.SINIESTRO));
        inkay.agregarAtaquePorNivel(4, new Ataque("hipnosis", 109, Tipo.SINIESTRO));
        inkay.evolucion = "malamar";
        inkay.nivelEvolucion = 2;
        lista.add(inkay);

        Pokemon chiyu =
                new Pokemon(
                        "chiyu",
                        Tipo.SINIESTRO,
                        55,
                        80,
                        80,
                        100,
                        1
                );

        chiyu.agregarAtaque(

                new Ataque(
                        "vendetta",
                        35,
                        Tipo.SINIESTRO
                )
        );
        chiyu.agregarAtaquePorNivel(2, new Ataque("rayo confuso", 58, Tipo.SINIESTRO));
        chiyu.agregarAtaquePorNivel(3, new Ataque("legado", 70, Tipo.SINIESTRO));
        chiyu.agregarAtaquePorNivel(4, new Ataque("sofoco", 90, Tipo.SINIESTRO));
        lista.add(chiyu);
        Pokemon gothita = new Pokemon(
                "gothita", Tipo.PSIQUICO, 45, 30, 50, 45, 1
        );
        gothita.agregarAtaque(
                new Ataque("cabezaso zen", 35, Tipo.PSIQUICO)
        );
        gothita.agregarAtaquePorNivel(2, new Ataque("psicorayo", 50, Tipo.PSIQUICO));
        gothita.agregarAtaquePorNivel(3, new Ataque("zona magica", 70, Tipo.PSIQUICO));
        gothita.agregarAtaquePorNivel(4, new Ataque("encanto", 100, Tipo.PSIQUICO));
        gothita.evolucion = "gothorita";
        gothita.nivelEvolucion = 2;
        gothita.evolucion2 = "gothitelle";
        gothita.NivelEvolucion2 = 3;
        lista.add(gothita);

        Pokemon flittle = new Pokemon(
                "flittle", Tipo.PSIQUICO, 30,35, 30,75,1
        );
        flittle.agregarAtaque(
                new Ataque("ojitos tiernos", 15,Tipo.PSIQUICO)
        );
        flittle.agregarAtaquePorNivel(2, new Ataque("fotocolision", 60, Tipo.PSIQUICO));
        flittle.agregarAtaquePorNivel(3, new Ataque("pico taladro", 65, Tipo.PSIQUICO));
        flittle.agregarAtaquePorNivel(4, new Ataque("psiquico", 90, Tipo.PSIQUICO));
        flittle.evolucion = "esphatra";
        flittle.nivelEvolucion = 2;
        lista.add(flittle);

        Pokemon charcadet = new Pokemon(
                "charcadet", Tipo.PSIQUICO, 40,50, 40,35,1
        );
        charcadet.agregarAtaque(
                new Ataque("paz mental", 20,Tipo.PSIQUICO)
        );
        charcadet.agregarAtaquePorNivel(2, new Ataque("aura esfera", 45, Tipo.PSIQUICO));
        charcadet.agregarAtaquePorNivel(3, new Ataque("malicioso", 60, Tipo.PSIQUICO));
        charcadet.agregarAtaquePorNivel(4, new Ataque("giro fuego", 85, Tipo.PSIQUICO));
        charcadet.evolucion = "armarouge";
        charcadet.nivelEvolucion = 2;
        lista.add(charcadet);
        Pokemon necrozma = new Pokemon(
                "necrozma", Tipo.PSIQUICO, 97, 107, 101, 79, 1
        );
        necrozma.agregarAtaque(
                new Ataque("garra mental", 40, Tipo.PSIQUICO)
        );
        necrozma.agregarAtaquePorNivel(2, new Ataque("poder reserva", 50, Tipo.PSIQUICO));
        necrozma.agregarAtaquePorNivel(3, new Ataque("gravedad", 68, Tipo.PSIQUICO));
        necrozma.agregarAtaquePorNivel(4, new Ataque("laser prisma", 100, Tipo.PSIQUICO));
        lista.add(necrozma);

        Pokemon poochyena = new Pokemon(
                "poochyena", Tipo.SINIESTRO, 35, 55, 35, 35, 1
        );
        poochyena.agregarAtaque(
                new Ataque("ladron", 30, Tipo.SINIESTRO)
        );
        poochyena.agregarAtaquePorNivel(2, new Ataque("mofa", 50, Tipo.SINIESTRO));
        poochyena.agregarAtaquePorNivel(3, new Ataque("buena baza", 57, Tipo.SINIESTRO));
        poochyena.agregarAtaquePorNivel(4, new Ataque("golpe bajo", 80, Tipo.SINIESTRO));
        poochyena.evolucion = "mightyena";
        poochyena.nivelEvolucion = 2;
        lista.add(poochyena);

        return lista;
    }

    // ==========================
    // ELEGIR EQUIPO
    // ==========================

    public static ArrayList<Pokemon>
    elegirEquipo(
            ArrayList<Pokemon>
                    pokedex) {

        ArrayList<Pokemon>
                equipo =
                new ArrayList<>();

        while (equipo.size() < 6) {

            System.out.println(
                    "\nELIGE POKEMON"
            );

            // MOSTRAR POKEDEX

            for (int i = 0;
                 i < pokedex.size();
                 i++) {

                Pokemon p =
                        pokedex.get(i);

                System.out.println(

                        (i + 1)
                                + ". "
                                + p.nombre
                                + " NIVEL "
                                + p.nivel
                );
            }

            int opcion =
                    sc.nextInt() - 1;

            // VALIDAR

            if (opcion >= 0 &&
                    opcion <
                            pokedex.size()) {

                // COPIAR POKEMON

                Pokemon copia =

                        pokedex
                                .get(opcion)
                                .copiar();

                equipo.add(copia);

                System.out.println(

                        copia.nombre +
                                " agregado"
                );
            }
        }

        return equipo;
    }

    public static int cambiarPokemon(

            ArrayList<Pokemon>
                    equipo,

            int actual) {

        System.out.println(
                "\nELIGE POKEMON"
        );

        for (int i = 0;
             i < equipo.size();
             i++) {

            Pokemon p =
                    equipo.get(i);

            System.out.println(

                    (i + 1)
                            + ". "
                            + p.nombre
                            + " HP: "
                            + p.hp
            );
        }

        int opcion =
                sc.nextInt() - 1;

        // VALIDAR

        if (opcion >= 0
                &&
                opcion < equipo.size()
                &&
                equipo.get(opcion).hp > 0
                &&
                opcion != actual) {

            System.out.println(

                    "CAMBIAS A "
                            + equipo.get(opcion).nombre
            );

            return opcion;
        }

        System.out.println(
                "NO SE PUDO CAMBIAR"
        );

        return actual;
    }
// ==========================
// REPARTIR EXPERIENCIA
// ==========================

    public static void repartirExperiencia(

            ArrayList<Pokemon>
                    equipo,

            int expTotal) {

        // CONTAR POKEMON VIVOS

        int vivos = 0;

        for (Pokemon p : equipo) {

            if (p.hp > 0) {

                vivos++;
            }
        }

        // EVITAR DIVISION ENTRE 0

        if (vivos == 0) {

            return;
        }

        // EXPERIENCIA PARA CADA UNO

        int expIndividual =
                expTotal / vivos;

        // DAR EXPERIENCIA

        for (Pokemon p : equipo) {

            if (p.hp > 0) {
                p.ganarExperiencia(
                        expIndividual
                );
            }
        }
    }

    // ==========================
// LIGA IA
// ==========================

    public static void ligaIA(

            ArrayList<Pokemon>
                    equipoJugador,

            ArrayList<Pokemon>
                    pokedex) {

        // 5 ENTRENADORES

        for (int ronda = 1;
             ronda <= 5;
             ronda++) {

            System.out.println(

                    "\n===================="
            );

            System.out.println(

                    "ENTRENADOR "
                            + ronda
            );

            System.out.println(

                    "===================="
            );

            // CREAR IA NUEVA

            ArrayList<Pokemon>
                    equipoIA =
                    elegirEquipoIA(
                            pokedex, ronda
                    );

            // BATALLA

            batallaIA(
                    equipoJugador,
                    equipoIA
            );

            // VERIFICAR DERROTA

            boolean derrota = true;

            for (Pokemon p :
                    equipoJugador) {

                if (p.hp > 0) {

                    derrota = false;
                }
            }

            // SI PERDIO

            if (derrota) {

                System.out.println(
                        "\nPERDISTE LA LIGA"
                );

                return;
            }

            // CURAR EQUIPO

            curarEquipo(
                    equipoJugador
            );
        }

        System.out.println(
                "\nGANASTE LA LIGA"
        );
    }
    // ==========================
// CURAR EQUIPO
// ==========================

    public static void curarEquipo(

            ArrayList<Pokemon>
                    equipo) {

        for (Pokemon p : equipo) {

            p.hp = p.hpMax;
        }

        System.out.println(
                "\nTU EQUIPO FUE CURADO"
        );
    }

    // ==========================
    // EQUIPO IA
    // ==========================

    public static ArrayList<Pokemon>
    elegirEquipoIA(
            ArrayList<Pokemon>
                     pokedex, int ronda) {

        ArrayList<Pokemon>
                ia =
                new ArrayList<>();

        // NIVEL SEGUN RONDA

        int nivelIA =
                ronda * 2;

        // MAXIMO 50

        if (nivelIA > 50) {

            nivelIA = 50;
        }

        for (int i = 0;
             i < 6;
             i++) {

            Pokemon copia =

                    pokedex.get(

                            random.nextInt(
                                    pokedex.size()
                            )
                    ).copiar();

            // SUBIR NIVEL

            copia.subirNivelIA(
                    nivelIA
            );

            ia.add(copia);
        }

        return ia;
    }

    // ==========================
    // BATALLA
    // ==========================

    public static void batallaIA(

            ArrayList<Pokemon>
                    jugador,

            ArrayList<Pokemon>
                    enemigo) {

        int indiceJugador = 0;

        int indiceEnemigo = 0;

        while (indiceJugador <
                jugador.size()

                &&

                indiceEnemigo <
                        enemigo.size()) {

            Pokemon actualJugador =

                    jugador.get(
                            indiceJugador
                    );

            Pokemon actualEnemigo =

                    enemigo.get(
                            indiceEnemigo
                    );

            // MOSTRAR INFO

            System.out.println(
                    "\n================"
            );

            System.out.println(

                    actualJugador.nombre
                            + " VS "
                            + actualEnemigo.nombre
            );

            System.out.println(

                    actualJugador.nombre
                            + " HP: "
                            + actualJugador.hp
            );

            System.out.println(

                    actualEnemigo.nombre
                            + " HP: "
                            + actualEnemigo.hp
            );

            // ATAQUES

            System.out.println(
                    "\n1. Atacar"
            );

            System.out.println(
                    "2. Cambiar Pokemon"
            );

            int accion =
                    sc.nextInt();
            // CAMBIAR

            if (accion == 2) {

                indiceJugador =
                        cambiarPokemon(
                                jugador,
                                indiceJugador
                        );

                continue;
            }
            System.out.println(
                    "\nELIGE ATAQUE"
            );

            actualJugador
                    .mostrarAtaques();

            int opcionAtaque =
                    sc.nextInt() - 1;

            // VALIDAR

            if (opcionAtaque < 0 ||

                    opcionAtaque >=
                            actualJugador
                                    .ataques
                                    .size()) {

                System.out.println(
                        "ATAQUE INVALIDO"
                );

                continue;
            }

            // ATAQUE IA

            int ataqueIA =

                    random.nextInt(

                            actualEnemigo
                                    .ataques
                                    .size()
                    );

// ==========================
// VELOCIDAD
// ==========================

// JUGADOR MAS RAPIDO

            if (actualJugador.velocidad
                    >=
                    actualEnemigo.velocidad) {

                // JUGADOR ATACA

                actualJugador.atacar(
                        actualEnemigo,
                        opcionAtaque
                );

                // ENEMIGO SOBREVIVE

                if (actualEnemigo.hp > 0) {

                    actualEnemigo.atacar(
                            actualJugador,
                            ataqueIA
                    );
                }
            }

// IA MAS RAPIDA

            else {

                // IA ATACA

                actualEnemigo.atacar(
                        actualJugador,
                        ataqueIA
                );

                // JUGADOR SOBREVIVE

                if (actualJugador.hp > 0) {

                    actualJugador.atacar(
                            actualEnemigo,
                            opcionAtaque
                    );
                }
            }

// ==========================
// DERROTAS
// ==========================

// IA DERROTADA

            if (actualEnemigo.hp <= 0) {

                System.out.println(

                        actualEnemigo.nombre
                                + " derrotado"
                );

                repartirExperiencia(
                        jugador,
                        120
                );

                indiceEnemigo++;

                continue;
            }

// JUGADOR DERROTADO

            if (actualJugador.hp <= 0) {

                System.out.println(

                        actualJugador.nombre
                                + " derrotado"
                );

                indiceJugador++;
            }
        }

        // RESULTADO

        if (indiceJugador >=
                jugador.size()) {

            System.out.println(
                    "\nPERDISTE"
            );
        }

        else {

            System.out.println(
                    "\nGANASTE"
            );
        }
    }

    // ==========================
    // GUARDAR PARTIDA
    // ==========================

    public static void guardarPartida(

            ArrayList<Pokemon>
                    equipo) {

        try {

            ObjectOutputStream salida =

                    new ObjectOutputStream(

                            new FileOutputStream(
                                    "save.dat"
                            )
                    );

            salida.writeObject(
                    equipo
            );

            salida.close();

            System.out.println(
                    "\nPARTIDA GUARDADA"
            );
        }

        catch (Exception e) {

            System.out.println(
                    "ERROR GUARDANDO"
            );
        }
    }

    // ==========================
    // CARGAR PARTIDA
    // ==========================

    public static ArrayList<Pokemon>
    cargarPartida() {

        try {

            ObjectInputStream entrada =

                    new ObjectInputStream(

                            new FileInputStream(
                                    "save.dat"
                            )
                    );

            ArrayList<Pokemon>
                    equipo =

                    (ArrayList<Pokemon>)
                            entrada.readObject();

            entrada.close();

            System.out.println(
                    "\nPARTIDA CARGADA"
            );

            return equipo;
        }

        catch (Exception e) {

            return null;
        }
    }
}