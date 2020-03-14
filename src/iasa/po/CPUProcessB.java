package iasa.po;

public class CPUProcessB extends CPUProcessParent {

    CPUQueue mainQueue,reserveQueue;
    int generateNumber;
    public CPUProcessB(CPUQueue mainQueue,CPUQueue reserveQueue, int gN){
        this.mainQueue= mainQueue;
        this.reserveQueue=reserveQueue;
        this.generateNumber = gN;
        this.setName("Process-B");

    }
    @Override
    public void run(){
        long generateDelay;
        for (int i = 0; i < generateNumber; i++) {
            int randMin=10;
            int randMax=40; // rand = [10,50]
            generateDelay = randMin + (int) (Math.random() * randMax);
            try {
                Thread.sleep(generateDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("Process generated with delay " + generateDelay);
                mainQueue.put(this.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (FullQueueExeption fullQueueExeption) {
                try {System.out.println("Process generated with delay " + generateDelay);
                    reserveQueue.put(this.getName());
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } catch (FullQueueExeption queueExeption) {

                }


            }
        }

        System.out.println("No more processes. Greatest queue size was " + mainQueue.getMaxSize());
        if (mainQueue.isTookOtherThreadProcess())
            System.out.println(this.getName()+"-queue took other process");

    }

}
