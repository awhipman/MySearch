package com.html;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 根据网址和页面的编码集  获取网页的源代码 临时文件版
 * @author wb
 *@param url 需要下载url地址
 *@param encoding 网页的编码集
 *@return String 网页的源代码
 */

public class HtmlJsoup {
 
	public static String getHtmlResourceByURL(String url,String encoding) {
		//声明一个存储网页源代码的容器
		StringBuffer buffer = new StringBuffer();
		
		URL  urlObj =null;
		URLConnection uc =null;
		InputStreamReader in = null;
		BufferedReader reader = null;
		try {
		//建立网络连接	
		 urlObj = new URL(url);
		//打开网络连接
		 uc = urlObj.openConnection();
		 //加UA
		 uc.addRequestProperty("User-Agent", "Mozilla/5.0(Windows NT 10.0;WOW64)AppleWebKit/537.36(KHTML,like Gerko)Chrome/53.0.2785.104"+
		 "Safari/537.36 Core/1.53.3427.400 QQBrower/9.6.12513.400");
		//建立网络的输入流
		 in = new InputStreamReader(uc.getInputStream(), encoding);
		//缓冲写入的文件流
		 reader = new BufferedReader(in);
		//临时变量
		String tempLine = null;
		//循环读取文件流
		while((tempLine = reader.readLine())!=null) {
			buffer.append(tempLine+"\n");    //循环不断地追加数据

		}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("connection timeOut！！！");
		}finally {
			if(in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buffer.toString();
	}
	
	/**
	 * 根据一个图片的URL地址，通过这个URL批量下载图片到服务器的磁盘
	 * @author wb
	 * @param imgURL  要下载的图片服务器地址
	 * @param filePath   下载完成后保存到服务器的地址
	 * 
	 */ 
	public static void downImages(String imgURL,String filePath) {
		String fileName = imgURL.substring(imgURL.lastIndexOf("/"));
		try {
		//创建文件目录
		File files =new File(filePath);
		//判断是否存在文件夹
		if(!files.exists()) {
			files.mkdirs();
		}
		//获取图片文件下载地址
		URL url = new URL(imgURL);
		
		//连接网络图片地址
		HttpURLConnection uc = (HttpURLConnection)url.openConnection();
		//获取连接的输出流
		InputStream  is = uc.getInputStream();
		
		// 创建文件
		File file = new File(filePath+fileName);
		
		//创建输出流，写入文件
		FileOutputStream out = new FileOutputStream(file);
		int i = 0;
		while((i=is.read())!=-1) {
			out.write(i);
		}
		is.close();
		out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	// 1.加载驱动
		static {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		// 2.获得数据库连接对象Connection
		public static Connection getConnection() {
			Connection conn = null;
			try {
				conn = DriverManager.getConnection("jdbc:mysql://rm-bp1s2659n28d2t858xo.mysql.rds.aliyuncs.com:3306/aircondition", "root", "Qaz123456!");
				if(!conn.isClosed()) {
					System.out.println("Succeeded connecting to the Database!");
				}
				else {
					System.out.println("Fail");
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
			return conn;
		}
		
		
	public static void downtables(String filePath) {
		
	}
	
	/**
	 * 因为每次要爬取一个网站中的多个网页，所以对网页的url用hashmap保存
	 */
	static HashMap<String, String> map = new HashMap<String, String>() {
	    {
	    	//先放几个热门城市
	        put("beijing", "北京");  
	        put("shanghai", "上海");
	        put("guangzhou","广东");
	        put("shenzhen","广东");
	        put("hangzhou","浙江");
	        put("tianjin","天津");
	        put("chengdu","四川");
	        put("nanjing","江苏");
	        put("xian","陕西");
	        put("wuhan","湖北");
	        //接下来是拼音 A 开头 (10个)
	        put("abazhou","四川");
	        put("akesudiqu","新疆");
	        put("alashanmeng","内蒙古");
	        put("aletaidiqu","新疆");
	        put("alidiqu","西藏");
	        put("ankang","陕西");
	        put("anqing","安徽");
	        put("anshan","辽宁");
	        put("anshun","贵州");
	        put("anyang","河南");
	        //B (18个)
	        put("baicheng","吉林");
	        put("baise","广西");
	        put("baishan","吉林");
	        put("baiyin","甘肃");
	        put("baoding","河北");
	        put("baoji","陕西");
	        put("baoshan","云南");
	        put("baotou","内蒙古");
	        put("bayannaoer","内蒙古");
	        put("bazhong","四川");
	        put("beihai","广西");
	        put("bengbu","安徽");
	        put("benxi","辽宁");
	        put("bijie","贵州");
	        put("binzhou","山东");
	        put("boertala","新疆");//博州
	        put("bozhou","安徽");
	      //put("beijing", "北京"); 北京
	        //C (21个)
	        put("cangzhou","河北");
	        put("changchun","吉林");
	        put("changde","湖南");
	        put("changdou","西藏");
	      //put("changdudiqu","西藏");
	        put("changjizhou","新疆");
	        put("changsha","湖南");
	        put("changshu","江苏");
	        put("changzhi","山西");
	        put("changzhou","江苏");
	        put("chaoyang","辽宁");
	        put("chaozhou","广东");
	        put("chengde","河北");
	        put("chenzhou","湖南");
	        put("chifeng","内蒙古");
	        put("chizhou","安徽");
	        put("chongqing","重庆");
	        put("chongzuo","广西");
	        put("chuxiongzhou","云南");
	        put("chuzhou","安徽");
	      //put("chengdu","四川"); 成都
	        //D (14个)
	        put("dalian","辽宁");
	        put("dalizhou","云南");
	        put("dandong","辽宁");
	        put("daqing","黑龙江");
	        put("datong","山西");
	        put("daxinganlingdiqu","黑龙江");
	        put("dazhou","四川");
	        put("dehongzhou","云南");
	        put("deyang","四川");
	        put("dezhou","山东");
	        put("dingxi","甘肃");
	        put("diqingzhou","云南");
	        put("dongguan","广东");
	        put("dongying","山东");
	        //E (3个)
	        put("eerduosi","内蒙古");
	        put("enshizhou","湖北");
	        put("ezhou","湖北");
	        //F (8个)
	        put("fangchenggang","广西");
	        put("foshan","广东");
	        put("fushun","辽宁");
	        put("fuxin","辽宁");
	        put("fuyang","安徽");
	        put("fuyangshi","浙江");
	        put("fuzhou","福建");
	        put("fuzhoushi","江西");
	        //G (12个)
	        put("gannanzhou","甘肃");
	        put("ganzhou","江西");
	      //put("ganzicangzuzizhizhou","四川");
	        put("ganzizhou","四川");
	        put("guangan","四川");
	        put("guangyuan","四川");
	        put("guigang","广西");
	        put("guilin","广西");
	        put("guiyang","贵州");
	        put("guoluozhou","青海");
	        put("guyuan","宁夏");
	      //put("guangzhou","广东");广州
	       //H (36个)
	        put("haerbin","黑龙江");
	        put("haibeizhou","青海");
	        put("haidongdiqu","青海");
	        put("haikou","海南");
	        put("haimen","江苏");
	        put("hainanzhou","青海");
	        put("haixizhou","青海");
	        put("hamidiqu","新疆");
	        put("handan","河北");
	        put("hanzhong","陕西");
	        put("hebi","河南");
	        put("hechi","广西");
	        put("hefei","安徽");
	        put("hegang","黑龙江");
	        put("heihe","黑龙江");
	        put("hengshui","河北");
	        put("hengyang","湖南");
	        put("hetiandiqu","新疆");
	        put("heyuan","广东");
	        put("heze","山东");
	        put("hezhou","广西");
	        put("honghezhou","云南");
	        put("huaian","江苏");
	        put("huaibei","安徽");
	        put("huaihua","湖南");
	        put("huainan","安徽");
	        put("huanggang","湖北");
	        put("huangnanzhou","甘肃");
	        put("huangshan","安徽");
	        put("huangshi","湖北");
	        put("huhehaote","内蒙古");
	        put("huizhou","广东");
	        put("huludao","辽宁");
	        put("hulunbeier","内蒙古");
	        put("huzhou","浙江");
	      //put("hangzhou","浙江"); 杭州
	        //J (27个)
	        put("jiamusi","黑龙江");
	        put("jian","江西");
	        put("jiangmen","广东");
	        put("jiangyin","江苏");
	        put("jiaonan","山东");
	        put("jiaozhou","山东");
	        put("jiaozuo","河南");
	        put("jiaxing","浙江");
	        put("jiayuguan","甘肃");
	        put("jieyang","广东");
	        put("jilin","吉林");
	        put("jimo","山东");
	        put("jinan","山东");
	        put("jinchang","甘肃");
	        put("jincheng","山西");
	        put("jingdezhen","江西");
	        put("jingmen","湖北");
	        put("jingzhou","湖北");
	        put("jinhua","浙江");
	        put("jining","山东");
	        put("jintan","江苏");
	        put("jinzhong","山西");
	        put("jinzhou","辽宁");
	        put("jiujiang","江西");
	        put("jiuquan","甘肃");
	        put("jixi","黑龙江");
	        put("jurong","江苏");
	        //K (7个)
	        put("kaifeng","河南");
	        put("kashediqu","新疆");
	        put("kelamayi","新疆");
	        put("kezhou","新疆");
	        put("kuerle","新疆");
	        put("kunming","云南");
	        put("kunshan","江苏");
	        //L (33个)
	        put("laibin","广西");
	        put("laiwu","山东");
	        put("laixi","山东");
	        put("laizhou","山东");
	        put("langfang","河北");
	        put("lanzhou","甘肃");
	        put("lasa","西藏");
	        put("leshan","四川");
	        put("liangshanzhou","四川");
	        put("lianyungang","江苏");
	        put("liaocheng","山东");
	        put("liaoyang","辽宁");
	        put("liaoyuan","吉林");
	        put("lijiang","云南");
	        put("linan","浙江");
	        put("lincang","云南");
	        put("linfen","山西");
	        put("linxiazhou","甘肃");
	        put("linyi","山东");
	        put("linzhi","西藏");
	      //put("linzhidiqu","西藏");
	        put("lishui","浙江");
	        put("liupanshui","贵州");
	        put("liuzhou","广西");
	        put("liyang","江苏");
	        put("longnan","甘肃");
	        put("longyan","福建");
	        put("loudi","湖南");
	        put("luan","安徽");
	        put("luohe","河南");
	        put("luoyang","河南");
	        put("luzhou","四川");
	        put("lvliang","山西");
	        //M (6个)
	        put("maanshan","安徽");
	        put("maoming","广东");
	        put("meishan","四川");
	        put("meizhou","广东");
	        put("mianyang","四川");
	        put("mudanjiang","黑龙江");
	        //N (12个)
	        put("nanchang","江西");
	        put("nanchong","四川");
	        put("nanning","广西");
	        put("nanping","福建");
	        put("nantong","江苏");
	        put("nanyang","河南");
	        put("naqudiqu","西藏");
	        put("neijiang","四川");
	        put("ningbo","浙江");
	        put("ningde","福建");
	        put("nujiangzhou","云南");
	      //put("nanjing","江苏"); 南京
	        //P (10个)
	        put("panjin","辽宁");
	        put("panzhihua","四川");
	        put("penglai","山东");
	        put("pingdingshan","河南");
	        put("pingdu","山东");
	        put("pingliang","甘肃");
	        put("pingxiang","江西");
	        put("puer","云南");
	        put("putian","福建");
	        put("puyang","河南");
	        //Q (13个)
	        put("qiandongnanzhou","贵州");
	        put("qiannanzhou","贵州");
	        put("qianxinanzhou","贵州");
	        put("qingdao","山东");
	        put("qingyang","甘肃");
	        put("qingyuan","广东");
	        put("qinhuangdao","河北");
	        put("qinzhou","广西");
	        put("qiqihaer","黑龙江");
	        put("qitaihe","黑龙江");
	        put("quanzhou","福建");
	        put("qujing","云南");
	        put("quzhou","浙江");
	        //R (5个)
	        put("rikaze","西藏");
	      //put("rikazediqu","西藏");
	        put("rizhao","山东");
	        put("rongcheng","山东");
	        put("rushan","山东");
	        //S (32个)
	        put("sanmenxia","河南");
	        put("sanming","福建");
	      //put("sansha","海南");
	        put("sanya","海南");
	        put("shangluo","陕西");
	        put("shangqiu","河南");
	        put("shangrao","江西");
	        put("shannan","西藏");
	      //put("shannandiqu","西藏");
	        put("shantou","广东");
	        put("shanwei","广东");
	        put("shaoguan","广东");
	        put("shaoxing","浙江");
	        put("shaoyang","湖南");
	        put("shenyang","辽宁");
	        put("shihezi","新疆");
	        put("shijiazhuang","河北");
	        put("shiyan","湖北");
	        put("shizuishan","宁夏");
	        put("shouguang","山东");
	        put("shuangyashan","黑龙江");
	        put("shuozhou","山西");
	        put("siping","吉林");
	        put("songyuan","吉林");
	        put("suihua","黑龙江");
	        put("suining","四川");
	        put("suizhou","湖北");
	        put("suqian","江苏");
	        put("suzhou","江苏");
	        put("suzhoushi","安徽");
	      //put("shanghai", "上海"); 上海
	      //put("shenzhen","广东"); 深圳
	        //T (16个)
	        put("tachengdiqu","新疆");
	        put("taian","山东");
	        put("taicang","江苏");
	        put("taiyuan","山西");
	        put("taizhou","浙江");
	        put("taizhoushi","江苏");
	        put("tangshan","河北");
	      //put("tianjin","天津"); 天津
	        put("tianshui","甘肃");
	        put("tieling","辽宁");
	        put("tongchuan","陕西");
	        put("tonghua","吉林");
	        put("tongliao","内蒙古");
	        put("tongling","安徽");
	        put("tongrendiqu","贵州");
	        put("tulufandiqu","新疆");
	        //W (18个)
	        put("wafangdian","辽宁");
	        put("weifang","山东");
	        put("weihai","山东");
	        put("weinan","陕西");
	        put("wendeng","山东");
	        put("wenshanzhou","云南");
	        put("wenzhou","浙江");
	        put("wuhai","内蒙古");
	      //put("wuhan","湖北"); 武汉
	        put("wuhu","安徽");
	        put("wujiang","江苏");
	        put("wujiaqu","新疆");
	        put("wulanchabu","内蒙古");
	        put("wulumuqi","新疆");
	        put("wuwei","甘肃");
	        put("wuxi","江苏");
	        put("wuzhong","宁夏");
	        put("wuzhou","广西");
	        //X (20个)
	        put("xiamen","福建");
	      //put("xian","陕西");  西安
	        put("xiangtan","湖南");
	        put("xiangxizhou","湖南");
	        put("xiangyang","湖北");
	        put("xianning","湖北");
	        put("xianyang","陕西");
	        put("xiaogan","湖北");
	        put("xilinguolemeng","内蒙古");
	        put("xinganmeng","内蒙古");
	        put("xingtai","河北");
	        put("xining","青海");
	        put("xinxiang","河南");
	        put("xinyang","河南");
	        put("xinyu","江西");
	        put("xinzhou","山西");
	        put("xishuangbannazhou","云南");
	        put("xuancheng","安徽");
	        put("xuchang","河南");
	        put("xuzhou","江苏");
	        //Y (28个)
	        put("yaan","四川");
	        put("yanan","陕西");
	        put("yanbianzhou","吉林");
	        put("yancheng","江苏");
	        put("yangjiang","广东");
	        put("yangquan","山西");
	        put("yangzhou","江苏");
	        put("yantai","山东");
	        put("yibin","四川");
	        put("yichang","湖北");
	        put("yichun","江西");
	        put("yichunshi","黑龙江");
	      //put("yilihasake","新疆");
	        put("yilihasakezhou","新疆");
	        put("yinchuan","宁夏");
	        put("yingkou","辽宁");
	        put("yingtan","江西");
	        put("yiwu","浙江");
	        put("yixing","江苏");
	        put("yiyang","湖南");
	        put("yongzhou","湖南");
	        put("yueyang","湖南");
	        put("yulin","广西");
	        put("yulinshi","陕西");
	        put("yuncheng","山西");
	        put("yunfu","广东");
	        put("yushuzhou","青海");
	        put("yuxi","云南");
	        //Z (25个)
	        put("zaozhuang","山东");
	        put("zhangjiagang","江苏");
	        put("zhangjiajie","湖南");
	        put("zhangjiakou","河北");
	        put("zhangqiu","山东");
	        put("zhangye","甘肃");
	        put("zhangzhou","福建");
	        put("zhanjiang","广东");
	        put("zhaoqing","广东");
	        put("zhaotong","云南");
	        put("zhaoyuan","山东");
	        put("zhengzhou","河南");
	        put("zhenjiang","江苏");
	        put("zhongshan","广东");
	        put("zhongwei","宁夏");
	        put("zhoukou","河南");
	        put("zhoushan","浙江");
	        put("zhuhai","广东");
	        put("zhuji","浙江");
	        put("zhumadian","河南");
	        put("zhuzhou","湖南");
	        put("zibo","山东");
	        put("zigong","四川");
	        put("ziyang","四川");
	        put("zunyi","贵州");
	           
	    }
	};
	
	//Java的入口函数
	public static void main(String[] args) throws SQLException {
		String index = "http://www.pm25.in/"; //要获取的网站主页网址
		String encoding = "utf-8";     //网页的编码集 如 utf-8
		String sql1="insert into showdata(province,area,aqi,pm2_5,pm10,co,no2,o3,o3_8h,so2,time_point) values(?,?,?,?,?,?,?,?,?,?,?)";
		String sql2="insert into crawler(province,area,position_name,aqi,quality,primary_pollutant,pm2_5,pm10,co,no2,o3,o3_8h,so2,time_point) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn=getConnection();
		
		//String filePath = "F:\\resource\\images\\";s
		//用迭代器遍历HashMap 不断取得新的网页url
		int j=0;//计数
		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();  //每个分页的url
				String url=index+key;
				String province = (String) entry.getValue();
				
				
				//根据网址和页面的编码集获取网页的源代码
				String htmlresource=getHtmlResourceByURL(url, encoding);	
				//System.out.println(htmlresource);
				//解析源代码 jsoup
				Document document = Jsoup.parse(htmlresource);
				String city = document.getElementsByClass("city_name").text();
				System.out.println(city);
				String timecontent = document.getElementsByClass("live_data_time").text();
				//进行time过滤
				String[] times=timecontent.trim().split("\\：");
				String time=times[1];
				System.out.println(time);
				//获取每个城市的简要监测数据
				PreparedStatement pstmt=conn.prepareStatement(sql1);
				Elements values = document.getElementsByClass("value");
				pstmt.setString(1, province);
				pstmt.setString(2, city);
				pstmt.setString(3, values.get(0).text());
				pstmt.setString(4, values.get(1).text());
				pstmt.setString(5, values.get(2).text());
				pstmt.setString(6, values.get(3).text());
				pstmt.setString(7, values.get(4).text());
				pstmt.setString(8, values.get(5).text());
				pstmt.setString(9, values.get(6).text());
				pstmt.setString(10, values.get(7).text());
				pstmt.setString(11, time);
				pstmt.executeUpdate();
				if(pstmt !=null && pstmt.isClosed()) {
					pstmt.close();
				}
				
				//获取各个监测点详细数据并过滤
				Elements elements=document.getElementsByTag("tr");
				boolean flag = true;  //过滤掉第一行的标签
				PreparedStatement pstmt2=conn.prepareStatement(sql2);
				for(Element element:elements) {
					String td = element.child(0).text()+"\t"+element.child(1).text()+"\t"+element.child(2).text()+"\t"+element.child(3).text()+"\t"+
							element.child(4).text()+"\t"+element.child(5).text()+"\t"+element.child(6).text()+"\t"+element.child(7).text()+"\t"+element.child(8).text()+"\t"+
							element.child(9).text()+"\t"+element.child(10).text();
					//System.out.println(td);
					if(flag) {
						flag = false;
					}else {
					pstmt2.setString(1, province);
					pstmt2.setString(2, city);
					pstmt2.setString(3, element.child(0).text());
					pstmt2.setString(4, element.child(1).text());
					pstmt2.setString(5, element.child(2).text());
					pstmt2.setString(6, element.child(3).text());
					pstmt2.setString(7, element.child(4).text());
					pstmt2.setString(8, element.child(5).text());
					pstmt2.setString(9, element.child(6).text());
					pstmt2.setString(10, element.child(7).text());
					pstmt2.setString(11, element.child(8).text());
					pstmt2.setString(12, element.child(9).text());
					pstmt2.setString(13, element.child(10).text());
					pstmt2.setString(14, time);
					pstmt2.executeUpdate();
					System.out.println("插入"+element.child(0).text()+"数据成功");
					}
				}
				
				System.out.println("已获取"+city+"信息");
				for(int i=0;i<30000;i++) {}
				j++;
				if(pstmt2 !=null && pstmt2.isClosed()) {
					pstmt2.close();
				}
		}
			try {
				
				if(conn!=null && conn.isClosed()) {
					conn.close();
				}
				}catch(SQLException e) {
					e.printStackTrace();
				}
			
			System.out.println(j + " : " + map.size());
		
		
		
		/*//获取网页的图片
		//图片标签<img src="" alt="图像替换文本" width=""  height="" />
		Elements elements = document.getElementsByTag("img");
		for(Element element :elements) {
			String imgSrc = element.attr("src");
			String imgPath = url+imgSrc;
			System.out.println("正在下载的图片地址："+url+imgSrc);
			downImages(imgPath, filePath);
			System.out.println("图片下载成功！！！");
		}*/
		//解析我们需要下载的内容部分 下载到服务器
		
	}
}
