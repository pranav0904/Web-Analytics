import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;
public class ReducerClass extends Reducer<Text, LongWritable,Text,LongWritable> {

    LongWritable result = new LongWritable();
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long Total = 0;
        for (LongWritable val: values) {
            Total += val.get();
        }
        result.set(Total);
        context.write(key,result);
    }
}