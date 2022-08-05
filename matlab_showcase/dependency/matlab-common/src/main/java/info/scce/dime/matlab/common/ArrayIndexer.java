package info.scce.dime.matlab.common;

import java.util.List;

class ArrayIndexer {
  // public static Object at(Object arr, List<Integer> dims, List<Integer> indices) {
  //  return ArrayIndexer.at(arr, dims, indices, 0);
  // }

  // private static Object at(Object arr, List<Integer> dims, List<Integer> indices, int rank) {
  //  if (dims.size() != indices.size()) return null;

  //  if (dims.size() == rank + 1) {
  //    return Array.get(arr, indices.get(rank));
  //  }

  //  return ArrayIndexer.at(Array.get(arr, indices.get(rank)), dims, indices, rank + 1);
  // }

  public static Object at(List<?> arr, List<Integer> dims, List<Integer> indices) {
    if (dims.size() != indices.size()) return null;
    return ArrayIndexer.at(arr, dims, indices, 0);
  }

  private static Object at(List<?> arr, List<Integer> dims, List<Integer> indices, int rank) {
    if (arr.get(0) instanceof List) {
      return ArrayIndexer.at((List) arr.get(indices.get(rank)), dims, indices, rank + 1);
    } else {
      return arr.get(indices.get(rank));
    }
  }
}
