package in.co.rays.proj4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

/**
 * The class College List
 * 
 * @author Abhishek Verma
 *
 */
@WebServlet(name="CollegeListCtl",urlPatterns={"/ctl/CollegeListCtl"})
public class CollegeListCtl extends BaseCtl {

    /** The log. */
    private static Logger log = Logger.getLogger(CollegeListCtl.class);
    
    
    @Override
    protected void preload(HttpServletRequest request){
    	
    	//System.out.println("CollegeListCtl preload 1");
    	
    	CollegeModel cmodel=new CollegeModel();
    	try{
    		List clist=cmodel.list();
    		
    		request.setAttribute("CollegeList", clist);
    	}
    	catch(ApplicationException e){
    		e.printStackTrace();
    	}
    }
    
    

    
    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
    	
    	//System.out.println("CollegeListCtl populateBean 1");
    	
        CollegeBean bean = new CollegeBean();

        bean.setName(DataUtility.getString(request.getParameter("name")));
        bean.setId(DataUtility.getLong(request.getParameter("collegeid")));
        //bean.setAddress(DataUtility.getString(request.getParameter("address")));
       // bean.setState(DataUtility.getString(request.getParameter("state")));
        bean.setCity(DataUtility.getString(request.getParameter("city")));
        //bean.setPhoneNo(DataUtility.getString(request.getParameter("phoneNo")));

        return bean;
    }

    /**
	 * Contains Display logics.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	
    	System.out.println("CollegeListCtl doGet 1");
    	

        int pageNo = 1;
        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

        CollegeBean bean = (CollegeBean) populateBean(request);

        String [] ids = request.getParameterValues("ids");
        CollegeModel model = new CollegeModel();

        List list = null;
        
        List nextList=null;

        try {
            list = model.search(bean, pageNo, pageSize);
            
            nextList=model.search(bean,pageNo+1,pageSize);
            
            request.setAttribute("nextlist", nextList.size());
        
        if (list == null || list.size() == 0) {
            ServletUtility.setErrorMessage("No record found ", request);
        }

        ServletUtility.setList(list, request);
        ServletUtility.setPageNo(pageNo, request);
        ServletUtility.setPageSize(pageSize, request);
        ServletUtility.forward(getView(), request, response);
    }
        catch (ApplicationException e) {
            log.error(e);
            ServletUtility.handleException(e, request, response);
            return;
        }
        
        System.out.println("CollegeListCtl doGet 2");
    }


    /**
	 * Contains Submit logics.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	
    	System.out.println("CollegeListCtl doPost 1");

        log.debug("CollegeListCtl doPost Start");

        List list = null;
        
        List  nextList=null;

        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

        pageNo = (pageNo == 0) ? 1 : pageNo;
        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

       
        String op = DataUtility.getString(request.getParameter("operation"));
        
        String [] ids = request.getParameterValues("ids");
        CollegeModel model = new CollegeModel();
        CollegeBean bean = (CollegeBean) populateBean(request);

                if (OP_SEARCH.equalsIgnoreCase(op)) {
                    pageNo = 1;
                } 
                else if (OP_NEXT.equalsIgnoreCase(op)) {
                    pageNo++;
                } 
                else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
                    pageNo--;
                }
                else if (OP_NEW.equalsIgnoreCase(op)) {
    			ServletUtility.redirect(ORSView.COLLEGE_CTL, request, response);
    			return;
    		}else if (OP_RESET.equalsIgnoreCase(op)) {
    			ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request, response);
    			return;
    		}  
            else if (OP_DELETE.equalsIgnoreCase(op)) {
                pageNo = 1;
                if (ids != null && ids.length > 0) {
                   CollegeBean deletebean = new CollegeBean();
               
                    for (String id : ids) {
                        deletebean.setId(DataUtility.getInt(id));
                        try {
							model.delete(deletebean);
						} catch (ApplicationException e) {
							ServletUtility.handleException(e, request, response);
							return;
						}ServletUtility.setSuccessMessage("College Data Successfully Deleted", request);
                    }
                } 
                else {
                    ServletUtility.setErrorMessage(
                            "Select at least one record", request);
                }
            }
            try {
				
            	list = model.search(bean, pageNo, pageSize);
				
            	nextList=model.search(bean,pageNo+1,pageSize);
				
            	request.setAttribute("nextlist", nextList.size());
				
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
         
            
            if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
                ServletUtility.setErrorMessage("No record found ", request);
            }
            ServletUtility.setList(list, request);
            ServletUtility.setBean(bean, request);
            ServletUtility.setPageNo(pageNo, request);
            ServletUtility.setPageSize(pageSize, request);
            ServletUtility.forward(getView(), request, response);
            log.debug("CollegeListCtl doPost End");
            
            
            
           // System.out.println("CollegeListCtl doPost 2");
    }
    
   
    @Override
    protected String getView() {
        return ORSView.COLLEGE_LIST_VIEW;
    }
}