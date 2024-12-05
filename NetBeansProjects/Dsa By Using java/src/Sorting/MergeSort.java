package Sorting;

public class MergeSort {

    // Function to divide the array and call merge
    private static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int mid = (l + r) / 2;

            // Sort first and second halves
            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);

            // Merge the sorted halves
            merge(arr, l, mid, r);
        }
    }

    // Function to merge two halves
    private static void merge(int[] arr, int l, int mid, int r) {
        int n1 = mid - l + 1;
        int n2 = r - mid;

        // Temporary arrays
        int[] left = new int[n1];
        int[] right = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            left[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            right[j] = arr[mid + 1 + j];

        // Merge the temp arrays

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray
        int k = l;
        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of left[], if any
        while (i < n1) {
            arr[k] = left[i];
            i++;
            k++;
        }

        // Copy remaining elements of right[], if any
        while (j < n2) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    // Utility function to print the array
    private static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {8, 57, 9, 111, 6, 7, 75};

        System.out.println("Array before sorting:");
        printArray(arr);

        mergeSort(arr, 0, arr.length - 1);

        System.out.println("Array after sorting:");
        printArray(arr);
    }
}
