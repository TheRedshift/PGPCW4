package CW4;

import CW4.Controller;

import java.io.IOException;

/**
 * Created by Rahul Soni on 02/05/2016.
 */
public class Main {



    public static void main(String[] args) throws IOException {
        Controller c = new Controller();
        Model m = new Model( c);
        View v = new View( c);

        c.setViewAndModel(v, m);
        m.create();
        v.create();
    }
}
