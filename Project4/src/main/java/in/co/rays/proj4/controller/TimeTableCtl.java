package in.co.rays.proj4.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.bean.TimeTableBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.CourseModel;
import in.co.rays.proj4.model.SubjectModel;
import in.co.rays.proj4.model.TimeTableModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

/**
 * The Class TimeTableCtl.
 * 
 * @author Abhishek Verma
 */
@WebServlet(name = "TimeTableCtl", urlPatterns = { "/ctl/TimeTableCtl" })
public class TimeTableCtl extends BaseCtl {

	/** The log. */
	private static Logger log = Logger.getLogger(TimeTableCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		CourseModel cmodel = new CourseModel();
		SubjectModel smodel = new SubjectModel();
		List<CourseBean> clist = new ArrayList<CourseBean>();
		List<SubjectBean> slist = new ArrayList<SubjectBean>();
		try {
			clist = cmodel.list();
			slist = smodel.list();
			request.setAttribute("CourseList", clist);
			request.setAttribute("SubjectList", slist);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("validate method of TimeTable Ctl started");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("courseId"))) {
			request.setAttribute("courseId", PropertyReader.getValue("error.require", "Course"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("subjectId"))) {
			request.setAttribute("subjectId", PropertyReader.getValue("error.require", "Subject"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("semester"))) {
			request.setAttribute("semester", PropertyReader.getValue("error.require", "Semester"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("ExDate"))) {
			request.setAttribute("ExDate", PropertyReader.getValue("error.require", "Exam Date"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("ExTime"))) {
			request.setAttribute("ExTime", PropertyReader.getValue("error.require", "Exam Time"));
			pass = false;
		}

		log.debug("validate method of TimeTable Ctl End");
		return pass;
	}

	@Override
	protected TimeTableBean populateBean(HttpServletRequest request) {
		log.debug("populateBean method of TimeTable Ctl start");
		TimeTableBean bean = new TimeTableBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
		bean.setSemester(DataUtility.getString(request.getParameter("semester")));
		bean.setExamDate(DataUtility.getDate(request.getParameter("ExDate")));
		bean.setExamTime(DataUtility.getString(request.getParameter("ExTime")));

		populateDTO(bean, request);
		log.debug("populateBean method of TimeTable Ctl End");
		return bean;
	}

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
		log.debug("do Get method of TimeTable Ctl Started");

		long id = DataUtility.getLong(request.getParameter("id"));

		TimeTableModel model = new TimeTableModel();
		TimeTableBean bean = null;
		if (id > 0) {
			try {
				bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				log.error(e);
				ServletUtility.handleException(e, request, response);
			}
		}

		log.debug("do Get method of TimeTable Ctl End");

		ServletUtility.forward(getView(), request, response);
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
		log.debug("do post method of TimeTable Ctl start");

		List list;
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		TimeTableModel model = new TimeTableModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			TimeTableBean bean = (TimeTableBean) populateBean(request);
			try {
				if (id > 0) {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage(" TimeTable is Successfully Updated", request);

				} else {

					model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage(" TimeTable is Successfully Added", request);

				}

			} catch (ApplicationException e) {
				log.error(e);
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Time Table already Exists", request);
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("do Post method of TimeTableList Ctl End");
	}

	@Override
	protected String getView() {
		return ORSView.TIMETABLE_VIEW;
	}

}
