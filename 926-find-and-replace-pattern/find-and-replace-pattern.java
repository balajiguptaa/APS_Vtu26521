import java.util.*;

class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();

        for (String word : words) {
            if (matches(word, pattern)) {
                result.add(word);
            }
        }

        return result;
    }

    private boolean matches(String word, String pattern) {
        if (word.length() != pattern.length()) return false;

        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();

        for (int i = 0; i < word.length(); i++) {
            char w = word.charAt(i);
            char p = pattern.charAt(i);

            // forward mapping
            if (map1.containsKey(p)) {
                if (map1.get(p) != w) return false;
            } else {
                map1.put(p, w);
            }

            // reverse mapping
            if (map2.containsKey(w)) {
                if (map2.get(w) != p) return false;
            } else {
                map2.put(w, p);
            }
        }

        return true;
    }
}