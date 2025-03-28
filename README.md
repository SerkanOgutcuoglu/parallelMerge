# Parallel Merge Performance Comparison

## Overview
This project compares the performance of merging multiple sorted arrays using **single-threaded** and **multi-threaded** approaches. The goal is to analyze how parallel execution can optimize the merging process.

## Problem Statement
You have four sorted arrays (in ascending order), each containing **100,000 elements**. The task is to merge them into a single sorted array with **400,000 elements**.

### Implementations:
1. **Single-Threaded Merge**
   - Implemented in `ParallelMergeMain.java`
   - Uses a traditional approach to merge the arrays sequentially.

2. **Multi-Threaded Merge**
   - Implemented in `ParallelMergeWithThread.java`
   - Uses multiple threads to merge the arrays in parallel.
   - `MergeTask.java` defines the thread tasks for parallel execution.

## Project Structure
```
src/
 ├── com.parallelMerge/
 │   ├── ParallelMergeMain.java  # Single-threaded merging
 │
 ├── com.parallelMergeWithThread/
 │   ├── MergeTask.java          # Thread implementation for merging
 │   ├── ParallelMergeWithThread.java  # Multi-threaded merging
```

## How to Run the Project
### Single-Threaded Execution
```sh
javac com/parallelMerge/ParallelMergeMain.java
java com.parallelMerge.ParallelMergeMain
```

### Multi-Threaded Execution
```sh
javac com/parallelMergeWithThread/*.java
java com.parallelMergeWithThread.ParallelMergeWithThread
```

## Expected Results
- The multi-threaded implementation should provide better performance by utilizing multiple cores.
- Performance improvements depend on factors such as system hardware and thread management.

## Key Learnings
- **Parallel processing** can significantly reduce execution time when merging large datasets.
- Proper **thread management** is crucial to avoid synchronization issues.
- The performance gain may vary based on the **number of threads and system capabilities**.

Feel free to modify the code and experiment with different approaches! 🚀

