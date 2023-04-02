package nus.iss.day24workshop.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import nus.iss.day24workshop.model.Neworder;
import nus.iss.day24workshop.model.Orderdetails;

@Repository
public class OrderRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String INSERTORDER = """
            insert into orders (order_date , customer_name , ship_address , notes ) values (? , ? , ? , ? );
                """;
    private static final String LASTINSERT = "SELECT LAST_INSERT_ID() as id";
    private static final String INSERTDETAILS = """
            insert into order_details (order_id , product , unit_price , quantity) values (? , ?, ? , ?);
                """;

    public Integer addOrder(Neworder neworder) {
        Integer rowsupdated = jdbcTemplate.update(INSERTORDER, neworder.getOrderDate(), neworder.getCustomerName(),
                neworder.getShipAddress(), neworder.getNotes());
        Integer lastinserted = 0;
        if (rowsupdated == 1) {
            Long id = jdbcTemplate.queryForObject(LASTINSERT, Long.class);
            lastinserted = Integer.parseInt(id.toString());
        } else {
            lastinserted = 0;
        }
        return lastinserted;
    }

    public Integer addOrderDetails(Integer orderId, Orderdetails orderdetails) {
        Integer rowsupdated = jdbcTemplate.update(INSERTDETAILS, orderId, orderdetails.getProduct(),
                orderdetails.getUnitPrice(), orderdetails.getQuantity());
        return rowsupdated;
    }

}
