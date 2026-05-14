import java.io.Serializable;
public class Ataque implements Serializable {

    String nombre;
    int poder;
    Tipo tipo;

    public Ataque(
            String nombre,
            int poder,
            Tipo tipo) {

        this.nombre = nombre;
        this.poder = poder;
        this.tipo = tipo;
    }

}