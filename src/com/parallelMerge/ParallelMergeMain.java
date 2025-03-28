package com.parallelMerge;

public class ParallelMergeMain {

	public static void main(String[] args) {
		// Başlangıç zamanını al
		long startTime = System.currentTimeMillis();

		// 4 adet sıralı dizi oluşturuluyor
		int[] array1 = generateSortedArray(100000);
		int[] array2 = generateSortedArray(100000);
		int[] array3 = generateSortedArray(100000);
		int[] array4 = generateSortedArray(100000);

		// Tüm dizileri birleştir
		int[] mergedArray = mergeSortedArrays(array1, array2, array3, array4);

		// Birleştirilen diziyi yazdır (ilk 10 eleman)
		for (int i = 0; i < 10; i++) {
			System.out.print(mergedArray[i] + " ");
		}
		System.out.println(); // Daha düzgün bir çıktı için yeni satır ekleyelim

		// Bitiş zamanını al
		long endTime = System.currentTimeMillis();

		// İşlem süresini hesapla (saniye cinsinden)
		long duration = (endTime - startTime); // Nanosecond cinsinden süre
		System.out.println("İşlem süresi: " + duration + " currentTimeMillis");
	}

	// Sıralı dizileri birleştiren fonksiyon
	public static int[] mergeSortedArrays(int[]... arrays) {
		int totalLength = 0;
		for (int[] array : arrays) {
			totalLength += array.length;
		}

		int[] mergedArray = new int[totalLength];
		int[] indices = new int[arrays.length];

		// Merge işlemi başlatılır
		for (int i = 0; i < totalLength; i++) {
			int minValue = Integer.MAX_VALUE;
			int minArrayIndex = -1;

			// Tüm dizilerden küçük olanı bul
			for (int j = 0; j < arrays.length; j++) {
				if (indices[j] < arrays[j].length && arrays[j][indices[j]] < minValue) {
					minValue = arrays[j][indices[j]];
					minArrayIndex = j;
				}
			}

			// Küçük olan değeri yerleştir ve index'i güncelle
			mergedArray[i] = minValue;
			indices[minArrayIndex]++;
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
