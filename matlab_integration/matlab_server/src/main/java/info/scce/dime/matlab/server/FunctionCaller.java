package info.scce.dime.matlab.server;

import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;
import info.scce.dime.matlab.common.Arg;
import info.scce.dime.matlab.common.FunctionCall;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class FunctionCaller {
  public static <T> Future<T> call(FunctionCall fc, MatlabEngine engine)
      throws /*EngineException,*/ InterruptedException, ExecutionException {

    ArrayList<Object> args = new ArrayList<>();
    for (Arg arg : fc.args) {
      switch (arg.ty) {
        case Numeric:
          // using a 1*1 array otherwise matlab receives a 0.0 for some reason
          double[] weird = {(double) arg.x};
          args.add(weird);
          break;
        case Str:
          args.add(arg.s);
          break;
        case Array:
          int[] dims = new int[arg.dims.size()];
          for (int i = 0; i < arg.dims.size(); ++i) dims[i] = arg.dims.get(i);
		  // TODO: handle every types, not just double 
          double[] xs = new double[arg.xs.size()];
          for (int i = 0; i < arg.xs.size(); ++i) xs[i] = (double) arg.xs.get(i);

          args.add(engine.fevalAsync("reshape", xs, dims).get());

          break;
      }
    }

    return engine.fevalAsync(fc.name, args.toArray());
  }
}
