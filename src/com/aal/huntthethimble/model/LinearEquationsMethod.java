package com.aal.huntthethimble.model;

import javax.sound.sampled.Line;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Marcin on 2016-01-03.
 */
public class LinearEquationsMethod
{

    private ArrayList<Point2D.Double> vertices;

    private ArrayList<Hint> hints;
    private double[] area;

    public LinearEquationsMethod()
    {
        area = new double[50];
        vertices = new ArrayList<>();

    }

    public void solve(TestCase testCase)
    {
        hints = testCase.getHintsList(); //straight reference to list, to save time
        int roomSize = testCase.getRoomSize();
        double currentArea = roomSize*roomSize;

        Point2D.Double prevPosition;
        Point2D.Double currPosition  = new Point2D.Double(0,0);

        //four initial vertices (corners of the room)
        vertices.add(new Point2D.Double(0,0));
        vertices.add(new Point2D.Double(0,roomSize));
        vertices.add(new Point2D.Double(roomSize,roomSize));
        vertices.add(new Point2D.Double(roomSize,0));

        Hint currentHint;
        int index = 0;

        while(currentArea > 0 && index < testCase.getAmountOfHints())
        {
            currentHint = hints.get(index);
            index++;
            //swap
            prevPosition = currPosition;
            currPosition = currentHint.getCoordinates();

            //find dividing line
            Point2D.Double middlePoint = new Point2D.Double((prevPosition.getX()+currPosition.getX())/2, (prevPosition.getY()+currPosition.getY())/2);
            LinearFunction moveLine = new LinearFunction(prevPosition, currPosition);
            LinearFunction divLine = new LinearFunction(moveLine,middlePoint);

            Point2D.Double divPoint, divPoint2;
            if(divLine.isVertical())
            {
                divPoint = new Point2D.Double(divLine.f(0),0);
                divPoint2 = new Point2D.Double(divLine.f(0),roomSize);
            }
            else
            {
                divPoint = new Point2D.Double(0, divLine.f(0));
                divPoint2 = new Point2D.Double(roomSize, divLine.f(roomSize));
            }

            System.out.println("div: " + divPoint + ", " + divPoint2);

            Point2D.Double intersectionPoint = null;
            int intersectionPointIndex = -1;
            Point2D.Double intersectionPoint2 = null;
            int intersectionPoint2Index = -1;

            int j;
            for(int i = 0; i< vertices.size(); i++)
            {
                j = (i + 1) % vertices.size();
                //System.out.println(i +"," +j);
                Point2D.Double intersection = findIntersection(vertices.get(i),vertices.get(j),divPoint,divPoint2);
                if(intersection != null)
                {
                    //System.out.println("here");
                    if(intersectionPoint == null)
                    {
                        intersectionPoint = intersection;
                        intersectionPointIndex = i;
                    }
                    else
                    {
                        //System.out.println("break");
                        intersectionPoint2 = intersection;
                        intersectionPoint2Index = i;
                        break;  //two intersection points found
                    }
                }
            }//for

            //System.out.println(intersectionPoint);
            //System.out.println(intersectionPoint2);

            if(intersectionPoint != null && intersectionPoint2 != null) //two intersection points were found
            {
                intersectionPointIndex = putOnList(intersectionPointIndex,intersectionPoint);
                if(vertices.contains(intersectionPoint)) intersectionPoint2Index++; //if first point was added, not swaped with existing one
                intersectionPoint2Index = putOnList(intersectionPoint2Index,intersectionPoint2);

                System.out.println("vertAdded:");
                for(Point2D.Double p : vertices)
                    System.out.println(p);

                boolean isNextBelow = false; //does prevPosition and next vertex lies on the same side of divLine

                if(divLine.isBelow(prevPosition))
                {
                    isNextBelow = divLine.isBelow(vertices.get((intersectionPointIndex + 1) % vertices.size())); //if next vertex is on the same side as prev position
                    System.out.println("CUT OFF");
                    if(isNextBelow)
                    {
                        if(currentHint.getTip().equals("HOTTER"))
                            removeBetween(intersectionPointIndex,intersectionPoint2Index);
                        else
                            removeBetween(intersectionPoint2Index,intersectionPointIndex);
                    }
                    else
                    {
                        if(currentHint.getTip().equals("HOTTER"))
                            removeBetween(intersectionPoint2Index,intersectionPointIndex);
                        else
                            removeBetween(intersectionPointIndex,intersectionPoint2Index);
                    }
                }
                else
                {
                    isNextBelow = divLine.isBelow(vertices.get((intersectionPointIndex + 1) % vertices.size())); //if next vertex is on the same side as prev position
                    System.out.println("CUT OFF2");
                    if(!isNextBelow)
                    {
                        if(currentHint.getTip().equals("HOTTER"))
                            removeBetween(intersectionPointIndex,intersectionPoint2Index);
                        else
                            removeBetween(intersectionPoint2Index,intersectionPointIndex);
                    }
                    else
                    {
                        if(currentHint.getTip().equals("HOTTER"))
                            removeBetween(intersectionPoint2Index,intersectionPointIndex);
                        else
                            removeBetween(intersectionPointIndex,intersectionPoint2Index);
                    }

                }
            }
            else if(intersectionPoint == null && intersectionPoint2 == null)
            {
                System.out.println("none");
            }
            //else do nothing

            //System.out.println(intersectionPoint);
            //System.out.println(intersectionPoint2);

            System.out.println("vert:");
            for(Point2D.Double p : vertices)
                System.out.println(p);

        }//while

    }//solve

