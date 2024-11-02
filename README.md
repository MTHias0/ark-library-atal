# ArkLibrary System Documentation

## Introduction
This document provides an overview of the data storage and organization methods used in the ArkLibrary book management system.

## Features
- **Book Registration**: Add new books to the library.
- **List and Sort Books**: Display a list of books sorted by title or author.
- **Search by Code**: Locate a specific book using its unique code.

## Data Structure: Dynamic Array

A dynamic array was selected to store books in memory due to its flexible and efficient nature for dynamically adjusting to changes in data size. Key benefits of using a dynamic array include:

- **Fast Element Access**: Access to elements by index is in constant time, O(1).
- **Flexible Size**: The dynamic array grows or shrinks automatically as books are added or removed, optimizing memory usage.
- **Simplicity in Implementation**: A dynamic array is straightforward to implement and maintain, resulting in more readable and easily debuggable code.

## Sorting Algorithm: Quicksort

For sorting books, the Quicksort algorithm was chosen for its efficiency and suitability for our application’s requirements:

- **Average Complexity O(n log n)**: Quicksort performs efficiently for large datasets, particularly in typical usage scenarios.
- **In-place Sorting**: Sorting occurs within the original array without significant additional memory allocation.

**Note**: We use a recursive implementation of Quicksort, selecting the pivot as the last element to keep the implementation simple while maintaining performance.