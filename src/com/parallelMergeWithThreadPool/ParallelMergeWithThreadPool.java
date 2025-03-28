package com.parallelMergeWithThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelMergeWithThreadPool {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long startTime = System.currentTimeMillis();

		// 4 adet sıralı dizi oluşturuluyor
		int[] array1 = generateSortedArray(100000);
		int[] array2 = generateSortedArray(100000);
		int[] array3 = generateSortedArray(100000);
		int[] array4 = generateSortedArray(100000);

		// ExecutorService ile paralel işlem başlatıyoruz
		ExecutorService executor = Executors.newFixedThreadPool(4);

		// Her bir dizi için merge işlemi ayrı iş parçacığıyla yapılır
		Future<int[]> future1 = executor.submit(() -> mergeSortedArrays(array1, array2));
		Future<int[]> future2 = executor.submit(() -> mergeSortedArrays(array3, array4));

		// İşlemler tamamlandığında, iki sonucu birleştir
		int[] mergedArray1 = future1.get();
		int[] mergedArray2 = future2.get();

		// İki ara sonucu tek bir diziye birleştir
		int[] finalMergedArray = mergeSortedArrays(mergedArray1, mergedArray2);

		// Birleştirilen diziyi yazdır (ilk 10 eleman)
		for (int i = 0; i < 10; i++) {
			System.out.print(finalMergedArray[i] + " ");
		}
		System.out.println();
		executor.shutdown();
		long endTime = System.currentTimeMillis();

		// İşlem süresini hesapla (saniye cinsinden)
		long duration = (endTime - startTime); // Nanosecond cinsinden süre
		System.out.println("İşlem süresi: " + duration + " currentTimeMillis");
	}

	// Sıralı dizileri birleştiren fonksiyon
	public static int[] mergeSortedArrays(int[] array1, int[] array2) {
		int[] mergedArray = new int[array1.length + array2.length];
		int i = 0, j = 0, k = 0;

		while (i < array1.length && j < array2.length) {
			if (array1[i] <= array2[j]) {
				mergedArray[k++] = array1[i++];
			} else {
				mergedArray[k++] = array2[j++];
			}
		}

		while (i < array1.length) {
			mergedArray[k++] = array1[i++];
		}

		while (j < array2.length) {
			mergedArray[k++] = array2[j++];
		}

		return mergedArray;
	}

	// Sıralı dizi oluşturma (1'den başlayarak)
	public static int[] generateSortedArray(int size) {
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = i + 1;
		}
		return array;
	}

}
