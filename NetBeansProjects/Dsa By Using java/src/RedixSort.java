import java.util.Arrays;

public class RedixSort
{
    public static void redixSort(int[] arr)
    {
        int max=Arrays.stream(arr).max().getAsInt();
        //do Count Sort for every digit place
        for(int exp=1;max/exp>0;exp*=10)
        {
            countSort(arr,exp);
        }
    }
    private static void countSort(int[] arr,int exp)
    {
        int n=arr.length;
        int [] output =new int[n];
        int []count =new int[10];
        Arrays.fill(count,0);
        for(int i=0;i<n;i++)
        {
            count[(arr[i]/exp)%10]++;

        }
        System.out.println("Count Array:"+exp+" :"+Arrays.toString(count));
        for(int i=1;i<10;i++)
        {
            count[i]=count[i]+count[i-1];
        }
        System.out.println("Updated Count "+ Arrays.toString(count));
        for(int i=n-1;i>=0;i--)
        {
            output[count[arr[i]/exp%10]-1]=arr[i];
            count[(arr[i]/exp)%10]--;

        }
        System.out.println("Output Array: "+ Arrays.toString(output));
        System.arraycopy(output,0,arr,0,n);

    }
    public static void main(String[]args)
    {
        int[] arr={29,83,471,36,91,8,2,1987};
        System.out.println("Original Array:"+Arrays.toString(arr));

        redixSort(arr);


        System.out.println("Sorted Array:"+Arrays.toString(arr));


    }
}