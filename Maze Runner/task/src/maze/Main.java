package maze;

import java.util.*;

public class Main {

    static Vertex[][] workingMaze;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean hasMaze = false;
        mainLoop:while (true) {
            if (hasMaze) {
                System.out.println("\n=== Menu ===\n" +
                        "1. Generate a new maze\n" +
                        "2. Load a maze\n" +
                        "3. Save the maze\n" +
                        "4. Display the maze\n" +
                        "5. Find the escape\n" +
                        "0. Exit");
            } else {
                System.out.println("\n=== Menu ===\n" +
                        "1. Generate a new maze\n" +
                        "2. Load a maze\n" +
                        "0. Exit");
            }

            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("Enter the size of a new maze");
                    int size = scanner.nextInt();
                    workingMaze = Util.createMaze(size, size);
                    Util.resolve3by3(workingMaze);
                    printMaze(workingMaze);
                    hasMaze = true;
                    break;
                case 2:
                    scanner.nextLine();
                    workingMaze = Util.loadMaze(scanner.nextLine());
                    if (workingMaze == null) {
                        System.out.println("Cannot load the maze. It has an invalid format");
                    } else if (workingMaze.length != 0) {
                        hasMaze = true;
                    }
                    break;
                case 3:
                    if (hasMaze) {
                        scanner.nextLine();
                        Util.saveMaze(scanner.nextLine(), workingMaze);
                    } else {
                        System.out.println("Incorrect option. Please try again");
                    }
                    break;
                case 4:
                    if (hasMaze) {
                        printMaze(workingMaze);
                    } else {
                        System.out.println("Incorrect option. Please try again");
                    }
                    break;
                case 5:
                    if (hasMaze) {
                        Util.solveMaze(workingMaze);
                        printMaze(workingMaze, true);
                    } else {
                        System.out.println("Incorrect option. Please try again");
                    }
                    break;
                case 0:
                    break mainLoop;
                default:
                    System.out.println("Incorrect option. Please try again");
            }
        }

        System.out.println("Bye!");

    }

    static void printMaze(Vertex[][] maze) {
        printMaze(maze, false);
    }

    static void printMaze(Vertex[][] maze, boolean showPath) {
        for (Vertex[] vertices : maze) {
            for (int j = 0; j < maze[0].length; j++) {
                if (vertices[j].isWall()) {
                    System.out.print("\u2588\u2588");
                } else if (vertices[j].isPath() && showPath) {
                    System.out.print("//");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

}
