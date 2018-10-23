package io.github.strivestruggleyou.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.benf.cfr.reader.Main;
import org.xeustechnologies.jcl.JarResources;

public class DecompileUtil {

  private static final String OUTPUTOPTION = "--outputdir";
  private static final String COMMENTS = "--comments";
  private static final String DecompilePath =
      System.getProperty("user.dir") + File.separator + "decompiles";


  public static String decompile(String name) {

    JarResources jarResources = new JarResources();
    jarResources.loadJar("/Users/manager/yunpian-blackcat/yunpian-blackcat.jar");

    Map<String, byte[]> map = jarResources.getResources();

    String fileClassPath = "BOOT-INF/classes/" + name.replace(".", "/") + ".class";

    byte[] fileByte = map.get(fileClassPath);

    String goalClass = DecompilePath + "/" + name.replace(".", "/") + ".class";

    String goalJava = DecompilePath + "/" + name.replace(".", "/") + ".java";

    File file = new File(goalClass);

    try {
      File mkdir = new File(DecompilePath + "/" + name.replace(".", "/"));
      mkdir.mkdirs();
      if (!file.exists()) {
        file.createNewFile();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    try {
      byteArrayOutputStream.write(fileByte);

      FileOutputStream fileOutputStream = new FileOutputStream(file);

      byteArrayOutputStream.writeTo(fileOutputStream);

      fileOutputStream.flush();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        byteArrayOutputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    List<String> options = new ArrayList<String>();
    options.add(goalClass);
    //   options.add(clazz.getName());

    options.add(OUTPUTOPTION);
    options.add(DecompilePath);
    options.add(COMMENTS);
    options.add("false");
    String arg[] = new String[options.size()];
    options.toArray(arg);
    Main.main(arg);

    StringBuffer sb = new StringBuffer();
    BufferedReader bufferedInputStream = null;
    try {
      bufferedInputStream = new BufferedReader(
          new FileReader(new File(goalJava)));

      String line = bufferedInputStream.readLine();
      int i = 1;
      if (StringUtils.isNotBlank(line)) {
        sb.append(i).append(line).append("\r\n");
      }
      while (line != null) {
        if (StringUtils.isNotBlank(line)) {
          i++;
          sb.append(i).append(line).append("\r\n");
        }
        line = bufferedInputStream.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        bufferedInputStream.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return sb.toString();

  }


  public static void main(String args[]) {
    System.out.println(decompile("com.qipeng.blackcat.web.TrackRecognitionController"));
  }


}
