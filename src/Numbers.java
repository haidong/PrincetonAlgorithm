
public class Numbers {

    public static String stringValue(int n, int base) {
        if (n < 0)
            return "-" + stringValue(-n, base);
        // if (n / base == 0)
        if (n < base)
            // return "" + n;
            return "0123456780".substring(n, n + 1);
        return stringValue(n / base, base) + "0123456789".charAt(n % base);
    }

}
