import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import java.awt.*;

public class CompositeKeyComparator extends WritableComparator {

    public CompositeKeyComparator(){
        super(CompositeKeyWritable.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        CompositeKeyWritable ckw1 = (CompositeKeyWritable) a;
        CompositeKeyWritable ckw2 = (CompositeKeyWritable) b;

        int result = -1 * ckw1.getWeb().compareTo(ckw2.getWeb());
        return result;
    }
}