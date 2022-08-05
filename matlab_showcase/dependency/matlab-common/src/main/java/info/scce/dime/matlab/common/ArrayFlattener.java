package info.scce.dime.matlab.common;

import java.util.ArrayList;
import java.util.List;

class ArrayFlattener {
  public static List<Integer> dimsOf(List<?> arr) {
    ArrayList<Integer> dims = new ArrayList<>();

    dims.add(arr.size());

    if (arr.get(0) instanceof List) {
      dims.addAll(ArrayFlattener.dimsOf((List) arr.get(0)));
    }

    return dims;
  }

  public static List<Object> flatten(List<?> arr, List<Integer> dims) {
    int n_elements = dims.stream().reduce(1, (a, b) -> a * b);
    ArrayList<Object> elems = new ArrayList<>(n_elements);
    ArrayList<Integer> indices = new ArrayList<>(dims.size());
    for (int i = 0; i < dims.size(); ++i) indices.add(0);

    while (elems.size() < n_elements) {
      Object elem = ArrayIndexer.at(arr, dims, indices);
      elems.add(elem);
      ArrayIncrementer.incr(dims, indices);
    }

    return elems;
  }
}
