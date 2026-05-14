
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

            poke1.mostrarAtaques();

            int ataque1 =
                    sc.nextInt() - 1;

            poke1.atacar(
                    poke2,
                    ataque1
            );

            // DERROTA

            if (poke2.hp <= 0) {

                System.out.println(

                        poke2.nombre +
                                " derrotado"
                );

                poke1.ganarExperiencia(
                        120
                );

                indice2++;

                continue;
            }

            // ==========================
            // TURNO JUGADOR 2
            // ==========================

            System.out.println(
                    "\nTURNO JUGADOR 2"
            );

            poke2.mostrarAtaques();

            int ataque2 =
                    sc.nextInt() - 1;

            poke2.atacar(
                    poke1,
                    ataque2
            );

            // DERROTA

            if (poke1.hp <= 0) {

                System.out.println(

                        poke1.nombre +
                                " derrotado"
                );

                poke2.ganarExperiencia(
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
                        60,
                        Tipo.FUEGO
                )
        );

        // ATAQUE NIVEL

        fennekin.agregarAtaquePorNivel(

                2,

                new Ataque(
                        "brasas",
                        3,
                        Tipo.FUEGO
                )
        );
        fennekin.agregarAtaquePorNivel(

                2,

                new Ataque(
                        "lanzallamas",
                        3001,
                        Tipo.FUEGO
                )
        );
        fennekin.agregarAtaquePorNivel(

                2,

                new Ataque(
                        "fuego mistico",
                        3001,
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
        litten.agregarAtaque(new Ataque("colmillo igneo", 40, Tipo.FUEGO));

        litten.agregarAtaquePorNivel(
                2,
                new Ataque("lariat oscuro", 50, Tipo.FUEGO)
        );
        litten.agregarAtaquePorNivel(
                2,
                new Ataque("evite igneo", 50, Tipo.FUEGO)
        );
        litten.agregarAtaquePorNivel(
                2,
                new Ataque("nitrocarga", 50, Tipo.FUEGO)
        );
        lista.add(litten);
        Pokemon snivy = new Pokemon(
                "snivy", Tipo.PLANTA, 45, 45, 55, 63, 1
        );
        snivy.agregarAtaque(
                new Ataque("latigo cepa", 88, Tipo.PLANTA)
        );
        snivy.agregarAtaquePorNivel(2, new Ataque("hoja magica", 50, Tipo.PLANTA));
        snivy.agregarAtaquePorNivel(2, new Ataque("gigadrenado", 50, Tipo.PLANTA));
        snivy.agregarAtaquePorNivel(2, new Ataque("lluevehojas", 50, Tipo.PLANTA));
        snivy.evolucion = "servine";
        snivy.nivelEvolucion = 2;
        snivy.evolucion2 = "serperior";
        snivy.NivelEvolucion2 = 3;
        lista.add(snivy);
        Pokemon ferroverdor = new Pokemon(
                "ferroverdor", Tipo.PLANTA, 90, 130, 88, 104, 1
        );
        ferroverdor.agregarAtaque(
                new Ataque("hoja afilada", 88, Tipo.PLANTA)
        );
        ferroverdor.agregarAtaquePorNivel(2, new Ataque("hoja aguda", 50, Tipo.PLANTA));
        ferroverdor.agregarAtaquePorNivel(2, new Ataque("psicohojas", 50, Tipo.PLANTA));
        ferroverdor.agregarAtaquePorNivel(2, new Ataque("cuchillasolar", 50, Tipo.PLANTA));
        lista.add(ferroverdor);
        Pokemon capsakid = new Pokemon(
                "capsakid", Tipo.PLANTA, 50, 62, 40, 50, 1
        );
        capsakid.agregarAtaque(
                new Ataque("follaje", 88, Tipo.PLANTA)
        );
        capsakid.agregarAtaquePorNivel(2, new Ataque("bala semilla", 50, Tipo.PLANTA));
        capsakid.agregarAtaquePorNivel(2, new Ataque("mordida", 50, Tipo.PLANTA));
        capsakid.agregarAtaquePorNivel(2, new Ataque("extracto picante", 50, Tipo.PLANTA));
        capsakid.evolucion = "scovillain";
        capsakid.nivelEvolucion = 2;
        lista.add(capsakid);
        Pokemon falinks = new Pokemon(
                "falinks", Tipo.LUCHA, 65, 100, 100, 75, 1
        );
        falinks.agregarAtaque(
                new Ataque("chiquipatada", 88, Tipo.LUCHA)
        );
        falinks.agregarAtaquePorNivel(2, new Ataque("drenadoras", 50, Tipo.PLANTA));
        falinks.agregarAtaquePorNivel(2, new Ataque("drenadoras", 50, Tipo.PLANTA));
        falinks.agregarAtaquePorNivel(2, new Ataque("drenadoras", 50, Tipo.PLANTA));
        lista.add(falinks);
        Pokemon tauros = new Pokemon(
                "tauros", Tipo.LUCHA, 75, 100, 95, 110, 1
        );
        tauros.agregarAtaque(
                new Ataque("chiquipatada", 88, Tipo.LUCHA)
        );
        tauros.agregarAtaquePorNivel(2, new Ataque("drenadoras", 50, Tipo.PLANTA));
        tauros.agregarAtaquePorNivel(2, new Ataque("drenadoras", 50, Tipo.PLANTA));
        tauros.agregarAtaquePorNivel(2, new Ataque("drenadoras", 50, Tipo.PLANTA));
        lista.add(tauros);
        Pokemon tyrogue = new Pokemon(
                "tyrogue", Tipo.LUCHA, 35, 35, 35, 35, 1
        );
        tyrogue.agregarAtaque(
                new Ataque("abocajarro", 88, Tipo.LUCHA)
        );
        tyrogue.agregarAtaquePorNivel(2, new Ataque("paliza", 50, Tipo.LUCHA));
        tyrogue.agregarAtaquePorNivel(2, new Ataque("paliza", 50, Tipo.LUCHA));
        tyrogue.agregarAtaquePorNivel(2, new Ataque("paliza", 50, Tipo.LUCHA));
        tyrogue.evolucion = "hitmonchan";
        tyrogue.nivelEvolucion = 2;
        lista.add(tyrogue);
        Pokemon pancham = new Pokemon(
                "pancham", Tipo.LUCHA, 67, 82, 62, 43, 1
        );
        pancham.agregarAtaque(
                new Ataque("abocajarro", 88, Tipo.LUCHA)
        );
        pancham.agregarAtaquePorNivel(2, new Ataque("paliza", 50, Tipo.LUCHA));
        pancham.agregarAtaquePorNivel(2, new Ataque("paliza", 50, Tipo.LUCHA));
        pancham.agregarAtaquePorNivel(2, new Ataque("paliza", 50, Tipo.LUCHA));
        pancham.evolucion = "pangoro";
        pancham.nivelEvolucion = 2;
        lista.add(pancham);

        Pokemon zorua = new Pokemon(
                "zorua", Tipo.SINIESTRO, 40, 65, 40, 65, 1
        );
        zorua.agregarAtaque(
                new Ataque("garra umbria", 88, Tipo.SINIESTRO)
        );
        zorua.agregarAtaquePorNivel(2, new Ataque("señuelo", 50, Tipo.SINIESTRO));
        zorua.agregarAtaquePorNivel(2, new Ataque("señuelo", 50, Tipo.SINIESTRO));
        zorua.agregarAtaquePorNivel(2, new Ataque("señuelo", 50, Tipo.SINIESTRO));
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
        absol.agregarAtaquePorNivel(2, new Ataque("señuelo", 50, Tipo.SINIESTRO));
        absol.agregarAtaquePorNivel(2, new Ataque("señuelo", 50, Tipo.SINIESTRO));
        absol.agregarAtaquePorNivel(2, new Ataque("señuelo", 50, Tipo.SINIESTRO));
        lista.add(absol);
        Pokemon inkay = new Pokemon(
                "inkay", Tipo.SINIESTRO, 53, 54, 53, 45, 1
        );
        inkay.agregarAtaque(
                new Ataque("garra umbria", 88, Tipo.SINIESTRO)
        );
        inkay.agregarAtaquePorNivel(2, new Ataque("señuelo", 50, Tipo.SINIESTRO));
        inkay.agregarAtaquePorNivel(2, new Ataque("señuelo", 50, Tipo.SINIESTRO));
        inkay.agregarAtaquePorNivel(2, new Ataque("señuelo", 50, Tipo.SINIESTRO));
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
                        "pulso oscuro",
                        35,
                        Tipo.SINIESTRO
                )
        );
        chiyu.agregarAtaquePorNivel(2, new Ataque("señuelo", 50, Tipo.SINIESTRO));
        chiyu.agregarAtaquePorNivel(2, new Ataque("señuelo", 50, Tipo.SINIESTRO));
        chiyu.agregarAtaquePorNivel(2, new Ataque("señuelo", 50, Tipo.SINIESTRO));
        lista.add(chiyu);
        Pokemon gothita = new Pokemon(
                "gothita", Tipo.PSIQUICO, 45, 30, 50, 45, 1
        );
        gothita.agregarAtaque(
                new Ataque("cabezaso_zen", 88, Tipo.PSIQUICO)
        );
        gothita.agregarAtaquePorNivel(2, new Ataque("psicorayo", 50, Tipo.PSIQUICO));
        gothita.agregarAtaquePorNivel(2, new Ataque("psicorayo", 50, Tipo.PSIQUICO));
        gothita.agregarAtaquePorNivel(2, new Ataque("psicorayo", 50, Tipo.PSIQUICO));
        gothita.evolucion = "gothorita";
        gothita.nivelEvolucion = 2;
        gothita.evolucion2 = "gothitelle";
        gothita.NivelEvolucion2 = 3;
        lista.add(gothita);

        Pokemon flittle = new Pokemon(
                "flittle", Tipo.PSIQUICO, 30,35, 30,75,1
        );
        flittle.agregarAtaque(
                new Ataque("cabezaso_zen", 88,Tipo.PSIQUICO)
        );
        flittle.agregarAtaquePorNivel(2, new Ataque("psicorayo", 50, Tipo.PSIQUICO));
        flittle.agregarAtaquePorNivel(2, new Ataque("psicorayo", 50, Tipo.PSIQUICO));
        flittle.agregarAtaquePorNivel(2, new Ataque("psicorayo", 50, Tipo.PSIQUICO));
        flittle.evolucion = "esphatra";
        flittle.nivelEvolucion = 2;
        lista.add(flittle);

        Pokemon charcadet = new Pokemon(
                "charcadet", Tipo.PSIQUICO, 40,50, 40,35,1
        );
        charcadet.agregarAtaque(
                new Ataque("cabezaso_zen", 88,Tipo.PSIQUICO)
        );
        charcadet.agregarAtaquePorNivel(2, new Ataque("psicorayo", 50, Tipo.PSIQUICO));
        charcadet.agregarAtaquePorNivel(2, new Ataque("psicorayo", 50, Tipo.PSIQUICO));
        charcadet.agregarAtaquePorNivel(2, new Ataque("psicorayo", 50, Tipo.PSIQUICO));
        charcadet.evolucion = "armarouge";
        charcadet.nivelEvolucion = 2;
        lista.add(charcadet);
        Pokemon necrozma = new Pokemon(
                "necrozma", Tipo.PSIQUICO, 97, 107, 101, 79, 1
        );
        necrozma.agregarAtaque(
                new Ataque("cabezaso_zen", 88, Tipo.PSIQUICO)
        );
        necrozma.agregarAtaquePorNivel(2, new Ataque("psicorayo", 50, Tipo.PSIQUICO));
        necrozma.agregarAtaquePorNivel(2, new Ataque("psicorayo", 50, Tipo.PSIQUICO));
        necrozma.agregarAtaquePorNivel(2, new Ataque("psicorayo", 50, Tipo.PSIQUICO));
        lista.add(necrozma);

        Pokemon poochyena = new Pokemon(
                "poochyena", Tipo.SINIESTRO, 35, 55, 35, 35, 1
        );
        poochyena.agregarAtaque(
                new Ataque("garra umbria", 88, Tipo.SINIESTRO)
        );
        poochyena.agregarAtaquePorNivel(2, new Ataque("señuelo", 50, Tipo.SINIESTRO));
        poochyena.agregarAtaquePorNivel(2, new Ataque("señuelo", 50, Tipo.SINIESTRO));
        poochyena.agregarAtaquePorNivel(2, new Ataque("señuelo", 50, Tipo.SINIESTRO));
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
                            pokedex
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

        // GANAR

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
                    pokedex) {

        ArrayList<Pokemon>
                ia =
                new ArrayList<>();

        for (int i = 0;
             i < 6;
             i++) {

            Pokemon copia =

                    pokedex.get(

                            random.nextInt(
                                    pokedex.size()
                            )
                    ).copiar();

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

            // JUGADOR ATACA

            actualJugador.atacar(
                    actualEnemigo,
                    opcionAtaque
            );

            // DERROTA IA

            if (actualEnemigo.hp <= 0) {

                System.out.println(

                        actualEnemigo.nombre
                                + " derrotado"
                );

                actualJugador
                        .ganarExperiencia(
                                120
                        );

                indiceEnemigo++;

                continue;
            }

            // IA ATACA

            int ataqueIA =

                    random.nextInt(

                            actualEnemigo
                                    .ataques
                                    .size()
                    );

            actualEnemigo.atacar(
                    actualJugador,
                    ataqueIA
            );

            // DERROTA JUGADOR

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