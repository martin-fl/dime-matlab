package info.scce.dime.matlab.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ArrayFlattenerTest {

  public static List<Integer> one = List.of(0, 1, 2, 3, 4);
  public static List<Integer> oneDims = List.of(5);

  @Test
  public void dimsOfOneD() {
    assertEquals(oneDims, ArrayFlattener.dimsOf(one));
  }

  @Test
  public void flattenOneD() {
    assertEquals(one, ArrayFlattener.flatten(one, oneDims));
  }

  public static List<List<Integer>> two =
      List.of(List.of(0, 1, 2), List.of(3, 4, 5), List.of(6, 7, 8), List.of(9, 10, 11));
  public static List<Integer> twoDims = List.of(4, 3);

  @Test
  public void dimsOfTwoD() {
    assertEquals(twoDims, ArrayFlattener.dimsOf(two));
  }

  @Test
  public void flattenTwoD() {
    assertEquals(
        List.of(0, 3, 6, 9, 1, 4, 7, 10, 2, 5, 8, 11), ArrayFlattener.flatten(two, twoDims));
  }

  public static List<List<List<Integer>>> three =
      List.of(
          List.of(List.of(0, 1), List.of(2, 3), List.of(4, 5)),
          List.of(List.of(6, 7), List.of(8, 9), List.of(10, 11)),
          List.of(List.of(12, 13), List.of(14, 15), List.of(16, 17)),
          List.of(List.of(18, 19), List.of(20, 21), List.of(22, 23)));
  public static List<Integer> threeDims = List.of(4, 3, 2);

  @Test
  public void dimsOfThreeD() {
    assertEquals(threeDims, ArrayFlattener.dimsOf(three));
  }

  @Test
  public void flattenThreeD() {
    assertEquals(
        List.of(
            0, 6, 12, 18, 2, 8, 14, 20, 4, 10, 16, 22, 1, 7, 13, 19, 3, 9, 15, 21, 5, 11, 17, 23),
        ArrayFlattener.flatten(three, threeDims));
  }
}
