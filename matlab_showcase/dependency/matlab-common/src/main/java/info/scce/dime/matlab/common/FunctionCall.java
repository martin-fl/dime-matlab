package info.scce.dime.matlab.common;

import java.util.ArrayList;
import java.util.List;

// Make this better! by handling everytype as `Object`, maybe handling both java
// arrays and `List`s, still flattening them
public class FunctionCall {
  public String name;
  public List<Arg> args;

  public FunctionCall(String name, Object... args) {
    this.name = name;
    this.args = new ArrayList<>(args.length);
    for (Object arg : args) {
      if (arg instanceof String) {
        this.args.add(new Arg((String) arg));
      } else if (arg instanceof Number) {
        this.args.add(new Arg((Number) arg));
      } else if (arg instanceof List) {
        List<Integer> dims = ArrayFlattener.dimsOf((List) arg);
        List<Object> flattened = ArrayFlattener.flatten((List) arg, dims);
        this.args.add(new Arg(flattened, dims));
      } else {
        System.out.println("unrecognised " + arg.getClass().getName());
      }
    }
  }
}
