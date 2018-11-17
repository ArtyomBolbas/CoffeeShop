package by.bolbas.database;

import java.util.ArrayList;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import by.bolbas.model.CoffeeOrder;
import by.bolbas.util.HibernateUtil;

@ManagedBean  @ApplicationScoped
public class DatabaseOperations {
	/*
	 * Класс DatabaseOperations отвечает за операции производимые с базой данных.
	 */

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Поля, свойства необходииые DatabaseOperations 
	 */
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static Transaction transObj;
	private static Session sessionObj = HibernateUtil.getSessionFactory().openSession();
	private final static Logger LOG = Logger.getLogger(DatabaseOperations.class);

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Метод отвечает за добавление "Заказа" в базу данных.
	 * После чего происходим перенаправление на "Главную" страницу
	 */
	public String addOrder(CoffeeOrder order) {		
		try {
			transObj = sessionObj.beginTransaction();
			sessionObj.save(order);
			LOG.debug("Запущен метод - addOrder(); (Метод добавления \"Заказа\"), в классе -  DatabaseOperations");

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("createdStudentId",  order.getId());					
		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return "/index.xhtml?faces-redirect=true";
	}

	/*
	 * Метод получение количество "Заказов" в базе данных 
	 * Используется для показа на каждой страничке кол-ва заказов
	 */
	public Long getOrderCount() {
		try {
			LOG.debug("Запущен метод - getOrderCount(); (Метод получаение кол-во \"Заказов\"), в классе -  DatabaseOperations");

			transObj = sessionObj.beginTransaction();
			return  (Long) sessionObj.createQuery("select count(*) from CoffeeOrder").uniqueResult();
		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return 0L;
	}
	
	/*
	 * Метод позволяет получить всю информацию из базы данных.
	 */
	@SuppressWarnings("unchecked")
	public List<CoffeeOrder> retrieveCoffeeOrder() {		
		CoffeeOrder coffeeOrdersObj;
		List<CoffeeOrder> allCoffeeOrders = new ArrayList<CoffeeOrder>();
		try {
			transObj = sessionObj.beginTransaction();
			Query queryObj = sessionObj.createQuery("from CoffeeOrder");
			allCoffeeOrders = queryObj.list();
			for(CoffeeOrder coffeeOrder : allCoffeeOrders) {
				coffeeOrdersObj = new CoffeeOrder(); 	
				coffeeOrdersObj.setId(coffeeOrder.getId());
				coffeeOrdersObj.setCoffeeType(coffeeOrder.getCoffeeType());
				coffeeOrdersObj.setDeliveryType(coffeeOrder.getDeliveryType());
				coffeeOrdersObj.setEndTime(coffeeOrder.getEndTime());
				coffeeOrdersObj.setOrderDate(coffeeOrder.getOrderDate());
				coffeeOrdersObj.setStartTime(coffeeOrder.getStartTime());
				coffeeOrdersObj.setTotalCost(coffeeOrder.getTotalCost());
				coffeeOrdersObj.setWeight(coffeeOrder.getWeight());						
				allCoffeeOrders.add(coffeeOrdersObj);  
			}			
			LOG.debug("Запущен метод - retrieveCoffeeOrder(); (Метод получение всех \"Заказов\"), в классе -  DatabaseOperations");

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findCoffeeOrderById", "true");
		} catch(Exception exceptionObj) {
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return allCoffeeOrders;
	}
	
	/*
	 * Метод позволяет получения информации о конкретном "Заказе"
	 * Служит для дальнейшего обновления информации.
	 * 
	 */
	public String getOrderById(int orderId) {	
		CoffeeOrder particularOrdObj = new CoffeeOrder();
		try {
			transObj = sessionObj.beginTransaction();
			Query queryObj = sessionObj.createQuery("from CoffeeOrder where id= :order_id").setInteger("order_id", orderId);
			
			particularOrdObj = (CoffeeOrder)queryObj.uniqueResult();
			
			LOG.debug("Запущен метод - getOrderById(); (Метод получния информации о конкретном \"Заказе\"), в классе -  DatabaseOperations");
	
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("editCoffeeOrder",  particularOrdObj);
		} catch(Exception exceptionObj) {
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return "/orderEdit.xhtml?faces-redirect=true";
	}
	
	/*
	 * Метод позволяет получения информации о конкретном "Заказе"
	 * Служит для дальнейшего удаления информации.
	 */
	public String getOrderByDeleteId(int orderId) {	
		CoffeeOrder particularOrdObj = new CoffeeOrder();
		try {
			transObj = sessionObj.beginTransaction();
			Query queryObj = sessionObj.createQuery("from CoffeeOrder where id= :order_id").setInteger("order_id", orderId);
			
			particularOrdObj = (CoffeeOrder)queryObj.uniqueResult();
			
			LOG.debug("Запущен метод - getOrderByDeleteId(); (Метод получения информации о конкретном \"Заказе\"), в классе -  DatabaseOperations");
		
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("deleteCoffeeOrder",  particularOrdObj);
		} catch(Exception exceptionObj) {
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return "/orderDelete.xhtml?faces-redirect=true";
	}
	
	/*
	 * Метод позволяет обновить информацию
	 * Служит для дальнейшего обновления информации.
	 * 
	 */
	public String updateCoffeeOrderRecord(CoffeeOrder updateCoffeeOrderObj) {
		try {
			transObj = sessionObj.beginTransaction();
			sessionObj.update(updateCoffeeOrderObj);		
			LOG.debug("Запущен метод - updateCoffeeOrderRecord(); (Метод обновляет информацию а каком-то конкретном \"Заказе\"), в классе -  DatabaseOperations");

			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("updatedCoffeeOrderRecord",  "Success");
		} catch(Exception exceptionObj){
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return "/index.xhtml?faces-redirect=true";
	}
	
	/*
	 * Метод позволяет удалить информацию
	 * Служит для дальнейшего удаления информации.
	 */
	@SuppressWarnings("deprecation")
	public String deleteCoffeeOrderInDb(int delCoffeeOrderId) {
		try {
			transObj = sessionObj.beginTransaction();
			CoffeeOrder studId = (CoffeeOrder)sessionObj.load(CoffeeOrder.class, new Integer(delCoffeeOrderId));
			sessionObj.delete(studId);
			LOG.debug("Запущен метод - deleteCoffeeOrderInDb(); (Метод удаляет информацию а каком-то конкретном \"Заказе\"), в классе -  DatabaseOperations");

			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("deletedCoffeeOrderId",  delCoffeeOrderId);	
		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return "/index.xhtml?faces-redirect=true";
	}

	/*// Method To Fetch Particular Student Details From The Database
	public List<Student> getStudentById(int studentId) {	
		Student particularStuDObj = new Student();
		List<Student> particularStudentList = new ArrayList<Student>();            
		try {
			transObj = sessionObj.beginTransaction();
			Query queryObj = sessionObj.createQuery("from Student where id= :student_id").setInteger("student_id", studentId);			
			particularStuDObj = (Student)queryObj.uniqueResult();
			particularStudentList = queryObj.list();			
			System.out.println("Student Record With Id: " + studentId + " Is Fetched Successfully From Database");

			// XHTML Response Text
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findStudentById",  studentId);
		} catch(Exception exceptionObj) {
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return particularStudentList;
	}*/

	// Method To Update Particular Student Details In The Database	
	/*public void updateStudentRecord(Student updateStudentObj) {
		try {
			transObj = sessionObj.beginTransaction();
			sessionObj.update(updateStudentObj);		
			System.out.println("Student Record With Id: " + updateStudentObj.getId() + " Is Successfully Updated In Database");	

			// XHTML Response Text
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("updatedStudentRecord",  "Success");
		} catch(Exception exceptionObj){
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
	}*/

	/*@SuppressWarnings("unchecked")
	public List<Student> retrieveStudent() {		
		Student studentsObj;
		List<Student>allStudents = new ArrayList<Student>();
		try {
			transObj = sessionObj.beginTransaction();
			Query queryObj = sessionObj.createQuery("from Student");
			allStudents = queryObj.list();
			for(Student stud : allStudents) {
				studentsObj = new Student(); 								
				studentsObj.setName(stud.getName());
				studentsObj.setDepartment(stud.getDepartment());								
				allStudents.add(studentsObj);  
			}			
			System.out.println("All The Students Records Are Fetched Successfully From Database");

			// XHTML Response Text
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findStudentById", "true");
		} catch(Exception exceptionObj) {
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return allStudents;
	}*/
}