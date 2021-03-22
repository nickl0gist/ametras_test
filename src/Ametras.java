import java.util.*;

/**
 * Created on 22.03.2021
 * @author Mykola Horkov
 * mykola.horkov@gmail.com
 */
public class Ametras {

    private static final String[] STRINGS_TO_CHECK1 = {
            "01632532948375",
            "01631234567890",
            "06219912345678",
            "062199B23RET78",
            "12345",
            "12345678901234567890123456789"
    };

    private static final Map<Character, Integer> TABLE = new HashMap<>();

    private static final Integer MOD = 36;

    static {
        TABLE.put('0', 0);
        TABLE.put('1', 1);
        TABLE.put('2', 2);
        TABLE.put('3', 3);
        TABLE.put('4', 4);
        TABLE.put('5', 5);
        TABLE.put('6', 6);
        TABLE.put('7', 7);
        TABLE.put('8', 8);
        TABLE.put('9', 9);
        TABLE.put('A', 10);
        TABLE.put('B', 11);
        TABLE.put('C', 12);
        TABLE.put('D', 13);
        TABLE.put('E', 14);
        TABLE.put('F', 15);
        TABLE.put('G', 16);
        TABLE.put('H', 17);
        TABLE.put('I', 18);
        TABLE.put('J', 19);
        TABLE.put('K', 20);
        TABLE.put('L', 21);
        TABLE.put('M', 22);
        TABLE.put('N', 23);
        TABLE.put('O', 24);
        TABLE.put('P', 25);
        TABLE.put('Q', 26);
        TABLE.put('R', 27);
        TABLE.put('S', 28);
        TABLE.put('T', 29);
        TABLE.put('U', 30);
        TABLE.put('V', 31);
        TABLE.put('W', 32);
        TABLE.put('X', 33);
        TABLE.put('Y', 34);
        TABLE.put('Z', 35);
    }

    public static void main(String[] args) {

        Arrays.stream(STRINGS_TO_CHECK1).forEach( str -> {
            Integer cd = calculateCheckDigit(str);
            System.out.println(getCheckedValue(cd));
                }
        );
    }


    /**
     * @param stringToCheck - String object to check
     * @return - Calculated Integer value
     */
    private static Integer calculateCheckDigit(String stringToCheck) {

        Integer cd = MOD;

        Character[] charArray = stringToCheck.toUpperCase().chars()
                .mapToObj(ch -> (char) ch)
                .toArray(Character[]::new);

        for (Character c :charArray){
            cd = cd + TABLE.get(c);

            if (cd > MOD) {
                cd = cd - MOD;
            }

            cd = cd * 2;

            if (cd > MOD) {
                cd = cd - MOD - 1;
            }
        }

        cd = MOD + 1 - cd;

        if (cd.equals(MOD))
            cd = 0;

        return cd;
    }

    /**
     * @param cd - Code Digit to to be inverted to Character
     * @return - Character value calculated from given Integer Digit Code.
     */
    private static Character getCheckedValue(Integer cd){
        return  TABLE.entrySet()
                .stream()
                .filter(entry -> cd.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

}
