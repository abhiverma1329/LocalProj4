package in.co.rays.proj4.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.RecordNotFoundException;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

/**
 * The Class ChangePasswordCtl.
 * 
 * @author Abhishek Verma
 */

@WebServlet(name="ChangePasswordCtl", urlPatterns={"/ctl/ChangePasswordCtl"})
public class ChangePasswordCtl extends BaseCtl {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
   /**The Constant OP_CHANGE_MY_PROFILE */
	public static final String OP_CHANGE_MY_PROFILE="Change My Profile";
	
	/** The log. */
	public static Logger log = Logger.getLogger(ChangePasswordCtl.class);
	
	
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("ChangePasswordCtl Method validate Started");

		boolean pass = true;

		String op = request.getParameter("operation");

		if (OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)) {

			return pass;
		}
		if (DataValidator.isNull(request.getParameter("oldPassword"))) {
			request.setAttribute("oldPassword", PropertyReader.getValue("error.require", "Old Password"));
			pass = false;
		}
	
		
		if (DataValidator.isNull(request.getParameter("newPassword"))) {
			request.setAttribute("newPassword", PropertyReader.getValue("error.require", "New Password"));
			pass = false;
		}
		else if(request.getParameter("oldPassword").equals(request.getParameter("newPassword"))){
		request.setAttribute("newPassword", "Old password and New password should not be same!!");
		pass = false;
		}
		
		else if (!DataValidator.isPassword(request.getParameter("newPassword"))) {
			  request.setAttribute("newPassword","Password should contain 8 letter with alpha-numeric,capital latter and special Character");
			  pass = false; 
			  }
			 
		
		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.require", "Confirm Password"));
			pass = false;
		}
	
		else if (!DataValidator.isPassword(request.getParameter("confirmPassword"))) {
			  request.setAttribute("confirmPassword","Password should contain 8 letter with alpha-numeric,capital latter and special Character");
			  pass = false; 
			  }
		
		else if(!request.getParameter("newPassword").equals(request.getParameter("confirmPassword"))){
			request.setAttribute("confirmPassword", "New password and Confirm password must be same!!");
			pass = false;
			}

		
		log.debug("ChangePasswordCtl Method validate Ended");

		return pass;
	}
	
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request){
		
		System.out.println("ChangePasswordCtl populateBean 1");
		
		log.debug("ChangePasswordCtl method populateBean Started");
		
		UserBean bean = new UserBean();
		bean.setPassword(DataUtility.getString(request.getParameter("oldPassword")));
		bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
		populateDTO(bean, request);
		
		log.debug("ChangePasswordCtl method populateBean Ended");
		
		System.out.println("ChangePasswordCtl populateBean 2");
		
		return bean;
	}
	
	
	/**
	 * Contain Display logics. 
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ChangePasswordCtl doGet 1");
		
		ServletUtility.forward(getView(), request, response);
	}

	
	/**
	 * Contains Submit logics.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ChangePasswordCtl doPost 1");

		log.debug("ChangePasswordCtl Method doGet Started");
		
		HttpSession session = request.getSession(true);

		String op = DataUtility.getString(request.getParameter("operation"));
	
		// get model
				UserModel model = new UserModel();

				UserBean bean = (UserBean) populateBean(request);

				UserBean UserBean = (UserBean) session.getAttribute("user");

				String newPassword = (String) request.getParameter("newPassword");
	
				long id = UserBean.getId();
				
				if (OP_SAVE.equalsIgnoreCase(op)) {
					try {
						boolean flag = model.changePassword(id, bean.getPassword(), newPassword);
						if (flag == true) {
							bean = model.findByLogin(UserBean.getLogin());
							session.setAttribute("user", bean);
							ServletUtility.setBean(bean, request);
							ServletUtility.setSuccessMessage("Password has been changed Successfully.", request);
						}
					} catch (ApplicationException e) {
						//log.error(e);
						ServletUtility.handleException(e, request, response);
						return;

					} catch (RecordNotFoundException e) {
						ServletUtility.setErrorMessage(e.getMessage(), request);
					}
				} else if (OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)) {
					ServletUtility.redirect(ORSView.MY_PROFILE_CTL, request, response);
					return;

				}
				ServletUtility.forward(ORSView.CHANGE_PASSWORD_VIEW, request, response);
				
				System.out.println("ChangePasswordCtl doPost 1");

				
				log.debug("ChangePasswordCtl Method doGet Ended");
	}
	@Override
	protected String getView() {
		return ORSView.CHANGE_PASSWORD_VIEW;
	}

}
