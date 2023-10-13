package in.co.rays.proj4.model.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.exception.RecordNotFoundException;
import in.co.rays.proj4.model.UserModel;

//TODO: Auto-generated Javadoc
/**
 * User Model Test classes.
 *
 * @author SunilOS
 * @version 1.0 Copyright (c) SunilOS
 */
public class UserModelTest {

	/** Model object to test. */

	public static UserModel model = new UserModel();

	/**
	 * Main method to call test methods.
	 *
	 * @param args
	 *            the arguments
	 * @throws ApplicationException
	 *             the application exception
	 * @throws DuplicateRecordException
	 *             the duplicate record exception
	 * @throws ParseException
	 *             the parse exception
	 * @throws RecordNotFoundException
	 *             the record not found exception
	 */
	public static void main(String[] args)
			throws ApplicationException, DuplicateRecordException, ParseException, RecordNotFoundException {
		testAdd();
		//testUpdate();
		// testFindByPK();
		//testDelete();
		 //testFindByLogin();
		// testSearch();
		// testGetRoles();
		// testlist();
		// testAuthenticate();
		// testRegisterUser();      //mail
		 testchangePassword();    //mail
		 //testforgetPassword();      //mail
		// testresetPassword();        // mail

	}

	/**
	 * Test add.
	 *
	 * @throws ParseException
	 *             the parse exception
	 * @throws DuplicateRecordException
	 *             the duplicate record exception
	 * @throws RecordNotFoundException
	 *             the record not found exception
	 */
	public static void testAdd() throws ParseException, DuplicateRecordException, RecordNotFoundException {

		try {
			UserBean bean = new UserBean();
			SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-yyyy");

			// bean.setId(5234L);
			bean.setFirstName("Abhishek");
			bean.setLastName("Verma");
			bean.setLogin("Abhi99@gmail.com");
			bean.setPassword("Abhi@123");
			bean.setDob(sdf.parse("23-07-1999"));
			bean.setRoleId(1);
			bean.setUnSuccessfulLogin(2);
			bean.setGender("male");
			bean.setMobileNo("9907779225");
			bean.setLastLogin(new Timestamp(new Date().getTime()));
			bean.setLock("Yes");
			bean.setConfirmPassword("Abhi@123");
			long pk = model.add(bean);
			UserBean addedbean = model.findByPK(pk);
			System.out.println("Test add succesfuly");
			if (addedbean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test update.
	 *
	 * @throws RecordNotFoundException
	 *             the record not found exception
	 */
	public static void testUpdate() throws RecordNotFoundException {

		try {
			UserBean bean = model.findByPK(1);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			//bean.setFirstName("kamal");
			//bean.setLastName("verma");
			//bean.setLogin("shrma@gmail.com");
			
			// bean.setPassword("vemaji@123");
			//bean.setMobileNo("9876544455");
			//bean.setConfirmPassword("vemaji@123");
			bean.setRoleId(1);
			bean.setCreatedBy("admin");
			bean.setModifiedBy("admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			model.update(bean);
			System.out.println("user update successfully");

			UserBean updatedbean = model.findByPK(52);
			/*if (!"shrma@gmail.com".equals(updatedbean.getLogin())) {
				System.out.println("Test Update fail");
			}*/
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests find a User by PK.
	 */
	public static void testFindByPK() {
		try {
			UserBean bean = new UserBean();
			long pk = 1;
			bean = model.findByPK(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getUnSuccessfulLogin());
			System.out.println(bean.getGender());
			System.out.println(bean.getLastLogin());
			System.out.println(bean.getLock());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test find by login.
	 */
	public static void testFindByLogin() {
		try {
			UserBean bean = new UserBean();
			bean = model.findByLogin("Radhe@gmail.com");
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getUnSuccessfulLogin());
			System.out.println(bean.getGender());
			System.out.println(bean.getLastLogin());
			System.out.println(bean.getLock());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test search.
	 */
	public static void testSearch() {

		try {
			UserBean bean = new UserBean();
			List list = new ArrayList();
			bean.setLogin("Radhe@gmail.com");
			list = model.search(bean);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (UserBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getFirstName());
				System.out.println(bean.getLastName());
				System.out.println(bean.getLogin());
				System.out.println(bean.getPassword());
				System.out.println(bean.getDob());
				System.out.println(bean.getRoleId());
				System.out.println(bean.getUnSuccessfulLogin());
				System.out.println(bean.getGender());
				System.out.println(bean.getLastLogin());
				System.out.println(bean.getLock());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}
	
	public static void testDelete() throws ApplicationException{
		UserBean bean= model.findByPK(1);
		model.delete(bean);
		System.out.println("user deleted");
		
	}

	/**
	 * Test get roles.
	 *
	 * @throws ApplicationException
	 *             the application exception
	 */
	private static void testGetRoles() throws ApplicationException {

		UserBean bean = new UserBean();
		List list = new ArrayList();
		bean.setRoleId(3);
		list = model.getRoles(bean);

		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean = (UserBean) it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getUnSuccessfulLogin());
			System.out.println(bean.getGender());
			System.out.println(bean.getLastLogin());
			System.out.println(bean.getLock());
		}
	}

	/**
	 * Testlist.
	 *
	 * @throws ApplicationException
	 *             the application exception
	 */
	private static void testlist() throws ApplicationException {
		UserBean bean = new UserBean();
		List list = new ArrayList();
		list = model.list(0, 0);

		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean = (UserBean) it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getUnSuccessfulLogin());
			System.out.println(bean.getGender());
			System.out.println(bean.getLastLogin());
			System.out.println(bean.getLock());
		}
	}

	/**
	 * TestAuthenticate.
	 *
	 * @throws ApplicationException
	 *             the application exception
	 */
	private static void testAuthenticate() throws ApplicationException {
		UserBean bean = new UserBean();
		bean.setLogin("rranjitch11ou1dhay@gmail.com");
		bean.setPassword("rr");
		bean = model.authenticate(bean.getLogin(), bean.getPassword());
		if (bean != null) {
			System.out.println("Successful login");
		} else {
			System.out.println("login id is wrong ");
		}

	}
	
	 public static void testRegisterUser() throws ParseException, RecordNotFoundException {
	        try {
	            UserBean bean = new UserBean();
	            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

	            // bean.setId(8L);
	            bean.setFirstName("vipin");
	            bean.setLastName("kumawat");
	            bean.setLogin("rranjitch11ou1dhay@gmail.com");
	            bean.setPassword("rr");
	            bean.setConfirmPassword("4444");
	            bean.setDob(sdf.parse("11/20/2015"));
	            bean.setGender("Male");
	            bean.setRoleId(2);
	            long pk = model.registerUser(bean);
	            System.out.println("Successfully register");
	            System.out.println(bean.getFirstName());
	            System.out.println(bean.getLogin());
	            System.out.println(bean.getLastName());
	            System.out.println(bean.getDob());
	            UserBean registerbean = model.findByPK(pk);
	            if (registerbean != null) {
	                System.out.println("Test registation fail");
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        } catch (DuplicateRecordException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 
	  /**
	     * Tests changepassword
	     *
	     * @throws ParseException
	     */
	    public static void testchangePassword() {

	        try {
	            UserBean bean = model.findByLogin("ranjitchoudhary20@gmail.com");
	            String oldPassword = bean.getPassword();
	            bean.setId(15l);
	            bean.setPassword("88");
	            bean.setConfirmPassword("kk");
	            String newPassword = bean.getPassword();
	            try {
	                model.changePassword(15L, oldPassword, newPassword);
	                System.out.println("password has been change successfully");
	            } catch (RecordNotFoundException e) {
	                e.printStackTrace();
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }

	    /**
	     * Tests fogetPassword
	     *
	     * @throws ParseException
	     */
	    public static void testforgetPassword() {
	        try {
	            boolean b = model.forgetPassword("Radhe@gmail.com");

	            System.out.println("Suucess : Test Forget Password Success");

	        } catch (RecordNotFoundException e) {
	            e.printStackTrace();
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }

	    
	    /**
	     * Tests resetPassword
	     * @throws RecordNotFoundException 
	     *
	     * @throws ParseException
	     */
	    public static void testresetPassword() throws RecordNotFoundException {
	        UserBean bean = new UserBean();
	        try {
	            bean = model.findByLogin("Radhe@gmail.com");
	            if (bean != null) {
	                boolean pass = model.resetPassword(bean);
	                if (pass = false) {
	                    System.out.println("Test Update fail");
	                }
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }
	}

