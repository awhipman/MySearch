package com.html;

import java.util.ArrayList;
import java.util.List;

public class optionDB3 {

	public static void main(String[] args) {
		
		//�������ݿ�
		List<Locate> locs = new ArrayList<>();
		
		// ͨ�����ݿ��ȡ����
		linkDB3 db = new linkDB3();
		db.searchDB(locs, db);
		db.insertDB(locs, db);
		db.close();
		
	}

}
