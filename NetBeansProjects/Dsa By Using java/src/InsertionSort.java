public class InsertionSort {
    public static void main(String[] args) {
        int arr[]={5,6,2,3,1};
        int key;
        int j;

        System.out.println("Before Sorting:");
        for(int num:arr)
        {
            System.out.print(num+" ");
        }

        for (int i=1;i<arr.length;i++)
        {
             key=arr[i];
            j=i-1;
            while (j>=0 && arr[j]>key)
            {
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1]=key;
            System.out.println();
            for(int num:arr)
            {
                System.out.print(num+" ");
            }

        }
        System.out.println();
        System.out.println("After Sorting:");
        for(int num:arr)
        {
            System.out.print(num+" ");
        }
    }
}
