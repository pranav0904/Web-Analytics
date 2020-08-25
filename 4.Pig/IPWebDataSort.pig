Data = LOAD '/home/pk/Desktop/Big Data Project/3.Hadoop_MR/MR_op/IPWebData';
 
IpWebD = FOREACH Data GENERATE (chararray) $0 as IP, (chararray) $1 as Resource, (int) $2 as Data_size;

Result = FILTER IpWebD BY Data_size > 51200;

odr = ORDER Result BY Data_size DESC;

lmt = LIMIT odr 100;

Store lmt into '/home/pk/Desktop/Big Data Project/4.Pig/IPWebDataSorting';

