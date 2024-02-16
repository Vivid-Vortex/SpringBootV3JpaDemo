package com.demo.jpa.repository;

import com.demo.jpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * "Spring Data Commons" is the parent project of "Spring Data", which encompasses other individual projects such as "Spring Data LDAP", "Spring Data MongoDB", "Spring Data JPA", "Spring Data JDBC" etc.
 * Project Spring Data Commons - PagingAndSortingRepository extends CrudRepository extends Repository and also one more interface named as QueryDsiPredicateExecutor
 * Project Spring Data JPA - JpaRepository extends PagingAndSortingRepository extends CrudRepository extends Repository and one more interface named as JpaSpecificationExecutor
 */

//@Repository // No need to include @Repository as JPARepository is extends all the other interfaces upto Repository interface, which in turn is implemented by SimpleJpaRepository class.
//    And this class has already has @Repository.
public interface ProductRepository extends JpaRepository<Product, Long> {

   /**
    * It totally depends on us what we want to return. We can either return entity type or Optional of entity type.
    * Both valid.
    */
//   Product findByName(String name);
   Optional<Product> findByName(String name);

   // We are overriding crudrepository findById by overriding it's return type. That is also possible.
   Optional<Product> findById(Long id);

   // We can either return list or single entity as per our chioce and understanding. Since here multipele products can be found with same name and description, so using as return type
   List<Product> findByNameOrDescription(String name, String description);
//   Product findByNameOrDescription(String name, String description);

   // We can either return list or single entity as per our chioce and understanding. Since here multipele products can be found with same name or description, so using as return type
   List<Product> findByNameAndDescription(String name, String description);
//   Product findByNameAndDescription(String name, String description);

   Product findDistinctByName(String name);

}
