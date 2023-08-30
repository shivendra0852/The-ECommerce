package com.ecommerce.module;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecommerce.entity.Employee;
import com.ecommerce.entity.helper.HelperExtension;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class EmployeeCriteriaRepository {
	
	@PersistenceContext 
	private EntityManager em;

	HelperExtension helper = new HelperExtension();
	
	public List<Employee> findEmployeeByFilters(Integer itemPerPage, Integer pageNumber, String sort, String sortBy, String search, String searchBy) {
	    CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
	    Root<Employee> root = cq.from(Employee.class);
	    CriteriaQuery<Employee> criteriaQuery = cq.select(root);
	    
	    List<Predicate> conditionsList = new ArrayList<>();

	    if (!helper.isNullOrEmpty(searchBy) && !helper.isNullOrEmpty(search)) {
	        conditionsList.add(cb.like(root.get(searchBy).as(String.class), "%" + search + "%"));
	    }

	    if (!helper.isNullOrEmpty(sort) && !helper.isNullOrEmpty(sortBy)) {
	        Expression<?> orderByExpression = root.get(sortBy);
	        Order order = sort.equalsIgnoreCase("asc") ? cb.asc(orderByExpression) : cb.desc(orderByExpression);
	        cq.orderBy(order);
	    }

	    cq.where(cb.and(conditionsList.toArray(new Predicate[0])));

	    if (itemPerPage > 0 && pageNumber > 0) {
	        TypedQuery<Employee> typedQuery = em.createQuery(cq);
	        typedQuery.setFirstResult((pageNumber - 1) * itemPerPage);
	        typedQuery.setMaxResults(itemPerPage);
	        return typedQuery.getResultList();
	    }

	    TypedQuery<Employee> typedQuery = em.createQuery(criteriaQuery);
	    return typedQuery.getResultList();
	}
	
	public long count(Integer itemPerPage, Integer pageNumber, String sort, String sortBy, String search, String searchBy) {
	    CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Long> cq = cb.createQuery(Long.class);
	    Root<Employee> root = cq.from(Employee.class);

	    cq.select(cb.count(root));

	    List<Predicate> conditionsList = new ArrayList<>();

	    if (!helper.isNullOrEmpty(searchBy) && !helper.isNullOrEmpty(search)) {
	        conditionsList.add(cb.like(root.get(searchBy), "%" + search + "%"));
	    }

	    if (!helper.isNullOrEmpty(sort) && !helper.isNullOrEmpty(sortBy)) {
	        Expression<?> orderByExpression = root.get(sortBy);
	        Order order = sort.equalsIgnoreCase("asc") ? cb.asc(orderByExpression) : cb.desc(orderByExpression);
	        cq.orderBy(order);
	    }

	    cq.where(cb.and(conditionsList.toArray(new Predicate[0])));

	    return em.createQuery(cq).getSingleResult();
	}

}
