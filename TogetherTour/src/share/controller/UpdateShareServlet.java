package share.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import common.MyFileRenamePolicy;
import member.model.vo.Member;
import share.model.service.ShareService;
import share.model.vo.Sattachment;
import share.model.vo.Share;

/** Servlet implementation class UpdateShareServlet */
@WebServlet("/update.share")
public class UpdateShareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /** @see HttpServlet#HttpServlet() */
    public UpdateShareServlet() {
        super();
    }

	/** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024 * 1024 * 50 ;
			String root = request.getSession().getServletContext().getRealPath("/") ;
			String savePath = root + "uploadFiles/" ;
			System.out.println("[savePath] : ["+savePath+"]") ;
			
			MultipartRequest multipartRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy()) ;
			ArrayList<String> saveFiles = new ArrayList<String>() ; // 바뀐 파일에 대한 이름을 저장
			ArrayList<String> originFiles = new ArrayList<String>() ; // 원본 파일에 대한 이름을 저장
			
			Enumeration<String> files = multipartRequest.getFileNames() ; // 폼에서 전송된 파일들의 이름 반환
			while(files.hasMoreElements()) {
				String name = files.nextElement() ; // 전송 순서의 역순으로 파일을 가져옴
				if(multipartRequest.getFilesystemName(name) != null) { // getFilesustemName(name) : MyFileRenamePolicy의 rename 메소드에서 작성된 대로 rename된 파일명
					saveFiles.add(multipartRequest.getFilesystemName(name)) ;
					originFiles.add(multipartRequest.getOriginalFileName(name)) ;
				}
			}
			
			int sbNum = Integer.parseInt(multipartRequest.getParameter("sbNum")) ;
			String title = multipartRequest.getParameter("title") ;
			String category = multipartRequest.getParameter("category") ;
			int kind = Integer.parseInt(multipartRequest.getParameter("kind")) ;
			String country = multipartRequest.getParameter("country") ;
			String city = multipartRequest.getParameter("city") ;
			String kakao = multipartRequest.getParameter("kakao") ;
			int stNum = Integer.parseInt(multipartRequest.getParameter("sbNum")) ;
			String content = multipartRequest.getParameter("content") ;
			String writer = ((Member)request.getSession().getAttribute("loginUser")).getmId() ;
			
			Share share = new Share() ;
			share.setSbNum(sbNum) ;
			share.setTitle(title) ;
			share.setCategory(category) ;
			share.setContent(content) ;
			share.setKind(kind) ;
			share.setCountry(country) ;
			share.setStName(""+stNum) ;
			share.setCity(city) ;
			share.setKakao(kakao) ;
			share.setWriter(writer) ;
			
			ArrayList<Sattachment> fileList = new ArrayList<Sattachment>() ;
			for(int i=originFiles.size()-1; i>=0; i--) {
				Sattachment at = new Sattachment() ;
				at.setSbNum(sbNum) ;
				at.setFilePath(savePath) ;
				at.setOriginName(originFiles.get(i)) ;
				at.setChangeName(saveFiles.get(i)) ;
				fileList.add(at) ;
			}
			ArrayList<Sattachment> old_satt = new ShareService().selectPicture(sbNum) ;
//			old_satt.get(0).
			int result = 0 ;
//			int result = new ShareService().updateShare(share,fileList) ;
			
			if(result>0) {
				response.sendRedirect("list.share") ;
			} else {
				for(int i=0; i<saveFiles.size(); i++) {
					File failedFile = new File(savePath+saveFiles.get(i)) ;
					failedFile.delete() ;
				}
				
				request.setAttribute("msg", "사진 게시판 등록에 실패했습니다.") ;
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response) ;
			}
		}
	}

	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
