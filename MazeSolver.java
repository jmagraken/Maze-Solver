import java.util.*;
import java.io.*;

public class MazeSolver {
    public static MazeLocation[] findPath(Maze maze) {
		String result = "";
		boolean visited[][] = new boolean[maze.getNumRows()][maze.getNumCols()];
		Stack<MazeLocation> moves = new StackRefBased<MazeLocation>();
		moves.push(maze.getEntry());
		MazeLocation cur;
		while(!moves.isEmpty() && !(moves.peek()).equals(maze.getExit())) {
			cur = moves.peek();
			visited[cur.getRow()][cur.getCol()] = true;
			if(!maze.isWall(cur.getRow() - 1, cur.getCol()) && !visited[cur.getRow() - 1][cur.getCol()]) {
				moves.push(new MazeLocation(cur.getRow() - 1, cur.getCol()));
			}
			else if(!maze.isWall(cur.getRow() + 1, cur.getCol()) && !visited[cur.getRow() + 1][cur.getCol()]) {
				moves.push(new MazeLocation(cur.getRow() + 1, cur.getCol()));
			}
			else if(!maze.isWall(cur.getRow(), cur.getCol() - 1) && !visited[cur.getRow()][cur.getCol() - 1]) {
				moves.push(new MazeLocation(cur.getRow(), cur.getCol() - 1));
			}
			else if(!maze.isWall(cur.getRow(), cur.getCol() + 1) && !visited[cur.getRow()][cur.getCol() + 1]) {
				moves.push(new MazeLocation(cur.getRow(), cur.getCol() + 1));
			}
			else {
				moves.pop();
			}
		}
		MazeLocation[] solution = new MazeLocation[moves.size()];
		int i = solution.length - 1;
		while(!moves.isEmpty()) {
			solution[i] = moves.pop();
			i--;
		}
		return solution;
    }
	
	public static void main(String[] args) throws FileNotFoundException {
		if(args.length > 0) {
			MazeLocation[] solution = findPath(new Maze(args[0]));
			if(solution.length == 0) {
				System.out.println("Maze has no solution.");
				return;
			}
			File infile = new File(args[0]);
			Scanner instream = new Scanner(infile);
			int numRows = instream.nextInt();
            int numCols = instream.nextInt();
			instream.nextLine();
			instream.nextLine();
			instream.nextLine();
			char maze[][] = new char[numRows][numCols];
			for(int i = 0; i < numRows; i++) {
				String next = instream.nextLine();
				for(int j = 0; j < numCols; j++) {
					maze[i][j] = next.charAt(j);
				}
			}
			for(int i = 0; i < solution.length; i++) {
				MazeLocation cur = solution[i];
				maze[cur.getRow()][cur.getCol()] = '.';
			}
			for(int i = 0; i < maze.length; i++) {
				for(int j = 0; j < numCols; j++) {
					System.out.print(maze[i][j]);
				}
				System.out.println();
			}
			
		}
		
	}
}
