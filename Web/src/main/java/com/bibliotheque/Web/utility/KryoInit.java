package com.bibliotheque.Web.utility;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class KryoInit {

        Kryo kryo = new Kryo();
        Output output = new Output(new FileOutputStream("file.dat"));
        Input input = new Input(new FileInputStream("file.dat"));

    public KryoInit() throws FileNotFoundException {
    }
}

