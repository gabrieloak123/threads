package br.ufrn.imd.view;

import br.ufrn.imd.controller.RaceController;
import br.ufrn.imd.dao.Repositorio;
import br.ufrn.imd.model.Lebre;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CorridaView {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Lebre l1 = new Lebre("Calleri");
        Lebre l2 = new Lebre("Luciano");
        Lebre l3 = new Lebre("Lucas");
        Lebre l4 = new Lebre("Arboleda");
        Lebre l5 = new Lebre("Alan Franco");

        Repositorio repo = new Repositorio(lock,condition);

        repo.add(l1);
        repo.add(l2);
        repo.add(l3);
        repo.add(l4);
        repo.add(l5);

        RaceController controller = new RaceController(repo);

        controller.race();

        System.out.println("----------------------Ordem de Chegada----------------------");
        repo.printScoreboard();
        System.out.println("------------------------------------------------------------");
    }
}
