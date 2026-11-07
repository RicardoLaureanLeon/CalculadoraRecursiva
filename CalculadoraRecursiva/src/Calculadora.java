
public class Calculadora {

    private String expresion;
    private int posicion;

    public Calculadora(String expresion) {
        this.expresion = expresion.replaceAll("\\s+", "");
        this.posicion = 0;
    }

    // Metodo principal para evaluar la expresion
    public int evaluar() {

        int resultado = expresion();

        // Verificar que no sobren caracteres
        if (posicion != expresion.length()) {
            throw new IllegalArgumentException(
                "Expresion incorrecta: " + expresion
            );
        }

        return resultado;
    }

    // Procesa sumas y restas
    private int expresion() {

        int resultado = termino();

        while (posicion < expresion.length()) {

            char operador = expresion.charAt(posicion);

            if (operador == '+') {
                posicion++;
                resultado += termino();

            } else if (operador == '-') {
                posicion++;
                resultado -= termino();

            } else {
                break;
            }
        }

        return resultado;
    }

    // Procesa multiplicaciones
    private int termino() {

        int resultado = factor();

        while (posicion < expresion.length()
                && expresion.charAt(posicion) == '*') {

            posicion++;
            resultado *= factor();
        }

        return resultado;
    }

    // Procesa numeros y parentesis
    private int factor() {

        if (posicion >= expresion.length()) {
            throw new IllegalArgumentException(
                "Falta un numero en la expresion"
            );
        }

        char actual = expresion.charAt(posicion);

        // Si encuentra un parentesis, realiza otra llamada recursiva
        if (actual == '(') {

            posicion++;

            int resultado = expresion();

            if (posicion >= expresion.length()
                    || expresion.charAt(posicion) != ')') {

                throw new IllegalArgumentException(
                    "Falta cerrar un parentesis"
                );
            }

            posicion++;

            return resultado;
        }

        // Leer numeros de varios digitos
        if (Character.isDigit(actual)) {

            int numero = 0;

            while (posicion < expresion.length()
                    && Character.isDigit(
                        expresion.charAt(posicion))) {

                numero = numero * 10
                        + (expresion.charAt(posicion) - '0');

                posicion++;
            }

            return numero;
        }

        throw new IllegalArgumentException(
            "Caracter inesperado: " + actual
        );
    }
}
