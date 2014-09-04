import java.math.BigInteger;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author H. Roy
 *
 */
public class Encoder {
	
	private static Map<Integer, byte[]> gammaTable = new HashMap<Integer, byte[]>();
	private static Map<Integer, byte[]> deltaTable = new HashMap<Integer, byte[]>();
	
	public static String intToGammaCode(int number) {
        if (number == 0) return "";
        String binary = Integer.toBinaryString(number);
        String offset = binary.substring(1);
        String unary = "";
        for (int i = 0; i < offset.length(); i++) unary = unary + "1";
        return unary + "0" + offset;
    }
	
    public static String intToDeltaCode(int number) {
        if (number == 0) return "";
        String binary = Integer.toBinaryString(number);
        return intToGammaCode(binary.length()) + binary.substring(1);
    }      

    public static byte[] gamma(int number) {
        if (gammaTable.containsKey(number)) {
            return gammaTable.get(number).clone();
        }
        byte[] gamma = new BigInteger(Encoder.intToGammaCode(number), 2).toByteArray();
        gammaTable.put(number, gamma.clone());
        return gamma;
    }   

    public static byte[] delta(int number) {
        if (deltaTable.containsKey(number)) {
            return deltaTable.get(number).clone();
        }
        byte[] delta = new BigInteger(Encoder.intToDeltaCode(number), 2).toByteArray();
        deltaTable.put(number, delta.clone());
        return delta;
    }
}
