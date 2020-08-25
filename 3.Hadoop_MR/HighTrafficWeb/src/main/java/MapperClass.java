import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapperClass extends Mapper<LongWritable, Text, Text, IntWritable> {

    Text Web = new Text();
    IntWritable one = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String [] Tokens = line.split(",");

        //127.0.0.1,15/Oct/2011:11:49:11,-0400,GET,/,HTTP/1.1,200,44
        // Key: web , hour ; val: count

        String [] timestamp = Tokens[1].split(":");
        String hour = timestamp[1];

        String input = Tokens[4] +"    " +  hour;

        Web.set(input);

        context.write(Web,one);
    }
}
