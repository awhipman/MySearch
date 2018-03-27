package com.html;

import java.util.ArrayList;
import java.util.List;

public class optionDB3 {

	public static void main(String[] args) {
		
		//连接数据库
		List<Locate> locs = new ArrayList<>();
		
		// 通过数据库获取数据
		linkDB3 db = new linkDB3();
		db.searchDB(locs, db);
		db.insertDB(locs, db);
		db.close();
		
	}

}
