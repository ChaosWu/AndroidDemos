package cn.android.demo.apis.java;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// TODO 继续学习
/**
 * 注解的观察对象:
 * 
 * // 注解的存在分为三个阶段，源程序---->class文件----->字节码（class文件里的不是字节码，虚拟机加载类后经过转换才产生字节码）
 * 
 * @author Elroy
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FruitName  {
	String date();

}
