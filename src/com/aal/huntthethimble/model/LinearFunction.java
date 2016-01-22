package com.aal.huntthethimble.model;

import java.awt.geom.Point2D;

/**
 * Created by Marcin on 2016-01-21.
 * f(x) = y = ax + b
 */
public class LinearFunction
{
    private double a;
    private double b;
    private boolean isVertical; //which means actually it's not a function

    public LinearFunction(double a, double b)
    {
        this.a = a;
        this.b = b;
    }

    public LinearFunction(Point2D.Double A, Point2D.Double B)
    {
        double deltaX = B.getX() - A.getX();
        double deltaY = B.getY() - A.getY();

        if(deltaY == 0) //line is horizontal
        {
            a = 0;
            b = A.getY();
            isVertical = false;
        }
        else if(deltaX == 0) //line is vertical
        {
            a = 0;
            b = A.getX();
            isVertical = true;
        }
        else
        {
            a = deltaY/deltaX;
            b = A.getY() - a*A.getX();
            isVertical = false;
        }

    }

    /**
     * Creates line, that is perpendicular to given line, and goes through point
     * @param perpLine new line will be perpendicular to that one
     * @param point new line will go through that point
     */
    public LinearFunction(LinearFunction perpLine, Point2D.Double point)
    {
        if(perpLine.isVertical)
        {
            a = 0;
            b = point.getY();
            isVertical = false;
        }
        else if(perpLine.getA() == 0) //check if it's horizontal line
        {
            a = 0;
            b = point.getX();
            isVertical = true;
        }
        else
        {
            //if(perpLine.getA() == 0) return; // TODO: 2016-01-21
            a = -1/perpLine.getA();
            b = point.getY() - a*point.getX();
            isVertical = false;
        }
    }

    /**
     * Calculates value of function at x
     * @param x
     * @return y, if function "is not vertical", otherwise returns x of vertical line
     */
    public double f(double x)
    {
        return a*x + b;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public boolean isVertical()
    {
        return isVertical;
    }

    public boolean isBelow(Point2D.Double point)
    {
        if(isVertical)
            return b < point.getX();
        if(a == 0) //is horizontal
            return b > point.getY();
        else
            return point.getY() < (a*point.getX() + b);
    }
}
