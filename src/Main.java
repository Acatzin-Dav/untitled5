
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Scanner sc =
            new Scanner(System.in);

    static Random random =
            new Random();

    public static void main(
            String[] args) {

        // POKEDEX

        ArrayList<Pokemon> pokedex =
                crearPokedex();

        // EQUIPO

        ArrayList<Pokemon> equipoJugador =
                elegirEquipo(
                        pokedex
                );

        // ENEMIGO

        ArrayList<Pokemon> equipoEnemigo =
                elegirEquipoIA(
                        pokedex
                );

        // BATALLA

        batalla(
                equipoJugador,
                equipoEnemigo
        );
    }

    // ==========================
    // CREAR POKEDEX
    // ==========================

    public static ArrayList<Pokemon>
    crearPokedex() {

        ArrayList<Pokemon> lista =
                new ArrayList<>();


        // GRENINJA

        Pokemon greninja =
                new Pokemon(
                        "Greninja",
                        Tipo.AGUA,
                        120,
                        30,
                        5,
                        50,
                        5
                );

        greninja.agregarAtaque(

                new Ataque(
                        "Surf",
                        40,
                        Tipo.AGUA
                )
        );

        greninja.agregarAtaquePorNivel(

                10,

                new Ataque(
                        "Hidrobomba",
                        80,
                        Tipo.AGUA
                )
        );

        lista.add(greninja);


        // CHARMANDER

        Pokemon charmander =
                new Pokemon(
                        "Charmander",
                        Tipo.FUEGO,
                        100,
                        25,
                        5,
                        40,
                        5
                );

        charmander.agregarAtaque(

                new Ataque(
                        "Arañazo",
                        20,
                        Tipo.NORMAL
                )
        );

        charmander.agregarAtaquePorNivel(

                7,

                new Ataque(
                        "Ascuas",
                        40,
                        Tipo.FUEGO
                )
        );

        charmander.evolucion =
                "Charmeleon";

        charmander.nivelEvolucion =
                16;

        lista.add(charmander);


        // PIKACHU

        Pokemon pikachu =
                new Pokemon(
                        "Pikachu",
                        Tipo.ELECTRICO,
                        90,
                        35,
                        4,
                        60,
                        5
                );

        pikachu.agregarAtaque(

                new Ataque(
                        "Impactrueno",
                        45,
                        Tipo.ELECTRICO
                )
        );

        lista.add(pikachu);

        return lista;
    }

    // ==========================
    // ELEGIR EQUIPO
    // ==========================

    public static ArrayList<Pokemon>
    elegirEquipo(
            ArrayList<Pokemon> pokedex) {

        ArrayList<Pokemon> equipo =
                new ArrayList<>();

        while (equipo.size() < 3) {

            System.out.println(
                    "\nELIGE POKEMON"
            );

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

            if (opcion >= 0 &&
                    opcion < pokedex.size()) {

                equipo.add(
                        pokedex.get(opcion)
                );

                System.out.println(

                        pokedex
                                .get(opcion)
                                .nombre
                                + " agregado"
                );
            }
        }

        return equipo;
    }

    // ==========================
    // EQUIPO IA
    // ==========================

    public static ArrayList<Pokemon>
    elegirEquipoIA(
            ArrayList<Pokemon> pokedex) {

        ArrayList<Pokemon> equipo =
                new ArrayList<>();

        for (int i = 0;
             i < 3;
             i++) {

            int randomPokemon =
                    random.nextInt(
                            pokedex.size()
                    );

            equipo.add(
                    pokedex.get(
                            randomPokemon
                    )
            );
        }

        return equipo;
    }

    // ==========================
    // BATALLA
    // ==========================

    public static void batalla(

            ArrayList<Pokemon> jugador,

            ArrayList<Pokemon> enemigo) {

        int indiceJugador = 0;

        int indiceEnemigo = 0;

        while (indiceJugador <
                jugador.size() &&

                indiceEnemigo <
                        enemigo.size()) {

            Pokemon pokeJugador =
                    jugador.get(
                            indiceJugador
                    );

            Pokemon pokeEnemigo =
                    enemigo.get(
                            indiceEnemigo
                    );

            System.out.println(
                    "\n================"
            );

            System.out.println(

                    pokeJugador.nombre +
                            " VS " +
                            pokeEnemigo.nombre
            );

            System.out.println(

                    pokeJugador.nombre +
                            " HP: " +
                            pokeJugador.hp
            );

            System.out.println(

                    pokeEnemigo.nombre +
                            " HP: " +
                            pokeEnemigo.hp
            );

            // ATAQUES

            System.out.println(
                    "\nELIGE ATAQUE"
            );

            pokeJugador.mostrarAtaques();

            int opcion =
                    sc.nextInt() - 1;

            pokeJugador.atacar(
                    pokeEnemigo,
                    opcion
            );

            // DERROTA

            if (pokeEnemigo.hp <= 0) {

                System.out.println(

                        pokeEnemigo.nombre +
                                " fue derrotado"
                );

                pokeJugador
                        .ganarExperiencia(
                                120
                        );

                indiceEnemigo++;

                continue;
            }

            // IA

            int ataqueIA =

                    random.nextInt(
                            pokeEnemigo
                                    .ataques
                                    .size()
                    );

            pokeEnemigo.atacar(
                    pokeJugador,
                    ataqueIA
            );

            // DERROTA

            if (pokeJugador.hp <= 0) {

                System.out.println(

                        pokeJugador.nombre +
                                " fue derrotado"
                );

                indiceJugador++;
            }
        }

        // GANADOR

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
}