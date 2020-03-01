package review.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import common.MyFileRenamePolicy;
import review.model.service.ReviewService;
import review.model.vo.Review;
import review.model.vo.rAttachment;

/**
 * Servlet implementation class ReviewUpdateServlet
 */
@WebServlet("/update.rv")
public class ReviewUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 1024 * 1024 * 100; // 100Mbyte : 전송파일 용량 제한
			String root = request.getSession().getServletContext().getRealPath("/"); // 웹 서버 컨테이너 경로 추출
			String savePath = root + "uploadFiles/";
			
			MultipartRequest multipartRequest 
			= new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
			ArrayList<String> saveFiles = new ArrayList<String>();		// 바뀐 파일의 이름을 저장할 ArrayList
			ArrayList<String> originFiles = new ArrayList<String>();	// 원본 파일의 이름을 저장할 ArrayList
			
			Enumeration<String> files = multipartRequest.getFileNames();
			
			// 폼에서 전송된 파일들의 이름 반환
			while(files.hasMoreElements()) {
				String name = files.nextElement(); // 전송 순서의 역순으로 파일을 가져옴
				
				if(multipartRequest.getFilesystemName(name) != null) {
					// getFilesystemName(name) : MyFileRenamePolicy의 rename메소드에서 작성한 대로 rename된 파일명
					saveFiles.add(multipartRequest.getFilesystemName(name));
					originFiles.add(multipartRequest.getOriginalFileName(name));
				}
			}
			
			
			
			
			request.setCharacterEncoding("UTF-8");
			int rNum = Integer.parseInt(multipartRequest.getParameter("rnum"));
			String location = multipartRequest.getParameter("location");
			String title = multipartRequest.getParameter("title");
			String content = multipartRequest.getParameter("content");
			String gCheck = multipartRequest.getParameter("guide");
			String guide="";
			
			if(gCheck != null) {
				guide="Y";
			} else {
				guide="N";
			}
			
			Review review = new Review();
			review.setrNum(rNum);
			review.setLocation(location);
			review.setTitle(title);
			review.setContent(content);
			review.setGuide(guide);
			
			
			ArrayList<rAttachment> fileList = new ArrayList<rAttachment>();
			for(int i = originFiles.size() -1; i >= 0; i--) {
				rAttachment at = new rAttachment();
				at.setbId(rNum);
				at.setFilePath(savePath);
				at.setOriginName(originFiles.get(i));
				at.setChangeName(saveFiles.get(i));
				
				
				fileList.add(at);
			}
			
			
			ArrayList<String> detailImgId = new ArrayList<String>();
			for(int i = 0; i < 3; i++) {
				detailImgId.add(multipartRequest.getParameter("detailImgId" + i));
			}
			
			ArrayList<String> changeImg = new ArrayList<String>();
			for(int i = 0; i < 3; i++) {
				changeImg.add(multipartRequest.getParameter("cContent" + (i)));
			}

			
			ArrayList<rAttachment> changeFile = new ArrayList<rAttachment>();
			ArrayList<rAttachment> newInsertFile = new ArrayList<rAttachment>();
			
			for(int h = 0; h < fileList.size();) {
				for(int i = 0; i < 3; i++) {
					if(!detailImgId.get(i).equals("") && changeImg.get(i).equals("data")) { // 바꾼 파일
						fileList.get(h).setfId(Integer.parseInt(detailImgId.get(i)));
						changeFile.add(fileList.get(h));
						h++;
					} else if(detailImgId.get(i).equals("") && changeImg.get(i).equals("data")) { // 새로 넣은 파일
						newInsertFile.add(fileList.get(h));
						h++;
					}
				}
			}
			
			int result = 0;
			
			
			if(changeFile.isEmpty() && newInsertFile.isEmpty()) {
				result = new ReviewService().updateReview(review);
			} else if(!changeFile.isEmpty() && newInsertFile.isEmpty()) {
				result = new ReviewService().updateReview(review, changeFile);
			} else if(changeFile.isEmpty() && !newInsertFile.isEmpty()) {
				result = new ReviewService().updateReview(review, newInsertFile);
			} else {
				result = new ReviewService().updateReview(review, changeFile, newInsertFile);
			}
		
			
			
			//int result = new ReviewService().updateReview(review);
			
			String page = "";
			
			if(result > 0) {
				page = "/detail.rv?rNum=" + rNum;
			} else {
				
				for(int i = 0; i < saveFiles.size(); i++) {
					File failedFile = new File(savePath + saveFiles.get(i));
					failedFile.delete();
				}
				
				
				page = "views/common/errorPage.jsp";
				request.setAttribute("msg", "게시판 수정에 실패하였습니다.");
			}
			RequestDispatcher view = request.getRequestDispatcher(page);
			view.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
