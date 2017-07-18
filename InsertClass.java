package com.insert;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

/**
 * 
 * @author yafei.qin
 * @Email yafei.qin@hirain.com 向class文件中添加方法，采用javassist.jar
 *        项目中需要导入要添加方法中的jar文件，例如这个例子， 需要添加的方法中引用了poi的类，所以项目中要添加poi对应的jar文件
 */
public class InsertClassMethod {

	public static void main(String[] args) throws CannotCompileException,
			IOException {
		try {
			ClassPool pool = ClassPool.getDefault();
			pool.importPackage("org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill");
			pool.importPackage("org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor");

			// 获取class文件的位置
			pool.insertClassPath("d:/cla/poi-ooxml-3.11-20141221-custom.jar");
			CtClass cc = pool
					.get("org.apache.poi11.xssf.usermodel.XSSFPatternFormatting");

			CtMethod m = CtNewMethod
					.make("public void setFillBackgroundColor(java.awt.Color clr) {"
							+ "CTPatternFill ptrn = _fill.isSetPatternFill() ? _fill.getPatternFill() : _fill.addNewPatternFill();"
							+ "CTColor bgColor = CTColor.Factory.newInstance();"
							+ "bgColor.setRgb(new byte[]{(byte)clr.getRed(), (byte)clr.getGreen(), (byte)clr.getBlue()});"
							+ "ptrn.setBgColor(bgColor); }", cc);

			cc.addMethod(m);
			// 输出修改后的class文件的位置
			cc.writeFile("d:/cla");
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

}
