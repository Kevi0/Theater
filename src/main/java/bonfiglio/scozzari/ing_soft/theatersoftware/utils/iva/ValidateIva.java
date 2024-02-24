package bonfiglio.scozzari.ing_soft.theatersoftware.utils.iva;

public class ValidateIva {

    static String normalize(String pi)
    {
        return pi.replaceAll("[ \t\r\n]", "");
    }

    static String format(String pi)
    {
        return normalize(pi);
    }

    static String validate(String pi)
    {
        pi = normalize(pi);
        if(pi.isEmpty())
            return "Empty.";
        else if( pi.length() != 11 )
            return "Invalid length.";
        if( ! pi.matches("^[0-9]{11}$") )
            return "Invalid characters.";
        int s = 0;
        for(int i = 0; i < 11; i++){
            int n = pi.charAt(i) - '0';
            if( (i & 1) == 1 ){
                n *= 2;
                if( n > 9 )
                    n -= 9;
            }
            s += n;
        }
        if( s % 10 != 0 )
            return "Invalid checksum.";
        return null;
    }

    private static void test(String raw_cf)
    {
        String got_err = validate(raw_cf);
        System.out.println(got_err);
    }

}
