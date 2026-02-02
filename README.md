# Barcodes

## About

Fully documented Barcodes.java (project 1) and StringExploration.java (lab 1)

### Authors

*   Daniel Lee, Juan-Diego Carpio
*  ...

### Resources

*   Talked to Professor Osera about the project

## Write-up

### Part 2.1: Old Hat, New Hat
1. 
    (a) Useful functions over strings
        strcmp, strcpy, strlen
    (b) operations (common programming patterns) over strings
        finding characters in strings, checking if strings are equal, shortening strings, concatenating strings
2. 
    strcmp -> compareTo()
    strcpy ->
        String original = "Hello";
        String copy = new String(original);
    strlen -> length()
    
Operations (in order they were listed):
    index()
    equals()
    substring()
    +

### Part 2.2: Iceberg, Right Head!
1.  true
    false
    false
2. To compare two strings for equality, we must use .equals()
3. == does not work for the examples above because using == checks if the strings, which are pointers, are equal to one another. 
Although the contents might agree, the literal pointers are different. In B and C, the strings are in different locations in memory,
so == returns false.
4. Writing two strings with the same string literal in Java results in both pointing to the same location in memory. Thus, using 
== and .equals() in A both return true.  