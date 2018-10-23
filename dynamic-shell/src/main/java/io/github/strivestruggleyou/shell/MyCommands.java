package io.github.strivestruggleyou.shell;

import com.sun.tools.attach.VirtualMachine;
import io.github.strivestruggleyou.NettyClient;
import io.github.strivestruggleyou.global.GobalResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellComponent;

@ShellComponent
public class MyCommands {

  NettyClient nettyClient = new NettyClient();
  VirtualMachine vm;

  @ShellMethod("连接指定jvm")
  public String attach(int pid) {
    if (pid == 0) {
      return "please input jvm pid";
    }
    try {
      vm = VirtualMachine.attach(String.valueOf(pid));
      vm.loadAgent(System.getProperty("user.dir")
          + "/dynamic-agent/target/dynamic-agent-1.0-SNAPSHOT-jar-with-dependencies.jar");
      nettyClient.connect();
    } catch (Exception e) {
      System.out.println(e);
      return e.toString();
    }
    return "success";
  }

  @ShellMethod("断开jvm Attach")
  public String disAttach() {
    try {
      nettyClient.closeConnect();
      vm.detach();
    } catch (Exception e) {
      System.out.println("disAttach Exception:" + e);
    }
    return "disAttach success";
  }

  @ShellMethod("jad反编译指定class")
  public String jad(String className) {
    try {
      nettyClient.send("jad" + " " + className);
    } catch (Exception e) {
      e.printStackTrace();
    }
    while (StringUtils.isBlank(GobalResponse.gobalResponse)) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    try {
      String msg = GobalResponse.gobalResponse;
      return msg;
    } finally {
      GobalResponse.gobalResponse = "";
    }

  }


  @ShellMethod("还原class至原始状态")
  public String restore() {
    nettyClient.send("restore");
    return "nihao";
  }


  @ShellMethod("指定行数插入java 语句")
  public String line(String className, String method, int line, String javaCode) {
    nettyClient.send("line " + className + " " + method + " " + line + " " + javaCode);
    return "insert into javaCode success";
  }


}
