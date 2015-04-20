package sit.khaycake.servlet.OrderStatus;

import com.google.gson.Gson;
import sit.khaycake.database.SQL;
import sit.khaycake.model.OrderItem;
import sit.khaycake.model.OrderStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Pasuth on 19/4/2558.
 */
public class OrderStatusServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List orderStatuses = SQL.findAll(OrderStatus.class);
            Gson gson = new Gson();
            String result = gson.toJson(orderStatuses,OrderStatus.class);
            response.getWriter().print(result);
        }catch (Exception ex){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SQL sql = new SQL();
            OrderStatus orderStatus = new OrderStatus();
            orderStatus.setName(request.getParameter("name"));


            int addId = sql
                    .insert()
                    .into(OrderStatus.TABLE_NAME, OrderStatus.COLUMN_NAME)
                    .values(orderStatus.getName())
                    .exec();
            sql.clear();
            orderStatus.setId(addId);

            Gson gson = new Gson();
            response.getWriter().print(gson.toJson(orderStatus));
        }catch (Exception ex){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }
}
