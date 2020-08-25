import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class CompositeKeyWritable implements WritableComparable<CompositeKeyWritable> {

    String Ip;
    String Web;

    public CompositeKeyWritable(){
    }

    public CompositeKeyWritable(String Ip, String Web) {
        this.Ip = Ip;
        this.Web = Web;
    }

    public String getIp() {
        return Ip;
    }

    public void setIp(String Ip) {
        this.Ip = Ip;
    }

    public String getWeb() {
        return Web;
    }

    public void setWeb(String Web) {
        this.Web = Web;
    }

    public void readFields(DataInput in) throws IOException {
        Ip = in.readUTF();
        Web = in.readUTF();
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(Ip);
        out.writeUTF(Web);
    }

    public int compareTo(CompositeKeyWritable o) {
        int result = this.Web.compareTo(o.getWeb());
        return (result < 0 ? -1 : (result == 0 ? 0 : 1));
    }

    @Override
    public String toString() {
        return Ip + " \t " + Web;
    }
}