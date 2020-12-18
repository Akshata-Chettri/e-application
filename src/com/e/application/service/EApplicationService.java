package com.e.application.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e.application.model.Details;
import com.e.application.model.Principal;
import com.e.application.model.Staff;
import com.e.application.model.Student;

@Service
@Transactional
public class EApplicationService {

	@Autowired
	Details details;
	@Autowired
	Student student;
	@Autowired
	Staff staff;
	@Autowired
	Principal principal;

	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

//	find role of user
	public String findRole(int id) {
		String detail = null;
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();
			Query query = s.createQuery("select role from Details where uniqueid=" + id + "");
			detail = (String) query.uniqueResult();
			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detail;
	}

//	find details of user
	public Details findDetails(int id) {
		details = null;
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();
			Query query = s.createQuery("from Details where uniqueid=" + id + "");
			details = (Details) query.uniqueResult();
			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return details;
	}

//	save details
	public void saveDetails(Details details, String role) {
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();
			if (role == "student") {
				if (null == uniqueIdStudent(details.getUniqueid())) {
					student = studentDetails(details, "No Request");
					s.save(student);
				} else {
					studentDetails(details,"No Request");
				}
			} else if (role == "staff") {
				if (null == uniqueIdStaff(details.getUniqueid())) {
					staff = staffDetails(details, "Not Required");
					s.save(staff);
				} else {
					staffDetails(details, "Not Required");
				}
			} else if (role == "principal") {
				if (null == uniqueIdPrincipal(details.getUniqueid())) {
					principal = principalDetails(details, "Not Required");
					s.save(principal);
				} else {
					principalDetails(details, "Not Required");
				}
			}
			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Student uniqueIdStudent(int id) {
		student = null;
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();

			Query query = s.createQuery("from Student where uniqueid=" + id + "");
			student = (Student) query.uniqueResult();

			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	public Staff uniqueIdStaff(int id) {
		staff = null;
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();
			Query query = s.createQuery("from Staff where uniqueid=" + id + "");
			staff = (Staff) query.uniqueResult();
			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return staff;
	}

	public Principal uniqueIdPrincipal(int id) {
		principal = null;
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();
			Query query = s.createQuery("from Principal where uniqueid=" + id + "");
			principal = (Principal) query.uniqueResult();
			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return principal;
	}

//	student details entry
	public Student studentDetails(Details detail, String status) {
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();
			
			student.setUniqueid(detail.getUniqueid());
			student.setFirstname(detail.getFirstname());
			student.setLastname(detail.getLastname());
			student.setBonafideCertificate(status);
			student.setTransferCertificate(status);
			student.setStaffBonafideStatus(status);
			student.setStaffTransferStatus(status);
			student.setPrincipalBonafideStatus(status);
			student.setPrincipalTransferStatus(status);

			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

//	staff details entry
	public Staff staffDetails(Details details, String status) {
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();

			staff.setUniqueid(details.getUniqueid());
			staff.setFirstname(details.getFirstname());
			staff.setLastname(details.getLastname());
			staff.setCertificate(status);

			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return staff;
	}

//	principal details entry
	public Principal principalDetails(Details details, String status) {
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();

			principal.setUniqueid(details.getUniqueid());
			principal.setFirstname(details.getFirstname());
			principal.setLastname(details.getLastname());
			principal.setCertificate(status);

			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return principal;
	}

//	add request
	public Student addCertificate(String certificate) {
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();
			String requested = "requested";
			if (certificate == "Bonafide") {
				Query query = s.createQuery(
						"update Student set bonafideCertificate=:y, staffBonafideStatus=:p, principalBonafideStatus=:p where uniqueid=:id");
				query.setParameter("y", requested);
				query.setParameter("p", "pending");
				query.setParameter("id", student.getUniqueid());
				query.executeUpdate();

				saveInStaff(student, "Bonafide");
			}
			if (certificate == "Transfer") {
				Query query = s.createQuery(
						"update Student set transferCertificate=:y, staffTransferStatus=:p, principalTransferStatus=:p where uniqueid=:id");
				query.setParameter("y", requested);
				query.setParameter("p", "pending");
				query.setParameter("id", student.getUniqueid());
				query.executeUpdate();

				saveInStaff(student, "Transfer");
			}
			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

//	save in staff
	public Staff saveInStaff(Student student, String certificate) {
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();

			staff.setUniqueid(student.getUniqueid());
			staff.setFirstname(student.getFirstname());
			staff.setLastname(student.getLastname());
			staff.setCertificate(certificate);
			s.save(staff);

			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return staff;
	}

//	find staff list
	@SuppressWarnings("unchecked")
	public List<Staff> findStaffList(String certificate) {
		List<Staff> studentList = new ArrayList<Staff>();
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();

			Query query = null;
			if (certificate == "Bonafide") {
				query = s.createQuery("from Staff where certificate='" + certificate + "'");
				studentList = query.list();
				if (studentList.isEmpty()) {
					return null;
				}
			}
			if (certificate == "Transfer") {
				query = s.createQuery("from Staff where certificate='" + certificate + "'");
				studentList = query.list();
				if (studentList.isEmpty()) {
					return null;
				}
			}

			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentList;
	}

//approved request from staff
	public Staff approveRequest(String certificate, String accept, int uniqueid) {
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();

			if (certificate == "Bonafide") {
				Query query = s.createQuery("update Staff set certificate=:y where uniqueid=:id and certificate=:x");
				query.setParameter("y", accept);
				query.setParameter("id", uniqueid);
				query.setParameter("x", certificate);
				query.executeUpdate();

				Staff staffDetails = (Staff) uniqueIdStaff(uniqueid);
				savStaffToPrincipal(staffDetails, "Bonafide");
				updateStatus(uniqueid, "approved", "Bonafide");
			}
			if (certificate == "Transfer") {
				Query query = s.createQuery("update Staff set certificate=:y where uniqueid=:id and certificate=:x");
				query.setParameter("y", accept);
				query.setParameter("id", uniqueid);
				query.setParameter("x", certificate);
				query.executeUpdate();

				Staff staffDetails = (Staff) uniqueIdStaff(uniqueid);
				savStaffToPrincipal(staffDetails, "Transfer");
				updateStatus(uniqueid, "approved", "Transfer");
			}
			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return staff;
	}

//	save staff details into principal
	public Principal savStaffToPrincipal(Staff staffDetails, String certificate) {
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();

			principal.setUniqueid(staffDetails.getUniqueid());
			principal.setFirstname(staffDetails.getFirstname());
			principal.setLastname(staffDetails.getLastname());
			principal.setCertificate(certificate);
			s.save(principal);

			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return principal;
	}

//	rejected request 
	public Student rejectRequest(String certificate, String remove, int uniqueid) {
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();
			if (certificate == "Bonafide") {
				Query query = s.createQuery(
						"update Student set bonafideCertificate=:y, staffBonafideStatus=:s where uniqueid=:id");
				query.setParameter("y", remove);
				query.setParameter("s", "rejected");
				query.setParameter("id", uniqueid);
				query.executeUpdate();

				removeStaffRequest(certificate, uniqueid);
				updateStatus(uniqueid, "rejected", "Bonafide");
			}
			if (certificate == "Transfer") {
				Query query = s.createQuery(
						"update Student set transferCertificate=:y, staffTransferStatus=:s where uniqueid=:id");
				query.setParameter("y", remove);
				query.setParameter("s", "rejected");
				query.setParameter("id", uniqueid);
				query.executeUpdate();

				removeStaffRequest(certificate, uniqueid);
				updateStatus(uniqueid, "rejected", "Transfer");
			}
			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

//	remove accepted request from staff
	public Staff removeStaffRequest(String certificate, int uniqueid) {
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();
			Query query = null;
			if (certificate == "Bonafide") {
//				query = s.createQuery("delete from Staff where uniqueid=:id and certificate=:y");
				query = s.createQuery("update Staff set certificate=:s where uniqueid=:id and certificate=:y");
				query.setParameter("s", "Bonafide Rejected");
				query.setParameter("id", uniqueid);
				query.setParameter("y", certificate);
				query.executeUpdate();
			}
			if (certificate == "Transfer") {
//				query = s.createQuery("delete from Staff where uniqueid=:id and certificate=:y");
				query = s.createQuery("update Staff set certificate=:s where uniqueid=:id and certificate=:y");
				query.setParameter("s", "Transfer Rejected");
				query.setParameter("id", uniqueid);
				query.setParameter("y", certificate);
				query.executeUpdate();
			}
			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return staff;
	}

//	find principal list
	@SuppressWarnings("unchecked")
	public List<Principal> findPrincipalList(String certificate) {
		List<Principal> studentList = new ArrayList<Principal>();
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();

			Query query = null;
			if (certificate == "Bonafide") {
				query = s.createQuery("from Principal where certificate='" + certificate + "'");
				studentList = query.list();
				if (studentList.isEmpty()) {
					return null;
				}
			}
			if (certificate == "Transfer") {
				query = s.createQuery("from Principal where certificate='" + certificate + "'");
				studentList = query.list();
				if (studentList.isEmpty()) {
					return null;
				}
			}

			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentList;

	}

// accepted request
	public Principal acceptedRequest(String certificate, String accept, int uniqueid) {
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();
			if (certificate == "Bonafide") {
				Query query = s.createQuery(
						"update Student set bonafideCertificate=:y, principalBonafideStatus=:s where uniqueid=:id");
				query.setParameter("y", accept);
				query.setParameter("s", "approved");
				query.setParameter("id", uniqueid);
				query.executeUpdate();

				updateRequest(certificate, accept, uniqueid);
			}
			if (certificate == "Transfer") {
				Query query = s.createQuery(
						"update Student set transferCertificate=:y, principalTransferStatus=:s where uniqueid=:id");
				query.setParameter("y", accept);
				query.setParameter("s", "approved");
				query.setParameter("id", uniqueid);
				query.executeUpdate();

				updateRequest(certificate, accept, uniqueid);
			}
			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return principal;
	}

//	remove accepted request from principal
	public Principal updateRequest(String certificate, String status, int uniqueid) {
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();
			Query query = null;
			if (certificate == "Bonafide") {
//				query = s.createQuery("delete from Principal where uniqueid=:id and certificate=:y");
				query = s.createQuery("update Principal set certificate=:s where uniqueid=:id and certificate=:y");
				query.setParameter("s", status);
				query.setParameter("id", uniqueid);
				query.setParameter("y", certificate);
				query.executeUpdate();
			}
			if (certificate == "Transfer") {
//				query = s.createQuery("delete from Principal where uniqueid=:id and certificate=:y");
				query = s.createQuery("update Principal set certificate=:s where uniqueid=:id and certificate=:y");
				query.setParameter("s", status);
				query.setParameter("id", uniqueid);
				query.setParameter("y", certificate);
				query.executeUpdate();
			}
			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return principal;
	}

//	update status in student
	public Student updateStatus(int uniqueid, String status, String certificate) {
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();
			if (certificate == "Bonafide") {
				Query query = s.createQuery("update Student set staffBonafideStatus=:s where uniqueid=:id");
				query.setParameter("s", status);
				query.setParameter("id", uniqueid);
				query.executeUpdate();
			}
			if (certificate == "Transfer") {
				Query query = s.createQuery("update Student set staffTransferStatus=:s where uniqueid=:id");
				query.setParameter("s", status);
				query.setParameter("id", uniqueid);
				query.executeUpdate();
			}
			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

//	find details of student
	public Student findStudentDetails(int id) {
		student = null;
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();
			Query query = s.createQuery("from Student where uniqueid=" + id + "");
			student = (Student) query.uniqueResult();
			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

}
