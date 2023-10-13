package in.co.rays.proj4.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

/**
 * College functionality Controller. Performs operation for add, update, delete
 * and get College
 * 
 * @author Abhishek Verma
 */
@WebServlet(name = "CollegeCtl",urlPatterns ={"/ctl/CollegeCtl"})
public class CollegeCtl extends BaseCtl {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	private static Logger log = Logger.getLogger(CollegeCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("CollegeCtl Method validate Started");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		}else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.name", "Invalid"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getValue("error.require", "Address"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("state"))) {
			request.setAttribute("state", PropertyReader.getValue("error.require", "State"));
			pass = false;
		}else if (!DataValidator.isName(request.getParameter("state"))) {
			request.setAttribute("state", PropertyReader.getValue("error.name", "Invalid State"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city", PropertyReader.getValue("error.require", "City"));
			pass = false;
		}else if (!DataValidator.isName(request.getParameter("city"))) {
			request.setAttribute("city", PropertyReader.getValue("error.name", "Invalid City"));
			pass = false;
		}
		else if (!DataValidator.isValidName(request.getParameter("city"))) {
	      	  request.setAttribute("city",PropertyReader.getValue("error.name", "Invalid City"));
	            pass = false;
			}
		if (DataValidator.isNull(request.getParameter("phoneNo"))) {
			request.setAttribute("phoneNo", PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		}else if (!DataValidator.isMobileNo(request.getParameter("phoneNo"))) {
			request.setAttribute("phoneNo", "Mobile No. must be 10 Digit and No. Series start with 6-9");
			pass = false;
		}

		log.debug("CollegeCtl Method validate Ended");
		return pass;
	}

	
	@Override
	protected BaseBean populateBean(HttpServletRequest request){
		
		System.out.println("CollegeCtl populateBean 1");
		
		log.debug("CollegeCtl method populateBean Started");
		CollegeBean bean = new CollegeBean();
		
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setState(DataUtility.getString(request.getParameter("state")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setPhoneNo(DataUtility.getString(request.getParameter("phoneNo")));
		
		populateDTO(bean, request);
		log.debug("CollegeCtl method populateBean Ended");
		
		System.out.println("CollegeCtl populateBean 1");
		
		return bean;
	}

	
	/**
	 * Contains Display logic.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("CollegeCtl doGet 1");
		
		log.debug("CollegeCtl method doGet Started");
		
		String op = DataUtility.getString(request.getParameter("operation"));
		
		//get model
		CollegeModel model= new CollegeModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		if(id>0||op!=null){
			CollegeBean bean;
			
			try {
				bean=model.findByPK(id);
				ServletUtility.setBean(bean, request);
			} catch (Exception e) {
				log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
			}
		}
		
		ServletUtility.forward(getView(), request, response);
		
		System.out.println("CollegeCtl doGet 2");
		
		log.debug("CollegeCtl method doGet Ended");

	}

	
	/**
	 * Contains Submit logics.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("CollegeCtl doPost 1");
		
		log.debug("CollegeCtl method doPost Started");
		
		String op= DataUtility.getString(request.getParameter("operation"));
		
		//get model
		CollegeModel model= new CollegeModel();
		CollegeBean bean = (CollegeBean) populateBean(request);
		long id = DataUtility.getLong(request.getParameter("id"));
		
		if(OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)){
			
		try{
			if(id>0){		
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("College is Successfully Updated", request);
				
			}else{
				 long pk = model.add(bean);
				 ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("College is Successfully Added", request);
				

			}
				
		
		
		}catch(ApplicationException e ){
			e.printStackTrace();
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		} catch (DuplicateRecordException e) {
			ServletUtility.setBean(bean, request);
			ServletUtility.setErrorMessage("College Name Already Exist", request);
			
		}		
		}
		else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request, response);
			return;
		}
		else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COLLEGE_CTL, request, response);
			return;
		}
		ServletUtility.setBean(bean, request);
		 ServletUtility.forward(getView(), request, response);

		 System.out.println("CollegeCtl doPost 2");
		 
	        log.debug("CollegeCtl Method doGet Ended");
	}

	@Override
	protected String getView() {
		
		return ORSView.COLLEGE_VIEW;
	}

}
