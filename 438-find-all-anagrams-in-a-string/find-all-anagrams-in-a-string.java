import java.util.*;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s.length() < p.length()) return result;

        int[] pCount = new int[26];
        int[] sCount = new int[26];

        // count characters of p
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }

        int k = p.length();

        for (int i = 0; i < s.length(); i++) {
            // add current char
            sCount[s.charAt(i) - 'a']++;

            // remove left char if window exceeded
            if (i >= k) {
                sCount[s.charAt(i - k) - 'a']--;
            }

            // compare arrays
            if (Arrays.equals(pCount, sCount)) {
                result.add(i - k + 1);
            }
        }

        return result;
    }
}