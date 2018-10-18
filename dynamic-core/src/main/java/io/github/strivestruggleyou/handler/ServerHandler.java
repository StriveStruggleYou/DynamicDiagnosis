package io.github.strivestruggleyou.handler;

import com.alipay.remoting.util.StringUtils;
import io.github.strivestruggleyou.global.GlobalCollection;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.io.FileInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.UUID;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ServerHandler extends SimpleChannelInboundHandler<String> {


    //读取客户端发送的数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("client response :" + msg);
        if (StringUtils.isNotBlank(msg)) {
            String str[] = msg.split(" ");
            String cmd = str[0];
            if (cmd.equals("line")) {
                final String classGoalName = str[1];

                final String method = str[2];

                final int line = Integer.valueOf(str[3]);

                final String javaCode = str[4];

                final String classReplaceName = classGoalName.replace(".", "/");
                //
                GlobalCollection.jcl.add(new FileInputStream("/Users/manager/yunpian-blackcat/yunpian-blackcat.jar"));
                Class cls = GlobalCollection.jcl.loadClass(classGoalName);
                GlobalCollection.instrumentation.retransformClasses(cls);
                GlobalCollection.instrumentation.addTransformer(new ClassFileTransformer() {
                    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
                        return transformClass(loader, className, classBeingRedefined,
                                protectionDomain,
                                classfileBuffer, classGoalName, classReplaceName, method, line,
                                javaCode);
                    }
                }, true);
            }
        }
        ctx.channel().writeAndFlush("im server: nihao");
    }

    //新客户端接入
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
    }

    //客户端断开
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
    }

    //异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //关闭通道
        ctx.channel().close();
        //打印异常
        cause.printStackTrace();
    }


    private static byte[] transformClass(ClassLoader loader, String className,
            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
            byte[] classfileBuffer, String classGoalName, String classReplaceName, String method,
            int line, String javaCode) {
        if (className.equals(classReplaceName)) {
            System.out.println("come in");
            try {
                CtClass clas = ClassPool.getDefault().get(classGoalName);
                clas.defrost();
                CtMethod mold = clas.getDeclaredMethod(method);//获取方法
                //方法的副本
                System.out.println(mold.insertAt(line, javaCode));
                clas.writeFile("/Users/manager/DynamicDiagnosis/dynamic-core/target");
                byte bytes[] = clas.toBytecode();
                return clas.toBytecode();
            } catch (Exception e) {
                System.out.println("come in" + e);
            }
        }

        ClassReader classReader = new ClassReader(classfileBuffer);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        classReader.accept(new MyClassClassVisitor(classWriter), 0);

        byte[] bytes = classWriter.toByteArray();
        return bytes;
    }

    static class MyClassClassVisitor extends ClassVisitor {

        public MyClassClassVisitor(ClassVisitor cv) {
            super(Opcodes.ASM4, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature,
                String[]
                        exceptions) {
            System.out.println("visit " + name + " desc " + desc);
            if ("say".equals(name)) {
                // do call
                MethodVisitor methodVisitor = cv
                        .visitMethod(access, name, desc, signature, exceptions);
                methodVisitor.visitCode();
                methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",
                        "Ljava/io/PrintStream;");
                methodVisitor.visitLdcInsn("CALL " + name);
                methodVisitor.visitEnd();
                return methodVisitor;
            }

            return super.visitMethod(access, name, desc, signature, exceptions);

        }
    }


}
