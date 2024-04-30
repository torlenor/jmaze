# BFS and A* for graphs in Java demonstrated on a map

This project demonstrates the Breadth-First Search (BFS) and A* algorithms for graphs in Java, using a (game-)map as an example.

## Requirements

- Java (tested with OpenJDK 21)

## How to run

To use this project, follow these steps:

1. Compile it: `javac JMaze.java`
2. Run it: `java JMaze`

## Custom maps

You can "draw" your maps in ASCII and load them. The first line indicates the dimensions. Then you draw walls by marking them with `x`.
See `maps/simple.map` for a simple example.

You can load the map with

```bash
java JMaze maps/simple.map 1 1 28 19
```

where the last numbers are the start x/y and goal x/y coordinates for the path search.
