
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Pokemon implements Serializable {

    String nombre;

    Tipo tipo;

    int hp;
    int hpMax;

    int ataque;
    int defensa;
    int velocidad;

    int nivel;
    int experiencia;

    String evolucion;
    int nivelEvolucion;

    String evolucion2;
    int NivelEvolucion2;

    ArrayList<Ataque> ataques =
            new ArrayList<>();

    HashMap<Integer, Ataque>
            ataquesPorNivel =
            new HashMap<>();


    // ==========================
    // CONSTRUCTOR
    // ==========================

    public Pokemon(
            String nombre,
            Tipo tipo,
            int hp,
            int ataque,
            int defensa,
            int velocidad,
            int nivel) {

        this.nombre = nombre;

        this.tipo = tipo;

        this.hp = hp;

        this.hpMax = hp;

        this.ataque = ataque;

        this.defensa = defensa;

        this.velocidad = velocidad;

        this.nivel = nivel;

        experiencia = 0;
    }

    // ==========================
    // AGREGAR ATAQUE
    // ==========================

    public void agregarAtaque(
            Ataque ataqueNuevo) {

        if (ataques.size() < 4) {

            ataques.add(
                    ataqueNuevo
            );
        }
    }

    // ==========================
    // ATAQUES POR NIVEL
    // ==========================

    public void agregarAtaquePorNivel(
            int nivel,
            Ataque ataque) {

        ataquesPorNivel.put(
                nivel,
                ataque
        );
    }

    // ==========================
    // ATACAR
    // ==========================

    public void atacar(
            Pokemon enemigo,
            int indiceAtaque) {

        Ataque ataqueUsado =
                ataques.get(indiceAtaque);

        double multiplicador = 1;

        // DEBILIDADES

        if (ataqueUsado.tipo ==
                Tipo.AGUA &&
                enemigo.tipo ==
                        Tipo.FUEGO) {

            multiplicador = 2;
        }

        if (ataqueUsado.tipo ==
                Tipo.FUEGO &&
                enemigo.tipo ==
                        Tipo.PLANTA) {

            multiplicador = 2;
        }

        if (ataqueUsado.tipo ==
                Tipo.PLANTA &&
                enemigo.tipo ==
                        Tipo.AGUA) {

            multiplicador = 2;
        }
        if (ataqueUsado.tipo ==
                Tipo.PSIQUICO &&
                enemigo.tipo ==
                        Tipo.LUCHA) {

            multiplicador = 2;
        }

        if (ataqueUsado.tipo ==
                Tipo.SINIESTRO &&
                enemigo.tipo ==
                        Tipo.PSIQUICO) {

            multiplicador = 2;
        }

        if (ataqueUsado.tipo ==
                Tipo.LUCHA &&
                enemigo.tipo ==
                        Tipo.SINIESTRO) {

            multiplicador = 2;
        }
        // STAB

        if (ataqueUsado.tipo ==
                this.tipo) {

            multiplicador *= 1.5;
        }

        int danio =

                (int)((ataque +
                        ataqueUsado.poder)
                        * multiplicador);

        danio -= enemigo.defensa;

        if (danio < 0) {

            danio = 0;
        }

        enemigo.hp -= danio;

        if (enemigo.hp < 0) {

            enemigo.hp = 0;
        }

        System.out.println(

                nombre +
                        " usó " +
                        ataqueUsado.nombre
        );

        System.out.println(

                "Hizo " +
                        danio +
                        " de daño"
        );

        System.out.println(

                enemigo.nombre +
                        " tiene " +
                        enemigo.hp +
                        " HP"
        );
    }

    // ==========================
    // GANAR EXPERIENCIA
    // ==========================

    public void ganarExperiencia(
            int exp) {

        experiencia += exp;

        System.out.println(

                nombre +
                        " ganó " +
                        exp +
                        " EXP"
        );

        while (experiencia >=
                nivel * 50) {

            experiencia -=
                    nivel * 50;

            subirNivel();
        }
    }

    // ==========================
    // SUBIR NIVEL
    // ==========================

    public void subirNivel() {

        nivel++;

        hpMax += 10;

        ataque += 3;

        defensa += 2;

        velocidad += 2;

        hp = hpMax;

        System.out.println(

                nombre +
                        " subió al nivel "
                        + nivel
        );

        verificarAtaques();

        verificarEvolucion();
    }

    // ==========================
    // DESBLOQUEAR ATAQUES
    // ==========================

    public void verificarAtaques() {

        if (ataquesPorNivel
                .containsKey(nivel)) {

            Ataque nuevo =

                    ataquesPorNivel
                            .get(nivel);

            System.out.println(

                    nombre +
                            " aprendió "
                            + nuevo.nombre
            );

            agregarAtaque(nuevo);
        }
    }

    // ==========================
    // EVOLUCIONAR
    // ==========================

    public void verificarEvolucion() {

        // PRIMERA EVOLUCION

        if (evolucion != null
                &&
                nivel >= nivelEvolucion
                &&
                !nombre.equals(evolucion)) {

            System.out.println(

                    nombre +
                            " evolucionó a "
                            + evolucion
            );

            nombre = evolucion;

            hpMax += 30;

            ataque += 10;

            defensa += 10;

            velocidad += 5;

            hp = hpMax;
            evolucion = null;
        }

        // SEGUNDA EVOLUCION

        else if (evolucion2 != null
                &&
                nivel >= NivelEvolucion2
                &&
                !nombre.equals(evolucion2)) {

            System.out.println(

                    nombre +
                            " evolucionó a "
                            + evolucion2
            );

            nombre = evolucion2;

            hpMax += 50;

            ataque += 20;

            defensa += 15;

            velocidad += 10;

            hp = hpMax;
            evolucion2= null;
        }
    }
    // ==========================
    // MOSTRAR ATAQUES
    // ==========================

    public void mostrarAtaques() {

        for (int i = 0;
             i < ataques.size();
             i++) {

            System.out.println(

                    (i + 1) +
                            ". " +
                            ataques.get(i).nombre
            );
        }
    }
    // ==========================
// AGREGAR ESTO EN POKEMON
// ==========================

    public Pokemon copiar() {

        Pokemon copia =

                new Pokemon(

                        nombre,

                        tipo,

                        hpMax,

                        ataque,

                        defensa,

                        velocidad,

                        nivel
                );

        copia.experiencia =
                experiencia;

        copia.evolucion =
                evolucion;

        copia.nivelEvolucion =
                nivelEvolucion;

        copia.NivelEvolucion2 = nivelEvolucion;
        copia.evolucion2 = evolucion2;

        // COPIAR ATAQUES

        for (Ataque a : ataques) {

            copia.agregarAtaque(a);
        }

        // COPIAR ATAQUES POR NIVEL

        copia.ataquesPorNivel.putAll(
                ataquesPorNivel
        );

        return copia;
    }
}