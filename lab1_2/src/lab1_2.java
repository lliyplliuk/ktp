public class lab1_2 {
    /**
     * @param args Обрабатываем получаемы параметры
     */
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            System.out.println(isPalindrome(s));
        }
    }

    /** Получаем реверсивную строку
     * @param s
     * @return
     */
    public static String reverseString(String s) {
        String ret = "";
        for (int i = s.length()-1; i >= 0; i--) {
            ret += s.charAt(i);
        }
        return ret;
    }

    /** Проверка на палиндром
     * @param s
     * @return
     */
    public static Boolean isPalindrome(String s){
        return s.equals(reverseString(s));
    }


}
