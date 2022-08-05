package info.scce.dime.matlab.common;

import java.util.List;

// poorman's tagged union
// instead of just storing args as a Object[] or ArrayList<Object>
// we have to do some static dispatch because :
//     1. Object is not serializable natively, so we have to use something
//	      else (I went with json)
//     2. Google's Gson parser transforms double[][][] (for example) to JSON
//        back to 3 nested ArrayList which the MatlabEngine doesn't understand
//     3. Trying to transform the nested ArrayLists into double[][][] after
//        getting the Json is possible through dynamic typing with an
//        ((Object[])[])[] hidden as Object[] or Object, but MatlabEngine
//        still doesn't like it
//
// so here we are, flattening arrays and using enums in java
// the flattened arrays will be reconstructed later directly in matlab
// with the reshape function
public class Arg {
  public enum ArgType {
    Numeric,
    Str,
    Array
  }

  public ArgType ty;

  public Number x;
  public String s;
  public List<Object> xs;
  public List<Integer> dims;

  public Arg(Number x) {
    this.ty = ArgType.Numeric;
    this.x = x;
  }

  public Arg(String s) {
    this.ty = ArgType.Str;
    this.s = s;
  }

  public Arg(List<Object> xs, List<Integer> dims) {
    this.ty = ArgType.Array;
    this.xs = xs;
    this.dims = dims;
  }
}
