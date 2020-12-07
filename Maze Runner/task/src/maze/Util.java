package maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Util {

    static Vertex[][] loadMaze(String fileName) {
        try {
            Scanner fileScanner = new Scanner(new File(fileName));

            ArrayList<ArrayList<Vertex>> vertices = new ArrayList<>();
            boolean firstLine = true;
            int entries = 0;

            int rowIndex = 1;
            while (fileScanner.hasNext()) {
                String[] numbers = fileScanner.nextLine().split(" ");
                if (firstLine) {
                    entries = numbers.length;
                    firstLine = false;
                } else {
                    if (numbers.length != entries) {
                        return null;
                    }
                }

                ArrayList<Vertex> row = new ArrayList<>();
                int colIndex = 1;
                for (String number : numbers) {
                    if (number.equals("0")) {
                        row.add(new Vertex(rowIndex, colIndex, false));
                    } else if (number.equals("1")) {
                        row.add(new Vertex(rowIndex, colIndex, true));
                    } else {
                        return null;
                    }
                    colIndex++;
                }
                vertices.add(row);
                rowIndex++;
            }

            if (vertices.size() != entries) {
                return null;
            } else {
                Vertex[][] result = new Vertex[entries][entries];
                for (int i = 0; i < entries; i++) {
                    for (int j = 0; j < entries; j++) {
                        result[i][j] = vertices.get(i).get(j);
                    }
                }
                addNeighbours(result);
                return result;
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file " + fileName + " does not exist");
            return new Vertex[0][0];
        }
    }

    static void addNeighbours(Vertex[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j].clearNeighbours();
                if (i != 0) {
                    maze[i][j].addNeighbour(maze[i - 1][j]);
                }
                if (i != maze.length - 1) {
                    maze[i][j].addNeighbour(maze[i + 1][j]);
                }
                if (j != 0) {
                    maze[i][j].addNeighbour(maze[i][j - 1]);
                }
                if (j != maze[0].length - 1) {
                    maze[i][j].addNeighbour(maze[i][j + 1]);
                }
            }
        }
    }

    static void saveMaze(String fileName, Vertex[][] workingMaze) {
        File file = new File(fileName);
        try (PrintWriter p = new PrintWriter(file)) {
            for (Vertex[] vertices : workingMaze) {
                for (Vertex vertex : vertices) {
                    p.print((vertex.isWall() ? 1 : 0) + " ");
                }
                p.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void resolve3by3(Vertex[][] maze) {
        for (int h = 0; h <= maze.length - 3; h++) {
            for (int w = 0; w <= maze[0].length - 3; w++) {
                if (maze[h][w].isWall() &&
                        maze[h][w + 1].isWall() &&
                        maze[h][w + 2].isWall() &&

                        maze[h + 1][w].isWall() &&
                        maze[h + 1][w + 1].isWall() &&
                        maze[h + 1][w + 2].isWall() &&

                        maze[h + 2][w].isWall() &&
                        maze[h + 2][w + 1].isWall() &&
                        maze[h + 2][w + 2].isWall()) {

                    if (h + 2 == maze.length - 1) {
                        if (w == 0) {
                            maze[h][w + 2].setWall(false);
                        } else {
                            maze[h][w].setWall(false);
                        }
                    } else if (h == 0) {
                        if (w == 0) {
                            maze[h + 2][w + 2].setWall(false);
                        } else {
                            maze[h + 2][w].setWall(false);
                        }
                    } else if (w == 0) {
                        maze[h][w + 2].setWall(false);
                    } else {
                        maze[h][w].setWall(false);
                    }

                }
            }
        }
    }

    static Vertex[][] createMaze(int height, int width) {
        Random random = new Random();
        Vertex[][] maze = new Vertex[height + 2][width + 2];

        for (int i = 0; i < height + 2; i++) {
            for (int j = 0; j < width + 2; j++) {
                maze[i][j] = new Vertex(i, j);
            }
        }
        addNeighbours(maze);

        for (int i = 0; i < height + 2; i++) {
            maze[i][0].setWall(false);
            maze[i][width + 1].setWall(false);
        }

        for (int j = 0; j < width + 2; j++) {
            maze[0][j].setWall(false);
            maze[height + 1][j].setWall(false);
        }

        ArrayList<Vertex> currentVertices = new ArrayList<>();

        currentVertices.add(maze[random.nextInt(maze.length - 5) + 2][1]);

        while (!currentVertices.isEmpty()) {
            Vertex currentVertex = currentVertices
                    .get(random.nextInt(currentVertices.size()));
            ArrayList<Vertex> neighbours = currentVertex.getNeighbours();

            long countRoad = neighbours.stream().filter(neighbour ->
                    !neighbour.isWall()).count();

            if (countRoad == 1) {
                currentVertex.setWall(false);
                currentVertices.addAll(neighbours.stream().filter(Vertex::isWall)
                        .collect(Collectors.toList()));
            }
            currentVertices.remove(currentVertex);
        }

        Vertex[][] resultMaze = new Vertex[height][width];
        for (int i = 0; i < height; i++) {
            if (width >= 0) System.arraycopy(maze[i + 1], 1, resultMaze[i], 0, width);
        }
        addNeighbours(resultMaze);

        while (true) {
            int out = random.nextInt(height - 2) + 1;
            if (!resultMaze[out][width - 2].isWall()) {
                resultMaze[out][width - 1].setWall(false);
                break;
            }
        }

        return resultMaze;
    }

    public static void solveMaze(Vertex[][] workingMaze) {
        Stack<Vertex> stack = new Stack<>();

        for (int i = 0; i < workingMaze.length; i++) {
            if (!workingMaze[i][0].isWall()) {
                stack.push(workingMaze[i][0]);
                break;
            }
        }

        while (!stack.isEmpty()) {
            Vertex currentPosition = stack.peek();
            currentPosition.setSearched(true);
            List<Vertex> neighboursNotUsed = currentPosition.getNeighbours()
                    .stream().filter(neighbour -> !neighbour.isSearched() && !neighbour.isWall())
                    .collect(Collectors.toList());
            if (neighboursNotUsed.size() == 0) {
                stack.pop();
            } else {
                Vertex nextVertex = neighboursNotUsed.get(0);
                stack.push(nextVertex);
                if (nextVertex.getY() == workingMaze.length) {
                    while (!stack.isEmpty()) {
                        Vertex vertex = stack.pop();
                        vertex.setPath(true);
                    }
                    break;
                }
            }
        }
    }
}
