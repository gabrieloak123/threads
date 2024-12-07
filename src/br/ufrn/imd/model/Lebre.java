package br.ufrn.imd.model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Lebre extends Thread {
    private int jumps = 0;
    private int metersJumped = 0;
    private int lebreIndex;
    private boolean finished = false;
    private ReentrantLock lock;
    private Condition condition;

    public Lebre(String name) {
        super(name);
    }

    public int getJumps() {
        return jumps;
    }

    public int getMetersJumped() {
        return metersJumped;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setLebreIndex(int lebreIndex) {
        this.lebreIndex = lebreIndex;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void setLock(ReentrantLock lock) {
        this.lock = lock;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public void jump(int currentTurnIndex, int raceDistance) {
        lock.lock();

        try {
            while(currentTurnIndex != lebreIndex && !finished) {
                condition.await();
            }

            this.jumps++;
            this.metersJumped += ((int) (Math.random() * 10) % 3 + 1);
            this.finished = metersJumped >= raceDistance;

            condition.signalAll();

        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }
}
