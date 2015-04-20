package sit.khaycake.servlet.Order;

import com.google.gson.Gson;
import sit.khaycake.database.SQL;
import sit.khaycake.model.Assis.AssisDateTime;
import sit.khaycake.model.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pasuth on 19/4/2558.
 */
public class PatternOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String resource = request.getRequestURI().substring(request.getRequestURI().indexOf("/",1));

        if(resource.indexOf("delete")>=0){
            resource = request.getRequestURI().substring(0, request.getRequestURI().indexOf("/",1));
            SQL sql = new SQL();
            try {
                int a = sql
                        .delete(Order.TABLE_NAME)
                        .where(Order.COLUMN_ID, SQL.WhereClause.Operator.EQ, resource)
                        .exec();
                if(a<0){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }

        }else{
            Order order = null;
            try {
                order = (Order)SQL.findById(Order.class,Integer.parseInt(resource));

            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
            if(order!=null){
                Gson gson = new Gson();
                response.getWriter().print(gson.toJson(order));
            }else{
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String resource = request.getRequestURI().substring(request.getRequestURI().indexOf("/",1));
        Order order = null;
        try {
            order = (Order) SQL.findById(Order.class, Integer.parseInt(resource));
        }catch(Exception e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        if(order!=null){
            order.setCustId(Integer.parseInt(request.getParameter("cusId")));
            order.setOrderDate(AssisDateTime.Date(request.getParameter("orderDate")));
            order.setOrstId(Integer.parseInt(request.getParameter("orstId")));
            order.setShmeId(Integer.parseInt(request.getParameter("shmeId")));
            order.setShtrId(request.getParameter("shtrId"));
            order.setTotalPrice(Double.parseDouble(request.getParameter("totalPrice")));
            order.setTotalQty(Integer.parseInt(request.getParameter("totalQty")));

            SQL sql = new SQL();
            try {
                sql
                        .update(Order.TABLE_NAME)
                        .set(Order.COLUMN_CUST_ID, order.getCustId())
                        .set(Order.COLUMN_ORDER_DATE, order.getOrderDate())
                        .set(Order.COLUMN_ORST_ID, order.getOrstId())
                        .set(Order.COLUMN_SHME_ID, order.getShmeId())
                        .set(Order.COLUMN_SHTR_ID, order.getShmeId())
                        .set(Order.COLUMN_TOTAL_PRICE, order.getTotalPrice())
                        .set(Order.COLUMN_TOTAL_QTY, order.getTotalQty())
                        .where(Order.COLUMN_ID, SQL.WhereClause.Operator.EQ, order.getOrderId())
                        .exec();

            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }else{
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }

}
