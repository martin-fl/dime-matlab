package info.scce.dime.matlab.common;

import java.util.ArrayList;
import java.util.List;

class ArrayIncrementer {
  public static void incr(List<Integer> dims, ArrayList<Integer> it) {
    ArrayIncrementer.incr(dims, it, 0);
  }

  private static void incr(List<Integer> dims, ArrayList<Integer> it, int rank) {
    if (rank == dims.size()) {
      return;
    }

    if (it.get(rank) < dims.get(rank) - 1) {
      it.set(rank, it.get(rank) + 1);
    } else {
      it.set(rank, 0);
      ArrayIncrementer.incr(dims, it, rank + 1);
    }
  }
}
