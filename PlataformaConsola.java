package PrimerParcialEsDatos;
import java.io.*;
import java.util.*;

public class PlataformaConsola {
    private static List<Integer> numeros = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    // Datos fijos del proyecto
    private static final String UNIVERSIDAD = "Universidad Da Vinci de Guatemala";
    private static final String CURSO = "Estructura de Datos";
    private static final String DOCENTE = "Ing. Brandon Chitay";

    // Variables para almacenar la información del desarrollador
    private static String nombreDesarrollador = "";
    private static String contactoDesarrollador = "";

    // Para mantener el estado de los puntos completados y faltantes
    private static Set<Integer> puntosCompletados = new HashSet<>();
    private static Set<Integer> puntosFaltantes = new HashSet<>();

    public static void main(String[] args) {
        while (true) {
            mostrarMenu();
            int opcion = obtenerEntrada();
            ejecutarOpcion(opcion);
        }
    }

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
        if (nombreDesarrollador.isEmpty() || contactoDesarrollador.isEmpty()) {
            System.out.print("\nIngrese su nombre como desarrollador: ");
            nombreDesarrollador = scanner.nextLine();
            System.out.print("Ingrese su contacto: ");
            contactoDesarrollador = scanner.nextLine();
        }

        System.out.println("\n========================================");
        System.out.println("Desarrollador: " + nombreDesarrollador);
        System.out.println("Contacto: " + contactoDesarrollador);
        System.out.println("Universidad: " + UNIVERSIDAD);
        System.out.println("Curso: " + CURSO);
        System.out.println("Docente: " + DOCENTE);
        System.out.println("========================================");

        // Después de ingresar la información, mostrar los pasos completados y faltantes
        puntosCompletados.add(1); // El punto 1 está completo
        puntosFaltantes.remove(1); // El punto 1 ya no está faltando

        mostrarEstadoProyecto();

        // Esperar que el usuario presione Enter, si no le dpy enter no me muestra otra vez el menu
        System.out.print("-- Presione Enter para continuar --");
        scanner.nextLine();
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
            puntosCompletados.add(2); // El punto 2 está completo
            puntosFaltantes.remove(2); // El punto 2 ya no está faltando
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer el archivo.");
        }

        mostrarEstadoProyecto();
        // Esperar que el usuario presione Enter
        System.out.print("-- Presione Enter para continuar --");
        scanner.nextLine();
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
        puntosCompletados.add(3); // El punto 3 está completo
        puntosFaltantes.remove(3); // El punto 3 ya no está faltando

        mostrarEstadoProyecto();
        // Esperar que el usuario presione Enter
        System.out.print("-- Presione Enter para continuar --");
        scanner.nextLine();
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
        puntosCompletados.add(4); // El punto 4 está completo
        puntosFaltantes.remove(4); // El punto 4 ya no está faltando

        mostrarEstadoProyecto();
        // Esperar que el usuario presione Enter
        System.out.print("-- Presione Enter para continuar --");
        scanner.nextLine();
    }

    private static void mostrarEstadoProyecto() {
        // Mostrar los puntos completados y faltantes con los emojis
        System.out.println("\nEstado del Proyecto:");
        for (int i = 1; i <= 4; i++) {
            if (puntosCompletados.contains(i)) {
                System.out.println("Punto " + i + " ✅");
            } else if (puntosFaltantes.contains(i)) {
                System.out.println("Punto " + i + " ❌");
            }
        }
    }

    // Métodos de ordenamiento

    // Bubble Sort
    private static void bubbleSort() {
        for (int i = 0; i < numeros.size() - 1; i++) {
            for (int j = 0; j < numeros.size() - i - 1; j++) {
                if (numeros.get(j) > numeros.get(j + 1)) {
                    Collections.swap(numeros, j, j + 1);
                }
            }
        }
    }

    // Bubble Sort Mejorado
    private static void bubbleSortMejorado() {
        boolean intercambio;
        for (int i = 0; i < numeros.size() - 1; i++) {
            intercambio = false;
            for (int j = 0; j < numeros.size() - i - 1; j++) {
                if (numeros.get(j) > numeros.get(j + 1)) {
                    Collections.swap(numeros, j, j + 1);
                    intercambio = true;
                }
            }
            if (!intercambio) break;
        }
    }

    // Quick Sort
    private static void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private static int partition(int low, int high) {
        int pivot = numeros.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (numeros.get(j) <= pivot) {
                i++;
                Collections.swap(numeros, i, j);
            }
        }
        Collections.swap(numeros, i + 1, high);
        return i + 1;
    }

    // Selection Sort
    private static void selectionSort() {
        for (int i = 0; i < numeros.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < numeros.size(); j++) {
                if (numeros.get(j) < numeros.get(minIndex)) {
                    minIndex = j;
                }
            }
            Collections.swap(numeros, i, minIndex);
        }
    }

    // Merge Sort
    private static void mergeSort(int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            merge(left, mid, right);
        }
    }

    private static void merge(int left, int mid, int right) {
        List<Integer> temp = new ArrayList<>(numeros.subList(left, right + 1));
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (temp.get(i - left) <= temp.get(j - left)) {
                numeros.set(k++, temp.get(i++ - left));
            } else {
                numeros.set(k++, temp.get(j++ - left));
            }
        }
        while (i <= mid) {
            numeros.set(k++, temp.get(i++ - left));
        }
        while (j <= right) {
            numeros.set(k++, temp.get(j++ - left));
        }
    }
}


