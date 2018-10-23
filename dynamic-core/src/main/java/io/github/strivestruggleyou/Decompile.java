package io.github.strivestruggleyou;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.benf.cfr.reader.Main;
import org.xeustechnologies.jcl.JarClassLoader;
import org.xeustechnologies.jcl.JarResources;

public class Decompile {

    private static final String OUTPUTOPTION = "--outputdir";
    private static final String COMMENTS = "--comments";
    private static final String DecompilePath =
            new File("/Users/manager/DynamicDiagnosis").getPath() + File.separator + "decompiles";

    public static void main(String args[]) throws FileNotFoundException {

//        JarClassLoader jcl = new JarClassLoader();
//
//        jcl.add(new FileInputStream("/Users/manager/yunpian-blackcat/yunpian-blackcat.jar"));
//
//        Map map = jcl.getLoadedClasses();

        JarResources jarResources = new JarResources();

        jarResources.loadJar("/Users/manager/yunpian-blackcat/yunpian-blackcat.jar");

        Map<String, byte[]> map = jarResources.getResources();

        byte[] fileByte=map.get("BOOT-INF/classes/com/qipeng/blackcat/web/TrackRecognitionController.class");



        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream() ;
        try {
            byteArrayOutputStream.write( fileByte );

            FileOutputStream fileOutputStream = new FileOutputStream( new File( "/Users/manager/DynamicDiagnosis/decompile/test.class" ) ) ;

            byteArrayOutputStream.writeTo( fileOutputStream ) ;

            fileOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        List < String > options = new ArrayList<String>();
        options.add("/Users/manager/DynamicDiagnosis/decompile/test.class");
//        options.add(clazz.getName());

        options.add(OUTPUTOPTION);
        options.add(DecompilePath);
        options.add(COMMENTS);
        options.add("false");
        String arg[] = new String[options.size()];
        options.toArray(arg);
        Main.main(arg);
    }
}
