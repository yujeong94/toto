package member.controller;

import java.io.IOException;
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
import member.model.service.MemberService;
import member.model.vo.Member;
import member.model.vo.mAttachment;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="MemberJoinServlet", urlPatterns="/insert.me")
public class MemberJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberJoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024 * 1024 * 10;
			
			/*String root = request.getSession().getServletContext().getRealPath("/"); // 절대경로 맨 위 / 웹 서버 컨테이너 경로 추출
*/			String savePath = "C:/KH/uploadFiles"; // 파일 저장 경로
			
			MultipartRequest multipartRequest
			= new MultipartRequest(request,savePath, maxSize, "UTF-8", new MyFileRenamePolicy()); 
			
			Enumeration<String> files = multipartRequest.getFileNames();
			
			String saveFile = null; // 바뀐이름
			String originFile = null;
			while(files.hasMoreElements()) {
				String name = files.nextElement();
				
				if(multipartRequest.getFilesystemName(name) != null) {
					// getFilesystemName(name) : MyFileRenamePolicy에 있는 rename메소드에서 작성한 대로 rename된 파일명
					saveFile = multipartRequest.getFilesystemName(name); 
					originFile = multipartRequest.getOriginalFileName(name);
				}
			}
			mAttachment userImg = new mAttachment();
			userImg.setFilePath(savePath);
			userImg.setOriginName(originFile);
			userImg.setChangeName(saveFile);
			
			System.out.println(userImg.getChangeName());
			System.out.println(userImg.getFilePath());
			System.out.println(userImg.getOriginName());
			
			String joinUserId = multipartRequest.getParameter("joinUserId");
			String nickName = multipartRequest.getParameter("nickName");
			String userName = multipartRequest.getParameter("userName");
			String joinUserPwd = multipartRequest.getParameter("joinUserPwd");
			String pwd2 = multipartRequest.getParameter("pwd2");
			String email = multipartRequest.getParameter("email");
			String gender = multipartRequest.getParameter("gender");
			String age = multipartRequest.getParameter("age");
			String userType = multipartRequest.getParameter("userType");
			
			int typeInt = 0;
			if(userType.equals("general")) {
				typeInt = 1;
			} else {
				typeInt = 2;
			}
			
			System.out.println(joinUserId);
			Member member = new Member(joinUserId, nickName, joinUserPwd, userName, gender, email, typeInt, age);
			
			int result = new MemberService().insertMember(member,userImg);
			
			System.out.println(result);
			String page = null;
			if(result > 0) {
				page="views/member/loginView.jsp";
				request.setAttribute("msg", "TogetherTour에 회원이 되신걸 환영합니다.");
			} else {
				page="views/member/joinForm.jsp";
				request.setAttribute("msg", "회원가입에 실패하였습니다.");
			}
			
			RequestDispatcher view = request.getRequestDispatcher(page);
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
