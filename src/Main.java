import iasa.po.*;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int queueCapacity = 5;
        int processToGenerate = 15;
        List<Thread> threadList=new LinkedList<>();
        System.out.println("\nQueue capacity = [" + queueCapacity + "], will be generated " + processToGenerate + " processes\n");
        CPUQueue qa = new CPUQueue(queueCapacity);
        CPUQueue qb=new CPUQueue(queueCapacity);
        CPUProcessParent Cp1 = new CPUProcessA(qa,qb, processToGenerate);
        CPUProcessParent Cp2 = new CPUProcessB(qb,qa, processToGenerate);

        CPU cpu1 = new CPU(qa,"Process-B");
        CPU cpu2 = new CPU(qb,"Process-A");
        //Thread t1=new Thread(Cp1);
        //Thread t2=new Thread(Cp2);
        //Thread t3=new Thread(cpu1);
        //Thread t4=new Thread(cpu2);
        threadList.add(new Thread(Cp1));
        threadList.add(new Thread(Cp2));
        threadList.add(new Thread(cpu1));
        threadList.add(new Thread(cpu2));

        for (Thread thread:threadList
             ) {thread.setDaemon(true);
            thread.start();
        }
//
//        t1.setDaemon(true);
//        t2.setDaemon(true);
//        t3.setDaemon(true);
//        t4.setDaemon(true);
//
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();




        Thread.sleep(5000);
        System.out.println("QA max size:"+qa.getMaxSize());
        System.out.println("QB max size:"+qb.getMaxSize());


        System.out.println("Main finished");
    }
}
