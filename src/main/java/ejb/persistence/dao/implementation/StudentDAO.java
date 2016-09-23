package ejb.persistence.dao.implementation;

import ejb.aop.annotation.Transactional;
import ejb.entities.Student;
import ejb.persistence.dao.GenericDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Stateless
public class StudentDAO implements GenericDAO<Student> {

    @Inject
    private EntityManager entityManager;

    @Override
    @Transactional
    public void persist(Student entity) {
        entityManager
                .persist(entity);
    }

    @Override
    public Student get(Long id) {
        return entityManager
                .find(Student.class, id);
    }

    @Override
    public void delete(Student entity) {
        entityManager.remove(entity);
    }

    @Override
    public void update(Student entity) {
        entityManager.refresh(entity);
    }

    @Override
    @Transactional
    public List<Student> getAll() {
        return entityManager
                .createNamedQuery("Student.getList", Student.class)
                .getResultList();
    }
}
