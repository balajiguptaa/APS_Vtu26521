class Solution {
    public boolean halvesAreAlike(String s) {
        int count = 0;
        int n = s.length();

        for(int i=0;i<n;i++){
            if(isVowel(s.charAt(i))){
                if(i<n/2){
                    count++;
                }
                else{
                    count--;
                }
            }
        }
        return count == 0;
        
    }
    private boolean isVowel(char c){
        return "aeiouAEIOU".indexOf(c) != -1;
    }
}