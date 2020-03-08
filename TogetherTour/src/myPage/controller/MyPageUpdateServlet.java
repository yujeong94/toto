package myPage.controller ;

import java.io.IOException ;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException ;
import javax.servlet.annotation.WebServlet ;
import javax.servlet.http.HttpServlet ;
import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import common.MyFileRenamePolicy;
import member.model.service.MemberService;
import member.model.vo.Member;
import member.model.vo.mAttachment;

/** Servlet implementation class MyPageUpdateServlet */
@WebServlet("/update.myPage")
public class MyPageUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L ;
       
    /** @see HttpServlet#HttpServlet() */
    public MyPageUpdateServlet() {
        super() ;
    }

	/** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024 * 1024 * 10;
			String root = request.getSession().getServletContext().getRealPath("/") ; // 절대경로 맨 위 / 웹 서버 컨테이너 경로 추출
			String savePath = root + "uploadFiles/" ; // 파일 저장 경로
			MultipartRequest multipartRequest = new MultipartRequest(request,savePath, maxSize, "UTF-8", new MyFileRenamePolicy()) ;
			
			String saveFile = null ; // 바뀐이름
			String originFile = null ;
			Enumeration<String> files = multipartRequest.getFileNames() ;
			String name = files.nextElement() ;
			if(multipartRequest.getFilesystemName(name) != null) {
				// getFilesystemName(name) : MyFileRenamePolicy에 있는 rename메소드에서 작성한 대로 rename된 파일명
				saveFile = multipartRequest.getFilesystemName(name) ;
				originFile = multipartRequest.getOriginalFileName(name) ;
				System.out.println(originFile+"\n"+saveFile) ;
			}
			mAttachment userImg = new mAttachment();
			userImg.setFilePath(savePath) ;
			userImg.setOriginName(originFile) ;
			userImg.setChangeName(saveFile) ;
			
			String joinUserId = multipartRequest.getParameter("joinUserId") ;
			String nickName = multipartRequest.getParameter("nickName") ;
			String userName = multipartRequest.getParameter("userName") ;
			String email = multipartRequest.getParameter("email") ;
			String gender = multipartRequest.getParameter("gender") ;
			System.out.println("[Info] [UpdateMember Servlet] [Gender : '"+gender+"']") ;
			String age = multipartRequest.getParameter("age") ;
			String userType = multipartRequest.getParameter("userType") ;
			int typeInt = 0 ;
			if(userType.equals("general"))	typeInt = 1;
			else							typeInt = 2 ;
			
			Member member = new Member() ;
			member.setmId(joinUserId) ;
			member.setNickName(nickName) ;
			member.setUserName(userName) ;
			member.setGender(gender) ;
			member.setEmail(email) ;
			member.setmKind(typeInt) ;
			member.setAge(age) ;
			int result = new MemberService().updateMember(member,userImg) ;
			
			String page = null ;
			if(result > 0) {
				page="viewSelf.myPage" ;
				request.setAttribute("msg", "회원수정에 성공했습니다.") ;
			} else {
				page="viewSelf.myPage" ;
				request.setAttribute("msg", "회원수정에 실패했습니다.") ;
			}
			
			RequestDispatcher view = request.getRequestDispatcher(page);
			view.forward(request, response);
		}
	}

	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response) ;
	}
}