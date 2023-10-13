package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.proj4.util.ServletUtility;

/**
 * Servlet implementation class WelcomeCtl
 * 
 * @author Abhishek Verma
 */
@WebServlet(name = "WelcomeCtl", urlPatterns = { "/WelcomeCtl" })
public class WelcomeCtl extends BaseCtl {

	/** The log. */
	private static Logger log = Logger.getLogger(WelcomeCtl.class);

	/**
	 * Contain Display Logics.
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
		log.debug("WelcomeCtl Method doGet Started");

		ServletUtility.forward(ORSView.WELCOME_VIEW, request, response);

		log.debug("WelcomeCtl Method doGet Ended");
	}

	/**
	 * Contain Submit Logics.
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
		log.debug("WelcomeCtl Method doPost Started");

		// ServletUtility.forward(getView(), request, response);
		doGet(request, response);

		log.debug("WelcomeCtl Method doGet Ended");
	}

	@Override
	protected String getView() {

		return ORSView.WELCOME_VIEW;
	}
}