package org.myorg.quickstart;

import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.runtime.executiongraph.restart.RestartStrategy;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;

import java.util.Properties;

/**
 * @menu
 * @company 金邦达互联网事业部
 * @Description： 描述
 * @Author liuchenguang
 * @Date: 2022/3/7 16:09
 * @Version 1.0
 */
public class KafkaExample {
    public static void main(String[] args) {
        final ParameterTool parameterTool=ParameterTool.fromArgs(args);
        StreamExecutionEnvironment env=StreamExecutionEnvironment.getExecutionEnvironment();
        env.getConfig().setRestartStrategy(RestartStrategies.fixedDelayRestart(4,10000));
        env.enableCheckpointing(5000);
        env.getConfig().setGlobalJobParameters(parameterTool);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        String topic = "t1";
        Properties prop = new Properties();
        prop.setProperty("bootstrap.servers","hadoop110:9092");
        prop.setProperty("group.id","con1");
        DataStream input=env.addSource(new FlinkKafkaConsumer010<>(topic, new SimpleStringSchema(), prop));
//                .keyBy("word").map(new RollingAdditionMapper()).shuffle();
    }
}
