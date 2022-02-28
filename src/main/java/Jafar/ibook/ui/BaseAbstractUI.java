package Jafar.ibook.ui;

import Jafar.ibook.response.ResponseEntity;

import uz.jl.utils.Color;
import uz.jl.utils.Print;



public abstract class BaseAbstractUI {
    protected void show(ResponseEntity response) {
        if (response.getStatus().equals(200))
            Print.println(Color.GREEN, response.getData());
        else
            Print.println(Color.RED, response.getData());
    }

}