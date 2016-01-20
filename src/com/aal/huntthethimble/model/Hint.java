package com.aal.huntthethimble.model;

import java.awt.geom.Point2D;

/**
 * Created by Marcin on 2016-01-04.
 */
public class Hint
{
    private Point2D.Double coordinates;
    private String tip;

    public Hint(Point2D.Double coordinates, String tip)
    {
        this.coordinates = coordinates;
        this.tip = tip;
    }

    public Point2D.Double getCoordinates()
    {
        return coordinates;
    }

    public String getTip()
    {
        return tip;
    }
}
