package org.myorg.quickstart;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @menu
 * @company 金邦达互联网事业部
 * @Description： 描述
 * @Author liuchenguang
 * @Date: 2022/3/4 17:41
 * @Version 1.0
 */
public class HelloWorld {

    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream dataStream = env.fromElements("Hello World");

        dataStream.print();

        try {
            env.execute("test");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
