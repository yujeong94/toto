package common;

import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File originFile) {
		long currentTime = System.currentTimeMillis();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		int ranNum = (int)(Math.random()*100000);
		
		String name = originFile.getName(); // 파일이름 
		String ext = null; // 확장자를 받아놓을 변수 초기화
		int dot = name.lastIndexOf("."); // 확장자의 위치를 가져오기 위함 file.jpg
		if(dot != -1) {
			// 확장자가 존재하면 .jpg 등등
			ext = name.substring(dot); // .부터 끝까지 ext에 넣음 
		} else {
			// . 확장자가 존재하지 않을때
			ext = "";
		}
		
		String fileName = sdf.format(new Date(currentTime)) + ranNum + ext; // db에 넣는게 아니라 날짜만 뽑아오는 거기 때문에 util.Date로 import
		File newFile = new File(originFile.getParent(), fileName); // 부모경로와 만들어준 파일이름 넣기
		
		return newFile;
	}
	
}
