package br.ufrn.imd.controller;

import br.ufrn.imd.dao.Repositorio;
import br.ufrn.imd.model.Lebre;

public class RaceController {
    private static  Repositorio repo;

    public RaceController(Repositorio repo) {
        this.repo = repo;
    }

    private void setup() {
        for (Lebre lebre : repo.getLebres()) {
            lebre.start();
        }
    }

    public void race() {
        int currentTurnIndex = 0;

        setup();

        while(!repo.allFinished()) {
            repo.getLock().lock();
            try {
                if(!repo.getLebres().get(currentTurnIndex).isFinished()) {
                    repo.getLebres().get(currentTurnIndex).jump(currentTurnIndex,repo.getRaceDistance());
                }

                currentTurnIndex++;
                currentTurnIndex %= repo.getLebres().size();

                repo.getCondition().signalAll();
            } finally {
                repo.getLock().unlock();
            }
        }
    }
}
