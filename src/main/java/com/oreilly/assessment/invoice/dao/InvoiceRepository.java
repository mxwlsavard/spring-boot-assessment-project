package com.oreilly.assessment.invoice.dao;

import com.oreilly.assessment.invoice.data.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
    List<Invoice> findAllByCustomerID(Long id);
}
