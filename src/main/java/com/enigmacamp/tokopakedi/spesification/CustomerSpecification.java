package com.enigmacamp.tokopakedi.spesification;

import com.enigmacamp.tokopakedi.dto.CustomerSearchDTO;
import com.enigmacamp.tokopakedi.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class CustomerSpecification {
    public static Specification<Customer> getSpecification(CustomerSearchDTO customerSearchDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            // interface atau fungsi yang menghasilkan nilai boolean
            if (customerSearchDTO.getCustomerFullName() != null){
                Predicate customerFullNamePredicate = criteriaBuilder.like(root.get("fullName"), "%" + customerSearchDTO.getCustomerFullName() + "%");
                predicates.add(customerFullNamePredicate);
            }
            if (customerSearchDTO.getCustomerAddress() != null){
                Predicate customerAddressPredicate = criteriaBuilder.like(root.get("address"), "%" + customerSearchDTO.getCustomerAddress() + "%");
                predicates.add(customerAddressPredicate);
            }

            Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
            return criteriaBuilder.and(arrayPredicates);
        };
    }

}
