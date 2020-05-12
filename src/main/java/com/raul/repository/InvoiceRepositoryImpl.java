package com.raul.repository;


import com.raul.model.Invoice;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

@Repository
public class InvoiceRepositoryImpl implements InvoiceRepository {

    private final MongoTemplate mongoTemplate;

    public InvoiceRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * this method save in mongoDB
     * @param invoices is used to save
     */
    @Override
    public void save(List<Invoice> invoices) {
        mongoTemplate.insertAll(invoices);
    }

    /**
     * this method is used to mark a pay in mongoDB for the invoice number
     * @param invoiceNumber is used to mark a mongoDB
     */
    @Override
    public void pay(Integer invoiceNumber) {
        Criteria criteria = Criteria.where("invoiceNumber").is(invoiceNumber);
        Update update = new Update();

        update.set("payDate", LocalDate.now());
        mongoTemplate.updateFirst(Query.query(criteria), update, Invoice.class);
    }

    /**
     * @return all the invoices that mongoDB contains
     */
    @Override
    public List<Invoice> findAll() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.ASC, "payDate", "dueDate"));
        return mongoTemplate.find(query, Invoice.class);
    }

    /**
     * @param text the company name
     * @return from mongoDB the invoices that have seller name like the pattern
     */
    @Override
    public List<Invoice> findByText(String text) {
        Pattern pattern = Pattern.compile(text, Pattern.CASE_INSENSITIVE);

        Criteria criteria = Criteria.where("seller.name").regex(pattern);

        Query query = Query.query(criteria);

        query.limit(10);

        return mongoTemplate.find(query, Invoice.class);
    }

    @Override
    public void removeAll() {
        mongoTemplate.remove(new Query(), Invoice.class);
    }
}
