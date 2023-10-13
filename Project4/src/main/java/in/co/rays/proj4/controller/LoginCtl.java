package in.co.rays.proj4.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.omg.PortableServer.ServantRetentionPolicyValue;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

/**
 * Login functionality Controller. Performs operation for Login
 *
 * @author Abhishek Verma
 */
@WebServlet(name = "LoginCtl", urlPatterns = { "/LoginCtl" })
public class LoginCtl extends BaseCtl {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant OP_REGISTER. */
	public static final String OP_REGISTER = "Register";

	/** The Constant OP_SIGN_IN. */
	public static final String OP_SIGN_IN = "SignIn";

	/** The Constant OP_SIGN_UP. */
	public static final String OP_SIGN_UP = "SignUp";

	/** The Constant OP_LOG_OUT. */
	public static final String OP_LOG_OUT = "logout";

	/** The log. */
	private static Logger log = Logger.getLogger(LoginCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("LoginCtl Method validate Started");
		boolean pass = true;

		String op = request.getParameter("operation");
		if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
			return pass;
		}

		String login = request.getParameter("login");

		if (OP_SIGN_IN.equals(op)) {
			if (DataValidator.isNull(login)) {
				request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
				pass = false;
			} else if (!DataValidator.isEmail(login)) {
				request.setAttribute("login", "Invalid Email Id");
				pass = false;
			}
			if (DataValidator.isNull(request.getParameter("password"))) {
				request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
				pass = false;
			}
		} else {
			return pass;
		}

		log.debug("LoginCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("LoginCtl Method populatebean Started");
		UserBean bean = new UserBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		log.debug("LoginCtl Method populatebean Ended");

		populateDTO(bean, request);
		return bean;
	}

	/**
	 * Display Login form.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("LoginCtl method doGet 1");

		log.debug(" Method doGet Started");

		HttpSession session = request.getSession(false);
		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_LOG_OUT.equals(op) && !OP_SIGN_IN.equals(op)) {

			session.invalidate();
			ServletUtility.setSuccessMessage("User Logout Succesfully", request);
			ServletUtility.forward(getView(), request, response);
			return;
		} /*
			 * else{ ServletUtility.redirect(ORSView.WELCOME_CTL, request,
			 * response); return; }
			 */

		ServletUtility.forward(getView(), request, response);

		System.out.println("LoginCtl method doGet 2");

		log.debug("LoginCtl Method doGet Ended");

	}

	/**
	 * Contains Submit logics.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		

		log.debug(" Login Ctl Method doPost Started");

		HttpSession session = request.getSession(true);
		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		UserModel model = new UserModel();
		RoleModel role = new RoleModel();

		if (OP_SIGN_IN.equalsIgnoreCase(op)) {
			UserBean bean = (UserBean) populateBean(request);

			try {
				//System.out.println("LoginCtl method doPost Authenticate........Id & password");
				bean = model.authenticate(bean.getLogin(), bean.getPassword());
				ServletUtility.setBean(bean, request);

				if (bean != null) {
					session.setAttribute("user", bean);
					long rollId = bean.getRoleId();
					RoleBean rolebean = role.findByPK(rollId);

					if (rolebean != null) {
						session.setAttribute("role", rolebean.getName());
					}

					// Code of The URI

				
					String str = (String) request.getParameter("URI");
					//String str = (String) session.getAttribute("URI");
					if (str == null || "null".equalsIgnoreCase(str)) {
						ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
						return;
					} else {
						ServletUtility.redirect(str, request, response);
						return;
					}
				} else {

				

					bean = (UserBean) populateBean(request);
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Invalid LoginId And Password", request);

				}
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request, response);
			return;
		}

		// ServletUtility.setBean(bean, request);
		log.debug("loginCtl Method doPost Ended");
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		return ORSView.LOGIN_VIEW;
	}
}
