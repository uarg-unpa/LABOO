package tp1;

import unpa.uarg.laboratorio.dao.CuentaDAO;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CuentaDAO dao = new CuentaDAO();
        int opcion, nro;

        do {
            System.out.println("\n--- SISTEMA BANCARIO UNPA-UARG ---");
            System.out.println("1. Agregar Cuenta");
            System.out.println("2. Borrar Cuenta");
            System.out.println("3. Modificar Cuenta");
            System.out.println("4. Buscar Cuenta");
            System.out.println("5. Mostrar Todas");
            System.out.println("0. Salir");
            System.out.print("Seleccione: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Número de cuenta: "); nro = sc.nextInt();
                    sc.nextLine(); // Limpiar buffer
                    System.out.print("Nombre Cliente: "); String nom = sc.nextLine();
                    System.out.print("Saldo Inicial: "); double sal = sc.nextDouble();
                    System.out.print("Tipo (A: Ahorro / C: Corriente): "); char tipo = sc.next().toUpperCase().charAt(0);
                    dao.agregar(nro, nom, sal, tipo);
                    break;
                case 2:
                    System.out.print("Número de cuenta a borrar: "); nro = sc.nextInt();
                    dao.borrar(nro);
                    break;
                case 3:
                    System.out.print("Número de cuenta a modificar: "); nro = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nuevo Nombre: "); String nNom = sc.nextLine();
                    System.out.print("Nuevo Saldo: "); double nSal = sc.nextDouble();
                    dao.modificar(nro, nNom, nSal);
                    break;
                case 4:
                    System.out.print("Ingrese número de cuenta: "); nro = sc.nextInt();
                    dao.buscar(nro);
                    break;
                case 5:
                    dao.mostrarTodas();
                    break;
            }
        } while (opcion != 0);
        sc.close();
    }
}