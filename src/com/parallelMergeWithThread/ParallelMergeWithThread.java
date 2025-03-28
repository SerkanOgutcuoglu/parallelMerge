package com.parallelMergeWithThread;

public class ParallelMergeWithThread {
	public static void main(String[] args) throws InterruptedException {
		int[] array1 = generateSortedArray(100000);
		int[] array2 = generateSortedArray(100000);
		int[] array3 = generateSortedArray(100000);
		int[] array4 = generateSortedArray(100000);
		long startTime = System.nanoTime();

		Thread t1 = new Thread(new MergeTask(array1, array2, 0));
		Thread t2 = new Thread(new MergeTask(array3, array4, 1));
		Thread t3 = new Thread(new MergeTask(array1, array3, 2));
		Thread t4 = new Thread(new MergeTask(array2, array4, 3));

		// İş parçacıklarını başlat
		t1.start();
		t2.start();
		t3.start();
		t4.start();

		// İş parçacıklarının bitmesini bekle
		t1.join();
		t2.join();
		t3.join();
		t4.join();

		// Birleştirilmiş alt dizileri al
		int[] mergedArray1 = MergeTask.getMergedArray(0);
		int[] mergedArray2 = MergeTask.getMergedArray(1);
		int[] mergedArray3 = MergeTask.getMergedArray(2);
		int[] mergedArray4 = MergeTask.getMergedArray(3);

		// Son olarak tüm alt dizileri birleştir
		int[] finalMergedArray = mergeSortedArrays(mergedArray1, mergedArray2);
		finalMergedArray = mergeSortedArrays(finalMergedArray, mergedArray3);
		finalMergedArray = mergeSortedArrays(finalMergedArray, mergedArray4);

		// Birleştirilen diziyi yazdır (ilk 10 eleman)
		for (int i = 0; i < 10; i++) {
			System.out.print(finalMergedArray[i] + " ");
		}
		System.out.println(); // Daha düzgün bir çıktı için yeni satır ekleyelim

		// Bitiş zamanını al
		long endTime = System.nanoTime();

		// İşlem süresini hesapla (milisaniye cinsinden)
		long durationInMillis = (endTime - startTime) / 1000000; // Nanosecond'ı milisaniyeye çevir

		// İşlem süresini yazdır
		System.out.println("İşlem süresi: " + durationInMillis + " milisaniye");
	}

	public static int[] mergeSortedArrays(int[] array1, int[] array2) {
		int[] mergedArray = new int[array1.length + array2.length];
		int i = 0, j = 0, k = 0;

		// İki diziyi birleştir
		while (i < array1.length && j < array2.length) {
			if (array1[i] < array2[j]) {
				mergedArray[k++] = array1[i++];
			} else {
				mergedArray[k++] = array2[j++];
			}
		}

		// Kalan elemanları ekle
		while (i < array1.length) {
			mergedArray[k++] = array1[i++];
		}
		while (j < array2.length) {
			mergedArray[k++] = array2[j++];
		}

		return mergedArray;
	}

	public static int[] generateSortedArray(int size) {
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = i + 1;
		}
		return array;
	}
}
