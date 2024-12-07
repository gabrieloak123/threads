package br.ufrn.imd.dao;

import br.ufrn.imd.controller.LebreComparator;
import br.ufrn.imd.model.Lebre;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Repositorio {
    private ArrayList<Lebre> lebres;
    private final ReentrantLock lock;
    private final Condition condition;
    private static final int raceDistance = 20;

    public Repositorio(ReentrantLock lock, Condition condition) {
        this.lebres = new ArrayList<Lebre>();
        this.lock = lock;
        this.condition = condition;
    }

    public ArrayList<Lebre> getLebres() {
        return lebres;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public  int getRaceDistance() {
        return raceDistance;
    }

    public Condition getCondition() {
        return condition;
    }

    public void add(Lebre lebre) {
        lebre.setLock(this.lock);
        lebre.setCondition(this.condition);
        lebre.setLebreIndex(this.lebres.size());
        lebres.add(lebre);
    }

    private boolean lebreFinished(Lebre lebre) {
        return lebre.getMetersJumped() >= raceDistance;
    }

    public boolean allFinished() {
        for (Lebre lebre : lebres) {
           if(!lebreFinished(lebre)) {
               return false;
           }
        }
        return true;
    }

    public void printScoreboard() {
        Comparator<Lebre> criteria = new LebreComparator();
        ArrayList<Lebre> lebresSorted = this.lebres;

        lebresSorted.sort(criteria);

        String format = "Lebre: %-12s Distancia: %3sm   Pulos: %3s\n";

        for(Lebre lebre : lebresSorted) {
            System.out.printf(format, lebre.getName(), lebre.getMetersJumped(), lebre.getJumps());
        }
    }


}
