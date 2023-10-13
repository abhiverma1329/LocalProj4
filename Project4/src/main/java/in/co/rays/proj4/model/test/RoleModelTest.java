package in.co.rays.proj4.model.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.RoleModel;

public class RoleModelTest {

	public static RoleModel model = new RoleModel();

	public static void main(String[] args) throws DuplicateRecordException, ApplicationException {
		 testAdd();
		// testUpdate();
		// testDelete();
		// testFindByName();
		//testFindByPK();
		//testSearch();
		//testList();
	}

	public static void testAdd() throws DuplicateRecordException {

		RoleBean bean = new RoleBean();
		// bean.setId(1);
		bean.setName("admin");
		bean.setDescription("admin");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		try {

			long pk = model.add(bean);

			System.out.println("role added success");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testUpdate() throws DuplicateRecordException {
		try {
			RoleBean bean = model.findByPK(3);
			bean.setName("Teacher");
			bean.setDescription("Teaching");
			model.update(bean);
			System.out.println("role updated");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testDelete() {
		try {
			RoleBean bean = model.findByPK(3);
			model.delete(bean);
			System.out.println("role delete success");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void testFindByName() throws ApplicationException {
		RoleBean bean = model.findByName("admin");
		if (bean == null) {
			System.out.println("find by name fail");
		}
		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getDescription());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());
	}

	public static void testFindByPK() {
		try {
			RoleBean bean = model.findByPK(1);
			if (bean == null) {
				System.out.println("test find by pk fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
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
		List list = new ArrayList();
		RoleBean bean = new RoleBean();
		
		bean.setName("ad");
		
		try {
			list =model.search(bean, 1, 10);
			
			
			if(list.size()<0){
				System.out.println("search role fail");
			}
			
			Iterator it = list.iterator();
			while(it.hasNext()){
				bean = (RoleBean)it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getDescription());
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
		RoleBean bean = new RoleBean();
		
		try {
			list=model.list(1, 10);
			
			Iterator it = list.iterator();
			while(it.hasNext()){
				bean= (RoleBean)it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getDescription());
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
}
