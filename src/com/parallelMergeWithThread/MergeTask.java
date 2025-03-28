package com.parallelMergeWithThread;

class MergeTask implements Runnable {
	private final int[] array1;
	private final int[] array2;
	private final int index;
	private static int[][] mergedArrays = new int[4][]; // Alt dizileri tutacak array

	public MergeTask(int[] array1, int[] array2, int index) {
		this.array1 = array1;
		this.array2 = array2;
		this.index = index;
	}

	@Override
	public void run() {
		// İki diziyi birleştir
		int[] mergedArray = ParallelMergeWithThread.mergeSortedArrays(array1, array2);

		// Sonuçları belirtilen index'e kaydet
		mergedArrays[index] = mergedArray;
	}

	// Birleştirilmiş dizileri almak için getter
	public static int[] getMergedArray(int index) {
		return mergedArrays[index];
	}
}
