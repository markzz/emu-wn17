/*
 * Mark Weiman
 * https://github.com/markzz/emu-wn17/blob/COSC311/PROJ-01/src/Server.java
 * COSC 311
 * PROJECT 01
 * WINTER 2017
 */

import java.util.Random;

public class Server {
    private long tick_expiry = 0;
    private long customers_served = 0;

    public void new_customer(long curr_tick) {
        Random r = new Random();
        this.tick_expiry = curr_tick + (r.nextInt(4) + 2);
        this.customers_served++;
    }

    public boolean is_serving(long curr_tick) {
        return curr_tick < this.tick_expiry;
    }

    public long get_customers_served() {
        return this.customers_served;
    }
}
