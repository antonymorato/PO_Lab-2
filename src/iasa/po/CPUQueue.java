//CPUQueue
package iasa.po;

import java.util.LinkedList;
import java.util.Queue;

public class CPUQueue {

    private Queue<String> queue = new LinkedList<>();
    private int capacity;
    boolean tookOtherThreadProcess=false;

    private String queueName;

    private int maxSize = 0;

    public boolean isTookOtherThreadProcess() {
        return tookOtherThreadProcess;
    }

    public void setTookOtherThreadProcess(boolean tookOtherThreadProcess) {
        this.tookOtherThreadProcess = tookOtherThreadProcess;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public CPUQueue(int capacity) {
        this.capacity = capacity;
    }
    public int getMaxSize() {
        return maxSize;
    }
    public boolean isFull()
    {
        return queue.size() == capacity;
    }
    public boolean isEmpty(){return queue.size()==0;}
    public synchronized void put(String element) throws InterruptedException, FullQueueExeption {
//        while(this.isFull()) {
//            System.out.println("Queue is FULL, waiting..");
//            wait();
//        }
        if (!this.isFull())
        queue.add(element);
        else throw new FullQueueExeption("Full Queue");

        if(queue.size()>maxSize)
            maxSize=queue.size();

        System.out.println("Process added, queue size = [" + queue.size() + "]\n");
        notify();
    }

    public synchronized String get() throws InterruptedException {
        while(queue.isEmpty()) {

            System.out.println("Queue is EMPTY, waiting..");
            wait();

        }
        String item = queue.remove();
        System.out.println("Process removed, queue size = [" + queue.size() + "]");
       // notify();
        return item;
    }
}

