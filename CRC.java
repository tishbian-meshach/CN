import java.util.Scanner;

class CRC {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Input data
        System.out.println("Enter the data bits:");
        int[] data = readBits(input);

        // Input divisor
        System.out.println("Enter the divisor bits:");
        int[] divisor = readBits(input);

        // Generate CRC code
        int[] crc = divide(data, divisor);
        System.out.println("Generated CRC code:");
        printBits(data);
        printBits(crc, crc.length - 1); // Exclude the last bit for CRC code

        // Prepare transmitted data
        int[] transmittedData = new int[data.length + crc.length - 1];
        System.arraycopy(data, 0, transmittedData, 0, data.length);
        System.arraycopy(crc, 0, transmittedData, data.length, crc.length - 1);

        // Check received data
        System.out.println("Checking received data...");
        checkData(transmittedData, divisor);

        input.close();
    }

    private static int[] readBits(Scanner input) {
        System.out.print("Enter the number of bits: ");
        int size = input.nextInt();
        int[] bits = new int[size];
        System.out.println("Enter the bits:");
        for (int i = 0; i < size; i++) {
            bits[i] = input.nextInt();
        }
        return bits;
    }

    private static int[] divide(int[] data, int[] divisor) {
        int[] tempData = new int[data.length + divisor.length - 1];
        System.arraycopy(data, 0, tempData, 0, data.length);

        int[] remainder = new int[divisor.length];
        System.arraycopy(tempData, 0, remainder, 0, divisor.length);

        for (int i = 0; i < data.length; i++) {
            if (remainder[0] == 1) {
                for (int j = 1; j < divisor.length; j++) {
                    remainder[j - 1] = xor(remainder[j], divisor[j]);
                }
            } else {
                for (int j = 1; j < divisor.length; j++) {
                    remainder[j - 1] = xor(remainder[j], 0);
                }
            }
            remainder[divisor.length - 1] = (i + divisor.length < tempData.length) ? tempData[i + divisor.length] : 0;
        }
        return remainder;
    }

    private static int xor(int a, int b) {
        return (a == b) ? 0 : 1;
    }

    private static void checkData(int[] data, int[] divisor) {
        int[] remainder = divide(data, divisor);
        boolean error = false;
        for (int bit : remainder) {
            if (bit != 0) {
                error = true;
                break;
            }
        }
        System.out.println(error ? "Error in received data." : "Data received without error.");
    }

    private static void printBits(int[] bits) {
        for (int bit : bits) {
            System.out.print(bit);
        }
        System.out.println();
    }

    private static void printBits(int[] bits, int limit) {
        for (int i = 0; i < limit; i++) {
            System.out.print(bits[i]);
        }
        System.out.println();
    }
}
