package hrds.commons.hadoop.opersolr;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.io.orc.OrcInputFormat;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.InputFormat;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;

public class ReadOrcFile {

	@SuppressWarnings({ "rawtypes" })
	public static RecordReader readFileData(Configuration config, Path filePath) {

		RecordReader reader = null;
		try {
			JobConf conf = new JobConf(config);
			InputFormat in = new OrcInputFormat();
			FileInputFormat.setInputPaths(conf, filePath);
			InputSplit[] splits = in.getSplits(conf, 1);
			reader = in.getRecordReader(splits[0], conf, Reporter.NULL);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return reader;
	}
}
