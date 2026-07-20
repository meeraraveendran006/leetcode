class Solution {

    List<String> ans = new ArrayList<>();

    public List<String> addOperators(String num, int target) {
        solve(num, target, 0, "", 0, 0);
        return ans;
    }

    void solve(String num, int target, int index,
               String expr, long value, long prev) {

        if (index == num.length()) {
            if (value == target)
                ans.add(expr);
            return;
        }

        for (int i = index; i < num.length(); i++) {

            // Skip numbers like 05, 012
            if (i > index && num.charAt(index) == '0')
                break;

            String curStr = num.substring(index, i + 1);
            long cur = Long.parseLong(curStr);

            if (index == 0) {
                solve(num, target, i + 1,
                      curStr, cur, cur);
            } else {

                solve(num, target, i + 1,
                      expr + "+" + curStr,
                      value + cur,
                      cur);

                solve(num, target, i + 1,
                      expr + "-" + curStr,
                      value - cur,
                      -cur);

                solve(num, target, i + 1,
                      expr + "*" + curStr,
                      value - prev + prev * cur,
                      prev * cur);
            }
        }
    }
}