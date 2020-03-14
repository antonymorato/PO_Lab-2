
//CPU
package iasa.po;


public class CPU implements Runnable{

    CPUQueue queue;
    private String  processTypeToCheckPattern;
    public CPU(CPUQueue q, String processTypeToCheckPattern){
        this.queue = q;
        this.processTypeToCheckPattern=processTypeToCheckPattern;
    }
    public void run(){
        long processingTime;
        while(true) {
            int randMin=20;
            int randMax=80; // rand = [20,100]
            processingTime = randMin + (int) (Math.random() * randMax);
            try {
                if (queue.get().equals(processTypeToCheckPattern))
                    queue.setTookOtherThreadProcess(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("CPU: Processed in time " + processingTime + "\n");
            try {
                Thread.sleep(processingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (queue.isTookOtherThreadProcess()) {
                System.out.println("***Reserve queue took:" + processTypeToCheckPattern + "***");
                queue.setTookOtherThreadProcess(false);
            }
        }
    }
}
