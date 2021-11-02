import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.Timer;

public class LifeModel implements ActionListener
{

    /*
     *  This is the Model component.
     */

    private static int SIZE = 60;
    private LifeCell[][] grid;
    

    LifeView myView;
    Timer timer;

    /** Construct a new model using a particular file */
    public LifeModel(LifeView view, String fileName) throws IOException
    {       
        int r, c;
        grid = new LifeCell[SIZE][SIZE];
        for ( r = 0; r < SIZE; r++ )
            for ( c = 0; c < SIZE; c++ )
                grid[r][c] = new LifeCell();

        if ( fileName == null ) //use random population
        {                                           
            for ( r = 0; r < SIZE; r++ )
            {
                for ( c = 0; c < SIZE; c++ )
                {
                    if ( Math.random() > 0.85) //15% chance of a cell starting alive
                        grid[r][c].setAliveNow(true);
                }
            }
        }
        else
        {                 
            Scanner input = new Scanner(new File(fileName));
            int numInitialCells = input.nextInt();
            for (int count=0; count<numInitialCells; count++)
            {
                r = input.nextInt();
                c = input.nextInt();
                grid[r][c].setAliveNow(true);
            }
            input.close();
        }

        myView = view;
        myView.updateView(grid);

    }

    /** Constructor a randomized model */
    public LifeModel(LifeView view) throws IOException
    {
        this(view, null);
    }

    /** pause the simulation (the pause button in the GUI */
    public void pause()
    {
        timer.stop();
    }

    /** resume the simulation (the pause button in the GUI */
    public void resume()
    {
        timer.restart();
    }

    /** run the simulation (the pause button in the GUI */
    public void run()
    {
        timer = new Timer(50, this);
        timer.setCoalesce(true);
        timer.start();
    }

    public void reset()
    {
        timer = new Timer(50, this);
        timer.setCoalesce(true);
        timer.start();
    }
    
    

    /** called each time timer fires */
    public void actionPerformed(ActionEvent e)
    {
        oneGeneration();
        myView.updateView(grid);
    }

    /** main logic method for updating the state of the grid / simulation */
    private void oneGeneration()
    {
        /* 
         * student code here 
         */

        for (int i=0; i<100;i++)
        {

            int currentCellRow = (int) (Math.random() * (SIZE-1));
            int currentCellCol= (int) (Math.random() * (SIZE-1));

            int noOfNeighbours = 0;
            int prevCellRow = currentCellRow ;
            int prevCellCol = currentCellCol -1;
            if (prevCellRow >=0 && prevCellRow < SIZE && prevCellCol >=0 && prevCellCol < SIZE &&
            grid[prevCellRow][prevCellCol].isAliveNow())
                noOfNeighbours++;

            int nextCellRow = currentCellRow;
            int nextCellCol = currentCellCol +1;
            if (nextCellRow >=0 && nextCellRow < SIZE && nextCellCol >=0 && nextCellCol < SIZE && 
            grid[nextCellRow][nextCellCol].isAliveNow())
                noOfNeighbours++;

            int aboveCellRow = currentCellRow -1;
            int aboveCellCol = currentCellCol;
            if (aboveCellRow >=0 && aboveCellRow < SIZE && aboveCellCol >=0 && aboveCellCol < SIZE && 
            grid[aboveCellRow][aboveCellCol].isAliveNow())
                noOfNeighbours++;
            int belowCellRow = currentCellRow + 1;
            int belowCellCol = currentCellCol;
            if (belowCellRow >=0 && belowCellRow < SIZE && belowCellCol >=0 && belowCellCol < SIZE && 
            grid[belowCellRow][belowCellCol].isAliveNow())
                noOfNeighbours++;

            int topLeftCellRow = currentCellRow -1;
            int topLeftCellCol = currentCellCol -1;
            if (topLeftCellRow >=0 && topLeftCellRow < SIZE && topLeftCellCol >=0 && topLeftCellCol < SIZE && 
            grid[topLeftCellRow][topLeftCellCol].isAliveNow())
                noOfNeighbours++;

            int bottomRightCellRow = currentCellRow + 1;
            int bottomRightCellCol = currentCellCol + 1;
            if (bottomRightCellRow >=0 && bottomRightCellRow < SIZE && bottomRightCellCol >=0 && bottomRightCellCol < SIZE && 
            grid[bottomRightCellRow][bottomRightCellCol].isAliveNow())
                noOfNeighbours++;

            int topRightCellRow = currentCellRow - 1;
            int topRightCellCol = currentCellCol + 1;
            if (topRightCellRow >=0 && topRightCellRow < SIZE && topRightCellCol >=0 && topRightCellCol < SIZE && 
            grid[topRightCellRow][topRightCellCol].isAliveNow())
                noOfNeighbours++;
            int bottomLeftCellRow = currentCellRow + 1;
            int bottomLeftCellCol = currentCellCol - 1;
            if (bottomLeftCellRow >=0 && bottomLeftCellRow < SIZE && bottomLeftCellCol >=0 && bottomLeftCellCol < SIZE && 
            grid[bottomLeftCellRow][bottomLeftCellCol].isAliveNow())
                noOfNeighbours++;

            if (noOfNeighbours <= 1 || noOfNeighbours >=4)
                grid[currentCellRow][currentCellCol].setAliveNow(false);
            else if (noOfNeighbours == 2 || noOfNeighbours == 3)
                grid[currentCellRow][currentCellCol].setAliveNow(true);
        }

    }
}

