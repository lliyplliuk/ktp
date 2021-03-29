public class Lab1 {
    public static void main(String[] args) {
        for (int i = 3; i < 100; i++) {
            if(isPrime(i))
                System.out.println(i);
        }
    }

    public static boolean isPrime(int n) {
        boolean prime=true;
        if(n>1) {
            for (int i = 2; i < n; i++) {
                if (prime)
                    prime = (n % i != 0);
                else
                    break;
            }
        }
        return prime;
    }
}
