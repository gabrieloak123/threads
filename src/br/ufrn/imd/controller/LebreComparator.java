package br.ufrn.imd.controller;

import br.ufrn.imd.model.Lebre;

import java.util.Comparator;

public class LebreComparator implements Comparator<Lebre> {
    @Override
    public int compare(Lebre l1, Lebre l2) {
        if(l1.getJumps() > l2.getJumps()) {
            return 1;
        } else if(l1.getJumps() == l2.getJumps()) {
           if(l1.getMetersJumped() >= l2.getMetersJumped()) {
               return -1;
           }
           return 1;
        }
        return -1;
    }
}
