package com.starryfei;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

//导出日志信息并且存放到HTML文件中,并且一起和项目打包（即生成war文件）
public class Export_log {
	private static String editer = "log信息";
	private static String log_info = null;
	static StringBuffer sb1 = null;

//	public static void main(String[] args) {
//		String url = "d:\\a\\log.html";// 模板文件地址
//		String savepath = "d:\\a\\log.html";// 生成文件地址
//		createHtml();
//		String info = readLog();
//		JspToHtmlFile(url, savepath, info);
//	}

	// 向静态页面输入log信息
	public  boolean JspToHtmlFile( String HtmlFile,
			String log_info) {
		String str = "";
		try {
			String tempStr = "";
			FileInputStream is = new FileInputStream(HtmlFile);// 读取模块文件
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while ((tempStr = br.readLine()) != null)
				str = str + tempStr;
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		try {
			str = str.replaceAll(editer, log_info);// 替换掉模块中相应的地方
			File f = new File(HtmlFile);
			BufferedWriter o = new BufferedWriter(new FileWriter(f));
			o.write(str);
			o.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 生成静态页面
	public  void createHtml(String path) {
		StringBuilder sb = new StringBuilder();
		try {
			PrintStream printStream = new PrintStream(new FileOutputStream(
					path));
			sb.append("<html lang=\"zh-CN\">");
			sb.append("<head>");
			sb.append("<title>日志信息</title>");
			sb.append("<body>");
			sb.append("<center>" + editer + "</center>");
			sb.append("</body>");
			sb.append("</html>");
			printStream.println(sb.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 读出log信息
	public  String readLog(String log_cmd) {
		sb1 = new StringBuffer();
//		String log_cmd = "svn log -r 15 https://yafeiqinl.hirain.com/svn/starryfei/starry";
		try {
			Process process = Runtime.getRuntime().exec(log_cmd);
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));

			while ((log_info = bufferedReader.readLine()) != null) {
				System.out.println(log_info);
				sb1.append(log_info);
				sb1.append("<br>");

			}
			System.out.println(sb1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb1.toString();
	}

}
