/*
 * Mark Weiman
 * https://github.com/markzz/emu-wn17/blob/COSC311/PROJ-02/src/FileHashTable.java
 * COSC 311
 * PROJECT 02
 * WINTER 2017
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import static java.util.Objects.hash;

public class FileHashTable {
    private RandomAccessFile raf;

    public FileHashTable(String fn) throws FileNotFoundException, IOException {
        this.raf = new RandomAccessFile(fn, "rw");

        if (this.raf.length() < 16 * 23) {
            this.raf.seek(this.raf.length());
            for (long i = this.raf.length(); i < 16 * 23; i++) {
                this.raf.write(0);
            }
        }
    }

    private void _increase_capacity() throws IOException {
        long len = this.raf.length();
        int entries = this._get_count();
        String[] data = new String[entries];
        byte[] bytes = new byte[20];
        int count = 0;

        for (long i = len; i <= len * 2; i++) {
            this.raf.write(0);
        }

        for (int i = 0; i < this._capacity() - 1; i++) {
            this.raf.seek(i * 23 + 22);
            if (this.raf.read() == 0x04) {
                this.raf.seek(i * 23);
                this.raf.read(bytes, 0, 20);
                data[count++] = new String(bytes);
                this.raf.seek(i * 23 + 22);
                this.raf.write(0x05);
            }
        }

        for (String d : data) {
            this.insert(d);
        }
    }

    private long _capacity() throws IOException {
        return this.raf.length() / 23;
    }

    private int _get_count() throws IOException {
        int ret = 0;
        for (int i = 0; i < this._capacity(); i++) {
            this.raf.seek(i * 23 + 22);

            if (this.raf.read() == 0x04) {
                ret++;
            }
        }
        return ret;
    }

    private String _resize_str(String str) {
        String data_cpy = str;
        if (data_cpy.length() < 20) {
            for (int i = data_cpy.length(); i < 22; i++) {
                data_cpy = data_cpy + Character.toString((char)0x00);
            }

            data_cpy = data_cpy + Character.toString((char)0x04);
        } else {
            data_cpy = data_cpy.substring(0, 20);
            data_cpy = data_cpy + Character.toString((char)0x00) + Character.toString((char)0x00);
            data_cpy = data_cpy + Character.toString((char)0x04);
        }

        return data_cpy;
    }

    public void insert(String data) throws IOException {
        String data_cpy = this._resize_str(data);

        long loc =  Math.abs(hash(data_cpy.trim()) % this._capacity());

        this.raf.seek(loc * 23 + 22);
        while (this.raf.read() == 0x04) {
            if (++loc >= _capacity()) {
                loc = 0;
            }
            this.raf.seek(loc * 23 + 22);
        }

        this.raf.seek(loc * 23);
        this.raf.write(data_cpy.getBytes());

        if ((double)this._get_count() / (double)this._capacity() > 0.50) {
            this._increase_capacity();
        }
    }

    public void delete(String data) throws IOException {
        String data_cpy = this._resize_str(data);
        data_cpy = data_cpy.trim();
        long loc = Math.abs(hash(data_cpy) % this._capacity());
        int tmp;
        byte[] b = new byte[20];
        String val;

        do {
            this.raf.seek(loc * 23);
            this.raf.read(b, 0, 20);
            val = new String(b);
            this.raf.seek(loc++ * 23 + 22);
            tmp = this.raf.read();
            if (tmp == 0x00) {
                return;
            }
        } while (!(data_cpy.equals(val) && tmp == 0x04));

        this.raf.seek(--loc * 23 + 22);
        this.raf.write(tmp+1);
    }

    public void print_table() throws IOException {
        System.out.printf("File Size: %d bytes (%d record capacity)\n", this.raf.length(), this._capacity());
        byte[] b = new byte[20];
        String data;
        char dib;
        int counter = 1;

        for (int i = 0; i < this._capacity(); i++) {
            this.raf.seek(i * 23);
            this.raf.read(b, 0, 20);
            data = new String(b);
            data = data.trim();
            this.raf.seek(i * 23 + 22);
            dib = (char)this.raf.read();
            System.out.printf("%d,\"%s\",%c\n", counter++, data, dib+48);
        }
    }
}
