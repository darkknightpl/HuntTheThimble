package com.aal.huntthethimble.model;

import java.util.ArrayList;

/**
 * Created by Marcin on 2016-01-04.
 */
public class TestCase
{
    private int roomSize;
    private ArrayList<Hint> hintsList;
    private int amountOfHints; //max 50

    public TestCase()
    {
        hintsList = new ArrayList<>();
        amountOfHints = 0;
    }

    public void addHint(Hint hint)
    {
        hintsList.add(hint);
        amountOfHints++;
    }

    public ArrayList<Hint> getHintsList()
    {
        return hintsList;
    }

    public void setRoomSize(int roomSize)
    {
        this.roomSize = roomSize;
    }

    public int getRoomSize()
    {
        return roomSize;
    }

    public int getAmountOfHints()
    {
        return amountOfHints;
    }
}
