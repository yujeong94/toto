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
			int result1 = 1 ;
			int result2 = 0 ;
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
			String content = multipartRequest.getParameter("content") ;
			int kind = Integer.parseInt(multipartRequest.getParameter("kind")) ;
			String country = multipartRequest.getParameter("country") ;
			String city = multipartRequest.getParameter("city") ;
			String kakao = multipartRequest.getParameter("kakao") ;
			int stNum = Integer.parseInt(multipartRequest.getParameter("stNum")) ;
			String writer = ((Member)request.getSession().getAttribute("loginUser")).getmId() ;
			String fileCheckUpdate = multipartRequest.getParameter("fileBool2") ;
			System.out.println("[Notice] [Update Share Servlet] [File 존재 여부 : "+(fileCheckUpdate.equals("trueFiles") ? "파일이 존재합니다." : "파일이 존재하지 않습니다.")) ;
			System.out.println("[Notice] [Update Share Servlet] [Title : ("+title+")]") ;
			System.out.println("[Notice] [Update Share Servlet] [Category(sNum, sName) : ("+category+")]") ;
			System.out.println("[Notice] [Update Share Servlet] [Kind : ("+kind+")]") ;
			System.out.println("[Notice] [Update Share Servlet] [Country : ("+country+")]") ;
			System.out.println("[Notice] [Update Share Servlet] [City : ("+city+")]") ;
			System.out.println("[Notice] [Update Share Servlet] [Kakao : ("+kakao+")]") ;
			System.out.println("[Notice] [Update Share Servlet] [StNum : ("+stNum+")]") ;
			System.out.println("[Notice] [Update Share Servlet] [Writer : ("+writer+")]") ;
			System.out.println("[Notice] [Update Share Servlet] [fileCheckUpdate : ("+fileCheckUpdate+")]") ;
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
			
			ArrayList<Sattachment> old_satt = new ShareService().selectPicture(sbNum) ;
			if(!old_satt.isEmpty()) {
				System.out.println("[Info] 사진 수정 서비스 : 기존 사진 삭제에서 사진이 ("+old_satt.size()+")개 감지되었습니다.") ;
				result1 = new ShareService().updateOldSatt(sbNum) ;
				System.out.println("[Info] 사진 수정 서비스 : 기존 사진 삭제"+((result1>0) ? "성공" : "실패")) ;
			}
			
			if(fileCheckUpdate.equals("trueFiles")) {
				System.out.println("[Info] 파일 확인 체크 서비스 [fileCheckUpdate:"+fileCheckUpdate+"]를 통해 사진 포함 게시글 수정 서비스 ("+title+")에 입장했습니다.") ;
				ArrayList<Sattachment> fileList = new ArrayList<Sattachment>() ;
				for(int i=originFiles.size()-1; i>=0; i--) {
					System.out.println("[Info] trueFiles를 통해 사진 포함 서비스의 for문에 입장했습니다.") ;
					Sattachment at = new Sattachment() ;
					at.setFilePath(savePath) ;
					System.out.println("[Info] savePath["+i+"] : "+savePath) ;
					at.setOriginName(originFiles.get(i)) ;
					System.out.println("[Info] originFiles["+i+"] : "+originFiles.get(i)) ;
					at.setChangeName(saveFiles.get(i)) ;
					System.out.println("[Info] saveFiles["+i+"] : "+saveFiles.get(i)) ;
					fileList.add(at) ;
				}
				result2 = new ShareService().updateShare(share,fileList) ;
			} else {
				result2 = new ShareService().updateShare(share) ;
				System.out.println("[Result] [falseFiles - Update Share Result] ["+result2+"]") ;
			}
			
			if(result1>0 && result2>0) {
				response.sendRedirect("detail.share?sbNum="+share.getSbNum()) ;
				System.out.println("[Final Result Files - Success] Result 1 : "+result1) ;
				System.out.println("[Final Result Files - Succes] Result 2 : "+result2) ;
			} else {
				for(int i=0; i<saveFiles.size(); i++) {
					File failedFile = new File(savePath+saveFiles.get(i)) ;
					failedFile.delete() ;
				}
				System.out.println("[Final Result NoFiles - Failed] Result 1 : "+result1) ;
				System.out.println("[Final Result NoFiles - Failed] Result 2 : "+result2) ;
				request.setAttribute("msg", "사진 게시판 수정에 실패했습니다.") ;
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response) ;
			}
		}
	}

	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}