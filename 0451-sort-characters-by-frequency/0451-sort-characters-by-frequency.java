class Solution {
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        // Count frequency
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Put characters into a list
        ArrayList<Character> list = new ArrayList<>(map.keySet());

        // Sort based on frequency
        list.sort((a, b) -> map.get(b) - map.get(a));

        // Build answer
        StringBuilder ans = new StringBuilder();

        for (char c : list) {
            for (int i = 0; i < map.get(c); i++) {
                ans.append(c);
            }
        }

        return ans.toString();
    }
}