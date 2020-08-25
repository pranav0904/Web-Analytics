import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

public class MapperClass extends Mapper<LongWritable, Text, Text, LongWritable> {
    Text ip = new Text();
    LongWritable data = new LongWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String [] Tokens = line.split(",");

        int len = Tokens.length;

        //127.0.0.1,15/Oct/2011:11:49:11,-400,GET,/,HTTP/1.1,200,44

        if(Tokens[len-1].trim().matches("-?\\d+")){
            data.set(Long.parseLong(Tokens[len-1]));
        }
        else {
            data.set(0);
        }
        ip.set(Tokens[0]);
        context.write(ip,data);
        // Key: IP , Values: bytes of data
    }
}
