import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.*;

/**
 * Created by L. Keijzer on 2017-04-08.
 */
public class Main {

    private final Character[] VALID_CHARS_ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '_', '0',
            '1', '2', '3', '4', '5', '6', '7', '8', '9', ',', '.', '?', '!', '|', '(', ')', '\\', '\'', ':', ';',
            '"', '/', '-', '=', '+', '*',};
    private final Character[] VALID_CHARS_BASE = {'.', '\'', ':', '*', '/', '\\', '-', '?', '+', '_'};   // 10 Characters
    // for max base of 10

    private boolean[] isValid;
    private boolean isAlphabet = true;
    private boolean isBase3 = true;

    private static int mAmountCharacters = 4;
    private static int mBase = 3;

    private Scanner mScanner;
    private StringBuilder mBuilder = new StringBuilder();
    private StringBuilder mSmallBuilder = new StringBuilder();
    private StringSelection mStringSelection;

    public Main() {
        mScanner = new Scanner(System.in);

        Map[] maps = genMaps();
        // Map from alphabet to base
        final Map<Character, String> mMapAB = maps[0];
        // Map from base to alphabet
        final Map<String, Character> mMapBA = maps[1];

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
            for (int i = 0; i < mBase; i++) {
                isValid[VALID_CHARS_BASE[i]] = true;
            }
            for (char c : inputChars) {
                if (!isValid[c]) {
                    isBase3 = false;
                    break;
                }
            }

            if (isAlphabet || isBase3) {
                mBuilder.replace(0, mBuilder.length(), "");
                // VALID_CHARS_BASE is subset of VALID_CHARS_ALPHABET
                if (isBase3) {
                    mSmallBuilder.replace(0, mSmallBuilder.length(), "");
                    int count = 0;
                    for (char c : inputChars) {
                        count++;
                        mSmallBuilder.append(c);
                        if (count == mAmountCharacters) {
                            mBuilder.append(mMapBA.get(mSmallBuilder.toString()));
                            mSmallBuilder.replace(0, mSmallBuilder.length(), "");
                            count = 0;
                        }
                    }
                } else if (isAlphabet) {
                    for (char c : inputChars) {
                        mBuilder.append(mMapAB.get(c));
                    }
                    Random random = new Random();
                    int amount = random.nextInt(mAmountCharacters);
                    for (int i = 0; i < amount; i++) {
                        mBuilder.append(VALID_CHARS_BASE[random.nextInt(mBase)]);
                    }
                }
                System.out.println("Output: " + mBuilder.toString());

                mStringSelection = new StringSelection(mBuilder.toString());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(mStringSelection, mStringSelection);

                System.out.println("Copied to clipboard");
            } else {
                System.out.println("Not alphabet or base 3!");
            }
        }
    }

    /**
     * Method that generates two Maps.
     * {@code Map[0]} contains {@code mapAB}, a mapping from alphanumeric to base encoded.
     * {@code Map[1]} contains {@code mapBA}, a mapping from base to alphanumeric encoded.
     * @return {@code Map[]} containing two Maps that are the same but the direction inverted
     */
    private Map[] genMaps() {
        Map<Character, String> mapAB = new HashMap<>();
        Map<String, Character> mapBA = new HashMap<>();

        int amountOfCharactersPossible = (int) Math.pow(mBase, mAmountCharacters);
        for (int i = 0; i < Math.min(amountOfCharactersPossible, VALID_CHARS_ALPHABET.length); i++) {
            Character character = VALID_CHARS_ALPHABET[i];
            StringBuilder stringy = new StringBuilder();
            int remaining = i;
            for (int j = mAmountCharacters - 1; j >= 0; j--) {
                int value = (int) Math.pow(mBase, j); // Get the values of the jth character
                int amount = 0;
                while (remaining - value >= 0) {
                    remaining -= value;
                    amount++;
                }
                stringy.append(VALID_CHARS_BASE[amount]);
            }
            mapAB.put(character, stringy.toString());
            mapBA.put(stringy.toString(), character);
            System.out.println("adding: " + character + " : " + stringy.toString());
        }
        return new Map[]{mapAB, mapBA};
    }

    private static void setBase(int base) {
        if (base > 1 && base <= 10) {
            mBase = base;
            System.out.println("Set base to " + mBase);
        } else {
            System.out.println("Base of " + base + " not between 2 and 10.");
        }
    }

    private static void setCharacters(int amount) {
        if (amount > 1 && amount <= 10) {
            mAmountCharacters = amount;
            System.out.println("Set amount of characters to " + mAmountCharacters);
        } else {
            System.out.println("Amount of characters of " + amount + " not between 2 and 10.");
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case Constants.ARG_BASE:
                        if (args.length > i + 1) {
                            try {
                                setBase(Integer.parseInt(args[i + 1]));
                            } catch (NumberFormatException e) {
                                System.out.println("Arg was wrong, not setting the base.");
                            }
                        } else {
                            System.out.println("Missing base variable");
                        }
                        break;
                    case Constants.ARG_AMOUNT_CHARACTERS:
                        if (args.length > i + 1) {
                            try {
                                setCharacters(Integer.parseInt(args[i + 1]));
                            } catch (NumberFormatException e) {
                                System.out.println("Arg was wrong, not setting the amount of characters.");
                            }
                        } else {
                            System.out.println("Missing amount of characters variable");
                        }
                        break;
                }
            }
        }

        new Main();
    }
}