    private void removeBetween(int indexV, int indexV2)
    {
        System.out.println("Remove between:(" + indexV + "," + indexV2 + ")");

        Point2D.Double toRemove = vertices.get((indexV + 1)%vertices.size());
        Point2D.Double end = vertices.get(indexV2);

        while(!toRemove.equals(end))
        {
            //System.out.println("e:" + end);
            //System.out.println("r: " + toRemove);
            vertices.remove(toRemove);
            if(indexV < vertices.size() -1)
                toRemove = vertices.get(indexV + 1);
            else
                toRemove = vertices.get(0);
            //System.out.println("r2: " + toRemove);
            //System.out.println("size: " +  lista.size());
        }

    }

    private int putOnList(int index, Point2D.Double point)
    {
        if(vertices.contains(point))
        {
            if(vertices.get(index).equals(point))
                vertices.set(index, point); // TODO: 2016-01-21
            else
                vertices.set(++index, point);
        }
        else
        {
            vertices.add(++index, point);
        }
        System.out.println("PUTTED ON POSITION: " + index);
        return index;
    }

    public static void main(String[] args)
    {
        TestCase test = new TestCase();
        test.setRoomSize(10);

        test.addHint(new Hint(new Point2D.Double(2,2), "HOTTER"));
        //test.addHint(new Hint(new Point2D.Double(4,0), "HOTTER"));
        //test.addHint(new Hint(new Point2D.Double(8,4), "HOTTER"));
        //test.addHint(new Hint(new Point2D.Double(7,5), "HOTTER"));
        test.addHint(new Hint(new Point2D.Double(2,6), "HOTTER"));
        test.addHint(new Hint(new Point2D.Double(0,6), "COLDER"));

        LinearEquationsMethod linearEquationsMethod = new LinearEquationsMethod();
        linearEquationsMethod.solve(test);
        //Point2D.Double a = findIntersection(new Point2D.Double(0,2),new Point2D.Double(10,-8),new Point2D.Double(10,0),new Point2D.Double(0,0));
        //System.out.println(a);

        ArrayList<Point2D.Double> lista = new ArrayList<>();
        lista.add(new Point2D.Double(0,2));
        lista.add(new Point2D.Double(0,10));
        lista.add(new Point2D.Double(10,10));
        lista.add(new Point2D.Double(10,0));
        lista.add(new Point2D.Double(2,0));

        calculatePolygonArea(lista);
    }

    /**
     * Find intersection point between two line segments
     * @param v1 start point of first line segment
     * @param v2 end point of first line segment
     * @param t1 start point of second line segment
     * @param t2 end point of second line segment
     * @return point of intersection, if exists, otherwise - null
     * source: https://en.wikipedia.org/wiki/Line%E2%80%93line_intersection
     */
    static Point2D.Double findIntersection(Point2D.Double v1, Point2D.Double v2, Point2D.Double t1, Point2D.Double t2)
    {
        //first line
        double deltaX = v1.getX() - v2.getX();  //line one: x1 - x2
        double deltaY = v1.getY() - v2.getY();  //line one: y1 - y2
        //second line
        double deltaX2 = t1.getX() - t2.getX(); //line two: x3 - x4
        double deltaY2 = t1.getY() - t2.getY(); //line two: y3 - y4
        //factors
        double factor = (v1.getX()*v2.getY()) - (v1.getY()*v2.getX());  //line one: x1*y2 - y1*x2
        double factor2 = (t1.getX()*t2.getY()) - (t1.getY()*t2.getX()); //line two: x3*y4 - y3*x4
        //denominator
        double denominator = (deltaX*deltaY2) - (deltaY*deltaX2);   //(x1 - x2)(y3 - y4) - (y1 - y2)(x3 - x4)

        //if lines are parallel or coincident, denominator is zero
        if(denominator == 0)
            return null;

        double intersectionX = ((factor*deltaX2) - (deltaX*factor2))/denominator; //((x1*y2 - y1*x2)(x3- x4) - (x1 - x2)(x3*y4 - y3*x4))/denominator
        double intersectionY = ((factor*deltaY2) - (deltaY*factor2))/denominator;

        //check if found intersection point lies on one of line segments
        if(isBetweenPoints2D(v1,v2,new Point2D.Double(intersectionX,intersectionY)))
            return new Point2D.Double(intersectionX,intersectionY);
        else
            return null;
    }

    static double calculatePolygonArea(ArrayList<Point2D.Double> vertices)
    {
        ArrayList<Point2D.Double> vert = (ArrayList<Point2D.Double>) vertices.clone();

        Point2D.Double temp = new Point2D.Double(vert.get(vert.size()-1).getX(),vert.get(vert.size()-1).getY());
        Point2D.Double temp2 = new Point2D.Double(vert.get(0).getX(),vert.get(0).getY());

        vert.add(0,temp);
        vert.add(temp2);

        //vert.set(vert.size()-1,vert.get(0));
        //vert.set(0,temp);

        int n = vert.size()-1;
        double sum = 0;
        for(int i = 1; i < n; i++)
        {
            sum += vert.get(i).getX() * (vert.get(i+1).getY() - vert.get(i-1).getY());
        }

        double area = Math.abs(sum) /2;

        System.out.println("given:");
        for( Point2D.Double point : vertices)
            System.out.println(point);

        System.out.println("new:");
        for( Point2D.Double point : vert)
            System.out.println(point);

        System.out.println(area);

        return 0;
    }

    static boolean isBetweenPoints2D(Point2D.Double pointA, Point2D.Double pointB, Point2D pointBetween)
    {
        if(isBetween(pointA.getX(),pointB.getX(),pointBetween.getX()))
            if(isBetween(pointA.getY(),pointB.getY(),pointBetween.getY()))
                return true;
        return false;
    }//isBetweenPoints2D

    public static boolean isBetween(double A, double B, double between)
    {
        if(A < B)
        {
            return A <= between && between <= B;
        }
        else
        {
            return A >= between && between >= B;
        }
    }

}
