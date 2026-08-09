class Solution {
    public int maxDepth(String s) {
        int depth=0;
        int maxdepth=0;
        for( char c: s.toCharArray() ){
            if(c=='('){
                depth++;
                maxdepth=Math.max(maxdepth,depth);
            }

                
            else if(c==')'){
                
                    depth--;
            }
                            
         }
        
        return maxdepth;
        
    }
}