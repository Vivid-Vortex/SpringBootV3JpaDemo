package com.demo.jpa.repository;

import com.demo.jpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * "Spring Data Commons" is the parent project of "Spring Data", which encompasses other individual projects such as "Spring Data LDAP", "Spring Data MongoDB", "Spring Data JPA", "Spring Data JDBC" etc.
 * Project Spring Data Commons - PagingAndSortingRepository extends CrudRepository extends Repository and also one more interface named as QueryDsiPredicateExecutor
 * Project Spring Data JPA - JpaRepository extends PagingAndSortingRepository extends CrudRepository extends Repository and one more interface named as JpaSpecificationExecutor
 *
 */

//@Repository // No need to include @Repository as JPARepository is extends all the other interfaces upto Repository interface, which in turn is implemented by SimpleJpaRepository class.
//    And this class has already has @Repository.
public interface ProductRepository extends JpaRepository<Product, Long> {

}
