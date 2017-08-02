# 工作工具
## 1、 根据制定版本号 从SVN里面获取对应版本的源代码和日志，并且采用ant打包生成war文件.需要的信息：svn的URL和项目的版本号
## 2、使用POI读取Excel表格,使用POI相关jar文件（readExcel.java）
## 3、向class文件中添加一个新的方法，需要导入javassist.jar（InsertClass.Java）
## 4、dom4j使用相关
1.读取XML文件,获得document对象              
                  SAXReader reader = new SAXReader();                
                  Document  document = reader.read(new File("csdn.xml"));  
2.解析XML形式的文本,得到document对象.  
                  String text = "<csdn></csdn>";              
                  Document document = DocumentHelper.parseText(text);  
3.主动创建document对象.  
                 Document document = DocumentHelper.createDocument(); //创建根节点  
                 Element root = document.addElement("csdn");  
4.获取根节点
	                Element root = document.getRootElement();
5.获取根节点的属性值
		              Attribute attr = root.attribute("filePath");
	                attr.getText(）//  取得属性的文字
6.获取根节点的子节点
	                 Element child = root.element("名字");
7.取得某节点下所有名为“test”的子节点，并进行遍历.
	                List nodes = rootElm.elements("test");
8.在某节点下添加子节点  
	                Element elm = newElm.addElement("test1");  
9.设置节点文字.     elm.setText("111"); 

10.将文档写入XML文件
    1).文档中全为英文,不设置编码,直接写入的形式.  
       XMLWriter writer = new XMLWriter(new  FileWriter("ot.xml"));  
       writer.write(document); 
       writer.close();  
    2).文档中含有中文,设置编码格式写入的形式. 
       OutputFormat format = OutputFormat.createPrettyPrint();// 创建文件输出的时候，自动缩进的格式                    
       format.setEncoding("UTF-8");//设置编码  
       XMLWriter writer = new XMLWriter(newFileWriter("output.xml"),format);  
       writer.write(document);  
       writer.close();  

11.字符串与XML的转换
	 1).将字符串转化为XML  
      String text = "<csdn> <java>test</java></csdn>";  
     Document document = DocumentHelper.parseText(text);  
   2).将文档或节点的XML转化为字符串.  
       SAXReader reader = new SAXReader();  
       Document   document = reader.read(new File("dom4j.xml"));              
       Element root=document.getRootElement();      
       String docXmlText=document.asXML();  
       String rootXmlText=root.asXML();  
       Element memberElm=root.element("test");  
       String memberXmlText=memberElm.asXML();  
