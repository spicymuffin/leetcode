class Solution {

    public static boolean isAlphaNumeric(char c) {
        return Character.isLetter(c) || Character.isDigit(c);
    }



    public boolean isPalindrome(String s) {
        int forward = 0;
        int backward = s.length() - 1;
        char[] arr = s.toCharArray();
        while (forward < backward) {
            while (forward < s.length() && !isAlphaNumeric(arr[forward])){
                forward++;
            } 
            while (backward >= 0 && !isAlphaNumeric(arr[backward])){
                backward--;
            }
            if (forward >= backward || forward >= s.length() || backward < 0) return true;
            if (Character.toLowerCase(arr[forward]) != Character.toLowerCase(arr[backward])) return false;
            forward++;
            backward--;
        }
        return true;
    }
}
