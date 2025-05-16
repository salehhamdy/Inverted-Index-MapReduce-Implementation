import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

import java.io.IOException;

public class InvertedIndexReducer extends Reducer<Text, Text, Text, Text> {
    private final Text result = new Text(); // Reuse the same Text object

    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        for (Text val : values) {
            sb.append(val.toString()).append(",");
        }
        result.set(sb.substring(0, sb.length() - 1));
        context.write(key, result);
    }
}
