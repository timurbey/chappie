/* ************************************************************************************************
* Copyright 2019 SUNY Binghamton
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

package jlibc;

import jlibc.proc.CPU;
import jlibc.proc.Task;

public class Test {
  public static void main(String[] args) {
    if (libc.getProcessId() == -1) {
      System.out.println("could not initialize libc");
      System.exit(0);
    }

    if (libc.getTaskId() == -1) {
      System.out.println("could not call gettid");
      System.exit(0);
    }

    Task[] tasks = Task.getCurrentTasks();
    if (tasks.length == 0) {
      System.out.println("could not sample main task " + libc.getProcessId());
      System.exit(0);
    }

    for (Task task: tasks)
      System.out.println(task);

    CPU[] cpus =  CPU.getCPUs();
    if (cpus.length == 0) {
      System.out.println("could not sample cpus ");
      System.exit(0);
    }

    for (CPU cpu: cpus)
      System.out.println(cpu);
  }
}
