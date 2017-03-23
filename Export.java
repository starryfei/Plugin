package com.starryfei;

import java.io.IOException;
import java.util.Scanner;

public class Export {

	private static String svn_url = null;
	private static String webapp_name = null;
	private static String basedir = null;
	private static String get_version = null;
	private static String before_url = null;
	private static String log_cmd =null;
	private static String log_path=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Export_log el = new Export_log();
		print();
		el.createHtml(log_path);
		String info =el.readLog(log_cmd);
		el.JspToHtmlFile(log_path, info);
		runBat();
	}

	private static void runBat() {
		// TODO Auto-generated method stub
		String cmd = "svn export -r " + get_version + " " + svn_url + " "
				+ basedir + "\\" + webapp_name;
		String build = "call ant -Dsvn.url=" + before_url + " -Dwebapp.name="
				+ webapp_name + " -Dsvn.version=" + get_version + " -Dbasedir="
				+ basedir;
		String cmd1 = "cd /d " + basedir+"\\"+webapp_name;
		String back = "cd..";
		String delete = "rd/s/q " + basedir+"\\"+webapp_name;
		String delete1 = "del " + basedir + "\\log.html";
		System.out.println(build);
		System.out.println(cmd);
		try {
			Runtime.getRuntime().exec(
					"cmd /c " + cmd + "&&" + cmd1 + "&&" + build + "&&" + back
							+ "&&" + delete+"&&"+delete1);
			System.out.println("-----------------success-----------------");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void print() {
		System.out.println("请输入SVN地址：");
		Scanner scUrl = new Scanner(System.in);
		svn_url = scUrl.next();

		System.out.println("请输入SVN版本号：");
		Scanner scVersion = new Scanner(System.in);
		get_version = scVersion.next();

		int index = svn_url.lastIndexOf("/");
		before_url = svn_url.substring(0, index);
		webapp_name = svn_url.substring(index + 1);
		basedir = System.getProperty("user.dir");
		System.out.println("user_dir:" + basedir);
		log_cmd="svn log -r"+get_version+" "+svn_url;
		log_path=basedir+"\\log.html";
		
	}

}
