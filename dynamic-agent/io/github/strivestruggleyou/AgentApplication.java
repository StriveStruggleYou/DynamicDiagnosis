package io.github.strivestruggleyou;

import java.lang.instrument.Instrumentation;

public class AgentApplication {

    public static void premain(String agentOps, Instrumentation inst) {
        start(agentOps, inst);
    }


    public static void agentmain(String agentOps, Instrumentation inst) {
        start(agentOps, inst);
    }


    public static void start(String agentOps, Instrumentation inst) {

        System.out.println("start DynamicServerApplication ");
        //启动9527程序
        try {
            NettyServerApplication.dostart(inst);
        } catch (Exception e) {
            System.out.println(e);
        }
        //注册勾子函数
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                NettyServerApplication.doStop();
            }
        });
        System.out.println("start DynamicServerApplication ---");
    }

}
