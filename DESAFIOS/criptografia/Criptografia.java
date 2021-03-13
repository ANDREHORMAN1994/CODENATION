package criptografia;

public class Criptografia {

    private static String alfNormal = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String alfCripto = "DEFGHIJKLMNOPQRSTUVWXYZABC";
    private static String alfNormalLowerCase = alfNormal.toLowerCase();
    private static String alfCriptoLowerCase = alfCripto.toLowerCase();
    private static char[] arrayAlfNormal = alfNormalLowerCase.toCharArray();
    private static char[] arrayAlfCripto = alfCriptoLowerCase.toCharArray();
    private static String numbers = "0123456789";
    private static char[] arrayNumbers = numbers.toCharArray();
    private static char[] alf1;
    private static char[] alf2;

    private static String criptografar(String texto) {
        return criptografando(texto, true);
    }

    private static String descriptografar(String texto) {
        return criptografando(texto, false);
    }

    private static String criptografando(String frase, boolean criptografar) {
        String fraseLowerCase = frase.toLowerCase();
        String result = new String();

        if (criptografar) {
            alf1 = arrayAlfNormal;
            alf2 = arrayAlfCripto;
        } else {
            alf1 = arrayAlfCripto;
            alf2 = arrayAlfNormal;
        }

        if (frase == null) throw new NullPointerException();
        if (frase == "") throw new IllegalArgumentException();
        for (int i = 0; i < frase.length(); i++) {
            char[] arrayCharacter = fraseLowerCase.toCharArray();
            for (int j = 0; j < alf1.length; j++) {
                if (arrayCharacter[i] == alf1[j]) result += alf2[j];
            }
            if (arrayCharacter[i] == ' ') {
                result += " ";
            } else {
                for (char num : arrayNumbers) {
                    if (arrayCharacter[i] == num) result += arrayCharacter[i];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String vazia = null;
//        System.out.println(criptografar(vazia));

        String frase = "a ligeira raposa marrom saltou sobre o cachorro cansado ANDRE1994";
//        resultado = d oljhlud udsrvd pduurp vdowrx vreuh r fdfkruur fdqvdgr

        System.out.println(criptografar(frase));
        System.out.println(descriptografar(criptografar(frase)));
    }
}
