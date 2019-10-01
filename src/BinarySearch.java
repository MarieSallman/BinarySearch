import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;

public class BinarySearch {

        static long MAXVALUE = 2000000000;
        static long MINVALUE = 0;
        static int MAXSIZE = 100000;
        static int MINSIZE = 10000;
        static long numberOfTrials = 10;
        static int SIZEINC = 10000;

        public static void main(String[] args) {

            long randomList[] = {123,227,308,452,553,670,716,734,794,824,900};
            System.out.println("Checking correctness of binary sort function...");
            System.out.println("Looking for: " + "92");
            System.out.println(Arrays.toString(randomList));
            binarySearch(randomList, 92);
            System.out.println(binarySearch(randomList, 92));
            System.out.println("---------------------------------------");


            System.out.println("InputSize   AvgTime  TotalTimeForAllTrials");


            /* For each size of input that we want to test */
            for(int inputSize = MINSIZE;inputSize<=MAXSIZE;inputSize = inputSize + SIZEINC) {
                /* repeat for the desired number of trials (for a specific size of input) */
                long totalTime = 0;
                for (long trial = 0; trial < numberOfTrials; trial++) {
                    /* For one trial: */
                    /* generate (random?) input data of desired size (a list of N random numbers) */
                    long[] testList = createAscendingList(inputSize);
                    int searchFor = (int)Math.round(MINVALUE + Math.random() * (MAXVALUE - MINVALUE));

                    /* run the trial */
                    long timeStampBefore = getCpuTime(); /* get a "before" time stamp" */

                    /* apply test function to the test input */
                    binarySearch(testList, searchFor);


                    /* get an "after" time stamp, calculate trial time */
                    long timeStampAfter = getCpuTime();
                    long trialTime = timeStampAfter - timeStampBefore;

                    totalTime = totalTime + trialTime;
                }
                double averageTime = (double)totalTime / (double)numberOfTrials;
                /* print data for this size of input */
                System.out.println(inputSize + "    " + averageTime + "     " + totalTime);
            }

        }

        /* return the index of the searched number if found or -1 if not found */
        public static long binarySearch(long[] list, int searchFor){
            int i = 0;
            int j = list.length -1;
            int k = (i+j)/2;
            while (i <= j){
                if (list[k] < searchFor){
                    i = k + 1; }
                else if(list[k] == searchFor){
                    return k; }
                else{
                    j = k - 1; }
                k = (i+j)/2; }
            return -1; }

        public static long[] createAscendingList(int size){

            int randomInt = (int)Math.round(MINVALUE + Math.random() * (MAXVALUE - MINVALUE));
            long[] newList = new long[size];
            for(int j=0; j<size; j++){
                newList[j] = randomInt + Math.round(MINVALUE + Math.random() * (MAXVALUE - MINVALUE));
            }
            for(int i = 0;i<newList.length;i++) {
                /* for index from 0 to N-1 compare item[index] to next item, swap if needed */
                for(int j=0;j<newList.length-1;j++){
                    if(newList[j]>newList[j+1]){
                        long tmp = newList[j];
                        newList[j] = newList[j + 1];
                        newList[j + 1] = tmp;
                    }
                }
            }

            return newList;
        }

        public static long getCpuTime( ){
            ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
            return bean.isCurrentThreadCpuTimeSupported( ) ?
                    bean.getCurrentThreadCpuTime( ) : 0L;
        }

    }


