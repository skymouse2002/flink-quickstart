package org.myorg.quickstart;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * @menu
 * @company 金邦达互联网事业部
 * @Description： 描述
 * @Author liuchenguang
 * @Date: 2022/3/7 15:25
 * @Version 1.0
 */
public class WindowWordCount {
    public static void main(String[] args) throws Exception {

        String host="10.165.62.119";
        StreamExecutionEnvironment environment=StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<Tuple2<String,Integer>> dataStream= environment.socketTextStream(host,9000).flatMap(new Splitter()).keyBy(value->value.f0).timeWindow(Time.seconds(5)).sum(1);
        dataStream.print();
        environment.execute("Window WordCount");
    }

    public static class Splitter implements FlatMapFunction<String, Tuple2<String,Integer>>{

        @Override
        public void flatMap(String sentence, Collector<Tuple2<String, Integer>> collector) throws Exception {
            for(String word: sentence.split(" ")){
                collector.collect(new Tuple2<>(word,2));
            }
        }
    }
}
