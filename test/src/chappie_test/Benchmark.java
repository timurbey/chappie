/* ************************************************************************************************
* Copyright 2017 SUNY Binghamton
* Permission is hereby granted, free of charge, to any person obtaining a copy of this
* software and associated documentation files (the "Software"), to deal in the Software
* without restriction, including without limitation the rights to use, copy, modify, merge,
* publish, distribute, sublicense, and/or sell copies of the Software, and to permit
* persons to whom the Software is furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all copies or
* substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
* INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
* PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
* FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
* OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
* DEALINGS IN THE SOFTWARE.
* ***********************************************************************************************/

package chappie_test;

import chappie_test.*;

import java.util.List;
import java.util.Arrays;

import java.io.*;

public abstract class Benchmark implements Runnable {

  protected Benchmark() {
  }

  public void run() {
    work();
  }

  public abstract void work();

  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    System.out.println("Making " + count + " thread" + (count != 1 ? "s" : ""));

    Thread[] threads = new Thread[count];
    for(int i = 0; i < threads.length; ++i) {
      threads[i] = new Thread(new MatrixMultiplication(250, 250));
    }

    for(int i = 0; i < threads.length; ++i) {
      threads[i].start();
    }

    for(int i = 0; i < threads.length; i++)
      try {
        threads[i].interrupt();
        threads[i].join();
        System.out.println(threads[i].getName() + " finished");
      } catch (InterruptedException e) { }
  }
}
