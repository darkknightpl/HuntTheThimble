package com.aal.huntthethimble.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created by Marcin on 2016-01-03.
 */
public class PicksTheorem
{                                           //-----> (x) table coordinates
    private boolean[][] room;               //|
    private ArrayList<Hint> hints;          //|
    private double[] area;                  //v (y)

    public PicksTheorem(TestCase testCase)
    {
        area = new double[50];
        hints = testCase.getHintsList(); //straight reference to list, to save time
        room = new boolean[testCase.getRoomSize()+1][testCase.getRoomSize()+1]; //plus one, because object can be hidden in corners (ex. (10,10) or (0,0))
        for(int i = 0; i<testCase.getRoomSize()+1; i++)
            for (int j = 0; j < testCase.getRoomSize() + 1; j++)
                room[i][j] = true;
    }

    public void init(TestCase testCase)
    {
        area = new double[50];
        hints = testCase.getHintsList(); //straight reference to list, to save time
        room = new boolean[testCase.getRoomSize()+1][testCase.getRoomSize()+1];
        for(int i = 0; i<testCase.getRoomSize()+1; i++)
            for (int j = 0; j < testCase.getRoomSize() + 1; j++)
                room[i][j] = true;

    }

    public void solve(TestCase testCase)
    {
        int roomSize = testCase.getRoomSize();
        double currentArea = roomSize*roomSize;

        Point2D.Double prevPosition;
        Point2D.Double currPosition  = new Point2D.Double(0,0);

        Hint currentHint;
        int index = 0;
        double prevDistance;
        double currDistance;

        while(currentArea > 0 && index < testCase.getAmountOfHints())
        {
            currentHint = hints.get(index);
            index++;
            //swap
            prevPosition = currPosition;
            currPosition = currentHint.getCoordinates();

            for(int x = 0; x<roomSize+1; x++)   //go through whole room table
            {
                for(int y = 0; y<roomSize+1; y++)
                {
                    prevDistance = distance(x,y,prevPosition.getX(), prevPosition.getY());
                    currDistance = distance(x,y,currPosition.getX(), currPosition.getY());
                    if(currentHint.getTip() == "HOTTER")
                    {
                        if(currDistance>prevDistance)
                            room[x][y] = false;
                    }
                    else if (currentHint.getTip() == "COLDER")
                    {
                        if(currDistance<prevDistance)
                            room[x][y] = false;
                    }
                    else //tip==THESAME
                    {
                        //System.out.println("the same");
                        //experimental
                        //double dist = distance(prevPosition.getX(), prevPosition.getY(), currPosition.getX(), currPosition.getY());
                        if (currDistance != prevDistance)
                            room[x][y] = false;
                    }
                }
            }//for

            double pointsInside = 0;
            double pointsBorder = 0;

            for(int x = 0; x<roomSize+1; x++)   //go through whole room table
            {
                for (int y = 0; y < roomSize + 1; y++)
                {
                    if (room[x][y] == true)
                    {
                        if (x == 0 || y == 0 || x == roomSize || y == roomSize)
                            pointsBorder++;
                        else
                        {
                            if(room[x-1][y] && room[x+1][y] && room[x][y-1] && room[x][y+1])
                                pointsInside++;
                            else
                                pointsBorder++;
                        }
                    }
                }
            }
            currentArea = pointsInside + (pointsBorder/2) -1;
            System.out.println("current area: " + currentArea);

            for(int i = 0; i<testCase.getRoomSize()+1; i++)
            {
                for (int j = 0; j < testCase.getRoomSize() + 1; j++)
                    System.out.printf(room[i][j] + " ");
                System.out.printf("\n");
            }
        }//while

    }//solve

    public double distance(double x1, double y1, double x2, double y2)
    {
        double deltaX = Math.abs(x1 - x2);
        double deltaY = Math.abs(y1 - y2);
        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

    }

    public void solveTest(TestCase testCase)
    {
        int roomSize = testCase.getRoomSize();
        int currentArea = roomSize*roomSize;

        Point2D.Double previousPosition;
        Point2D.Double currentPosition  = new Point2D.Double(0,0);

        Hint currentHint;

        int index = 0;
        for(Hint h : hints)
        {
            currentHint = h;
            index++;

            previousPosition = currentPosition;
            currentPosition = currentHint.getCoordinates();

            System.out.println(index + "-> prev: " + previousPosition + ", curr: " + currentPosition);
        }

    }

    public static void main(String[] args)
    {
        TestCase test = new TestCase();
        test.setRoomSize(10);

        test.addHint(new Hint(new Point2D.Double(2,2), "HOTTER"));
        test.addHint(new Hint(new Point2D.Double(4,4), "THESAME"));
        //test.addHint(new Hint(new Point2D.Double(3,3), "hot"));

        PicksTheorem picks = new PicksTheorem(test);
        picks.solve(test);

    }
}
