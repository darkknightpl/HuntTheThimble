package com.aal.huntthethimble;

import com.aal.huntthethimble.controller.Controller;
import com.aal.huntthethimble.model.Model;
import com.aal.huntthethimble.view.View;

import java.awt.*;

public class Main
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        Model model = new Model();
                        Controller controller = new Controller(model);
                        View view = new View(model, controller);
                        controller.setView(view);
                    }
                }
        );
    }
}
