package radixsort;
import java.util.Arrays;
public class RadixSort {
	// Función para obtener el dígito en una posición específica
    static int getDigit(int number, int position) {
        return (number / (int) Math.pow(10, position)) % 10;
    }

    // Función para encontrar el valor máximo en el array
    static int findMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    // Función principal del algoritmo Radix Sort
    static void radixSort(int[] array) {
        // Registra el tiempo de inicio
        long startTime = System.currentTimeMillis();

        // Encuentra el valor máximo en el array
        int max = findMax(array);

        // Itera sobre cada posición del dígito (de derecha a izquierda)
        for (int position = 0; max / (int) Math.pow(10, position) > 0; position++) {
            // Llama a countingSort para ordenar en la posición actual
            countingSort(array, position);

            // Imprime el estado actual del array después de la iteración
            System.out.println("Iteración " + (position + 1) + ": ");
            printArray(array);
        }

        // Registra el tiempo de finalización
        long endTime = System.currentTimeMillis();

        // Calcula y muestra el tiempo total de ejecución
        long duration = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + duration + " ms");
    }

    // Función de ordenación usando Counting Sort en el dígito específico
    static void countingSort(int[] array, int position) {
        int[] output = new int[array.length];
        int[] count = new int[10];

        // Inicializa el array de conteo
        Arrays.fill(count, 0);

        // Cuenta la frecuencia de cada dígito en la posición actual
        for (int i = 0; i < array.length; i++) {
            count[getDigit(array[i], position)]++;
        }

        // Ajusta el array de conteo para tener las posiciones correctas
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Construye el array de salida usando el array de conteo
        for (int i = array.length - 1; i >= 0; i--) {
            output[count[getDigit(array[i], position)] - 1] = array[i];
            count[getDigit(array[i], position)]--;
        }

        // Copia el array de salida al array original
        System.arraycopy(output, 0, array, 0, array.length);
    }

    // Función para imprimir el estado actual del array
    static void printArray(int[] array) {
        for (int item : array) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    // Programa principal para probar Radix Sort
    public static void main(String[] args) {
        int[] array = {170, 45, 75, 90, 802, 24, 2, 66};
        System.out.println("Array original:");
        printArray(array);

        // Llama a radixSort para ordenar el array
        radixSort(array);

        System.out.println("\nArray ordenado:");
        printArray(array);
    }
}
