
public class Main {

    public static void main(String[] args) {

        String[] pruebas = {
            "7+10+2",
            "1+5*(7-1)+10",
            "5+7+(1+2*(8-9)+2)-10",
            "9-2*(75-9+(6+3+(1+2)*1)+7)+9"
        };

        for (String prueba : pruebas) {

            try {

                Calculadora calculadora =
                        new Calculadora(prueba);

                int resultado = calculadora.evaluar();

                System.out.println(
                    prueba + " = " + resultado
                );

            } catch (IllegalArgumentException e) {

                System.out.println(
                    "Error en " + prueba + ": "
                    + e.getMessage()
                );
            }
        }

        // Pruebas de expresiones incorrectas
        String[] errores = {
            "5++1+10",
            "-5+10",
            "74-7/7",
            "10+9-A"
        };

        System.out.println("\nPruebas de errores:");

        for (String prueba : errores) {

            try {

                Calculadora calculadora =
                        new Calculadora(prueba);

                System.out.println(
                    prueba + " = " + calculadora.evaluar()
                );

            } catch (IllegalArgumentException e) {

                System.out.println(
                    prueba + " -> Excepcion: "
                    + e.getMessage()
                );
            }
        }
    }
}
