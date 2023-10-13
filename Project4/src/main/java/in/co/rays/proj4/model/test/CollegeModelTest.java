package in.co.rays.proj4.model.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.CollegeModel;

public class CollegeModelTest {

	public static CollegeModel model = new CollegeModel();

	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {

		 //testAdd();
		 //testDelete();
		//testUpdate();
		//testFindByName();
		//testFindByPK();
		//testSearch();
		testList();
	}

	public static void testAdd() throws ApplicationException, DuplicateRecordException {
		CollegeBean bean = new CollegeBean();
		// bean.setId(1);
		System.out.println(bean.getId());
		bean.setName("LNCT");
		bean.setAddress("BHOPAL");
		bean.setState("Madhya Pradesh");
		bean.setCity("BHOPAL");
		bean.setPhoneNo("98765456789");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		long pk = model.add(bean);
		System.out.println("college added success");
	}

	public static void testDelete() {
		CollegeBean bean = new CollegeBean();
		long pk = 3L;
		bean.setId(pk);

		try {
			model.delete(bean);
			System.out.println("college deleted");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testUpdate() throws DuplicateRecordException {
		try {
			CollegeBean bean = model.findByPK(3);
			bean.setName("SHIVAJI UNIVERSITY");
			bean.setAddress("Bhopal");
			bean.setCity("Bhopal");
			bean.setPhoneNo("987654323456");
			model.update(bean);
			System.out.println("college update success");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void testFindByName(){
		
		
		
		try {
			CollegeBean bean =model.findByName("SHIVAJI UNIVERSITY");
			
			if(bean==null){
				System.out.println("no college found");
			}
			
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneNo());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testFindByPK(){
		
		try {
			CollegeBean bean= model.findByPK(2);
			
			if(bean==null){
				System.out.println("college not found");
			}
			
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneNo());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testSearch(){
		
		
		
		try {
			CollegeBean bean = new CollegeBean();
			List list = new ArrayList();
			bean.setName("BU");
			list=model.search(bean, 1, 10);
			
			if(list.size()<0){
				System.out.println("no record found");
			}
			
			Iterator it =list.iterator();
			while(it.hasNext()){
				bean= (CollegeBean)it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
                System.out.println(bean.getAddress());
                System.out.println(bean.getState());
                System.out.println(bean.getCity());
                System.out.println(bean.getPhoneNo());
                System.out.println(bean.getCreatedBy());
                System.out.println(bean.getModifiedBy());
                System.out.println(bean.getCreatedDatetime());
                System.out.println(bean.getModifiedDatetime());
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void testList(){
		List list = new ArrayList();
		CollegeBean bean= new CollegeBean();
		 try {
			list =model.list(1, 10);
			if(list.size()<0){
				System.out.println("college list not found");
			}
			
			Iterator it = list.iterator();
			while(it.hasNext()){
				bean=(CollegeBean)it.next();
				 System.out.println(bean.getId());
	                System.out.println(bean.getName());
	                System.out.println(bean.getAddress());
	                System.out.println(bean.getState());
	                System.out.println(bean.getCity());
	                System.out.println(bean.getPhoneNo());
	                System.out.println(bean.getCreatedBy());
	                System.out.println(bean.getCreatedDatetime());
	                System.out.println(bean.getModifiedBy());
	                System.out.println(bean.getModifiedDatetime());
			}
			
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
