package com.starryfei;

import java.io.IOException;
import java.util.Scanner;

public class Export {

	private static String svn_url = null;
	private static String webapp_name = null;
	private static String basedir = null;
	private static String get_version = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		print();
		runBat();
	}

	private static void runBat() {
		// TODO Auto-generated method stub
		String cmd = "svn export -r " + get_version + " " + svn_url + "/"
				+ webapp_name + " " + basedir;
		String build = "call ant -Dsvn.url=" + svn_url + " -Dwebapp.name="
				+ webapp_name + " -Dsvn.version=" + get_version + " -Dbasedir="
				+ basedir;
		String cmd1 = "cd /d " + basedir;
		String back = "cd.." ;
		String delete="rd/s/q " + basedir;
		System.out.println(build);
		try {
			 Runtime.getRuntime().exec(
					"cmd /c " + cmd + "&&" + cmd1 + "&&" + build + "&&"
							+ back + "&&"+delete);
			System.out.println("-----------------success-----------------");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void print() {
		System.out.println("请输入SVN地址：");
		Scanner scUrl = new Scanner(System.in);
		String url = scUrl.next();

		System.out.println("请输入项目名称：");
		Scanner scName = new Scanner(System.in);
		String name = scName.next();

		System.out.println("请输入SVN版本号：");
		Scanner scVersion = new Scanner(System.in);
		String version = scVersion.next();

		System.out.println("请输入存放路径(格式如:d:\\123):");
		Scanner scPath = new Scanner(System.in);
		String path = scPath.next();

		svn_url = url.toString();
		webapp_name = name.toString();
		get_version = version;
		basedir = path;

	}
}
