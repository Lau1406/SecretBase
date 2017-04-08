import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by L. Keijzer on 2017-04-08.
 */
public class Main {
    /**
     * base 3 zero = .
     * base 3 one = '
     * base 3 two = :
     */

    // Map from alphabet to base
    private final Map<Character, String> mMapAB = new HashMap<Character, String>() {
        {
            put('a', "....");
            put('b', "...'");
            put('c', "...:");
            put('d', "..'.");
            put('e', "..''");
            put('f', "..':");
            put('g', "..:.");
            put('h', "..:'");
            put('i', "..::");
            put('j', ".'..");
            put('k', ".'.'");
            put('l', ".'.:");
            put('m', ".''.");
            put('n', ".'''");
            put('o', ".'':");
            put('p', ".':.");
            put('q', ".':'");
            put('r', ".'::");
            put('s', ".:..");
            put('t', ".:.'");
            put('u', ".:.:");
            put('v', ".:'.");
            put('w', ".:''");
            put('x', ".:':");
            put('y', ".::.");
            put('z', ".::'");
            put(' ', ".:::");

            put('A', "'...");
            put('B', "'..'");
            put('C', "'..:");
            put('D', "'.'.");
            put('E', "'.''");
            put('F', "'.':");
            put('G', "'.:.");
            put('H', "'.:'");
            put('I', "'.::");
            put('J', "''..");
            put('K', "''.'");
            put('L', "''.:");
            put('M', "'''.");
            put('N', "''''");
            put('O', "''':");
            put('P', "'':.");
            put('Q', "'':'");
            put('R', "''::");
            put('S', "':..");
            put('T', "':.'");
            put('U', "':.:");
            put('V', "':'.");
            put('W', "':''");
            put('X', "':':");
            put('Y', "'::.");
            put('Z', "'::'");
            put('_', "':::");

            put('0', ":...");
            put('1', ":..'");
            put('2', ":..:");
            put('3', ":.'.");
            put('4', ":.''");
            put('5', ":.':");
            put('6', ":.:.");
            put('7', ":.:'");
            put('8', ":.::");
            put('9', ":'..");
            put(',', ":'.'");
            put('.', ":'.:");
            put('?', ":''.");
            put('!', ":'''");
            put('@', ":'':");
            put(':', ":':.");
            put('|', ":':'");
            put('\\', ":'::");
            put('\'', "::..");
            put(':', "::.'");
            put(';', "::.:");
            put('"', "::'.");
            put('/', "::''");
            put('-', "::':");
            put('=', ":::.");
            put('+', ":::'");
            put('*', "::::");
        }
    };

    // Map from base to alphabet
    private final Map<String, Character> mMapBA = new HashMap<String, Character>() {
        {
            put("....", 'a');
            put("...'", 'b');
            put("...:", 'c');
            put("..'.", 'd');
            put("..''", 'e');
            put("..':", 'f');
            put("..:.", 'g');
            put("..:'", 'h');
            put("..::", 'i');
            put(".'..", 'j');
            put(".'.'", 'k');
            put(".'.:", 'l');
            put(".''.", 'm');
            put(".'''", 'n');
            put(".'':", 'o');
            put(".':.", 'p');
            put(".':'", 'q');
            put(".'::", 'r');
            put(".:..", 's');
            put(".:.'", 't');
            put(".:.:", 'u');
            put(".:'.", 'v');
            put(".:''", 'w');
            put(".:':", 'x');
            put(".::.", 'y');
            put(".::'", 'z');
            put(".:::", ' ');

            put("'...", 'A');
            put("'..'", 'B');
            put("'..:", 'C');
            put("'.'.", 'D');
            put("'.''", 'E');
            put("'.':", 'F');
            put("'.:.", 'G');
            put("'.:'", 'H');
            put("'.::", 'I');
            put("''..", 'J');
            put("''.'", 'K');
            put("''.:", 'L');
            put("'''.", 'M');
            put("''''", 'N');
            put("''':", 'O');
            put("'':.", 'P');
            put("'':'", 'Q');
            put("''::", 'R');
            put("':..", 'S');
            put("':.'", 'T');
            put("':.:", 'U');
            put("':'.", 'V');
            put("':''", 'W');
            put("':':", 'X');
            put("'::.", 'Y');
            put("'::'", 'Z');
            put("':::", '_');

            put(":...", '0');
            put(":..'", '1');
            put(":..:", '2');
            put(":.'.", '3');
            put(":.''", '4');
            put(":.':", '5');
            put(":.:.", '6');
            put(":.:'", '7');
            put(":.::", '8');
            put(":'..", '9');
            put(":'.'", ',');
            put(":'.:", '.');
            put(":''.", '?');
            put(":'''", '!');
            put(":'':", '@');
            put(":':.", ':');
            put(":':'", '|');
            put(":'::", '\\');
            put("::..", '\'');
            put("::.'", ':');
            put("::.:", ';');
            put("::'.", '"');
            put("::''", '/');
            put("::':", '-');
            put(":::.", '=');
            put(":::'", '+');
            put("::::", '*');
        }
    };

    private final char[] VALID_CHARS_ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '_', '0',
            '1', '2', '3', '4', '5', '6', '7', '8', '9', ',', '.', '?', '!', '@', ':', '|', '\\', '\'', ':', ';',
            '"', '/', '-', '=', '+', '*',};
    private final char[] VALID_CHARS_BASE_3 = {'.', '\'', ':'};

    private boolean[] isValid;
    private boolean isAlphabet = true;
    private boolean isBase3 = true;

    private Scanner mScanner;
    private StringBuilder mBuilder = new StringBuilder();
    private StringBuilder mSmallBuilder = new StringBuilder();

    public Main() {
        mScanner = new Scanner(System.in);

        while (true) {
            System.out.println("Input please: ");
            char[] inputChars = mScanner.nextLine().toCharArray();

            isAlphabet = true;
            isBase3 = true;

            // Check if is  a valid alphabet
            isValid = new boolean[Character.MAX_VALUE + 1];
            for (char c : VALID_CHARS_ALPHABET) {
                isValid[c] = true;
            }
            for (char c : inputChars) {
                if (!isValid[c]) {
                    isAlphabet = false;
                    break;
                }
            }

            // Check if is  a valid base 3
            isValid = new boolean[Character.MAX_VALUE + 1];
            for (char c : VALID_CHARS_BASE_3) {
                isValid[c] = true;
            }
            for (char c : inputChars) {
                if (!isValid[c]) {
                    isBase3 = false;
                    break;
                }
            }

            if (isAlphabet || isBase3) {
                mBuilder.replace(0, mBuilder.length(), "");
                // VALID_CHARS_BASE_3 is subset of VALID_CHARS_ALPHABET
                if (isBase3) {
                    mSmallBuilder.replace(0, mSmallBuilder.length(), "");
                    int count = 0;
                    for (char c : inputChars) {
                        count++;
                        mSmallBuilder.append(c);
                        if (count == 4) {
                            mBuilder.append(mMapBA.get(mSmallBuilder.toString()));
                            mSmallBuilder.replace(0, mSmallBuilder.length(), "");
                            count = 0;
                        }
                    }
                    System.out.println("Output: " + mBuilder.toString());
                } else if (isAlphabet) {
                    for (char c : inputChars) {
                        mBuilder.append(mMapAB.get(c));
                    }
                    System.out.println("Output: " + mBuilder.toString());
                }
            } else {
                System.out.println("Not alphabet or base 3!");
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
