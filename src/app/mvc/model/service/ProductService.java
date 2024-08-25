package app.mvc.model.service;

import app.mvc.exception.NotFoundException;
import app.mvc.model.dao.ProductsDAO;
import app.mvc.model.dao.ProductsDAOImpl;
import app.mvc.model.dto.Products;
import java.sql.SQLException;
import java.util.List;

public class ProductService {

    ProductsDAO productsDAO = new ProductsDAOImpl();

    public List<Products> productSelect() throws NotFoundException, SQLException{
        List<Products> list = productsDAO.productSelect();
        if (list.size() == 0) {
            throw new NotFoundException("상품이 없습니다 다음을 기약해주세요 ㅠㅠ");
        }
        return list;
    }




}
