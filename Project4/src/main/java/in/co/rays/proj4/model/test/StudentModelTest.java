package in.co.rays.proj4.model.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.StudentModel;

public class StudentModelTest {

	public static StudentModel model = new StudentModel();

	public static void main(String[] args) throws ParseException, DuplicateRecordException {

		 //testAdd();
		 //testDelete();
		 //TestFindByEmailId();
		 //testFindByPk();
		// testUpdate();
		//testSearch();
		testList();

	}

	private static void testAdd() throws Exception {
		StudentBean bean = new StudentBean();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// bean.setId(1);
			bean.setFirstName("Sourabh");
			bean.setLastName("Rajput");
			bean.setDob(sdf.parse("09/02/1998"));
			bean.setMobileNo("9876544567");
			bean.setEmail("Sourabh1@gmail.com");
			bean.setCollegeId(2L);
			bean.setCollegeName("LNCT");
			bean.setCreatedBy("admin");
			bean.setModifiedBy("admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			long pk = model.add(bean);
			System.out.println("record Added successfully");

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testDelete() {
		StudentBean bean = new StudentBean();
		Long pk = 2L;
		bean.setId(pk);
		try {
			model.delete(bean);
			System.out.println("student deleted");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void TestFindByEmailId() {

		StudentBean bean = new StudentBean();
		try {
			bean = model.findByEmailId("Sourabh1@gmail.com");

			if (bean == null) {
				System.out.println("test find by email failed");
			}

			System.out.println(bean.getId());
			System.out.println(bean.getCollegeId());
			System.out.println(bean.getCollegeName());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getModifiedDatetime());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testFindByPk() {

		StudentBean bean = new StudentBean();

		try {
			bean = model.findByPK(1);

			if (bean == null) {
				System.out.println("test find by pk failed");
			}

			System.out.println(bean.getId());
			System.out.println(bean.getCollegeId());
			System.out.println(bean.getCollegeName());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getEmail());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testUpdate() throws DuplicateRecordException {

		StudentBean bean;
		try {
			bean = model.findByPK(2);
			//bean.setCollegeId(2l);
			//bean.setCollegeName("Barkatullah University");
			//bean.setFirstName("Rajveer");
			//bean.setLastName("Sharma");
			bean.setCreatedBy("student");
			bean.setModifiedBy("student");
			model.update(bean);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public static void testSearch(){
		
		StudentBean bean = new StudentBean();
		
		
		try {
			List list = new ArrayList();
			//bean.setFirstName("Abhi");
			bean.setId(1);
			//bean.setCollegeName("nm");
			list = model.search(bean, 0, 0);
			
			if(list.size()<0){
				System.out.println("searching fail");
			}
			
			Iterator it = list.iterator();
			while(it.hasNext()){
			bean=(StudentBean)it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getCollegeId());
			System.out.println(bean.getCollegeName());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getEmail());
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void testList(){
		
		
		
		try {
			StudentBean bean = new StudentBean();
			List list = new ArrayList();
			list =  model.list(1, 10);
			
			if(list.size()<0){
				System.out.println("search student list fail");
			}
			
			Iterator it = list.iterator();
			while(it.hasNext()){
				bean= (StudentBean)it.next();
				
				System.out.println(bean.getId());
				System.out.println(bean.getCollegeId());
				System.out.println(bean.getCollegeName());
				System.out.println(bean.getFirstName());
				System.out.println(bean.getLastName());
				System.out.println(bean.getDob());
				System.out.println(bean.getMobileNo());
				System.out.println(bean.getEmail());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getModifiedBy());
			}
					
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}