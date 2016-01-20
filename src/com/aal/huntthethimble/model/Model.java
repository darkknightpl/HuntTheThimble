package com.aal.huntthethimble.model;

import java.io.File;

/**
 * Created by Marcin on 2016-01-03.
 */
public class Model
{

    private LinearEquationsMethod linearEquationsMethod;
    private PicksTheorem picksTheorem;

    private TestFileParser testFileParser;
    private TestCase testCase;

    // TODO: 2016-01-15 decide if it should be the only mode  
    private TestCase[] testCasesTable;

    public Model()
    {
        linearEquationsMethod = new LinearEquationsMethod();
        //picksTheorem = new PicksTheorem();
    }

    public void loadFile(File file)
    {
        testFileParser.parseFileInto(file, testCase);
    }

    public void solvePicksMethod()
    {
        picksTheorem = new PicksTheorem(testCase);
        picksTheorem.solve(testCase);
    }

    public void solveLinearEquationa()
    {
        linearEquationsMethod.solve(testCase);
    }
}
