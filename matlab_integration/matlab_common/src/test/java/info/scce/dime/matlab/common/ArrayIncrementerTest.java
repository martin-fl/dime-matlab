package info.scce.dime.matlab.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ArrayIncrementerTest {
  public static List<Integer> dims = List.of(3, 1, 4, 2, 1);
  public static List<Integer> max = List.of(2, 0, 3, 1, 0);

  @Test
  public void incrementThreeTimesFromZero() {
    ArrayList<Integer> it = new ArrayList(List.of(0, 0, 0, 0, 0));
    ArrayIncrementer.incr(dims, it);
    assertEquals(List.of(1, 0, 0, 0, 0), it);
    ArrayIncrementer.incr(dims, it);
    assertEquals(List.of(2, 0, 0, 0, 0), it);
    ArrayIncrementer.incr(dims, it);
    assertEquals(List.of(0, 0, 1, 0, 0), it);
  }

  @Test
  public void incrementOnce() {
    ArrayList<Integer> it = new ArrayList(List.of(2, 0, 4, 0, 0));
    ArrayIncrementer.incr(dims, it);
    assertEquals(List.of(0, 0, 0, 1, 0), it);
  }

  @Test
  public void incrementAll() {
    ArrayList<Integer> it = new ArrayList(List.of(0, 0, 0, 0, 0));
    ArrayIncrementer.incr(dims, it);
    ArrayIncrementer.incr(dims, it);
    ArrayIncrementer.incr(dims, it);
    ArrayIncrementer.incr(dims, it);
    ArrayIncrementer.incr(dims, it);
    ArrayIncrementer.incr(dims, it);
    ArrayIncrementer.incr(dims, it);
    ArrayIncrementer.incr(dims, it);
    ArrayIncrementer.incr(dims, it);
    ArrayIncrementer.incr(dims, it);
    ArrayIncrementer.incr(dims, it);
    ArrayIncrementer.incr(dims, it);
    ArrayIncrementer.incr(dims, it);
    ArrayIncrementer.incr(dims, it);
    ArrayIncrementer.incr(dims, it);
    ArrayIncrementer.incr(dims, it);
    ArrayIncrementer.incr(dims, it);
    ArrayIncrementer.incr(dims, it);
    ArrayIncrementer.incr(dims, it);
    ArrayIncrementer.incr(dims, it);
    ArrayIncrementer.incr(dims, it);
    ArrayIncrementer.incr(dims, it);
    ArrayIncrementer.incr(dims, it);
    assertEquals(max, it);
  }
}
