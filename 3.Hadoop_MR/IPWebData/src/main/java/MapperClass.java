import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapperClass extends Mapper<LongWritable, Text, CompositeKeyWritable, IntWritable> {

    IntWritable data = new IntWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        // 127.0.0.1,15/Oct/2011:11:49:11,-400,GET,/,HTTP/1.1,200,44

        String line = value.toString();
        String[] tokens = line.split(",");

        Integer len = tokens.length;
        String Ip = null;
        String Web = null;

        //String[] timestamp = tokens[1].split(":");
        //String date=null;

        try {
            if (tokens.length == 8) {
                if(tokens[len-1].trim().matches("-?\\d+")){
                    Ip = tokens[0];
                    Web = tokens[4];
                    data.set(Integer.parseInt(tokens[len-1]));
                }
                else {
                    Ip = "Unknown";
                    Web = "N/A";
                    data.set(0);
                }
            } else{
                Ip = "Unknown";
                Web = "N/A";
                data.set(0);
            }
        } catch (Exception ex) {
            //Do Nothing
        }

        CompositeKeyWritable obj = new CompositeKeyWritable(Ip, Web);

        context.write(obj, data);
    }
}