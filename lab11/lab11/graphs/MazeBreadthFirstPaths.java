package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private Queue<Integer> queue;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        queue = new LinkedList<>();
        queue.add(s);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        int v = queue.poll();
        marked[v] = true;
        announce();


        if (v == t) {
            targetFound = true;
        }
        if (targetFound) {
            return;
        }
        for (int w : maze.adj(v)) {
            if (!marked[w]) {
                queue.add(w);
                edgeTo[w] = v;
                distTo[w] = distTo[v] + 1;
            }
        }
        bfs();
    }


    @Override
    public void solve() {
         bfs();
    }
}

