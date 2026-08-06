class Solution {

    
    public String removeOuterParentheses(String s) {
        
        StringBuilder result = new StringBuilder();  
        
        int level = 0;  

        
        for (char ch : s.toCharArray()) {
            
            if (ch == '(') {
                
                if (level > 0) result.append(ch);
                 
                level++; 
            } 
            
            else if (ch == ')') {
                
                level--;  
                
                if (level > 0) result.append(ch);
            }
        }

        // Return the result as a string after removing the outer parentheses
        return result.toString();
    }
}