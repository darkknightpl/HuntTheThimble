package com.aal.huntthethimble.controller;

import com.aal.huntthethimble.model.Model;
import com.aal.huntthethimble.view.View;

/**
 * Created by Marcin on 2016-01-03.
 */
public class Controller
{
    private Model model;
    private View view;

    private int mode;

    public Controller(Model model)
    {
        this.model = model;
    }

    public void setView(View view)
    {
        this.view = view;
    }
}
