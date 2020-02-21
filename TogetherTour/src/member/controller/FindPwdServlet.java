package member.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class FindPwdServlet
 */
@WebServlet("/findPwd.me")
public class FindPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userFindId = request.getParameter("userFindId");
		String findEmail = request.getParameter("findEmail");
		
		Member member = new MemberService().findPwd(userFindId,findEmail);
		
		String page=null;
		if(member != null) {
			page="views/member/findResult.jsp";
			request.setAttribute("userFindId", member.getmId());
			request.setAttribute("findEmail", member.getEmail());
		} else {
			page="views/member/findResult.jsp";
			request.setAttribute("msg", "존재하지 않는 회원입니다.");
		}
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
		
		
		// 이메일 전송
		
		String host = "smtp.naver.com";
		final String admin = "togethertour_team@naver.com";
		final String password = "toto200110";
		
		String userEmail = member.getEmail();
		int port = 465;
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host);
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(admin,password);
			}
		});
		try {
			// 이메일 내용 구성
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(admin));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
			
			//제목
			message.setSubject("[TogetherTour]비밀번호 찾기 결과 이메일입니다.");
			//내용
			
			// --------- 임시 비밀번호 생성 
			// 처음 문자는 영어로
			char pwSet1[] = new char[] {
                      'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
                      'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
			};
			
			char pwSet2[] = new char[] {
					'1','2','3','4','5','6','7','8','9','0',
                    'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
                    'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
			};
			
			int firstRan = (int)(Math.random()*(pwSet1.length));
			char firstPw = pwSet1[firstRan];
			String ranPw = "";
			for(int i = 0; i < 10; i++) {
				int ranIndex = (int)(Math.random()*(pwSet2.length));
				ranPw += pwSet2[ranIndex];
			}
			
			String lastPw = firstPw + ranPw + "!";
			member.setPwd(lastPw); // 회원비밀번호 임시비밀번호로 변경
			message.setText("임시비밀번호는 " + lastPw + "입니다.\n 임시비밀번호로 로그인하고 비밀번호를 변경해주세요.");
			
			
			//이메일 보내기
			Transport.send(message);
			System.out.println("성공적으로 메일을 보냈습니다.");
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
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
