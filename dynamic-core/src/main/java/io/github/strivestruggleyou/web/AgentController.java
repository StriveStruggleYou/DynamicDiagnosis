//package io.github.strivestruggleyou.web;
//
//import io.github.strivestruggleyou.global.GlobalCollection;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.lang.instrument.ClassFileTransformer;
//import java.lang.instrument.IllegalClassFormatException;
//import java.lang.instrument.Instrumentation;
//import java.lang.instrument.UnmodifiableClassException;
//import java.security.ProtectionDomain;
//import javassist.ClassPool;
//import javassist.CtClass;
//import javassist.CtMethod;
//import org.objectweb.asm.ClassReader;
//import org.objectweb.asm.ClassVisitor;
//import org.objectweb.asm.ClassWriter;
//import org.objectweb.asm.MethodVisitor;
//import org.objectweb.asm.Opcodes;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//public class AgentController {
//
//    @Autowired
//    GlobalCollection globalCollection;
//
//    @RequestMapping("index")
//    @ResponseBody
//    public Object index() {
//        return "index";
//    }
//
//
//    @RequestMapping("lineJavaCode")
//    @ResponseBody
//    public Object lineJavaCode(String name) throws Exception {
//        Instrumentation instrumentation = globalCollection.instrumentation;
//        globalCollection.jcl.add(new FileInputStream(
//                "/Users/manager/yunpian-blackcat/yunpian-blackcat.jar"));
//        Class cls = globalCollection.jcl
//                .loadClass("com.qipeng.blackcat.web.TrackRecognitionController");
//        instrumentation.retransformClasses(cls);
//        instrumentation.addTransformer(new ClassFileTransformer() {
//            public byte[] transform(ClassLoader loader, String className,
//                    Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
//                    byte[] classfileBuffer) throws IllegalClassFormatException {
//                return transformClass(loader, className, classBeingRedefined, protectionDomain,
//                        classfileBuffer);
//            }
//        }, true);
//        return "test";
//    }
//
//    private static byte[] transformClass(ClassLoader loader, String className,
//            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
//            byte[] classfileBuffer) {
//        System.out.println(
//                "Transform loader " + loader + " className: " + className + " classBeingRedefined "
//                        + classBeingRedefined);
//        if (className.equals("com/qipeng/blackcat/web/TrackRecognitionController")) {
//            System.out.println("come in");
//            try {
//                CtClass clas = ClassPool.getDefault()
//                        .get("com.qipeng.blackcat.web.TrackRecognitionController");
//
//                clas.defrost();
//                String mname = "testShow";
//                CtMethod mold = clas.getDeclaredMethod(mname);//获取方法
//                //方法的副本
//                System.out.println(mold.insertAt(24, "  logger.info(\"test\");\n"));
//                clas.writeFile("/Users/manager/DynamicDiagnosis/dynamic-core/target");
//                byte bytes[] = clas.toBytecode();
//                return clas.toBytecode();
//            } catch (Exception e) {
//                System.out.println("come in" + e);
//            }
//        }
//
//        ClassReader classReader = new ClassReader(classfileBuffer);
//        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
//        classReader.accept(new MyClassClassVisitor(classWriter), 0);
//
//        byte[] bytes = classWriter.toByteArray();
//        return bytes;
//    }
//
//    static class MyClassClassVisitor extends ClassVisitor {
//
//        public MyClassClassVisitor(ClassVisitor cv) {
//            super(Opcodes.ASM4, cv);
//        }
//
//        @Override
//        public MethodVisitor visitMethod(int access, String name, String desc, String signature,
//                String[]
//                        exceptions) {
//            System.out.println("visit " + name + " desc " + desc);
//            if ("say".equals(name)) {
//                // do call
//                MethodVisitor methodVisitor = cv
//                        .visitMethod(access, name, desc, signature, exceptions);
//                methodVisitor.visitCode();
//                methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",
//                        "Ljava/io/PrintStream;");
//                methodVisitor.visitLdcInsn("CALL " + name);
//                methodVisitor.visitEnd();
//                return methodVisitor;
//            }
//
//            return super.visitMethod(access, name, desc, signature, exceptions);
//
//        }
//    }
//
//
//}
