package PrimerParcialEsDatos;
import java.io.*;
import java.util.*;

public class PlataformaConsola {
    private static List<Integer> numeros = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            mostrarMenu();
            int opcion = obtenerEntrada();
            ejecutarOpcion(opcion);
        }
    }
//El Menu
    private static void mostrarMenu() {
        System.out.println("\n---* Menú Principal *---");
        System.out.println("1. Información del Desarrollador");
        System.out.println("2. Cargar datos desde CSV");
        System.out.println("3. Ordenar números");
        System.out.println("4. Buscar un número");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static int obtenerEntrada() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> mostrarInformacionDesarrollador();
            case 2 -> cargarDatosDesdeCSV();
            case 3 -> ordenarNumeros();
            case 4 -> buscarNumero();
            case 5 -> {
                System.out.println("Saliendo...");
                System.exit(0);
            }
            default -> System.out.println("Opción no válida. Intente de nuevo.");
        }
    }

    private static void mostrarInformacionDesarrollador() {
        System.out.println("\nDesarrollador: Nombre Apellido");
        System.out.println("Contacto: correo@ejemplo.com");
    }

    private static void cargarDatosDesdeCSV() {
        System.out.print("Ingrese el nombre del archivo CSV: ");
        String nombreArchivo = scanner.nextLine();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            numeros.clear();
            String linea;
            while ((linea = br.readLine()) != null) {
                for (String num : linea.split(",")) {
                    numeros.add(Integer.parseInt(num.trim()));
                }
            }
            System.out.println("Datos cargados correctamente.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer el archivo.");
        }
    }

    private static void ordenarNumeros() {
        if (numeros.isEmpty()) {
            System.out.println("No hay datos cargados.");
            return;
        }
        System.out.println("Seleccione un método de ordenamiento:");
        System.out.println("1. Bubble Sort\n2. Bubble Sort Mejorado\n3. Quick Sort\n4. Selection Sort\n5. Merge Sort");
        int metodo = obtenerEntrada();
        switch (metodo) {
            case 1 -> bubbleSort();
            case 2 -> bubbleSortMejorado();
            case 3 -> quickSort(0, numeros.size() - 1);
            case 4 -> selectionSort();
            case 5 -> mergeSort(0, numeros.size() - 1);
            default -> System.out.println("Método no válido.");
        }
        System.out.println("Lista ordenada: " + numeros);
    }

    private static void bubbleSort() {
        for (int i = 0; i < numeros.size() - 1; i++) {
            for (int j = 0; j < numeros.size() - 1 - i; j++) {
                if (numeros.get(j) > numeros.get(j + 1)) {
                    Collections.swap(numeros, j, j + 1);
                }
            }
        }
    }

    private static void bubbleSortMejorado() {
        boolean swapped;
        for (int i = 0; i < numeros.size() - 1; i++) {
            swapped = false;
            for (int j = 0; j < numeros.size() - 1 - i; j++) {
                if (numeros.get(j) > numeros.get(j + 1)) {
                    Collections.swap(numeros, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    private static void quickSort(int inicio, int fin) {
        if (inicio < fin) {
            int pi = particion(inicio, fin);
            quickSort(inicio, pi - 1);
            quickSort(pi + 1, fin);
        }
    }

    private static int particion(int inicio, int fin) {
        int pivote = numeros.get(fin);
        int i = inicio - 1;
        for (int j = inicio; j < fin; j++) {
            if (numeros.get(j) < pivote) {
                i++;
                Collections.swap(numeros, i, j);
            }
        }
        Collections.swap(numeros, i + 1, fin);
        return i + 1;
    }

    private static void selectionSort() {
        for (int i = 0; i < numeros.size() - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < numeros.size(); j++) {
                if (numeros.get(j) < numeros.get(minIdx)) {
                    minIdx = j;
                }
            }
            Collections.swap(numeros, i, minIdx);
        }
    }

    private static void mergeSort(int izquierda, int derecha) {
        if (izquierda < derecha) {
            int medio = (izquierda + derecha) / 2;
            mergeSort(izquierda, medio);
            mergeSort(medio + 1, derecha);
            merge(izquierda, medio, derecha);
        }
    }

    private static void merge(int izquierda, int medio, int derecha) {
        List<Integer> izquierdaLista = new ArrayList<>(numeros.subList(izquierda, medio + 1));
        List<Integer> derechaLista = new ArrayList<>(numeros.subList(medio + 1, derecha + 1));
        int i = 0, j = 0, k = izquierda;
        while (i < izquierdaLista.size() && j < derechaLista.size()) {
            if (izquierdaLista.get(i) <= derechaLista.get(j)) {
                numeros.set(k++, izquierdaLista.get(i++));
            } else {
                numeros.set(k++, derechaLista.get(j++));
            }
        }
        while (i < izquierdaLista.size()) numeros.set(k++, izquierdaLista.get(i++));
        while (j < derechaLista.size()) numeros.set(k++, derechaLista.get(j++));
    }

    private static void buscarNumero() {
        if (numeros.isEmpty()) {
            System.out.println("No hay datos cargados.");
            return;
        }
        System.out.print("Ingrese el número a buscar: ");
        int valor = obtenerEntrada();
        int resultado = Collections.binarySearch(numeros, valor);
        System.out.println(resultado >= 0 ? "Número encontrado." : "Número no encontrado.");
    }
}
