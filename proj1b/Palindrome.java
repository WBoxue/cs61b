public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        int p = 0, q = word.length() - 1;
        while (p < q) {
            if (word.charAt(p) != word.charAt(q)) {
                return false;
            }
            p++;
            q--;
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int p = 0, q = word.length() - 1;
        while (p < q) {
            if (!cc.equalChars(word.charAt(p), word.charAt(q))) {
                return false;
            }
            p++;
            q--;
        }
        return true;
    }
}
