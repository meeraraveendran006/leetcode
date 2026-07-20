class Solution {

    public boolean isExist(char[][] board, String word, int i, int j, int index) {

        // All characters matched
        if (index == word.length())
            return true;

        // Out of bounds
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length)
            return false;

        // Character mismatch
        if (board[i][j] != word.charAt(index))
            return false;

        // Already visited
        if (board[i][j] == '#')
            return false;

        char temp = board[i][j];
        board[i][j] = '#';

        boolean found =
                isExist(board, word, i + 1, j, index + 1) ||
                isExist(board, word, i - 1, j, index + 1) ||
                isExist(board, word, i, j + 1, index + 1) ||
                isExist(board, word, i, j - 1, index + 1);

        board[i][j] = temp;

        return found;
    }

    public boolean exist(char[][] board, String word) {

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (isExist(board, word, i, j, 0))
                    return true;
            }
        }

        return false;
    }
}