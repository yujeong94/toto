package review.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import common.MyFileRenamePolicy;
import member.model.vo.Member;
import review.model.service.ReviewService;
import review.model.vo.Review;
import review.model.vo.rAttachment;

/**
 * Servlet implementation class ReviewInsertServlet
 */
@WebServlet("/insert.rv")
public class ReviewInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (ServletFileUpload.isMultipartContent(request)) { // enctype(지금 넘어온 인코드 타입)이 mutipart/form-data로 전송되었는지 확인
			
			int maxSize = 1024 * 1024 * 10;// 10Mbyte : 전송파일 용량 제한
			String root = request.getSession().getServletContext().getRealPath("/"); // 웹 서버 컨테이너 경로 추출
			String savePath = root + "uploadFiles/";
			
			MultipartRequest multipartRequest 
			= new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			ArrayList<String> saveFiles = new ArrayList<String>();		// 바뀐 파일의 이름을 저장할 ArrayList
			ArrayList<String> originFiles =  new ArrayList<String>();	// 원본 파일의 이름을 저장할 ArrayList
			
			Enumeration<String> files = multipartRequest.getFileNames();
			// 폼에서 전송된 파일들의 이름 반환
			
			
			while(files.hasMoreElements()) {
				String name = files.nextElement(); // 전송 순서의 역순으로 가져옴
				
				if(multipartRequest.getFilesystemName(name) != null) {
					// getFilesystemName(name) : MyFileRenamePolicy의 rename메소드에서 작성한 대로 rename된 파일 명
					saveFiles.add(multipartRequest.getFilesystemName(name));
					originFiles.add(multipartRequest.getOriginalFileName(name));
				}
			}
			
			
			request.setCharacterEncoding("UTF-8");
			String location = multipartRequest.getParameter("location");
			String startDate = multipartRequest.getParameter("startDate");
			String lastDate = multipartRequest.getParameter("lastDate");
			String gCheck = multipartRequest.getParameter("guide");
			String title = multipartRequest.getParameter("title");
			String content = multipartRequest.getParameter("content");
			String userId = ((Member)request.getSession().getAttribute("loginUser")).getmId();
			
			String guide="";
			
			if(gCheck != null) {
				guide="Y";
			} else {
				guide="N";
			}
			
			
			
			//System.out.println("확인 : " + guide);
			
			Date sqlStartDate = null;
			Date sqlLastDate = null;
			System.out.println(startDate);
			System.out.println(lastDate);
		
			
			Review r = new Review();
			
			
			if(startDate != "") {
				String[] dateArr = startDate.split("-");
				
				int year = Integer.parseInt(dateArr[0]);
				int month = Integer.parseInt(dateArr[1])-1;
				int day = Integer.parseInt(dateArr[2]);
				
				sqlStartDate = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
			} else {
				sqlStartDate = new Date(new GregorianCalendar().getTimeInMillis());
			}
			
			if(lastDate != "") {
				String[] dateArr = lastDate.split("-");
				
				int year = Integer.parseInt(dateArr[0]);
				int month = Integer.parseInt(dateArr[1])-1;
				int day = Integer.parseInt(dateArr[2]);
				
				sqlLastDate = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
			} else {
				sqlLastDate = new Date(new GregorianCalendar().getTimeInMillis());
			}
			
			
			ArrayList<rAttachment> fileList =  new ArrayList<rAttachment>();
			for(int i=originFiles.size()-1; i>=0; i--) {
				rAttachment at = new rAttachment();
				at.setFilePath(savePath);
				at.setOriginName(originFiles.get(i));
				at.setChangeName(saveFiles.get(i));
				
				fileList.add(at);
			}
			
			
			
			
			
			r = new Review(title, content,location,userId, sqlStartDate,sqlLastDate,guide);
			//Notice n = new Notice(title, content, userId, sqlDate);
			int result = new ReviewService().insertReview(r,fileList);
			
			if(result > 0) {
				response.sendRedirect("list.rv");
			} else {
				for(int i = 0; i < saveFiles.size(); i++) {
					File failedFile = new File(savePath + saveFiles.get(i));
					failedFile.delete();
				}
				
				RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
				request.setAttribute("msg", "게시판 등록에 실패하였습니다.");
			}
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
