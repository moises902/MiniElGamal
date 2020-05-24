import java.math.BigInteger;

public class ElGamal
{
    public static void main(String [] args)
    {
        BigInteger prime = new BigInteger("42073");
        BigInteger primeRootAlpha = new BigInteger("1473");
        BigInteger xValue = new BigInteger("7011");
        BigInteger yValue = new BigInteger("4066");

        BigInteger alphaX = primeRootAlpha.modPow(xValue,prime); // alpha^x (mod p)
        BigInteger alphaY = primeRootAlpha.modPow(yValue,prime); // alpha^y (mod p)
        BigInteger key = alphaY.modPow(alphaX,prime); // alpha^(xy) (mod p)

        System.out.println("Public");
        System.out.println("-------");
        System.out.println("p = " + prime);
        System.out.println("Prime root alpha = " + primeRootAlpha);
        System.out.println("alpha^x: " + alphaX);
        System.out.println("alpha^y: " + alphaY);

        BigInteger message = new BigInteger("31415");
        BigInteger cipherText = key.multiply(message).mod(prime); // alpha^(x*y) * message (mod p)

        System.out.println();
        System.out.println("Ciphertext: " + cipherText);

        BigInteger inverse = modInverse(alphaX,prime).modPow(yValue,prime);// alpha^(-x*y) (mod p)
        BigInteger newCipherText = new BigInteger("17681");
        BigInteger decryptedMessage = inverse.multiply(cipherText).mod(prime); // alpha^(-x*y) * ciphertext (mod p)
        System.out.println("Decrypted Message:" + decryptedMessage);
    }

    //Method finds the modular multiplicative inverse, naively
    public static BigInteger modInverse(BigInteger alphaX, BigInteger modulo)
    {
        alphaX = alphaX.mod(modulo);
        BigInteger value = BigInteger.ONE;

        while (!value.equals(modulo.divide(BigInteger.valueOf(2))))
        {
            if ((alphaX.multiply(value).mod(modulo).equals(BigInteger.ONE)))
            {
                return value;
            }

            value = value.add(BigInteger.ONE);
        }

        return value;
    }
}
