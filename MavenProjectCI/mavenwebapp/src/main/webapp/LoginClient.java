

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginClient
 */
@WebServlet(
        description = "LoginServlet", 
        urlPatterns = { "/LoginServlet" }, 
        initParams = { 
                @WebInitParam(name = "user", value = "admin"), 
                @WebInitParam(name = "password", value = "admin")
        })
public class LoginClient extends HttpServlet {
    private static final long serialVersionUID = 1L;
        
     
    public void init() throws ServletException {
        //we can create DB connection resource here and set it to Servlet context
    	 if(getServletContext().getInitParameter("dbURL").equals("jdbc:mysql://localhost/mysql_db") &&
                getServletContext().getInitParameter("dbUser").equals("mysql_user") &&
                getServletContext().getInitParameter("dbUserPwd").equals("mysql_pwd"))
        getServletContext().setAttribute("DB_Success", "True");
        else throw new ServletException("DB Connection error");
    }
 
     
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        //get request parameters for userID and password
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
  
        //get servlet config init params
        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");
        //logging example
        log("User="+user+"::password="+pwd);
        System.out.println("Userid"+ userID+ "password"+password);
        if(userID.equals(user) && password.equals(pwd)){
            //response.sendRedirect("LoginSuccess.jsp");
        	response.sendRedirect("LoginSuccess.jsp");
        }
        else if(user.equals("admin") && pwd.equals("admin")){
            response.sendRedirect("LoginSuccess.jsp");
        }
        else{
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request, response);
             
        }         
    }
}