data = LOAD '/home/pk/Desktop/Big Data Project/3.Hadoop_MR/MR_op/HighTrafficWeb.csv' AS (Resource:chararray,hour:chararray, count:int);

grp = GROUP data ALL;

result = FOREACH grp GENERATE (data.Resource, data.hour), MAX(data.count) as cnt;

Store result into '/home/pk/Desktop/Big Data Project/4.Pig/trafficByHour';

