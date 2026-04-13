public class Orchard {

    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    // DFS function to count connected trees
    public static int dfs(char[][] grid, int i, int j, boolean[][] visited) {
        int count = 1;
        visited[i][j] = true;

        for (int d = 0; d < 8; d++) {
            int ni = i + dx[d];
            int nj = j + dy[d];

            // Check bounds and if it's a tree and not visited
            if (ni >= 0 && nj >= 0 && ni < grid.length && nj < grid[0].length
                    && grid[ni][nj] == 'T' && !visited[ni][nj]) {
                count += dfs(grid, ni, nj, visited);
            }
        }
        return count;
    }

    public static void main(String[] args) {

        char[][] grid = {
                {'O','T','O','O'},
                {'O','T','O','T'},
                {'T','T','O','T'},
                {'O','T','O','T'}
        };

        boolean[][] visited = new boolean[grid.length][grid[0].length];

        System.out.print("Orchard sizes: ");

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == 'T' && !visited[i][j]) {
                    int size = dfs(grid, i, j, visited);
                    System.out.print(size + " ");
                }
            }
        }
    }
}