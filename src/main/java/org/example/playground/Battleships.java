package org.example.playground;

public class Battleships {

    static int[] dx = {-1, 1, 0, 0}; // Directions for X axis
    static int[] dy = {0, 0, -1, 1}; // Directions for Y axis
    static boolean[][] visited;

    public static void main(String[] args) {
        String[] B = {
                ".##.#",
                "#.#..",
                "#...#",
                "#.##."
        };

        int n = B.length;
        int m = B[0].length();
        visited = new boolean[n][m];

        int patrolBoats = 0, submarines = 0, destroyers = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (B[i].charAt(j) == '#' && !visited[i][j]) {
                    int shipSize = dfs(B, i, j, n, m);

                    // Classify ship based on size
                    if (shipSize == 1) {
                        patrolBoats++;
                    } else if (shipSize == 2) {
                        submarines++;
                    } else if (shipSize == 3) {
                        destroyers++;
                    }
                }
            }
        }

        System.out.println("Patrol Boats: " + patrolBoats);
        System.out.println("Submarines: " + submarines);
        System.out.println("Destroyers: " + destroyers);
    }

    public static int dfs(String[] B, int x, int y, int n, int m) {
        visited[x][y] = true;
        int size = 1;

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m && B[nx].charAt(ny) == '#' && !visited[nx][ny]) {
                size += dfs(B, nx, ny, n, m);
            }
        }

        return size;
    }


}

