package com.example.dell.goatlendar.droits;

import java.util.ArrayList;

public abstract class DroitSupplementaires implements Droit{

    private ArrayList<Droit> droits;

    public DroitSupplementaires(ArrayList<Droit> droits) {
        this.droits = droits;
    }
}
