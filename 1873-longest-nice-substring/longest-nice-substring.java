class Solution {
    public String longestNiceSubstring(String s) {
        return helper(s);
    }

    private String helper(String s) {
        if (s.length() < 2) return "";
        
        // store characters
        java.util.Set<Character> set = new java.util.HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            // if one case missing → split
            if (!set.contains(Character.toLowerCase(c)) ||
                !set.contains(Character.toUpperCase(c))) {
                
                String left = helper(s.substring(0, i));
                String right = helper(s.substring(i + 1));
                
                return left.length() >= right.length() ? left : right;
            }
        }
        
        // whole string is nice
        return s;
    }
}