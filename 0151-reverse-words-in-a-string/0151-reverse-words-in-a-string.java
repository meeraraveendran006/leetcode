class Solution {
    // Function to reverse the order of words in a string
    public String reverseWords(String s) {
        // List to store words
        List<String> words = new ArrayList<>();
        
        // StringBuilder to store a single word
        StringBuilder word = new StringBuilder();
        
        // Traverse each character in the string
        for (int i = 0; i < s.length(); i++) {
            // If it's not a space, add it to current word
            if (s.charAt(i) != ' ') {
                word.append(s.charAt(i));
            } 
            // If space and a word is formed
            else if (word.length() > 0) {
                // Add word to list
                words.add(word.toString());
                // Reset word
                word.setLength(0);
            }
        }
        
        // Add the last word if present
        if (word.length() > 0) {
            words.add(word.toString());
        }
        
        // Reverse the list
        Collections.reverse(words);
        
        // Join with single space
        return String.join(" ", words);
    }
}