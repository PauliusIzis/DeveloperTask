package generator;

public class Main implements Command {

    static final int KOEF_A = 16807;
    static final int KOEF_B = 48271;
    static final int LOOP_COUNT = 1000000;
    static final int DIVISOR = Integer.MAX_VALUE;

    public static void main(String[] args) {
        callCommand(new Main(), args[0], args[1]);
    }

    @Override
    public void execute(Object dataA, Object dataB) {
        String convertedStringA = dataA.toString();
        String convertedStringB = dataB.toString();
        System.out.println(generator(Long.parseLong(convertedStringA), Long.parseLong(convertedStringB)));
    }

    private static void callCommand(Command command, Object dataA, Object dataB) {
        command.execute(dataA, dataB);
    }

    public static int generator(long numA, long numB) {
        int count = 0;
        for (int i = 0; i < LOOP_COUNT; i++) {
            numA = (numA * KOEF_A) % DIVISOR;
            numB = (numB * KOEF_B) % DIVISOR;
            if (first8bitsEqual(numA, numB)) {
                count++;
            }
        }
        return count;
    }

    private static boolean first8bitsEqual(long numA, long numB) {
        String binaryA = convertToBinaryOfAtleast8bits(numA);
        String binaryB = convertToBinaryOfAtleast8bits(numB);
        return binaryA.substring(binaryA.length() - 8).equals(binaryB.substring(binaryB.length() - 8));
    }

    private static String convertToBinaryOfAtleast8bits(long num) {
        String binary = Long.toBinaryString(num);
        while (binary.length() < 8) {
            binary = "0".concat(binary);
        }
        return binary;
    }
}


