package info.scce.dime.matlab.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ArrayIndexerTest {
  public static List<Integer> one = List.of(0, 1, 2, 3, 4);
  public static List<Integer> oneDims = List.of(5);

  public static List<List<Integer>> two =
      List.of(List.of(0, 1, 2), List.of(3, 4, 5), List.of(6, 7, 8), List.of(9, 10, 11));
  public static List<Integer> twoDims = List.of(4, 3);

  public static List<List<List<Integer>>> three =
      List.of(
          List.of(List.of(0, 1), List.of(2, 3), List.of(4, 5)),
          List.of(List.of(6, 7), List.of(8, 9), List.of(10, 11)),
          List.of(List.of(12, 13), List.of(14, 15), List.of(16, 17)),
          List.of(List.of(18, 19), List.of(20, 21), List.of(22, 23)));
  public static List<Integer> threeDims = List.of(4, 3, 2);

  @Test
  public void indexingOneD() {
    assertEquals(2, ArrayIndexer.at(one, oneDims, List.of(2)));
  }

  @Test
  public void indexingTwoD() {
    assertEquals(2, ArrayIndexer.at(two, twoDims, List.of(0, 2)));
    assertEquals(8, ArrayIndexer.at(two, twoDims, List.of(2, 2)));
    assertEquals(9, ArrayIndexer.at(two, twoDims, List.of(3, 0)));
  }

  @Test
  public void indexingThreeD() {
    assertEquals(2, ArrayIndexer.at(three, threeDims, List.of(0, 1, 0)));
    assertEquals(7, ArrayIndexer.at(three, threeDims, List.of(1, 0, 1)));
    assertEquals(21, ArrayIndexer.at(three, threeDims, List.of(3, 1, 1)));
  }
}
