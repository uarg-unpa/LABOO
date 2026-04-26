package proyectJDBC;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CuentaDAO dao = new CuentaDAO();
        int opcion, nro;

        do {
            System.out.println("\n--- LABORATORIO DE PROGRAMACIÓN - TP1 ---");
            System.out.println("1. Agregar Cuenta");
            System.out.println("2. Borrar Cuenta");
            System.out.println("3. Modificar Cuenta");
            System.out.println("4. Buscar Cuenta");
            System.out.println("5. Mostrar Todas");
            System.out.println("6. Depositar (Transacción)");
            System.out.println("7. Extraer (Transacción)");
            System.out.println("0. Salir");
            System.out.print("Elija opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Nro Cuenta: "); nro = sc.nextInt(); sc.nextLine();
                    System.out.print("Nombre: "); String nom = sc.nextLine();
                    System.out.print("Saldo: "); double sal = sc.nextDouble();
                    System.out.print("Tipo (A/C): "); char tip = sc.next().toUpperCase().charAt(0);
                    dao.agregar(nro, nom, sal, tip);
                    break;
                case 2:
                    System.out.print("Nro Cuenta a borrar: ");
                    dao.borrar(sc.nextInt());
                    break;
                case 3:
                    System.out.print("Nro Cuenta a modificar: "); nro = sc.nextInt(); sc.nextLine();
                    System.out.print("Nuevo Nombre: "); String nNom = sc.nextLine();
                    System.out.print("Nuevo Saldo: "); double nSal = sc.nextDouble();
                    dao.modificar(nro, nNom, nSal);
                    break;
                case 4:
                    System.out.print("Nro Cuenta a buscar: ");
                    dao.buscar(sc.nextInt());
                    break;
                case 5:
                    dao.mostrarTodas();
                    break;
                case 6:
                    System.out.print("Cuenta: "); nro = sc.nextInt();
                    System.out.print("Importe a depositar: ");
                    dao.depositar(nro, sc.nextDouble());
                    break;
                case 7:
                    System.out.print("Cuenta: "); nro = sc.nextInt();
                    System.out.print("Importe a extraer: ");
                    dao.extraer(nro, sc.nextDouble());
                    break;
            }
        } while (opcion != 0);
        sc.close();
    }
}