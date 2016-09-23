package ejb.services;

import ejb.entities.Student;
import ejb.persistence.dao.GenericDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class StudentService {

    @Inject
    private GenericDAO<Student> studentDAO;

    public List<Student> getAllStudents() {
        return studentDAO.getAll();
    }

    public void persist(Student entity) {
        studentDAO.persist(entity);
    }

    public void update(Student entity) {
        studentDAO.update(entity);
    }

    public void delete(Student entity) {
        studentDAO.delete(entity);
    }

    public Student get(Long id) {
        return studentDAO.get(id);
    }
}
